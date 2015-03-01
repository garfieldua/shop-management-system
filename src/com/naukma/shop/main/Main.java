package com.naukma.shop.main;

import com.naukma.shop.controller.LogInController;
import com.naukma.shop.controller.MainController;
import com.naukma.shop.utils.Strings;

public class Main {
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		MainController.getInstance().setCurrentController(new LogInController());
		System.out.println(Strings.getProperty("ITEM_ADDED_TO_WAREHOUSE"));
	}
}
