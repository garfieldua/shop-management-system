package com.naukma.shop.view;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

public class SuppliersView extends PanelWithLogOut{
	private JButton btnBack;
	private JTable tableSuppliers;
	private JScrollPane scrollPaneSuppliersView;
	private JLabel lblSuppliersView;
	private DefaultTableModel modelSuppliers;
	
	public SuppliersView() {
		
		lblSuppliersView = new JLabel("Suppliers View");
		lblSuppliersView.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSuppliersView.setBounds(10, 5, 200, 50);
		add(lblSuppliersView);
		
		scrollPaneSuppliersView = new JScrollPane();
		scrollPaneSuppliersView.setBounds(50, 100, 500, 330);
		add(scrollPaneSuppliersView);
		
		modelSuppliers = new DefaultTableModel();
		tableSuppliers = new JTable(modelSuppliers);
		tableSuppliers.setEnabled(false);
		scrollPaneSuppliersView.setViewportView(tableSuppliers);
		
		btnBack = new JButton("Back");
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnBack.setBounds(50, 441, 117, 29);
		add(btnBack);
	}
	
	public JButton getBtnBack() {
		return btnBack;
	}
	
	public JTable getTableSuppliers() {
		return tableSuppliers;
	}
	
	public DefaultTableModel getModelSuppliers() {
		return modelSuppliers;
	}
}
