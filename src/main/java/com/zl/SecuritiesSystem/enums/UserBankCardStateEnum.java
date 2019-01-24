package com.zl.SecuritiesSystem.enums;

public enum UserBankCardStateEnum {
	CHECKOFFLINE(-1, "传入了空的信息"), INNER_ERROR(0, "操作失败"), SUCCESS(1, "操作成功");
	private int state;
	private String stateInfo;
	private UserBankCardStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	public int getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public static UserBankCardStateEnum stateOf(int index) {
		for (UserBankCardStateEnum state : values()) {
			if (state.getState() == index) {
				return state;
			}
		}
		return null;
	}
}
