package com.zl.SecuritiesSystem.dao;

import org.apache.ibatis.annotations.Param;

import com.zl.SecuritiesSystem.entity.UserAccount;

public interface UserAccountDao {
	
	//根据用户的id查寻出用户的资金账户
	UserAccount queryUserAccountByUserId(Integer userId);
	
	/**
	 * 根据用户的id,修改user_account的两个pwd
	 * @param userId
	 * @param dealPwd
	 * @param billPwd
	 * @return
	 */
	Integer updateTwoPwdByUserId(@Param("userId")Integer userId, @Param("dealPwd")String dealPwd, @Param("billPwd")String billPwd);
	
}
