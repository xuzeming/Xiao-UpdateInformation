package com.zl.SecuritiesSystem.service;

import com.zl.SecuritiesSystem.dto.BankCardExecution;

public interface BankCardService {
	BankCardExecution getBankCardListByUserId(Integer userId);
	
	BankCardExecution getBankCardByCardId(Integer cardId);

	BankCardExecution queryBankCardByBankCardNum(String cardNum);

	
}
