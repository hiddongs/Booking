package com.booking.menu;

import java.io.BufferedReader;

import com.booking.DAO.AccommodationviewDAO;
import com.booking.accommodation.Accommodation_bak;

public class AccommodationMenu_bak { 
	private BufferedReader br;
	// 숙소 DAO
	static AccommodationviewDAO aviewDAO;
	// 숙소 클래스
	static Accommodation_bak accommodation;

	public void AccMenu(BufferedReader br, Accommodation_bak accommodation, AccommodationviewDAO aviewDAO) {
		//숙소 메뉴
		AccommodationMenu_bak.accommodation = accommodation;
		AccommodationMenu_bak.aviewDAO = aviewDAO;

		//1. 숙소보기
		try {
			while (true) {
				System.out.println("숙소 메뉴 입니다.");
				System.out.println("원하는 항목을 선택하세요.");
				System.out.println("1.전체 숙소 보기");
				System.out.println("2.숙소 상세보기");
				System.out.println("3.예약하기");
				
				int no = Integer.parseInt(br.readLine());
				if(no==1) {
					aviewDAO.selectInfo();
				}else if(no==2){
					//상세 글 한번 보기
					// 목록에서 선택할 글 번호 확인
					System.out.println();
					
					
				}
		
			}

		} catch (Exception e) {

		}finally {

		}
	}


} // class
