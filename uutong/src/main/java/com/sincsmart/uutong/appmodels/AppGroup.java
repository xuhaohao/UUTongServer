package com.sincsmart.uutong.appmodels;

import java.io.Serializable;

import com.sincsmart.uutong.common.R;
import com.sincsmart.uutong.models.OrganizationInfo;

public class AppGroup implements Serializable{

	private static final long serialVersionUID = 1L;

	private String id;
	private String name;
	private String pid;
	private String hint;
	private String cover;
	
	public AppGroup() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public AppGroup(OrganizationInfo organizationInfo) {
		this.id = organizationInfo.getStr("id");
		this.name = organizationInfo.getStr("name");
		this.pid = organizationInfo.getStr("parentId");
		this.hint = organizationInfo.getStr("hint");
		this.cover = R.df.format(organizationInfo.getDate("cover"));
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getHint() {
		return hint;
	}

	public void setHint(String hint) {
		this.hint = hint;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}
	
	
	
}
