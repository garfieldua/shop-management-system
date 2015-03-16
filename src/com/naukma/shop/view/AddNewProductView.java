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

public class AddNewProductView extends PanelWithLogOut{
	private JTextField text;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JLabel lblPrice;
	private JLabel lblOrigin;
	private JLabel lblMinimalQuantity;
	private JLabel lblDepartment;
	private JComboBox comboBox;
	private JButton btnAdd;
	private JLabel lblNewProduct;
	private JLabel lblTitle;
	private JLabel lblDescription;
	
	
	public AddNewProductView() {
		
		lblNewProduct = new JLabel("New product");
		lblNewProduct.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewProduct.setBounds(25, 15, 100, 25);
		add(lblNewProduct);
		
		lblTitle = new JLabel("Title");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTitle.setBounds(80, 125, 100, 25);
		add(lblTitle);
		
		lblDescription = new JLabel("Description");
		lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDescription.setBounds(80, 165, 100, 25);
		add(lblDescription);
		
		lblOrigin = new JLabel("Origin");
		lblOrigin.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblOrigin.setBounds(80, 205, 100, 25);
		add(lblOrigin);
		
		lblDepartment = new JLabel("Department");
		lblDepartment.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDepartment.setBounds(80, 245, 100, 25);
		add(lblDepartment);
		
		lblPrice = new JLabel("Price");
		lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPrice.setBounds(80, 285, 100, 25);
		add(lblPrice);
		
		lblMinimalQuantity = new JLabel("Minimal quantity");
		lblMinimalQuantity.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMinimalQuantity.setBounds(80, 325, 100, 25);
		add(lblMinimalQuantity);
		
		text = new JTextField();
		text.setBounds(245, 125, 250, 25);
		add(text);
		text.setColumns(10);
		
		textField = new JTextField();
		textField.setBounds(245, 165, 250, 25);
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(245, 205, 250, 25);
		add(textField_1);
		textField_1.setColumns(10);
		
		comboBox = new JComboBox();
		comboBox.setBounds(245, 245, 250, 25);
		add(comboBox);
		
		textField_2 = new JTextField();
		textField_2.setBounds(245, 285, 250, 25);
		add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(245, 325, 250, 25);
		add(textField_3);
		textField_3.setColumns(10);
		
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
