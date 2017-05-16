package com.uncleserver.service.company;

import java.util.List;

import com.uncleserver.model.Aunt;
import com.uncleserver.model.Company;
import com.uncleserver.model.CompanyWithBLOBs;
import com.uncleserver.model.FileInfo;
import com.uncleserver.model.Result.PQuery;
import com.uncleserver.model.Result.QueryResult;

public interface CompanyService {

	QueryResult<Aunt> getStaffList(PQuery pquery, Integer companyId, String keywords);
	
	List<Aunt> selectByids(Integer[] ids);
	
	void updateAunt(Aunt aunt);
	
	Aunt selectById(Integer id);
	
	String getFilePathById(Integer fileid);
	
	Company selectCompanyByCid(int companyId);
	
	void updateByPrimaryKeyWithBLOBs(CompanyWithBLOBs record);
	
	FileInfo selectFileInfo(Integer id);
	
	Integer saveFileInfo(String key);
	
	void updateFileInfo(FileInfo file);
}
