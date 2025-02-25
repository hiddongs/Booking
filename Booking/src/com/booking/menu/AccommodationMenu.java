package com.booking.menu;

import java.io.BufferedReader;
import java.io.IOException;

import com.booking.DAO.AccommodationDAO;
import com.booking.DAO.AccommodationviewDAO;
import com.booking.DAO.CashDAO;
import com.booking.DAO.CouponDAO;
import com.booking.DAO.ReservationDAO;
import com.booking.DAO.ReviewDAO;
import com.booking.DAO.UserDAO;
import com.booking.accommodation.Accommodation;
import com.booking.member.Reservation;
import com.booking.member.Review;
import com.booking.member.User;

public class AccommodationMenu { 
	private BufferedReader br;
	static AccommodationviewDAO accommodationviewDAO;  // 숙소 DAO
	static Accommodation accommodation;   // 숙소클래스(생성자, getter, setter)
	// 리뷰 클래스
	static Review review; 
	// 리뷰 DAO
	static ReviewDAO reviewDAO; 

	static User user;
	static UserDAO userDAO;
	static CashDAO cashDAO;
	static CouponDAO couponDAO;
	
	static ReservationDAO reservationDAO;
	static Reservation reservation;

	// 숙소메뉴 호출 함수
	// 희동 -> 유저 아이디 호출 매개변수 추가
	public void AccMenu(BufferedReader br, Accommodation accommodation, AccommodationviewDAO accommodationviewDAO) throws IOException {
		//숙소 메뉴
		AccommodationMenu.accommodation = accommodation;
		AccommodationMenu.accommodationviewDAO = accommodationviewDAO;


		UserMenu.user = user;

		

		try {

			while (true) {
				System.out.println("우와! 환영합니다! 😊 우와놀자에서 최고의 여행을 경험하세요!");
				System.out.println("원하는 항목을 선택하세요.");

				System.out.println("1.전체 숙소 보기");
				System.out.println("2.숙소 상세보기");
				System.out.println("3.예약하기");
				System.out.println("4.마이페이지");



				int no = Integer.parseInt(br.readLine());
				if(no==1) {
					System.out.println("1.국내 2.해외");

					int num1 = Integer.parseInt(br.readLine());


					accommodationviewDAO.selectdomesticInfo();

					if(num1 == 1) {
					    accommodationviewDAO.selectdomesticInfo();
					}else if(num1 == 2) {
						accommodationviewDAO.selectoverseasInfo();
					}


					// 생성하고 다음 메뉴 부르기(reviewMenu)
					ReviewMenu reviewMenu = new ReviewMenu();
					reviewMenu.R_menu(br,review, reviewDAO, accommodation,accommodationviewDAO );


				}else if(no==2){
					//상세 글 한번 보기
					// 목록에서 선택할 글 번호 확인
					// (숙소정보 한번 더 보여주기)
					

					System.out.print("선택한 숙소 번호 >");
					int num = Integer.parseInt(br.readLine());
					System.out.println("============================");
					accommodationviewDAO.selectDetailInfo(num);




					// 목록을 보여주고
					int menunum = Integer.parseInt(br.readLine());
					try {
						if(menunum == 1) {
							accommodationviewDAO.selectdomesticInfo();
							System.out.println("> 숙소 정보 확인하시겠습니까? 1. 예 / 2. 아니오 [1 or 2 중에서 선택해주세요]");
							int detailnum = Integer.parseInt(br.readLine());
							// 숙소정보 확인하시겠습니까?
							if (detailnum == 1) {
								System.out.print("숙소번호 선택 >");
								int acconum = Integer.parseInt(br.readLine());
								System.out.println("============================");
								accommodationviewDAO.selectDetailInfo(acconum);
								
								// 생성하고 다음 메뉴 부르기(reviewMenu)
								ReviewMenu reviewMenu = new ReviewMenu();
								reviewMenu.R_menu(br,review, reviewDAO, accommodation,accommodationviewDAO );
							
							// 숙소 상세정보 안봄	-> 전체 메뉴로 돌아가
							}else if(menunum == 2) {
								AccommodationMenu accommodationMenu = new AccommodationMenu();
								accommodationMenu.AccMenu(br,accommodation, accommodationviewDAO);
							}
							// 해외숙소보기
						}else if(menunum == 2 ){
							accommodationviewDAO.selectoverseasInfo();
							System.out.println("> 숙소 정보 확인하시겠습니까? 1. 예 / 2. 아니오 [1 or 2 중에서 선택해주세요]");
							// 숙소정보 번호 작성
							int detailnum2 = Integer.parseInt(br.readLine());
							if (detailnum2 == 1) {
								System.out.print("숙소번호 선택 >");
								int acconum2 = Integer.parseInt(br.readLine());
								System.out.println("============================");
								accommodationviewDAO.selectDetailInfo(acconum2);
								// 생성하고 리뷰 메뉴 부르기(reviewMenu)
								ReviewMenu reviewMenu = new ReviewMenu();
								reviewMenu.R_menu(br,review, reviewDAO, accommodation,accommodationviewDAO );
								// 숙소 상세정보 안봄	-> 전체 메뉴로 돌아가
							}else if(detailnum2 == 2) {
								AccommodationMenu accommodationMenu = new AccommodationMenu();
								accommodationMenu.AccMenu(br,accommodation, accommodationviewDAO);
							}
							
						}else {
							System.out.println("번호 잘못 입력했습니다.");
						}
					} catch (NumberFormatException | IOException e) {
						e.printStackTrace();
					
					}
				

				}
				// 희동쿤이 해야할 예약 하기
				else if(no == 3) {
					
					ReservationMenu reservationMenu = new ReservationMenu();
					reservationMenu.reservationMenu();
					
				}
				else if(no == 4 ) {
					UserMenu userMenu = new UserMenu();
					CouponDAO couponDAO = new CouponDAO();
					String ID = UserDAO.getCurrentUserID();
					userMenu.u_Menu(br, user, review, userDAO, cashDAO, reviewDAO, couponDAO,ID);
				}

			} 
		}catch(NumberFormatException e){
			System.out.println("[숫자만 입력 가능]");
		}finally {

		}
		
	
		


	} // class

}
