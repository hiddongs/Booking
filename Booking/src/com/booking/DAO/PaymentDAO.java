package com.booking.DAO;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.booking.accommodation.Accommodation;
import com.booking.member.Coupon;
import com.booking.member.Payment;
import com.booking.member.User;

public class PaymentDAO {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	// 결제할 때 필요한 것
	// 결제, 쿠폰, 숙소, 장바구니, 사용자(포인트) 클래스 필요
	static User user; // 포인트 가져오려고 
	static Payment payment;
	static Coupon coupon;
	static Accommodation accomodation;
	// 장바구니도 가져와야 할 것 같음 - 장바구니의 ID를 가져와야 할거같은디
	// create, update, insert, delete
	
	
	
}
