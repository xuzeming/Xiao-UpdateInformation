package com.zl.SecuritiesSystem.dao;

import org.apache.ibatis.annotations.Param;

import com.zl.SecuritiesSystem.entity.UserCenters;

public interface UserCentersDao {
	/**
	 * 根据用户的id,查寻用户的信息
	 * 在这里我们只查用户的手机号与用户的密码
	 * @param userId
	 * @return
	 */
	UserCenters queryUserInfoByUserId(Integer userId);
	
	/**
	 * 根据用户的id修改用户的密码
	 * @param newPwd
	 * @return
	 */
	Integer updateUserPwdByUserId(@Param("userId")Integer userId, @Param("newPwd")String newPwd);
	
	/**
	  *    根据用户的id查询用户的手机号
	 * @param userId
	 * @return
	 */
	UserCenters phoneNumOfLogUser(Integer userId);

}
