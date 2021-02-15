package goldbigdragon.dfc2020.quiz301.crypt;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import goldbigdragon.dfc2020.quiz301.main.Main;

public class CryptThread implements Runnable {

	private boolean isEncrypt = true;
    public CryptThread(boolean isEncrypt) {
    	this.isEncrypt = isEncrypt;
    }

    synchronized private File getFile() {
    	File f = null;
    	if( ! CryptAPI.fileList.isEmpty()) {
    		try{
            	f = CryptAPI.fileList.get(0);
            	CryptAPI.fileList.remove(0);	
    		} catch(Exception e) {
    			return null;
    		}
    	}
    	return f;
    }
    
	public void run() {
		while (true) {
			File target = getFile();
			if(target == null)
				break;
			else {
	            try {
            		crypt(target, isEncrypt);
	            } catch(Exception e) {
	            	e.printStackTrace();
	            	if(isEncrypt)
	            		System.out.println("[ × ] " + target.getPath() + " 파일은 암호화 되지 못하였습니다!");
	            	else
	            		System.out.println("[ × ] " + target.getPath() + " 파일은 복호화 되지 못하였습니다!");
	            }
			}
		}
	}

	protected void crypt(File target, boolean isEncrypt) throws Exception{
        String name = target.getName();
		String path = target.getPath();
		if(isEncrypt) {
			path += "." + Main.encryptExtension;
		} else {
			path = path.substring(0, path.lastIndexOf("."));
	        name = name.substring(0, name.lastIndexOf("."));
		}
		
        File encryptedFile = new File(path);
		FileOutputStream fos = new FileOutputStream(encryptedFile);
		
		long length = target.length();
		if(length > Integer.MAX_VALUE) {
			return;
		}
		
		byte[] targetBinary = Files.readAllBytes(target.toPath());
		String sha256 = getSHA256(name);
		ARIACipher aria = new ARIACipher();
		fos.write(aria.crypt(targetBinary, sha256.getBytes("utf8"), isEncrypt));
        fos.close();
        target.delete();
	}
	
	private String getSHA256(String fileName) {
		String originalSHA256 = null;
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			digest.reset();
			digest.update(fileName.getBytes("utf8"));
			originalSHA256 = String.format("%x", new BigInteger(1, digest.digest()));
		} catch(NoSuchAlgorithmException | IOException e) {
    		System.out.println("[ × ] " + fileName + " 파일의 해시값을 추출하지 못하였습니다!");
		}
		return originalSHA256;
	}
}
