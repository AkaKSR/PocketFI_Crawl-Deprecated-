package to.na.akaksr.pocketfi.app;

import javax.swing.JOptionPane;

import to.na.akaksr.pocketfi.api.Api;
import to.na.akaksr.pocketfi.vo.PocketFiVO;

//TODO 실행시 로딩 스플래시 프레임 출력
public class Main {
	static final int SCREEN_WIDTH = 250;
	static final int SCREEN_HEIGHT = 400;
	static PocketFiVO vo = new PocketFiVO();
	static Api api = new Api();

	public static void main(String[] args) {
		try {
			switch (args[0]) {
			case "/cli":
				new PocketFi_Data_Usage_CLI();
				break;
				
			case "/gui":
				startGUI();
				break;
				
			default:
				System.out.println("매개변수가 입력되지 않았습니다.");
				System.out.println("/gui = GUI 모드로 실행");
				System.out.println("/cli = CLI 모드로 실행");
				break;
			}
		} catch (Exception e) {
			startGUI();
		}
	}
	
	private static void startGUI() {
		vo.setPassword(JOptionPane.showInputDialog(null, "사용자 비밀번호를 입력하세요\r\n종료하실경우 입력하지말고 버튼을 클릭하세요",
				"SKT 포켓파이 Z 데이터 사용량", JOptionPane.OK_CANCEL_OPTION));
		if (vo.getPassword().equals("")) {
			System.exit(0);
		}
		try {
			new Loding_GUI(api, vo);
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
}
