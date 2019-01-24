package com.zl.SecuritiesSystem.service;

import com.zl.SecuritiesSystem.dto.UserBankCardExecution;

public interface UserBankCardService {

	void addUnion(Integer userId, Integer cardId);

	UserBankCardExecution deleteUnion(Integer cardId);

	Integer cardIsExit(Integer cardId);

}
