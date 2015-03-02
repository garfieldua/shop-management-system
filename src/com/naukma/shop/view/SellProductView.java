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

public class SellProductView extends PanelWithLogOut {
 

	private JLabel lblSuppliers;
	private JLabel lblItem;
	private JComboBox comboBox;
	private JTextPane textPaneItemsInfo;


	private JLabel lblQuantity;
	private JButton btnOk;
	private JButton btnAllItems;
	private JTextField textFieldQuantity;

	public SellProductView() {
		getBtnLogOut().setLocation(500, 11);
		
		lblSuppliers = new JLabel("Sell Products");
		lblSuppliers.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSuppliers.setBounds(25, 15, 89, 30);
		add(lblSuppliers);
		
		lblItem = new JLabel("Item:");
		lblItem.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblItem.setBounds(109, 129, 55, 25);
		add(lblItem);
		
		comboBox = new JComboBox();
		comboBox.setBounds(225, 130, 266, 25);
		add(comboBox);
		
		textPaneItemsInfo = new JTextPane();
		textPaneItemsInfo.setBounds(109, 191, 382, 125);
		textPaneItemsInfo.setEditable(false);
		add(textPaneItemsInfo);
		
		
		lblQuantity = new JLabel("Quantity:");
		lblQuantity.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblQuantity.setBounds(109, 380, 55, 16);
		add(lblQuantity);
		
		btnOk = new JButton("Ok");
		btnOk.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnOk.setBounds(425, 433, 66, 25);
		add(btnOk);
		
		btnAllItems = new JButton("All items");
		btnAllItems.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAllItems.setBounds(402, 82, 89, 25);
		add(btnAllItems);
		
		textFieldQuantity = new JTextField();
		textFieldQuantity.setBounds(225, 379, 266, 25);
		add(textFieldQuantity);
		textFieldQuantity.setColumns(10);
		
	
}	
	
	public void addListener(ActionListener l){
		this.getBtnLogOut().addActionListener(l);
		this.btnOk.addActionListener(l);
		this.btnAllItems.addActionListener(l);
	}

	public JComboBox getComboBox() {
		return comboBox;
	}

	public JButton getBtnOk() {
		return btnOk;
	}

	public JButton getBtnAllItems() {
		return btnAllItems;
	}

	public JLabel getLblItem() {
		return lblItem;
	}

	public JTextPane getTextPaneItemsInfo() {
		return textPaneItemsInfo;
	}

	public JLabel getLblQuantity() {
		return lblQuantity;
	}

	public JTextField getTextFieldQuantity() {
		return textFieldQuantity;
	}


}
