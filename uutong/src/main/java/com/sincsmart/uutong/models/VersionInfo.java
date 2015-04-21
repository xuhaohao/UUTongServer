package com.sincsmart.uutong.models;

import com.jfinal.plugin.activerecord.Model;

@SuppressWarnings("serial")
public class VersionInfo extends Model<VersionInfo> {

	public static final VersionInfo me = new VersionInfo();
	
	public static final String Android = "Android";
	public static final String iOS = "iOS";
	
	public VersionInfo getLast(String type){
		return findFirst("select * from versioninfo where type = ? order by cover desc",type);
	}
}