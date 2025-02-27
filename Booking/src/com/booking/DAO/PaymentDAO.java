package com.booking.DAO;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.booking.accommodation.Accommodation;
import com.booking.member.Coupon;
import com.booking.member.Payment;
import com.booking.member.User;

//public class PaymentDAO {
//   BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//   // 결제할 때 필요한 것
//   // 결제, 쿠폰, 숙소, 장바구니, 사용자(포인트) 클래스 필요
//   static User user; // 포인트 가져오려고 
//   static Payment payment;
//   static Coupon coupon;
//   static Accommodation accomodation;
//   // 장바구니도 가져와야 할 것 같음 - 장바구니의 ID를 가져와야 할거같은디
//   // create, update, insert, delete
//}


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.booking.accommodation.Accommodation;
import com.booking.member.User;
import com.booking.member.Reservation;
import com.dbutil.DBUtil;
//userDAO 사용
public class PaymentDAO {

   // user를 받아서 가져오기
    User user;
    Reservation reservation;
    UserDAO userDAO;
    // 생성자
   
   
	 // 생성자
	 public PaymentDAO(User user, Reservation reservation) {
		 this.user = user;
		 this.reservation = reservation;
		 BufferedReader br;
	 }
	
	 public int[] processCashPayment(String user_id, int reservation_id) throws ClassNotFoundException {

     Connection conn = null;
     PreparedStatement pstmt = null;
     ResultSet rs = null;
     
     try {
         conn = DBUtil.getConnection();
         String sql = "SELECT CASH, RESERVATION_PRICE FROM \"USER\" U "
                 + "JOIN RESERVATION R ON U.USER_ID = R.USER_ID "
                 + "WHERE U.USER_ID = ? AND R.RESERVATION_ID = ?";
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, user_id);
         pstmt.setInt(2, reservation_id);
         rs = pstmt.executeQuery();
         
         if (rs.next()) {
             int userCash = rs.getInt("CASH"); // 현금 내역
             int accommodationPrice = rs.getInt("RESERVATION_PRICE"); // 예약 금액
             return new int[] {userCash, accommodationPrice}; // 현금이랑 예약 금액 배열로 받아옴
         } else {
             System.out.println("해당 예약 사항이 없습니다.");
         }
     } catch (SQLException e) {
         e.printStackTrace();
     } finally {
         DBUtil.executeClose(rs, pstmt, conn);
     }
     return null;
 }

   // 현금 결제 후 잔액 업데이트
       public void updateCashPayment(String userId, int newCashBalance) throws ClassNotFoundException {
           Connection conn = null;
           PreparedStatement pstmt = null;
           String sql = "UPDATE \"USER\" SET CASH = ? WHERE USER_ID = ?";
           
           try {
               conn = DBUtil.getConnection();
               pstmt = conn.prepareStatement(sql);
               pstmt.setInt(1, newCashBalance);
               pstmt.setString(2, userId);
               int update = pstmt.executeUpdate();
               if (update == 1) {
                   conn.commit();
                   System.out.println("잔액 업데이트 완료");
               } else {
                   conn.rollback();
                   System.out.println("잔액 업데이트 실패");
               }
           } catch (SQLException e) {
               e.printStackTrace();
           } finally {
               DBUtil.executeClose(null, pstmt, conn);
           }
       }


    // 결제 내역 기록
       
       
	 // 결제 내역 기록
	    public void recordPaymentHistory(String USER_ID,int RESERVATION_ID, int PAYMENT_USED_CASH, int PAYMENT_METHOD ) throws SQLException, ClassNotFoundException {
	        Connection conn = null;
	        PreparedStatement pstmt = null;
	        String sql = "INSERT INTO PAYMENT(PAYMENT_ID,USER_ID, RESERVATION_ID, PAYMENT_USED_CASH,PAYMENT_USED_POINT,PAYMENT_TOTAL_PRICE, PAYMENT_DATE, PAYMENT_METHOD)"
	                   + "VALUES(PAYMENT_SEQ.NEXTVAL, ?, ?, ?, ?, ?, SYSDATE, ?)";
	        try {
	            conn = DBUtil.getConnection();
	            pstmt = conn.prepareStatement(sql);	            
	            pstmt.setString(1, USER_ID);
	            pstmt.setInt(2, RESERVATION_ID);
	            pstmt.setInt(3, PAYMENT_USED_CASH);
	            pstmt.setInt(4, 0);
	            pstmt.setInt(5, PAYMENT_USED_CASH);
	            pstmt.setInt(6, PAYMENT_METHOD);
	            
	            int count = pstmt.executeUpdate();
	            
	            System.out.println(count+"개의 행을 결제 내역에 기록되었습니다.");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            DBUtil.executeClose(null, pstmt, conn);
	        }
	    }
	    //결제 내역, select로 기록한 것을\확인하기
	    public void select_PaymentHistory() {
	    	Connection conn = null;
	    	PreparedStatement pstmt = null;
	    	String sql = null;
	    	ResultSet rs = null;
	    	
	    	try {
				conn = DBUtil.getConnection();
				sql = "SELECT * FROM PAYMENT order by PAYMENT_ID desc";
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				System.out.println("=============================");
				if (rs.next()) {
					System.out.println("번호\t결제유저\t결제 진행한 예약 ID\t사용한 현금 \t결제날짜");
				do {
					System.out.print(rs.getInt("PAYMENT_ID"));
					System.out.print("      ");
					
					System.out.print(rs.getString("USER_ID"));
					System.out.print("     ");
					System.out.print(rs.getInt("RESERVATION_ID"));
					System.out.print("           ");
					System.out.print(rs.getInt("PAYMENT_USED_CASH"));
					System.out.print("            ");
					System.out.print(rs.getDate("PAYMENT_DATE"));
					System.out.println();

					
				}while(rs.next());
				}else {
					System.out.println("표시할 데이터 없습니다.");
				}
				System.out.println("=============================");
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				DBUtil.executeClose(rs,pstmt, conn);
			}
	    }

           

} // class
   
   
      


	
		

