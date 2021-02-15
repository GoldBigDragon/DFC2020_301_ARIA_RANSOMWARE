package goldbigdragon.dfc2020.quiz301.crypt;

import goldbigdragon.dfc2020.quiz301.main.Main;

public class ARIACipher {
	private ARIAEngine aria = null;
	private byte[] iv;
	protected final int BLOCK_SIZE = 16;

	public byte[] encrypt(byte[] plain) throws Exception {
		byte[] inputBytes;
		inputBytes = pad(plain, BLOCK_SIZE);
		return cbcEncrypt(inputBytes);
	}

	public byte[] decrypt(byte[] encryptedData) throws Exception {
		byte[] output;
		output = cbcDecrypt(encryptedData);
		return unpad(output, BLOCK_SIZE);
	}

	protected byte[] pad(byte[] inputBytes, int blockSize) {
		if (inputBytes == null)
			return null;
		int offset = inputBytes.length;
		int len = blockSize - (offset % blockSize);
		byte paddingOctet = (byte) (len & 0xff);
		byte[] outputBytes = new byte[offset + len];
		System.arraycopy(inputBytes, 0, outputBytes, 0, inputBytes.length);
		for (int i = offset; i < outputBytes.length; i++) {
			outputBytes[i] = paddingOctet;
		}
		return outputBytes;
	}

	protected byte[] unpad(byte[] in, int blockSize) {
		if (in == null) {
			return null;	
		}
		int len = in.length;
		byte lastByte = in[len - 1];
		int padValue = (int) (lastByte & 0xff);
		if ((padValue < 0x01) || (padValue > blockSize)) {
			return null;
		}
		int offset = len - padValue;
		for (int i = offset; i < len; i++) {
			if (in[i] != padValue) {
				return null;
			}
		}
		byte[] out = new byte[offset];
		System.arraycopy(in, 0, out, 0, offset);
		return out;
	}

	protected byte[] cbcEncrypt(byte[] plain) throws Exception {
		byte[] XORBlock = new byte[plain.length];
		byte[] output = new byte[plain.length];
		int outputIdx = 0;
		for (int i = 0; i < plain.length; i += BLOCK_SIZE) {
			if (i == 0) {
				for (int idx = 0; idx < 16; idx++) {
					XORBlock[idx] = (byte) (plain[idx] ^ this.iv[idx]);
				}
				aria.encrypt(XORBlock, i, output, i);
			} else {
				for (int currentIdx = i; currentIdx < i + BLOCK_SIZE; currentIdx++) {
					XORBlock[currentIdx] = (byte) (plain[currentIdx] ^ output[outputIdx]);
					outputIdx++;
				}
				aria.encrypt(XORBlock, i, output, i);
			}
		}
		return output;
	}
	
	protected byte[] cbcDecrypt(byte[] encryptedBytes) throws Exception{
		byte[] output = new byte[encryptedBytes.length];
		int outputIdx = 0;
		byte[] decryptedBytes = new byte[encryptedBytes.length];
		for (int i = 0; i < encryptedBytes.length; i += BLOCK_SIZE) {
			aria.decrypt(encryptedBytes, i, output, i);
			if (i == 0) {
				for (int idx = 0; idx < 16; idx++) {
					decryptedBytes[idx] = (byte) (this.iv[idx] ^ output[idx]);
				}
			} else {
				for (int currentIdx = i; currentIdx < i + 16; currentIdx++) {
					decryptedBytes[currentIdx] = (byte) (output[currentIdx] ^ encryptedBytes[outputIdx]);
					outputIdx++;
				}
			}
		}
		return decryptedBytes;
	}

	public void setKey(byte[] key) throws Exception {
		aria = new ARIAEngine(256);
		aria.setKey(key);
		aria.setupRoundKeys();
	}
	
	public void setIV(byte[] iv) {
		byte[] out = new byte[16];
		int ivLen = iv.length;
		if(ivLen > 16) {
			System.arraycopy(iv, 0, out, 0, 16);	
		} else {
			System.arraycopy(iv, 0, out, 0, ivLen);
		}
		this.iv = out;
	}

	public byte[] crypt(byte[] plainBytes, byte[] key, boolean isEncrypt) throws Exception {
		setKey(key);
		setIV(Main.IV.getBytes("utf8"));
		if (isEncrypt) {
			return encrypt(plainBytes);
		} else {
			return decrypt(plainBytes);
		}
	}
}
