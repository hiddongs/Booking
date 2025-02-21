package com.booking.accommodation;

import java.util.Date;

public class Review_bak {
	int reviewid;
	String userid;
	int accomodationid;
	Date reviewdate;
	String reviewcontent;
	int reviewrating;
	
	public Review_bak(int reviewid, String userid, int accomodationid, Date reviewdate, String reviewcontent,
			int reviewrating) {
		super();
		this.reviewid = reviewid;
		this.userid = userid;
		this.accomodationid = accomodationid;
		this.reviewdate = reviewdate;
		this.reviewcontent = reviewcontent;
		this.reviewrating = reviewrating;
	}
	
	public int getReviewid() {
		return reviewid;
	}

	public void setReviewid(int reviewid) {
		this.reviewid = reviewid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public int getAccomodationid() {
		return accomodationid;
	}

	public void setAccomodationid(int accomodationid) {
		this.accomodationid = accomodationid;
	}

	public Date getReviewdate() {
		return reviewdate;
	}

	public void setReviewdate(Date reviewdate) {
		this.reviewdate = reviewdate;
	}

	public String getReviewcontent() {
		return reviewcontent;
	}

	public void setReviewcontent(String reviewcontent) {
		this.reviewcontent = reviewcontent;
	}

	public int getReviewrating() {
		return reviewrating;
	}

	public void setReviewrating(int reviewrating) {
		this.reviewrating = reviewrating;
	}

	
}// class

