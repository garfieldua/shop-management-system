package com.naukma.shop.view;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JScrollPane;


public class AllItemsView extends PanelWithLogOut {
	private JTable table;
	private JLabel lblAllItems;
	private JScrollPane scrollPaneAllItems;
	public AllItemsView() {
		getBtnLogOut().setLocation(500, 11);
		
		lblAllItems = new JLabel("All Items");
		lblAllItems.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAllItems.setBounds(24, 16, 100, 25);
		add(lblAllItems);
		
		scrollPaneAllItems = new JScrollPane();
		scrollPaneAllItems.setBounds(50, 100, 500, 350);
		add(scrollPaneAllItems);
		
		table = new JTable();
		scrollPaneAllItems.setViewportView(table);
	}

}
