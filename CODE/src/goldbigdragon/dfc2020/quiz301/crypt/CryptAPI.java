package goldbigdragon.dfc2020.quiz301.crypt;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import goldbigdragon.dfc2020.quiz301.main.Main;

public class CryptAPI {
	public static List<File> fileList;
	private final int THREAD_AMOUNT = 8;
	
	public List<Thread> threadList = new ArrayList<Thread>();
	
	public void crypt(boolean isEncrypt) {
        if(isEncrypt) {
        	System.out.println("[암호화 시작]");
    		System.out.println("　암호화 대상을 탐색하는 중...");
        }
        else {
        	System.out.println("[복호화 시작]");
    		System.out.println("　복호화 대상을 탐색하는 중...");
        }
        fileList = new ArrayList<>();
		getFiles(new File(System.getProperty("user.dir")), isEncrypt);
        try {
    		for(int count = 0; count < THREAD_AMOUNT; count++) {
    			threadList.add(new Thread(new CryptThread(isEncrypt)));
    		}
    		for(int count = 0; count < THREAD_AMOUNT; count++) {
    			threadList.get(count).start();
    		}
    		for(int count = 0; count < THREAD_AMOUNT; count++) {
    			threadList.get(count).join();
    		}
        } catch(InterruptedException e) {
        	e.printStackTrace();
        }
        
        if(isEncrypt)
        	System.out.println("[암호화 완료]");
        else
        	System.out.println("[복호화 완료]");
        threadList.clear();
	}

	private void getFiles(File dir, boolean isEncrypt) {
		File[] fList = dir.listFiles();
		String filename;
		String extension;
		for (File result : fList) {
			filename = result.getAbsolutePath();
			if (result.isDirectory())
				getFiles(new File(result.getAbsolutePath()), isEncrypt);
			else if (result.isFile()) {
				extension = filename.substring(filename.lastIndexOf(".") + 1);
				if(isEncrypt) {
					if(Main.encryptTarget.contains(extension.toLowerCase()) &&
						(! extension.equals("bat") && ! extension.equals("jar"))) {
						fileList.add(result);
						System.out.println("　　암호화 대상 : " + filename);
					}
				} else {
					if(extension.equals(Main.encryptExtension)) {
						fileList.add(result);
						System.out.println("　　복호화 대상 : " + filename);
					}
				}
			}
		}
	}

}
