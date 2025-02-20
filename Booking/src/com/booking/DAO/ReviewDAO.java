package com.booking.DAO;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;

import com.dbutil.DBUtil;

public class ReviewDAO {
	// 리뷰 작성
	/*
	 * int reviewid;
	String userid;
	int accomodationid;
	Date reviewdate;
	String reviewcontent;
	int reviewrating;
	userid, accomodationid reviewcontent reviewrating
	REVIEW_CONTENT 
	숙소 목록 보여준 후 => 리뷰작성
	 */
	// 리뷰 작성
	// 로그인 되어 있음 -> getUser
	
	// 목록 출력
	
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public void insertReview(int USER_ID, int ACCOMMODATION_ID, String REVIEW_CONTENT, int REVIEW_RATING) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		int cnt = 0;
		
		try {
			//JDBC 수행 1,2 단계
			conn=DBUtil.getConnection();
			// 목록 출력
			sql = "INSERT INTO REVIEW (REVIEW_ID, ACCOMMODATION_ID, REVIEW_DATE, REVIEW_CONTENT, REVIEW_RATING) VALUES(?,?,?,SYSDATE,?,?)";
			// JDBC 수행 3단계
			pstmt = conn.prepareStatement(sql);
			//pstmt.getInt(++cnt, USER_ID);
			pstmt.setInt(++cnt, ACCOMMODATION_ID);
			pstmt.setString(++cnt, REVIEW_CONTENT);
			pstmt.setInt(++cnt, REVIEW_RATING);
		    // JDBC 4단계
			int count = pstmt.executeUpdate();
			System.out.println(count + "개의 리뷰를 작성했습니다.");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}

	
	// 목록 수정
	public void updateReview() {
		
	}
	
}// class
