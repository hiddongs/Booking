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
import com.dbutil.DBUtil;

public class AccommodationMenu { 
	private BufferedReader br;
	static AccommodationviewDAO accommodationviewDAO;  // 숙소 DAO
	static Accommodation accommodation;   // 숙소클래스(생성자, getter, setter)
	// 리뷰 클래스
	static Review review; 
	// 리뷰 DAO
	static ReviewDAO reviewDAO; 

	User user;
	private Enum grade;
	static UserDAO userDAO;
	static CashDAO cashDAO;
	static CouponDAO couponDAO;

	static ReservationDAO reservationDAO;
	static Reservation reservation;
	ReservationMenu reservationMenu = new ReservationMenu();

	// 숙소메뉴 호출 함수
	// 희동 -> 유저 아이디 호출 매개변수 추가

	public AccommodationMenu(User user, BufferedReader br, Enum<?> grade) {

		this.br = br;
		this.user= user;
		this.grade = grade;
	}

	public  AccommodationMenu(BufferedReader br, ReservationMenu reservationMenu) {
		this.br = br;
		this.reservationMenu =reservationMenu;
	}
	public void AccMenu(BufferedReader br, Accommodation accommodation, AccommodationviewDAO accommodationviewDAO) throws IOException {
		//숙소 메뉴
		AccommodationMenu.accommodation = accommodation;
		AccommodationMenu.accommodationviewDAO = accommodationviewDAO;





		

			while (true) {
				System.out.println();
				System.out.println();

				System.out.println("==================================================================================================================================================");
				System.out.println("                                                         🌍 와우놀자 - 숙소 예약 프로그램 🏡");
				System.out.println("==================================================================================================================================================");
				System.out.println("                                                                  🌟 W O W 🌟");
				System.out.println("==================================================================================================================================================");
				System.out.println("                 __      __     _____       __      __                __  __      _____       __         _____     ______     \r\n"
						+ "                /\\ \\  __/\\ \\   /\\  __`\\    /\\ \\  __/\\ \\              /\\ \\/\\ \\    /\\  __`\\    /\\ \\       /\\___ \\   /\\  _  \\    \r\n"
						+ "                \\ \\ \\/\\ \\ \\ \\  \\ \\ \\/\\ \\   \\ \\ \\/\\ \\ \\ \\             \\ \\ `\\\\ \\   \\ \\ \\/\\ \\   \\ \\ \\      \\/__/\\ \\  \\ \\ \\L\\ \\   \r\n"
						+ "                 \\ \\ \\ \\ \\ \\ \\  \\ \\ \\ \\ \\   \\ \\ \\ \\ \\ \\ \\             \\ \\ , ` \\   \\ \\ \\ \\ \\   \\ \\ \\  __    _\\ \\ \\  \\ \\  __ \\  \r\n"
						+ "                  \\ \\ \\_/ \\_\\ \\  \\ \\ \\_\\ \\   \\ \\ \\_/ \\_\\ \\             \\ \\ \\`\\ \\   \\ \\ \\_\\ \\   \\ \\ \\L\\ \\  /\\ \\_\\ \\  \\ \\ \\/\\ \\ \r\n"
						+ "                   \\ `\\___x___/   \\ \\_____\\   \\ `\\___x___/              \\ \\_\\ \\_\\   \\ \\_____\\   \\ \\____/  \\ \\____/   \\ \\_\\ \\_\\\r\n"
						+ "                   '\\/__//__/     \\/_____/    '\\/__//__/                \\/_/\\/_/    \\/_____/    \\/___/    \\/___/     \\/_/\\/_/");
			

				System.out.println("==================================================================================================================================================");


				System.out.println("                                                         🌟 최고의 여행을 위한 최고의 숙소! 🌟");
				System.out.println("==================================================================================================================================================");

				
				try {
				System.out.println("                                                  와우 ! 환영합니다! 😊 우와놀자에서 최고의 여행을 경험하세요!");

				System.out.println("==================================================================================================================================================");
				System.out.println();
				System.out.println();
				System.out.println("원하는 항목을 선택하세요.");
				System.out.println("1.숙소 전체 보기");
				System.out.println("2.예약하기");
				System.out.println("3.마이페이지");

				int no = Integer.parseInt(br.readLine());
				if(no==1) {
					System.out.println("1.국내 / 2.해외 선택하세요");
					int num = Integer.parseInt(br.readLine());
					try {
						// 국내 숙소만 보여줘
						if (num == 1) {
							accommodationviewDAO.selectdomesticInfo();
							while(true) {
								System.out.println("숙소 상세정보 보시겠습니까? ( y / n ) ");
								try {
									char answer = br.readLine().charAt(0);


									if(answer == 'y') {
										while(true) {
											try {
												System.out.print("선택한 숙소 번호 >");
												int num1 = Integer.parseInt(br.readLine());
												System.out.println("============================");
												accommodationviewDAO.selectDetailInfo(num1);
												// 생성하고 다음 메뉴 부르기(reviewMenu)
												ReviewMenu reviewMenu = new ReviewMenu(br, user, grade);
												reviewMenu.R_menu(br,review, reviewDAO, accommodation,accommodationviewDAO );
												break;
											}catch(Exception e) {
												System.err.println("번호 형식으로 입력하세요 ! ! !");
												continue;
											}
										}
									}else if(answer == 'n') {

										AccommodationMenu accommodationMenu = new AccommodationMenu(user, br,grade);
										accommodationMenu.AccMenu(br,accommodation, accommodationviewDAO);
										break;
									}

								}catch(Exception e) {
									System.err.println("y/n을 입력하세요");
									continue;
								}
							}
							// 해외숙소만 보여주기
						}else if(num == 2) {

							try {
							accommodationviewDAO.selectOverseasInfo();
							System.out.println("숙소 상세정보 보시겠습니까? ( y / n ) ");
							char answer2 = br.readLine().charAt(0);


							if(answer2 == 'y') {
								while(true) {

									try {
										System.out.print("선택한 숙소 번호 >");
										int num2 = Integer.parseInt(br.readLine());
										System.out.println("============================");
										accommodationviewDAO.selectDetailInfo(num2);
										ReviewMenu reviewMenu = new ReviewMenu(br, user, grade);
										reviewMenu.R_menu(br,review, reviewDAO, accommodation,accommodationviewDAO );
										break;
									}catch(NumberFormatException e) {
										System.out.println("숫자만 입력하세요");
										continue;
									}
								}
							}else if(answer2 == 'n') {
								AccommodationMenu accommodationMenu = new AccommodationMenu(user, br,grade);
								accommodationMenu.AccMenu(br,accommodation, accommodationviewDAO);
								break;
							}
							}catch(Exception e) {
								System.err.println("y 아니면 n 만 입력하세요");
							}

						}else {
							System.err.println("잘못된 선택입니다.");
						}
						
					
					}catch(Exception e) {
						System.err.println("1에서 2 숫자만 입력하세요");
					}

//					int num1 = Integer.parseInt(br.readLine());
//
//
//					accommodationviewDAO.selectdomesticInfo();
//
//					if(num1 == 1) {
//						accommodationviewDAO.selectdomesticInfo();
//					}else if(num1 == 2) {
//						accommodationviewDAO.selectoverseasInfo();
//					}
//					// 생성하고 다음 메뉴 부르기(reviewMenu)
//					ReviewMenu reviewMenu = new ReviewMenu(br, user, grade);
//					reviewMenu.R_menu(br,review, reviewDAO, accommodation,accommodationviewDAO );
//					}

				}else if(no==2){
					//상세 글 한번 보기
					// 목록에서 선택할 글 번호 확인
					// (숙소정보 한번 더 보여주기)


					System.out.println();

					reservationMenu.reservationMenu();



				}	


				else if(no == 3 ) {
					UserMenu userMenu = new UserMenu(br, user);
					CouponDAO couponDAO = new CouponDAO();
					String ID = UserDAO.getCurrentUserID();
					userMenu.u_Menu(br, user, review, userDAO, cashDAO, reviewDAO, couponDAO,ID);

				}

			} catch(NumberFormatException e){
				System.out.println("[숫자만 입력 가능]");
				System.out.println("1, 2, 3사이의 숫자만 입력하세요");
				continue;
			}
		}


		// 목록을 보여주고
		while(true) {
			try {
				int menunum = Integer.parseInt(br.readLine());
				if(menunum == 1) {
					accommodationviewDAO.selectdomesticInfo();

					// 숙소정보 확인하시겠습니까?

					while(true) {
						try {

							System.out.println("> 숙소 정보 확인하시겠습니까? 1. 예 / 2. 아니오 [1 or 2 중에서 선택해주세요]");
							int detailnum = Integer.parseInt(br.readLine());


							if (detailnum == 1) {
								System.out.print("숙소번호 선택 >");
								int acconum = Integer.parseInt(br.readLine());
								System.out.println("============================");
								accommodationviewDAO.selectDetailInfo(acconum);

								// 생성하고 다음 메뉴 부르기(reviewMenu)
								ReviewMenu reviewMenu = new ReviewMenu(br, user, grade);
								reviewMenu.R_menu(br,review, reviewDAO, accommodation,accommodationviewDAO );
								break;

								// 숙소 상세정보 안봄	-> 전체 메뉴로 돌아가
							}
							else if(detailnum == 2 ) {
								AccommodationMenu accommodationMenu = new AccommodationMenu(user, br,grade);
								accommodationMenu.AccMenu(br,accommodation, accommodationviewDAO);
								break;
							}
						}catch(Exception e) {

							System.out.println("1에서 2 숫자만 입력하세요");
						} 
					}
				}
				// 해외숙소보기
				else if(menunum == 2 ){
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
						ReviewMenu reviewMenu = new ReviewMenu(br, user, grade);
						reviewMenu.R_menu(br,review, reviewDAO, accommodation,accommodationviewDAO );
						break;
						// 숙소 상세정보 안봄	-> 전체 메뉴로 돌아가
					}else if(detailnum2 == 2) {
						AccommodationMenu accommodationMenu = new AccommodationMenu(user, br,grade);
						accommodationMenu.AccMenu(br,accommodation, accommodationviewDAO);
						break;
					}

				}else {
					System.err.println("번호 잘못 입력했습니다.");
					continue;
				}


			}catch(Exception e1) {

				System.err.println("잘못된 값을 입력했습니다.");
				System.err.println("번호로 입력하세요.");
				continue;

			}

		}
	}
	// 희동쿤이 해야할 예약 하기

} // class


