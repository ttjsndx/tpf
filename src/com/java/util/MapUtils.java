package com.java.util;

import java.util.HashMap;

public class MapUtils {
	/**
	 * @date 2018-4-21
	 * @author @2wl
	 * @说明 将约定好的字符串格式"x=1,y=2"转换成HashMap
	 */
	public static HashMap getHashMapByPara(String para){
		if(StringUtils.isBlank(para)){
			return new HashMap();
		}
		String[] str = para.split(",");
		HashMap map = new HashMap();
		for(int i = 0; i < str.length; i++){
			String[] keyValue = str[i].split("=");
			if(keyValue.length > 1){
				map.put(keyValue[0], keyValue[1]);
			}
		}
		return map;
	}
}
