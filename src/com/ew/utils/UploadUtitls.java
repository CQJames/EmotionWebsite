package com.ew.utils;

import java.util.UUID;

public class UploadUtitls {
	
	public static String getUuidFileName(String fileName){
		int idx = fileName.lastIndexOf(".");
		String extions = fileName.substring(idx);
		return UUID.randomUUID().toString().replace("-","") + extions;
	}
	
	public static String getPath(String uuidFileName){
		int code1 = uuidFileName.hashCode();
		int d1 = code1 & 0xf;
		int code2 = code1 >>> 4;
		int d2 = code2 & 0Xf;
		int code3 = code2 >>> 4;
		int d3 = code3 & 0Xf;
		int code4 = code3 >>> 4;
		int d4 = code4 & 0Xf;
		int code5 = code4 >>> 4;
		int d5 = code5 & 0Xf;
		int code6 = code5 >>> 4;
		int d6 = code6 & 0Xf;
		int code7 = code6 >>> 4;
		int d7 = code7 & 0Xf;
		int code8 = code7 >>> 4;
		int d8 = code8 & 0Xf;
		
		return "/"+d1+"/"+d2+"/"+d3+"/"+d4+"/"+d5+"/"+d6+"/"+d7+"/"+d8;
	}

}
