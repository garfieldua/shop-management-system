package com.naukma.shop.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterJob;
import java.text.MessageFormat;
import java.util.Vector;

import javax.swing.JButton;

import com.naukma.shop.database.DaoObjectException;
import com.naukma.shop.database.Objects.OrderedItem;
import com.naukma.shop.database.Objects.Product;
import com.naukma.shop.database.Objects.Supplier;
import com.naukma.shop.view.ManagerView;
import com.naukma.shop.view.OrderProductView;
import com.naukma.shop.utils.PrintPreview;

public class ManagerController extends AbstractController {
	private ManagerView managerView;
	private OrderProductView orderProductView;

	public ManagerController() {
		managerView = new ManagerView();
		orderProductView = new OrderProductView();
		
		managerView.getBtnOrderProducts().addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	MainController.getInstance().setPanel(orderProductView);
	        	prepareOrderProductView();
	        }
		});
	}
	
	private void prepareOrderProductView() {
		orderProductView.getBtnBack().addActionListener(new ActionListener() {
			
	        public void actionPerformed(ActionEvent e) {
	        	MainController.getInstance().setPanel(managerView);
	        	purgeOrderProductView();
	        }
		});
		
		
		orderProductView.getModelOrder().addColumn("Article");
		orderProductView.getModelOrder().addColumn("Product");
		orderProductView.getModelOrder().addColumn("Quantity");
		
		orderProductView.getTable().getColumnModel().getColumn(0).setPreferredWidth(20);
		orderProductView.getTable().getColumnModel().getColumn(2).setPreferredWidth(20);
		orderProductView.getTable().getColumnModel().getColumn(1).setPreferredWidth(250);
		
		orderProductView.getBtnAdd().addActionListener(new ActionListener() {
			
	        public void actionPerformed(ActionEvent e) {
	        	Product product = (Product)orderProductView.getComboBox().getSelectedItem();
	        	int quantity = Integer.parseInt(orderProductView.getTextField().getText());
	        	
	        	System.out.println(product.id);
	        	System.out.println(quantity);
	        	
	        	// filling order table
	        	orderProductView.getModelOrder().addRow(new Object[]{product.id, product.title, quantity});
	        	
	        	// performing order
	        	OrderedItem order = new OrderedItem(product.id, quantity);
	        	try {
					order.save();
				} catch (DaoObjectException e1) {
					e1.printStackTrace();
				}
	        }
		});
		
		orderProductView.getBtnPrint().addActionListener(new ActionListener() {
			
	        public void actionPerformed(ActionEvent e) {
	        	javax.print.attribute.HashPrintRequestAttributeSet att = new javax.print.attribute.HashPrintRequestAttributeSet();
		           PrinterJob pj = PrinterJob.getPrinterJob();
		           new PrintPreview(orderProductView.getTable().getPrintable(javax.swing.JTable.PrintMode.FIT_WIDTH, new MessageFormat("Orders"), new MessageFormat("Orders" + " Page {0}")), pj.getPageFormat(att));
	        }
		});
	
		Vector<Product> toOrderProducts = Product.getWithLittleQuantity();
		
		for (Product product: toOrderProducts) {
			orderProductView.getComboBox().addItem(product);
		}
	}
	
	private void purgeOrderProductView() {
		orderProductView.getComboBox().removeAllItems();
		orderProductView.getTextField().setText("");
		orderProductView.getModelOrder().setColumnCount(0);
		orderProductView.getModelOrder().setRowCount(0);
	}
	
	@Override
	public void assignToMainContainer() {
		MainController.getInstance().setPanel(managerView);
		MainController.getInstance().addLogOutButtonListener(managerView);
	}
	
}
