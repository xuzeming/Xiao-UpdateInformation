package com.zl.SecuritiesSystem.entity;

import java.math.BigDecimal;

public class BankCard {
	private Integer cardId;
	private String cardNum;
	private String cardPwd;
	private String cardType;
	private BigDecimal cardBalance;
	private String cardOwner;
	private String cardTel;
	
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
	public String getCardNum() {
		return cardNum;
	}
	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}
	public String getCardPwd() {
		return cardPwd;
	}
	public void setCardPwd(String cardPwd) {
		this.cardPwd = cardPwd;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public BigDecimal getCardBalance() {
		return cardBalance;
	}
	public void setCardBalance(BigDecimal cardBalance) {
		this.cardBalance = cardBalance;
	}
	public String getCardOwner() {
		return cardOwner;
	}
	public void setCardOwner(String cardOwner) {
		this.cardOwner = cardOwner;
	}
	public String getCardTel() {
		return cardTel;
	}
	public void setCardTel(String cardTel) {
		this.cardTel = cardTel;
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
		return "BankCard [cardId=" + cardId + ", cardNum=" + cardNum + ", cardPwd=" + cardPwd + ", cardType=" + cardType
				+ ", cardBalance=" + cardBalance + ", cardOwner=" + cardOwner + ", cardTel=" + cardTel + "]";
	}
}
