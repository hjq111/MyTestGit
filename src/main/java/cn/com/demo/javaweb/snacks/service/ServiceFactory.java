package cn.com.demo.javaweb.snacks.service;

import java.io.InputStream;
import java.util.Properties;

import cn.com.demo.javaweb.snacks.dao.DAOFactory;

public class ServiceFactory {
	private static ServiceFactory factory;
	private Properties props;
	private ServiceFactory() {
		try {
			this.init();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static ServiceFactory buildFactory() {
		if(factory == null) {
			factory = new ServiceFactory();
		}
		return factory;
	}

	public IFoodService createFoodService() {
		try {
			return (IFoodService)this.createObject(props.getProperty("foodService"));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
    private Object createObject(String clsName)throws Exception{
    	Object obj = null;
    	// 加载类
    	Class cls = Class.forName(clsName);
    	// 反射产生对象
    	obj = cls.newInstance();
    	return obj;
    }
    
    private void init()throws Exception{
    	// 读配置文件
    	InputStream in = DAOFactory.class.getClassLoader().getResourceAsStream("service.properties");
    	props = new Properties();
    	// 加载配置文件流
    	props.load(in);
    }
}
