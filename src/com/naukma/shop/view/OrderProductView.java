package com.naukma.shop.view;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OrderProductView extends PanelWithLogOut{
	private JTextField textField;
	private JTable table;
	private JLabel lblManager;
	private JLabel lblItem;
	private JComboBox comboBox;
	private JLabel lblQuantity;
	private JButton btnAdd;
	private JButton btnBack;
	private JButton btnPrint;
	private JScrollPane scrollPane;
	private DefaultTableModel modelOrder;
	
	public OrderProductView() {
		getBtnLogOut().setLocation(500, 11);
		
		lblManager = new JLabel("Order product");
		lblManager.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblManager.setBounds(26, 16, 154, 25);
		add(lblManager);
		
		lblItem = new JLabel("Item");
		lblItem.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblItem.setBounds(100, 92, 56, 25);
		add(lblItem);
		
		comboBox = new JComboBox();
		comboBox.setBounds(250, 93, 250, 25);
		add(comboBox);
		
		textField = new JTextField();
		textField.setBounds(250, 150, 250, 25);
		add(textField);
		textField.setColumns(10);
		
		lblQuantity = new JLabel("Input quantity");
		lblQuantity.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblQuantity.setBounds(100, 147, 94, 25);
		add(lblQuantity);
		
		btnAdd = new JButton("Order");
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAdd.setBounds(412, 186, 89, 25);
		add(btnAdd);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(100, 222, 400, 212);
		add(scrollPane);
		
		modelOrder = new DefaultTableModel();
		table = new JTable(modelOrder);
		table.setEnabled(false);
		scrollPane.setViewportView(table);
		
		btnBack = new JButton("Back");
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnBack.setBounds(100, 445, 117, 29);
		add(btnBack);
		
		btnPrint = new JButton("Print order");
		btnPrint.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnPrint.setBounds(383, 445, 117, 29);
		add(btnPrint);
	}

	public JTextField getTextField() {
		return textField;
	}

	public JTable getTable() {
		return table;
	}

	public JComboBox getComboBox() {
		return comboBox;
	}

	public JButton getBtnAdd() {
		return btnAdd;
	}

	public JButton getBtnBack() {
		return btnBack;
	}
	
	public JButton getBtnPrint() {
		return btnPrint;
	}

	public DefaultTableModel getModelOrder() {
		return modelOrder;
	}
}
