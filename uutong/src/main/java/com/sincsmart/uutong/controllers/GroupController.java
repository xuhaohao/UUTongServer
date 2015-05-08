package com.sincsmart.uutong.controllers;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.jfinal.core.Controller;
import com.jfinal.kit.JsonKit;
import com.sincsmart.uutong.appmodels.AppGroup;
import com.sincsmart.uutong.appmodels.SdkHttpResult;
import com.sincsmart.uutong.models.OrganizationInfo;
import com.sincsmart.uutong.models.UserInfo;

public class GroupController extends Controller{

	private static final Logger log = LogManager.getLogger(GroupController.class);
	
	
	public void myGroups(){
		String id = getPara("id");
		UserInfo userInfo = UserInfo.me.findById(id);
		List<AppGroup> appGroups = new ArrayList<AppGroup>();
		if (userInfo != null) {
			List<OrganizationInfo> organizationInfos = OrganizationInfo.me.find("select oi.* from organizationinfo oi,organizationuser ou where oi.id = ou.oid and ou.uid = ?", id);
			for (OrganizationInfo organizationInfo : organizationInfos) {
				AppGroup appGroup = new AppGroup(organizationInfo);
				appGroups.add(appGroup);
			}
		}
		SdkHttpResult sdkHttpResult = new SdkHttpResult(200, JsonKit.toJson(appGroups));
		renderJson(sdkHttpResult);
	}
	
	/**
	 * 创建一个群组
	 */
	public void save(){
		//创建Oganization
		//创建OrganizationUser
		//同步到融云Group
	}
	
}
