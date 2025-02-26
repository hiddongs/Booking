package com.booking.menu;

import java.io.BufferedReader;
import java.io.IOException;

import com.booking.DAO.AccommodationviewDAO;
import com.booking.DAO.PaymentDAO;
import com.booking.DAO.ReservationDAO;
import com.booking.DAO.UserDAO;
import com.booking.accommodation.Accommodation;
import com.booking.member.Payment;
import com.booking.member.Reservation;
import com.booking.member.User;

public class PaymentMenu {
	
	private Reservation reservation;
	private User user;
	
	//결제(결제, 사용자, 숙소
	public PaymentMenu(User user, Reservation reservation) {
		this.user=user;
		this.reservation= reservation;
	}
	
	static Payment payment;
	static PaymentDAO paymentDAO;
	static UserDAO userDAO;
	static ReservationDAO reserVationDAO;
		
	public void P_menu(BufferedReader br, Payment payment, User user, Reservation reservation) throws NumberFormatException, IOException {
		// 이게 맞나?
		PaymentMenu.payment = payment;
		PaymentMenu.paymentDAO = new PaymentDAO(user, reservation); // user객체를 전달하여 paymentDAO 생성
		
		
		while (true) {
			System.out.println("결제 메뉴입니다.");
			System.out.println("결제 수단을 선택하세요");
			System.out.println("1.현금 / 2.현금+포인트/ 3.현금+쿠폰 / 4.현금+포인트+쿠폰");
			
			int choice = Integer.parseInt(br.readLine());
			if(choice == 1) {
				String user_id = user.getID();
				
				
				
			}else if(choice == 2) {
				// 2.현금 + 포인트 결제
				String user_id = user.getID();
				System.out.println("결제를 진행하겠습니다.");
				System.out.println("사용할 현금 금액을 입력하세요:");
				//현금 금액 작성
				int usecash = Integer.parseInt(br.readLine());
				
				System.out.println("사용할 포인트 금액을 입력하세요:");
				// 포인트 금액 작성
				int usepoint = Integer.parseInt(br.readLine());
				
				// 결제 로직
				PaymentDAO paymentDAO = new PaymentDAO(user, reservation);
				paymentDAO.Check_cashandPoint(br, user_id);
				
	
			}else if(choice == 3) {
				// 3.현금+쿠폰
				
				
				
				
				
			}else if(choice == 4) {
				// 4. 현금+포인트+쿠폰
				
			}
		}
	}
}