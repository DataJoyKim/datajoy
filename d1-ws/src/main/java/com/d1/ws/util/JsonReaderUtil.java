package com.d1.ws.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletContext;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonReaderUtil {
	public static void main(String[] args) {
		JsonReaderUtil util = new JsonReaderUtil();
		JSONArray obj = (JSONArray) util.readAsResource("/config/example.json");
		System.out.println(obj);
		
		JSONArray obj2 = (JSONArray) util.readAsAbsolutePath("C:\\eclipse-workspace\\d1-common-core\\src\\main\\resources\\config\\example.json");
		System.out.println(obj2);
		
		//JSONArray obj3 = (JSONArray) util.readAsTest();
		//System.out.println(obj3);
	}
	
	/**
	 * 외부파일을 읽을때 사용.
	 * @param filePath 절대경로
	 * @return
	 */
	public Object readAsAbsolutePath(String filePath) {
		Object obj = null;
		JSONParser parser = new JSONParser();
		
		try {
			obj = parser.parse(new FileReader(filePath));
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		} 
		catch (ParseException e) {
			e.printStackTrace();
		}
		
		return obj;
	}
	/**
	 * src/main/resource 폴더의 파일을 읽을때 사용.
	 * @param filePath resource 하위 경로
	 * @return
	 */
	public Object readAsResource(String filePath) {
		Object obj = null;
		
		JSONParser parser = new JSONParser();
		InputStream in = this.getClass().getResourceAsStream(filePath);
    	InputStreamReader reader = new InputStreamReader(in);
    	
    	try {
			obj = parser.parse(reader);
		} 
    	catch (IOException e) {
			e.printStackTrace();
		} 
    	catch (ParseException e) {
			e.printStackTrace();
		}
    	return obj;
	}
	
	public Object readAsWebInf(ServletContext context) {
		Object obj = null;
		JSONParser parser = new JSONParser();
		InputStream in = context.getResourceAsStream("/WEB-INF/config/example.json");
		try {
	    	InputStreamReader reader = new InputStreamReader(in);
			obj = parser.parse(reader);
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    	catch (IOException e) {
			e.printStackTrace();
		} 
		catch (ParseException e) {
			e.printStackTrace();
		}
    	return obj;
	}
}
