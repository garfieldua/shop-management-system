package com.naukma.shop.main;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.naukma.shop.controller.MainController;
import com.naukma.shop.database.*;
import com.naukma.shop.view.LogInView;
import com.naukma.shop.view.MainContainer;

public class Main extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
//		// singletone is not the best way to handle db connections
//		Dao provider = new Dao(MySQLProvider.getInstance());
//		
//		// using like this 
//		//ResultSet allitems = provider.getAllItems();
//		
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					MainView frame = new MainView();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
		
		MainController.getInstance();
	}
}
