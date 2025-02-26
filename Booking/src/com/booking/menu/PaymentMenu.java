package com.booking.menu;
 // 결제 메뉴 입니다.
 //  

import java.io.BufferedReader;
import java.io.IOException;

import com.booking.DAO.AccommodationviewDAO;
import com.booking.DAO.PaymentDAO;
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
	
		
	public void P_menu(BufferedReader br, Payment payment, User user, Accommodation accommodation) throws NumberFormatException, IOException {
		
		PaymentMenu.payment = payment;
		PaymentMenu.accommodationviewDAO = new AccommodationviewDAO();
		PaymentMenu.paymentDAO = new PaymentDAO(user); // user객체를 전달하여 paymentDAO 생성
	
		
		
		while (true) {
			System.out.println("결제 메뉴입니다.");
			System.out.println("1.예약현황 보기");
			int no = Integer.parseInt(br.readLine());
			if(no == 1) {
				String user_id = user.getID();
				paymentDAO.select_procesPayment();
			}
		}
	}
}

