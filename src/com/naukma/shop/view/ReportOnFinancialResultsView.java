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
import com.toedter.calendar.JDayChooser;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionEvent;

public class ReportOnFinancialResultsView extends PanelWithLogOut{
	private JButton btnPrint;
	private JLabel lblReportOn;
	private JButton btnClose;
	private JLabel lblDepartment;
	private JDateChooser dateTo;
	private JDateChooser dateFrom;
	private JTextPane textField;
	private JComboBox comboBox;
	private JLabel lblNewLabel;
	private JLabel lblEndDate;
	private JSeparator separator;
	private JButton btnReport;

	public ReportOnFinancialResultsView() {
		
		separator = new JSeparator();
		separator.setBounds(0, 60, 600, 2);
		add(separator);
		
		lblReportOn = new JLabel("Report on financial results");
		lblReportOn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblReportOn.setBounds(25, 15, 250, 25);
		add(lblReportOn);
		
		btnClose = new JButton("Close");
		btnClose.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnClose.setBounds(100, 421, 100, 25);
		add(btnClose);
		
		lblDepartment = new JLabel("Department");
		lblDepartment.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDepartment.setBounds(100, 75, 100, 25);
		add(lblDepartment);
		
		comboBox = new JComboBox();
		comboBox.setBounds(225, 75, 250, 25);
		add(comboBox);
		
		textField = new JTextPane();
		textField.setBounds(100, 241, 375, 143);
		textField.setEditable(false);
		add(textField);
		
		btnPrint = new JButton("Print");
		btnPrint.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnPrint.setBounds(375, 421, 100, 25);
		add(btnPrint);
		
		dateFrom = new JDateChooser();
		dateFrom.setBounds(225, 125, 100, 25);
		add(dateFrom);
		
		lblNewLabel = new JLabel("Start date");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(100, 125, 100, 25);
		add(lblNewLabel);
		
		lblEndDate = new JLabel("End date");
		lblEndDate.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEndDate.setBounds(100, 175, 100, 25);
		add(lblEndDate);
		
		dateTo = new JDateChooser();
		dateTo.setBounds(225, 175, 100, 25);
		add(dateTo);
		
		btnReport = new JButton("Get report");
		btnReport.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnReport.setBounds(375, 205, 100, 25);
		add(btnReport);
		
		
	}

	public JButton getBtnPrint() {
		return btnPrint;
	}

	public JButton getBtnClose() {
		return btnClose;
	}
	
	public JButton getBtnReport() {
		return btnReport;
	}

	public JDateChooser getDateTo() {
		return dateTo;
	}

	public JDateChooser getDateFrom() {
		return dateFrom;
	}

	public JTextPane getTextField() {
		return textField;
	}

	public JComboBox getComboBox() {
		return comboBox;
	}
}

