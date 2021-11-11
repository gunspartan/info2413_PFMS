package info2413_PFMS;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


// TODO User settings
// TODO Generate Reports
// TODO Show total spent

public class HomePanel extends JPanel {
	private final ContainerPanel parentPanel;
	private final CardLayout cl;
	private final JScrollPane inventoryScrollPane;
	private final JPanel inventoryPanel;
	private ArrayList<String[]> groceryInventories;
	private User currUser;
	
	public HomePanel(ContainerPanel parentPanel, CardLayout cl, User currUser) {
		this.currUser = currUser;
		// Get inventories from database
		groceryInventories = GroceryInventory.getGroceryInventories(currUser);
		this.parentPanel = parentPanel;
		this.cl = cl;
		
		// Set size	
		Dimension size = getPreferredSize();
		size.width = 250;
		setPreferredSize(size);

		// Initialize scroll pane
		inventoryScrollPane = new JScrollPane();
		// Initialize a panel for the scroll pane
		inventoryPanel = new JPanel();
				
		// Labels
		JLabel titleLabel = new JLabel("Home");
		JLabel inventoryLabel = new JLabel("My Inventories");
		JLabel shoppingDateLabel = new JLabel("Shopping Date:");
		JLabel totalSpentLabel = new JLabel("Total Spent: ");
		
		// Log out Button
		JButton logoutBtn = new JButton("Logout");
		logoutBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				parentPanel.handleHomePanelLogoutBtn(e);
			}
			
		});
		
		// New Inventory Button
		JButton newInventoryBtn = new JButton("New Inventory");
		newInventoryBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				parentPanel.handleHomePanelNewInventoryBtn(e, currUser);
			}
			
		});
		
		// Layout
		setLayout(new GridBagLayout());
		inventoryPanel.setLayout(new GridBagLayout());
		
		// Headings layout
		GridBagConstraints gc = new GridBagConstraints();

		// List layout
		GridBagConstraints listConstraints = new GridBagConstraints();
		listConstraints.anchor = GridBagConstraints.CENTER;
		listConstraints.weightx = 0.25;
		listConstraints.weighty = 0.15;
		

		// Add Title
		gc.anchor = GridBagConstraints.CENTER;
		gc.weightx = 0.25;
		gc.weighty = 0.25;
		add(titleLabel, gc);
		
		// Add Button
		gc.gridx = 2;
		gc.gridy = 0;
		add(newInventoryBtn, gc);
		
		gc.gridx = 3;
		gc.gridy = 0;
		add(logoutBtn, gc);
		
		// Add Label
		gc.gridx = 1;
		gc.gridy = 0;
		add(inventoryLabel, gc);
		
		listConstraints.anchor = GridBagConstraints.PAGE_START;
		listConstraints.gridx = 0;
		listConstraints.gridy = 0;
		inventoryPanel.add(shoppingDateLabel, listConstraints);
		listConstraints.gridx = 1;
		listConstraints.gridy = 0;
		inventoryPanel.add(totalSpentLabel, listConstraints);
		
		// Check if inventories are empty
		if (groceryInventories.isEmpty()) {
			JLabel emptyInventoryLabel = new JLabel("Your inventory is empty");
			// Add Label
			listConstraints.gridx = 0;
			listConstraints.gridy = 1;
			inventoryPanel.add(emptyInventoryLabel, listConstraints);
		} else {
			for (int i = 0; i < groceryInventories.size(); i++) {
				// Create Labels
				JLabel inventoryIdLabel = new JLabel(groceryInventories.get(i)[0]);
				JLabel dateLabel = new JLabel(groceryInventories.get(i)[1]);
				JLabel spendingLabel = new JLabel(groceryInventories.get(i)[2]);
				JButton inventoryBtn = new JButton("OPEN");
				inventoryBtn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						String inventorySelectedId = inventoryIdLabel.getText();
						try {
							parentPanel.handleHomePanelInventoryBtn(e, Integer.parseInt(inventorySelectedId));
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						
					}
				});
				// Add Labels
				listConstraints.gridx = 0;
				listConstraints.gridy = i + 1;
				inventoryPanel.add(dateLabel, listConstraints);
				listConstraints.gridx = 1;
				listConstraints.gridy = i + 1;
				inventoryPanel.add(spendingLabel, listConstraints);
				// Add Buttons
				listConstraints.gridx = 2;
				listConstraints.gridy = i + 1;
				inventoryPanel.add(inventoryBtn, listConstraints);
			}
		}
		
		// Layout for scroll pane
		gc.anchor = GridBagConstraints.PAGE_START;
		inventoryScrollPane.add(inventoryPanel);
		inventoryScrollPane.setViewportView(inventoryPanel);
		gc.ipady = 450;
		gc.ipadx = 750;
		gc.gridwidth = 4;
		gc.gridx = 0;
		gc.gridy = 2;
		add(inventoryScrollPane, gc);
	
	}
	
}
