package com.booking.DAO;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.booking.member.Admin;
import com.dbutil.DBUtil;

public class QnADAO {

	BufferedReader br;
	Admin admin;
	
	public QnADAO(BufferedReader br , Admin admin){
		this.br = br;
		this.admin = admin;
	}

	public void answerToQNA() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		ResultSet rs = null;
		List<Integer> qna_id_list = new ArrayList<>();

		try { // 답변하지않은 항목들 추출
			conn = DBUtil.getConnection();
			sql = "SELECT * "
					+ "FROM QNA Q INNER JOIN QNA_SUBJECT QS "
					+ "ON Q.SUBJECT_ID = QS.SUBJECT_ID "
					+ "WHERE QNA_ANSWERED_STATUS = 0 "
					+ "ORDER BY QNA_ID";

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				do {
					int qna_id = rs.getInt("QNA_ID");
					qna_id_list.add(qna_id);
					String qna_user_id = rs.getString	("USER_ID");
					String qna_subject_name = rs.getString("SUBJECT_NAME");
					String qna_content = rs.getString("QNA_CONTENT");
					Date qna_questioned_date = rs.getDate("QNA_QUESTIONED_DATE");
					System.out.printf("%d번 :\t%s\t%s\t\t%s\t\t%s\n",qna_id ,qna_user_id ,qna_subject_name,qna_content,qna_questioned_date);

				}while(rs.next());
			}else {
				System.out.println("미답변된 문의가 없습니다.");
				return;
			}

			int qna_id = Integer.MIN_VALUE;
			while(!qna_id_list.contains(qna_id)) {
				System.out.println("답변할 글의 번호를 입력해주세요");
				System.out.println("뒤로가기를 원하시면 0을 입력해주세요");
				while(true) {
					try {
						qna_id = Integer.parseInt(br.readLine());
						break;
					} catch (Exception e) {
						System.out.println("유효하지않은 입력값입니다.");
						continue;
					}
				}
			}

			if(qna_id == 0) return;
			adminAnswerToQNA(qna_id);



		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("유효하지않은 입력입니다.");
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);

		}

	}

	private void adminAnswerToQNA(int qna_id) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		String answer = null;

		System.out.println("답변할 내용을 입력해주세요.");
		try {
			answer = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			conn = DBUtil.getConnection();
			sql = "UPDATE QNA SET "
					+ "QNA_ANSWERED_STATUS = 1, "
					+ "QNA_ANSWER = ?, "
					+ "QNA_ANSWERED_DATE = SYSDATE, "
					+ "ADMIN_ID = ? "
					+ "WHERE QNA_ID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, answer);
			pstmt.setString(2, admin.getID());
			pstmt.setInt(3, qna_id);
			int update = pstmt.executeUpdate();

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			try {conn.rollback();} catch (SQLException e1) {}
		}finally {
			System.out.println("답변이 완료되었습니다.");
			try {conn.commit();} catch (SQLException e1) {}
			DBUtil.executeClose(null, pstmt, conn);
		}

	}

	public void answerUpdate() {

		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		int qna_id = 0;
		try {
			if((qna_id = selectAnsweredQNA()) == -1) {
				return;
			}

			System.out.println("수정할 답변 내용을 입력해주세요");
			String answer = br.readLine();
			conn = DBUtil.getConnection();
			sql = "UPDATE QNA SET QNA_ANSWER = ? WHERE QNA_ID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, answer);
			pstmt.setInt(2, qna_id);
			int update = pstmt.executeUpdate();
			if(update == 1) {
				System.out.println("수정이 완료되었습니다.");				
				try {conn.commit();} catch (SQLException e1) {}
			}
		} catch (Exception e) {
			try {conn.rollback();} catch (SQLException e1) {}
			e.printStackTrace();
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}

	}

	private int selectAnsweredQNA() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		ResultSet rs = null;
		List<Integer> qnaList = new ArrayList<Integer>();

		try {
			conn = DBUtil.getConnection();
			sql = "SELECT * FROM QNA Q INNER JOIN QNA_SUBJECT QS ON Q.SUBJECT_ID = QS.SUBJECT_ID  WHERE QNA_ANSWERED_STATUS = 1 AND ADMIN_ID = ?"; 
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, admin.getID());
			rs = pstmt.executeQuery();



			if(rs.next()) {
				do{ // 답변한 qna들 출력
					int qna_id = rs.getInt("QNA_ID");
					qnaList.add(qna_id);
					String user_id = rs.getString("USER_ID");
					String qna_subject = rs.getString("SUBJECT_NAME");
					String qna_content = rs.getString("QNA_CONTENT");
					Date qna_questioned_date = rs.getDate("QNA_QUESTIONED_DATE");

					String admin_id = rs.getString("ADMIN_ID");
					String admin_answer = rs.getString("QNA_ANSWER");
					Date qna_answered_date = rs.getDate("QNA_ANSWERED_DATE");
					System.out.printf("%d번 문의\n" , qna_id);
					System.out.printf("%s\t%s\t\t%s\t%s\n",user_id, qna_subject, qna_content, qna_questioned_date);
					System.out.printf("%s\t\t%s\t%s\n" , admin_id, admin_answer , qna_answered_date);
				}while(rs.next());
			}else {
				System.out.println("답변한 QNA가 존재하지않습니다.");
				return -1;
			}

			int choiceNum = Integer.MIN_VALUE;

			while(true) {
				try {
					System.out.println("답변을 수정할 문의의 문의번호를 골라주세요");
					choiceNum = Integer.parseInt(br.readLine());

					if(qnaList.contains(choiceNum)) {
						System.out.println(choiceNum + " 번 선택");
						return choiceNum;
					}else {
						System.out.println("유효하지않은 문의 번호입니다");
						continue;
					}

				}catch (Exception e) {
					System.out.println("숫자만 입력해주세요");
					continue;
				}
			}


		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//			DBUtil.executeClose(rs, pstmt, conn);
		}
		return -1;
	}

	public void showQnA() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.getConnection();
			sql = "SELECT * FROM QNA Q INNER JOIN QNA_SUBJECT QS ON Q.SUBJECT_ID = QS.SUBJECT_ID"; 
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				do{
					int qna_id = rs.getInt("QNA_ID");
					String user_id = rs.getString("USER_ID");
					String qna_subject = rs.getString("SUBJECT_NAME");
					String qna_content = rs.getString("QNA_CONTENT");
					Date qna_questioned_date = rs.getDate("QNA_QUESTIONED_DATE");

					String admin_id = rs.getString("ADMIN_ID");
					String admin_answer = rs.getString("QNA_ANSWER");
					Date qna_answered_date = rs.getDate("QNA_ANSWERED_DATE");
					int qna_status = rs.getInt("QNA_ANSWERED_STATUS");
					System.out.printf("%d번 문의\n" , qna_id);
					System.out.printf("%s\t%s\t\t%s\t%s\n",user_id, qna_subject, qna_content, qna_questioned_date);
					if(qna_status == 1) {
						System.out.printf("%s\t\t%s\t%s\n" , admin_id, admin_answer , qna_answered_date);
					}
				}while(rs.next());
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//			DBUtil.executeClose(rs, pstmt, conn);
		}

	}
	
	
}
