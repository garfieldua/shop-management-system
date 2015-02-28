package com.naukma.shop.controller;

import com.naukma.shop.view.SellProductView;
import com.naukma.shop.view.StorekeeperView;

public class SellerController extends AbstractController {
	private SellProductView view; 

	public SellerController() {
		view = new SellProductView();
	}
	
	@Override
	public void assignToMainContrainer() {
		MainController.getInstance().setPanel(view);
		MainController.getInstance().addLogOutButtonListener(view);
	}
	
}
