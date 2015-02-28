package com.naukma.shop.controller;

import com.naukma.shop.view.StorekeeperView;

public class StorekeeperController extends AbstractController {
	private StorekeeperView view; 

	public StorekeeperController() {
		view = new StorekeeperView();
	}
	
	@Override
	public void assignToMainContrainer() {
		MainController.getInstance().setPanel(view);
		MainController.getInstance().addLogOutButtonListener(view);
	}
	
}
