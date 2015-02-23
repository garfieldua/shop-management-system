package com.naukma.shop.view;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class SuppliesView extends PanelWithLogOut{
	private JTable table;
	private JScrollPane scrollPaneSuppliersView;
	private JLabel lblSuppliersView;
	public SuppliesView() {
		
		lblSuppliersView = new JLabel("Suppliers View");
		lblSuppliersView.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSuppliersView.setBounds(10, 5, 200, 50);
		add(lblSuppliersView);
		
		scrollPaneSuppliersView = new JScrollPane();
		scrollPaneSuppliersView.setBounds(50, 100, 500, 350);
		add(scrollPaneSuppliersView);
		
		table = new JTable();
		scrollPaneSuppliersView.setViewportView(table);
	}
}
