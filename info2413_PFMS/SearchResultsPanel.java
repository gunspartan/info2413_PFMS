package info2413_PFMS;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class SearchResultsPanel extends JPanel {
	private JScrollPane inventoryScrollPane;
	private JPanel inventoryPanel;
	private ArrayList<String[]> searchResults;
	public SearchResultsPanel (ContainerPanel parentPanel, CardLayout cl, String searchParams) {
		// Get results from search
		searchResults = GroceryInventory.searchInventories(searchParams, parentPanel.getCurrUser().getUserId());
		// Set size	
		Dimension size = getPreferredSize();
		size.width = 250;
		setPreferredSize(size);

		// Initialize scroll pane
		inventoryScrollPane = new JScrollPane();
		// Initialize a panel for the scroll pane
		inventoryPanel = new JPanel();

		// Labels
		JLabel titleLabel = new JLabel("Results for " + searchParams + ":");

		// Items Labels
		JLabel imgLabel = new JLabel("Image");
		JLabel nameLabel = new JLabel("Name");
		JLabel categoryLabel = new JLabel("Category");
		JLabel priceLabel = new JLabel("Price");
		JLabel expiryLabel = new JLabel("Expiry Date");
		JLabel shopDateLabel = new JLabel("Shopping Date");
		JLabel qtyLabel = new JLabel("# Remaining");
		JLabel consumedLabel = new JLabel("# Consumed");
		JLabel expiredLabel = new JLabel("Expired");

		// Back to Home Button
		JButton homeBtn = new JButton("Home");
		homeBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				parentPanel.handleInventoryPanelHomeBtn(e);
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


		// Add title
		gc.anchor = GridBagConstraints.CENTER;
		gc.weightx = 0.25;
		gc.weighty = 0.25;
		add(titleLabel, gc);


		// Add Button
		gc.gridx = 1;
		gc.gridy = 0;
		add(homeBtn, gc);

		// Add Labels for list

		listConstraints.anchor = GridBagConstraints.PAGE_START;
		listConstraints.ipady = 10;
		listConstraints.ipadx = 15;
		listConstraints.gridx = 0;
		listConstraints.gridy = 0;
		inventoryPanel.add(imgLabel, listConstraints);

		listConstraints.gridx = 1;
		listConstraints.gridy = 0;
		inventoryPanel.add(nameLabel, listConstraints);

		listConstraints.gridx = 2;
		listConstraints.gridy = 0;
		inventoryPanel.add(categoryLabel, listConstraints);


		listConstraints.gridx = 3;
		listConstraints.gridy = 0;
		inventoryPanel.add(priceLabel, listConstraints);

		listConstraints.gridx = 4;
		listConstraints.gridy = 0;
		inventoryPanel.add(expiryLabel, listConstraints);
		
		listConstraints.gridx = 5;
		listConstraints.gridy = 0;
		inventoryPanel.add(shopDateLabel, listConstraints);

		listConstraints.gridx = 6;
		listConstraints.gridy = 0;
		inventoryPanel.add(qtyLabel, listConstraints);

		listConstraints.gridx = 7;
		listConstraints.gridy = 0;
		inventoryPanel.add(consumedLabel, listConstraints);

		listConstraints.gridx = 8;
		listConstraints.gridy = 0;
		inventoryPanel.add(expiredLabel, listConstraints);


		listConstraints.ipady = 5;

		// Get inventories from database
		if (searchResults == null || searchResults.size() == 0) {
			JLabel emptyGroceryItemsLabel = new JLabel("No items found.");
			listConstraints.gridx = 0;
			listConstraints.gridy = 2;
			inventoryPanel.add(emptyGroceryItemsLabel,listConstraints);
		} else {
			for (int i = 0; i < searchResults.size(); i++) {

				// Create Label for each item
				int inventoryId = Integer.parseInt(searchResults.get(i)[0]);

				// Get image file
				JLabel img;
				String uri = searchResults.get(i)[1];
				// Load image
				BufferedImage image;
				try {
					image = ImageIO.read(new File(uri));
					// Resize image
					Image newImg = image.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
					img = new JLabel(new ImageIcon(newImg));
				} catch (Exception e) {
					System.out.print(e);
					img = new JLabel("Img not found");
				}
				JLabel name = new JLabel(searchResults.get(i)[2]);
				JLabel category = new JLabel(searchResults.get(i)[3]);
				JLabel price = new JLabel(searchResults.get(i)[4]);
				JLabel expiry = new JLabel(searchResults.get(i)[5]);
				JLabel shopDate = new JLabel(searchResults.get(i)[6]);
				int itemId = Integer.parseInt(searchResults.get(i)[10]);
				int remaining = GroceryItem.getRemaining(itemId);
				JLabel qty = new JLabel(Integer.toString(remaining));
				JLabel numConsumed = new JLabel(searchResults.get(i)[8]);
				JLabel expired = new JLabel(searchResults.get(i)[9].equals("0") ? "No" : "Yes");
				// Create a button for each item
				JButton goBtn = new JButton("GO");
				// Add Action Listener for each button
				goBtn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						parentPanel.handleHomePanelInventoryBtn(e, inventoryId);
					}

				});

				// Add Labels
				// Add image
				listConstraints.gridx = 0;
				listConstraints.gridy = i + 2;
				inventoryPanel.add(img, listConstraints);

				listConstraints.gridx = 1;
				listConstraints.gridy = i + 2;
				inventoryPanel.add(name, listConstraints);

				listConstraints.gridx = 2;
				listConstraints.gridy = i + 2;
				inventoryPanel.add(category, listConstraints);

				listConstraints.gridx = 3;
				listConstraints.gridy = i + 2;
				inventoryPanel.add(price, listConstraints);

				listConstraints.gridx = 4;
				listConstraints.gridy = i + 2;
				inventoryPanel.add(expiry, listConstraints);
				
				listConstraints.gridx = 5;
				listConstraints.gridy = i + 2;
				inventoryPanel.add(shopDate, listConstraints);

				listConstraints.gridx = 6;
				listConstraints.gridy = i + 2;
				inventoryPanel.add(qty, listConstraints);

				listConstraints.gridx = 7;
				listConstraints.gridy = i + 2;
				inventoryPanel.add(numConsumed, listConstraints);

				listConstraints.gridx = 8;
				listConstraints.gridy = i + 2;
				inventoryPanel.add(expired, listConstraints);
				
				listConstraints.gridx = 9;
				listConstraints.gridy = i + 2;
				inventoryPanel.add(goBtn, listConstraints);
			}
		}


		// Layout for scroll pane
		gc.anchor = GridBagConstraints.PAGE_START;
		inventoryScrollPane.add(inventoryPanel);
		inventoryScrollPane.setViewportView(inventoryPanel);
		gc.ipady = 450;
		gc.ipadx = 750;
		gc.gridwidth = 2;
		gc.gridx = 0;
		gc.gridy = 2;
		add(inventoryScrollPane, gc);
	}
}

