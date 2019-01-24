package com.zl.SecuritiesSystem.entity;

public class UserCenters {
	private Integer userId;
	private Integer userStateId;
	private String userLoginName;
	private String netName;
	private String userTel;
	private String userPwd;
	private Integer accountNum;
	private String capitalPwd;
	private String A1;
	private String A2;
	private String A3;
	private String A4;
	private String A5;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getUserStateId() {
		return userStateId;
	}
	public void setUserStateId(Integer userStateId) {
		this.userStateId = userStateId;
	}
	public String getUserLoginName() {
		return userLoginName;
	}
	public void setUserLoginName(String userLoginName) {
		this.userLoginName = userLoginName;
	}
	public String getNetName() {
		return netName;
	}
	public void setNetName(String netName) {
		this.netName = netName;
	}
	public String getUserTel() {
		return userTel;
	}
	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public Integer getAccountNum() {
		return accountNum;
	}
	public void setAccountNum(Integer accountNum) {
		this.accountNum = accountNum;
	}
	public String getCapitalPwd() {
		return capitalPwd;
	}
	public void setCapitalPwd(String capitalPwd) {
		this.capitalPwd = capitalPwd;
	}
	public String getA1() {
		return A1;
	}
	public void setA1(String a1) {
		A1 = a1;
	}
	public String getA2() {
		return A2;
	}
	public void setA2(String a2) {
		A2 = a2;
	}
	public String getA3() {
		return A3;
	}
	public void setA3(String a3) {
		A3 = a3;
	}
	public String getA4() {
		return A4;
	}
	public void setA4(String a4) {
		A4 = a4;
	}
	public String getA5() {
		return A5;
	}
	public void setA5(String a5) {
		A5 = a5;
	}
	@Override
	public String toString() {
		return "UserCenters [userId=" + userId + ", userStateId=" + userStateId + ", userLoginName=" + userLoginName
				+ ", netName=" + netName + ", userTel=" + userTel + ", userPwd=" + userPwd + ", accountNum="
				+ accountNum + ", capitalPwd=" + capitalPwd + "]";
	}
}
