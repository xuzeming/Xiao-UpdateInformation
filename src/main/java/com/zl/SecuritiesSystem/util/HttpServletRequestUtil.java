package com.zl.SecuritiesSystem.util;

import javax.servlet.http.HttpServletRequest;

/**
 * 前端数据获取util类
 * @author 王徐
 *
 */
public class HttpServletRequestUtil {
	//获取前端传递过来的Int类型参数
	public static int getInt(HttpServletRequest request, String key){
		try {
			return Integer.decode(request.getParameter(key));
		} catch (Exception e) {
			return -1;
		}
	}
	
	//获取前端传递过来的Long类型参数
	public static long getLong(HttpServletRequest request, String key){
		try {
			return Long.valueOf(request.getParameter(key));
		} catch (Exception e) {
			return -1;
		}
	}
	
	//获取前端传递过来的double类型参数
	public static double getDouble(HttpServletRequest request, String key){
		try {
			return Double.valueOf(request.getParameter(key));
		} catch (Exception e) {
			return -1d;
		}
	}
	
	//获取前端传递过来的Boolean类型参数
	public static boolean getBoolean(HttpServletRequest request, String key){
		try {
			return Boolean.valueOf(request.getParameter(key));
		} catch (Exception e) {
			return false;
		}
	}
	
	//获取前端传递过来的String类型参数
	public static String getString(HttpServletRequest request, String key){
		try {
			String result = request.getParameter(key);
			if (result != null) {
				result = result.trim();
			}
			if ("".equals(result)) {
				result = null;
			}
			return result;
		} catch (Exception e) {
			return null;
		}
	}
}