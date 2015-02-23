package com.naukma.shop.view;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class ManagerView extends PanelWithLogOut{
	private JTextField textField;
	private JTable table;
	private JLabel lblManager;
	private JLabel lblItem;
	private JComboBox comboBox;
	private JLabel lblQuantity;
	private JButton btnOk;
	private JScrollPane scrollPane;
	public ManagerView() {
		getBtnLogOut().setLocation(500, 11);
		
		lblManager = new JLabel("Manager");
		lblManager.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblManager.setBounds(26, 16, 73, 25);
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
		
		btnOk = new JButton("Ok");
		btnOk.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnOk.setBounds(436, 213, 64, 25);
		add(btnOk);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(100, 263, 400, 191);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}
	
	

}
