package com.booking.DAO;

import java.io.BufferedReader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.booking.accommodation.Accommodation;
import com.dbutil.DBUtil;

public class AccommodationviewDAO {
<<<<<<< HEAD
	// 국내
=======
	// 국내만 보여주기
>>>>>>> branch 'main' of https://github.com/hiddongs/Booking.git
	public void selectdomesticInfo() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
<<<<<<< HEAD
			sql = "select * from accommodation where location_name != '해외'";
=======
			sql = "select * from accommodation where location_name != '해외' order by accommodation_id desc";
>>>>>>> branch 'main' of https://github.com/hiddongs/Booking.git
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			System.out.println("================================================");
			if(rs.next()) {
				System.out.println("숙소번호\t숙소이름\t지역\t주소\t운영상태\t예약 가능 인원");
				do {
					System.out.print(rs.getInt("accommodation_id"));
					System.out.print("\t");
					System.out.print(rs.getString("accommodation_name"));
					System.out.print("\t");
					System.out.print(rs.getString("location_name"));
					System.out.print("\t");
					System.out.print(rs.getString("accommodation_address"));
					System.out.print("\t");
					System.out.print(rs.getString("accommodation_status"));
					System.out.print("\t");
					System.out.print(rs.getString("allowed_number"));
					System.out.println();
					
					
				} while (rs.next());
			}else {
				System.out.println("검색된 정보가 없습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//자원관리
			DBUtil.executeClose(rs, pstmt, conn);
		}
	}
<<<<<<< HEAD
	
	// 해외
		public void selectOverseasInfo() {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = null;
			
			try {
				conn = DBUtil.getConnection();
				sql = "select * from accommodation where location_name = '해외'";
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				System.out.println("================================================");
				if(rs.next()) {
					System.out.println("숙소번호\t숙소이름\t\t지역\t주소");
					do {
						System.out.print(rs.getInt("accommodation_id"));
						System.out.print("\t");
						System.out.print(rs.getString("accommodation_name"));
						System.out.print("\t");
						System.out.print(rs.getString("location_name"));
						System.out.print("\t");
						System.out.println(rs.getString("accommodation_address"));
						
					} while (rs.next());
				}else {
					System.out.println("검색된 정보가 없습니다.");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				//자원관리
				DBUtil.executeClose(rs, pstmt, conn);
			}
		}
	
=======
	// 해외만 보여주기
		public void selectoverseasInfo() {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = null;
			
			try {
				conn = DBUtil.getConnection();
				sql = "select * from accommodation where location_name = '해외' order by accommodation_id desc";
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				System.out.println("================================================");
				if(rs.next()) {
					System.out.println("숙소번호\t숙소이름\t지역\t주소\t운영상태\t\t예약 가능 인원");
					do {
						System.out.print(rs.getInt("accommodation_id"));
						System.out.print("\t");
						System.out.print(rs.getString("accommodation_name"));
						System.out.print("\t");
						System.out.print(rs.getString("location_name"));
						System.out.print("\t");
						System.out.print(rs.getString("accommodation_address"));
						System.out.print("\t");
						System.out.print(rs.getString("accommodation_status"));
						System.out.print("\t");
						System.out.print(rs.getString("allowed_number"));
						System.out.println();
						
					} while (rs.next());
				}else {
					System.out.println("검색된 정보가 없습니다.");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				//자원관리
				DBUtil.executeClose(rs, pstmt, conn);
			}
		}
>>>>>>> branch 'main' of https://github.com/hiddongs/Booking.git
	public void selectDetailInfo(int accommodation_id) {
		// 숙소 상세 정보 보기
		// 정보 : 숙소ID(시퀀스), 숙소이름, 설명, 가격, 추천계절, 숙소 정원
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			sql = "select * from accommodation where accommodation_id = ? ";
			// 3단계
			pstmt = conn.prepareStatement(sql);
			// 데이터 할당
			pstmt.setInt(1, accommodation_id);
			// 4단계
			rs = pstmt.executeQuery();
			if(rs.next()) {
				System.out.println("숙소 ID: "+rs.getInt("accommodation_id"));
				System.out.println("숙소 이름: "+rs.getString("accommodation_name"));
				System.out.println("숙소 설명: "+rs.getString("accommodation_description"));
				System.out.println("숙소 가격: "+rs.getInt("accommodation_price"));
				System.out.println("추천 계절: "+rs.getString("recommendation_season"));
				System.out.println("숙소 정원: "+rs.getString("allowed_number"));
			}else {
				System.out.println("검색된 정보가 없습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
	} // public
} // class
