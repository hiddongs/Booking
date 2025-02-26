package com.booking.menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.booking.DAO.AccommodationDAO;
import com.booking.DAO.AccommodationviewDAO;
import com.booking.DAO.PaymentDAO;
import com.booking.DAO.ReservationDAO;
import com.booking.member.User;

public class ReservationMenu {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static AccommodationDAO accommodationDAO = new AccommodationDAO();
    static AccommodationviewDAO accommodationViewDAO = new AccommodationviewDAO();
	static ReservationDAO reserVationDAO = new ReservationDAO();
	ReservationDAO reservationDAO;
	User user;
	PaymentDAO paymentDAO;
	PaymentMenu paymentMenu;
	
//	public ReservationMenu()
//	{
//		reservationDAO = new ReservationDAO(user);
//	}
	public void reservationMenu() {
		System.out.println("에약하기");
		System.out.println("1. 국내");
		System.out.println("2. 해외");
        System.out.println("3. 예약 목록 조회 및 삭제");
		int areaNum;
		try {
			areaNum = Integer.parseInt(br.readLine());
		
			
			
			if(areaNum == 1) {
				System.out.println("국내 예약");
				try {
					System.out.println("1. 국내 여행지");	
					System.out.println("2. 추천여행지 보기");
					int sugNum = Integer.parseInt(br.readLine());
					try {
						if(sugNum == 1) {
							System.out.println("국내 여행지 목록");
							 accommodationViewDAO.selectdomesticInfo();
							 // 숙소 번호 체크
					        System.out.println("예약하고 싶은 숙소 번호를 입력하세요:");
					        int num = Integer.parseInt(br.readLine());
					        
					    
					        
							if(reservationDAO.select_domestic(br,num)) {
								reservationDAO.domestic_reservation(num);
							}
					        
							 
						}
						else if(sugNum == 2) { // 추천 여행지 메뉴판
							System.out.println("추천 여행지 보기");
							accommodationDAO.suggest_accommodation(br, null);
						}

					}catch (Exception e) {
						
					}


				}catch (Exception e) {
					
				}
			}
			else if(areaNum == 2) {
				
				
				try {
					
					System.out.println("1. 해외 예약");
					System.out.println("2. 추천 여행지 보기");
					int sugNum = Integer.parseInt(br.readLine());
					if(sugNum == 1) {
						System.out.println("1. 해외 예약");
						System.out.println("해외 여행지 목록");
						accommodationViewDAO.selectoverseasInfo();
						
						 // 숙소 번호 체크
				        System.out.println("예약하고 싶은 숙소 번호를 입력하세요:");
				        int num = Integer.parseInt(br.readLine());
				        
						if(reservationDAO.select_overseas(br,"해외",num)) {
							reservationDAO.overeas_reservation(num);
						}

					}
					else if(sugNum == 2) { // 추천 여행지 메뉴판
						System.out.println("추천 여행지 보기");
						accommodationDAO.suggest_accommodation(br, "해외");
					}

				}catch (Exception e) {
					// TODO: handle exception
				}

				
			}
			else if(areaNum == 3) {
				System.out.println("1. 예약 목록 조회");
				System.out.println("2. 예약 삭제");
				int num = Integer.parseInt(br.readLine());
				if(num == 1) {
					reservationDAO.showReservation();
					//여기
					// 여기에 그 메뉴 가져와
					paymentDAO.select_CheckPayment();
					paymentMenu.P_menu(br, null, user, null);
					
					
					
				}else if(num == 2) {
					System.out.println("삭제할 번호를 입력하세요");
					int num2  = Integer.parseInt(br.readLine());
					reservationDAO.showReservation();
					reservationDAO.deleteReservation(num2);
				}
			}

		}catch(Exception e) {

		}


	}
	
}
