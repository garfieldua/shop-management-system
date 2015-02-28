package com.naukma.shop.main;

import com.naukma.shop.controller.LogInController;
import com.naukma.shop.controller.MainController;

public class Main {
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		MainController.getInstance().setCurrentController(new LogInController());
	}
}
