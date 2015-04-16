package com.sincsmart.uutong.user;

import java.util.List;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.sincsmart.uutong.models.UserModel;

public class UserController extends Controller{

	public void index() {
		renderText("你好");
	}
	
	public void login(){
		
		
	}
	
	public void list(){
		Page<UserModel> pageModel = UserModel.me.paginate(1, 50);
		List<UserModel> list = pageModel.getList();
		renderJson(list);
	}
	
	public void delete(){
		String param = getPara("id");
		UserModel userModel = UserModel.me.findById(param);
		if (userModel != null) {
			userModel.delete();
		}
		renderText("OK");
	}
}
