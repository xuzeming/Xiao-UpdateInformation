package com.zl.SecuritiesSystem.dao;

import org.apache.ibatis.annotations.Param;

public interface UserBankCardDao {
	/**
	 * 添加关联,
	 * 用户的id,与银卡的Id相互关联,cardID是唯一的,userId不唯一
	 * @param userId
	 * @param cardId
	 */
	void addUnion(@Param("userId")Integer userId, @Param("cardId")Integer cardId);
	
	/**
	 * 根据cardid将,需要删除的银行卡与用户的关联删除
	 * @param cardId
	 * @return
	 */
	Integer deleteUnionByCardId(Integer cardId);

	/**
	 * 查询该cardId是否在该表中已经存在
	 * @param cardId
	 * @return
	 */
	Integer cardIsExit(Integer cardId);

}
