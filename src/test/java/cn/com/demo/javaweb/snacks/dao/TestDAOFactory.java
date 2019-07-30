package cn.com.demo.javaweb.snacks.dao;

import org.junit.Assert;
import org.junit.Test;

public class TestDAOFactory {
	@Test
    public void testBuildDAOFactory() {
    	DAOFactory factory = DAOFactory.buildDAOFactory();
    	Assert.assertNotNull(factory);
    }
	@Test
	public void testCreateFoodDAO() {
		DAOFactory factory = DAOFactory.buildDAOFactory();
		IFoodDAO dao = factory.createFoodDAO();
		Assert.assertNotNull(dao);
	}
}
