package com.d1.ws.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.codec.binary.Base64;

public class RSAUtil {
	public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException {
        // 암호화 할 평문
		String plainText = "RSA를 테스트 해볼까요?????????";
        
		// 공개키, 개인키 생성
        KeyPair keyPair = RSAUtil.genRSAKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();
        
        //=============================================================
        // 공개키 개인키로 암호화 복호화 메소드 테스트
        //=============================================================
        // 암호화
        String encrypted = RSAUtil.encryptRSA(plainText, publicKey);
        System.out.println(encrypted);
        
        // 복호화
        String decrypted = RSAUtil.decryptRSA(encrypted, privateKey);
        System.out.println(decrypted);
        
        
        //=============================================================
        // 인코딩 된 공개키 개인키로 암호화 복호화 테스트
        //=============================================================
        // 공개키를 인코딩
        byte[] bytePublicKey = publicKey.getEncoded();
        String base64PublicKey = Base64.encodeBase64String(bytePublicKey);

        // 개인키를 인코딩
        byte[] bytePrivateKey = privateKey.getEncoded();
        String base64PrivateKey = Base64.encodeBase64String(bytePrivateKey);

        // 인코딩 된 공개키로 암호화
        PublicKey encodedPublicKey = RSAUtil.decodeBase64PublicKey(base64PublicKey);
        String encryptedByEncodedPublicKey = RSAUtil.encryptRSA(plainText, encodedPublicKey);
        System.out.println(encryptedByEncodedPublicKey);
        // 개인키로 복호화
        String decryptedRe = RSAUtil.decryptRSA(encryptedByEncodedPublicKey, privateKey);
        System.out.println(decryptedRe);
        // 인코딩 된 개인키로 복호화
        PrivateKey decodedPrivateKey = RSAUtil.decodeBase64PrivateKey(base64PrivateKey);
        String decryptedByDecodedPrivateKey = RSAUtil.decryptRSA(encryptedByEncodedPublicKey, decodedPrivateKey);
        System.out.println(decryptedByDecodedPrivateKey);
	}
	
	/**
     * 1024비트 RSA 키쌍을 생성
     */
    public static KeyPair genRSAKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator gen = KeyPairGenerator.getInstance("RSA");
        gen.initialize(1024, new SecureRandom());
        return gen.genKeyPair();
    }

    /**
     * 공개키로 RSA 암호화를 수행
     * 
     * @param plainText 평문
     * @param publicKey 공개키
     * @return 암호문
     */
    public static String encryptRSA(String plainText, PublicKey publicKey)
            throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        byte[] bytePlain = cipher.doFinal(plainText.getBytes());
        return Base64.encodeBase64String(bytePlain);
    }

    /**
     * 개인키로 RSA 복호화를 수행
     * 
     * @param encrypted 암호문
     * @param privateKey 개인키
     * @return 평문
     */
    public static String decryptRSA(String encrypted, PrivateKey privateKey)
            throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException {
        Cipher cipher = Cipher.getInstance("RSA");
        byte[] byteEncrypted = Base64.decodeBase64(encrypted.getBytes());

        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] bytePlain = cipher.doFinal(byteEncrypted);
        return new String(bytePlain, "utf-8");
    }

    /**
     * 인코딩 된 공개키를 디코딩
     * 
     * @param base64PublicKey 인코딩 된 공개키
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public static PublicKey decodeBase64PublicKey(String base64PublicKey)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] decodedBase64PubKey = Base64.decodeBase64(base64PublicKey);

        return KeyFactory.getInstance("RSA")
                .generatePublic(new X509EncodedKeySpec(decodedBase64PubKey));
    }

    /**
     * 인코딩 된 개인키를 디코딩
     * 
     * @param base64PrivateKey 인코딩 된 개인키
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public static PrivateKey decodeBase64PrivateKey(String base64PrivateKey)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] decodedBase64PrivateKey = Base64.decodeBase64(base64PrivateKey);

        return KeyFactory.getInstance("RSA")
                .generatePrivate(new PKCS8EncodedKeySpec(decodedBase64PrivateKey));
    }
}
