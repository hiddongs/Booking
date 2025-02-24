package com.booking.member;

import java.util.Date;

public class Payment {
	int payment_id; // 결제 ID
	String user_id; // 사용자 ID
	int reservation_id; // 예약 아이디
	int payment_used_cash; // 사용된 ID
	int payment_used_point; //  사용한 ID
	int payment_total_price; // 전체금약
	Date payment_date; // 구매한 날짜
	int payment_method;  // 1 = 전액현금 , 2= 현금+포인트 , 3= 현금 + 쿠폰 4. 현금+포인트 + 쿠폰
	
	
}