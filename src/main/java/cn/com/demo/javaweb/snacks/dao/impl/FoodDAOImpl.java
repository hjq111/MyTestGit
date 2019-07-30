package cn.com.demo.javaweb.snacks.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cn.com.demo.javaweb.snacks.dao.IFoodDAO;
import cn.com.demo.javaweb.snacks.db.DBConnection;
import cn.com.demo.javaweb.snacks.po.FoodPO;
import cn.com.demo.javaweb.snacks.util.Constant;

public class FoodDAOImpl implements IFoodDAO {

	@Override
	public int foodCount(String keyword) {
		int count = 0;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		DBConnection dbConn = DBConnection.getInstance();
		try {
			// 获取记录数（无条件）
			String sql = "select count(*) from snacks_foods";
			// 有条件（添加where语句）
			if (keyword != null && !(keyword = keyword.trim()).equals("")) {
				sql += " where fd_name like ?";
			}
			conn = dbConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			// 如果有条件，设置占位符（？）的值
			if (keyword != null && !keyword.equals("")) {
				// 添加模糊匹配符号
				if (keyword.startsWith("%") && keyword.endsWith("%")) {
					pstmt.setString(1, keyword);
				} else {
					pstmt.setString(1, "%" + keyword + "%");
				}
			}
			// 执行
			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbConn.close(conn, pstmt, rs);
		}

		return count;
	}

	@Override
	public FoodPO findFoodById(int fdId) {
		FoodPO food = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		DBConnection dbConn = DBConnection.getInstance();
		try {
			conn = dbConn.getConnection();
			String sql = "select * from snacks_foods where fd_id=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fdId);
			rs = pstmt.executeQuery();
			if (rs != null && rs.next()) {
				food = new FoodPO();
				food.setFdDetail(rs.getString("fd_detail"));
				food.setFdId(rs.getInt("fd_id"));
				food.setFdInventory(rs.getInt("fd_inventory"));
				food.setFdName(rs.getString("fd_name"));
				food.setFdPackae(rs.getString("fd_package"));
				food.setFdPrice(rs.getDouble("fd_price"));
				food.setFdTaste(rs.getString("fd_taste"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbConn.close(conn, pstmt, rs);
		}

		return food;
	}

	@Override
	public List<FoodPO> searchCurrPageFoods(int currPageNo, int number, String keyword) {
		List<FoodPO> foodList = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		DBConnection dbConn = DBConnection.getInstance();
		try {
			conn = dbConn.getConnection();
			String sql = "select * from snacks_foods";
			// 判断是否有查询条件，如果有，就在sql后添加where语句
			if (keyword != null && !(keyword = keyword.trim()).equals("")) {
				sql += " where fd_name like ?";
			}
			sql += " limit ?,?";

			pstmt = conn.prepareStatement(sql);
			currPageNo--;
			// 判断是否有条件，设置占位符信息
			if (keyword != null && !keyword.equals("")) {
				pstmt.setString(1, keyword);
				pstmt.setInt(2, currPageNo * number);
				pstmt.setInt(3, number);
			} else {
				pstmt.setInt(1, currPageNo * number);
				pstmt.setInt(2, number);
			}
			rs = pstmt.executeQuery();
			if (rs != null) {
				foodList = new ArrayList<FoodPO>();
				FoodPO food = null;
				while (rs.next()) {
					food = new FoodPO();
					food.setFdDetail(rs.getString("fd_detail"));
					food.setFdId(rs.getInt("fd_id"));
					food.setFdInventory(rs.getInt("fd_inventory"));
					food.setFdName(rs.getString("fd_name"));
					food.setFdPackae(rs.getString("fd_package"));
					food.setFdPrice(rs.getDouble("fd_price"));
					food.setFdTaste(rs.getString("fd_taste"));
					foodList.add(food);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbConn.close(conn, pstmt, rs);
		}
		return foodList;
	}

	@Override
	public int findFoodSaleCount(int fdId) {
		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		DBConnection dbConn = DBConnection.getInstance();
		try {
			conn = dbConn.getConnection();

			String sql = "select sum(it.od_it_count) from snacks_order_item it, snacks_order od"
					+ " where it.od_it_fd_id=? and od.od_status=? and od.od_id=it.od_id";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fdId);
			pstmt.setInt(2, Constant.ORDER_STATUS_OK);// 9表示订单正常完成状态
			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbConn.close(conn, pstmt, rs);
		}
		return count;
	}

//	Connection conn = null;
//	PreparedStatement pstmt = null;
//	ResultSet rs = null;
//	DBConnection dbConn = DBConnection.getInstance();
//	try {
//		conn = dbConn.getConnection();
//	}catch(Exception e) {
//		e.printStackTrace();
//	}finally {
//		dbConn.close(conn, pstmt, rs);
//	}

}
