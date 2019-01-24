package com.zl.SecuritiesSystem.dto;

import java.util.List;

import com.zl.SecuritiesSystem.entity.BankCard;

public class BankCardExecution {
	//操作数据库返回的状态
	private Integer state;
	//状态信息
	private String stateInfo;
	//操作的bankcard
	private BankCard bankCard;
	//获取的bankCard列表
	private List<BankCard> bankCardList;
	
	public BankCardExecution() {
	}
	//操作失败的返回构造器
	public BankCardExecution(Integer state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}
	//操作成功返回的构造器
	public BankCardExecution(Integer state, String stateInfo, BankCard bankCard) {
		this.state = state;
		this.stateInfo = stateInfo;
		this.bankCard = bankCard;
	}
	//操作成功返回的构造器
	public BankCardExecution(Integer state, String stateInfo, List<BankCard> bankCardList) {
		super();
		this.state = state;
		this.stateInfo = stateInfo;
		this.bankCardList = bankCardList;
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
	public BankCard getBankCard() {
		return bankCard;
	}
	public void setBankCard(BankCard bankCard) {
		this.bankCard = bankCard;
	}
	public List<BankCard> getBankCardList() {
		return bankCardList;
	}
	public void setBankCardList(List<BankCard> bankCardList) {
		this.bankCardList = bankCardList;
	}

}
