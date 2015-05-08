package com.sincsmart.uutong.rongmodels;

import com.sincsmart.uutong.common.R;
import com.sincsmart.uutong.models.OrganizationInfo;

//群信息
public class GroupInfo {

	private String id;
	private String name;
	private String hint;
	private String createDate;

	public GroupInfo(String id, String name) {
		this.name = name;
		this.id = id;
	}
	
	public GroupInfo(OrganizationInfo organizationInfo) {
		this.id = organizationInfo.getStr("id");
		this.name = organizationInfo.getStr("name");
		this.hint = organizationInfo.getStr("hint");
		this.createDate = R.df.format(organizationInfo.getDate("cover"));
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getHint() {
		return hint;
	}

	public void setHint(String hint) {
		this.hint = hint;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	
	

//	@Override
//	public String toString() {
//		return String.format("{\"id\":\"%s\",\"name\":\"%s\"}", id, name);
//	}

}
