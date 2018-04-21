package com.java.util.bean;

import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

public class XrProperties {
	private static String XR_PRO_NAME = "xr.properties";
	private static Properties properties = null;
	
	public XrProperties(){
		if(properties == null){
			try {
				URL url = XrProperties.class.getClassLoader().getResource(XR_PRO_NAME);
				if(url != null){
					InputStream is = url.openStream();
					properties.load(is);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}			
		}
	}
	
	public static String getProperties(String key){
		if(properties != null){
			return properties.get(key).toString();
		}
		return "";
	}
}
