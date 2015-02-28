package com.naukma.shop.controller;

import com.naukma.shop.view.ManagerView;

public class ManagerController extends AbstractController {
	private ManagerView view; 

	public ManagerController() {
		view = new ManagerView();
	}
	
	@Override
	public void assignToMainContainer() {
		MainController.getInstance().setPanel(view);
		MainController.getInstance().addLogOutButtonListener(view);
	}
	
}
