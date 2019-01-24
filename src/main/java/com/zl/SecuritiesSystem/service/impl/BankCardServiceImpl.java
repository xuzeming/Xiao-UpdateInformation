package com.zl.SecuritiesSystem.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zl.SecuritiesSystem.dao.BankCardDao;
import com.zl.SecuritiesSystem.dto.BankCardExecution;
import com.zl.SecuritiesSystem.entity.BankCard;
import com.zl.SecuritiesSystem.enums.CardStateEnum;
import com.zl.SecuritiesSystem.service.BankCardService;

@Service
public class BankCardServiceImpl implements BankCardService{
	@Autowired
	private BankCardDao bankCardDao;

	@Override
	public BankCardExecution getBankCardListByUserId(Integer userId) {
		BankCardExecution bankCardExecution = new BankCardExecution();
		List<BankCard> bankCardList = bankCardDao.queryBankCardListByUserId(userId);
		if (bankCardList != null) {
			bankCardExecution.setBankCardList(bankCardList);
			bankCardExecution.setState(CardStateEnum.SUCCESS.getState());
		} else {
			bankCardExecution.setState(CardStateEnum.INNER_ERROR.getState());
		}
		return bankCardExecution;
	}

	@Override
	public BankCardExecution getBankCardByCardId(Integer cardId) {
		BankCardExecution bankCardExecution = new BankCardExecution();
		BankCard bankCard = bankCardDao.queryBankCardByCardId(cardId);
		if (bankCard != null) {
			bankCardExecution.setBankCard(bankCard);
			bankCardExecution.setState(CardStateEnum.SUCCESS.getState());
		} else {
			bankCardExecution.setState(CardStateEnum.INNER_ERROR.getState());
		}
		return bankCardExecution;
	}

	@Override
	public BankCardExecution queryBankCardByBankCardNum(String cardNum) {
		BankCardExecution bankCardExecution = new BankCardExecution();
		BankCard bankCard = bankCardDao.queryBankCardByCardNum(cardNum);
		if (bankCard != null) {
			bankCardExecution.setBankCard(bankCard);
			bankCardExecution.setState(CardStateEnum.SUCCESS.getState());
		} else {
			bankCardExecution.setState(CardStateEnum.INNER_ERROR.getState());
		}
		return bankCardExecution;
	}



}
