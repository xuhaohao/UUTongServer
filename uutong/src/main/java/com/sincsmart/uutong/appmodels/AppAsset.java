package com.sincsmart.uutong.appmodels;

import java.io.Serializable;

import com.sincsmart.uutong.models.AssetInfo;

public class AppAsset implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String id;
	private String name;
	private String address;
	private String mac;
	
	private String url;
	private String backUrl;
	
	private int ownershipType;

	public AppAsset() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public AppAsset(AssetInfo assetInfo) {
		setId(assetInfo.getStr("id"));
		setName(assetInfo.getStr("name"));
		setAddress(assetInfo.getStr("address"));
		setMac(assetInfo.getStr("mac"));
		setUrl(assetInfo.getStr("url"));
		setBackUrl(assetInfo.getStr("backUrl"));
		setOwnershipType(assetInfo.getInt("ownershipType"));
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getBackUrl() {
		return backUrl;
	}

	public void setBackUrl(String backUrl) {
		this.backUrl = backUrl;
	}

	public int getOwnershipType() {
		return ownershipType;
	}

	public void setOwnershipType(int ownershipType) {
		this.ownershipType = ownershipType;
	}
	
	
}
