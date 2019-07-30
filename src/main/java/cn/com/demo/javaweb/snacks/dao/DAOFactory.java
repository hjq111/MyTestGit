package cn.com.demo.javaweb.snacks.dao;

import java.io.InputStream;
import java.util.Properties;

public class DAOFactory {
	// 定义全局工厂对象
	private static DAOFactory factory ;
	// 存放配置信息
	private Properties props;
	// 私有化构造器
    private DAOFactory() {
    	try {
			this.init();
		} catch (Exception e) {
			//e.printStackTrace();
			throw new RuntimeException(e);
		}
    }
    
    public static DAOFactory buildDAOFactory() {
    	if(factory == null) {
    		factory = new DAOFactory();
    	}
    	return factory;
    }
    
    public IPromotionDAO createPromotionDAO() {
    	IPromotionDAO dao = null;
    	try {
    		// 创建IFoodDAO对象（new 配置文件中foodDAO对应的实现类）
			dao = (IPromotionDAO)this.createObject(this.props.getProperty("promDAO"));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
    	return dao;
    }
    
    public IFoodDAO createFoodDAO() {
    	IFoodDAO dao = null;
    	try {
    		// 创建IFoodDAO对象（new 配置文件中foodDAO对应的实现类）
			dao = (IFoodDAO)this.createObject(this.props.getProperty("foodDAO"));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
    	return dao;
    }
    
    public IPictureDAO createPictureDAO() {
    	IPictureDAO dao = null;
    	try {
    		// 创建IFoodDAO对象（new 配置文件中foodDAO对应的实现类）
			dao = (IPictureDAO)this.createObject(this.props.getProperty("picDAO"));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
    	return dao;
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
    	InputStream in = DAOFactory.class.getClassLoader().getResourceAsStream("dao.properties");
    	props = new Properties();
    	// 加载配置文件流
    	props.load(in);
    }
    
    
}
