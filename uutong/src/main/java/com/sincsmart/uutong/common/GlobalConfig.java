package com.sincsmart.uutong.common;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.tx.TxByRegex;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.sincsmart.uutong.controllers.AssetController;
import com.sincsmart.uutong.controllers.NewsController;
import com.sincsmart.uutong.controllers.UserController;
import com.sincsmart.uutong.controllers.VersionController;
import com.sincsmart.uutong.models.AssetInfo;
import com.sincsmart.uutong.models.AssetType;
import com.sincsmart.uutong.models.ChildInfo;
import com.sincsmart.uutong.models.ClassInfo;
import com.sincsmart.uutong.models.NewsDetail;
import com.sincsmart.uutong.models.NewsInfo;
import com.sincsmart.uutong.models.SchoolInfo;
import com.sincsmart.uutong.models.UserInfo;
import com.sincsmart.uutong.models.VersionInfo;

/**
 * API引导式配置
 */
public class GlobalConfig extends JFinalConfig {

	@Override
	public void configConstant(Constants me) {
		// 加载少量必要配置，随后可用getProperty(...)获取值
		loadPropertyFile("db.txt");
		me.setDevMode(getPropertyToBoolean("devMode", false));
	}

	@Override
	public void configRoute(Routes me) {

		// me.add("/", IndexController.class, "/index"); //
		// 第三个参数为该Controller的视图存放路径
		// me.add("/blog", BlogController.class); // 第三个参数省略时默认与第一个参数值相同，在此即为
		
		me.add("/", UserController.class, "user"); // 第三个参数为该Controller的视图存放路径
		me.add("/user", UserController.class);
		me.add("/news", NewsController.class);
		me.add("/version",VersionController.class);
		me.add("/asset",AssetController.class);
	}

	@Override
	public void configPlugin(Plugins me) {
		// 配置C3p0数据库连接池插件
		C3p0Plugin c3p0Plugin = new C3p0Plugin(getProperty("jdbcUrl"),
				getProperty("user"), getProperty("password").trim());
		
		me.add(c3p0Plugin);
		// 配置ActiveRecord插件
		ActiveRecordPlugin arp = new ActiveRecordPlugin(c3p0Plugin);
		arp.setShowSql(true);//这句话就是ShowSql
		me.add(arp);
		
		Class[] mapClassArray = new Class[]{ 
				UserInfo.class,
				ChildInfo.class,
				ClassInfo.class,
				SchoolInfo.class,
				NewsInfo.class,
				NewsDetail.class,
				AssetInfo.class,
				AssetType.class,
				VersionInfo.class};
		for (Class classess : mapClassArray) {
			String className = classess.getSimpleName().toLowerCase();
			arp.addMapping(className, classess);
		}
//		arp.addMapping("userinfo", UserInfo.class);
//		arp.addMapping("childinfo", ChildInfo.class);
//		arp.addMapping("classinfo", ClassInfo.class);
//		arp.addMapping("schoolinfo", SchoolInfo.class);
//		arp.addMapping("newsinfo",NewsInfo.class);
	}

	@Override
	public void configInterceptor(Interceptors me) {
		// TODO Auto-generated method stub
		me.add(new TxByRegex(".*save.*"));
		me.add(new TxByRegex(".*update.*"));
		me.add(new TxByRegex(".*delete.*"));
	}

	@Override
	public void configHandler(Handlers me) {
		// TODO Auto-generated method stub

	}

	/**
	 * 建议使用 JFinal 手册推荐的方式启动项目 运行此 main
	 * 方法可以启动项目，此main方法可以放置在任意的Class类定义中，不一定要放于此
	 */
	public static void main(String[] args) {
		JFinal.start("src/main/webapp", 80, "/", 5);
	}

}
