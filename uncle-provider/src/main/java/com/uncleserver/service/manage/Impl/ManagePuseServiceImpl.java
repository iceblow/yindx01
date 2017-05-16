package com.uncleserver.service.manage.Impl;

import java.util.Date;
import java.util.List;

import com.mysql.fabric.xmlrpc.base.Data;
import com.uncleserver.common.CommonUtils;
import com.uncleserver.model.AuntMessageSys;
import com.uncleserver.model.MessageSys;
import com.uncleserver.model.Result.PQuery;
import com.uncleserver.model.Result.QueryResult;
import com.uncleserver.service.api.Impl.BaseServiceImpl;
import com.uncleserver.service.manage.ManagePuseService;

public class ManagePuseServiceImpl extends BaseServiceImpl implements ManagePuseService {

	@Override
	public QueryResult<MessageSys> getPuseList(PQuery pquery) {
		List<MessageSys> puseList = managePuseMapper.getPuseList(pquery.getStartPage(), pquery.getRows());
		long count = managePuseMapper.getPuseListCount();

		return new QueryResult<MessageSys>(puseList, count, pquery.getPage(), pquery.getRows());
	}

	@Override
	public QueryResult<AuntMessageSys> getPuseAuntList(PQuery pquery) {
		List<AuntMessageSys> puseList = managePuseMapper.getPuseAuntList(pquery.getStartPage(), pquery.getRows());
		long count = managePuseMapper.getPuseAuntListCount();

		return new QueryResult<AuntMessageSys>(puseList, count, pquery.getPage(), pquery.getRows());
	}

	@Override
	public int delAuntPuse(int id) {
		int result = 0;
		if (managePuseMapper.delAuntPuseDetail(id) > 0) {
			result = managePuseMapper.delAuntPuse(id);
		}
		return result;
	}

	@Override
	public int delPuse(int id) {
		int result = 0;
		if (managePuseMapper.delPuseDetail(id) > 0) {
			result = managePuseMapper.delPuse(id);
		}
		return result;
	}

	@Override
	public int addPuse(String type, String title, String detail, String linkTitle, String linkType, String linkContent,
			String city) {
		int result = 0;
		MessageSys messageSys = new MessageSys();
		messageSys.setAddtime(new Date());
		messageSys.setCity(city);
		messageSys.setDel_state((byte) 0);
		messageSys.setDetail(detail);
		messageSys.setLink_content(linkContent);
		messageSys.setLink_title(linkTitle);
		if (linkType != null)
			messageSys.setLink_type((byte) CommonUtils.parseInt(linkType, 0));
		messageSys.setTitle(title);
		messageSys.setType((byte) CommonUtils.parseInt(type, 0));
		result = managePuseMapper.addPuse(messageSys);
		return result;
	}

	@Override
	public int addAuntPuse(String type, String title, String detail, String linkTitle, String linkType,
			String linkContent, String city) {
		int result = 0;
		MessageSys messageSys = new MessageSys();
		messageSys.setAddtime(new Date());
		messageSys.setCity(city);
		messageSys.setDel_state((byte) 0);
		messageSys.setDetail(detail);
		messageSys.setLink_content(linkContent); 
		messageSys.setLink_title(linkTitle);
		if(linkType!=null)
		messageSys.setLink_type((byte) CommonUtils.parseInt(linkType, 0));
		messageSys.setTitle(title);
		messageSys.setType((byte) CommonUtils.parseInt(type, 0));
		// Data addtime = new Data();
		result=managePuseMapper.addAuntPuse(messageSys);
		return result;
	}

}
