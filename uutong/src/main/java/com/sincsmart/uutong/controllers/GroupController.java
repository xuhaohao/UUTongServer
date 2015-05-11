package com.sincsmart.uutong.controllers;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.jfinal.core.Controller;
import com.jfinal.kit.JsonKit;
import com.sincsmart.uutong.appmodels.AppGroup;
import com.sincsmart.uutong.appmodels.SdkHttpResult;
import com.sincsmart.uutong.common.R;
import com.sincsmart.uutong.models.OrganizationInfo;
import com.sincsmart.uutong.models.OrganizationUser;
import com.sincsmart.uutong.models.UserInfo;
import com.sincsmart.uutong.rong.ApiHttpClient;
import com.sincsmart.uutong.rongmodels.FormatType;

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
		String oid = "000000000000000000000000000000000101";
		OrganizationInfo organizationInfo = OrganizationInfo.me.findById(oid);
		String oName = organizationInfo.getStr("name");
		if (organizationInfo != null) {
			List<OrganizationUser> list = OrganizationUser.me.find("select * from organizationuser where oid = ?", oid);
			List<String> userIds = new ArrayList<String>();
			for (OrganizationUser ou : list) {
				userIds.add(ou.getStr("uid"));
			}
			try {
				SdkHttpResult sdkHttpResult = ApiHttpClient.createGroup(R.Rong_Key, R.Rong_Secret, userIds, oid, oName, FormatType.json);
				if (sdkHttpResult != null) {
					
				}
				log.info("创建成功"+oName);
			} catch (Exception e) {
				log.error("同步到融云服务错误", e);
			}
		}
		
		//创建Oganization
		//创建OrganizationUser
		//同步到融云Group
	}
	
}
