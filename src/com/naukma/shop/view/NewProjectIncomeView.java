package com.naukma.shop.view;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;

public class NewProjectIncomeView extends PanelWithLogOut{
	
	
	private JTextField textField;
	private JLabel lblItem;
	private JLabel lblSuppliers;
	private JLabel lblQuantity;
	private JLabel lblNewProductIncome;
	private JButton btnAdd;
	private JComboBox comboBoxItem;
	private JComboBox comboBoxSuppliers;
	
	public NewProjectIncomeView() {
		getBtnLogOut().setLocation(500, 15);
		
		comboBoxItem = new JComboBox();
		comboBoxItem.setBounds(175, 150, 327, 30);
		add(comboBoxItem);
		
		comboBoxSuppliers = new JComboBox();
		comboBoxSuppliers.setBounds(175, 220, 325, 30);
		add(comboBoxSuppliers);
		
		textField = new JTextField();
		textField.setBounds(175, 290, 325, 30);
		add(textField);
		textField.setColumns(10);
		
		lblItem = new JLabel("Item:");
		lblItem.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblItem.setBounds(100, 144, 75, 40);
		add(lblItem);
		
		lblSuppliers = new JLabel("Suppliers:");
		lblSuppliers.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSuppliers.setBounds(100, 209, 75, 50);
		add(lblSuppliers);
		
		lblQuantity = new JLabel("Quantity:");
		lblQuantity.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblQuantity.setBounds(100, 279, 75, 50);
		add(lblQuantity);
		
		lblNewProductIncome = new JLabel("New Product Income:");
		lblNewProductIncome.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewProductIncome.setBounds(10, 5, 200, 50);
		add(lblNewProductIncome);
		
		btnAdd = new JButton("Add");
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAdd.setBounds(250, 385, 100, 30);
		add(btnAdd);
	}
	public JButton getBtnAdd() {
		return btnAdd;
	}
	public JComboBox getComboBoxItem() {
		return comboBoxItem;
	}
	public JComboBox getComboBoxSuppliers() {
		return comboBoxSuppliers;
	}
	public void addListener(ActionListener l) {
		btnAdd.addActionListener(l);

	}
}
