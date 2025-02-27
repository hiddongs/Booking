package com.booking.menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.rmi.server.ExportException;

import com.booking.DAO.AccommodationviewDAO;
import com.booking.DAO.ReservationDAO;
import com.booking.DAO.ReviewDAO;
import com.booking.DAO.UserDAO;
import com.booking.accommodation.Accommodation;
import com.booking.member.Review;
import com.booking.member.User;

public class ReviewMenu {
	static Review review;
	static ReviewDAO reviewDAO;
	static Accommodation accommodation;
	//숙소 뷰
	static AccommodationviewDAO adao;
	static ReservationDAO reservationDAO;
	static UserDAO userDAO;
	//

	BufferedReader br;

	User user;
	private Enum grade;

	public ReviewMenu(BufferedReader br , User user,Enum grade){

		this.user = user;
		this.br = br;
		this.grade = grade;
	}



	public void R_menu(BufferedReader br, Review review, ReviewDAO reviewDAO, Accommodation accommodation, AccommodationviewDAO adao ) throws IOException {
		// 선택된 숙소의 리뷰 보기
		// 초기화를 안했

		ReviewMenu.review = review;
		ReviewMenu.reviewDAO = new ReviewDAO();


		
			while(true) {
				System.out.println("숙소 리뷰 확인하시겠습니까?");
				System.out.println("1. 예 2. 아니오");

				int no = Integer.parseInt(br.readLine());
				if(no == 1) {

					while(true) {
					 try {
			                System.out.print("숙소번호 입력하세요> ");
			                int num = Integer.parseInt(br.readLine()); // int로 변환

			                // 정상적인 정수 입력 시 실행
			                ReviewMenu.reviewDAO.selectdetailReview(num);
			                break;
			            } catch (NumberFormatException e) {
			                // 숫자가 아닌 경우 예외 처리
			                System.out.println("호수에 맞는 숫자만 입력하세요.");
			                continue;
			            }
					}
					while(true) {

						System.out.println("예약을 원하시면 y 아니면 n를 입력하세요");
						char answer = br.readLine().charAt(0);
						if(answer == 'y') {
							ReservationMenu reservationMenu = new ReservationMenu();
							reservationMenu.reservationMenu();
							break;

						}
						else if(answer == 'n') {
							System.out.println("처음 화면으로 돌아갑니다.");
							AccommodationMenu accommodationMenu= new AccommodationMenu(user, br,grade);
							accommodationMenu.AccMenu(br,accommodation, adao);
							break;

						}
						else
						{
							System.out.println("y / n 두 글자 중에서 하나만 입력하세요");
							continue;
						}

					}

					}else if(no == 2) {
						while(true) {

						System.out.println("예약을 원하시면 y 아니면 n를 입력하세요");
						char answer = br.readLine().charAt(0);
						if(answer == 'y') {
							ReservationMenu reservationMenu = new ReservationMenu();
							reservationMenu.reservationMenu();
							break;

						}else if(answer == 'n') {
							System.out.println("처음 화면으로 돌아갑니다.");
							AccommodationMenu accommodationMenu= new AccommodationMenu(user, br,grade);
							accommodationMenu.AccMenu(br,accommodation, adao);
							break;

						}
						else
						{
							System.out.println("y / n 두 글자 중에서 하나만 입력하세요");
							continue;
						}
					}
				}
				else {
					System.out.println("1 아니면 2의 숫자만 입력하세요");
					continue;
				}
			}
			


		}
		
	

}// class
