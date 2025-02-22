package com.booking.menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.booking.DAO.AccommodationviewDAO;

public class AccommodationMenu2 {
	private BufferedReader br ;
	private AccommodationviewDAO adao;
	
	//생성자
	public AccommodationMenu2() {
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			adao = new AccommodationviewDAO();
			
			// 메뉴 호출
			A_menu();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			// 자원 정리
			if(br!= null) try {br.close();}catch(IOException e) {}
			
		}
	}

	private void A_menu() {
		System.out.println("숙소 메뉴입니다.");
		System.out.println("원하는 항목을 선택해주세요");
		System.out.println("1. 숙소 보기");
		System.out.println("2. 숙소 상세보기");
		System.out.println("3. 예약하기");
		
		while (true) {
			try {
				int no = Integer.parseInt(br.readLine());
				if(no == 1) {
					adao.selectInfo();
				}
			} catch (Exception e) {
				e.printStackTrace();
			
			}finally {
				
			}
		}
		
		
		
	}

}
