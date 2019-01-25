package com.zl.SecuritiesSystem.util;
import java.util.Map;

/**
 * controller层返回值modelMap对应的多种情况
 * @author 王徐
 *
 */
public class ModelMapUtil {
	
	/**
	 * 出现异常时返回的modelMap
	 * @param modelMap
	 * @param e
	 * @return
	 */
	public static Map<String,Object> modelMapWhileCatchException(Map<String,Object> modelMap, Exception e) {
		e.printStackTrace();
		modelMap.put("success", false);
		modelMap.put("errMsg", e.toString());
		return modelMap;
	}
	
	/**
	 * 所有操作都正确时返回的modelMap
	 * @param modelMap
	 * @return
	 */
	public static Map<String,Object> modelMapWhileTrue(Map<String,Object> modelMap) {
		modelMap.put("success", true);
		return modelMap;
	}
	
	/**
	 * 不满足条件时返回的modelMap
	 * @param modelMap
	 * @param reason
	 * @return
	 */
	public static Map<String,Object> modelMapWhileWrong(Map<String,Object> modelMap, String reason) {
		modelMap.put("success", false);
		modelMap.put("errMsg", reason);
		return modelMap;
	}	
}
