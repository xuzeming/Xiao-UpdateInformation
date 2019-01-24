package com.zl.SecuritiesSystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zl.SecuritiesSystem.dao.UserAccountDao;
import com.zl.SecuritiesSystem.dto.UserAccountExecution;
import com.zl.SecuritiesSystem.entity.UserAccount;
import com.zl.SecuritiesSystem.enums.UserAccountEnum;
import com.zl.SecuritiesSystem.service.UserAccountService;

@Service
public class UserAccountServiceImpl implements UserAccountService{
	@Autowired
	private UserAccountDao userAccountDao;
	
	@Override
	public UserAccountExecution getUserAccountByUserId(Integer userId) {
		UserAccountExecution userAccountExecution = new UserAccountExecution();
		UserAccount userAccount=userAccountDao.queryUserAccountByUserId(userId);
		if (userAccount != null) {
			userAccountExecution.setUserAccount(userAccount);
			userAccountExecution.setState(UserAccountEnum.SUCCESS.getState());
		} else {
			userAccountExecution.setState(UserAccountEnum.INNER_ERROR.getState());
		}
		return userAccountExecution;
	}

	@Override
	public UserAccountExecution updateDealPwdAndBillPwd(Integer userId, String dealPwd, String billPwd) {
		UserAccountExecution userAccountExecution = new UserAccountExecution();
		Integer effectedNum = userAccountDao.updateTwoPwdByUserId(userId, dealPwd, billPwd);
		if (effectedNum >=0) {
			userAccountExecution.setState(UserAccountEnum.SUCCESS.getState());
		} else {
			userAccountExecution.setState(UserAccountEnum.INNER_ERROR.getState());
		}
		
		return userAccountExecution;
	}
}
