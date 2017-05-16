package com.uncleserver.service.manage;


import com.uncleserver.model.AuntMessageSys;
import com.uncleserver.model.MessageSys;
import com.uncleserver.model.Result.PQuery;
import com.uncleserver.model.Result.QueryResult;

public interface ManagePuseService {
	QueryResult<MessageSys> getPuseList(PQuery pquery);
	QueryResult<AuntMessageSys> getPuseAuntList(PQuery pquery);
	int delAuntPuse(int id);
	int delPuse(int id);
	int addPuse(String type, String title, String detail, String linkTitle, String linkType,
			String linkContent, String city);
	int addAuntPuse(String type, String title, String detail, String linkTitle, String linkType,
			String linkContent, String city);
}
