package com.naukma.shop.view;

import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.event.ActionEvent;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.SwingConstants;
import javax.swing.JToolBar;
import javax.swing.JSpinner;
import javax.swing.JList;
import javax.swing.JScrollBar;
import javax.swing.JComboBox;


public class LogInView extends Panel {
	
	String txtBtnLogIn = "Log In";
	String txtLblYourLogin = "Your login:";
	String txtLblYourPswd = "Your password :";
	String txtLblGreetings = "Sing up to work";
	
	
	private JButton btnLogIn;
	private JTextField textFieldLogin;
	private JPasswordField textFieldPswd;
	private JLabel lblGreeting;
	private JLabel lblInfoTool;
	private JLabel lblYourLogin;
	private JLabel lblYourPassword;
	

	public JButton getLogInBtn() {
		return btnLogIn;
	}

	public LogInView() {

		btnLogIn = new JButton(txtBtnLogIn);
		btnLogIn.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnLogIn.setBounds(250, 371, 100, 32);
		add(btnLogIn);
		
		textFieldLogin = new JTextField();
		textFieldLogin.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textFieldLogin.setBounds(250, 219, 200, 32);
		add(textFieldLogin);
		textFieldLogin.setColumns(10);
		
		textFieldPswd = new JPasswordField();
		textFieldPswd.setBounds(250, 274, 200, 32);
		add(textFieldPswd);
		
		lblYourLogin = new JLabel(txtLblYourLogin);
		lblYourLogin.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblYourLogin.setBounds(150, 224, 100, 20);
		add(lblYourLogin);
		
		lblYourPassword = new JLabel(txtLblYourPswd);
		lblYourPassword.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblYourPassword.setBounds(150, 279, 100, 20);
		add(lblYourPassword);
		
		lblGreeting = new JLabel(txtLblGreetings);
		lblGreeting.setForeground(Color.WHITE);
		lblGreeting.setHorizontalAlignment(SwingConstants.CENTER);
		lblGreeting.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblGreeting.setBounds(200, 61, 200, 96);
		add(lblGreeting);
		
	}

	public JButton getBtnLogIn() {
		return btnLogIn;
	}

	public JPasswordField getTextFieldPswd() {
		return textFieldPswd;
	}

	
	public JTextField getTextFieldLogin() {
		return textFieldLogin;
	}

	public void addListener(ActionListener l) {
		btnLogIn.addActionListener(l);

	}
}
