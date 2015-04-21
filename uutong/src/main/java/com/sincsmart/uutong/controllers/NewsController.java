package com.sincsmart.uutong.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.google.common.collect.Maps;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.ext.interceptor.POST;
import com.jfinal.plugin.activerecord.Db;
import com.sincsmart.uutong.appmodels.AppNews;
import com.sincsmart.uutong.common.R;
import com.sincsmart.uutong.models.ChildInfo;
import com.sincsmart.uutong.models.ClassInfo;
import com.sincsmart.uutong.models.NewsInfo;
import com.sincsmart.uutong.models.SchoolInfo;
import com.sincsmart.uutong.models.UserInfo;

public class NewsController extends Controller{

	private static final Logger log = LogManager.getLogger(UserController.class);
	
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * 获取最新公告
	 * @throws ParseException
	 */
	public void lastMyNews() throws ParseException{
		String id = getPara("id");
		String dt = getPara("dt");
		Date date = df.parse(dt);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		
		List<AppNews> appNewsList = new ArrayList<AppNews>();
		
		UserInfo userInfo = UserInfo.me.findById(id);
		if (userInfo != null) {
			if (R.Identity_Parent.equals(userInfo.getStr("identity"))) {
				//孩子所在班级的公告
				List<ChildInfo> childInfos = ChildInfo.me.find("select * from childinfo where linkman1 = ?", id);
				//***暂时支持第一个孩子
				if (childInfos.size() > 0) {
					String classId = childInfos.get(0).getStr("classId");
					String schoolId = classId.substring(0, 18);
					List<NewsInfo> newsInfoList = NewsInfo.me.find("select * from newsinfo where cover > ? and ((ownershipType = ? and ownership = ? ) or (ownershipType = ? and ownership = ?))", calendar,R.OwnershipType_School,schoolId,R.OwnershipType_Class,classId);
					
					ClassInfo classInfo = ClassInfo.me.findById(classId, "name");
					SchoolInfo schoolInfo = SchoolInfo.me.findById(schoolId, "name");
					Map<String,String> authorMap = Maps.newHashMap();
					for (NewsInfo newsInfo : newsInfoList) {
						String className = newsInfo.getInt("ownershipType") == R.OwnershipType_Class  ? classInfo.getStr("name"): "全校";
						AppNews appNews = new AppNews(newsInfo,schoolId,schoolInfo.getStr("name"),classId,className);
						String authorId = newsInfo.getStr("author");
						if (!authorMap.containsKey(authorId)) {
							UserInfo author = UserInfo.me.findById(authorId, "name");
							authorMap.put(authorId, author.getStr("name"));
						}
						appNews.setAuthorName(authorMap.get(authorId));
						appNewsList.add(appNews);
					}
				}
			}
			if (R.Identity_Teacher.equals(userInfo.getStr("identity"))) {
				//自己发布的公告+所在校园的公告(自己班级的公告+所在校园的公告)
				List<ClassInfo> classInfos = ClassInfo.me.find("select * from classinfo where header = ?", id);
				if (classInfos.size() > 0) {
					//默认取第一个班级的学校部分（一个老师可以在多个学校的多个班级吗？）
					ClassInfo classInfo = classInfos.get(0);
					String schoolId = classInfo.getStr("schoolId");
					SchoolInfo schoolInfo = SchoolInfo.me.findById(schoolId,"name");
					List<NewsInfo> newsInfoList = NewsInfo.me.find("select * from newsinfo where cover > ? and (author = ? or ownership = ?)", calendar,id,schoolId);
					
					Map<String,String> authorMap = Maps.newHashMap();
					for (NewsInfo newsInfo : newsInfoList) {
						String className = newsInfo.getInt("ownershipType") == R.OwnershipType_Class  ? classInfo.getStr("name"): "全校";
						AppNews appNews = new AppNews(newsInfo,schoolId,schoolInfo.getStr("name"),classInfo.getStr("id"),className);
						String authorId = newsInfo.getStr("author");
						if (!authorMap.containsKey(authorId)) {
							UserInfo author = UserInfo.me.findById(authorId, "name");
							authorMap.put(authorId, author.getStr("name"));
						}
						appNews.setAuthorName(authorMap.get(authorId));
						appNewsList.add(appNews);
					}
				}
			}
			if (R.Identity_Principal.equals(userInfo.getStr("identity"))) {
				//所在校园公告
				SchoolInfo schoolInfo = SchoolInfo.me.findFirst("select * from schoolinfo where principal = ?", id);
				if (schoolInfo != null) {
					String schoolId = schoolInfo.getStr("id");
					String schoolName = schoolInfo.getStr("name");
					List<NewsInfo> newsInfoList = NewsInfo.me.find("select * from newsinfo where cover > ? and ownership like ? ",calendar , schoolId+"%");
					
					Map<String,String> authorMap = Maps.newHashMap();
					for (NewsInfo newsInfo : newsInfoList) {
						int ownershipType = newsInfo.getInt("ownershipType");
						String classId = "";
						String className = "全校";
						if(ownershipType == R.OwnershipType_Class){
							ClassInfo classInfo = ClassInfo.me.findById(newsInfo.get("ownership"),"name");
							classId = classInfo.getStr("id");
							className = classInfo.getStr("name");
						}
						AppNews appNews = new AppNews(newsInfo,schoolId,schoolName,classId,className);
						String authorId = newsInfo.getStr("author");
						if (!authorMap.containsKey(authorId)) {
							UserInfo author = UserInfo.me.findById(authorId, "name");
							authorMap.put(authorId, author.getStr("name"));
						}
						appNews.setAuthorName(authorMap.get(authorId));
						appNewsList.add(appNews);
					}
				}
			}
		}
		renderJson(appNewsList);
	}
	
