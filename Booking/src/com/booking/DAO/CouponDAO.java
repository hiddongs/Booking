package com.booking.DAO;

import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.nio.Buffer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.booking.member.Admin;
import com.booking.member.Coupon;
import com.dbutil.DBUtil;

public class CouponDAO {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
    static Coupon coupon;
    static Admin admin;
	public void updateCoupon(Admin ID) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		try {
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}	
	
		
	}
	// 기본 쿠폰 (신규 사용자에게 기본으로 주는 쿠폰)
	public Coupon firstCoupon(int coupon_ID) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		ResultSet rs = null;
		try {
			
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			sql = "SELECT * FROM COUPON WHERE COUPON_ID=?";
			pstmt.setInt(1, coupon_ID);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				 coupon = new Coupon(
		                    rs.getInt("COUPON_ID"),
		                    rs.getInt("ADMIN_ID"),
		                    rs.getString("COUPON_CODE"),
		                    rs.getDate("COUPON_ISSUANCE_DATE"),
		                    rs.getDate("COUPON_EXPIRED_DATE"),
		                    rs.getInt("COUPON_DISCOUNT")
		                );
			}
			
			
		} catch (SQLException | ClassNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
		return coupon;
		
	}
	
	// 신규 사용자에게 기본 쿠폰을 지급하는 코드
	public void giveNewUserCoupon(Coupon coupon) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			sql = "INSERT INTO \"USER\" (USER_ID, COUPON_ID, ISSUE_DATE, USED) " +
					"SELECT U.USER_ID, ?, SYSDATE, 'N' " +
					"FROM USERS U " +
					"WHERE U.JOIN_DATE = SYSDATE";
			
			pstmt.setInt(1, coupon.getCoupon_ID());
			rs = pstmt.executeQuery();
			
			
			int update = pstmt.executeUpdate();
			if(update == 1) {
				conn.commit();
				System.out.println("신규 사용자에게 기본 쿠폰을 지급했습니다.");
			}
			else {
				conn.rollback();
				System.out.println("쿠폰 지급에 실패했습니다.");
			}
			}catch (Exception e) {
			// TODO: handle exception
				e.printStackTrace();
		}
		finally {
			DBUtil.executeClose(null, pstmt, conn);
		}

	}
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

	// 쿠폰 등록하는 코드
	public void reg_coupon(BufferedReader br) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		
		try {
			sql = "INSERT INTO COUPON (COUPON_ID, ADMIN_ID, COUPON_CODE, COUPON_ISSUANCE_DATE, COUPON_EXPIRED_DATE, COUPON_DISCOUNT) " +
                    "VALUES (COUPON_SEQ.NEXTVAL, ?, ?, SYSDATE, ?, ?)";

			
			
			//System.out.print("쿠폰 번호 : ");
			//int = Integer.parseInt(br.readLine());
			
			System.out.print("쿠폰 번호 : ");
			int coupon_ID = Integer.parseInt(br.readLine());
			
			//System.out.print("쿠폰 번호 : ");
			//int coupon_ID = Integer.parseInt(br.readLine());
			
			
			
		}catch(Exception e) {
			
		}
		
	}
}
