package info2413_PFMS;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JPanel;

public class ContainerPanel extends JPanel {
	private App app;
	private final static CardLayout cl = new CardLayout();
	private LoginPanel loginPanel;
	private RegisterPanel registerPanel;
	private HomePanel homePanel;
	private InventoryPanel inventoryPanel;
	private String currInventory = "";
	private User currUser = null;

	public ContainerPanel() throws Exception {
		this.app = app;

		loginPanel = new LoginPanel(this, cl);

		// Set Layout
		setLayout(cl);
		add(loginPanel, "1");
		cl.show(this, "1");
	}



	// ---- Handle Changing Panels ----
	// -- Login Page --
	public void handleLoginPanelLoginBtn(ActionEvent e) {
		homePanel = new HomePanel(this, cl, currUser);
		add(homePanel, "3");
		cl.show(this, "3");

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

	// -- Home Page --
	public void handleHomePanelLogoutBtn(ActionEvent e) {
		cl.show(this, "1");
	}
	

	public void handleHomePanelInventoryBtn(ActionEvent e, int inventorySelectedId) throws IOException, URISyntaxException {
		// Check database for matching inventory
		inventoryPanel = new InventoryPanel(this, cl, inventorySelectedId);
		add(inventoryPanel, "4");
		cl.show(this, "4");
		
	}


	// -- Inventory Page --
	public void handleInventoryPanelHomeBtn (ActionEvent e) {
		cl.show(this, "3");
	}



	// Set current user
	public void setCurrUser(User usr) {
		currUser = usr;
	}

	public User getCurrUser() {
		return currUser;
	}
}