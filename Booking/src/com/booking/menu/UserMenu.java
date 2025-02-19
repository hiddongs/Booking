package com.booking.menu;

import java.io.BufferedReader;
import java.io.IOException;

import com.booking.DAO.UserDAO;
import com.booking.member.User;
import com.dbutil.DBUtil;

public class UserMenu {

	private BufferedReader br = null;
	static User user;
	static UserDAO userDAO;



	public void U_Menu(BufferedReader br, User user, UserDAO userDAO)  {
		// TODO Auto-generated method stub 
		// 사용자 정보 메뉴 
		UserMenu.user = user;
		UserMenu.userDAO = userDAO;
		try {
			while(true) {
				System.out.println("1. 회원 이름 변경");
                System.out.println("2. 비밀번호 변경");
                System.out.println("3. 등급 확인");
                System.out.println("4. 잔액 충전");
                System.out.println("5. 작성 리뷰 내역");
                System.out.println("6. 쿠폰 확인");
                System.out.println("7. 로그아웃");
                System.out.println("8. 회원 탈퇴");
                
				String ID = user.getName();
				int no = Integer.parseInt(br.readLine());
				if(no == 1) {
					System.out.println("회원 정보 변경");
					System.out.println("변경하고 싶은 정보를 선택하세요.(숫자)");
					System.out.println("1. 이름 2. 이메일");
                    int num1 = Integer.parseInt(br.readLine());
					
					
					try {
						if(num1 == 1) {
							System.out.println("이름을 변경하세요 : ");
							String name = br.readLine();
							userDAO.changeUserName(ID, name);
							
						} // if
						else if(no == 2) {
							System.out.println("이메일을 변경하세요 : ");
							String email = br.readLine();
							userDAO.changeUserEmail(ID, email);
							System.out.println("이메일 변경 완료");
						} // else if
						else if(no != 1 && no != 2) {
							System.out.println("1 ~ 2 의 숫자를 입력하세요");
							continue;
						} // if
					}catch(Exception e) {
						System.out.println("오로지. 오직. 무조건. [숫자]만 입력하세요");
						continue;
					} // catch
				}
				else if(no == 2 ) {
					try {
						System.out.println("비밀번호 변경");
						String passwd = user.getPasswd();
						userDAO.changeUserPW(ID,passwd);
						System.out.println("변경할 비밀번호 입력 :");
						passwd = br.readLine();
						System.out.println("비밀번호가 변경됐습니다");
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
				else if(no == 3) {
					
					try {
						System.out.println("등급 확인");
						Enum grade = user.getGrade();
						userDAO.checkGrade(ID,grade);
					}catch(Exception e){
						
					}
				}else if(no == 4) {

				}else if(no == 5) {

				}else if(no == 6) {

				}else if(no == 7) {

				}else if(no == 8) {

				}

				else if(no > 8 ) { 
					System.out.println("1 ~ 8 의 숫자를 입력하세요");
					continue;
				}
			}
		}catch(Exception e) {

		}

	} // userMenu
} // class



	


