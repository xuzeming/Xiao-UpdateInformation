package com.zl.SecuritiesSystem.entity;

public class UserBankCard {
	private Integer cardId;
	private Integer userId;
	
	private String A1;
	private String A2;
	private String A3;
	private String A4;
	private String A5;
	public Integer getCardId() {
		return cardId;
	}
	public void setCardId(Integer cardId) {
		this.cardId = cardId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
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
		return "UserBankCard [cardId=" + cardId + ", userId=" + userId + "]";
	}
}
