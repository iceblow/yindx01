package com.uncleserver.service.manage.Impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.uncleserver.model.AuntCash;
import com.uncleserver.model.Result.PQuery;
import com.uncleserver.model.Result.QueryResult;
import com.uncleserver.modelVo.AuntCashVo;
import com.uncleserver.service.api.Impl.BaseServiceImpl;
import com.uncleserver.service.manage.ManageAuntService;

@Service("manageAuntService")
public class ManageAuntServiceImpl extends BaseServiceImpl implements ManageAuntService{

	/**
	 * 分页查询阿姨提现列表
	 */
	@Override
	public QueryResult<AuntCashVo> auntCashPageQuery(PQuery pquery) {
		List<AuntCashVo> cashlist = auntCashMapper.getAuntCashList(pquery.getStartPage(), pquery.getRows());
		Long cashcount = auntCashMapper.auntCashListCount();
		QueryResult<AuntCashVo> result = new QueryResult<AuntCashVo>(cashlist,cashcount,pquery.getPage(),pquery.getRows());
		return result;
	}

	@Override
	public AuntCash getAuntCashById(Integer id) {
		return auntCashMapper.selectByPrimaryKey(id);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public void saveAuntCash(AuntCash auntCash) {
		auntCashMapper.updateByPrimaryKeySelective(auntCash);
	}

}
