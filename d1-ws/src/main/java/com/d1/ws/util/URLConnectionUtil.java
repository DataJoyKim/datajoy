package com.d1.ws.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 
 * @author 김낙영
 *
 * @connectTimeout 서버에 연결되는 Timeout 시간 설정
 * @readTimeout inputStream으로 읽어오는 Timeout 시간 설정
 * 
 */
public class URLConnectionUtil {
	private int connectTimeout = 5000; 
	private int readTimout = 5000;
	
	public void requestHttpGET(String url)  {
		try {
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection(); 
			con.setConnectTimeout(this.connectTimeout); 
			con.setReadTimeout(this.readTimout);
			con.setRequestMethod("GET");

			con.setDoInput(true); // URL로부터 읽기가능여부 (Default : true)
			con.setDoOutput(false); // URL로부터 쓰기가능여부(Default : false)

			StringBuilder sb = new StringBuilder();
			if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
				BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
				
				String line;
				while ((line = br.readLine()) != null) {
					sb.append(line).append("\n");
				}
				br.close();
				System.out.println("" + sb.toString());
			} 
			else {
				System.out.println(con.getResponseMessage());
			}
		} 
		catch (IOException e) {
		}
	}
	
	public void requestHttpPOST(String url, String jsonBody) {
		try {
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setConnectTimeout(this.connectTimeout); 
			con.setReadTimeout(this.readTimout);

			con.setRequestMethod("POST");

			con.setRequestProperty("Content-Type", "application/json");
			con.setDoInput(true); // URL로부터 읽기가능여부 (Default : true)
			con.setDoOutput(true); // URL로부터 쓰기가능여부(Default : false) 
			con.setUseCaches(false);
			con.setDefaultUseCaches(false);

			OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
			wr.write(jsonBody); 
			wr.flush();

			StringBuilder sb = new StringBuilder();
			if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
				BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
				
				String line;
				while ((line = br.readLine()) != null) {
					sb.append(line).append("\n");
				}
				
				br.close();
				System.out.println("" + sb.toString());
			} else {
				System.out.println(con.getResponseMessage());
			}
		} catch (IOException e){
		}
	}
	
	//https 정의필요.
}
