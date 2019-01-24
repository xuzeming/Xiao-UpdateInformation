package com.zl.SecuritiesSystem.util;
import java.util.Map;

/**
 * controller层返回值modelMap对应的多种情况
 * @author 王徐
 *
 */
public class ModelMapUtil {
	
	//出现异常时返回的modelMap
	public static Map<String,Object> modelMapWhileCatchException(Map<String,Object> modelMap, Exception e) {
		e.printStackTrace();
		modelMap.put("success", false);
		modelMap.put("errMsg", e.toString());
		return modelMap;
	}
	
	//所有操作都正确时返回的modelMap
	public static Map<String,Object> modelMapWhileTrue(Map<String,Object> modelMap) {
		modelMap.put("success", true);
		return modelMap;
	}
	
	//不满足条件时返回的modelMap
	public static Map<String,Object> modelMapWhileWrong(Map<String,Object> modelMap, String reason) {
		modelMap.put("success", false);
		modelMap.put("errMsg", reason);
		return modelMap;
	}
	
	
	
/*	
	//用户没有登录时返回的modelMap
	public static Map<String,Object> modelMapWhileNotLog(Map<String,Object> modelMap) {
		modelMap.put("success", false);
		modelMap.put("errMsg", "你还未登录,请你先进行登录!");
		return modelMap;
	}
	
	//比较的信息不匹配时返回的modelMap
	public static Map<String,Object> modelMapWhileCheckNotMacth(Map<String,Object> modelMap) {
		modelMap.put("success", false);
		modelMap.put("errMsg", "数据不匹配,请输入正确的信息!");
		return modelMap;
	}
	
	//操作数据库出现错误时返回的modelMap
	public static Map<String,Object> modelMapWhileDatabaseWrong(Map<String,Object> modelMap) {
		modelMap.put("success", false);
		modelMap.put("errMsg", "操作数据库出错!");
		return modelMap;
	}

	//传入参数为空时的返回的modelMap
	public static Map<String,Object> modelMapWhileParamIsNull(Map<String,Object> modelMap) {
		modelMap.put("success", false);
		modelMap.put("errMsg", "参数为空,");
		return modelMap;
	}
*/	
}
