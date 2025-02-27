package com.booking.menu;
// 결제 메뉴 입니다.
//  

import java.io.BufferedReader;
import java.io.IOException;
<<<<<<< HEAD
import java.sql.SQLException;
=======
import java.util.List;
>>>>>>> branch 'main' of https://github.com/hiddongs/Booking.git

import com.booking.DAO.AccommodationviewDAO;
import com.booking.DAO.CashDAO;
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
	CashDAO cashDAO = new CashDAO();


	//결제(결제, 사용자, 숙소
	public PaymentMenu(User user, Reservation reservation, PaymentDAO paymentDAO) {
		this.user=user;
		this.reservation= reservation;
		this.paymentDAO = paymentDAO;
	}

	static Payment payment;
	static PaymentDAO paymentDAO;
	static UserDAO userDAO;
<<<<<<< HEAD
	static ReservationDAO reserVationDAO;
	
	public void P_menu(BufferedReader br, PaymentDAO paymentDAO,Payment payment, User user, Reservation reservation, String ID) throws NumberFormatException, IOException, ClassNotFoundException {
		PaymentMenu.payment = payment;
		PaymentMenu.paymentDAO = new PaymentDAO(user, reservation); // user객체를 전달하여 paymentDAO 생성

		// 현재 로그인한 사용자 아이디 가져오기
		if (user != null && user.getID() != null) {
			ID = user.getID(); // user객체에서 가져옴
		}else {
			ID=UserDAO.getCurrentUserID(); // 아니면 userDAO에서 가져오기
		}

		System.out.println("현재 아이디:" +ID);

=======
	static Accommodation accommodation;
	static AccommodationviewDAO accommodationviewDAO;
	static ReservationDAO reservationDAO;
		
	public void P_menu(BufferedReader br, Payment payment, User user, Accommodation accommodation) {
		
>>>>>>> branch 'main' of https://github.com/hiddongs/Booking.git
		while (true) {
			System.out.println("결제 메뉴입니다.");
<<<<<<< HEAD
			System.out.println("1.예약 현황 조회");
			System.out.println("2.결제 내역 조회");


			int no = Integer.parseInt(br.readLine());
			if (no == 1) {  // 예약 현황 조회
				// 예약현황 보기
				// 예약 현황을 보여주고 예약 번호 입력 받기
				System.out.println("예약 번호를 입력해주세요:");
				int reservationId = Integer.parseInt(br.readLine());

				// 결제 진행
				System.out.println("결제 방법을 선택하세요: 1.현금 2.현금+포인트 3.현금+쿠폰");
				int paymentMethod = Integer.parseInt(br.readLine());

				// BufferedReader br, PaymentDAO paymentDAO,Payment payment, User user, Reservation reservation,String ID
				if (paymentMethod == 1) {
					// 현금 결제 처리
					int[] result = paymentDAO.processCashPayment(ID, reservationId);
					if (result != null) {
						int userCash = result[0];
						int accommodationPrice = result[1];

						System.out.printf("현재 보유한 캐시: %d원\n", userCash);
						System.out.printf("숙소 가격: %d원\n", accommodationPrice);

						//캐시 있는지 확인
						// 부족한 가격만 추가해주면 되는데
						// 숙소 가격보다 사용자 현금이 크면 바로 계산
						if(userCash >= accommodationPrice) {
							System.out.println("결제 진행합니다.");
							// 결제 진행 후 잔액 업데이트 진행
							// 현재 로그인한 사용자 : ID
							paymentDAO.updateCashPayment(ID, userCash-accommodationPrice);
							paymentDAO.select_PaymentHistory(); // 결제 내역 select
							System.out.println("결제가 완료되었습니다.");
						}else {
							//잔액부족
							int requiredCash = accommodationPrice-userCash;
							System.out.printf("잔액이 부족하여 결제할 수 없습니다. 추가로 %d원이 필요합니다.\n", requiredCash);
							System.out.println("부족한 금액 충전하시겠습니까? (1.예 / 2.아니오");
							int num1 = Integer.parseInt(br.readLine());
							if(num1 == 1) {
								cashDAO.chargeCash(ID, requiredCash, br);
							}else {
								System.out.println("결제가 취소되었습니다.");
							}
						}
						cashDAO.chargeCash(ID, userCash, br);
					}
				}else if(paymentMethod == 2) {
					// 2.현금 + 포인트 결제


				}else if(paymentMethod == 3) {
					// 3.현금+쿠폰
          // 결제 내역 보기
	     }else if (no == 2) {
	System.out.println("결제내역 조회");
	//결제 로직 조회
	paymentDAO.select_PaymentHistory();
}
				} 

=======
			System.out.println("1.예약현황 보기");
			int no;
			try {
				no = Integer.parseInt(br.readLine());
				if(no == 1) {
					
					reservationDAO.showReservation();
					
					int payNum = Integer.parseInt(br.readLine());
					List<Integer> list = reservationDAO.reservationIDlist();
					if(list.contains(payNum)) {
						// 결제 진행
						
					}
					
					paymentDAO.select_procesPayment();
				}
				else if (no == 2) {
					paymentDAO.select_CheckPayment();
				}
			} catch (NumberFormatException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
>>>>>>> branch 'main' of https://github.com/hiddongs/Booking.git
			}
			
		}
	}



