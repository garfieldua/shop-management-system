package com.naukma.shop.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterJob;
import java.util.Date;
import java.text.MessageFormat;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import com.naukma.shop.database.Dao;
import com.naukma.shop.database.DaoObjectException;
import com.naukma.shop.database.Objects.Department;
import com.naukma.shop.database.Objects.OrderedItem;
import com.naukma.shop.database.Objects.Product;
import com.naukma.shop.database.Objects.Supplier;
import com.naukma.shop.view.AddNewProductView;
import com.naukma.shop.view.AddNewSupplierView;
import com.naukma.shop.view.ManagerView;
import com.naukma.shop.view.OrderProductView;
import com.naukma.shop.view.ReportOnFinancialResultsView;
import com.naukma.shop.utils.PrintPreview;
import com.naukma.shop.utils.Strings;

public class ManagerController extends AbstractController {
	private ManagerView managerView;
	private OrderProductView orderProductView;
	private AddNewSupplierView addNewSupplierView;
	private AddNewProductView addNewProductView;
	private ReportOnFinancialResultsView reportOnFinancialResultsView;

	public ManagerController() {
		managerView = new ManagerView();
		orderProductView = new OrderProductView();
		addNewSupplierView = new AddNewSupplierView();
		addNewProductView = new AddNewProductView();
		reportOnFinancialResultsView = new ReportOnFinancialResultsView();
		
		managerView.getBtnOrderProducts().addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	MainController.getInstance().setPanel(orderProductView);
	        	prepareOrderProductView();
	        }
		});
		
		managerView.getBtnAddNewSupplier().addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	MainController.getInstance().setPanel(addNewSupplierView);
	        	prepareAddNewSupplierView();
	        }
		});
		
		managerView.getBtnAddNewProduct().addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	MainController.getInstance().setPanel(addNewProductView);
	        	prepareAddNewProductView();
	        }
		});
		
		managerView.getBtnReportOnFinancial().addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	MainController.getInstance().setPanel(reportOnFinancialResultsView);
	        	prepareReportOnFinancialResultsView();
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
	
	private void prepareAddNewSupplierView() {
		addNewSupplierView.getBtnBack().addActionListener(new ActionListener() {
			
	        public void actionPerformed(ActionEvent e) {
	        	MainController.getInstance().setPanel(managerView);
	        	purgeAddNewSupplierView();
	        }
		});
		
		addNewSupplierView.getBtnAdd().addActionListener(new ActionListener() {
			
	        public void actionPerformed(ActionEvent e) {
	        	String supplierName = addNewSupplierView.getTextFieldName().getText();
	        	String supplierPhone = addNewSupplierView.getTextFieldPhone().getText();
	        	
	        	Supplier supplier = new Supplier();
	        	supplier.name = supplierName;
	        	supplier.phone = supplierPhone;
	        	
	        	try {
					supplier.save();
				} catch (DaoObjectException e1) {
					e1.printStackTrace();
				}
	        	
	        	JOptionPane.showMessageDialog(addNewSupplierView,
		                   Strings.getProperty("SUPPLIER_ADDED"),
		                   Strings.getProperty("SUCCESS"),
		                   JOptionPane.INFORMATION_MESSAGE);
	        	
	        	purgeAddNewSupplierView();
	        }
		});
	}
	
	private void prepareAddNewProductView() {
		addNewProductView.getBtnBack().addActionListener(new ActionListener() {
			
	        public void actionPerformed(ActionEvent e) {
	        	MainController.getInstance().setPanel(managerView);
	        	purgeAddNewProductView();
	        	addNewProductView.getComboBox().removeAllItems();
	        }
		});
		
		addNewProductView.getBtnAdd().addActionListener(new ActionListener() {
			
	        public void actionPerformed(ActionEvent e) {
	        	String productTitle = addNewProductView.getTextTitle().getText();
	        	String productDescription = addNewProductView.getTextDescription().getText();
	        	String productOrigin = addNewProductView.getTextOrigin().getText();
	        	int productDepartmentId = ((Department)addNewProductView.getComboBox().getSelectedItem()).id;
	        	System.out.println(addNewProductView.getTextPrice().getText());
	        	float productPrice = Float.parseFloat(addNewProductView.getTextPrice().getText());
	        	int productMinQuantity = Integer.parseInt(addNewProductView.getTextMinimalQuantity().getText());
	        	
	        	Product product = new Product();
	        	product.title = productTitle;
	        	product.description = productDescription;
	        	product.origin = productOrigin;
	        	product.departmentId = productDepartmentId;
	        	product.price = productPrice;
	        	product.minAmount = productMinQuantity;
	        	
	        	try {
					product.save();
				} catch (DaoObjectException e1) {
					e1.printStackTrace();
				}
	        	
	        	JOptionPane.showMessageDialog(addNewSupplierView,
		                   Strings.getProperty("PRODUCT_ADDED"),
		                   Strings.getProperty("SUCCESS"),
		                   JOptionPane.INFORMATION_MESSAGE);
	        	
	        	purgeAddNewProductView();
	        }
		});
		
		// need to load all departments
		try {
			Vector<Department> departments = Dao.getInstance().find(new Department());
			for (int i = 0; i < departments.size(); ++i) {
				addNewProductView.getComboBox().addItem(departments.get(i));
			}
		} catch (DaoObjectException e) {
			System.out.println("Exception in ManagerController::prepareAddNewProductView" + e.getMessage());
		}
	}
	
	private void prepareReportOnFinancialResultsView() {
		reportOnFinancialResultsView.getBtnClose().addActionListener(new ActionListener() {
			
	        public void actionPerformed(ActionEvent e) {
	        	MainController.getInstance().setPanel(managerView);
	        	purgeReportOnFinancialResultsView();
	        }
		});
		
		reportOnFinancialResultsView.getBtnReport().addActionListener(new ActionListener() {
			
	        public void actionPerformed(ActionEvent e) {
	        	purgeReportOnFinancialResultsView();
	        	
	        	Date startDate = reportOnFinancialResultsView.getDateFrom().getDate();
	        	Date endDate = reportOnFinancialResultsView.getDateTo().getDate();
	        	
	        	java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
	        	java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());
	        	
	        	reportOnFinancialResultsView.getTextField().setText(
	        			"==== FINANCIAL REPORT ===="
	        			+ "\nDepartment: " + reportOnFinancialResultsView.getComboBox().getSelectedItem()
	        			+ "\nStart date: " + sqlStartDate
	        			+ "\nEnd date: " + sqlEndDate);
	        }
		});
		
		// need to load all departments
		try {
			Vector<Department> departments = Dao.getInstance().find(new Department());
			for (int i = 0; i < departments.size(); ++i) {
				reportOnFinancialResultsView.getComboBox().addItem(departments.get(i));
			}
		} catch (DaoObjectException e) {
			System.out.println("Exception in ManagerController::prepareReportOnFinancialResultsView" + e.getMessage());
		}
	}
	
	private void purgeOrderProductView() {
		orderProductView.getComboBox().removeAllItems();
		orderProductView.getTextField().setText("");
		orderProductView.getModelOrder().setColumnCount(0);
		orderProductView.getModelOrder().setRowCount(0);
	}
	
	private void purgeAddNewProductView() {
		addNewProductView.getTextTitle().setText("");
		addNewProductView.getTextDescription().setText("");
		addNewProductView.getTextOrigin().setText("");
		addNewProductView.getTextPrice().setText("");
		addNewProductView.getTextMinimalQuantity().setText("");
	}
	
	private void purgeAddNewSupplierView() {
		addNewSupplierView.getTextFieldName().setText("");
    	addNewSupplierView.getTextFieldPhone().setText("");
	}
	
	private void purgeReportOnFinancialResultsView() {
		reportOnFinancialResultsView.getTextField().setText("");
	}
	
	@Override
	public void assignToMainContainer() {
		MainController.getInstance().setPanel(managerView);
		MainController.getInstance().addLogOutButtonListener(managerView);
	}
	
}
