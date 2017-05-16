package com.uncleserver.service.manage;

import com.uncleserver.model.AuntCash;
import com.uncleserver.model.Result.PQuery;
import com.uncleserver.model.Result.QueryResult;
import com.uncleserver.modelVo.AuntCashVo;

public interface ManageAuntService {

	/**
	 * 分页查询提现列表
	 * @param pquery
	 * @return
	 */
	QueryResult<AuntCashVo> auntCashPageQuery(PQuery pquery);
	
	
	AuntCash getAuntCashById(Integer id);
	
	void saveAuntCash(AuntCash auntCash);
}
