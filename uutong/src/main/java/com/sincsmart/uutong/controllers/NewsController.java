package com.sincsmart.uutong.controllers;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import com.google.common.base.Joiner;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.ext.interceptor.POST;
import com.jfinal.kit.JsonKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.upload.UploadFile;
import com.sincsmart.uutong.appmodels.AppNews;
import com.sincsmart.uutong.appmodels.SdkHttpResult;
import com.sincsmart.uutong.common.R;
import com.sincsmart.uutong.models.NewsInfo;
import com.sincsmart.uutong.models.OrganizationUser;
import com.sincsmart.uutong.models.UserInfo;

public class NewsController extends Controller{

	private static final Logger log = LogManager.getLogger(UserController.class);
	
	
	/**
	 * 获取最新公告
	 * @throws ParseException
	 */
	public void lastMyNews() throws ParseException{
		String id = getPara("id");
		String dt = getPara("dt");
		Date date = R.df.parse(dt);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		
		List<AppNews> appNewsList = new ArrayList<AppNews>();
		
		UserInfo userInfo = UserInfo.me.findById(id);
		if (userInfo != null) {
			//查询家长所在的群组或父群组id
//			List<OrganizationUser> organizationUsers = OrganizationUser.me.find("select * from organizationuser where uid = ?", id);
			//查询群组或父群组的公告
			List<Record> newsList = Db.find("select ni.*,ui.name from newsinfo ni,organizationuser ou,userinfo ui where ou.oid = ni.ownership and ni.author = ui.id and ou.uid = ? and ni.cover > ?  order by ni.cover", id,calendar.getTime());
			for (Record record : newsList) {
				AppNews appNews = new AppNews(record);

				appNewsList.add(appNews);
			}
		}
		
		SdkHttpResult sdkHttpResult = new SdkHttpResult(200, JsonKit.toJson(appNewsList));
		renderJson(sdkHttpResult);
	}
	
	/**
	 * 最新的我的公告的条数
	 * @throws ParseException
	 */
	public void lastMyNewsCount() throws ParseException{
		String id = getPara("id");
		String dt = getPara("dt");
		Date date = R.df.parse(dt);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		
		Long count = 0L;
		UserInfo userInfo = UserInfo.me.findById(id);
		if (userInfo != null) {
			count = Db.queryLong("select count(ni.id) from newsinfo ni,organizationuser ou,userinfo ui where ou.oid = ni.ownership and ni.author = ui.id and ou.uid = ? and ni.cover > ?  order by ni.cover", id,calendar.getTime());
		}
		SdkHttpResult sdkHttpResult = new SdkHttpResult(200, count.toString());
		renderJson(sdkHttpResult);
	}
	/**
	 * 最新公司发布的公告
	 * @throws ParseException
	 */
	public void lastCorpNews() throws ParseException{
		String id = getPara("id");
		String dt = getPara("dt");
		Date date = R.df.parse(dt);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		
		List<AppNews> appNewsList = new ArrayList<AppNews>();
		
		//查询群组或父群组的公告
		List<Record> newsList = Db.find("select ni.*,ui.name from newsinfo ni,userinfo ui where ni.author = ui.id and ni.ownership = ? and ni.cover > ?  order by ni.cover", R.CorpId,calendar.getTime());
		for (Record record : newsList) {
			AppNews appNews = new AppNews(record);
			
			appNewsList.add(appNews);
		}
		SdkHttpResult sdkHttpResult = new SdkHttpResult(200, JsonKit.toJson(appNewsList));
		renderJson(sdkHttpResult);
	}
	
	/**
	 * 最新公司发布的公告条数
	 * @throws ParseException
	 */
	public void lastCorpNewsCount() throws ParseException{
		String id = getPara("id");
		String dt = getPara("dt");
		Date date = R.df.parse(dt);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		
		Long count = Db.queryLong("select count(ni.id) from newsinfo ni,userinfo ui where ni.author = ui.id and ni.ownership = ? and ni.cover > ?  order by ni.cover", R.CorpId,calendar.getTime());

		SdkHttpResult sdkHttpResult = new SdkHttpResult(200, count.toString());
		renderJson(sdkHttpResult);
	}
	
	
	@Before(POST.class) 
	public void save(){
		SdkHttpResult result = new SdkHttpResult(200,"OK");
		
		String id= UUID.randomUUID().toString();
		String imageBaseUrl = "news/"+id;
		List<UploadFile> files = getFiles(imageBaseUrl);
		
		String authorId = getPara("authorId");
		String title = getPara("title");
		String content = getPara("content");

		UserInfo userInfo = UserInfo.me.findById(authorId,"identity");
        
		if (userInfo != null) {
			NewsInfo newsInfo = new NewsInfo();
			newsInfo.set("id", UUID.randomUUID().toString());
			
			newsInfo.set("title", title);
			newsInfo.set("author", authorId);
			newsInfo.set("content",content);
			
			newsInfo.set("optIn", 0);
			newsInfo.set("cover", Calendar.getInstance().getTime());
			if (R.Identity_Parent.equals(userInfo.getStr("identity"))) {
				result.setFieldValues(401, "没有权限");
				renderJson(result);
				return;
			}
			if (R.Identity_Teacher.equals(userInfo.getStr("identity"))) {
				OrganizationUser organizationUser = OrganizationUser.me.findFirst("select * organizationuser where uid = ? and identity = 2", authorId);
				if (organizationUser != null) {
					newsInfo.set("ownershipType", R.OwnershipType_Class);
					newsInfo.set("ownership", organizationUser.get("uid"));
				}else {
					result.setFieldValues(404, "没有教师对应班级");
				}
			}
//			if (R.Identity_Principal.equals(userInfo.getStr("identity"))) {
//				SchoolInfo schoolInfo = SchoolInfo.me.findFirst("select * from schoolinfo where principal = ?", authorId);
//				if (schoolInfo != null) {
//					newsInfo.set("ownershipType", R.OwnershipType_School);
//					newsInfo.set("ownership", schoolInfo.get("id"));
//				}else {
//					result.setFieldValues(404, "没有校长对应学校");
//				}
//			}
			if (R.Identity_Author.equals(userInfo.getStr("identity"))) {
				newsInfo.set("ownershipType", R.OwnershipType_Public);
				newsInfo.set("ownership", R.CorpId);
			}
			if (result.getCode() == 200) {
				if (files.size() > 0) {
					List<String> fileNames = new ArrayList<String>();
					for (UploadFile uploadFile : files) {
						if (!uploadFile.getFileName().contains("thumbnail_")) {
							fileNames.add(imageBaseUrl + "/" + uploadFile.getFileName());
						}
					}
					newsInfo.set("image", Joiner.on(";").join(fileNames));
				}
				newsInfo.save();
			}
			renderJson(result);
		}else {
			renderJson(new SdkHttpResult(404,"没有作者"));
		}
	}
	
}
