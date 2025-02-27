package com.booking.menu;
 // 결제 메뉴 입니다.
 //  

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import com.booking.DAO.AccommodationviewDAO;
import com.booking.DAO.PaymentDAO;
import com.booking.DAO.ReservationDAO;
import com.booking.DAO.UserDAO;
import com.booking.accommodation.Accommodation;
import com.booking.member.Payment;
import com.booking.member.User;

public class PaymentMenu {
	//결제(결제, 사용자, 숙소
	
	static Payment payment;
	static PaymentDAO paymentDAO;
	static User user;
	static UserDAO userDAO;
	static Accommodation accommodation;
	static AccommodationviewDAO accommodationviewDAO;
	static ReservationDAO reservationDAO;
		
	public void P_menu(BufferedReader br, Payment payment, User user, Accommodation accommodation) {
		
		while (true) {
			System.out.println("결제 메뉴입니다.");
			System.out.println("1.예약현황 보기");
			int no;
			try {
				no = Integer.parseInt(br.readLine());
				if(no == 1) {
					
					reservationDAO.showReservation();
					
					System.out.println("예약할 번호를 입력하세요");
					int payNum = Integer.parseInt(br.readLine());
					List<Integer> list = reservationDAO.reservationIDlist();
					if(list.contains(payNum)) {
						// 결제 진행
						
						
						// 원화 결ㅈ[
					}
					
					paymentDAO.select_procesPayment();
					break;
				}
				else if (no == 2) {
					paymentDAO.select_CheckPayment();
					break;
				}
			} catch (NumberFormatException | IOException e) {
				// TODO Auto-generated catch block
				System.out.println("제대로 된 값을 입력하세요.");
				continue;
			}
			
		}
	}
	
	
}

