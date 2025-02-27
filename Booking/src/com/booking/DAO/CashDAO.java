package com.booking.DAO;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Random;

import com.booking.member.User;
import com.dbutil.DBUtil;

public class CashDAO {
	
	static UserDAO userDAO;
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  
	public void chargeCash(String ID, int cash, BufferedReader br) {
		Connection conn = null;
		PreparedStatement pstmtU = null;
		String sqlU = null;

			try {
				conn = DBUtil.getConnection();
				// 기존 금액 유지함녀서 충전 기능 추가
			    sqlU =  "UPDATE \"USER\" SET CASH = CASH + ? WHERE USER_ID=?";
			    pstmtU = conn.prepareStatement(sqlU);
			    pstmtU.setInt(1, cash);
			    pstmtU.setString(2, ID);
			    int update = pstmtU.executeUpdate();
			    if(update == 1) {
			    	conn.commit();
			    	System.out.println("충전 성공 !");
			    }else {
			    	conn.rollback();
			    	System.out.println("충전 실패 ! ! ! ");
			    }
			    
			}catch(NumberFormatException | InputMismatchException | ClassNotFoundException | SQLException e1) {
				
				e1.printStackTrace();
				System.out.println("정수가 아닌 다른 형태는 입력할 수 없습니다. 숫자만 입력하세요.");
				 			    
			}finally {
			DBUtil.executeClose(null, pstmtU, conn);
			}
			
	}
		


	
	public void showCash(String ID) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.getConnection();		   
			
			sql = "SELECT USER_ID, CASH FROM \"USER\" WHERE USER_ID=?";
			pstmt = conn.prepareStatement(sql);
		//	pstmt.setString(1,user.getID());
		    pstmt.setString(1,ID);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				do {
					System.out.println("============================================================");
					System.out.println("사용자 ID : " + rs.getString("USER_ID"));
					System.out.println("사용자 보유 금액 : " + rs.getInt("CASH"));
					System.out.println("============================================================");
				}
				while(rs.next());
			}
			}catch(Exception e) {
				e.printStackTrace();
			}
			finally {
				DBUtil.executeClose(rs, pstmt, conn);
			}
	}
	
	public int showPoint(String ID) {
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    int point = -1; // Default value in case of failure
	    
	    try {
	        conn = DBUtil.getConnection();
	       

	        String sql = "SELECT POINT FROM \"USER\" WHERE USER_ID=?";
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, UserDAO.getCurrentUserID());

	        rs = pstmt.executeQuery();
	        
	        if (rs.next()) { // Check if result exists
	            point = rs.getInt("POINT");
	            System.out.println("============================================================");
	            System.out.println("사용자 ID : " + UserDAO.getCurrentUserID());
	            System.out.println("사용자 보유 포인트 : " + point);
	            System.out.println("============================================================");
	        } else {
	            System.out.println("해당 사용자 정보를 찾을 수 없습니다.");
	        }
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        DBUtil.executeClose(rs, pstmt, conn);
	    }

	    return point;
	}
	
	public void updatePoint(String ID) {


		Connection conn = null;
		PreparedStatement pstmtU = null;
		ResultSet rs = null;
		
		
		try {
			int point = (new Random().nextInt(1501) + 500);
			
			conn = DBUtil.getConnection();
			// 기존 금액 유지하면서 포인트 충전 
		    String sqlU =  "UPDATE \"USER\" SET POINT = POINT + ? WHERE USER_ID=?";
		    pstmtU = conn.prepareStatement(sqlU);
		    pstmtU.setInt(1, point);
		    pstmtU.setString(2, ID);
		    int update = pstmtU.executeUpdate();
		    if(update == 1) {
		    	conn.commit();
		    	System.out.println(point + "포인트 적립 성공 !");
		    }else {
		    	conn.rollback();
		    	System.out.println("충전 실패 ! ! ! ");
		    }
		    
		}catch(NumberFormatException | InputMismatchException | ClassNotFoundException | SQLException e1) {
			
			e1.printStackTrace();
			
			 			    
		}finally {
		DBUtil.executeClose(null, pstmtU, conn);
		}

	}
	public void usePoint(String ID,int point) {
		Connection conn = null;
		PreparedStatement pstmtU = null;
		ResultSet rs = null;

		try {

			conn = DBUtil.getConnection();
			// 기존 금액 유지하면서 포인트 충전 
		    String sqlU =  "UPDATE \"USER\" SET POINT = POINT - ? WHERE USER_ID=?";
		    pstmtU = conn.prepareStatement(sqlU);
		    pstmtU.setInt(1, point);
		    pstmtU.setString(2, ID);
		    int update = pstmtU.executeUpdate();
		    if(update == 1) {
		    	conn.commit();
		    	System.out.println( point+ "포인트 사용 성공 !");
		    }else {
		    	conn.rollback();
		    	System.out.println("포인트 사용 실패 ! ! ! ");
		    }
		    
		}catch(NumberFormatException | InputMismatchException | ClassNotFoundException | SQLException e1) {
			
			e1.printStackTrace();
			
			 			    
		}finally {
		DBUtil.executeClose(null, pstmtU, conn);
		}

	}
	public void useCash(String ID,int cash) {
		Connection conn = null;
		PreparedStatement pstmtU = null;
		ResultSet rs = null;

		try {

			conn = DBUtil.getConnection();
			// 기존 금액 유지하면서 포인트 충전 
		    String sqlU =  "UPDATE \"USER\" SET CASH = CASH - ? WHERE USER_ID=?";
		    pstmtU = conn.prepareStatement(sqlU);
		    pstmtU.setInt(1, cash);
		    pstmtU.setString(2, ID);
		    int update = pstmtU.executeUpdate();
		    if(update == 1) {
		    	conn.commit();
		    	System.out.println(cash + "원화 사용 성공 !");
		    }else {
		    	conn.rollback();
		    	System.out.println("원화 사용 실패 ! ! ! ");
		    }
		    
		}catch(NumberFormatException | InputMismatchException | ClassNotFoundException | SQLException e1) {
			
			e1.printStackTrace();
			
			 			    
		}finally {
		DBUtil.executeClose(null, pstmtU, conn);
		}

	}
        
        
	
	public boolean checkPay(int reservation_ID) {
		Connection conn = null;
		PreparedStatement pstmtS = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			
			String sql = "SELECT RESERVATION_ID FROM PAYMENT" +		      
				      	 "WHERE RESERVATION_ID=?";
			
			pstmtS = conn.prepareStatement(sql);
			pstmtS.setInt(1, reservation_ID);
	
			if(rs.next()) {
				System.out.println("결제 처리된 예약");
				return true;
			}
			else {
				System.out.println("미결제 예약");
				return false;
			}
		 
		}catch(Exception e) {
			
		}
		return false;

	}
}
