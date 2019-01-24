package com.zl.SecuritiesSystem.entity;

import java.math.BigDecimal;
import java.util.Date;

public class UserAccount {
	private Integer accountId;
	private Integer userId;
	private Integer accountNum;
	private BigDecimal accountBalance;
	private Date accountOpenTime;
	private String TransationPwd;
	private String CapitalPwd;
	private Integer orgId;
	private Integer shAccountNum;
	private Integer szAccountNum;
	private String A1;
	private String A2;
	private String A3;
	private String A4;
	private String A5;
	public Integer getAccountId() {
		return accountId;
	}
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getAccountNum() {
		return accountNum;
	}
	public void setAccountNum(Integer accountNum) {
		this.accountNum = accountNum;
	}
	public BigDecimal getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(BigDecimal accountBalance) {
		this.accountBalance = accountBalance;
	}
	public Date getAccountOpenTime() {
		return accountOpenTime;
	}
	public void setAccountOpenTime(Date accountOpenTime) {
		this.accountOpenTime = accountOpenTime;
	}
	public String getTransationPwd() {
		return TransationPwd;
	}
	public void setTransationPwd(String transationPwd) {
		TransationPwd = transationPwd;
	}
	public String getCapitalPwd() {
		return CapitalPwd;
	}
	public void setCapitalPwd(String capitalPwd) {
		CapitalPwd = capitalPwd;
	}
	public Integer getOrgId() {
		return orgId;
	}
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	public Integer getShAccountNum() {
		return shAccountNum;
	}
	public void setShAccountNum(Integer shAccountNum) {
		this.shAccountNum = shAccountNum;
	}
	public Integer getSzAccountNum() {
		return szAccountNum;
	}
	public void setSzAccountNum(Integer szAccountNum) {
		this.szAccountNum = szAccountNum;
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
		return "UserAccount [accountId=" + accountId + ", userId=" + userId + ", accountNum=" + accountNum
				+ ", accountBalance=" + accountBalance + ", accountOpenTime=" + accountOpenTime + ", TransationPwd="
				+ TransationPwd + ", CapitalPwd=" + CapitalPwd + ", orgId=" + orgId + ", shAccountNum=" + shAccountNum
				+ ", szAccountNum=" + szAccountNum + "]";
	}
}
