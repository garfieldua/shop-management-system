package com.naukma.shop.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import com.naukma.shop.database.Dao;
import com.naukma.shop.database.DaoObjectException;
import com.naukma.shop.database.WhereClause;
import com.naukma.shop.database.Objects.Department;
import com.naukma.shop.database.Objects.Employee;
import com.naukma.shop.database.Objects.Product;
import com.naukma.shop.database.Objects.SoldItem;
import com.naukma.shop.database.Objects.SuppliedItem;
import com.naukma.shop.database.Objects.Supplier;
import com.naukma.shop.database.Objects.Warehouseitem;
import com.naukma.shop.utils.Strings;
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
	        	purgeSellerView();
	        }
		});
		
		// back button from All Items
		allItemsView.getBtnBackButton().addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e)
	        {
	        	MainController.getInstance().setPanel(sellProductView);
	        	fillSellerView();
	        	purgeAllItemsView();
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
			allItemsView.getModelAllItems().addColumn("Title");
			allItemsView.getModelAllItems().addColumn("Quantity");
			allItemsView.getModelAllItems().addColumn("Price");
			
			JTable table = allItemsView.getTable();
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			table.getColumnModel().getColumn(0).setPreferredWidth(350);
			table.getColumnModel().getColumn(1).setPreferredWidth(60);
			table.getColumnModel().getColumn(2).setPreferredWidth(70);
			
			Vector<Product> products = Dao.getInstance().Where(new WhereClause<Product>(){
				public boolean compare(Product row) {
					return row.departmentId == MainController.getInstance().getCurrentUser().departmentId;
				}
			}).find(new Product());	
			
			for (Product p: products) {
				allItemsView.getModelAllItems().addRow(new Object[]{p.title, p.quantity, p.price} );
			}			
			
		} catch (DaoObjectException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    //JTable table = allItemsView.getTable();
	    //table = //new JTable(headers, data);
	}
	
	public void fillSellerView() {
		
		
		sellProductView.getBtnOk().addActionListener(new ActionListener() {
			
	        public void actionPerformed(ActionEvent e) {
	        	
	        	int productId = ((Product)sellProductView.getComboBox().getSelectedItem()).id;
	        	
	        	try {
	        		int quantity = Integer.parseInt(sellProductView.getTextFieldQuantity().getText());
	        		
	        		Product p = new Product(productId);
	        		int warehouse_quantity = p.quantity;
	        		
	        		if (quantity <= warehouse_quantity) {
	        			// item count ok
	        			
	        			//stats
	        			SoldItem s = new SoldItem();
	        			s.quantity = quantity;
	        			s.productId = productId;
	        			s.supplierId = MainController.getInstance().getCurrentUser().id;
	        			// date!
	        			s.save();
	        			
	        			// ok, show dialog
	    	        	JOptionPane.showMessageDialog(sellProductView,
	 		                   Strings.getProperty("ITEM_SOLD"),
	 		                   Strings.getProperty("SUCCESS"),
	 		                   JOptionPane.INFORMATION_MESSAGE);
	    	        	
	    	        	// need to load all products and suppliers
	    	    		reloadProducts();
	    	        	
	    	        	
	
	        		} else {
	        			// quantity error
	        			JOptionPane.showMessageDialog(sellProductView,
				                   Strings.getProperty("SELL_QUANTITY_FAILURE"),
				                   Strings.getProperty("ERROR"),
				                   JOptionPane.ERROR_MESSAGE);
	        		}
	        		
	        	}
	        	catch (Exception exc) {
	        		exc.printStackTrace();
	        	}
	        }
		});
		
		sellProductView.getComboBox().addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		        // change
		    	Product p = (Product)sellProductView.getComboBox().getSelectedItem();
		    	if (p != null) {
		    		StringBuilder sb = new StringBuilder();
		    		
			    	String desc = p.description;
			    	int quanity = p.quantity;
			    	double price = p.price;
			    	
			    	sb.append("Desciption: ");
			    	sb.append(desc);
			    	sb.append("\n");
			    	
			    	sb.append("In Warehouse : ");
			    	sb.append(quanity);
			    	sb.append("\n");
			    	
			    	sb.append("Price: ");
			    	sb.append(price);
			    	
			    	sellProductView.getTextPaneItemsInfo().setText(sb.toString());
		    	}
		    }
		});
		
		// need to load all products and suppliers
		reloadProducts();
		
	}
	
	public void reloadProducts() {
		purgeSellerView();
		
		try {
			// get
			Vector<Product> products2 = Dao.getInstance().Where(new WhereClause<Product>(){
				public boolean compare(Product row) {
					return row.id == 1;
				}
			}).find(new Product());	
			
			System.out.println("reload " + products2.get(0).quantity);
			
			//
			
			Vector<Product> products = Dao.getInstance().Where(new WhereClause<Product>(){
				public boolean compare(Product row) {
					return row.departmentId == MainController.getInstance().getCurrentUser().departmentId;
				}
			}).find(new Product());	
			
			for (Product p: products) {
				sellProductView.getComboBox().addItem(p);
			}
		} catch (DaoObjectException e) {
			System.out.println("Exception in StorekeeperController::prepareIncomeView" + e.getMessage());
		}	
	}
	
	private void purgeAllItemsView() {
		allItemsView.getModelAllItems().setRowCount(0);
		allItemsView.getModelAllItems().setColumnCount(0);
	}
	
	private void purgeSellerView() {
		sellProductView.getComboBox().removeAllItems();
		sellProductView.getTextFieldQuantity().setText("");
		sellProductView.getLblQuantity().setText("");
		sellProductView.getTextPaneItemsInfo().setText("");
	
	}
	
}
