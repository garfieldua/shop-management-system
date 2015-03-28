package com.naukma.shop.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.Calendar;
import java.util.Date;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import com.naukma.shop.database.Dao;
import com.naukma.shop.database.DaoObjectException;
import com.naukma.shop.database.WhereClause;
import com.naukma.shop.database.Objects.Department;
import com.naukma.shop.database.Objects.Employee;
import com.naukma.shop.database.Objects.OrderedItem;
import com.naukma.shop.database.Objects.Product;
import com.naukma.shop.database.Objects.SoldItem;
import com.naukma.shop.database.Objects.Supplier;
import com.naukma.shop.database.Objects.extras.MonthSalesCount;
import com.naukma.shop.view.AddNewProductView;
import com.naukma.shop.view.AddNewSupplierView;
import com.naukma.shop.view.ManagerView;
import com.naukma.shop.view.OrderProductView;
import com.naukma.shop.view.ReportOnBalancesView;
import com.naukma.shop.view.ReportOnFinancialResultsView;
import com.naukma.shop.view.ReportOnSalesOnMonthView;
import com.naukma.shop.view.ReportOnSellersEffectivnessView;
import com.naukma.shop.utils.PrintPreview;
import com.naukma.shop.utils.Strings;

public class ManagerController extends AbstractController {
	private ManagerView managerView;
	private OrderProductView orderProductView;
	private AddNewSupplierView addNewSupplierView;
	private AddNewProductView addNewProductView;
	private ReportOnFinancialResultsView reportOnFinancialResultsView;
	private ReportOnSalesOnMonthView reportOnSalesOnMonthView;
	private ReportOnBalancesView reportOnBalancesView;
	private ReportOnSellersEffectivnessView reportOnSellersEffectivnessView;

