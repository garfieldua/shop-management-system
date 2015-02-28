package com.naukma.shop.view;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AllItemsView extends PanelWithLogOut {
	private JTable table;
	private JLabel lblAllItems;
	private JScrollPane scrollPaneAllItems;
	private JButton btnBackButton;
	
	public AllItemsView() {
		getBtnLogOut().setLocation(500, 11);
		
		lblAllItems = new JLabel("All Items");
		lblAllItems.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAllItems.setBounds(24, 16, 100, 25);
		add(lblAllItems);
		
		scrollPaneAllItems = new JScrollPane();
		scrollPaneAllItems.setBounds(50, 100, 500, 312);
		add(scrollPaneAllItems);
		    
		table = new JTable();
		scrollPaneAllItems.setViewportView(table);
		
		btnBackButton = new JButton("Back");
		btnBackButton.setBounds(50, 431, 117, 29);
		add(btnBackButton);
	}
	
	public JTable getTable() {
		return table;
	}

	public JLabel getLblAllItems() {
		return lblAllItems;
	}

	public JScrollPane getScrollPaneAllItems() {
		return scrollPaneAllItems;
	}

	public JButton getBtnBackButton() {
		return btnBackButton;
	}
	
}
