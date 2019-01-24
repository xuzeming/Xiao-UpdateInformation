package com.zl.SecuritiesSystem.service;

import com.zl.SecuritiesSystem.dto.UserAccountExecution;

public interface UserAccountService {
	UserAccountExecution getUserAccountByUserId(Integer userId);

	UserAccountExecution updateDealPwdAndBillPwd(Integer userId, String dealPwd, String billPwd);
}
