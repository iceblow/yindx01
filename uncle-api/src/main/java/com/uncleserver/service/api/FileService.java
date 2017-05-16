package com.uncleserver.service.api;

import com.uncleserver.modelVo.ApiResult;

public interface FileService {
	
	ApiResult getUploadToken();
	
	ApiResult uploadFilePath(String key,int fileType);
	
	String  getUploadTokenWeb();
	

}
