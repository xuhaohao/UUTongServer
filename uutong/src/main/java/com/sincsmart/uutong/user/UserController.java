package com.sincsmart.uutong.user;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.sincsmart.uutong.appmodels.SdkHttpResult;
import com.sincsmart.uutong.models.ChildInfo;
import com.sincsmart.uutong.models.UserInfo;
import com.sincsmart.uutong.rong.ApiHttpClient;
import com.sincsmart.uutong.rongmodels.FormatType;
import com.sincsmart.uutong.utils.JSONHelper;
import com.sincsmart.uutong.utils.PsdUtil;

public class UserController extends Controller{

	private static final Logger log = LogManager.getLogger(UserController.class);
	
	public static final String appKey = "3argexb6rn24e";
	public static final String appSecret = "ZaF3DOggLT1J";
	
	public void index() {
		renderText("你好");
	}
	
	public void login() throws Exception{
		String id = getPara("id");
		String psd= getPara("psd");
		psd = PsdUtil.getPassMD5(psd);
		UserInfo userInfo = UserInfo.me.findFirst("select * from userinfo where id = ? and psd = ?", id , psd );
		
		SdkHttpResult sdkHttpResult;
		if (userInfo != null) {
			sdkHttpResult = ApiHttpClient.getToken(appKey, appSecret, userInfo.getStr("id"), userInfo.getStr("name"), "http://aa.com/a.png", FormatType.json);
    		JSONObject jsonObj = new JSONObject(sdkHttpResult.getResult());
    		if (jsonObj.getInt("code")==200) {
    			AppUser appUser = new AppUser(userInfo);
    			appUser.setRongToken(jsonObj.getString("token"));
    			log.debug("gettoken=" + jsonObj.getString("token"));
    			sdkHttpResult.setResult(JSONHelper.toJSON(appUser));
			}
		}else {
			sdkHttpResult = new SdkHttpResult(-1, "用户名或密码不正确");
		}
		
		renderJson(sdkHttpResult);
	}
	
	public void classGroup(){
		
		List<UserInfo> list = UserInfo.me.getClassGroup(getPara("id"));
		List<AppUser> appUsers = new ArrayList<AppUser>();
		for (UserInfo userInfo : list) {
			AppUser appUser = new AppUser(userInfo);
			appUsers.add(appUser);
		}
		renderJson(appUsers);
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
