package com.naukma.shop.view;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class StorekeeperView extends PanelWithLogOut{
	private JLabel lblStorekeeperView;
	private JButton btnViewSuppliers;
	private JButton btnNewProductIncome;

	public StorekeeperView() {
		
		lblStorekeeperView = new JLabel("Storekeeper");
		lblStorekeeperView.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblStorekeeperView.setBounds(10, 0, 200, 50);
		add(lblStorekeeperView);
		
		btnViewSuppliers = new JButton("View Suppliers");
		btnViewSuppliers.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnViewSuppliers.setBounds(150, 200, 300, 50);
		add(btnViewSuppliers);
		
		btnNewProductIncome = new JButton("New product income");
		btnNewProductIncome.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewProductIncome.setBounds(150, 300, 300, 50);
		add(btnNewProductIncome);
	}

	public JButton getBtnViewSuppliers() {
		return btnViewSuppliers;
	}

	public JButton getBtnNewProductIncome() {
		return btnNewProductIncome;
	}
	
	public void addListener(ActionListener l) {
		btnViewSuppliers.addActionListener(l);
		btnNewProductIncome.addActionListener(l);

	}
	
}
