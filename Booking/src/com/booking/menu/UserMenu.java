package com.booking.menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.booking.DAO.UserDAO;

public class UserMenu {

	private BufferedReader br;
	static UserDAO userdao;
	public UserMenu() {

		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			userdao = new UserDAO();
			callUser();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} // catch
		finally { 
			if( br != null )
			{
				try {
					br.close();
				}
				catch(IOException e) {

				}
			}
		} // finally

	}
	private void callUser() {
		// TODO Auto-generated method stub
		while(true) {
			System.out.print("1. 회원 정보 변경\n 2. 등급 확인\n 3. 잔액 충전\n 4. 작성 리뷰 내역\n, 5. 쿠폰 확인\n 6. 회원 탈퇴");
			try {
				int no = Integer.parseInt(br.readLine());
				if(no == 1) {
					System.out.println("회원 정보 변경");
					System.out.println("변경하고 싶은 정보를 선택하세요.(숫자)");

					try {
						if(no != 1 && no != 2 && no != 3) {
							System.out.println("0 ~ 3 의 숫자를 입력하세요");
							continue;
						} // if
					}catch(Exception e) {
						System.out.println("오로지. 오직. 무조건. [숫자]만 입력하세요");
						continue;
					} // catch





				}
			}catch(IOException e) {

			}

		}
	}
}
