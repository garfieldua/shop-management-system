package com.naukma.shop.controller;

import com.naukma.shop.view.NewProductIncomeView;
import com.naukma.shop.view.StorekeeperView;

public class StorekeeperController extends AbstractController {
	private StorekeeperView storekeeperView; 
	private NewProductIncomeView incomeView;

	public StorekeeperController() {
		storekeeperView = new StorekeeperView();
		incomeView = new NewProductIncomeView();
	}
	
	@Override
	public void assignToMainContainer() {
		MainController.getInstance().setPanel(storekeeperView);
		MainController.getInstance().addLogOutButtonListener(storekeeperView);
	}
	
}
