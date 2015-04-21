package com.sincsmart.uutong.appmodels;

import java.io.Serializable;

public class SdkHttpResult implements Serializable{

	private int code;
	private String result;

	public SdkHttpResult(int code, String result) {
		this.code = code;
		this.result = result;
	}

	public String getResult() {
		return result;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public SdkHttpResult() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

//	@Override
//	public String toString() {
//		return String.format("{\"code\":\"%s\",\"result\":%s}", code,
//				result);
//	}
}
