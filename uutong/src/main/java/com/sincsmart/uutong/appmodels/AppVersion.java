package com.sincsmart.uutong.appmodels;

import java.io.Serializable;

import com.sincsmart.uutong.models.VersionInfo;

public class AppVersion implements Serializable{

	private static final long serialVersionUID = 1L;

	private String id;
	private String name;
	private String feature;
	private String type;
	private int beta;
	private String url;
	
	
	public AppVersion(VersionInfo versionInfo) {
		setId(versionInfo.getStr("id"));
		setName(versionInfo.getStr("name"));
		setFeature(versionInfo.getStr("feature"));
		setType(versionInfo.getStr("type"));
		setBeta(versionInfo.getInt("beta"));
		setUrl(versionInfo.getStr("url"));
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
	public String getFeature() {
		return feature;
	}
	public void setFeature(String feature) {
		this.feature = feature;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public int getBeta() {
		return beta;
	}

	public void setBeta(int beta) {
		this.beta = beta;
	}

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	

}
