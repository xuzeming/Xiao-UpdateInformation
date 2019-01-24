package com.zl.SecuritiesSystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zl.SecuritiesSystem.dao.UserCentersDao;
import com.zl.SecuritiesSystem.dto.UserCentersExecution;
import com.zl.SecuritiesSystem.entity.UserCenters;
import com.zl.SecuritiesSystem.enums.UserCentersStateEnum;
import com.zl.SecuritiesSystem.service.UserCentersService;
@Service
public class UserCentersServiceImpl implements UserCentersService {
	
	@Autowired
	private UserCentersDao userCentersDao;
	
	@Override
	public UserCentersExecution getUserInfo(Integer userId) {
		UserCentersExecution userCentersExecution = new UserCentersExecution();
		UserCenters user = userCentersDao.queryUserInfoByUserId(userId);
		if (user != null ) {
			userCentersExecution.setUserCenters(user);
			userCentersExecution.setState(UserCentersStateEnum.SUCCESS.getState());
		} else {
			userCentersExecution.setState(UserCentersStateEnum.INNER_ERROR.getState());
		}
		return userCentersExecution;
	}

	@Override
	public UserCentersExecution updateUserPwd(Integer userId, String newPwd) {
		UserCentersExecution userCentersExecution = new UserCentersExecution();
		Integer effectedNum = userCentersDao.updateUserPwdByUserId(userId, newPwd);
		if (effectedNum >= 0 ) {
			userCentersExecution.setState(UserCentersStateEnum.SUCCESS.getState());
		} else {
			userCentersExecution.setState(UserCentersStateEnum.INNER_ERROR.getState());
		}
		return userCentersExecution;
	}

	@Override
	public UserCentersExecution findPhoneNumOfUserByLogId(Integer userId) {
		UserCentersExecution userCentersExecution = new UserCentersExecution();
		UserCenters userCenters = userCentersDao.phoneNumOfLogUser(userId);
		if (userCenters.getUserTel() != null) {
			userCentersExecution.setUserCenters(userCenters);
			userCentersExecution.setState(UserCentersStateEnum.SUCCESS.getState());;
		} else {
			userCentersExecution.setState(UserCentersStateEnum.INNER_ERROR.getState());
		}
		return userCentersExecution;
	}

}
