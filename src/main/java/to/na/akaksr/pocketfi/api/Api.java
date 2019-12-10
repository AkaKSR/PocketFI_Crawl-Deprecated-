package to.na.akaksr.pocketfi.api;

import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import to.na.akaksr.pocketfi.app.Main;
import to.na.akaksr.pocketfi.vo.PocketFiVO;

public class Api {
	// Runtime exec
	// 프로그램 실행 후 chromedriver가 자동으로 닫히지 않는 현상 해결
	static Runtime rt = Runtime.getRuntime();
	static Process pc = null;

	// Properties
//	static URL url = Main.class.getResource("/tool/chromedriver.exe");
	static URL url = Main.class.getResource("/chromedriver.exe");
	public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
	public static final String WEB_DRIVER_PATH = url.getFile();
	private String base_url;

	// WebDriver
	private static WebDriver driver;
	private static WebElement webElement;

	public Api() {
		super();

		// System Property SetUp
		System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);

	}

	public WebElement getWebElement() {
		return webElement;
	}

	public PocketFiVO login(PocketFiVO vo) {
		// Driver SetUp
		ChromeOptions options = new ChromeOptions();
		
		// Chrome Headless 모드 실행
		// Headless 모드를 사용 안하면 프로그램 실행시마다 Chrome창이 화면에 노출된다.
		options.addArguments("headless");
		driver = new ChromeDriver(options);

		base_url = "http://192.168.1.1/0_login.html";

		try {
			// get page (= 브라우저에서 url을 주소창에 넣은 후 request 한 것과 같다)
			driver.get(base_url);

			// ID_INPUT_PASSWORD 필드 탐색
			webElement = driver.findElement(By.id("ID_INPUT_PASSWORD"));
			webElement.sendKeys(vo.getPassword());

			// 로그인 버튼 클릭
			webElement = driver.findElement(By.xpath("//*[@id=\"login_kr\"]/input"));
			webElement.click();

			// 로그인 처리를 위한 시간 (1초)
			Thread.sleep(2000);

			return vo;
		} catch (Exception e) {
			close();
			e.printStackTrace();
		}
		return null;
	}

	public PocketFiVO crawl(PocketFiVO vo) {
		// 로그인 이후 데이터 사용량 페이지 접속
		driver.get("http://192.168.1.1/17_data_usage.html");
		try {
			Thread.sleep(1000);

			// 데이터 사용량 페이지의 실 데이터 사용량 크롤링
			webElement = driver.findElement(By.xpath("//*[@id=\"substance\"]/div[1]/div/table/tbody"));
			webElement.getText();

			setVO(webElement, vo, "data");

			// 데이터 사용량 로드 될때까지 대기
			while (vo.getID_TOTAL_RX_BYTES().equals("")) {
				if (vo.getID_TOTAL_RX_BYTES().equals("N/A")) {
					webElement = driver.findElement(By.xpath("//*[@id=\"substance\"]/div[1]/div/table/tbody"));
					webElement.getText();
					setVO(webElement, vo, "data");
				}
			}

			// 데이터 사용량 출력
			System.out.println("[데이터 사용량]");
			System.out.println(webElement.getText());

			return vo;
		} catch (Exception e) {
			close();
			e.printStackTrace();
		}
		return null;
	}

	public PocketFiVO information(PocketFiVO vo) {
		// 페이지 이동
		driver.get("http://192.168.1.1/4_4G_Information.html");
		try {

			// 데이터 사용량 페이지의 실 데이터 사용량 크롤링
			webElement = driver.findElement(By.xpath("//*[@id=\"substance\"]/div[1]/div/table/tbody"));
			setVO(webElement, vo, "info");

			// 데이터 사용량 로드 될때까지 대기
			while (vo.getLTE_SEC_DNS().equals("")) {
				if (vo.getLTE_SEC_DNS().equals("N/A")) {
					driver.get("http://192.168.1.1/4_4G_Information.html");
					webElement = driver.findElement(By.xpath("//*[@id=\"substance\"]/div[1]/div/table/tbody"));
					webElement.getText();
				}
				setVO(webElement, vo, "info");
			}

			// 네트워크 상태 출력
			System.out.println();
			System.out.println("[네트워크 상태]");
			System.out.println(webElement.getText());
			
			Thread.sleep(1000);

			// 데이터 사용량 반환
			return vo;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// PocketFiVO에 해당 자료들을 저장
	public void setVO(WebElement webElement, PocketFiVO vo, String arg) {
		switch (arg) {
		// 데이터 사용량
		case "data":
			// 부팅후 송수신량
			webElement = driver.findElement(By.xpath("//*[@id=\"ID_TX_BYTES\"]"));
			vo.setID_TX_BYTES(webElement.getText());
			webElement = driver.findElement(By.xpath("//*[@id=\"ID_RX_BYTES\"]"));
			vo.setID_RX_BYTES(webElement.getText());

			// 일간 송수신량
			webElement = driver.findElement(By.xpath("//*[@id=\"ID_DAILY_TX_BYTES\"]"));
			vo.setID_DAILY_TX_BYTES(webElement.getText());
			webElement = driver.findElement(By.xpath("//*[@id=\"ID_DAILY_RX_BYTES\"]"));
			vo.setID_DAILY_RX_BYTES(webElement.getText());

			// 월간 송수신량
			webElement = driver.findElement(By.xpath("//*[@id=\"ID_TOTAL_TX_BYTES\"]"));
			vo.setID_TOTAL_TX_BYTES(webElement.getText());
			webElement = driver.findElement(By.xpath("//*[@id=\"ID_TOTAL_RX_BYTES\"]"));
			vo.setID_TOTAL_RX_BYTES(webElement.getText());

			break;

		// LTE 정보
		case "info":
			// 연결 상태
			webElement = driver.findElement(By.xpath("//*[@id=\"LTE_STATUS\"]"));
			vo.setLTE_STATUS(webElement.getText());

			// 연결 시간
			webElement = driver.findElement(By.xpath("//*[@id=\"LTE_USE_TIME\"]"));
			vo.setLTE_USE_TIME(webElement.getText());

			// 전화 번호
			webElement = driver.findElement(By.xpath("//*[@id=\"LTE_DIAL\"]"));
			vo.setLTE_DIAL(webElement.getText());

			// IMEI
			webElement = driver.findElement(By.xpath("//*[@id=\"LTE_IMEI\"]"));
			vo.setLTE_IMEI(webElement.getText());

			// APN NAME
			webElement = driver.findElement(By.xpath("//*[@id=\"LTE_APN_NAME\"]"));
			vo.setLTE_APN_NAME(webElement.getText());

			// MTU 크기
			webElement = driver.findElement(By.xpath("//*[@id=\"LTE_MTU\"]"));
			vo.setLTE_MTU(webElement.getText());

			// IP 주소
			webElement = driver.findElement(By.xpath("//*[@id=\"LTE_IP_ADDR\"]"));
			vo.setLTE_IP_ADDR(webElement.getText());

			// 서브넷 마스크
			webElement = driver.findElement(By.xpath("//*[@id=\"LTE_SUBNETMASK\"]"));
			vo.setLTE_SUBNETMASK(webElement.getText());

			// 기본 게이트웨이
			webElement = driver.findElement(By.xpath("//*[@id=\"LTE_DEF_GATEWAY\"]"));
			vo.setLTE_DEF_GATEWAY(webElement.getText());

			// 기본 DNS
			webElement = driver.findElement(By.xpath("//*[@id=\"LTE_FST_DNS\"]"));
			vo.setLTE_FST_DNS(webElement.getText());

			// 보조 DNS
			webElement = driver.findElement(By.xpath("//*[@id=\"LTE_SEC_DNS\"]"));
			vo.setLTE_SEC_DNS(webElement.getText());

			break;

		default:
			break;
		}
	}
	
	public void close() {
		driver.quit();
	}
}
