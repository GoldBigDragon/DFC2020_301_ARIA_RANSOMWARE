package goldbigdragon.dfc2020.quiz301.settings;

import java.util.Scanner;

import goldbigdragon.dfc2020.quiz301.main.Main;

public class ManageExtension {
	public void console() {
		Scanner s = new Scanner(System.in);
		String selected = null;
		while(true) {
			System.out.println("[암호화/복호화 대상 확장자 관리]");
			System.out.println("　1. 확장자 추가하기");
			if(Main.encryptTarget.isEmpty())
				System.out.println("　×. 확장자 제거하기");
			else
				System.out.println("　2. 확장자 제거하기");
			System.out.println("　3. 모든 확장자 제거");
			System.out.println("　4. 등록된 확장자 목록");
			System.out.println("　5. 돌아가기");
			System.out.print("> ");
			selected = s.nextLine();
			if(selected.equals("5")) {
				break;
			} else if(selected.equals("1")) {
				System.out.println("　추가할 암호화/복호화 대상은?");
				System.out.print("> ");
				String target = s.nextLine();
				if(Main.encryptTarget.contains(target)) {
					System.out.println("\r\n　" + target + " 확장자는 이미 추가되어 있습니다!");
				} else {
					Main.encryptTarget.add(target);
					System.out.println("\r\n　" + target + " 확장자가 추가 되었습니다!");
				}
			} else if(selected.equals("2")) {
				if(! Main.encryptTarget.isEmpty()) {
					System.out.println("　제거할 암호화/복호화 대상은? ");
					System.out.print("> ");
					String target = s.nextLine();
					for(int count = 0; count < Main.encryptTarget.size(); count++) {
						if(Main.encryptTarget.get(count).equals(target)) {
							Main.encryptTarget.remove(count);
							break;
						}
					}
					System.out.println("\r\n　암호화/복호화 대상 확장자 " + target + " 삭제 완료!");
				}
			} else if(selected.equals("3")) {
				Main.encryptTarget.clear();
				System.out.println("　모든 암호화/복호화 대상 확장자를 제거하였습니다!");
			} else if(selected.equals("4")) {
				System.out.println("　등록된 확장자 목록");
				for(int count = 0; count < Main.encryptTarget.size(); count++) {
					System.out.println("　　." + Main.encryptTarget.get(count));
				}
			}
		}
	}
}
