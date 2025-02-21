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

	// 숙소 목록을 띄우고 -> 숙소 번호 선택했을 때 상세정보 출력
	// 숙소 리뷰 보시겠습니까? 멘트 나오고 '(1)예/(2)아니오' 선택
	// 예 선택 했들 때 해당 숙소번호 입력해주세요 > 
	// 관련 숙소에 대한 상세 리뷰 보기 


	// 이 부분 다시 확인하기
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
			sql = "INSERT INTO REVIEW (REVIEW_ID, ACCOMMODATION_ID, REVIEW_DATE, REVIEW_CONTENT, REVIEW_RATING) VALUES(?,?,?,SYSDATE,?,?)";
			// JDBC 수행 3단계
			pstmt = conn.prepareStatement(sql);
			// pstmt.getInt(++cnt, USER_ID);
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

	// 해당 숙소에 대한 리뷰 보기
	public void selectdetailReview(int accomodation_ID) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();
			sql ="SELECT * FROM REVIEW WHERE ACCOMODATION_ID =? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, accomodation_ID);
			rs = pstmt.executeQuery();

			if(rs.next()){
				System.out.println("-------------------------------------------------");
				System.out.println("사용자 ID:" + rs.getString("user_ID"));
				System.out.println("리뷰 대상 숙소 번호:" + rs.getInt("accomodation_ID"));
				System.out.println("리뷰 작성일:" + rs.getDate("review_date"));
				System.out.println("리뷰 내용:"+rs.getString("review_content"));
				System.out.println("리뷰 평점:"+rs.getInt("review_rating"));
				System.out.println("-------------------------------------------------");
			}else {
				System.out.println("검색된 숙소 리뷰가 없습니다.");
			}
			System.out.println("========================");
		} catch(NumberFormatException | InputMismatchException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.executeClose(rs,  pstmt, conn);
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
			sql = "SELECT * FROM REVIEW WHERE USER_ID=?";
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
