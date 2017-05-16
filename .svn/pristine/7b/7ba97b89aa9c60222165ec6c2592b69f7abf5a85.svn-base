package com.uncleserver.service.api.Impl;

import java.util.Date;
import java.util.HashMap;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.uncleserver.common.qiniu.QiniuFileUtils;
import com.uncleserver.model.FileInfo;
import com.uncleserver.modelVo.ApiResult;
import com.uncleserver.service.api.FileService;

@Service("fileService")
public class FileServiceImpl extends BaseServiceImpl implements FileService {

	@Override
	public ApiResult getUploadToken() {
		ApiResult result = new ApiResult();
		result.setCode("1");
		result.setMessage("请求成功");
		HashMap<String, Object> map = new HashMap<>();
		map.put("uptoken", QiniuFileUtils.getFileToken());
		/*
		 * if(Integer.parseInt(overwrite) == 0){
		 * 
		 * long time = System.currentTimeMillis(); String key =
		 * CommonUtils.MD5(Long.toString(time)+filePath); map.put("key", key);
		 * }else { map.put("upToken", QiniuFileUtils.getFileToken(filePath));
		 * map.put("key", filePath); }
		 */
		result.setResult(map);
		return result;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public ApiResult uploadFilePath(String key, int fileType) {
		ApiResult result = new ApiResult();
		result.setCode("1");
		result.setMessage("请求成功");
		FileInfo fileInfo = new FileInfo();
		fileInfo.setAddtime(new Date());
		fileInfo.setFiletype((byte) fileType);
		fileInfo.setState((byte) 0);
		fileInfo.setFilePath(key);
		fileInfoMapper.insert(fileInfo);
		HashMap<String, Object> map = new HashMap<>();
		map.put("fileid", fileInfo.getFileid());
		result.setResult(map);
		return result;
	}

	@Override
	public String getUploadTokenWeb() {
		HashMap<String, Object> map = new HashMap<>();
		map.put("uptoken", QiniuFileUtils.getFileToken());
		return new Gson().toJson(map);
	}

}
