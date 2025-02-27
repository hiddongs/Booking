package com.booking.menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.booking.DAO.AccommodationDAO;
import com.booking.DAO.AccommodationviewDAO;
import com.booking.DAO.PaymentDAO;
import com.booking.DAO.ReservationDAO;
import com.booking.member.Payment;
import com.booking.member.Reservation;
import com.booking.member.User;

public class ReservationMenu {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static AccommodationDAO accommodationDAO = new AccommodationDAO();
    static AccommodationviewDAO accommodationViewDAO = new AccommodationviewDAO();
    ReservationDAO reserVationDAO = new ReservationDAO();
    //
    User user; // 객체선언
    Reservation reservation; // 객체 선언
    PaymentDAO paymentDAO; // 객체 선언
    Payment payment;
    
 
    public ReservationMenu(){}
	public ReservationMenu(User user, Reservation reservation, PaymentDAO paymentDAO, Payment payment) {
		super();
		this.user = user;
		this.reservation = reservation;
		this.paymentDAO = paymentDAO;
		this.payment = payment;
	}
    
    public void reservationMenu() {
        while (true) {
            System.out.println("예약하기");
            System.out.println("1. 국내");
            System.out.println("2. 해외");
            System.out.println("3. 예약 관리");

            try {
                int areaNum = Integer.parseInt(br.readLine());

                if (areaNum == 1) { // 국내 예약
                    System.out.println("국내 예약");
                    try {
                        System.out.println("1. 국내 여행지");
                        System.out.println("2. 추천 여행지 보기");
                        int sugNum = Integer.parseInt(br.readLine());

                        if (sugNum == 1) {
                            System.out.println("국내 여행지 목록");
                            accommodationViewDAO.selectdomesticInfo();

                            // 숙소 번호 체크
                            System.out.println("예약하고 싶은 숙소 번호를 입력하세요:");
                            int num = Integer.parseInt(br.readLine());

                            if (reserVationDAO.select_domestic(br, num)) {
                                reserVationDAO.domestic_reservation(num);
                            }
                        } else if (sugNum == 2) { // 추천 여행지
                            System.out.println("추천 여행지 보기");
                            accommodationDAO.suggest_accommodation(br, null);
                        } else {
                            System.err.println("1 또는 2만 입력하세요.");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (areaNum == 2) { // 해외 예약
                    while (true) {
                        try {
                            System.out.println("1. 해외 예약");
                            System.out.println("2. 추천 여행지 보기");
                            int sugNum = Integer.parseInt(br.readLine());

                            if (sugNum == 1) {
                                System.out.println("해외 여행지 목록");
                                accommodationViewDAO.selectoverseasInfo();

                                // 숙소 번호 체크
                                System.out.println("예약하고 싶은 숙소 번호를 입력하세요:");
                                int num = Integer.parseInt(br.readLine());

                                if (reserVationDAO.select_overseas(br, "해외", num)) {
                                    reserVationDAO.overeas_reservation(num);
                                    break;
                                }
                            } else if (sugNum == 2) { // 추천 여행지
                                System.out.println("추천 여행지 보기");
                                accommodationDAO.suggest_accommodation(br, "해외");
                                break;
                            } else {
                                System.err.println("1 또는 2만 입력하세요.");
                            }
                        } catch (NumberFormatException | IOException e) {
                            System.err.println("숫자만 입력하세요.");
                        }
                    }
                } else if (areaNum == 3) { // 예약 관리
                    while (true) {
                        try {
                            System.out.println("1. 예약 목록 조회 및 결제");
                            System.out.println("2. 예약 삭제");
                            int num = Integer.parseInt(br.readLine());

                            if (num == 1) {
//                                break; // 있으면 안되고 
                               
                                PaymentMenu paymentMenu = new PaymentMenu(user, reservation, paymentDAO);
                                paymentMenu.P_menu(br, paymentDAO, payment, user, reservation); // 결제메뉴호출
                            
                            } else if (num == 2) {
                                System.out.println("삭제할 번호를 입력하세요:");
                                int num2 = Integer.parseInt(br.readLine());
                                // 객체 (소문자)
                                reserVationDAO.showReservation();
                                reserVationDAO.deleteReservation(num2);
                                break;
                            } else {
                                System.err.println("1 또는 2만 입력하세요.");
                            }
                        } catch (NumberFormatException | IOException e) {
                            System.err.println("숫자만 입력하세요.");
                        } catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
                    }
                } else {
                    System.err.println("1 ~ 3 사이의 숫자만 입력하세요.");
                }
            } catch (NumberFormatException | IOException e) {
                System.err.println("숫자만 입력하세요.");
            }
        }
    }


}