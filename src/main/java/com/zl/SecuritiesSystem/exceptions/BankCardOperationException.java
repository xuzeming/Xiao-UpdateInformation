package com.zl.SecuritiesSystem.exceptions;

public class BankCardOperationException extends RuntimeException{
	private static final long serialVersionUID = 8008671420827580381L;
	public BankCardOperationException(String msg) {
		super(msg);
	}
}
