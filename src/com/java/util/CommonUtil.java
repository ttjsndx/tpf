package com.java.util;

import java.io.InputStream;
import java.net.URL;

import com.java.util.bean.XrProperties;

public class CommonUtil {
	
	
	/**
	 * @date 2018-4-21
	 * @author @2wl
	 * @说明 获取当前系统的文件路径分隔符 方向。window:\  linux:/
	 */
	public static String getSystemSeq(){
		return System.getProperty("file.separator");		
	}
	
	/**
	 * @date 2018-4-21
	 * @author @2wl
	 * @param str
	 * @return 返回xr.properties配置文件中的数据
	 */
	public static String getXrProperties(String str){
		return XrProperties.getProperties(str);
	}
}
