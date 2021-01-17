package com.d1.ws.util;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;

public class Base64Util {
    public static void main(String[] args) throws UnsupportedEncodingException {
    	String target = "base 64 텍스트 인코딩 테스트";
    	
    	/* encoding */
    	byte[] encodeBytes = target.getBytes(); // text to byte convert
    	String encodeStr = new String(Base64.encodeBase64(encodeBytes)); // base 64 encoding to string
    	
    	System.out.println(encodeStr);
    	
    	/* decoding */
    	byte[] decodeBytes = Base64.decodeBase64(encodeStr); // base 64 decoding
    	String decodeStr = new String(decodeBytes, "UTF-8"); // to string convert utf 8
    	
    	System.out.println(decodeStr);
	}
}
