package com.booking.menu;

import java.io.BufferedReader;

import java.io.IOException;
import java.util.InputMismatchException;

import com.booking.DAO.CashDAO;
import com.booking.DAO.CouponDAO;
import com.booking.DAO.ReviewDAO;
import com.booking.DAO.UserDAO;
import com.booking.member.Review;
import com.booking.member.User;

public class UserMenu {

//	private BufferedReader br = null;
	static User user;
	static Review review;
	static UserDAO userDAO;
    static CashDAO cashDAO;
    static ReviewDAO reviewDAO;
    static CouponDAO couponDAO;
    
    
   
  
	public void u_Menu(BufferedReader br, User user,Review review, UserDAO userDAO, CashDAO cashDAO,ReviewDAO reviewDAO
			           ,CouponDAO couponDAO,String ID)  {
		// TODO Auto-generated method stub 
		// 사용자 정보 메뉴 
		UserMenu.user = user;
		UserMenu.review = review;
		UserMenu.userDAO = userDAO;
		UserMenu.cashDAO = cashDAO;
		UserMenu.reviewDAO = reviewDAO;
		UserMenu.couponDAO = couponDAO;
		
		
		try {
			while(true) {
				
				System.out.println("--------------------------------");
				System.out.println("사용자 메뉴");
				System.out.printf("사용자 ID : %s\n", ID);
			    System.out.println("--------------------------------");
				System.out.println("1. 회원 이름 변경");
                System.out.println("2. 비밀번호 변경");
                System.out.println("3. 등급 확인");
                System.out.println("4. 금액 충전");
                System.out.println("5. 작성 리뷰 내역");
                System.out.println("6. 쿠폰 확인");
                System.out.println("7. 로그아웃");
                System.out.println("8. 회원 탈퇴");
                
				
                
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
						else if(num1 == 2) {
							System.out.println("이메일을 변경하세요 : ");
							String email = br.readLine();
							userDAO.changeUserEmail(ID, email);
							
						} // else if
						else if(num1 != 1 && num1 != 2) {
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
						//String nowPasswd = user.getPasswd();
						String passwd = user.getPasswd();
						System.out.println("변경할 비밀번호 입력 :");
						passwd = br.readLine();
						userDAO.changeUserPW(ID,passwd,br);
						
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

					try {
						System.out.println("금액 충전");
						int cash = user.getCash();
						System.out.println("충전할 금액을 입력하세요.");
						cash = Integer.parseInt(br.readLine());
						cashDAO.chargeCash(ID, cash, br);
						}catch(NumberFormatException e) {
						e.printStackTrace();
						System.out.println("숫자만 입력하세요 ");
					}
			
				}else if(no == 5) {
					
					try {
						System.out.println("작성 리뷰 내역 확인");
						System.out.println("리뷰 관리하시겠습니까? ( y / n )");
						char answer = br.readLine().charAt(0);
						if(answer == 'y') {
							int review_ID = review.getReview_ID();
							String review_content = review.getReview_content();
							reviewDAO.manageReview(ID, br,review_ID, review_content,reviewDAO);
						}else if(answer == 'n') {
							
						}
					} catch (InputMismatchException | IllegalArgumentException | StringIndexOutOfBoundsException e) {
						e.printStackTrace();
						System.out.println("y/n글자만 입력하세요");
					} 
					
				}else if(no == 6) {
					System.out.printf("%s 님이 보유하신 쿠폰입니다 \n", ID);
					couponDAO.showUserCoupon(ID);
					

				}else if(no == 7) {

					System.out.println("🚪 로그아웃되었습니다. 프로그램을 종료합니다.");
				    System.exit(0); // 프로그램 완전 종료
				    
				}else if(no == 8) {

					userDAO.deleteUser(ID,br);
					
				}

				else if(no > 8 ) { 
					System.out.println("1 ~ 8 의 숫자를 입력하세요");
					continue;
				}
			}

		}catch(NumberFormatException | IOException e) {
			e.printStackTrace();	

		}catch(Exception e) {
			System.out.println("오류발생");

			e.printStackTrace();
		}
	} // userMenu	
} // class



	


