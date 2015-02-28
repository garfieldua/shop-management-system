package com.naukma.shop.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTable;

import com.naukma.shop.view.AllItemsView;
import com.naukma.shop.view.SellProductView;
//import com.naukma.shop.view.StorekeeperView;

public class SellerController extends AbstractController {
	private SellProductView sellProductView; 
	private AllItemsView allItemsView;

	public SellerController() {
		sellProductView = new SellProductView();
		allItemsView = new AllItemsView();
		
		// proceed to all items view
		sellProductView.getBtnAllItems().addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e)
	        {
	        	MainController.getInstance().setPanel(allItemsView);
	        }
		});
		fillSellerView();
		
		// back button from All Items
		allItemsView.getBtnBackButton().addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e)
	        {
	        	MainController.getInstance().setPanel(sellProductView);
	        }
		});
		fillAllItemsView();
	
	}
	
	@Override
	public void assignToMainContrainer() {
		MainController.getInstance().setPanel(sellProductView);
		MainController.getInstance().addLogOutButtonListener(sellProductView);
	}
	
	public void fillAllItemsView() {
	 
	    
	    //JTable table = allItemsView.getTable();
	    //table = //new JTable(headers, data);
	}
	
	public void fillSellerView() {
		
	}
	
}
