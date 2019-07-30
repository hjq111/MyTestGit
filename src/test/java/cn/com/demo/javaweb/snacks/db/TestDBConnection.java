package cn.com.demo.javaweb.snacks.db;

import java.sql.Connection;

import org.junit.Assert;
import org.junit.Test;

public class TestDBConnection {
	@Test
	public void testGetInstance() {
		DBConnection dbConn = DBConnection.getInstance();
		Assert.assertNotNull(dbConn);
	}
	
	@Test
	public void testGetConnection() {
		DBConnection dbConn = DBConnection.getInstance();
		Connection conn = dbConn.getConnection();
		Assert.assertNotNull(conn);
	}
}
