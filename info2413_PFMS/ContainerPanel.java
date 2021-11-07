package info2413_PFMS;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.LinkedList;

import javax.swing.JPanel;

public class ContainerPanel extends JPanel {
	private final static CardLayout cl = new CardLayout();
	private LoginPanel loginPanel;
	private RegisterPanel registerPanel;
	private HomePanel homePanel;
	private InventoryPanel inventoryPanel;
	private LinkedList<String> temp = new LinkedList<>();
	private String currInventory = "";
	
	public ContainerPanel() throws Exception {
		// Temp user inventories
		
		// Temporary Inventories
		temp.add("Oct 27, 2021");
		temp.add("Oct 26, 2021");
		temp.add("Oct 25, 2021");
		temp.add("Oct 24, 2021");
		temp.add("Oct 23, 2021");
		temp.add("Oct 22, 2021");
		temp.add("Oct 21, 2021");
		temp.add("Oct 19, 2021");
		temp.add("Oct 18, 2021");
		temp.add("Oct 12, 2021");
		temp.add("Oct 10, 2021");
		temp.add("Oct 1, 2021");
		temp.add("Oct 2, 2021");
		
		loginPanel = new LoginPanel(this, cl);
		registerPanel = new RegisterPanel(this, cl);
		homePanel = new HomePanel(this, cl, temp);
		inventoryPanel = new InventoryPanel(this, cl, "Test");
		
		
		// Set Layout
		setLayout(cl);
		
		add(loginPanel, "1");
		add(registerPanel, "2");
		add(homePanel, "3");
		add(inventoryPanel, "4");
		cl.show(this, "1");
	}
	
	
	
	// ---- Handle Changing Panels ----
	// -- Login Page --
	public void handleLoginPanelLoginBtn(ActionEvent e) {
		cl.show(this, "3");
	}
	// Show Register Panel
	public void handleLoginPanelRegisterBtn (ActionEvent e) {
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
	public void handleHomePanelInventoryBtn(ActionEvent e,String inventorySelectedId, String inventorySelected) throws IOException, URISyntaxException {
		// Check database for matching inventory
		for (int i = 0; i < temp.size(); i ++) {
			if (inventorySelected.equals(temp.get(i))) {
				System.out.println(inventorySelectedId + ": " + inventorySelected);
				currInventory = inventorySelected;
				inventoryPanel = new InventoryPanel(this, cl, currInventory);
				add(inventoryPanel, "4");
				cl.show(this, "4");
			}
		}
	}
	
	
	// -- Inventory Page --
	public void handleInventoryPanelHomeBtn (ActionEvent e) {
		cl.show(this, "3");
	}
}
