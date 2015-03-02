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
import javax.swing.JComponent;
import javax.swing.SwingConstants;
import javax.swing.JToolBar;
import javax.swing.JSpinner;
import javax.swing.JList;
import javax.swing.JScrollBar;
import javax.swing.JComboBox;

public class ManagerView extends PanelWithLogOut{
	
	private JLabel lblManagerView;
	private JButton btnOrderProducts;

	public ManagerView() {
		lblManagerView = new JLabel("Manager View");
		lblManagerView.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblManagerView.setBounds(10, 5, 200, 50);
		add(lblManagerView);
		
		btnOrderProducts = new JButton("Order critical amount products");
		btnOrderProducts.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnOrderProducts.setBounds(175, 137, 250, 25);
		add(btnOrderProducts);
	}

	public JButton getBtnOrderProducts() {
		return btnOrderProducts;
	}
	
	public void addListener(ActionListener l){
		this.getBtnOrderProducts().addActionListener(l);
	
	
	}
}


