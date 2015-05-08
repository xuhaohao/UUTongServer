package com.sincsmart.uutong.appmodels;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;

import com.jfinal.plugin.activerecord.Record;
import com.sincsmart.uutong.common.R;
import com.sincsmart.uutong.models.NewsInfo;

public class AppNews implements Serializable{

	private static final long serialVersionUID = 1L;

	private String id;

	private String authorId;
	private String authorName;
 
	private String groupId;
	
	private String title;
	
	private int optIn;
	private String url;
	private String cover;
	private int ownershipType;
	
	private String image;
	private String content;
	
	public AppNews() {
		
	}
	
	public AppNews(NewsInfo newsInfo) {
		setId(newsInfo.getStr("id"));

		setAuthorId(newsInfo.getStr("author"));
		setTitle(newsInfo.getStr("title"));
		setUrl(newsInfo.getStr("url"));
		setOptIn(newsInfo.getInt("optIn"));
		Calendar coverTime = Calendar.getInstance();
		coverTime.setTime(newsInfo.getTimestamp("cover"));
		
		Timestamp timeStamp = newsInfo.getTimestamp("cover");
		if (timeStamp != null) {
			setCover(timeStamp.toString().substring(0,19));
		}
		setImage(newsInfo.getStr("image"));
		setOwnershipType(newsInfo.getInt("ownershipType"));
		
		setContent(newsInfo.getStr("content"));
	}
	
	public AppNews(Record record){
		this.id = record.getStr("id");
		this.authorId = record.getStr("author");
		this.authorName = record.getStr("name");
		this.image = record.getStr("image");
		this.title = record.getStr("title");
		this.content = record.getStr("content");
		this.groupId = record.getStr("ownership");
		this.cover = R.df.format(record.getTimestamp("cover"));
	}
	


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public int getOwnershipType() {
		return ownershipType;
	}

	public void setOwnershipType(int ownershipType) {
		this.ownershipType = ownershipType;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	
	
	
}
