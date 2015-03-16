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

public class AddNewSupplierView extends PanelWithLogOut{
	private JTextField text;
	private JTextField textField;
	private JButton btnAdd;
	private JLabel lblDescription;
	private JLabel lblTitle;
	private JLabel lblNewProduct;
	
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
		
		text = new JTextField();
		text.setBounds(245, 210, 250, 25);
		add(text);
		text.setColumns(10);
		
		textField = new JTextField();
		textField.setBounds(245, 280, 250, 25);
		add(textField);
		textField.setColumns(10);
		
		btnAdd = new JButton("Add");
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAdd.setBounds(450, 411, 100, 25);
		add(btnAdd);
	}

	public JButton getBtnAdd() {
		return btnAdd;
	}
	public void addListener(ActionListener l){
		this.btnAdd.addActionListener(l);
	}
}
