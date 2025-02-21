package com.booking.accommodation;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import com.dbutil.DBUtil;

public class Accommodation {
	int accommodation_id;
	String accommodation_name;
	String accommodation_address;
	String accommodation_description;
	int accommodation_price;
	String location_name; 
	String recommendation_season; 
	int accommodation_status;
	int allowed_number;


	public Accommodation(int accommodation_id, String accommodation_name, String accommodation_address,
			String accommodation_description, int accommodation_price, String location_name,
			String recommendation_season, int accommodation_status, int allowed_number) {
		super();
		this.accommodation_id = accommodation_id;
		this.accommodation_name = accommodation_name;
		this.accommodation_address = accommodation_address;
		this.accommodation_description = accommodation_description;
		this.accommodation_price = accommodation_price;
		this.location_name = location_name;
		this.recommendation_season = recommendation_season;
		this.accommodation_status = accommodation_status;
		this.allowed_number = allowed_number;
	}


	public int getAccommodation_id() {
		return accommodation_id;
	}

	public void setAccommodation_id(int accommodation_id) {
		this.accommodation_id = accommodation_id;
	}

	public String getAccommodation_name() {
		return accommodation_name;
	}

	public void setAccommodation_name(String accommodation_name) {
		this.accommodation_name = accommodation_name;
	}

	public String getAccommodation_address() {
		return accommodation_address;
	}

	public void setAccommodation_address(String accommodation_address) {
		this.accommodation_address = accommodation_address;
	}

	public String getAccommodation_description() {
		return accommodation_description;
	}

	public void setAccommodation_description(String accommodation_description) {
		this.accommodation_description = accommodation_description;
	}

	public int getAccommodation_price() {
		return accommodation_price;
	}

	public void setAccommodation_price(int accommodation_price) {
		this.accommodation_price = accommodation_price;
	}

	public String getLocation_name() {
		return location_name;
	}

	public void setLocation_name(String location_name) {
		this.location_name = location_name;
	}

	public String getRecommendation_season() {
		return recommendation_season;
	}

	public void setRecommendation_season(String recommendation_season) {
		this.recommendation_season = recommendation_season;
	}

	public int getAccommodation_status() {
		return accommodation_status;
	}

	public void setAccommodation_status(int accommodation_status) {
		this.accommodation_status = accommodation_status;
	}

	public int getAllowed_number() {
		return allowed_number;
	}

	public void setAllowed_number(int allowed_number) {
		this.allowed_number = allowed_number;
	}
}
