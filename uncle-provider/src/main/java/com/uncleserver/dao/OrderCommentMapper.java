package com.uncleserver.dao;

import java.util.List;

import com.uncleserver.model.OrderComment;

public interface OrderCommentMapper {
	int deleteByPrimaryKey(Integer commentid);

	int insert(OrderComment record);

	int insertSelective(OrderComment record);

	OrderComment selectByPrimaryKey(Integer commentid);

	int updateByPrimaryKeySelective(OrderComment record);

	int updateByPrimaryKey(OrderComment record);

	List<OrderComment> selectByAuntId(Integer auntid);

	List<OrderComment> selectByAuntIdAndPage(Integer auntid, Integer offest, Integer pageSize);

	List<OrderComment> selectByAuntIdsAndPage(String ids, Integer offest, Integer pageSize);

	Long selectAuntScoreTotal(int auntid);

	Long selectAuntScoreCount(int auntid);

	Long selectCompanyScoreTotal(int companyid);

	Long selectCompanyScoreCount(int companyid);
}