package com.java.util;

public class StringUtils {
	
	/**
	 * @date 2018-4-21
	 * @author @2wl
	 * @说明 判断字符串是否为空 或 全为 空格、tab键、换行符
	 */
	public static boolean isBlank(String str){
		int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((Character.isWhitespace(str.charAt(i)) == false)) {
                return false;
            }
        }
        return true;
	}
	
	/**
	 * @date 2018-4-21
	 * @author @2wl
	 * @说明 判断字符串是否不为空 且 不能全为 空格、tab键、换行符
	 */
	public static boolean isNotBlank(String str){
		return !isBlank(str);
	}
}
