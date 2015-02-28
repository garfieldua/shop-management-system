package com.naukma.shop.view;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainContainer extends JFrame {

	public MainContainer() {
		this.setSize(new Dimension(600, 600));
		this.setResizable(false);
		this.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width/2 - this.getSize().width/2, 
				Toolkit.getDefaultToolkit().getScreenSize().height/2 - this.getSize().height/2);
		this.setTitle("Shop management system");

	}

	public void showPanel(JPanel p) {
		this.getContentPane().removeAll();
		this.getContentPane().repaint();
		this.getContentPane().revalidate();

		this.getContentPane().add(p);

		this.getContentPane().repaint();
		this.getContentPane().revalidate();

	}

}