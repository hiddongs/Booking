package com.booking.DAO;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;

import com.booking.member.Review;
import com.dbutil.DBUtil;

public class ReviewDAO {
	BufferedReader br = null;
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = null;
	
	static Review review;
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

