package info2413_PFMS;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JPanel;

public class ContainerPanel extends JPanel {
	private final static CardLayout cl = new CardLayout();
	private LoginPanel loginPanel;
	private RegisterPanel registerPanel;
	private HomePanel homePanel;
	private EditBudgetPanel editBudgetPanel;
	private InventoryPanel inventoryPanel;
	private NewInventoryPanel newInventoryPanel;
	private CategoriesPanel categoriesPanel;
	private NewItemPanel newItemPanel;
	private EditItemPanel editItemPanel;
	private User currUser = null;

	public ContainerPanel() throws Exception {
		loginPanel = new LoginPanel(this, cl);

		// Set Layout
		setLayout(cl);
		add(loginPanel, "1");
		cl.show(this, "1");
	}



	// ---- Handle Changing Panels ----
	// -- Login Page --
	public void handleLoginPanelLoginBtn(ActionEvent e) {
		// Make user set budget if there is no budget or at begininng of month
		if (currUser.getBudget(currUser.getUserId()) == 0 || App.isFirstDay(App.getToday())) {
			editBudgetPanel = new EditBudgetPanel(this, cl, currUser);
			add(editBudgetPanel, "9");
			cl.show(this, "9");
		} else {
			homePanel = new HomePanel(this, cl, currUser);
			add(homePanel, "3");
			cl.show(this, "3");
		}
	}
	
	// Show Register Panel
	public void handleLoginPanelRegisterBtn (ActionEvent e) {
		registerPanel = new RegisterPanel(this, cl);
		add(registerPanel, "2");
		cl.show(this, "2");
	}

	// -- Register Page --
	public void handleRegisterPanelBackBtn (ActionEvent e) {
		cl.show(this, "1");
	}
	
	// Edit Budget Page
	public void handleEditBudgetPanelBackBtn(ActionEvent e) {
		homePanel = new HomePanel(this, cl, currUser);
		add(homePanel, "3");
		cl.show(this, "3");
	}

	// -- Home Page --
	public void handleHomePanelLogoutBtn(ActionEvent e) {
		cl.show(this, "1");
	}
	
	public void handleHomePanelInventoryBtn(ActionEvent e, int inventorySelectedId) {
		// Check database for matching inventory
		inventoryPanel = new InventoryPanel(this, cl, inventorySelectedId);
		add(inventoryPanel, "4");
		cl.show(this, "4");
	}

	public void handleHomePanelNewInventoryBtn(ActionEvent e, User user) {
		newInventoryPanel = new NewInventoryPanel(this, cl, user);
		add(newInventoryPanel, "5");
		cl.show(this, "5");
	}
	
	public void handleHomePanelManageCategoriesBtn(ActionEvent e) {
		categoriesPanel = new CategoriesPanel(this, cl);
		add(categoriesPanel, "8");
		cl.show(this, "8");
	}
	
	public void handleHomePanelEditBudgetBtn(ActionEvent e) {
		editBudgetPanel = new EditBudgetPanel(this, cl, currUser);
		add(editBudgetPanel, "9");
		cl.show(this, "9");
	}
	
	// -- New Inventory Page --
	public void handleNewInventoryPanelBackBtn(ActionEvent e) {
		// Remake the home panel
		homePanel = new HomePanel(this, cl, currUser);
		add(homePanel, "3");
		cl.show(this, "3");
	}
	
	// -- Manage Categories Page --
	public void handleCategoriesPanelBackBtn(ActionEvent e) {
		homePanel = new HomePanel(this, cl, currUser);
		add(homePanel, "3");
		cl.show(this, "3");
	}

	// -- Inventory Page --
	public void handleInventoryPanelHomeBtn (ActionEvent e) {
		// Remake the home panel
		homePanel = new HomePanel(this, cl, currUser);
		add(homePanel, "3");
		cl.show(this, "3");
	}
	
	public void handleInventoryPanelAddItemBtn(ActionEvent e, int inventoryId) {
		newItemPanel = new NewItemPanel(this, cl, inventoryId);
		add(newItemPanel, "6");
		cl.show(this, "6");
	}
	
	public void handleInventoryPanelEditItemBtn(ActionEvent e, int inventoryId, int itemId) {
		editItemPanel = new EditItemPanel(this, cl, inventoryId, itemId);
		add(editItemPanel, "7");
		cl.show(this, "7");
	}


	// -- New Item Page --
	public void handleNewItemPanelBackBtn(ActionEvent e, int inventoryId) {
		// Check database for matching inventory
		inventoryPanel = new InventoryPanel(this, cl, inventoryId);
		add(inventoryPanel, "4");
		cl.show(this, "4");
	}


	// Set current user
	public void setCurrUser(User usr) {
		currUser = usr;
	}
	// Get current user
	public User getCurrUser() {
		return currUser;
	}
}