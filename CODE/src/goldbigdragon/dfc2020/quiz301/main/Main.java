package goldbigdragon.dfc2020.quiz301.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import goldbigdragon.dfc2020.quiz301.crypt.CryptAPI;
import goldbigdragon.dfc2020.quiz301.settings.ManageExtension;
import goldbigdragon.dfc2020.quiz301.settings.ManageIV;

public class Main {
	
	private static String version = "0.0.1";
	public static List<String> encryptTarget = new ArrayList<>();
	public static String IV = "dfc_challenge20";
	public static String encryptExtension = "enc";

	public static void main(String[] args) {
		encryptTarget.add("txt");
		encryptTarget.add("png");
		encryptTarget.add("pptx");
		encryptTarget.add("csv");
		encryptTarget.add("hwp");
		encryptTarget.add("pdf");
		encryptTarget.add("xlsx");
		Scanner s = new Scanner(System.in);
		String selected = null;
		while(true) {
			System.out.println("[DFC2020 ��������] " + version + "ver");
			System.out.println("��1. �� ���� �� ���� ��� ��ȣȭ ��Ű��");
			System.out.println("��2. �� ���� �� ���� ��� ��ȣȭ ��Ű��");
			System.out.println("��3. ��ȣȭ/��ȣȭ ��� Ȯ���� ����");
			System.out.println("��4. IV�� ����");
			System.out.println("��5. ����");
			System.out.print("> ");
			selected = s.nextLine();
			if(selected.equals("5")) {
				break;
			} else if(selected.equals("1")) {
				new CryptAPI().crypt(true);
			} else if(selected.equals("2")) {
				new CryptAPI().crypt(false);
			} else if(selected.equals("3")) {
				new ManageExtension().console();
			} else if(selected.equals("4")) {
				new ManageIV().console();
			}
		}
	}
}
