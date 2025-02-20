package com.booking.menu;

import java.io.BufferedReader;
import java.io.IOException;

import com.booking.DAO.AccommodationviewDAO;
import com.booking.accommodation.Accommodation;

public class AccommodationMenu { 
	private BufferedReader br;
	static AccommodationviewDAO accommodationviewDAO;  // 숙소 DAO
	static Accommodation accommodation;   // 숙소클래스(생성자, getter, setter)

	// 숙소메뉴 호출 함수
	public void AccMenu(BufferedReader br, Accommodation accommodation, AccommodationviewDAO accommodationviewDAO) throws IOException {
		//숙소 메뉴
		AccommodationMenu.accommodation = accommodation;
		AccommodationMenu.accommodationviewDAO = accommodationviewDAO;

		//1. 숙소보기
		try {
			
			while (true) {
				System.out.println("원하는 항목을 선택하세요.");
				System.out.println("1.전체 숙소 보기");
				System.out.println("2.숙소 상세보기");
				System.out.println("3.예약하기");
				
				int no = Integer.parseInt(br.readLine());
				if(no==1) {
					accommodationviewDAO.selectInfo();
					
				}else if(no==2){
					//상세 글 한번 보기
					// 목록에서 선택할 글 번호 확인
					// (숙소정보 한번 더 보여주기)
					accommodationviewDAO.selectInfo();
					System.out.print("선택한 숙소 번호 >");
					int num = Integer.parseInt(br.readLine());
					System.out.println("============================");
					accommodationviewDAO.selectDetailInfo(num);
					
				}
		
			}

		}catch(NumberFormatException e){
			System.out.println("[숫자만 입력 가능]");
		}finally {

		}
	}


} // class
