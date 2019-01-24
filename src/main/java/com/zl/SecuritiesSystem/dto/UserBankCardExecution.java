package com.zl.SecuritiesSystem.dto;

import java.util.List;

import com.zl.SecuritiesSystem.entity.UserBankCard;

public class UserBankCardExecution {
	//操作数据库返回的状态
	private Integer state;
	//状态信息
	private String stateInfo;
	//操作的userAccount
	private UserBankCard userBankCard;
	private List<UserBankCard> userBankCardList;
	public UserBankCardExecution() {
	}
	public UserBankCardExecution(Integer state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}
	public UserBankCardExecution(Integer state, String stateInfo, UserBankCard userBankCard) {
		this.state = state;
		this.stateInfo = stateInfo;
		this.userBankCard = userBankCard;
	}
	public UserBankCardExecution(Integer state, String stateInfo, List<UserBankCard> userBankCardList) {
		this.state = state;
		this.stateInfo = stateInfo;
		this.userBankCardList = userBankCardList;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getStateInfo() {
		return stateInfo;
	}
	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}
	public UserBankCard getUserBankCard() {
		return userBankCard;
	}
	public void setUserBankCard(UserBankCard userBankCard) {
		this.userBankCard = userBankCard;
	}
	public List<UserBankCard> getUserBankCardList() {
		return userBankCardList;
	}
	public void setUserBankCardList(List<UserBankCard> userBankCardList) {
		this.userBankCardList = userBankCardList;
	}
}
