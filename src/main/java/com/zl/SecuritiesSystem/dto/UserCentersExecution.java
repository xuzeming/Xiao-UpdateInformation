package com.zl.SecuritiesSystem.dto;

import java.util.List;

import com.zl.SecuritiesSystem.entity.UserCenters;

public class UserCentersExecution {
	//操作数据库返回的状态
	private Integer state;
	//状态信息
	private String stateInfo;
	//操作的userAccount
	private UserCenters userCenters;
	private List<UserCenters> userCentersList;
	public UserCentersExecution() {
	}
	public UserCentersExecution(Integer state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}
	public UserCentersExecution(Integer state, String stateInfo, List<UserCenters> userCentersList) {
		this.state = state;
		this.stateInfo = stateInfo;
		this.userCentersList = userCentersList;
	}
	public UserCentersExecution(Integer state, String stateInfo, UserCenters userCenters) {
		this.state = state;
		this.stateInfo = stateInfo;
		this.userCenters = userCenters;
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
	public UserCenters getUserCenters() {
		return userCenters;
	}
	public void setUserCenters(UserCenters userCenters) {
		this.userCenters = userCenters;
	}
	public List<UserCenters> getUserCentersList() {
		return userCentersList;
	}
	public void setUserCentersList(List<UserCenters> userCentersList) {
		this.userCentersList = userCentersList;
	}
}
