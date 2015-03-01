package com.naukma.shop.controller;

import com.naukma.shop.database.Dao;
import com.naukma.shop.database.DaoObjectException;
import com.naukma.shop.database.MySQLProvider;
import com.naukma.shop.database.Objects.Employee;
import com.naukma.shop.utils.Strings;
import com.naukma.shop.view.LogInView;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;


// test user:
// login: niddhogg
// password: q1q2w1w2
// password(hashed): 1709d7085575655babfd199390ff32d0

public class LogInController extends AbstractController {
	private LogInView view; 
	
	public LogInController() {
		view = new LogInView();
		
	
		view.getLogInBtn().addActionListener(new ActionListener() {

	        public void actionPerformed(ActionEvent e)
	        {
	            Dao db = new Dao(new MySQLProvider());
	            
	            //String passHash = Dao.md5Custom("q1q2w1w2");
	            //System.out.println(passHash);
	            
				boolean result = db.checkLogin(view.getTextFieldLogin().getText(), new String(view.getTextFieldPswd().getPassword()));
				if (!result) {
					//Login error
					//System.out.println("Login error");
					view.getTextFieldLogin().setText("");
					view.getTextFieldPswd().setText("");
					
					JOptionPane.showMessageDialog(view,
			                   Strings.getProperty("LOGIN_FAILURE"),
			                   Strings.getProperty("ERROR"),
			                   JOptionPane.ERROR_MESSAGE);
				} else {
					//Login succeed
					System.out.println("Login succeed");
					
					try {
						Employee user = db.getEmployeeByName(view.getTextFieldLogin().getText());	
						MainController.getInstance().setCurrentUser(user);
						
						String position = user.position;
						if (position.equals("manager")) {
							MainController.getInstance().setCurrentController(new ManagerController());
						}
						else if (position.equals("storekeeper")) {
							MainController.getInstance().setCurrentController(new StorekeeperController());
						}
						else if (position.equals("seller")) {
							MainController.getInstance().setCurrentController(new SellerController());
						}
						
					} catch (DaoObjectException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
	        }
	    }); 
	}
	
	@Override
	public void assignToMainContainer() {
		MainController.getInstance().setPanel(view);
	}
}
