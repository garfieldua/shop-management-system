package com.naukma.shop.view;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;

public class AddNewSupplierView extends PanelWithLogOut{
	private JTextField textName;
	private JTextField textPhone;
	private JButton btnAdd;
	private JLabel lblDescription;
	private JLabel lblTitle;
	private JLabel lblNewProduct;
	private JButton btnBack;
	
	public AddNewSupplierView() {
		
		lblNewProduct = new JLabel("New product");
		lblNewProduct.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewProduct.setBounds(25, 15, 100, 25);
		add(lblNewProduct);
		
		lblTitle = new JLabel("Name");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTitle.setBounds(110, 209, 100, 25);
		add(lblTitle);
		
		lblDescription = new JLabel("Phone");
		lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDescription.setBounds(110, 279, 100, 25);
		add(lblDescription);
		
		textName = new JTextField();
		textName.setBounds(245, 210, 250, 25);
		add(textName);
		textName.setColumns(10);
		
		textPhone = new JTextField();
		textPhone.setBounds(245, 280, 250, 25);
		add(textPhone);
		textPhone.setColumns(10);
		
		btnAdd = new JButton("Add");
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAdd.setBounds(450, 411, 100, 25);
		add(btnAdd);
		
		btnBack = new JButton("Back");
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnBack.setBounds(50, 411, 116, 25);
		add(btnBack);
	}

	public JButton getBtnAdd() {
		return btnAdd;
	}
	
	public JButton getBtnBack() {
		return btnBack;
	}
	
	public JTextField getTextFieldName() {
		return textName;
	}
	
	public JTextField getTextFieldPhone() {
		return textPhone;
	}
	
	public void addListener(ActionListener l){
		this.btnAdd.addActionListener(l);
	}
}
