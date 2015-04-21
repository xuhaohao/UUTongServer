package com.sincsmart.uutong.news;

import java.io.Serializable;
import java.util.Calendar;

import com.sincsmart.uutong.models.NewsInfo;
import com.sincsmart.uutong.models.UserInfo;

public class AppNews implements Serializable{

	private static final long serialVersionUID = 1L;

	private String id;
	private String schoolId;
	private String schoolName;
	private String classId;
	private String className;
	private String authorId;
	private String authorName;
 
	private String title;
	
	private int optIn;
	private String url;
	private Calendar coverTime;
	
	public AppNews() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public AppNews(NewsInfo newsInfo,String schoolName, String className) {
		setId(newsInfo.getStr("id"));
		setSchoolId(schoolId);
		setSchoolName(schoolName);
		setClassId(classId);
		setClassName(className);

		setAuthorId(newsInfo.getStr("author"));
//		appNews.setAuthorName(authorMap.get(authorId));
		setTitle(newsInfo.getStr("title"));
		setUrl(newsInfo.getStr("url"));
		setOptIn(newsInfo.getInt("optIn"));
		Calendar coverTime = Calendar.getInstance();
		coverTime.setTime(newsInfo.getTimestamp("cover"));
		setCoverTime(coverTime);
	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getAuthorId() {
		return authorId;
	}

	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getOptIn() {
		return optIn;
	}

	public void setOptIn(int optIn) {
		this.optIn = optIn;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Calendar getCoverTime() {
		return coverTime;
	}

	public void setCoverTime(Calendar coverTime) {
		this.coverTime = coverTime;
	}
	
	
}
