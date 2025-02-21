package com.booking.menu;

import java.io.BufferedReader;

import com.booking.DAO.AccommodationviewDAO;
import com.booking.DAO.ReviewDAO;
import com.booking.accommodation.Accommodation;
import com.booking.accommodation.Review;

public class ReviewMenu {
	static Review review;
	static ReviewDAO reviewDAO;
	static Accommodation accommodation;
	static AccommodationviewDAO adao;
	
	//
	public static void R_menu(BufferedReader br, Review review, ReviewDAO reviewDAO, Accommodation accommodation) {
		// 선택된 숙소의 리뷰 보기
		
		ReviewMenu.review = review;
		ReviewMenu.reviewDAO = reviewDAO;
		
		try {
			while(true) {
			System.out.println("숙소 리뷰 확인하시겠습니까?");
			System.out.println("1. 예 2. 아니오");
			
			int no = Integer.parseInt(br.readLine());
			if(no == 1) {
				//adao.selectInfo();
				System.out.println("숙소번호 입력하세요 >");
				int num = Integer.parseInt(br.readLine());
				System.out.println("------------------------------------------");
				
				//reviewDAO.selectdetailReview(num);
				
			}else if(no == 2) {
				// 예약하기 메뉴로
				
			}
			
			
			
			
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
		
		}
		
	}
	
	
}// class
