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
	private JButton btnAddNewProduct;
	private JButton btnAddNewSupplier;
	private JButton btnReportOnSales;
	private JButton btnReportOnBalances;
	private JButton btnReportOnFinancial;
	private JButton btnReportOnSellers;

	public ManagerView() {
		lblManagerView = new JLabel("Manager View");
		lblManagerView.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblManagerView.setBounds(10, 5, 200, 50);
		add(lblManagerView);
		
		btnOrderProducts = new JButton("Order critical amount products");
		btnOrderProducts.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnOrderProducts.setBounds(175, 135, 250, 25);
		add(btnOrderProducts);
		
		btnAddNewProduct = new JButton("Add new product");
		btnAddNewProduct.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAddNewProduct.setBounds(175, 175, 250, 25);
		add(btnAddNewProduct);
		
		btnAddNewSupplier = new JButton("Add new supplier");
		btnAddNewSupplier.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAddNewSupplier.setBounds(175, 215, 250, 25);
		add(btnAddNewSupplier);
		
		btnReportOnSales = new JButton("Report on sales");
		btnReportOnSales.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnReportOnSales.setBounds(175, 255, 250, 25);
		add(btnReportOnSales);
		
		btnReportOnBalances = new JButton("Report on balances of products");
		btnReportOnBalances.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnReportOnBalances.setBounds(175, 295, 250, 25);
		add(btnReportOnBalances);
		
		btnReportOnFinancial = new JButton("Report on financial results");
		btnReportOnFinancial.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnReportOnFinancial.setBounds(175, 335, 250, 25);
		add(btnReportOnFinancial);
		
		btnReportOnSellers = new JButton("Report on seller's effectivness");
		btnReportOnSellers.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnReportOnSellers.setBounds(175, 375, 250, 25);
		add(btnReportOnSellers);
	}

	public JButton getBtnAddNewSupplier() {
		return btnAddNewSupplier;
	}

	public JButton getBtnReportOnSales() {
		return btnReportOnSales;
	}

	public JButton getBtnReportOnBalances() {
		return btnReportOnBalances;
	}

	public JButton getBtnReportOnFinancial() {
		return btnReportOnFinancial;
	}

	public JButton getBtnReportOnSellers() {
		return btnReportOnSellers;
	}

	public JButton getBtnAddNewProduct() {
		return btnAddNewProduct;
	}

	public JButton getBtnOrderProducts() {
		return btnOrderProducts;
	}
	
	public void addListener(ActionListener l){
		this.getBtnOrderProducts().addActionListener(l);
		this.getBtnAddNewProduct().addActionListener(l);
		this.getBtnReportOnSellers().addActionListener(l);
		this.getBtnReportOnFinancial().addActionListener(l);
		this.getBtnReportOnBalances().addActionListener(l);
		this.getBtnReportOnSales().addActionListener(l);
		this.getBtnAddNewSupplier().addActionListener(l);
	
	}
}


