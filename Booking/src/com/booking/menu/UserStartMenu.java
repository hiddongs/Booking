package com.booking.menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.booking.DAO.AccommodationviewDAO;
import com.booking.DAO.AdminDAO;
import com.booking.DAO.CashDAO;
import com.booking.DAO.ReviewDAO;
import com.booking.DAO.UserDAO;
import com.booking.accommodation.Accommodation;
import com.booking.member.Review;
import com.booking.member.User;

public class UserStartMenu {
	BufferedReader br = null;
	UserDAO userDAO;
	UserMenu userMenu;
	AccommodationMenu accommodationMenu;
	Accommodation accommodation;
	AccommodationviewDAO accommodationviewDAO;
	User user;
	Review review;
	CashDAO cashDAO;
	ReviewDAO reviewDAO;
	
	
	public void showStart() {
		br = new BufferedReader(new InputStreamReader(System.in));
		userDAO = new UserDAO();
		AccommodationMenu accommodationMenu = new AccommodationMenu();
		UserMenu userMenu = new UserMenu();

		accommodationviewDAO = new AccommodationviewDAO();
		cashDAO = new CashDAO();

		reviewDAO = new ReviewDAO();
		
		System.out.println("우와! 환영합니다! 😊 우와놀자에서 최고의 여행을 경험하세요!");
		System.out.println("원하시는 항목을 선택하세요 ! ! !");
		
		System.out.println("1. 숙소 예약");	
		System.out.println("2. 마이페이지");
		System.out.println("3. 문의하기");
		
		
		
		System.out.println("0. 로그아웃");
		int num;
		try {
			num = Integer.parseInt(br.readLine());
			if(num == 1) {
				System.out.println("숙소 예약");
				System.out.println("숙소 메뉴 입니다.");
				accommodationMenu.AccMenu(br,accommodation, accommodationviewDAO);
			}else if(num == 2) {
				System.out.println("마이페이지");
				
				userMenu.U_Menu(br,user,review, userDAO, cashDAO, reviewDAO);
			}
			
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
