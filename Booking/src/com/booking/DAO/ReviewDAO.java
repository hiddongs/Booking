package com.booking.DAO;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;

import com.booking.member.Review;
import com.dbutil.DBUtil;

public class ReviewDAO {
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = null;
	
	static Review review;
	
	// 리뷰 작성
		/*
		 * int reviewid;
		String userid;
		int accomodationid;
		Date reviewdate;
		String reviewcontent;
		int reviewrating;
		userid, accomodationid reviewcontent reviewrating
		REVIEW_CONTENT 
		숙소 목록 보여준 후 => 리뷰작성
		 */
		
	// 목록 출력
	// 리뷰 작성
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public void insertReview(int USER_ID, int ACCOMMODATION_ID, String REVIEW_CONTENT, int REVIEW_RATING) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		int cnt = 0;
		
		try {
			//JDBC 수행 1,2 단계
			conn=DBUtil.getConnection();
			// 목록 출력
			// 우선 시퀀스 받아야 함
			sql = "INSERT INTO REVIEW (REVIEW_ID, ACCOMMODATION_ID, REVIEW_DATE, REVIEW_CONTENT, REVIEW_RATING) VALUES(?,?,?,SYSDATE,?,?)";
			// JDBC 수행 3단계
			pstmt = conn.prepareStatement(sql);
			//pstmt.getInt(++cnt, USER_ID);
			pstmt.setInt(++cnt, ACCOMMODATION_ID);
			pstmt.setString(++cnt, REVIEW_CONTENT);
			pstmt.setInt(++cnt, REVIEW_RATING);
		    // JDBC 4단계
			int count = pstmt.executeUpdate();
			System.out.println(count + "개의 리뷰를 작성했습니다.");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}

	
	// 목록 수정
	public void updateReview() {
		
	}
	public void rewriteReview(String ID, BufferedReader br, Review review_ID) {
		
		
		try {
			System.out.println("1. 리뷰 수정 하기");
			System.out.println("2. 리뷰 삭제 하기");
			int num = Integer.parseInt(br.readLine());
			conn = DBUtil.getConnection();
			try {
				if(num == 1) {

					System.out.println("리뷰 수정하기");
					System.out.println("수정할 리뷰 번호 선택");
					num = Integer.parseInt(br.readLine());
					try{
						if(num == review.getReview_ID()) {
							System.out.println("수정할 내용을 입력하세요");
						}
					}catch(Exception e)
					{
						
					}
												
					
				}else if(num == 2) {

				}
				
			}catch(Exception e) {
				e.printStackTrace();
			}

			
		}catch(Exception e) {
			
		}
		
	}

	public void showReview(String ID) {

		try {
			conn = DBUtil.getConnection();
			sql = "SELECT * FROM \"REVIEW\" WHERE USER_ID=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,ID);

			rs = pstmt.executeQuery();
			pstmt.setString(1,ID);


			if(rs.next()) {
				do {
					System.out.println("----------------------------------------------");
					System.out.println("번호 : " + rs.getInt("REVIEW_ID"));
					System.out.println("작성자 이름 : " + rs.getString("USER_ID"));
					System.out.println("숙소 번호 : " + rs.getInt("ACCOMODATION_ID"));
					System.out.println("리뷰 작성 날짜 : " + rs.getDate("REVIEW_DATE"));
					System.out.println("리뷰 내용 : " + rs.getString("REVIEW_CONTENT"));
					System.out.println("평점 : " + rs.getInt("REVIEW_RATING"));
					System.out.println("----------------------------------------------");
				}while(rs.next());
				

			}	
			else {
				System.out.println("검색 리뷰 없음");
			}
			System.out.println();
		}    
		catch(NumberFormatException | InputMismatchException | ClassNotFoundException | SQLException e1) {

			e1.printStackTrace();
			
		}

		finally {
			DBUtil.executeClose(rs, pstmt, conn);
			if(br != null) try {br.close();} catch(IOException e1) {}
		}
	} 
}
