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
	static AccommodationviewDAO accommodationViewDAO = new AccommodationviewDAO();
	

	private LocalDate s_date;
	private LocalDate e_date;
	private int price;
	private int accommodation_ID;

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
	
	public boolean select_overseas(BufferedReader br, String location_name,int num) {
	 
	    try {
	        AccommodationDAO accommodationDAO = new AccommodationDAO();
	        accommodationViewDAO.selectoverseasInfo();

	        // 날짜 체크
	        System.out.println("예약하고 싶은 시작 날짜를 입력하세요 (yyyy-MM-dd):");
	        LocalDate today = LocalDate.now();
	        String input_s_date = br.readLine().trim();
	        this.s_date = LocalDate.parse(input_s_date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	        
	       
	        System.out.println("예약하고 싶은 종료 날짜를 입력하세요 (yyyy-MM-dd):");
	        String input_e_date = br.readLine().trim();
	        this.e_date = LocalDate.parse(input_e_date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

	        boolean isDateValid = posDate(br, s_date, e_date, today); // 날짜 유효성 체크

	        // 인원 수 체크
	        System.out.println("예약하고 싶은 인원 수를 입력하세요:");
	        int mem = Integer.parseInt(br.readLine());
	        int allowedMem = accommodationDAO.getAllowedMem(mem);
	        boolean isMemValid = memCheck(num, mem, allowedMem); // 인원 수 체크

	        // 운영 여부 체크
	        boolean isOpen = openCheck(accommodationDAO.getAccommodationID(num));
	        
	        this.accommodation_ID = num;

	        // 예약 가능 여부 확인
	        if (isMemValid && isDateValid && isOpen) {
	            System.out.println("예약 가능");
	            return true;
	            
	         
	            
	        } else {
	            System.out.println("예약 불가");
	            return false;
	        }

	    

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		return false; 
	}

	// 국내 선택했을 시 예약 기능
	public boolean select_domestic(BufferedReader br,int num) {

		try {
	        AccommodationDAO accommodationDAO = new AccommodationDAO();
	        accommodationViewDAO.selectdomesticInfo();
 
	       

	        // 날짜 체크
	        System.out.println("예약하고 싶은 시작 날짜를 입력하세요 (yyyy-MM-dd):");
	        LocalDate today = LocalDate.now();
	        String input_s_date = br.readLine().trim();
	        this.s_date = LocalDate.parse(input_s_date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	        
	        System.out.println("예약하고 싶은 종료 날짜를 입력하세요 (yyyy-MM-dd):");
	        String input_e_date = br.readLine().trim();
	        this.e_date = LocalDate.parse(input_e_date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

	        boolean isDateValid = posDate(br, s_date, e_date, today); // 날짜 유효성 체크

	        // 인원 수 체크
	        System.out.println("예약하고 싶은 인원 수를 입력하세요:");
	        int mem = Integer.parseInt(br.readLine());
	        int allowedMem = accommodationDAO.getAllowedMem(mem);
	        boolean isMemValid = memCheck(num, mem, allowedMem); // 인원 수 체크

	        
	        this.accommodation_ID = num;
	        // 운영 여부 체크
	        boolean isOpen = openCheck(accommodationDAO.getAccommodationID(num));
	        
	        

	        // 예약 가능 여부 확인
	        if (isMemValid && isDateValid && isOpen) {
	            System.out.println("예약 가능");
	            return true;
	        } else {
	            System.out.println("예약 불가");
	            return false;
	        }


	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		return false; 
	}
	public void domestic_reservation(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement price_pstmt = null;
		String i_sql = null;
		String s_sql_prcie = "SELECT ACCOMMODATION_PRICE FROM ACCOMMODATION WHERE ACCOMMODATION_ID=?";
		ResultSet rs = null;
		try {
			String user_ID = UserDAO.getCurrentUserID();
			conn = DBUtil.getConnection();
		
					i_sql = "INSERT INTO RESERVATION ("
							+ "RESERVATION_ID, "  // ✅ PRIMARY KEY (시퀀스로 자동 증가)
							+ "USER_ID, "
							+ "ACCOMMODATION_ID, "
							+ "RESERVATION_START_DATE, "
							+ "RESERVATION_END_DATE, "
							+ "RESERVATION_PRICE) "
							+ "VALUES (RESERVATION_SEQ.NEXTVAL, ?, ?, ?, ?, ?)";

					pstmt = conn.prepareStatement(i_sql);
					price_pstmt= conn.prepareStatement(s_sql_prcie);
					price_pstmt.setInt(1,accommodation_ID);
					rs = price_pstmt.executeQuery();

					if(rs.next()) {
						price = rs.getInt("ACCOMMODATION_PRICE");
					}


					pstmt.setString(1, user_ID);
					pstmt.setInt(2, accommodation_ID);
					pstmt.setDate(3, java.sql.Date.valueOf(s_date));
					pstmt.setDate(4, java.sql.Date.valueOf(e_date));
					pstmt.setInt(5, price);
				
					int update = pstmt.executeUpdate();
					if(update == 1) {
						conn.commit();
						System.out.println("예약이 완료되었습니다!");
					}
					else {
						conn.rollback();
						System.out.println("예약 실패 ! ! !");
					}

				
				}catch(Exception e) {
					e.printStackTrace();
				}

			
	}
		
	public void overeas_reservation(int num) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement price_pstmt = null;
		String i_sql = null;
		String s_sql_prcie = "SELECT ACCOMMODATION_PRICE FROM ACCOMMODATION WHERE ACCOMMODATION_ID=?";
		ResultSet rs = null;
		try {
			String user_ID = UserDAO.getCurrentUserID();


			conn = DBUtil.getConnection();
			
				
					
					i_sql = "INSERT INTO RESERVATION ("
							+ "RESERVATION_ID, "  // PRIMARY KEY (시퀀스로 자동 증가)
							+ "USER_ID, "
							+ "ACCOMMODATION_ID, "
							+ "RESERVATION_START_DATE, "
							+ "RESERVATION_END_DATE, "
							+ "RESERVATION_PRICE) "
							+ "VALUES (RESERVATION_SEQ.NEXTVAL, ?, ?, ?, ?, ?)";

					pstmt = conn.prepareStatement(i_sql);
					price_pstmt= conn.prepareStatement(s_sql_prcie);
					price_pstmt.setInt(1,accommodation_ID);
					rs = price_pstmt.executeQuery();

					if(rs.next()) {
						price = rs.getInt("ACCOMMODATION_PRICE");
					}



					pstmt.setString(1, user_ID);
					pstmt.setInt(2, accommodation_ID);
					pstmt.setDate(3, java.sql.Date.valueOf(s_date));
					pstmt.setDate(4, java.sql.Date.valueOf(e_date));
					pstmt.setInt(5, price);
					
					int update = pstmt.executeUpdate();
					if(update == 1) {
						conn.commit();
						System.out.println("예약이 완료되었습니다!");
					}
					else {
						conn.rollback();
						System.out.println("예약 실패 ! ! !");
					}

				
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
		
		try {

			
			try {
				
				if (!e_date.isAfter(s_date)) {
					System.out.println("종료 날짜는 시작 날짜(" + s_date + ") 이후여야 합니다. 다시 입력하세요.");
					return false;
				}
				if(!s_date .isAfter(today)) {
					
					System.out.println(today + "이후 날짜만 예약 가능합니다.");
					return false;

				}
				return true;
			
				
			}catch(DateTimeParseException e) {
				System.out.println("날짜 형식이 올바르지 않습니다.yyyy-MM-dd");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
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
	
	
	
	
	
}
