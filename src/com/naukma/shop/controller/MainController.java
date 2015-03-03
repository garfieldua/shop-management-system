package com.naukma.shop.controller;

import javax.swing.JPanel;

import com.naukma.shop.database.Dao;
import com.naukma.shop.database.Objects.Employee;
import com.naukma.shop.view.MainContainer;
import com.naukma.shop.view.PanelWithLogOut;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class MainController extends AbstractController {
	// static controller
	private static MainController instance = null;
	
	// current controller for main container
	private AbstractController currentController = null;
	
	// main container
	private final MainContainer main = new MainContainer();
	
	// current user
	private Employee currentUser = null;
	
	
	private MainController() {
		Dao.enableCache(false);
		main.setVisible(true);
	}
	
	public static MainController getInstance() {
		if (instance == null) {
			instance = new MainController();
		}
		return instance;
	}
	
	public void setPanel(JPanel panel) {
		main.showPanel(panel);
	}
	
	public void setCurrentController(AbstractController c) {
		currentController = c;
		c.assignToMainContainer();
	}
	
	public void setCurrentUser(Employee employee) {
		currentUser = employee;
	}
	
	public Employee getCurrentUser() {
		return currentUser;
	}
	
	public void addLogOutButtonListener(PanelWithLogOut view) {
		view.getBtnLogOut().addActionListener(new ActionListener() {
			@Override
	        public void actionPerformed(ActionEvent e)
	        {
	        	System.out.println("want logout");
	        	
	        	setCurrentUser(null);
	        	setCurrentController(new LogInController());
	        }
		});
	}
}
