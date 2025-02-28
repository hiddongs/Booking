package com.booking.menu;
// 결제 메뉴 입니다.
//  

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.booking.DAO.AccommodationviewDAO;
import com.booking.DAO.CashDAO;
import com.booking.DAO.CouponDAO;
import com.booking.DAO.PaymentDAO;
import com.booking.DAO.ReservationDAO;
import com.booking.DAO.UserDAO;
import com.booking.accommodation.Accommodation;
import com.booking.member.Payment;
import com.booking.member.Reservation;
import com.booking.member.User;


public class PaymentMenu {

   private Reservation reservation;
   private User user;
   private CashDAO cashDAO = new CashDAO();
   private PaymentDAO paymentDAO = new PaymentDAO(user, reservation);;
  CouponDAO couponDAO = new CouponDAO();

   //결제 메뉴 생성자(결제, 사용자, 숙소)
   public PaymentMenu(User user, Reservation reservation, PaymentDAO paymentDAO) {
      this.user=user;
      this.reservation= reservation;
      this.paymentDAO  = paymentDAO; // 객체 할당
      this.reserVationDAO = reserVationDAO;
   }


   static UserDAO userDAO;
   ReservationDAO reserVationDAO = new ReservationDAO();
   Payment payment = new Payment();
   // PaymentMenu paymentmenu = new PaymentMenu(user,reservation,paymentDAO);

