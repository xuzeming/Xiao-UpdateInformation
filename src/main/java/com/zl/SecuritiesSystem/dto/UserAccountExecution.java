package com.zl.SecuritiesSystem.dto;

import java.util.List;

import com.zl.SecuritiesSystem.entity.UserAccount;

public class UserAccountExecution {
	//操作数据库返回的状态
	private Integer state;
	//状态信息
	private String stateInfo;
	//操作的userAccount
	private UserAccount userAccount;
	//获取的userAccountList列表
	private List<UserAccount> userAccountList;
	public UserAccountExecution() {
	}
	public UserAccountExecution(Integer state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}
	public UserAccountExecution(Integer state, String stateInfo, UserAccount userAccount) {
		this.state = state;
		this.stateInfo = stateInfo;
		this.userAccount = userAccount;
	}
	public UserAccountExecution(Integer state, String stateInfo, List<UserAccount> userAccountList) {
		this.state = state;
		this.stateInfo = stateInfo;
		this.userAccountList = userAccountList;
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
	public UserAccount getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(UserAccount userAccount2) {
		this.userAccount = userAccount2;
	}
	public List<UserAccount> getUserAccountList() {
		return userAccountList;
	}
	public void setUserAccountList(List<UserAccount> userAccountList) {
		this.userAccountList = userAccountList;
	}
}
