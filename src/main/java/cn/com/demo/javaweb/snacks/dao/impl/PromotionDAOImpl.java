package cn.com.demo.javaweb.snacks.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import cn.com.demo.javaweb.snacks.dao.IPromotionDAO;
import cn.com.demo.javaweb.snacks.db.DBConnection;
import cn.com.demo.javaweb.snacks.po.PromotionPO;

public class PromotionDAOImpl implements IPromotionDAO {

	@Override
	public List<PromotionPO> findPromotions(int fdId) {
		Timestamp curr = new Timestamp(System.currentTimeMillis());
		return this.findPromotions(fdId, curr, curr);
	}

	@Override
	public List<PromotionPO> findPromotions(int fdId, Timestamp begin, Timestamp end) {
		List<PromotionPO> prmList = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		DBConnection dbConn = DBConnection.getInstance();
		try {
			conn = dbConn.getConnection();
//			String sql = "select prm.* "+
//			             " from snacks_fd_prm mp, snacks_promotion prm"+
//					     " where mp.fd_id=? and ((prm_start_time between ? and ?) or "
//					                          + "(prm_end_time between ? and ?) or "
//					                          + "(prm_start_time<=? and prm_end_time>=?))  and mp.prm_id=prm.prm_id";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, fdId);
//			pstmt.setTimestamp(2, begin);
//			pstmt.setTimestamp(3, end);
//			pstmt.setTimestamp(4, begin);
//			pstmt.setTimestamp(5, end);
//			pstmt.setTimestamp(6, begin);
//			pstmt.setTimestamp(7, end);

			String sql = "select prm.* " + " from snacks_fd_prm mp, snacks_promotion prm"
					+ " where mp.fd_id=? and prm_end_time>=? and prm_start_time<=? and mp.prm_id=prm.prm_id";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fdId);
			pstmt.setTimestamp(2, begin);
			pstmt.setTimestamp(3, end);

			rs = pstmt.executeQuery();
			if (rs != null) {
				prmList = new ArrayList<PromotionPO>();
				PromotionPO prom = null;
				while (rs.next()) {
					prom = new PromotionPO();
					prom.setPrmDetail(rs.getString("prm_detail"));
					prom.setPrmDiscount(rs.getDouble("prm_discount"));
					prom.setPrmEndTime(rs.getTimestamp("prm_end_time"));
					prom.setPrmId(rs.getInt("prm_id"));
					prom.setPrmName(rs.getString("prm_name"));
					prom.setPrmStartTime(rs.getTimestamp("prm_start_time"));
					prmList.add(prom);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbConn.close(conn, pstmt, rs);
		}

		return prmList;
	}

}
