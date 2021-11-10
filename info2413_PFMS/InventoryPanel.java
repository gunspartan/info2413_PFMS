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
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


// TODO sorting
public class InventoryPanel extends JPanel {
	private final ContainerPanel parentPanel;
	private final CardLayout cl;
	private final int inventoryId;
	private final JScrollPane inventoryScrollPane;
	private final JPanel inventoryPanel;
	private ArrayList<String[]> groceryItems;

	public InventoryPanel(ContainerPanel parentPanel, CardLayout cl, int inventoryId) {
		// Get items from database
		groceryItems = GroceryItem.getGroceryItems(inventoryId);
		this.parentPanel = parentPanel;
		this.cl = cl;
		this.inventoryId = inventoryId;
		// Name of the inventory
		String inventoryName = GroceryInventory.getGroceryInventoryById(inventoryId)[1];

		// Set size	
		Dimension size = getPreferredSize();
		size.width = 250;
		setPreferredSize(size);

		// Initialize scroll pane
		inventoryScrollPane = new JScrollPane();
		// Initialize a panel for the scroll pane
		inventoryPanel = new JPanel();

		// Labels
		JLabel titleLabel = new JLabel(inventoryName);
		JLabel inventoryLabel = new JLabel("My Items");

		// Items table
		JLabel imgLabel = new JLabel("Image");
		JLabel nameLabel = new JLabel("Name");
		JLabel priceLabel = new JLabel("Price");
		JLabel expiryLabel = new JLabel("Expiry Date");
		JLabel shopDateLabel = new JLabel("Purchase Date");
		JLabel qtyLabel = new JLabel("Quantity");
		JLabel expiredLabel = new JLabel("Expired");
		JLabel editLabel = new JLabel("Edit");

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

		// Add Label
		gc.gridx = 1;
		gc.gridy = 0;
		add(inventoryLabel, gc);

		// Add Button
		gc.gridx = 2;
		gc.gridy = 0;
		add(homeBtn, gc);

		// Add Labels for list

		listConstraints.anchor = GridBagConstraints.CENTER;
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
		inventoryPanel.add(priceLabel, listConstraints);

		listConstraints.gridx = 3;
		listConstraints.gridy = 0;
		inventoryPanel.add(expiryLabel, listConstraints);

		listConstraints.gridx = 4;
		listConstraints.gridy = 0;
		inventoryPanel.add(shopDateLabel, listConstraints);

		listConstraints.gridx = 5;
		listConstraints.gridy = 0;
		inventoryPanel.add(qtyLabel, listConstraints);

		listConstraints.gridx = 6;
		listConstraints.gridy = 0;
		inventoryPanel.add(expiredLabel, listConstraints);

		listConstraints.gridx = 7;
		listConstraints.gridy = 0;
		inventoryPanel.add(editLabel, listConstraints);

		listConstraints.ipady = 5;

		// Get inventories from database
		if (groceryItems == null) {
			JLabel emptyGroceryItemsLabel = new JLabel("You have no items");
			listConstraints.gridx = 0;
			listConstraints.gridy = 2;
			inventoryPanel.add(emptyGroceryItemsLabel,listConstraints);
		} else {
			for (int i = 0; i < groceryItems.size(); i++) {

				// Create Label for each item
				JLabel itemId = new JLabel((groceryItems.get(i)[0]));

				// Get image file
				JLabel img;
				String uri = groceryItems.get(i)[1];
				// Load image
				BufferedImage image;
				try {
					image = ImageIO.read(new File(getClass().getResource(uri).toURI()));
					// Resize image
					Image newImg = image.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
					img = new JLabel(new ImageIcon(newImg));
				} catch (Exception e) {
					System.out.print(e);
					img = new JLabel("Img not found");
				}

				JLabel name = new JLabel(groceryItems.get(i)[2]);
				JLabel price = new JLabel(groceryItems.get(i)[3]);
				JLabel expiry = new JLabel(groceryItems.get(i)[4]);
				JLabel shopDate = new JLabel(groceryItems.get(i)[5]);
				JLabel qty = new JLabel(groceryItems.get(i)[6]);
				JLabel expired = new JLabel(groceryItems.get(i)[7].equals("0") ? "No" : "Yes");
				// Create a button for each item
				JButton editBtn = new JButton("EDIT");
				// Add Action Listener for each button
				editBtn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {

						System.out.println("Pressed editBtn with ID of: " + itemId.getText());

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
				inventoryPanel.add(price, listConstraints);

				listConstraints.gridx = 3;
				listConstraints.gridy = i + 2;
				inventoryPanel.add(expiry, listConstraints);

				listConstraints.gridx = 4;
				listConstraints.gridy = i + 2;
				inventoryPanel.add(shopDate, listConstraints);

				listConstraints.gridx = 5;
				listConstraints.gridy = i + 2;
				inventoryPanel.add(qty, listConstraints);

				listConstraints.gridx = 6;
				listConstraints.gridy = i + 2;
				inventoryPanel.add(expired, listConstraints);

				listConstraints.gridx = 7;
				listConstraints.gridy = i + 2;
				inventoryPanel.add(editBtn, listConstraints);
			}
		}


		// Layout for scroll pane
		gc.anchor = GridBagConstraints.PAGE_START;
		inventoryScrollPane.add(inventoryPanel);
		inventoryScrollPane.setViewportView(inventoryPanel);
		gc.ipady = 450;
		gc.ipadx = 750;
		gc.gridwidth = 3;
		gc.gridx = 0;
		gc.gridy = 2;
		add(inventoryScrollPane, gc);
	}
}

