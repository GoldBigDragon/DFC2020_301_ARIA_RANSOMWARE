package goldbigdragon.dfc2020.quiz301.settings;

import java.util.Scanner;

import goldbigdragon.dfc2020.quiz301.main.Main;

public class ManageIV {
	public void console() {
		Scanner s = new Scanner(System.in);
		String selected = null;
		while(true) {
			System.out.println("[IV값 관리]");
			System.out.println("※ 현재 IV값 : " + Main.IV);
			System.out.println("　1. IV값 변경하기");
			System.out.println("　2. 돌아가기");
			System.out.print("> ");
			selected = s.nextLine();
			if(selected.equals("2")) {
				break;
			} else if(selected.equals("1")) {
				System.out.println("　변경할 IV값은? ");
				System.out.println("　(영문 기준 16자 초과 시 16자 까지만 적용)");
				System.out.print("> ");
				String target = s.nextLine();
				Main.IV = target;
				System.out.println("\r\n　IV값이 " + Main.IV + " 으로 변경되었습니다!");
			}
		}
	}
}
