package com.booking.menu;

import java.io.BufferedReader;

import java.io.IOException;

import com.booking.DAO.AccommodationDAO;
import com.booking.DAO.AccommodationviewDAO;
import com.booking.DAO.ReviewDAO;
import com.booking.accommodation.Accommodation;
import com.booking.member.Review;

public class AccommodationMenu { 
	private BufferedReader br;
	static AccommodationviewDAO accommodationviewDAO;  // 숙소 DAO
	static Accommodation accommodation;   // 숙소클래스(생성자, getter, setter)
	// 리뷰 클래스
	static Review review; 
	// 리뷰 DAO
	static ReviewDAO reviewDAO; 
	
	// 숙소메뉴 호출 함수
	public void AccMenu(BufferedReader br, Accommodation accommodation, AccommodationviewDAO accommodationviewDAO) throws IOException {
		//숙소 메뉴
		AccommodationMenu.accommodation = accommodation;
		AccommodationMenu.accommodationviewDAO = accommodationviewDAO;
		
		//1. 숙소보기
		try {

			while (true) {
				System.out.println("원하는 항목을 선택하세요.");
				System.out.println("1.숙소 보기");
				//System.out.println("2.숙소 상세보기");
				System.out.println("2.예약하기");
				int no = Integer.parseInt(br.readLine());
				if(no==1) {
					System.out.println("1.국내 2.해외");
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
					AccommodationDAO accommodationDAO = new AccommodationDAO();

					System.out.println("예약을 위해 국내 / 해외 선택하세요 (1번을 누르면 [국내] 2번을 누르면 [해외] 입니다 ");
					int areaNum = Integer.parseInt(br.readLine());
					try {
						if(areaNum == 1) {
							System.out.println("국내 예약");
							try {
								System.out.println("2번 추천여행지 보기");
								int sugNum = Integer.parseInt(br.readLine());
								try {
									if(sugNum == 2) { // 추천 여행지 메뉴판
										System.out.println("추천 여행지 보기");
										accommodationDAO.suggest_accommodation(br, null);
									}

								}catch (Exception e) {
									// TODO: handle exception
								}


							}catch (Exception e) {
								// TODO: handle exception
							}
						}
						else if(areaNum == 2) {
							System.out.println("해외 예약");

							System.out.println("2번 추천 여행지 보기");
							int sugNum = Integer.parseInt(br.readLine());
							try {
								if(sugNum == 2) {// 추천 여행지 메뉴판
									accommodationDAO.suggest_accommodation(br, "해외");
									
								}
							}catch (Exception e) {
								// TODO: handle exception
							}
						}

					}catch(Exception e) {

					}


				}

			} 
		}catch(NumberFormatException e){
			System.out.println("[숫자만 입력 가능]");
		}finally {

		}


	} // class

}
