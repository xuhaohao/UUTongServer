package com.sincsmart.uutong.controllers;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.jfinal.core.Controller;
import com.mysql.jdbc.StringUtils;
import com.sincsmart.uutong.appmodels.AppVersion;
import com.sincsmart.uutong.appmodels.SdkHttpResult;
import com.sincsmart.uutong.models.VersionInfo;

public class VersionController extends Controller{

	private static final Logger log = LogManager.getLogger(VersionController.class);

	/**
	 * 
	 */
	public void lastVer(){
		String type = getPara("type");
		if (StringUtils.isNullOrEmpty(type)) {
			renderJson(new SdkHttpResult(401, "type 参数不能为空"));
		}else {
			VersionInfo lastVer = VersionInfo.me.getLast(type);
			if (lastVer != null) {
				AppVersion appVersion = new AppVersion(lastVer);
				renderJson(appVersion);
			}else {
				renderJson(new SdkHttpResult(404, "没有相关版本"));
			}
		}
	}
}
