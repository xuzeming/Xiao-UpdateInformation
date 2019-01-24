package com.zl.SecuritiesSystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zl.SecuritiesSystem.dao.UserBankCardDao;
import com.zl.SecuritiesSystem.dto.UserBankCardExecution;
import com.zl.SecuritiesSystem.enums.UserBankCardStateEnum;
import com.zl.SecuritiesSystem.service.UserBankCardService;

@Service
public class UserBankCardServiceImpl implements UserBankCardService{
	
	@Autowired
	private UserBankCardDao userBankCardDao;
	
	@Override
	public void addUnion(Integer userId, Integer cardId) {
		userBankCardDao.addUnion(userId, cardId);
	}

	@Override
	public UserBankCardExecution deleteUnion(Integer cardId) {
		UserBankCardExecution userBankCardExecution = new UserBankCardExecution();
		Integer effectedNum = userBankCardDao.deleteUnionByCardId(cardId);
		if (effectedNum<=0) {
			userBankCardExecution.setState(UserBankCardStateEnum.INNER_ERROR.getState());
		} else {
			userBankCardExecution.setState(UserBankCardStateEnum.SUCCESS.getState());
		}
		return userBankCardExecution;
	}

	@Override
	public Integer cardIsExit(Integer cardId) {
		return userBankCardDao.cardIsExit(cardId);
	}

}
