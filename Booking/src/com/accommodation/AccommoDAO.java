package com.accommodation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.dbutil.DBUtil;

public class AccommoDAO {

	// 목록보기
	public void selectInfo() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			sql = "select * from accommodation order by accommodation_id desc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			System.out.println("=========================");
			if(rs.next()) {
				System.out.println("번호\t숙소이름\t지역\t주소");
			
				do {
					System.out.print(rs.getInt("accommodation_id"));
					System.out.print("\t");
					System.out.print(rs.getString("accommodation_name"));
					System.out.print("\t");
					System.out.print(rs.getString("location_name"));
					System.out.print("\t");
					System.out.print(rs.getString("accommodation_adress"));
					System.out.print("\t");
				} while (rs.next());
			}else {
				System.out.println();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//자원관리
			DBUtil.executeClose(rs, pstmt, conn);
		}
	}
	
	
	
	public void selectDetailInfo() {
		// 숙소 상세 보기
		// 정보 : 숙소ID(시퀀스), 숙소이름, 
		// 주소, 설명, 가격, 지역 이름, 추천계절, 숙소운영여부, 숙서정원
		
		
	}
	




}//class
