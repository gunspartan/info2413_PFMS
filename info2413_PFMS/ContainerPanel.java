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
	private SearchResultsPanel searchResultsPanel; 
	private ReportPanel reportPanel;
	private EditBudgetPanel editBudgetPanel;
	private InventoryPanel inventoryPanel;
	private EditInventoryPanel editInventoryPanel;
	private NewInventoryPanel newInventoryPanel;
	private CategoriesPanel categoriesPanel;
	private EditCategoriesPanel editCategoriesPanel;
	private NewCategoryPanel newCategoryPanel;
	private NewItemPanel newItemPanel;
	private EditItemPanel editItemPanel;
	private User currUser = null;

	public ContainerPanel() throws Exception {
		loginPanel = new LoginPanel(this, cl);

		// Set Layout
		setLayout(cl);
		
		// Initial Panel
		add(loginPanel, "login");
		cl.show(this, "login");
	}



	// ---- Handle Changing Panels ----
	
	// -- Login Page --
	public void handleLoginPanelLoginBtn(ActionEvent e) {
		// Make user set budget if there is no budget or at begininng of month
		if (currUser.getBudget(currUser.getUserId()) == 0 || App.isFirstDay(App.getToday())) {
			editBudgetPanel = new EditBudgetPanel(this, cl, currUser);
			add(editBudgetPanel, "editBudget");
			cl.show(this, "editBudget");
		} else {
			homePanel = new HomePanel(this, cl, currUser);
			add(homePanel, "home");
			cl.show(this, "home");
		}
		GroceryItem.checkQuantity();
		GroceryItem.checkExpiryDate();
	}
	
	public void handleLoginPanelRegisterBtn (ActionEvent e) {
		registerPanel = new RegisterPanel(this, cl);
		add(registerPanel, "register");
		cl.show(this, "register");
	}

	// -- Register Page --
	public void handleRegisterPanelBackBtn (ActionEvent e) {
		cl.show(this, "login");
	}
	
	// -- Edit Budget Page --
	public void handleEditBudgetPanelBackBtn(ActionEvent e) {
		homePanel = new HomePanel(this, cl, currUser);
		add(homePanel, "home");
		cl.show(this, "home");
	}

	// -- Home Page --
	public void handleHomePanelLogoutBtn(ActionEvent e) {
		cl.show(this, "login");
	}
	
	public void handleHomePanelSearchBtn(ActionEvent e, String params) {
		searchResultsPanel = new SearchResultsPanel(this, cl, params);
		add(searchResultsPanel, "searchResults");
		cl.show(this, "searchResults");
	}
	
	public void handleHomePanelCreateReportBtn(ActionEvent e, User user) {
		reportPanel = new ReportPanel(this, cl, user);
		add(reportPanel, "report");
		cl.show(this, "report");
	}
	
	public void handleHomePanelInventoryBtn(ActionEvent e, int inventorySelectedId) {
		inventoryPanel = new InventoryPanel(this, cl, inventorySelectedId);
		add(inventoryPanel, "inventory");
		cl.show(this, "inventory");
	}

	public void handleHomePanelNewInventoryBtn(ActionEvent e, User user) {
		newInventoryPanel = new NewInventoryPanel(this, cl, user);
		add(newInventoryPanel, "newInventory");
		cl.show(this, "newInventory");
	}
	
	public void handleHomePanelManageCategoriesBtn(ActionEvent e) {
		categoriesPanel = new CategoriesPanel(this, cl);
		add(categoriesPanel, "categories");
		cl.show(this, "categories");
	}
	
	public void handleHomePanelEditBudgetBtn(ActionEvent e) {
		editBudgetPanel = new EditBudgetPanel(this, cl, currUser);
		add(editBudgetPanel, "editBudget");
		cl.show(this, "editBudget");
	}
	
	// -- New Inventory Page --
	public void handleNewInventoryPanelBackBtn(ActionEvent e) {
		homePanel = new HomePanel(this, cl, currUser);
		add(homePanel, "home");
		cl.show(this, "home");
	}
	
	// -- Manage Categories Page --
	public void handleCategoriesPanelBackBtn(ActionEvent e) {
		homePanel = new HomePanel(this, cl, currUser);
		add(homePanel, "home");
		cl.show(this, "home");
	}
	public void handleCategoriesPanelEditBtn(ActionEvent e, int categoryId) {
		editCategoriesPanel = new EditCategoriesPanel(this, cl, categoryId);
		add(editCategoriesPanel, "editCategories");
		cl.show(this, "editCategories");
	}
	
	public void handleCategoriesPanelNewCategoryBtn(ActionEvent e) {
		newCategoryPanel = new NewCategoryPanel(this, cl);
		add(newCategoryPanel, "newCategory");
		cl.show(this, "newCategory");
	}

	// -- Edit/New Categories Page --
	public void handleEditCategoriesPanelBackBtn(ActionEvent e) {
		categoriesPanel = new CategoriesPanel(this, cl);
		add(categoriesPanel, "categories");
		cl.show(this, "categories");
	}
	
	// -- Inventory Page --
	public void handleInventoryPanelHomeBtn (ActionEvent e) {
		// Recreate the home panel
		homePanel = new HomePanel(this, cl, currUser);
		add(homePanel, "home");
		cl.show(this, "home");
	}
	
	public void handleInventoryPanelEditInventoryBtn(ActionEvent e, int inventoryId) {
		editInventoryPanel = new EditInventoryPanel(this, cl, inventoryId);
		add(editInventoryPanel, "editInventory");
		cl.show(this, "editInventory");
	}
	
	public void handleInventoryPanelAddItemBtn(ActionEvent e, int inventoryId) {
		newItemPanel = new NewItemPanel(this, cl, inventoryId);
		add(newItemPanel, "newItem");
		cl.show(this, "newItem");
	}
	
	public void handleInventoryPanelEditItemBtn(ActionEvent e, int inventoryId, int itemId) {
		editItemPanel = new EditItemPanel(this, cl, inventoryId, itemId);
		add(editItemPanel, "editItem");
		cl.show(this, "editItem");
	}

	// -- Edit Inventory Page --
	public void handleEditInventoryPanelBackBtn(ActionEvent e) {
		// Recreate the home panel
		homePanel = new HomePanel(this, cl, currUser);
		add(homePanel, "home");
		cl.show(this, "home");
	}
	
	// -- New Item Page --
	public void handleNewItemPanelBackBtn(ActionEvent e, int inventoryId) {
		inventoryPanel = new InventoryPanel(this, cl, inventoryId);
		add(inventoryPanel, "inventory");
		cl.show(this, "inventory");
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