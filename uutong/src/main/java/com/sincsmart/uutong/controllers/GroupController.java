package com.sincsmart.uutong.controllers;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.jfinal.core.Controller;
import com.jfinal.kit.JsonKit;
import com.sincsmart.uutong.appmodels.SdkHttpResult;
import com.sincsmart.uutong.models.OrganizationInfo;
import com.sincsmart.uutong.models.UserInfo;
import com.sincsmart.uutong.rongmodels.GroupInfo;

public class GroupController extends Controller{

	private static final Logger log = LogManager.getLogger(GroupController.class);
	
	
	public void myGroups(){
		String id = getPara("id");
		UserInfo userInfo = UserInfo.me.findById(id);
		List<GroupInfo> groupInfos = new ArrayList<GroupInfo>();
		if (userInfo != null) {
			List<OrganizationInfo> organizationInfos = OrganizationInfo.me.find("select oi.* from organizationinfo oi,organizationuser ou where oi.id = ou.oid and ou.uid = ?", id);
			for (OrganizationInfo organizationInfo : organizationInfos) {
				GroupInfo groupInfo = new GroupInfo(organizationInfo);
				groupInfos.add(groupInfo);
			}
		}
		SdkHttpResult sdkHttpResult = new SdkHttpResult(200, JsonKit.toJson(groupInfos));
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
