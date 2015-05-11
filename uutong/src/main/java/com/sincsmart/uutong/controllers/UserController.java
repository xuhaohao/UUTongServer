package com.sincsmart.uutong.controllers;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.jfinal.core.Controller;
import com.jfinal.kit.JsonKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.sincsmart.uutong.appmodels.AppUser;
import com.sincsmart.uutong.appmodels.SdkHttpResult;
import com.sincsmart.uutong.common.R;
import com.sincsmart.uutong.models.UserInfo;
import com.sincsmart.uutong.rong.ApiHttpClient;
import com.sincsmart.uutong.rongmodels.FormatType;
import com.sincsmart.uutong.utils.JSONHelper;
import com.sincsmart.uutong.utils.PsdUtil;

public class UserController extends Controller{

	private static final Logger log = LogManager.getLogger(UserController.class);
	
	
	public void index() {
		renderText("你好");
	}
	
	public void connect() throws Exception{
		String id = getPara("id");
		String psd= getPara("psd");
		String device = getPara("device");
		psd = PsdUtil.getPassMD5(psd);
		UserInfo userInfo = UserInfo.me.findFirst("select * from userinfo where id = ? and psd = ?", id , psd );
		
		SdkHttpResult sdkHttpResult;
		if (userInfo != null) {
			sdkHttpResult = ApiHttpClient.getToken(R.Rong_Key, R.Rong_Secret, userInfo.getStr("id"), userInfo.getStr("name"), "http://aa.com/a.png", FormatType.json);
    		JSONObject jsonObj = new JSONObject(sdkHttpResult.getResult());
    		if (jsonObj.getInt("code")==200) {
    			AppUser appUser = new AppUser(userInfo);
    			String rongToken = jsonObj.getString("token");
    			appUser.setRongToken(rongToken);
    			log.debug("gettoken=" + jsonObj.getString("token"));
    			sdkHttpResult.setResult(JSONHelper.toJSON(appUser));
    			userInfo.set("rongToken", rongToken);
    			userInfo.set("logonDevice", device).update();
			}
		}else {
			sdkHttpResult = new SdkHttpResult(-1, "用户名或密码不正确");
		}
		
		renderJson(sdkHttpResult);
	}
	
	
	/**
	 * 获取我的好友
	 */
	public void myFriends(){
		String id = getPara("id");
		UserInfo userInfo = UserInfo.me.findById(id);
		List<AppUser> appUsers = new ArrayList<AppUser>();
		if (userInfo != null) {
			List<Record> list = Db.find("select ui.* from userinfo ui,organizationuser ou where ou.uid = ui.id and ou.oid in (select oid from organizationuser ou where ou.uid = ?)", id);
			for (Record record : list) {
				AppUser appUser = new AppUser();
				appUser.setId(record.getStr("id"));
				appUser.setName(record.getStr("name"));
				appUser.setNickName(record.getStr("nickName"));
				appUser.setGender(record.getInt("gender"));
				appUser.setPhone1(record.getStr("phone1"));
				appUsers.add(appUser);
			}
			if (R.Identity_Parent.equals(userInfo.getStr("identity"))) {
				
			}
			if (R.Identity_Principal.equals(userInfo.getStr("identity"))) {
				
			}
			if (R.Identity_Teacher.equals(userInfo.getStr("identity"))) {
				
			}
			if (R.Identity_Author.equals(userInfo.getStr("identity"))) {
				
			}
		}
		SdkHttpResult sdkHttpResult = new SdkHttpResult(200, JsonKit.toJson(appUsers));
		renderJson(sdkHttpResult);
	}
	
	public void list(){
		Page<UserInfo> pageModel = UserInfo.me.paginate(1, 50);
		List<UserInfo> list = pageModel.getList();
		renderJson(list);
	}
	
	public void delete(){
		String param = getPara("id");
		UserInfo userModel = UserInfo.me.findById(param);
		if (userModel != null) {
			userModel.delete();
		}
		renderText("OK");
	}
}
