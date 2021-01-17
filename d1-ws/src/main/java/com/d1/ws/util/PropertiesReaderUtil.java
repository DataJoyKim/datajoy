package com.d1.ws.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReaderUtil {

	public void getExternalReader() throws FileNotFoundException {
		FileReader resources= new FileReader("DB.properties");
		 Properties properties = new Properties();
		 try { properties.load(resources);
		 System.out.println(properties.getProperty("driver"));
		 System.out.println(properties.getProperty("username"));
		 System.out.println(properties.getProperty("password"));
		 System.out.println(properties.getProperty("url"));
		 } catch (IOException e) { e.printStackTrace();
		 }
	}
	
	public void getInternalReader() {
		/*
		String resource = "config/DB.properties";
		 Properties properties = new Properties();
		 try { Reader reader = Resources.getResourceAsReader(resource);
		 properties.load(reader);
		 System.out.println(properties.getProperty("driver"));
		 System.out.println(properties.getProperty("username"));
		 System.out.println(properties.getProperty("password"));
		 System.out.println(properties.getProperty("url"));
		 } catch (IOException e) { e.printStackTrace();
		 }
		 */
	}
}
