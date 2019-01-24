package com.zl.SecuritiesSystem.dao;

import java.util.List;

import com.zl.SecuritiesSystem.entity.BankCard;

/**
 * 
 * @author 王徐
 *
 */
public interface BankCardDao {
	/**
	 * 通过用户的id获取该用户绑定的银行卡
	 * @return
	 */
	List<BankCard> queryBankCardListByUserId(Integer userId);
	
	/**
	 * 用户新增绑定银行卡
	 * @param bankCard
	 * @return
	 */
	Integer addBankCard(BankCard bankCard);
	
	/**
	 * 通过银行卡的Id,获取到该银行卡的信息
	 * @param cardId
	 * @return
	 */
	BankCard queryBankCardByCardId(Integer cardId);
	
	/**
	 * 根据银行卡的CardNum查询数据的Card的信息
	 * @param cardNum
	 * @return
	 */
	BankCard queryBankCardByCardNum(String cardNum);
}
