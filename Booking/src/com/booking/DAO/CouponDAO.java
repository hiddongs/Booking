package com.booking.DAO;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
	// 사용자가 겹치는 쿠폰이 있는지 확인하는 코드
	
	public boolean  isDupUserCoupon(int coupon_ID) {
	
			Connection conn = null;
		    PreparedStatement pstmt = null;
		    ResultSet rs = null;
		    String sql = "SELECT COUNT(*) FROM COUPON WHERE COUPON_ID = ?";
		    try {
		        conn = DBUtil.getConnection();
		        pstmt = conn.prepareStatement(sql);
		        pstmt.setInt(1, coupon_ID);
		        rs = pstmt.executeQuery();

		        if (rs.next() && rs.getInt(1) > 0) {
		            return true;  // 중복된 쿠폰 코드가 있음
		        }

		    } catch (Exception e) {
		        e.printStackTrace();
		    } finally {
		        DBUtil.executeClose(rs, pstmt, conn);
		    }

		    return false;  // 중복된 쿠폰 코드 없음
		}
		
		
	
	// 사용자에게 기본 쿠폰을 지급하는 코드
	public void giveNewUserCoupon(int coupon_ID) {
		Connection conn = null;
		PreparedStatement insert_pstmt = null;
		PreparedStatement update_pstmt = null;
		String insert_sql = null;
		String update_sql = null;
		
		try {
			conn = DBUtil.getConnection();
			insert_pstmt = conn.prepareStatement(insert_sql);
			
			insert_sql = "INSERT INTO \"USER\" (USER_ID, COUPON_ID, ISSUE_DATE, USED) " +
                    "SELECT U.USER_ID, ?, SYSDATE, 'N' " +
                    "FROM USERS U WHERE U.JOIN_DATE = SYSDATE";
			
			insert_pstmt.setInt(1, coupon.getCoupon_ID());
			
			
			int update = insert_pstmt.executeUpdate();
			
			update_sql = "UPDATE COUPON SET COUPON_COUNT = COUPON_COUNT + 1 " +
                    "WHERE USER_ID IN (SELECT USER_ID FROM USERS WHERE JOIN_DATE = SYSDATE) " +
                    "AND COUPON_ID = ?";
			update_pstmt = conn.prepareStatement(update_sql);
			update_pstmt.setInt(1, coupon_ID);
			update_pstmt.executeUpdate();
			
			int update2 = update_pstmt.executeUpdate();
			
			  if (update > 0 || update2 > 0) {
		            conn.commit();
		            System.out.println(" 쿠폰 지급 성공!");
		        } else {
		            conn.rollback();
		            System.out.println(" 쿠폰 지급 실패!");
		        }
			}catch (Exception e) {
			// TODO: handle exception
				e.printStackTrace();
				
			
		}
		finally {
			DBUtil.executeClose(null, insert_pstmt, null);
	        DBUtil.executeClose(null, update_pstmt, conn);
			
		}

	}
	
	public void giveCouponUser(String ID, int coupon_id) {
		Connection conn = null;
		PreparedStatement select_pstmt = null;
		PreparedStatement insert_pstmt = null;
		PreparedStatement update_pstmt = null;
		String insert_sql = null;
		String update_sql = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.getConnection();
			
			String select_sql = "SELECT COUPON_ID, COUPON_CODE,COUPON_DISCOUNT FROM COUPON";
			rs = select_pstmt.executeQuery();

	        List<Integer> couponList = new ArrayList<>();
	        while (rs.next()) {
	            couponList.add(rs.getInt("COUPON_ID"));
	        }
			
		} catch (Exception e) {
			// TODO: handle exception
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
                    "VALUES (COUPON_SEQ.NEXTVAL, ?, ?, SYSDATE, SYSDATE + 30, ?)";
				
			String AdminID = "ADMIN";
			System.out.print("쿠폰 코드 : \n");
		    String coupon_code = br.readLine();
			
		    System.out.print("할인율 가격 : ");		
		    int coupon_discount = Integer.parseInt(br.readLine());
		    pstmt = conn.prepareStatement(sql);
		    pstmt.setString(1, AdminID);
	        pstmt.setString(2, coupon_code);
	        pstmt.setInt(3, coupon_discount);
		    
		    if(!isCouponExists(coupon_code)) {
		    	int update = pstmt.executeUpdate();
			    if(update == 1) {
			    	conn.commit();
			    	System.out.println("쿠폰 발급 성공");
			    }
			    else {
			    	conn.rollback();
			    	System.out.println("쿠폰 발급 실패");
			    }
		    }
		    else {
		    	
			    	conn.rollback();
			    	System.out.println("쿠폰 발급 실패");
			    	System.out.println("쿠폰 코드가 겹칩니다");
			    
		    }
		    	
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
		
	}
	
	// 관리자가 모든 쿠폰을 확인하는 코드
	public void showAllCoupon(String ID) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		
		try {
			System.out.println(" 보유한 쿠폰 목록 조회 ");
			conn = DBUtil.getConnection();
			sql = "SELECT * FROM COUPON WHERE ADMIN_ID=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ID);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				System.out.println("쿠폰 코드 : " + rs.getInt("COUPON_CODE"));
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
	
	// 발급하려는 코드가 중복되면 표시

	public boolean isCouponExists(String coupon_code) {
		
		Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    String sql = "SELECT COUNT(*) FROM COUPON WHERE COUPON_CODE = ?";
	    try {
	        conn = DBUtil.getConnection();
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, coupon_code);
	        rs = pstmt.executeQuery();

	        if (rs.next() && rs.getInt(1) > 0) {
	            return true;  // 중복된 쿠폰 코드가 있음
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        DBUtil.executeClose(rs, pstmt, conn);
	    }

	    return false;  // 중복된 쿠폰 코드 없음
	}
	
	// 사용한 쿠폰이면 지워주는 코드
	
	
}
