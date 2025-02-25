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
	static AccommodationviewDAO accommodationViewDAO;
	


	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	/* 더 좋은 코드
	public void selectAccommodation(BufferedReader br, String location_name, boolean isOverseas) {
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;

	    try {
	        if (isOverseas) {
	            accommodationViewDAO.selectoverseasInfo();
	        } else {
	            accommodationViewDAO.selectdomesticInfo();
	        }

	        // 숙소 번호 입력
	        System.out.println("예약하고 싶은 숙소 번호를 입력하세요:");
	        int num = Integer.parseInt(br.readLine());

	        // 날짜 입력
	        System.out.println("예약하고 싶은 시작 날짜 (yyyy-MM-dd):");
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	        LocalDate today = LocalDate.now();
	        LocalDate s_date = LocalDate.parse(br.readLine(), formatter);

	        System.out.println("예약하고 싶은 종료 날짜 (yyyy-MM-dd):");
	        LocalDate e_date = LocalDate.parse(br.readLine(), formatter);

	        boolean isDateValid = posDate(s_date, e_date, today);

	        // 인원 체크
	        System.out.println("예약하고 싶은 인원 수를 입력하세요:");
	        int mem = Integer.parseInt(br.readLine());
	        int allowedMem = accommodationDAO.getAllowedMem(num);
	        boolean isMemValid = memCheck(num, mem, allowedMem);

	        // 운영 여부 체크
	        boolean isOpen = openCheck(accommodationDAO.getAccommodationID(num));

	        // 최종 예약 가능 여부 확인
	        if (isMemValid && isDateValid && isOpen) {
	            System.out.println("✅ 예약 가능");
	        } else {
	            System.out.println("❌ 예약 불가");
	        }

	        // 숙소 정보 가져오기
	        conn = DBUtil.getConnection();
	        String sql = "SELECT * FROM ACCOMMODATION WHERE location_name = ? AND is_open = 1";
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, location_name);
	        rs = pstmt.executeQuery();

	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        DBUtil.executeClose(rs, pstmt, conn);
	    }
	}
*/
	
	// 해외 선택
	
	public void select_overseas(BufferedReader br, String location_name) {
	    Connection conn = null;
	    PreparedStatement i_pstmt = null;
	    ResultSet rs = null;
	    
	    try {
	        AccommodationDAO accommodationDAO = new AccommodationDAO();
	        accommodationViewDAO.selectoverseasInfo();

	        // 숙소 번호 체크
	        System.out.println("예약하고 싶은 숙소 번호를 입력하세요:");
	        int num = Integer.parseInt(br.readLine());

	        // 날짜 체크
	        System.out.println("예약하고 싶은 시작 날짜를 입력하세요 (yyyy-MM-dd):");
	        LocalDate today = LocalDate.now();
	        LocalDate s_date = LocalDate.parse(br.readLine(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	        
	        System.out.println("예약하고 싶은 종료 날짜를 입력하세요 (yyyy-MM-dd):");
	        LocalDate e_date = LocalDate.parse(br.readLine(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));

	        boolean isDateValid = posDate(br, s_date, e_date, today); // 날짜 유효성 체크

	        // 인원 수 체크
	        System.out.println("예약하고 싶은 인원 수를 입력하세요:");
	        int mem = Integer.parseInt(br.readLine());
	        int allowedMem = accommodationDAO.getAllowedMem(mem);
	        boolean isMemValid = memCheck(num, mem, allowedMem); // 인원 수 체크

	        // 운영 여부 체크
	        boolean isOpen = openCheck(accommodationDAO.getAccommodationID(num));

	        // 예약 가능 여부 확인
	        if (isMemValid && isDateValid && isOpen) {
	            System.out.println("예약 가능");
	        } else {
	            System.out.println("예약 불가");
	        }

	        // 데이터베이스 연결 및 정보 조회
	        conn = DBUtil.getConnection();
	        String i_sql = "SELECT * FROM ACCOMMODATION WHERE location_name = ? AND is_open = 1";
	        i_pstmt = conn.prepareStatement(i_sql);
	        i_pstmt.setString(1, location_name);
	        rs = i_pstmt.executeQuery();

	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        // 리소스 닫기
	        try {
	            if (rs != null) rs.close();
	            if (i_pstmt != null) i_pstmt.close();
	            if (conn != null) conn.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}

	// 국내 선택했을 시 예약 기능
	public void select_domestic(BufferedReader br,String location_name) {
		Connection conn = null;
		String s_sql = null;

		PreparedStatement s_pstmt = null;

		ResultSet rs = null;
		try {
	        AccommodationDAO accommodationDAO = new AccommodationDAO();
	        accommodationViewDAO.selectdomesticInfo();

	        // 숙소 번호 체크
	        System.out.println("예약하고 싶은 숙소 번호를 입력하세요:");
	        int num = Integer.parseInt(br.readLine());

	        // 날짜 체크
	        System.out.println("예약하고 싶은 시작 날짜를 입력하세요 (yyyy-MM-dd):");
	        LocalDate today = LocalDate.now();
	        LocalDate s_date = LocalDate.parse(br.readLine(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	        
	        System.out.println("예약하고 싶은 종료 날짜를 입력하세요 (yyyy-MM-dd):");
	        LocalDate e_date = LocalDate.parse(br.readLine(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));

	        boolean isDateValid = posDate(br, s_date, e_date, today); // 날짜 유효성 체크

	        // 인원 수 체크
	        System.out.println("예약하고 싶은 인원 수를 입력하세요:");
	        int mem = Integer.parseInt(br.readLine());
	        int allowedMem = accommodationDAO.getAllowedMem(mem);
	        boolean isMemValid = memCheck(num, mem, allowedMem); // 인원 수 체크

	        // 운영 여부 체크
	        boolean isOpen = openCheck(accommodationDAO.getAccommodationID(num));

	        // 예약 가능 여부 확인
	        if (isMemValid && isDateValid && isOpen) {
	            System.out.println("예약 가능");
	        } else {
	            System.out.println("예약 불가");
	        }

	        // 데이터베이스 연결 및 정보 조회
	        conn = DBUtil.getConnection();
	        s_sql = "SELECT * FROM ACCOMMODATION WHERE location_name = ? AND is_open = 1";
	        s_pstmt = conn.prepareStatement(s_sql);
	        s_pstmt.setString(1, location_name);
	        rs = s_pstmt.executeQuery();

	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        // 리소스 닫기
	        try {
	            if (rs != null) rs.close();
	            if (s_pstmt != null) s_pstmt.close();
	            if (conn != null) conn.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
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
				s_date = LocalDate.parse(start, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				LocalDate e_date = null; // 예약 종료 날짜 초기값
				String end = br.readLine();
				e_date = LocalDate.parse(end, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				
				posDate(br,s_date,e_date,today);
				

			
		
		}catch(Exception e) {
			e.printStackTrace();
		}
	

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
				
				String end = br.readLine();
				s_date = LocalDate.parse(end, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				if (!e_date.isAfter(s_date)) {
					System.out.println("종료 날짜는 시작 날짜(" + s_date + ") 이후여야 합니다. 다시 입력하세요.");
				} else {
					break;
				}
			}catch(DateTimeParseException | IOException e) {
				System.out.println("날짜 형식이 올바르지 않습니다.yyyy-MM-dd");
			}
		}
		while(true) {
			try {
				
				if(!s_date .isAfter(today)) {
					System.out.println(today + "이후 날짜만 예약 가능합니다.");
				}
				else {
					break;
				}
			}catch(DateTimeParseException e) {
				System.out.println("날짜 형식이 올바르지 않습니다.yyyy-MM-dd");
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
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return false;
		
		
	}
	
	// 장바구니에 추가
	
    public void addCart() {
    	
    }

	
	
}
