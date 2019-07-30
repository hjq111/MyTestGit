package cn.com.demo.javaweb.snacks.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cn.com.demo.javaweb.snacks.dao.IPictureDAO;
import cn.com.demo.javaweb.snacks.db.DBConnection;
import cn.com.demo.javaweb.snacks.po.PicturePO;

public class PictureDAOImpl implements IPictureDAO {

	@Override
	public List<PicturePO> findPictures(int fdId, int picType) {
		List<PicturePO> picList = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		DBConnection dbConn = DBConnection.getInstance();
		try {
			conn = dbConn.getConnection();
			
			String sql = "select * from snacks_picture where pic_fd_id=? and pic_type=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fdId);
			pstmt.setInt(2, picType);
			rs = pstmt.executeQuery();
			if(rs != null) {
				picList = new ArrayList<PicturePO>();
				PicturePO pic = null;
				while(rs.next()) {
					pic = new PicturePO();
					pic.setPicDesc(rs.getString("pic_desc"));
					pic.setPicFdId(rs.getInt("pic_fd_id"));
					pic.setPicId(rs.getInt("pic_id"));
					pic.setPicType(rs.getInt("pic_type"));
					pic.setPicUrl(rs.getString("pic_url"));
					picList.add(pic);
				}
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbConn.close(conn, pstmt, rs);
		}
		return picList;
	}

}
