package com.notice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.notice.vo.NoticeVO;

public class NoticeDao {
	
	public NoticeDao() {}
	
	public ArrayList<NoticeVO> noticeList(Connection conn) {
		ArrayList<NoticeVO> list = new ArrayList<NoticeVO>();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		NoticeVO noticeVO = null;
		StringBuffer query = new StringBuffer();
		query.append("SELECT n_num, n_writer, n_title, n_date FROM notice ");
		query.append("order by n_num desc");
	try {
		pstmt = conn.prepareStatement(query.toString());
		rs = pstmt.executeQuery();
		
		while(rs.next()) {
			noticeVO = new NoticeVO();
			noticeVO.setN_num(rs.getInt("n_num"));
			noticeVO.setN_writer(rs.getString("n_writer"));
			noticeVO.setN_title(rs.getString("n_title"));
			noticeVO.setN_date(rs.getString("n_date"));
			list.add(noticeVO);
		}
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		pstmt.close();
	}
	return list;
	}

}