	public ManagerController() {
		managerView = new ManagerView();
		orderProductView = new OrderProductView();
		addNewSupplierView = new AddNewSupplierView();
		addNewProductView = new AddNewProductView();
		reportOnFinancialResultsView = new ReportOnFinancialResultsView();
		reportOnSalesOnMonthView = new ReportOnSalesOnMonthView();
		reportOnBalancesView = new ReportOnBalancesView();
		reportOnSellersEffectivnessView = new ReportOnSellersEffectivnessView();
		
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
		
		managerView.getBtnReportOnSalesOnMonth().addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	MainController.getInstance().setPanel(reportOnSalesOnMonthView);
	        	prepareReportOnSalesOnMonthView();
	        }
		});
		
		managerView.getBtnReportOnBalances().addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	MainController.getInstance().setPanel(reportOnBalancesView);
	        	prepareReportOnBalancesView();
	        }
		});
		
		managerView.getBtnReportOnSellers().addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	MainController.getInstance().setPanel(reportOnSellersEffectivnessView);
	        	prepareReportOnSellersEffectivnessView();
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
	        	reportOnFinancialResultsView.getComboBox().removeAllItems();
	        	purgeReportOnFinancialResultsView();
	        }
		});
		
		reportOnFinancialResultsView.getBtnPrint().addActionListener(new ActionListener() {
			
	        public void actionPerformed(ActionEvent e) {
	        	try {
					reportOnFinancialResultsView.getTextField().print();
				} catch (PrinterException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	        }
		});
		
		reportOnFinancialResultsView.getBtnReport().addActionListener(new ActionListener() {
			
	        public void actionPerformed(ActionEvent e) {
	        	purgeReportOnFinancialResultsView();
	        	
	        	Date startDate = reportOnFinancialResultsView.getDateFrom().getDate();
	        	Date endDate = reportOnFinancialResultsView.getDateTo().getDate();
	        	
	        	Calendar startCal = Calendar.getInstance();
	        	startCal.setTime(startDate);
	        	startCal.set(Calendar.HOUR_OF_DAY, 0);
	        	startCal.set(Calendar.MINUTE, 0);
	        	startCal.set(Calendar.SECOND, 0);
	        	startCal.set(Calendar.MILLISECOND, 0);
	        	
	        	Calendar endCal = Calendar.getInstance();
	        	endCal.setTime(endDate);
	        	endCal.set(Calendar.HOUR_OF_DAY, 23);
	        	endCal.set(Calendar.MINUTE, 59);
	        	endCal.set(Calendar.SECOND, 59);
	        	endCal.set(Calendar.MILLISECOND, 0);
	        	
	        	long startDateMillis = startCal.getTimeInMillis();
	        	long endDateMillis = endCal.getTimeInMillis();
	        	
	        	SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm");
	        	
	        	float totalIncome = 0;
	        	int departmentId = ((Department)reportOnFinancialResultsView.getComboBox().getSelectedItem()).id;
	        	if (departmentId == -1) {
	        		totalIncome = SoldItem.getTotalIncomeByPeriodAll(startDateMillis, endDateMillis);
	        	}
	        	else {
	        		totalIncome = SoldItem.getTotalIncomeByPeriodDepartment(startDateMillis, endDateMillis, departmentId);
	        	}
	        	reportOnFinancialResultsView.getTextField().setText(
	        			"==== FINANCIAL REPORT ===="
	        			+ "\nDepartment: " + reportOnFinancialResultsView.getComboBox().getSelectedItem()
	        			+ "\nStart date: " + format.format(startCal.getTime())
	        			+ "\nEnd date: " + format.format(endCal.getTime())
	        			+ "\n\nTotal income: " + totalIncome + " USD");
	        }
		});
		
		// need to load all departments
		try {
			// adding fake department to represent whole shop
			Department allDepartments = new Department();
			allDepartments.id = -1;
			allDepartments.name = "ALL DEPARTMENTS";
			
			reportOnFinancialResultsView.getComboBox().addItem(allDepartments);
			
			Vector<Department> departments = Dao.getInstance().find(new Department());
			for (int i = 0; i < departments.size(); ++i) {
				reportOnFinancialResultsView.getComboBox().addItem(departments.get(i));
			}
		} catch (DaoObjectException e) {
			System.out.println("Exception in ManagerController::prepareReportOnFinancialResultsView" + e.getMessage());
		}
	}
	
	private void prepareReportOnSalesOnMonthView() {
		reportOnSalesOnMonthView.getBtnClose().addActionListener(new ActionListener() {
			
	        public void actionPerformed(ActionEvent e) {
	        	MainController.getInstance().setPanel(managerView);
	        	reportOnSalesOnMonthView.getComboBox().removeAllItems();
	        	purgeReportOnSalesOnMonthView();
	        }
		});
		
		reportOnSalesOnMonthView.getBtnPrint().addActionListener(new ActionListener() {
				
	        public void actionPerformed(ActionEvent e) {
	        	try {
	        		reportOnSalesOnMonthView.getTextPane().print();
				} catch (PrinterException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	        }
		});
		
		reportOnSalesOnMonthView.getBtnReport().addActionListener(new ActionListener() {
			
	        public void actionPerformed(ActionEvent e) {
	        	purgeReportOnSalesOnMonthView();
	        	
	        	int startYear = reportOnSalesOnMonthView.getYearStartChooser().getYear();
	        	int endYear = reportOnSalesOnMonthView.getYearEndChooser().getYear();
	        	
	        	int startMonth = reportOnSalesOnMonthView.getMonthStartChooser().getMonth();
	        	int endMonth = reportOnSalesOnMonthView.getMonthEndChooser().getMonth();
	        	
	        	Calendar startCal = Calendar.getInstance();
	        	startCal.set(Calendar.YEAR, startYear);
	        	startCal.set(Calendar.MONTH, startMonth);
	        	startCal.set(Calendar.DAY_OF_MONTH, 1);
	        	startCal.set(Calendar.HOUR_OF_DAY, 0);
	        	startCal.set(Calendar.MINUTE, 0);
	        	startCal.set(Calendar.SECOND, 0);
	        	startCal.set(Calendar.MILLISECOND, 0);
	        	
	        	Calendar endCal = Calendar.getInstance();
	        	endCal.set(Calendar.YEAR, endYear);
	        	endCal.set(Calendar.MONTH, endMonth);
	        	endCal.set(Calendar.DAY_OF_MONTH, endCal.getActualMaximum(Calendar.DAY_OF_MONTH));
	        	endCal.set(Calendar.HOUR_OF_DAY, 23);
	        	endCal.set(Calendar.MINUTE, 59);
	        	endCal.set(Calendar.SECOND, 59);
	        	endCal.set(Calendar.MILLISECOND, 0);
	        	
	        	long startDateMillis = startCal.getTimeInMillis();
	        	long endDateMillis = endCal.getTimeInMillis();
	        	
	        	SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm");
	        	
	        	Vector<MonthSalesCount> monthlySales = null;
	        	
	        	int departmentId = ((Department)reportOnSalesOnMonthView.getComboBox().getSelectedItem()).id;
	        	if (departmentId == -1) {
	        		monthlySales = MonthSalesCount.getMonthSalesCountByPeriodAll(startDateMillis, endDateMillis);
	        	}
	        	else {
	        		monthlySales = MonthSalesCount.getMonthSalesCountByPeriodDepartment(startDateMillis, endDateMillis, departmentId);
	        	}
	        	reportOnSalesOnMonthView.getTextPane().setText(
	        			"==== MONTHLY SALES REPORT ===="
	        			+ "\nDepartment: " + reportOnSalesOnMonthView.getComboBox().getSelectedItem()
	        			+ "\nStart date: " + format.format(startCal.getTime())
	        			+ "\nEnd date: " + format.format(endCal.getTime()) + "\n");
	        	
	        	for (MonthSalesCount msc:monthlySales) {
	        		String beforeContent = reportOnSalesOnMonthView.getTextPane().getText();
	        		reportOnSalesOnMonthView.getTextPane().setText(beforeContent + "\n"+msc.month + "\t" + msc.id + " USD");
	        	}
	        }
		});
		
		// need to load all departments
		try {
			// adding fake department to represent whole shop
			Department allDepartments = new Department();
			allDepartments.id = -1;
			allDepartments.name = "ALL DEPARTMENTS";
			
			reportOnSalesOnMonthView.getComboBox().addItem(allDepartments);
			
			Vector<Department> departments = Dao.getInstance().find(new Department());
			for (int i = 0; i < departments.size(); ++i) {
				reportOnSalesOnMonthView.getComboBox().addItem(departments.get(i));
			}
		} catch (DaoObjectException e) {
			System.out.println("Exception in ManagerController::prepareReportOnSalesOnMonthView" + e.getMessage());
		}
	}
	
	private void prepareReportOnBalancesView() {
		reportOnBalancesView.getBtnBack().addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	MainController.getInstance().setPanel(managerView);
	        	reportOnBalancesView.getComboBox().removeAllItems();
	        	purgeReportOnBalancesView();
	        	
	        	// remove action listener
	    	    for( ActionListener al : reportOnBalancesView.getBtnPrint().getActionListeners() ) {
	    	    	reportOnBalancesView.getBtnPrint().removeActionListener( al );
	    	    }
	        }
		});
		
		reportOnBalancesView.getBtnPrint().addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	try {
	        		reportOnBalancesView.getTable().print();
				} catch (PrinterException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	        }
		});
		
		
		
		reportOnBalancesView.getComboBox().addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		        // change
		    	final Department d = (Department)reportOnBalancesView.getComboBox().getSelectedItem();
		    	if (d != null) {
		    		// clear previous one
		    		purgeReportOnBalancesView();
		    		
		    		
		    		
		    		// build up new one
		    		reportOnBalancesView.getModel().addColumn("Title");
		    		reportOnBalancesView.getModel().addColumn("Quantity");
		    		reportOnBalancesView.getModel().addColumn("Min Amount");
					
					JTable table = reportOnBalancesView.getTable();
					table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					table.getColumnModel().getColumn(0).setPreferredWidth(350);
					table.getColumnModel().getColumn(1).setPreferredWidth(60);
					table.getColumnModel().getColumn(2).setPreferredWidth(70);
					
					Vector<Product> products = null;
					try {
						if (d.id != -1) {
							products = Dao.getInstance().Where(new WhereClause<Product>(){
								public boolean compare(Product row) {
									return row.departmentId == d.id;
								}
							}).find(new Product());
						}
						else {
							products = Dao.getInstance().find(new Product());
						}
						
						for (Product p: products) {
							reportOnBalancesView.getModel().addRow(new Object[]{p.title, p.quantity, p.minAmount} );
						}	
					} catch (DaoObjectException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}	
					

		    	}
		    }
		});
		
		// need to load all departments
		try {
			// adding fake department to represent whole shop
			Department allDepartments = new Department();
			allDepartments.id = -1;
			allDepartments.name = "ALL DEPARTMENTS";
			
			reportOnBalancesView.getComboBox().addItem(allDepartments);
			
			Vector<Department> departments = Dao.getInstance().find(new Department());
			for (int i = 0; i < departments.size(); ++i) {
				reportOnBalancesView.getComboBox().addItem(departments.get(i));
			}
		} catch (DaoObjectException e) {
			System.out.println("Exception in ManagerController::prepareReportOnBalancesView" + e.getMessage());
		}
		
	}
	
	
	// seller's effectiveness view
	private void prepareReportOnSellersEffectivnessView() {
		// back button
		reportOnSellersEffectivnessView.getBtnClose().addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	MainController.getInstance().setPanel(managerView);
	        	reportOnSellersEffectivnessView.getComboBox().removeAllItems();
	        	purgeSellersEffectivnessView();
	        	
	        	// remove action listener
	    	    for( ActionListener al : reportOnSellersEffectivnessView.getBtnPrint().getActionListeners() ) {
	    	    	reportOnSellersEffectivnessView.getBtnPrint().removeActionListener( al );
	    	    }
	    	    
	    	    for( ActionListener al : reportOnSellersEffectivnessView.getBtnReport().getActionListeners() ) {
	    	    	reportOnSellersEffectivnessView.getBtnReport().removeActionListener( al );
	    	    }
	        }
		});
		
		// print button
		reportOnSellersEffectivnessView.getBtnPrint().addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	try {
	        		reportOnSellersEffectivnessView.getTable().print();
				} catch (PrinterException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	        }
		});
		
		// report btn
		reportOnSellersEffectivnessView.getBtnReport().addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	
		    	
		        // change
		    	final Department d = (Department)reportOnSellersEffectivnessView.getComboBox().getSelectedItem();
		    	if (d != null) {
		    		// clear previous one
		    		purgeSellersEffectivnessView();
		    		
		    		
		    		// get dates
		    		Date date_start = reportOnSellersEffectivnessView.getDateChooserStart().getDate();
		    		Date date_end = reportOnSellersEffectivnessView.getDateChooserEnd().getDate();

		    		// build up calendar
		        	Calendar startCal = Calendar.getInstance();
		        	if (date_start != null) {
		        		startCal.setTime(date_start);
		        	}
		        	
		        	Calendar endCal = Calendar.getInstance();
		        	if (date_end != null) {
		        		endCal.setTime(date_end);
		        	}
		        	
		        	// get milliseconds
		        	long startDateMillisTemp = startCal.getTimeInMillis();
		        	long endDateMillisTemp = endCal.getTimeInMillis();
		        	
		        	// just to show something if end one is kinda wrong
		        	if (endDateMillisTemp == startDateMillisTemp) {
		        		endDateMillisTemp = endDateMillisTemp + 1000000000;
		        	}
		    		
		        	final long startDateMillis = startDateMillisTemp;
		        	final long endDateMillis = endDateMillisTemp;
		        	
		    		// build up new one
		        	reportOnSellersEffectivnessView.getModel().addColumn("Name");
		        	reportOnSellersEffectivnessView.getModel().addColumn("Quantity");
		        	reportOnSellersEffectivnessView.getModel().addColumn("Money");
					
					JTable table = reportOnSellersEffectivnessView.getTable();
					table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					table.getColumnModel().getColumn(0).setPreferredWidth(250);
					table.getColumnModel().getColumn(1).setPreferredWidth(60);
					table.getColumnModel().getColumn(2).setPreferredWidth(70);
					
					//System.out.println("LOLz");
					
					
					Vector<Employee> sellers = null;
					try {
						// get all sellers
						if (d.id != -1) {
							sellers = Dao.getInstance().Where(new WhereClause<Employee>(){
								public boolean compare(Employee row) {
									return row.departmentId == d.id ;
								}
							}).find(new Employee());
						}
						else {
							sellers = Dao.getInstance().find(new Employee());
						}
						
						//System.out.println(sellers.size());
						
						// we got sellers
						for (final Employee employee: sellers) {
							Vector<SoldItem> solditems = null;
							try {
								solditems = Dao.getInstance().Where(new WhereClause<SoldItem>(){
									public boolean compare(SoldItem row) {
										// do calendar funcs
										Calendar cal = Calendar.getInstance();
										cal.setTime(row.date);
										long curMillis = cal.getTimeInMillis(); 
										
										return row.supplierId == employee.id && 
												curMillis >= startDateMillis &&
												curMillis <= endDateMillis;
									}
								}).find(new SoldItem());
							
								String sellername = employee.firstName + " " + employee.lastName;
								int quantity = 0;
								int money = 0;
								
								for (SoldItem p: solditems) {
									quantity += p.quantity;
									money += p.totalPrice;
								}	
								
								if (quantity > 0) {
									reportOnSellersEffectivnessView.getModel().addRow(new Object[]{sellername, quantity, money} );
								}
							} catch (DaoObjectException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}	
						}	
						
						// end try 
					} catch (DaoObjectException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}	
					
					
					
					// end :)

		    	}
		    }
		});
		
		// load data for combobox
		// need to load all departments
		try {
			// adding fake department to represent whole shop
			Department allDepartments = new Department();
			allDepartments.id = -1;
			allDepartments.name = "ALL DEPARTMENTS";
			
			reportOnSellersEffectivnessView.getComboBox().addItem(allDepartments);
			
			Vector<Department> departments = Dao.getInstance().find(new Department());
			for (int i = 0; i < departments.size(); ++i) {
				reportOnSellersEffectivnessView.getComboBox().addItem(departments.get(i));
			}
		} catch (DaoObjectException e) {
			System.out.println("Exception in ManagerController::prepareReportOnBalancesView" + e.getMessage());
		}
	}
	
	private void purgeSellersEffectivnessView() {
		reportOnSellersEffectivnessView.getModel().setRowCount(0);
		reportOnSellersEffectivnessView.getModel().setColumnCount(0);
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
	
	private void purgeReportOnSalesOnMonthView() {
		reportOnSalesOnMonthView.getTextPane().setText("");
	}
	
	private void purgeReportOnBalancesView() {
		reportOnBalancesView.getModel().setRowCount(0);
		reportOnBalancesView.getModel().setColumnCount(0);
		//reportOnBalancesView.getTextPane().setText("");
		//reportOnBalancesView.getComboBox().removeAllItems();
	}
	
	@Override
	public void assignToMainContainer() {
		MainController.getInstance().setPanel(managerView);
		MainController.getInstance().addLogOutButtonListener(managerView);
	}
	
}
