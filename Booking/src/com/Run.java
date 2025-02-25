package com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.booking.Main;
import com.booking.accommodation.Accommodation;
import com.booking.member.Payment;
import com.booking.member.User;
import com.booking.menu.AdminMenu;
import com.booking.menu.PaymentMenu;

public class Run {

	public static void main(String[] args) throws NumberFormatException, IOException {

		//new Main();
		//UserMenu userMenu = new UserMenu();
		//userMenu.U_Menu(br, user, userDAO);

		
		PaymentMenu paymentMenu = new PaymentMenu();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Payment payment = null;
		User user = new User();
		Accommodation accommodation = null;
		paymentMenu.P_menu(br, payment, user, accommodation);
		
	} // main
} //class