	/**
	 * 最新的我的公告的条数
	 * @throws ParseException
	 */
	public void lastMyNewsCount() throws ParseException{
		String id = getPara("id");
		String dt = getPara("dt");
		Date date = df.parse(dt);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		
		Long count = 0L;
		UserInfo userInfo = UserInfo.me.findById(id);
		if (userInfo != null) {
			if (R.Identity_Parent.equals(userInfo.getStr("identity"))) {
				//孩子所在班级的公告
				List<ChildInfo> childInfos = ChildInfo.me.find("select * from childinfo where linkman1 = ?", id);
				//***暂时支持第一个孩子
				if (childInfos.size() > 0) {
					String classId = childInfos.get(0).getStr("classId");
					String schoolId = classId.substring(0, 18);
					count = Db.queryLong("select count(*) from newsinfo where cover > ? and ((ownershipType = ? and ownership = ? ) or (ownershipType = ? and ownership = ?))", calendar,R.OwnershipType_School,schoolId,R.OwnershipType_Class,classId);
				}else{
					log.warn("没有当前家长对应的孩子=_=b" + id);
				}
			}
			if (R.Identity_Teacher.equals(userInfo.getStr("identity"))) {
				//自己发布的公告+所在校园的公告(自己班级的公告+所在校园的公告)
				List<ClassInfo> classInfos = ClassInfo.me.find("select * from classinfo where header = ?", id);
				if (classInfos.size() > 0) {
					//默认取第一个班级的学校部分（一个老师可以在多个学校的多个班级吗？）
					ClassInfo classInfo = classInfos.get(0);
					String schoolId = classInfo.getStr("schoolId");
					count = Db.queryLong("select count(*) from newsinfo where cover > ? and (author = ? or ownership = ?)", calendar,id,schoolId);
				}else{
					log.warn("没有当前教师对应的班级=_=b" + id);
				}
			}
			if (R.Identity_Principal.equals(userInfo.getStr("identity"))) {
				//所在校园公告
				SchoolInfo schoolInfo = SchoolInfo.me.findFirst("select * from schoolinfo where principal = ?", id);
				if (schoolInfo != null) {
					count = Db.queryLong("select count(*) from newsinfo where cover > ? and ownership like ? ",calendar , schoolInfo.getStr("id")+"%");
				}else{
					log.warn("没有当前校长对应的学校=_=b" + id);
				}
			}
		}
		renderJson("count",count);
	}
	/**
	 * 最新公司发布的公告
	 * @throws ParseException
	 */
	public void lastCorpNews() throws ParseException{
		String id = getPara("id");
		String dt = getPara("dt");
		Date date = df.parse(dt);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		
		List<AppNews> appNewsList = new ArrayList<AppNews>();
		
		List<NewsInfo> newsInfoList = NewsInfo.me.find("select * from newsinfo where ownershipType = ? and cover > ?", R.OwnershipType_Public , calendar);
		for (NewsInfo newsInfo : newsInfoList) {
			AppNews appNews = new AppNews(newsInfo);
			appNewsList.add(appNews);
		}
		renderJson(appNewsList);
	}
	
	/**
	 * 最新公司发布的公告条数
	 * @throws ParseException
	 */
	public void lastCorpNewsCount() throws ParseException{
		String id = getPara("id");
		String dt = getPara("dt");
		Date date = df.parse(dt);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		
		Long count = Db.queryLong("select count(*) from newsinfo where ownershipType = ? and cover > ?", R.OwnershipType_Public , calendar);

		renderJson("count",count);
	}
	
	
	@Before(POST.class) 
	public void save(){
		
	}
	
	public void detail(){
		String id = getPara("id");
		
	}
	
}
