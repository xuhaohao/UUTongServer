package com.sincsmart.uutong.models;


import java.util.List;

import com.google.common.base.Joiner;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

@SuppressWarnings("serial")
public class UserInfo extends Model<UserInfo>{
	
	public static final UserInfo me = new UserInfo();
	
	/**
	 * 所有 sql 与业务逻辑写在 Model 或 Service 中，不要写在 Controller 中，养成好习惯，有利于大型项目的开发与维护
	 */
	public Page<UserInfo> paginate(int pageNumber, int pageSize) {
		return paginate(pageNumber, pageSize, "select *", "from userinfo order by strName");
	}
	
	
	public List<UserInfo> getClassGroup(String id){
		List<Record> records = Db.find("select linkman1 from childinfo where classId = (select classId from childinfo where linkman1 = ?)", id);
		
		Joiner joiner = Joiner.on(",");
		String[] strArray = new String[records.size()];
		for (int i = 0; i < records.size(); i++) {
			strArray[i] = new StringBuilder("\"")
			.append(records.get(i).getStr("linkman1"))
			.append("\"").toString();
		}
		StringBuilder sb = new StringBuilder("select * from userinfo where id in (");
		sb = joiner.appendTo(sb, strArray).append(")");
		List<UserInfo> list = find(sb.toString());
		return list;
	}
}
