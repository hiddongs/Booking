package com.booking.DAO;

import java.io.BufferedReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import com.booking.member.User;
import com.dbutil.DBUtil;

public class PaymentDAO {
	// user를 받아서 가져오기
	 User user;
	 // 생성자
	 public PaymentDAO(User user) {
		 this.user = user;
	 }
	 
	 BufferedReader br;
	// 1 = 전액현금 , 2= 현금+포인트 , 3= 현금 + 쿠폰 4. 현금+포인트 + 쿠폰

	// select로 예약 목록 보기
	// int reservation_id, String user_id, int accomodation_id, Date reservation_start_date, Date reservation_end_date, int reservation_price
	// 예약 고유ID 식별자, 유저ID, 예약 대상 숙소 ID, 예약 금액, 예약 시작일, 예약 종료일, 예약날짜에 예약을잡은 인원수)
	public void select_procesPayment(String user_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		
		try {
			//JDBC 1단계
			conn = DBUtil.getConnection();
			//sql 작성
			sql = "SELECT USER_ID, RESERVATION_START_DATE, RESERVATION_END_DATE, RESERVATION_PRICE, RESERVATION_NUMBER, ACCOMMODATION_NAME"
					+ "FROM RESERVATION R JOIN ACCOMMODATION A"
					+ "ON R.ACCOMMODATION_ID = A.ACCOMMODATION_ID"
					+ "WHERE USER_ID = ?";
			// 3단계
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			// 4단계
			rs=pstmt.executeQuery();
			if(rs.next()) {
				System.out.println("사용자 ID:"+user.getID());
				//System.out.println("사용자 ID:"+rs.getString("user_id"));
				System.out.println("예약 시작일:" + rs.getDate("reservation_start_date"));
				System.out.println("예약 종료일:" + rs.getDate("reservation_end_date"));
				System.out.println("예약 금액:" + rs.getInt("reservation_price"));
				System.out.println("예약 인원수:"+rs.getInt("reservation_number"));
				System.out.println("예약된 숙소:"+rs.getString("accommodation_name"));
				
			}else {
				System.out.println("검색된 정보가 없습니다.");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.executeClose(rs,pstmt, conn);
		}	
	}
	
	
	
	
} // class
	
	
		
	
	
