package com.naukma.shop.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JOptionPane;

import com.naukma.shop.database.Dao;
import com.naukma.shop.database.DaoObjectException;
import com.naukma.shop.database.Objects.Product;
import com.naukma.shop.database.Objects.SuppliedItem;
import com.naukma.shop.database.Objects.Supplier;
import com.naukma.shop.utils.Strings;
import com.naukma.shop.view.NewProductIncomeView;
import com.naukma.shop.view.StorekeeperView;
import com.naukma.shop.view.SuppliersView;

public class StorekeeperController extends AbstractController {
	private StorekeeperView storekeeperView; 
	private NewProductIncomeView incomeView;
	private SuppliersView suppliersView;

	public StorekeeperController() {
		storekeeperView = new StorekeeperView();
		incomeView = new NewProductIncomeView();
		suppliersView = new SuppliersView();
		
		// open view to register new product income
		storekeeperView.getBtnNewProductIncome().addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	MainController.getInstance().setPanel(incomeView);
	        	prepareIncomeView();
	        }
		});
		
		// open view to show all suppliers
		storekeeperView.getBtnViewSuppliers().addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	MainController.getInstance().setPanel(suppliersView);
	        	prepareSuppliersView();
	        }
		});
	}
	
	@Override
	public void assignToMainContainer() {
		MainController.getInstance().setPanel(storekeeperView);
		MainController.getInstance().addLogOutButtonListener(storekeeperView);
	}
	
	private void prepareIncomeView() {
		incomeView.getBtnBack().addActionListener(new ActionListener() {
			
	        public void actionPerformed(ActionEvent e) {
	        	MainController.getInstance().setPanel(storekeeperView);
	        	purgeIncomeView();
	        }
		});
		
		incomeView.getBtnAdd().addActionListener(new ActionListener() {
			
	        public void actionPerformed(ActionEvent e) {
	        	int productId = ((Product)incomeView.getComboBoxItem().getSelectedItem()).id;
	        	int supplierId = ((Supplier)incomeView.getComboBoxSuppliers().getSelectedItem()).id;
	        	int quantity = Integer.parseInt(incomeView.getTextFieldQuantity().getText());
	        	
	        	// we need to fixate product income in DB
	        	SuppliedItem si = new SuppliedItem(productId, supplierId, quantity);
	        	try {
					si.save();
				} catch (DaoObjectException e1) {
					e1.printStackTrace();
				}
	        	
	        	// informing user that everything is OK
	        	JOptionPane.showMessageDialog(incomeView,
		                   Strings.getProperty("ITEM_ADDED_TO_WAREHOUSE"),
		                   Strings.getProperty("SUCCESS"),
		                   JOptionPane.INFORMATION_MESSAGE);
	        }
		});
		
		// need to load all products and suppliers
		try {
			Vector<Supplier> suppliers = Dao.getInstance().find(new Supplier());
			for (int i = 0; i < suppliers.size(); ++i) {
				incomeView.getComboBoxSuppliers().addItem(suppliers.get(i));
			}
			
			Vector<Product> products = Dao.getInstance().find(new Product());
			for (int i = 0; i < products.size(); ++i) {
				incomeView.getComboBoxItem().addItem(products.get(i));
			}
		} catch (DaoObjectException e) {
			System.out.println("Exception in StorekeeperController::prepareIncomeView" + e.getMessage());
		}
	}
	
	private void purgeIncomeView() {
		incomeView.getComboBoxItem().removeAllItems();
		incomeView.getComboBoxSuppliers().removeAllItems();
		incomeView.getTextFieldQuantity().setText("");
	}
	
	private void prepareSuppliersView() {
		suppliersView.getBtnBack().addActionListener(new ActionListener() {
			
	        public void actionPerformed(ActionEvent e) {
	        	MainController.getInstance().setPanel(storekeeperView);
	        	purgeSuppliersView();
	        }
		});
		
		try {
			Vector<Supplier> suppliers = Dao.getInstance().getSuppliers();
			
			suppliersView.getModelSuppliers().addColumn("Name");
			suppliersView.getModelSuppliers().addColumn("Phone");
			
			for (int i = 0; i < suppliers.size(); ++i) {
				suppliersView.getModelSuppliers().addRow(new Object[]{suppliers.get(i).name, suppliers.get(i).phone});
			}
			
		} catch (DaoObjectException e1) {
			System.out.println("Exception in StorekeeperController::prepareSuppliersView" + e1.getMessage());
		}
	}
	
	private void purgeSuppliersView() {
		suppliersView.getModelSuppliers().setRowCount(0);
		suppliersView.getModelSuppliers().setColumnCount(0);
	}
	
}
