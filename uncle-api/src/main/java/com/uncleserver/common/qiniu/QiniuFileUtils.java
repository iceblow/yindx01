package com.uncleserver.common.qiniu;

import com.qiniu.util.Auth;
import com.uncleserver.common.Constant;

public class QiniuFileUtils {

	public static String getFileToken(){
		
		Auth auth = Auth.create(Constant.ACCESS_KEY, Constant.SECRET_KEY);
		String upToken = auth.uploadToken(Constant.BUCKET);
		return upToken;
	}
	
	public static String getFileToken(String key){
		Auth auth = Auth.create(Constant.ACCESS_KEY, Constant.SECRET_KEY);
		String upToken = auth.uploadToken(Constant.BUCKET,key);
		return upToken;
	}
	
	public static String getFilePath(String fileName){
		String finalUrl = String.format("%s/%s", Constant.DOMAINOFBUCKET, fileName);
		return finalUrl;
	} 
	
	
	
	
}
