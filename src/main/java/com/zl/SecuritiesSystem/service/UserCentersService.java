package com.zl.SecuritiesSystem.service;

import com.zl.SecuritiesSystem.dto.UserCentersExecution;

public interface UserCentersService {

	UserCentersExecution getUserInfo(Integer userId);

	UserCentersExecution updateUserPwd(Integer userId, String newPwd);

	UserCentersExecution findPhoneNumOfUserByLogId(Integer userId);

}
