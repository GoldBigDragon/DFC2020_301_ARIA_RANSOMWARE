package goldbigdragon.dfc2020.quiz301.settings;

import java.util.Scanner;

import goldbigdragon.dfc2020.quiz301.main.Main;

public class ManageIV {
	public void console() {
		Scanner s = new Scanner(System.in);
		String selected = null;
		while(true) {
			System.out.println("[IV�� ����]");
			System.out.println("�� ���� IV�� : " + Main.IV);
			System.out.println("��1. IV�� �����ϱ�");
			System.out.println("��2. ���ư���");
			System.out.print("> ");
			selected = s.nextLine();
			if(selected.equals("2")) {
				break;
			} else if(selected.equals("1")) {
				System.out.println("�������� IV����? ");
				System.out.println("��(���� ���� 16�� �ʰ� �� 16�� ������ ����)");
				System.out.print("> ");
				String target = s.nextLine();
				Main.IV = target;
				System.out.println("\r\n��IV���� " + Main.IV + " ���� ����Ǿ����ϴ�!");
			}
		}
	}
}
