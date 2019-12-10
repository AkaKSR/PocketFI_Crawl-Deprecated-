package to.na.akaksr.pocketfi.app;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

import to.na.akaksr.pocketfi.api.Api;
import to.na.akaksr.pocketfi.vo.PocketFiVO;

public class Loding_GUI extends JFrame {
	private static final long serialVersionUID = -4960367095840535419L;
	static final int SCREEN_WIDTH = 400;
	static final int SCREEN_HEIGHT = 250;
	private static JProgressBar progress;
	private static JLabel splashLabel = new JLabel(new ImageIcon(Loding_GUI.class.getResource("/splash/splash.gif")));
//	private static JLabel splashLabel = new JLabel(new ImageIcon(Loding_GUI.class.getResource("/res/splash/splash.gif")));

	public Loding_GUI(Api api, PocketFiVO vo) {
		getContentPane().setBackground(Color.WHITE);

		setUndecorated(true);
		setTitle("PocketFi_Data_Usage");
		setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		getContentPane().setLayout(null);
		
		splashLabel.setBounds(14, 12, 372, 204);
		getContentPane().add(splashLabel);
		
		progress = new JProgressBar();
		progress.setStringPainted(true);
		progress.setValue(0);
		progress.setBounds(0, 228, 400, 22);
		getContentPane().add(progress);
		
		try {
			progress.setString("ChromeDriver 초기화중...");
			progress.setValue(0);
			Thread.sleep(1500);
			// 1. login
			progress.setString("WebCM 접속중...");
			progress.setValue(20);
			progress.setString("WebCM 로그인중...");
			progress.setValue(30);
			vo = api.login(vo);
			// 2. crawl
			progress.setString("데이터 사용량 수집중...");
			progress.setValue(50);
			vo = api.crawl(vo);
			// 3. information
			progress.setString("가입자 정보 수집중...");
			progress.setValue(80);
			vo = api.information(vo);
			// 4. 내부 자료 정리 대기
			progress.setString("실행 초기화중...");
			progress.setValue(90);
			Thread.sleep(1000);
			progress.setString("프로그램 실행");
			progress.setValue(100);
			
			new PocketFi_Data_Usage_GUI(vo, api);
			dispose();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
