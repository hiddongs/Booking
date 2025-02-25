package com.booking.menu;

import java.io.BufferedReader;

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
	
	public void P_menu(BufferedReader br, Payment payment, User user, Accommodation accommodation) {
		PaymentMenu.payment = payment;
		PaymentMenu.accommodationviewDAO = new AccommodationviewDAO();
		PaymentMenu.paymentDAO = new PaymentDAO();
		
		try {
			while (true) {
				System.out.println("결제 메뉴입니다.");
				System.out.println("1. 숙소예약 확인");
			}	
		} catch (Exception e) {
			// TODO: handle exception
		}
			
	}
	}
