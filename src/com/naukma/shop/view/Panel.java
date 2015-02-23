package com.naukma.shop.view;

	import java.awt.*;
	import java.awt.event.ActionListener;

	import javax.swing.*;

	import java.awt.event.ActionEvent;

	public class Panel extends JPanel {
	private JButton exitButton;
	private JLabel attentLbl;

	private String textToExitButton = "EXIT";

	public JLabel getAttentLbl() {
	return attentLbl;
	}

	public Panel() {

	setLayout(null);
	setBackground(new Color(205, 192, 176));

	exitButton = new JButton(textToExitButton);
	exitButton.addActionListener(new ActionListener() {
	@Override
	public void actionPerformed(ActionEvent arg0) {
	//here you should close connection
	System.exit(0);

	}
	});
	exitButton.setBounds(450, 511, 100, 40);
	add(exitButton);

	Component horizontalStrut = Box.createHorizontalStrut(20);
	horizontalStrut.setBounds(802, 511, -869, -35);
	add(horizontalStrut);

	JSeparator separator = new JSeparator();
	separator.setBounds(0, 480, 600, 20);
	add(separator);

	attentLbl = new JLabel("");
	attentLbl.setHorizontalAlignment(SwingConstants.CENTER);
	attentLbl.setForeground(Color.RED);
	attentLbl.setFont(new Font("Tahoma", Font.PLAIN, 22));
	attentLbl.setBounds(191, 511, 432, 40);
	add(attentLbl);
	}

	public JButton getExitButton() {
	return exitButton;
	}

	public void addListener(ActionListener l) {
	//exitButton.addActionListener(l);

	}
	}

