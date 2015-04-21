package com.sincsmart.uutong.controllers;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.jfinal.core.Controller;
import com.sincsmart.uutong.appmodels.AppAsset;
import com.sincsmart.uutong.common.R;
import com.sincsmart.uutong.models.AssetInfo;

public class AssetController extends Controller{

	private static final Logger log = LogManager.getLogger(AssetController.class);

	public void myAssets(){
		String id = getPara("id");
		List<AppAsset> result = new ArrayList<AppAsset>();
		//暂时实现为所有公开设备
		List<AssetInfo> publicAssets = AssetInfo.me.find("select * from assetinfo where ownershipType = ?", R.OwnershipType_Public);
		for (AssetInfo assetInfo : publicAssets) {
			AppAsset appAsset = new AppAsset(assetInfo);
			result.add(appAsset);
		}
		renderJson(result);
	}
}
