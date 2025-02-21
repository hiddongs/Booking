package com.booking.DAO;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.dbutil.DBUtil;

public class CouponDAO {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public void showUserCoupon(String ID) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		
		try {
			System.out.println(" 보유한 쿠폰 목록 조회 ");
			conn = DBUtil.getConnection();
			sql = "SELECT * FROM COUPON WHERE USER_ID=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ID);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				System.out.println("쿠폰 번호 : " + rs.getInt("COUPON_CODE"));
				System.out.println("발급 일자 : " + rs.getDate("COUPON_ISSUANCE_DATE"));
				System.out.println("만료일 : " + rs.getDate("COUPON_EXPIRED_DATE"));
				System.out.println("할인 금액 : " + rs.getInt("COUPON_DISCOUNT"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
	}
}
