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

public class ReportOnSellersEffectivnessView extends Panel{
	private JDateChooser dateChooser_1;
	private JLabel lblEndDate;
	private JLabel lblNewLabel;
	private JDateChooser dateChooser;
	private JButton btnPrint;
	private JScrollPane scrollPane;
	private JComboBox comboBox;
	private JLabel lblDepartment;
	private JButton btnClose;
	private JLabel lblReportOn;
	private JSeparator separator;
	private JTable table;
	private DefaultTableModel model;
	private JButton btnReport;

	public ReportOnSellersEffectivnessView() {
		
		separator = new JSeparator();
		separator.setBounds(0, 60, 600, 2);
		add(separator);
		
		lblReportOn = new JLabel("Report on seller's effectivness");
		lblReportOn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblReportOn.setBounds(25, 15, 250, 25);
		add(lblReportOn);
		
		btnClose = new JButton("Close");
		btnClose.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnClose.setBounds(94, 381, 89, 30);
		add(btnClose);
		
		lblDepartment = new JLabel("Department");
		lblDepartment.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDepartment.setBounds(100, 75, 100, 25);
		add(lblDepartment);
		
		comboBox = new JComboBox();
		comboBox.setBounds(225, 75, 250, 25);
		add(comboBox);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(100, 233, 375, 151);
		add(scrollPane);
		
		model = new DefaultTableModel();
		table = new JTable(model);
		table.setEnabled(false);
		scrollPane.setViewportView(table);
		
		btnPrint = new JButton("Print");
		btnPrint.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnPrint.setBounds(450, 421, 100, 25);
		add(btnPrint);
		
		btnReport = new JButton("Report");
		btnReport.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnReport.setBounds(377, 384, 100, 25);
		add(btnReport);
		
		dateChooser = new JDateChooser();
		dateChooser.setBounds(225, 125, 202, 25);
		add(dateChooser);
		
		lblNewLabel = new JLabel("Start date");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(100, 125, 100, 25);
		add(lblNewLabel);
		
		lblEndDate = new JLabel("End date");
		lblEndDate.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEndDate.setBounds(100, 175, 100, 25);
		add(lblEndDate);
		
		dateChooser_1 = new JDateChooser();
		dateChooser_1.setBounds(225, 175, 202, 25);
		add(dateChooser_1);
		
		
	}

	public JButton getBtnPrint() {
		return btnPrint;
	}
	
	public JButton getBtnReport() {
		return btnReport;
	}

	public JButton getBtnClose() {
		return btnClose;
	}
	public void addListener(ActionListener l){
		btnPrint.addActionListener(l);
		btnClose.addActionListener(l);
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
	
	public JComboBox getComboBox() {
		return comboBox;
	}

	public JDateChooser getDateChooserEnd() {
		return dateChooser_1;
	}

	public void setDateChooserEnd(JDateChooser dateChooser_1) {
		this.dateChooser_1 = dateChooser_1;
	}

	public JDateChooser getDateChooserStart() {
		return dateChooser;
	}

	public void setDateChooserStart(JDateChooser dateChooser) {
		this.dateChooser = dateChooser;
	}
}

