package to.na.akaksr.pocketfi.app;

import java.util.Scanner;

import to.na.akaksr.pocketfi.api.Api;
import to.na.akaksr.pocketfi.vo.PocketFiVO;

public class PocketFi_Data_Usage_CLI {
	Api api = new Api();
	PocketFiVO vo = new PocketFiVO();

	public PocketFi_Data_Usage_CLI() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Password > ");
		vo.setPassword(sc.nextLine());
		try {
			while (true) {
				api.login(vo);
				Thread.sleep(5000);
			}
		} catch (Exception e) {
			api.close();
			System.out.println("CLI 오류");
			e.printStackTrace();
		} finally {
			api.close();
		}
		sc.close();
	}
}
