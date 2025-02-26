package com.booking.DAO;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.booking.accommodation.Accommodation;
import com.booking.member.User;
import com.booking.member.Reservation;
import com.dbutil.DBUtil;

public class PaymentDAO {
	// user를 받아서 가져오기
	 User user;
	 Reservation reservation;
	 // 생성자
	 public PaymentDAO(User user, Reservation reservation) {
		 this.user = user;
		 this.reservation = reservation;
		 BufferedReader br;
	 }
	 
	 
	// 1 = 전액현금 , 2= 현금+포인트 , 3=현금+쿠폰 4. 현금+포인트 + 쿠폰

	 // 1. cash로만 결제 진행
	// 1) 사용자 현금 잔액 조회
	// select_CheckPayment 다음에 chargeCash가 와야함
	public void select_CheckPayment() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String user_id =user.getID(); // ID를 받아옴
		ResultSet rs = null;
		String sql = null;
		
		try {
			//JDBC 수행 1단계
			conn = DBUtil.getConnection();
			// sql문 작성 user_id,NAME,CASH,USER_GRADE,USER_PAY_SUM
			sql ="select user_id,name, cash,user_grade,user_pay_sum"
					+ "from \"USER\" "
					+ "where user_id = ?";
			//3단계
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			// 4단계
			rs=pstmt.executeQuery();
			if(rs.next()) {
				System.out.println("사용자 ID:"+user.getID());
				System.out.println("사용자 이름:" + rs.getString("name"));
				System.out.println("보유 현금:" + rs.getInt("cash"));
				System.out.println("보유 포인트:" + rs.getInt("point"));
				System.out.println("등급:" + rs.getString("user_grade"));
				System.out.println("결제 현황:"+rs.getInt("user_pay_sum"));
				// 결제 금액이 잔액보다 적으면 결제 가능
				if(user.getCash() >= reservation.getReservation_price()) {
					System.out.println("결제를 진행합니다");
					// 결제 이후 캐시 잔액 업데이트
				}else {
					System.out.println("잔액이 부족합니다. 충전하시겠습니까? (1.예 / 2.아니오)");
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.executeClose(rs,pstmt, conn);
		}	
	}
	
	// 1. 캐시 잔액 업데이트
	public void update_CashPayment(String ID, int CASH, BufferedReader br ){
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			System.out.println("잔액 확인");
			sql="UPDATE \"USER\" SET CASH=? WHERE USER_ID=? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, user.getCash()-reservation.getReservation_price());
			pstmt.setString(2, ID);
			// 업데이트 쿼리 실행
			int update_cash = pstmt.executeUpdate();
			if(update_cash > 0) {
				System.out.println("결제가 완료되었습니다.");
			}else {
				System.out.println("결제 실패: 사용자 정보가 업데이트 되지 않았습니다.");
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		}
	}
	
	//2 = 현금+포인트
	// Check_cashandPoint
	public void Check_cashandPoint(BufferedReader br, String ID) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		ResultSet rs = null;
		String CP_sql = null;
		String user_id =user.getID(); //userID 기져옴
		
		try {
			// JDBC수행 1단계
			conn = DBUtil.getConnection();
			// SQL문 작성
			// 예약 가격
			//  이름, 현금, 포인트
			CP_sql = "select user_id, name, cash, point"
					+ "from \"USER\" "
					+ "where user_id = ?";
			// 수행 3단계
			pstmt=conn.prepareStatement(CP_sql);
			pstmt.setString(1, user_id);
			//4단계
			rs = pstmt.executeQuery();
			// 사용자 정보 출력
			if(rs.next()) {
				System.out.println("사용자 ID:"+user.getID());
				System.out.println("사용자 이름:" + rs.getString("name"));
				System.out.println("보유 현금:" + rs.getInt("cash"));
				System.out.println("보유 포인트:" + rs.getInt("point"));
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			
		}
	}
	
	// 3. 현금+쿠폰
	
	
	
	
	
	
	// 4. 현금+포인트 + 쿠폰
	
	
	
	
	
	
	
	
} // class
	
	
		
	
	
