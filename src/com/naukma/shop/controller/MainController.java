package com.naukma.shop.controller;

import javax.swing.JPanel;

import com.naukma.shop.view.LogInView;
import com.naukma.shop.view.MainContainer;

public class MainController {
	private static MainController instance = null;
	private final MainContainer main = new MainContainer();
	
	private MainController() {
		this.setPanel(new LogInView());
		main.setVisible(true);
	}
	
	public static MainController getInstance() {
		if (instance == null) {
			instance = new MainController();
		}
		return instance;
	}
	
	public void setPanel(JPanel panel) {
		main.showPane(panel);
	}
}
