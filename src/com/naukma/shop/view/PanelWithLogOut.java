package com.naukma.shop.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.event.ActionEvent;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JToolBar;
import javax.swing.JSpinner;
import javax.swing.JList;
import javax.swing.JScrollBar;
import javax.swing.JTabbedPane;
import javax.swing.JDesktopPane;
import javax.swing.JCheckBox;
import javax.swing.JMenuBar;

public class PanelWithLogOut extends Panel {
	private String txtBtnLogOut = "Log Out";
	
	private JSeparator separator;
	private JButton btnLogOut;

public PanelWithLogOut() {
	getExitButton().setLocation(450, 511);

	separator = new JSeparator();
	separator.setBounds(0, 60, 600, 2);
	add(separator);
	
	btnLogOut = new JButton(txtBtnLogOut); 
	btnLogOut.setBounds(500, 15, 89, 30);
	add(btnLogOut); 
}
public JButton getBtnLogOut() {
	return btnLogOut;
}
public void addListener(ActionListener l){
	btnLogOut.addActionListener(l);
}
}