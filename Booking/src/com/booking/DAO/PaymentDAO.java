package com.booking.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import com.dbutil.DBUtil;

public class PaymentDAO {
	
	// 1 = 전액현금 , 2= 현금+포인트 , 3= 현금 + 쿠폰 4. 현금+포인트 + 쿠폰
	// 먼저 장바구니에 넣은 아이템 총 가격 계산하고
	// 현금 잔액 조회하고 
	// 그다음에 로직 비교
	
	// 결제처리 메소드
	// 숙소 금액 보기
	// select로 예약 목록 보기
	// int reservation_id, String user_id, int accomodation_id, Date reservation_start_date, Date reservation_end_date, int reservation_price
	// 예약 고유ID 식별자, 유저ID, 예약 대상 숙소 ID, 예약 금액, 예약 시작일, 예약 종료일, 예약날짜에 예약을잡은 인원수)
	public void select_proceePayment(int user_id ) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		
		// 로그인 한 사용자의 user_id를 가져오고 싶음
		
		try {
			//JDBC 1단계
			conn = DBUtil.getConnection();
			//sql 작성
			sql = "SELECT R.USER_ID, R.RESERVATION_START_DATE, R.RESERVATION_END_DATE,R.RESERVATION_PRICE, R.RESERVATION_NUMBER, A.ACCOMMODATION_NAME "
					+ "FROM RESERVATION R JOIN ACCOMMODATION A"
					+ "ON R.ACCOMMODATION_ID=A.ACCOMMODATION_ID "
					+ "WHERE USER_ID = ?";
			// 3단계
			pstmt = conn.prepareStatement(sql);
			// 4단계
			rs=pstmt.executeQuery();
			if(rs.next()) {
				System.out.println("사용자 ID:"+rs.getString("user_id"));
				System.out.println("예약 시작일:" + rs.getDate("reservation_start_date"));
				System.out.println("예약 종료일:" + rs.getDate("reservation_end_date"));
				System.out.println("예약 금액:" + rs.getInt("reservation_price"));
				System.out.println("예약 인원수:"+rs.getInt("reservation_number"));
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
	
	
		
	
	
