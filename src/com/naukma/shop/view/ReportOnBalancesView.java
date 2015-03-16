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

public class ReportOnBalancesView extends Panel{
	private JButton btnPrint;
	private JScrollPane scrollPane;
	private JComboBox comboBox;
	private JLabel lblDepartment;
	private JButton btnClose;
	private JLabel lblReportOn;
	private JSeparator separator;

	public ReportOnBalancesView() {
		
		separator = new JSeparator();
		separator.setBounds(0, 60, 600, 2);
		add(separator);
		
		lblReportOn = new JLabel("Report on balances of products");
		lblReportOn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblReportOn.setBounds(25, 15, 250, 25);
		add(lblReportOn);
		
		btnClose = new JButton("Close");
		btnClose.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnClose.setBounds(500, 15, 89, 30);
		add(btnClose);
		
		lblDepartment = new JLabel("Department");
		lblDepartment.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDepartment.setBounds(100, 130, 100, 25);
		add(lblDepartment);
		
		comboBox = new JComboBox();
		comboBox.setBounds(230, 130, 250, 25);
		add(comboBox);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(100, 180, 380, 204);
		add(scrollPane);
		
		btnPrint = new JButton("Print");
		btnPrint.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnPrint.setBounds(450, 421, 100, 25);
		add(btnPrint);
	}

	public JButton getBtnPrint() {
		return btnPrint;
	}

	public JButton getBtnClose() {
		return btnClose;
	}
	public void addListener(ActionListener l){
		btnPrint.addActionListener(l);
		btnClose.addActionListener(l);
	}
	}

