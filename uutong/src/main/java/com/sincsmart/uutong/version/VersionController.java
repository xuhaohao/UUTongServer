package com.sincsmart.uutong.version;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.jfinal.core.Controller;
import com.sincsmart.uutong.models.VersionInfo;

public class VersionController extends Controller{

	private static final Logger log = LogManager.getLogger(VersionController.class);

	/**
	 * 
	 */
	public void lastVer(){
		String type = getPara("plat");
		VersionInfo lastVer = VersionInfo.me.getLast(type);
		AppVersion appVersion = new AppVersion(lastVer);
		renderJson(appVersion);
	}
}
