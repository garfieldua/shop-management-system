package com.naukma.shop.view;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
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
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;
import java.awt.event.ActionEvent;

public class ReportOnSalesOnMonthView extends Panel{
	private JLabel lblEndDate;
	private JLabel lblNewLabel;
	private JButton btnPrint;
	private JTextPane textPane;
	private JComboBox comboBox;
	private JLabel lblDepartment;
	private JComponent btnClose;
	private JLabel lblReportOn;
	private JSeparator separator;
	private JMonthChooser monthStartChooser;
	private JYearChooser yearStartChooser;
	private JYearChooser yearEndChooser;
	private JMonthChooser monthEndChooser;
	private JButton btnReport;

	public ReportOnSalesOnMonthView() {
		
		separator = new JSeparator();
		separator.setBounds(0, 60, 600, 2);
		add(separator);
		
		lblReportOn = new JLabel("Report on sales on month ");
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
		
		textPane = new JTextPane();
		textPane.setBounds(100, 252, 375, 132);
		add(textPane);
		
		btnPrint = new JButton("Print");
		btnPrint.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnPrint.setBounds(375, 421, 100, 25);
		add(btnPrint);
		
		lblNewLabel = new JLabel("Start date");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(100, 125, 100, 25);
		add(lblNewLabel);
		
		lblEndDate = new JLabel("End date");
		lblEndDate.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEndDate.setBounds(100, 175, 100, 25);
		add(lblEndDate);
		
		monthStartChooser = new JMonthChooser();
		monthStartChooser.setBounds(225, 130, 100, 25);
		add(monthStartChooser);
		
		yearStartChooser = new JYearChooser();
		yearStartChooser.setBounds(350, 130, 75, 25);
		add(yearStartChooser);
		
		yearEndChooser = new JYearChooser();
		yearEndChooser.setBounds(350, 175, 75, 25);
		add(yearEndChooser);
		
		monthEndChooser = new JMonthChooser();
		monthEndChooser.setBounds(225, 175, 100, 25);
		add(monthEndChooser);
		
		btnReport = new JButton("Get report");
		btnReport.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnReport.setBounds(375, 216, 100, 25);
		add(btnReport);
	}

	public JButton getBtnPrint() {
		return btnPrint;
	}

	public JComponent getBtnClose() {
		return btnClose;
	}

	public JMonthChooser getMonthStartChooser() {
		return monthStartChooser;
	}

	public JYearChooser getYearStartChooser() {
		return yearStartChooser;
	}

	public JYearChooser getYearEndChooser() {
		return yearEndChooser;
	}

	public JMonthChooser getMonthEndChooser() {
		return monthEndChooser;
	}

	public JButton getBtnReport() {
		return btnReport;
	}
}

