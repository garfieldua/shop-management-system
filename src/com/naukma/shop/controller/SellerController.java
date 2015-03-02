package com.naukma.shop.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JTable;

import com.naukma.shop.database.Dao;
import com.naukma.shop.database.DaoObjectException;
import com.naukma.shop.database.Objects.Department;
import com.naukma.shop.database.Objects.Product;
import com.naukma.shop.view.AllItemsView;
import com.naukma.shop.view.SellProductView;
//import com.naukma.shop.view.StorekeeperView;

public class SellerController extends AbstractController {
	private SellProductView sellProductView; 
	private AllItemsView allItemsView;

	public SellerController() {
		sellProductView = new SellProductView();
		allItemsView = new AllItemsView();
		
		fillSellerView();
		
		// proceed to all items view
		sellProductView.getBtnAllItems().addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e)
	        {
	        	MainController.getInstance().setPanel(allItemsView);
	        	fillAllItemsView();
	        }
		});
		
		// back button from All Items
		allItemsView.getBtnBackButton().addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e)
	        {
	        	MainController.getInstance().setPanel(sellProductView);
	        	fillSellerView();
	        }
		});
	
	}
	
	@Override
	public void assignToMainContainer() {
		MainController.getInstance().setPanel(sellProductView);
		MainController.getInstance().addLogOutButtonListener(sellProductView);
	}
	
	public void fillAllItemsView() {
	 
		try {
			Vector<Product> products = Dao.getInstance().find(new Product());
			
			allItemsView.getModelAllItems().addColumn("Title");
			allItemsView.getModelAllItems().addColumn("Quantity");
			allItemsView.getModelAllItems().addColumn("Price");
			
			for (int i = 0; i < products.size(); ++i) {
				allItemsView.getModelAllItems().addRow(new Object[]{products.get(i).title, products.get(i).quantity, products.get(i).price} );
			}
			
		} catch (DaoObjectException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    //JTable table = allItemsView.getTable();
	    //table = //new JTable(headers, data);
	}
	
	public void fillSellerView() {
		
	}
	
}
