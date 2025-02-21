package com.booking.DAO;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
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
	// 리뷰 작성
	// 로그인 되어 있음 -> getUser

	// 목록 출력

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
	// 사용자 리뷰 수정
	public void manageReview(String ID, BufferedReader br, int review_ID, String review_content,ReviewDAO reviewDAO) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
         
		try {

			System.out.println("원하는 번호를 선택하세요.");
			System.out.println("1. 리뷰 수정 하기");
			System.out.println("2. 리뷰 삭제 하기");

			int num = Integer.parseInt(br.readLine());

			conn = DBUtil.getConnection();
			try {
				if(num == 1) {
					review = reviewDAO.showReview(ID);
					System.out.println("리뷰 수정하기");
					System.out.println("수정할 리뷰 번호 선택");
					
					try{
						
						int s_num = Integer.parseInt(br.readLine());
						if(s_num == review.getReview_ID()) {
							System.out.println("수정할 내용을 입력하세요");
							review_content = br.readLine();
							sql ="UPDATE \"REVIEW\" SET REVIEW_CONTENT=? WHERE USER_ID=?";
							pstmt = conn.prepareStatement(sql);

							pstmt.setString(1, review_content);
							pstmt.setString(2, ID);
							

							int update = pstmt.executeUpdate();
							if(update == 1) {
								conn.commit();
								System.out.println("수정 성공 ! ! ! ");

							}else
							{
								conn.rollback();
								System.out.println("수정 실패");
							}

						}
					}catch(NumberFormatException  | InputMismatchException e)
					{
						System.out.println("목록 내 번호만 입력하세요.");
					}
					finally {
						DBUtil.executeClose(null, pstmt, conn);
						
					}


				}else if(num == 2) {

					while(true) {

						System.out.println("리뷰 삭제하기");
						System.out.println("삭제할 리뷰 번호 선택");
						num = Integer.parseInt(br.readLine());

						System.out.println("정말 삭제하시겠습니까? ( y / n )");
						char answer = br.readLine().charAt(0);
						try {
							if(answer == 'y') {
								System.out.printf("%c 번호의 리뷰를 삭제합니다.\n", answer);
								sql = "DELETE FROM REVIEW WHERE REVIEW_ID=?";

								pstmt=conn.prepareStatement(sql);
								pstmt.setInt(1, review_ID);

								int update = pstmt.executeUpdate();
								if(update == 1) {
									conn.commit();
									System.out.println("리뷰를 삭제했습니다.");

								}else {
									conn.rollback();
									System.out.println("삭제에 실패했습니다.");
								}
							}else if(answer == 'n') {
								return; // 뒤로 가기
							}
						}catch(InputMismatchException | SQLException e) {
							e.printStackTrace();
						}
						finally {
							DBUtil.executeClose(null, pstmt, conn);
						}
					}

				}


			}catch(InputMismatchException | NumberFormatException | IOException e) {
				e.printStackTrace();
			}

			
		}catch(InputMismatchException | NumberFormatException | ClassNotFoundException | SQLException | IOException e) {
			e.printStackTrace();
		}
		
	}

	public Review showReview(String ID) {

		Review review = null;
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT * FROM REVIEW WHERE USER_ID=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,ID);

			rs = pstmt.executeQuery();
			pstmt.setString(1,ID);


			if(rs.next()) {
				do {
				    review = new Review(); // Review 객체 생성
			        review.setReview_ID(rs.getInt("REVIEW_ID"));
			        review.setID(rs.getString("USER_ID"));
			        review.setAccomodation_ID(rs.getInt("ACCOMODATION_ID"));
			        review.setReview_date(rs.getDate("REVIEW_DATE"));
			        review.setReview_content(rs.getString("REVIEW_CONTENT"));
			        review.setReview_rating(rs.getInt("REVIEW_RATING"));
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

//			if(br != null) try {br.close();} catch(IOException e1) {}

		}
		return review; 
	}


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
}
