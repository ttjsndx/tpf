package com.java.util.bean;

import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

public class XrProperties {
	private static String XR_PRO_NAME = "xr.properties";
	private static Properties properties = null;
	
	public static String getProperties(String key){
		if(properties == null){
			try {
				System.out.println(XrProperties.class.getClassLoader());
				URL url = XrProperties.class.getClassLoader().getResource(XR_PRO_NAME);
				if(url != null){
					InputStream is = url.openStream();
					properties = new Properties();
					properties.load(is);
					is.close();
				}
			} catch (Exception e) {
				// TODO: handle exception
			}			
		}
		if(properties != null){
			return properties.get(key).toString();
		}
		return "";
	}
}
