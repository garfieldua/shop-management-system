package com.naukma.shop.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JOptionPane;

import com.naukma.shop.database.Dao;
import com.naukma.shop.database.DaoObject;
import com.naukma.shop.database.DaoObjectException;
import com.naukma.shop.database.Objects.Product;
import com.naukma.shop.database.Objects.Supplier;
import com.naukma.shop.utils.Strings;
import com.naukma.shop.view.NewProductIncomeView;
import com.naukma.shop.view.StorekeeperView;

public class StorekeeperController extends AbstractController {
	private StorekeeperView storekeeperView; 
	private NewProductIncomeView incomeView;

	public StorekeeperController() {
		storekeeperView = new StorekeeperView();
		incomeView = new NewProductIncomeView();
		
		// open view to register new product income
		storekeeperView.getBtnNewProductIncome().addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	MainController.getInstance().setPanel(incomeView);
	        	prepareIncomeView();
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
	        	
	        	// fixing product income in DB
	        	Dao.getInstance().fixAddProduct(productId, supplierId, quantity);
	        	
	        	// informing user that everything is OK
	        	JOptionPane.showMessageDialog(incomeView,
		                   Strings.getProperty("ITEM_ADDED_TO_WAREHOUSE"),
		                   Strings.getProperty("SUCCESS"),
		                   JOptionPane.INFORMATION_MESSAGE);
	        }
		});
		
		// need to load all products and suppliers
		try {
			Vector<Supplier> suppliers = DaoObject.all(new Supplier());
			for (int i = 0; i < suppliers.size(); ++i) {
				incomeView.getComboBoxSuppliers().addItem(suppliers.get(i));
			}
			
			Vector<Product> products = DaoObject.all(new Product());
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
}
