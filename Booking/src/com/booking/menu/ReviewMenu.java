package com.booking.menu;

import java.io.BufferedReader;
import java.io.IOException;

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

		
		try {
			while(true) {
			System.out.println("숙소 리뷰 확인하시겠습니까?");
			System.out.println("1. 예 2. 아니오");
			
			int no = Integer.parseInt(br.readLine());
			if(no == 1) {
				System.out.println("숙소번호 입력하세요>");
				int num = Integer.parseInt(br.readLine());
				
				ReviewMenu.reviewDAO.selectdetailReview(num);

				


			}else if(no == 2) {
				// 예약하기 화면으로
				AccommodationMenu accommodationMenu= new AccommodationMenu(user, br,grade);
				accommodationMenu.AccMenu(br,accommodation, adao);
				

			}
		}


		} catch (Exception e) {
			e.printStackTrace();

		}finally {
			
		}
	}
	
}// class
