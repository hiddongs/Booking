package com.booking.DAO;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.InputMismatchException;

import com.dbutil.DBUtil;

public class CashDAO {
	
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public void chargeCash(String ID, int cash, BufferedReader br) {
		Connection conn = null;
		PreparedStatement pstmtU = null;
		String sqlU = null;

			try {
				conn = DBUtil.getConnection();
			    sqlU =  "UPDATE \"USER\" SET CASH =? WHERE USER_ID=?";
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
		


	
	public int showCash(String ID, int cash) {
		return cash;
	}
	public int showPoint(String ID, int point){
		return point;
		
	}
}