   public void P_menu(BufferedReader br, PaymentDAO paymentDAO,Payment payment, User user, Reservation reservation) throws NumberFormatException, IOException, ClassNotFoundException {
      paymentDAO = new PaymentDAO(user, reservation); // user객체를 전달하여 paymentDAO 생성

      // 현재 로그인한 사용자 아이디 가져오기
      String ID;
      if (user != null && user.getID() != null) {
         ID = user.getID(); // user객체에서 가져옴
      }else {
         ID=UserDAO.getCurrentUserID(); // 아니면 userDAO에서 가져오기
      }

      System.out.println("현재 아이디:" +ID);

      while (true) {
         System.out.println("결제 메뉴입니다.");
         System.out.println("1.예약 현황 조회");
         System.out.println("2.결제 내역 조회");



         int no = Integer.parseInt(br.readLine());
         if (no == 1) {  // 예약 현황 조회
            // 예약현황 보기
            // 예약 현황을 보여주고 예약 번호 입력 받기
            // 호출이 된 것
            
            
            reserVationDAO.showReservation(); // 예약 목록 보여주기
            System.out.println("예약 번호를 입력해주세요:");
            int reservationId = Integer.parseInt(br.readLine());
            int[] result = paymentDAO.processCashPayment(ID, reservationId);

            if (result == null) {
                System.err.println("결제 정보를 가져오는 데 실패했습니다.");
                return;
            }
            int userCash = result[0];
            int accommodationPrice = result[1];
            
            // 결제 진행
            System.out.println("결제 방법을 선택하세요: 1.현금 2.현금+포인트 3.현금+쿠폰");
            int paymentMethod = Integer.parseInt(br.readLine());

            
               
               // BufferedReader br, PaymentDAO paymentDAO,Payment payment, User user, Reservation reservation,String ID
               if (paymentMethod == 1) {
                  // 현금 결제 처리


                  System.out.printf("현재 보유한 원화: %d원\n", userCash);
                  System.out.printf("숙소 가격: %d원\n", accommodationPrice);

                  //캐시 있는지 확인
                  // 부족한 가격만 추가해주면 되는데
                  // 숙소 가격보다 사용자 현금이 크면 바로 계산
                  if(userCash >= accommodationPrice) {
                     System.out.println("결제 진행합니다.");
                     // 결제 진행 후 잔액 업데이트 진행
                     // 현재 로그인한 사용자 : ID
                     paymentDAO.updateCashPayment(ID, userCash-accommodationPrice);
                     paymentDAO.select_PaymentHistory(); // 결제 내역 select
                     System.out.println("결제가 완료되었습니다.");
                  }else {
                     //잔액부족
                     int requiredCash = accommodationPrice-userCash;
                     System.out.printf("잔액이 부족하여 결제할 수 없습니다. 추가로 %d원이 필요합니다.\n", requiredCash);
                     System.out.println("부족한 금액 충전하시겠습니까? (1.예 / 2.아니오");
                     int num1 = Integer.parseInt(br.readLine());
                     if(num1 == 1) {
                        cashDAO.chargeCash(ID, requiredCash, br);
                     }else {
                        System.out.println("결제가 취소되었습니다.");
                     }
                  }
                  cashDAO.chargeCash(ID, userCash, br);
               }
            
            else if(paymentMethod == 2) {

               // 2.현금 + 포인트 결제

               // 1. 보유한 포인트 확인
               // 2. 알마 쓸지 결정
               // 3. 포인트 쓴 만큼 나머지 원화
               // 4. 만약 원화 결제 부족하면 원화 충전 창으로 
               // 5. 있으면 합산해서 결제
               // 6. 포인트 차감
               // 7. 원화 차감
               try {
                  
                  System.out.printf("숙소 가격: %d원\n", accommodationPrice);
                  System.out.println("현재 보유한 원화입니다.");
                  cashDAO.showCash(ID);
                  System.out.println("현재 보유한 포인트입니다.");
                  cashDAO.showPoint(ID);
                  System.out.println("사용할 포인트를 입력하세요");
                  int point = Integer.parseInt(br.readLine());
                  System.out.println("결제 예정 금액 : "+ (accommodationPrice - point));
                  

                  if(userCash < accommodationPrice - point ) {
                     System.out.println("결제 금액이 모자랍니다");
                     System.out.println("모자란 금액 : " + ((accommodationPrice - point) - userCash));
                     System.out.println("필요한 금액만큼 자동 충전 및 자동 결제 하시겠습니까?");
                     System.out.println("1. 예 2. 아니오 (번호 입력)");
                     int s_num = Integer.parseInt(br.readLine());
                     if(s_num == 1) {
                        cashDAO.chargeCash(ID, accommodationPrice - point, br);
                        System.out.println("결제를 진행합니다.");
                        cashDAO.useCash(ID, accommodationPrice - point);
                        
                        
                        cashDAO.usePoint(ID, point);
                        
                        System.out.println("현재 보유한 원화입니다.");
                        cashDAO.showCash(ID);
                        System.out.println("현재 보유한 포인트입니다.");
                        cashDAO.showPoint(ID);
                     }
                     else if(s_num == 2) {
                        System.out.println("결제 취소");
                        continue;
                     }
                     else
                     {
                        System.out.println("올바른 값을 입력하세요 (1, 2)");
                     }

                  }else {

                     System.out.println("결제를 진행합니다.");
                     paymentDAO.updateCashPayment(ID, accommodationPrice - point);
                     cashDAO.usePoint(ID, point);
                     System.out.println("현재 보유한 원화입니다.");
                     cashDAO.showCash(ID);
                     System.out.println("현재 보유한 포인트입니다.");
                     cashDAO.showPoint(ID);
                  }



               }catch(Exception e) {
                  e.printStackTrace();
               }

            }else if(paymentMethod == 3) {
               // 3. 현금 + 쿠폰
                // 1. 보유한 쿠폰 확인
                // 2. 얼마 쓸지 결정
                // 3. 전체 금액 - 쿠폰, 나머지 원화
               // 4. 할인된 가격으로 계산
               // 5. 만약 원화 결제 부족하면 원화 충전 창으로 
                // 6. 쿠폰 삭제
                // 7. 현금 차감
               System.out.printf("숙소 가격: %d원\n", accommodationPrice);
                System.out.println("현재 보유한 원화입니다.");
                cashDAO.showCash(ID);
                System.out.println("현재 보유한 쿠폰입니다.");
                
                couponDAO.showUserCoupon(ID);
                //쿠폰 ID
                System.out.println("쿠폰 ID목록:");
                List<Integer> coupons = couponDAO.coupon_ID(ID); // 쿠폰DAO에서 쿠폰들의 ID목록ㄱ 가져옴
                for (Integer coupon :  coupons) {
               System.out.println("쿠폰번호:"+coupons);
            }
                System.out.println("사용할 사용할 쿠폰번호를 입력하세요");
                // 쿠폰 번호를 입력하기
                int availableCoupon = Integer.parseInt(br.readLine());
                if (coupons.contains(availableCoupon)) {
               System.out.println("쿠폰 적용 완료. 할인금액:"+availableCoupon);
               //쿠폰 적용 후 결제 예정 금액 계산
               int discountPrice = accommodationPrice - availableCoupon;
               System.out.println("할인된 결제 금액:"+discountPrice);
               //현재 보유한 캐시 목록 보기
               cashDAO.showCash(ID);
               if(userCash < discountPrice) {
                  System.out.println("결제 금액이 모자랍니다");
                  System.out.println("모자란 금액 : " + (discountPrice - userCash));
                  System.out.println("필요한 금액만큼 충전 하시겠습니까?");
                  System.out.println("(1.예 / 2.아니오) 선택하세요");
                  
                  int num = Integer.parseInt(br.readLine());
                  // 충전하러
                  if (num == 1) {
                     cashDAO.chargeCash(ID, discountPrice - userCash, br);
                     System.out.println("결제를 진행합니다.");
                     
                     // 결제 진행
                     cashDAO.useCash(ID,discountPrice);
                     System.out.println("결제 완료 !!");
                     
                     //사용자가 사용한 쿠폰 삭제하기
                     couponDAO.useCoupon(ID, availableCoupon);
                     System.out.println("사용한 쿠폰이 삭제 되었습니다.");
                     
                     System.out.println("현재 보유한 원화입니다.");
                           cashDAO.showCash(ID);
                           System.out.println("현재 보유한 쿠폰입니다.");
                           couponDAO.showUserCoupon(ID);
                        }else if(num == 2) {
                           System.out.println("결제 취소");
                           continue;
                     
                  }else {
                     System.out.println("올바른 값을 선택하세요 (1 / 2)");
                  }
               }else {
                  //현금이 충분한 경우
                   System.out.println("결제를 진행합니다.");
                   paymentDAO.updateCashPayment(ID, discountPrice);
                   couponDAO.useCoupon(ID,availableCoupon);
                   
                   System.out.println("현재 보유한 원화입니다.");
                        cashDAO.showCash(ID);
                        
                        System.out.println("현재 보유한 쿠폰입니다.");
                        couponDAO.showUserCoupon(ID);
               }
               
                }else {
                   System.out.println("선택한 쿠폰은 존재하지 않습니다. 다시 입력해주세요");
                }   
            }


               // 결제 내역 보기
            }else if (no == 2) {
               System.out.println("결제내역 조회");
               //결제 로직 조회
               paymentDAO.select_PaymentHistory();
            }
            break;
         } 

      }
   
}