package com.booking.DAO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.List;

import javax.swing.plaf.synth.SynthOptionPaneUI;

import com.booking.accommodation.Accommodation;
import com.dbutil.DBUtil;



public class ReservationDAO {
	
	static Accommodation accomo;
	static AccommodationDAO accommodationDAO;

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	// 해외 선택
	public void select_overseas(BufferedReader br,String location_name, List<Integer> list) throws ClassNotFoundException, SQLException {
		Connection conn = null;
		String s_sql = null;
		String i_sql = null;
		PreparedStatement s_pstmt = null;
		PreparedStatement i_pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.getConnection();

			// 예약 가능한 숙소 찾기
			s_sql = "SELECT * FROM ACCOMMODATION WHERE LOCATION_NAME=? AND ACCOMMODATION_STATUS=?";
			i_sql = "INSERT INTO CART (CART_ID, USER_ID, ACCOMMODATION_ID,CART_ADD_DATE)"
					+ "SELECT CART_SEQ.NEXTVAL,USER_ID,ACCOMODATION_ID,SYSDATE"
					+ "FROM ACCOMMODATION a"
					+ "JOIN \"USER\" u ON u.USER_ID = ? "
					+ "WHERE a.ACCOMMODATION_ID=?";

			// sql = "INSERT INTO REVIEW (REVIEW_ID, ACCOMMODATION_ID, REVIEW_DATE, REVIEW_CONTENT, REVIEW_RATING) VALUES(?,?,?,SYSDATE,?,?)";
			s_pstmt = conn.prepareStatement(s_sql);

			s_pstmt.setString(1,location_name);
			s_pstmt.setInt(2,1);
			rs = s_pstmt.executeQuery();


			while(rs.next()) {
				System.out.println("---------------------------------------------------------------");
				System.out.println("숙소번호 : " + rs.getInt("ACCOMMODATION_ID"));
				System.out.println("숙소 이름 : " + rs.getString("ACCOMMODATION_NAME"));
				System.out.println("숙소 주소 : " + rs.getString("ACCOMMODATION_ADDRESS"));
				System.out.println("숙소 가격 : " + rs.getInt("ACCOMMODATION_PRICE"));
				System.out.println("추천 계절 : " + rs.getString("RECOMMENDATION_SEASON"));
				System.out.println("예약 가능 인원 : " + rs.getInt("ALLOWED_NUMBER"));
				System.out.println("---------------------------------------------------------------");
			}
		}catch(Exception e) {
	    }
	}
		
		
		public void reservation() {
			try {
				AccommodationDAO accommodationDAO = new AccommodationDAO();
				
				System.out.println("예약하고 싶은 숙소 번호를 입력하세요");
				int num = Integer.parseInt(br.readLine());
				System.out.println("예약하고 싶은 인원 수를 입력하세요");
				int mem = Integer.parseInt(br.readLine());
				int allowedMem = accommodationDAO.getAllowedMem(mem); // 허용 인원 가져오기
				memCheck(num,mem,allowedMem);
				System.out.println("예약하고 싶은 날짜를 입력하세요");
				LocalDate today = LocalDate.now();
				LocalDate s_date = null; // 예약 시작 날짜 초기값
				String start = br.readLine();
				s_date = LocalDate.parse(start, DateTimeFormatter.ofPattern("yyyy-mm-dd"));
				LocalDate e_date = null; // 예약 종료 날짜 초기값
				String end = br.readLine();
				e_date = LocalDate.parse(end, DateTimeFormatter.ofPattern("yyyy-mm-dd"));
				
				posDate(br,s_date,e_date,today);
				

			
		
		}catch(Exception e) {
			e.printStackTrace();
		}
	

	}

	
	
	// 국내 선택했을 시 예약 기능
	public void select_domestic() {
		
	}
	// 지역 선택 

	public void choiceArea() {
	}


	public boolean memCheck(int num,int allow_mem, int mem) {
		Connection conn = null;
		String s_sql = null;
	
		PreparedStatement s_pstmt = null;
		
		ResultSet rs = null;

		String sql = "SELECT ALLOWED_NUMBER FROM ACCOMMODATION WHERE ACCOMMODATION_ID = ?";

		try {
			conn = DBUtil.getConnection();
			

			s_pstmt = conn.prepareStatement(sql);
			s_pstmt.setInt(1, num);
			rs = s_pstmt.executeQuery();

			if (rs.next()) {
				int allowedNumber = rs.getInt("ALLOWED_NUMBER");
				System.out.println("해당 숙소의 허용 인원: " + allowedNumber + "명");
				if(mem > allow_mem) {
					System.out.println("인원 초과로 예약이 불과합니다");
					return false;
				}
				else {
					System.out.println("예약 가능");
					return true;
					
					
				}
			} else {
				System.out.println("해당 숙소가 존재하지 않습니다.");
				return false;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			DBUtil.executeClose(rs, s_pstmt, conn);
		}
		return false;

	}
	// 날짜 가능 여부 
	public boolean posDate(BufferedReader br,LocalDate s_date, LocalDate e_date,LocalDate today) {
		
		while(true) {
			try {
				
				if(!s_date .isAfter(today)) {
					System.out.println(today + "이후 날짜만 예약 가능합니다.");
				}
				else {
					break;
				}
			}catch(DateTimeParseException e) {
				System.out.println("날짜 형식이 올바르지 않습니다.yyyy-mm-dd");
			}
		}

		while(true) {
			try {
				String end = br.readLine();
				s_date = LocalDate.parse(end, DateTimeFormatter.ofPattern("yyyy-mm-dd"));
				if (!e_date.isAfter(s_date)) {
					System.out.println("종료 날짜는 시작 날짜(" + s_date + ") 이후여야 합니다. 다시 입력하세요.");
				} else {
					break;
				}
			}catch(DateTimeParseException | IOException e) {
				System.out.println("날짜 형식이 올바르지 않습니다.yyyy-mm-dd");
			}
		}
		return false;

}
	
	// 운영 중 인지 여부
	public boolean openCheck(int accommodation_ID) {
		// 만약 숙소 인원이 다 차면 숙소 상태 0으로 만들기
		Connection conn = null;
		String sql = null;
		PreparedStatement pstmt = null;
		PreparedStatement i_pstmt = null;
		ResultSet rs = null;
		sql = "SELECT ACCOMMODATION_STATUS FROM ACCOMMODATION WHERE ACCOMMODATION_ID=?";
		
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, accommodation_ID);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getInt(1) == 1) {

					System.out.println("운영 가능");
					return true;
				}
				else {
					System.out.println("운영 불가");
					return false;
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			DBUtil.executeClose(rs, i_pstmt, conn);
		}
		return false;
		
		
	}
	
	

	
	
}
