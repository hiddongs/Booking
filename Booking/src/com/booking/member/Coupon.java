package com.booking.member;

import java.util.Date;


public class Coupon {
	
	int coupon_ID;
	int admin_ID;
	int coupon_code;
	Date coupon_issuance_date;
	Date coupon_expired_date;
	int coupon_discount;
	
	
	public Coupon(int coupon_ID, int admin_ID, int coupon_code, Date coupon_issuance_date, Date coupon_expired_date,
			int coupon_discount) {
		super();
		this.coupon_ID = coupon_ID;
		this.admin_ID = admin_ID;
		this.coupon_code = coupon_code;
		this.coupon_issuance_date = coupon_issuance_date;
		this.coupon_expired_date = coupon_expired_date;
		this.coupon_discount = coupon_discount;
	}
	public int getCoupon_ID() {
		return coupon_ID;
	}
	public void setCoupon_ID(int coupon_ID) {
		this.coupon_ID = coupon_ID;
	}
	public int getAdmin_ID() {
		return admin_ID;
	}
	public void setAdmin_ID(int admin_ID) {
		this.admin_ID = admin_ID;
	}
	public int getCoupon_code() {
		return coupon_code;
	}
	public void setCoupon_code(int coupon_code) {
		this.coupon_code = coupon_code;
	}
	public Date getCoupon_issuance_date() {
		return coupon_issuance_date;
	}
	public void setCoupon_issuance_date(Date coupon_issuance_date) {
		this.coupon_issuance_date = coupon_issuance_date;
	}
	public Date getCoupon_expired_date() {
		return coupon_expired_date;
	}
	public void setCoupon_expired_date(Date coupon_expired_date) {
		this.coupon_expired_date = coupon_expired_date;
	}
	public int getCoupon_discount() {
		return coupon_discount;
	}
	public void setCoupon_discount(int coupon_discount) {
		this.coupon_discount = coupon_discount;
	}
	
}
