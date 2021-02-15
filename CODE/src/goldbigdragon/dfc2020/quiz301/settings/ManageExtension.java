package goldbigdragon.dfc2020.quiz301.settings;

import java.util.Scanner;

import goldbigdragon.dfc2020.quiz301.main.Main;

public class ManageExtension {
	public void console() {
		Scanner s = new Scanner(System.in);
		String selected = null;
		while(true) {
			System.out.println("[��ȣȭ/��ȣȭ ��� Ȯ���� ����]");
			System.out.println("��1. Ȯ���� �߰��ϱ�");
			if(Main.encryptTarget.isEmpty())
				System.out.println("����. Ȯ���� �����ϱ�");
			else
				System.out.println("��2. Ȯ���� �����ϱ�");
			System.out.println("��3. ��� Ȯ���� ����");
			System.out.println("��4. ��ϵ� Ȯ���� ���");
			System.out.println("��5. ���ư���");
			System.out.print("> ");
			selected = s.nextLine();
			if(selected.equals("5")) {
				break;
			} else if(selected.equals("1")) {
				System.out.println("���߰��� ��ȣȭ/��ȣȭ �����?");
				System.out.print("> ");
				String target = s.nextLine();
				if(Main.encryptTarget.contains(target)) {
					System.out.println("\r\n��" + target + " Ȯ���ڴ� �̹� �߰��Ǿ� �ֽ��ϴ�!");
				} else {
					Main.encryptTarget.add(target);
					System.out.println("\r\n��" + target + " Ȯ���ڰ� �߰� �Ǿ����ϴ�!");
				}
			} else if(selected.equals("2")) {
				if(! Main.encryptTarget.isEmpty()) {
					System.out.println("�������� ��ȣȭ/��ȣȭ �����? ");
					System.out.print("> ");
					String target = s.nextLine();
					for(int count = 0; count < Main.encryptTarget.size(); count++) {
						if(Main.encryptTarget.get(count).equals(target)) {
							Main.encryptTarget.remove(count);
							break;
						}
					}
					System.out.println("\r\n����ȣȭ/��ȣȭ ��� Ȯ���� " + target + " ���� �Ϸ�!");
				}
			} else if(selected.equals("3")) {
				Main.encryptTarget.clear();
				System.out.println("����� ��ȣȭ/��ȣȭ ��� Ȯ���ڸ� �����Ͽ����ϴ�!");
			} else if(selected.equals("4")) {
				System.out.println("����ϵ� Ȯ���� ���");
				for(int count = 0; count < Main.encryptTarget.size(); count++) {
					System.out.println("����." + Main.encryptTarget.get(count));
				}
			}
		}
	}
}
