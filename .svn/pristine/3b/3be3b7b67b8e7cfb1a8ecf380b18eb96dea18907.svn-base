package com.uncleserver.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.uncleserver.model.AuntMessageSys;
import com.uncleserver.model.MessageSys;

public interface ManagePuseMapper {

	List<MessageSys> getPuseList(@Param(value = "startPage") int startPage, @Param(value = "rows") int rows);

	List<AuntMessageSys> getPuseAuntList(@Param(value = "startPage") int startPage, @Param(value = "rows") int rows);

	Long getPuseListCount();

	Long getPuseAuntListCount();

	int delAuntPuse(int id);

	int delAuntPuseDetail(int id);

	int delPuse(int id);

	int delPuseDetail(int id);
	
	int addPuse(MessageSys record);
	
	int addAuntPuse(MessageSys record);
}
