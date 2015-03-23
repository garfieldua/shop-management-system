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
import javax.swing.JSeparator;

public class ReportOnBalancesView extends PanelWithLogOut{
	private JButton btnPrint;
	private JTable table;
	private JComboBox comboBox;
	private JLabel lblDepartment;
	private JButton btnBack;
	private JLabel lblReportOn;
	private JSeparator separator;
	private DefaultTableModel model;
	private JScrollPane scrollPane;
	
	public ReportOnBalancesView() {
		
		separator = new JSeparator();
		separator.setBounds(0, 60, 600, 2);
		add(separator);
		
		lblReportOn = new JLabel("Report on balances of products");
		lblReportOn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblReportOn.setBounds(25, 15, 250, 25);
		add(lblReportOn);
		
		btnBack = new JButton("Back");
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnBack.setBounds(22, 52, 89, 30);
		add(btnBack);
		
		lblDepartment = new JLabel("Department");
		lblDepartment.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDepartment.setBounds(100, 130, 100, 25);
		add(lblDepartment);
		
		comboBox = new JComboBox();
		comboBox.setBounds(230, 130, 250, 25);
		add(comboBox);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(50, 190, 500, 222);
		add(scrollPane);
		    
		model = new DefaultTableModel();
		table = new JTable(model);
		table.setEnabled(false);
		scrollPane.setViewportView(table);
		
		btnPrint = new JButton("Print");
		btnPrint.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnPrint.setBounds(450, 421, 100, 25);
		add(btnPrint);
	}
	
	public JComboBox getComboBox() {
		return comboBox;
	}

	public JButton getBtnPrint() {
		return btnPrint;
	}

	public JButton getBtnBack() {
		return btnBack;
	}
	
	public JTable getTable() {
		return table;
	}

	public DefaultTableModel getModel() {
		return model;
	}

	public void setModel(DefaultTableModel model) {
		this.model = model;
	}
	
	}

