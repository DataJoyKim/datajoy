package com.d1.ws.util;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class AES256Util {
	public static void main(String[] args) {
		String plainText = "암호화를 해봐용~~~";
		try { 
			long beforeTime = System.currentTimeMillis(); 
			/* 암호화 */
			String encryptText = AES256Util.encrypt(plainText);
			System.out.println(encryptText);
			
			/* 복호화 */
			String decryptText = AES256Util.decrypt(encryptText);
			System.out.println(decryptText);
			
			long afterTime = System.currentTimeMillis(); 
			long secDiffTime = (afterTime - beforeTime); 
			System.out.println("시간차이(m) : "+secDiffTime);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
    private final static String ALG = "AES/CBC/PKCS5Padding"; // padding
    private final static String SECRET_KEY = "01234567890123456789012345678901"; // 32byte
    private final static String IV = SECRET_KEY.substring(0,16); // 16byte
    
	/**
	 * AES256 암호화
	 * 
	 * @param plainText
	 *            	암호화할 평문
	 * @return encryptText 
	 * 				암호문 
	 * @throws NoSuchAlgorithmException
	 * @throws GeneralSecurityException
	 * @throws UnsupportedEncodingException
	 * @throws InvalidAlgorithmParameterException 
	 * @throws InvalidKeyException 키값의 길이가 16이하인경우 발생
	 * @throws NoSuchPaddingException 
	 */
	public static String encrypt(String plainText) throws IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException, InvalidKeyException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchPaddingException {
        Cipher cipher = Cipher.getInstance(ALG);
        SecretKeySpec keySpec = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
        IvParameterSpec ivParamSpec = new IvParameterSpec(IV.getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParamSpec);
        
        byte[] encrypted = cipher.doFinal(plainText.getBytes("UTF-8"));
        return Base64.encodeBase64String(encrypted);
	}

	/**
	 * AES256 복호화
	 * 
	 * @param cipherText
	 *            	복호화할 암호문
	 * @return plainText
	 * 				평문
	 * @throws InvalidAlgorithmParameterException 
	 * @throws InvalidKeyException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws UnsupportedEncodingException 
	 */
	public static String decrypt(String cipherText) throws InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException {
        Cipher cipher = Cipher.getInstance(ALG);
        SecretKeySpec keySpec = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
        IvParameterSpec ivParamSpec = new IvParameterSpec(IV.getBytes());
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParamSpec);
        
        byte[] decodedBytes =  Base64.decodeBase64(cipherText);
        byte[] decrypted = cipher.doFinal(decodedBytes);
        return new String(decrypted, "UTF-8");
	}
}
