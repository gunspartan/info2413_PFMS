package info2413_PFMS;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

enum NotificationType {
	EXPIRING, EXPIRED, LOW_QTY, LOW_BUDGET;
}

public class Notification extends JFrame {
	private String title;
	private JFrame frame;
	private JPanel content = new JPanel();
	private JLabel contentLabel;
	private JScrollPane sp;
	private JPanel innerPanel;

	public Notification(NotificationType type, ArrayList<String[]> itemsArray) {
		// Set size
		Dimension size = getPreferredSize();
		size.width = 250;
		content.setPreferredSize(size);
		
		sp = new JScrollPane();
		innerPanel = new JPanel();
		
		// Labels
		JLabel inventoryLabel = new JLabel("Inventory: ");
		JLabel imgLabel = new JLabel("Image: ");
		JLabel foodNameLabel = new JLabel("Food Name");
		JLabel categoryLabel = new JLabel("Category: ");
		JLabel priceLabel = new JLabel("Price: ");
		JLabel expiryDateLabel = new JLabel("Expiry Date: ");
		JLabel qtyRemainingLabel = new JLabel("# Remaining: ");
		JLabel qtyConsumedLabel = new JLabel("# Consumed: ");
		
		switch (type) {
		case EXPIRING:
			title = "Expiring Items Warning";
			contentLabel = new JLabel("The following items are expiring: ");
			break;
		case EXPIRED:
			title = "Expired Items Warning";
			contentLabel = new JLabel("The following items have expired: ");
			break;
		case LOW_QTY:
			title = "Low Remaining Items Warning";
			contentLabel = new JLabel("You are running low on the following items: ");
			break;
		default:
			break;
		}
		
		// Frame
		frame = new JFrame(title);
		
		// Layout
		content.setLayout(new GridBagLayout());
		innerPanel.setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		GridBagConstraints gcInner = new GridBagConstraints();
		
		gc.anchor = GridBagConstraints.CENTER;
		gcInner.anchor = GridBagConstraints.CENTER;
		gc.weightx = 0.25;
		gc.weighty = 0.25;
		gcInner.weightx = 0.25;
		gcInner.weighty = 0.15;
		
		content.add(contentLabel, gc);
		innerPanel.add(inventoryLabel, gcInner);
		gcInner.gridx = 2;
		innerPanel.add(imgLabel, gcInner);
		gcInner.gridx = 3;
		innerPanel.add(foodNameLabel, gcInner);
		gcInner.gridx = 4;
		innerPanel.add(categoryLabel, gcInner);
		gcInner.gridx = 5;
		innerPanel.add(priceLabel, gcInner);
		gcInner.gridx = 6;
		innerPanel.add(expiryDateLabel, gcInner);
		gcInner.gridx = 7;
		innerPanel.add(qtyRemainingLabel, gcInner);
		gcInner.gridx = 8;
		innerPanel.add(qtyConsumedLabel, gcInner);
		
		for (int i = 0; i < itemsArray.size(); i++) {
			String inventoryName = GroceryInventory.getGroceryInventoryById(Integer.parseInt(itemsArray.get(i)[1]))[1];
			JLabel inventory = new JLabel(inventoryName);
			// Image
			JLabel img;
			String uri = itemsArray.get(i)[2];
			BufferedImage image;
			try {
				image = ImageIO.read(new File(uri));
				// Resize image
				Image newImg = image.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
				img = new JLabel(new ImageIcon(newImg));
			} catch (Exception e) {
				e.printStackTrace();
				img = new JLabel("Image not found");
			}
			JLabel foodName = new JLabel(itemsArray.get(i)[3]);
			JLabel category = new JLabel(itemsArray.get(i)[4]);
			JLabel price = new JLabel(itemsArray.get(i)[5]);
			JLabel expiryDate = new JLabel(itemsArray.get(i)[6]);
			int itemId = Integer.parseInt(itemsArray.get(i)[0]);
			int remaining = GroceryItem.getRemaining(itemId);
			JLabel qtyRemaining = new JLabel(Integer.toString(remaining));
			JLabel qtyConsumed = new JLabel(itemsArray.get(i)[8]);
			
			gcInner.gridy = i + 1;
			gcInner.gridx = 0;
			innerPanel.add(inventory, gcInner);
			gcInner.gridx = 2;
			innerPanel.add(img, gcInner);
			gcInner.gridx = 3;
			innerPanel.add(foodName, gcInner);
			gcInner.gridx = 4;
			innerPanel.add(category, gcInner);
			gcInner.gridx = 5;
			innerPanel.add(price, gcInner);
			gcInner.gridx = 6;
			innerPanel.add(expiryDate, gcInner);
			gcInner.gridx = 7;
			innerPanel.add(qtyRemaining, gcInner);
			gcInner.gridx = 8;
			innerPanel.add(qtyConsumed, gcInner);
		}
		gc.anchor = GridBagConstraints.PAGE_START;
		sp.add(innerPanel);
		sp.setViewportView(innerPanel);
		gc.ipady = 450;
		gc.ipadx = 750;
		gc.gridy = 1;
		content.add(sp, gc);
		frame.add(content);
		frame.setSize(800, 600);
		frame.setVisible(true);
	}
}
