package info2413_PFMS;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ReportPanel extends JPanel {
	public ReportPanel(ContainerPanel parentPanel, CardLayout cl, User user) {
		JScrollPane reportSP = new JScrollPane();
		JPanel reportPanel = new JPanel();
		// Total Spending
		float monthSpending = User.checkAllSpentMonth(user.getUserId(), App.getToday());
		// Spending per category
		ArrayList<Object[]> spendingPerCategory = Report.spendingPerCategory(App.getToday());
		JPanel spendingPerCategoryPanel = new JPanel();
		// Top 5 Consumed
		ArrayList<Object[]> mostConsumed = Report.mostConsumed(App.getToday());
		JPanel mostConsumedPanel = new JPanel();
		// Top 5 Wasted
		ArrayList<Object[]> mostExpired = Report.mostExpired(App.getToday());
		JPanel mostExpiredPanel = new JPanel();

		// Set size 
		Dimension size = getPreferredSize();
		size.width = 250;
		setPreferredSize(size);

		// Labels
		JLabel titleLabel = new JLabel( App.getToday() + " Report");
		JLabel spendingLabel = new JLabel("You spent $" + String.format("%.2f", monthSpending) + " this month.");
		JLabel spendingPerCategoryLabel = new JLabel("Spending Per Category");
		JLabel mostComsumedLabel = new JLabel("Top 5 Consumed");
		JLabel mostExpiredLabel = new JLabel("Top 5 Wasted");
		JLabel noItemsLabel = new JLabel("No items found");

		// Layout
		setLayout(new GridBagLayout());
		reportPanel.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		gc.anchor = GridBagConstraints.LINE_START;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.weightx = 0.25;
		gc.weighty = 0.25;
		// Add Labels
		gc.gridx = 0;
		reportPanel.add(titleLabel, gc);
		gc.gridy = 1;
		reportPanel.add(spendingLabel, gc);
		
		// Category Spending Layout
		gc.gridy = 2;
		reportPanel.add(spendingPerCategoryLabel, gc);
		
		spendingPerCategoryPanel.setLayout(new GridBagLayout());
		GridBagConstraints spendingGC = new GridBagConstraints();
		spendingGC.ipadx = 25;
		spendingGC.ipady = 10;
		spendingGC.fill = GridBagConstraints.HORIZONTAL;
		spendingGC.anchor = GridBagConstraints.CENTER;
		if (spendingPerCategory.isEmpty()) {
			spendingPerCategoryPanel.add(noItemsLabel, spendingGC);
		} else {
			for (int i = 0; i < spendingPerCategory.size(); i++) {
				JLabel categoryName = new JLabel((String) spendingPerCategory.get(i)[1]);
				JLabel amountSpent = new JLabel("$" + spendingPerCategory.get(i)[2].toString());
				spendingGC.gridy = i;
				spendingGC.gridx = 0;
				spendingPerCategoryPanel.add(categoryName, spendingGC);
				spendingGC.gridx = 1;
				spendingPerCategoryPanel.add(amountSpent, spendingGC);
			}
		}
		
		gc.gridy = 3;
		reportPanel.add(spendingPerCategoryPanel, gc);
		
		// Top 5 Consumed Layout
		gc.gridy = 4;
		reportPanel.add(mostComsumedLabel, gc);
		
		mostConsumedPanel.setLayout(new GridBagLayout());
		GridBagConstraints consumedGC = new GridBagConstraints();
		consumedGC.ipadx = 25;
		consumedGC.ipady = 10;
		consumedGC.fill = GridBagConstraints.HORIZONTAL;
		consumedGC.anchor = GridBagConstraints.CENTER;
		JLabel imgLabel = new JLabel("Image");
		JLabel nameLabel = new JLabel("Name");
		JLabel categoryLabel = new JLabel("Category");
		JLabel priceLabel = new JLabel("Price");
		JLabel expiryLabel = new JLabel("Expiry Date");
		JLabel shopDateLabel = new JLabel("Shopping Date");
		JLabel qtyLabel = new JLabel("# Remaining");
		JLabel consumedLabel = new JLabel("# Consumed");
		JLabel expiredLabel = new JLabel("Expired");
		JLabel imgLabel1 = new JLabel("Image");
		JLabel nameLabel1 = new JLabel("Name");
		JLabel categoryLabel1 = new JLabel("Category");
		JLabel priceLabel1 = new JLabel("Price");
		JLabel expiryLabel1 = new JLabel("Expiry Date");
		JLabel shopDateLabel1 = new JLabel("Shopping Date");
		JLabel expiredLabel1 = new JLabel("Expired");
		consumedGC.gridx = 0;
		consumedGC.gridy = 0;
		mostConsumedPanel.add(imgLabel, consumedGC);
		consumedGC.gridx = 1;
		mostConsumedPanel.add(nameLabel, consumedGC);
		consumedGC.gridx = 2;
		mostConsumedPanel.add(categoryLabel, consumedGC);
		consumedGC.gridx = 3;
		mostConsumedPanel.add(priceLabel, consumedGC);
		consumedGC.gridx = 4;
		mostConsumedPanel.add(expiryLabel, consumedGC);
		consumedGC.gridx = 5;
		mostConsumedPanel.add(shopDateLabel, consumedGC);
		consumedGC.gridx = 6;
		mostConsumedPanel.add(qtyLabel, consumedGC);
		consumedGC.gridx = 7;
		mostConsumedPanel.add(consumedLabel, consumedGC);
		consumedGC.gridx = 8;
		mostConsumedPanel.add(expiredLabel, consumedGC);
		
		if (mostConsumed.isEmpty()) {
			consumedGC.gridx = 0;
			consumedGC.gridy = 1;
			mostConsumedPanel.add(noItemsLabel, consumedGC);
		} else {
			for (int i = 0; i < mostConsumed.size(); i++) {
				JLabel img;
				String uri = (String) mostConsumed.get(i)[1];
				BufferedImage image;
				try {
					image = ImageIO.read(new File(uri));
					Image newImg = image.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
					img = new JLabel(new ImageIcon(newImg));
				} catch (Exception e) {
					e.printStackTrace();
					img = new JLabel("Img not found");
				}
				JLabel name = new JLabel((String) mostConsumed.get(i)[2]);
				JLabel category = new JLabel((String) mostConsumed.get(i)[3]);
				JLabel price = new JLabel("$" + mostConsumed.get(i)[4].toString());
				JLabel expiry = new JLabel((String) mostConsumed.get(i)[5]);
				JLabel shopDate = new JLabel((String) mostConsumed.get(i)[6]);
				int itemId = Integer.parseInt(mostConsumed.get(i)[0].toString());
				int remaining = GroceryItem.getRemaining(itemId);
				JLabel qty = new JLabel(Integer.toString(remaining));
				JLabel qtyConsumed = new JLabel(mostConsumed.get(i)[8].toString());
				JLabel expired = new JLabel((boolean) mostConsumed.get(i)[9] ? "Expired" : "Not Expired");
				
				consumedGC.gridx = 0;
				consumedGC.gridy = i + 1;
				mostConsumedPanel.add(img, consumedGC);
				consumedGC.gridx = 1;
				mostConsumedPanel.add(name, consumedGC);
				consumedGC.gridx = 2;
				mostConsumedPanel.add(category, consumedGC);
				consumedGC.gridx = 3;
				mostConsumedPanel.add(price, consumedGC);
				consumedGC.gridx = 4;
				mostConsumedPanel.add(expiry, consumedGC);
				consumedGC.gridx = 5;
				mostConsumedPanel.add(shopDate, consumedGC);
				consumedGC.gridx = 6;
				mostConsumedPanel.add(qty, consumedGC);
				consumedGC.gridx = 7;
				mostConsumedPanel.add(qtyConsumed, consumedGC);
				consumedGC.gridx = 8;
				mostConsumedPanel.add(expired, consumedGC);
			}
		}
		
		gc.gridy = 5;
		reportPanel.add(mostConsumedPanel, gc);
		
		// Top 5 Expired Layout
		gc.gridy = 6;
		reportPanel.add(mostExpiredLabel, gc);
		
		mostExpiredPanel.setLayout(new GridBagLayout());
		GridBagConstraints expiredGC = new GridBagConstraints();
		expiredGC.ipadx = 25;
		expiredGC.ipady = 10;
		expiredGC.fill = GridBagConstraints.HORIZONTAL;
		expiredGC.anchor = GridBagConstraints.CENTER;
		JLabel numExpiredLabel = new JLabel("# Expired");
		expiredGC.gridx = 0;
		expiredGC.gridy = 0;
		mostExpiredPanel.add(imgLabel1, expiredGC);
		expiredGC.gridx = 1;
		mostExpiredPanel.add(nameLabel1, expiredGC);
		expiredGC.gridx = 2;
		mostExpiredPanel.add(categoryLabel1, expiredGC);
		expiredGC.gridx = 3;
		mostExpiredPanel.add(priceLabel1, expiredGC);
		expiredGC.gridx = 4;
		mostExpiredPanel.add(expiryLabel1, expiredGC);
		expiredGC.gridx = 5;
		mostExpiredPanel.add(shopDateLabel1, expiredGC);
		expiredGC.gridx = 6;
		mostExpiredPanel.add(numExpiredLabel, expiredGC);
		expiredGC.gridx = 7;
		mostExpiredPanel.add(expiredLabel1, expiredGC);
		
		if (mostExpired.isEmpty()) {
			expiredGC.gridx = 0;
			expiredGC.gridy = 1;
			mostExpiredPanel.add(noItemsLabel, expiredGC);
		} else {
			for (int i = 0; i < mostExpired.size(); i++) {
				JLabel img;
				String uri = (String) mostExpired.get(i)[1];
				BufferedImage image;
				try {
					image = ImageIO.read(new File(uri));
					Image newImg = image.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
					img = new JLabel(new ImageIcon(newImg));
				} catch (Exception e) {
					e.printStackTrace();
					img = new JLabel("Img not found");
				}
				JLabel name = new JLabel((String) mostExpired.get(i)[2]);
				JLabel category = new JLabel((String) mostExpired.get(i)[3]);
				JLabel price = new JLabel("$" + mostExpired.get(i)[4].toString());
				JLabel expiry = new JLabel((String) mostExpired.get(i)[5]);
				JLabel shopDate = new JLabel((String) mostExpired.get(i)[6]);
				JLabel numExpired = new JLabel(mostExpired.get(i)[7].toString());
				JLabel expired = new JLabel((boolean) mostExpired.get(i)[8] ? "Expired" : "Not Expired");
				
				expiredGC.gridx = 0;
				expiredGC.gridy = i + 1;
				mostExpiredPanel.add(img, expiredGC);
				expiredGC.gridx = 1;
				mostExpiredPanel.add(name, expiredGC);
				expiredGC.gridx = 2;
				mostExpiredPanel.add(category, expiredGC);
				expiredGC.gridx = 3;
				mostExpiredPanel.add(price, expiredGC);
				expiredGC.gridx = 4;
				mostExpiredPanel.add(expiry, expiredGC);
				expiredGC.gridx = 5;
				mostExpiredPanel.add(shopDate, expiredGC);
				expiredGC.gridx = 6;
				mostExpiredPanel.add(numExpired, expiredGC);
				expiredGC.gridx = 7;
				mostExpiredPanel.add(expired, expiredGC);
			}
		}
		gc.gridy = 7;
		reportPanel.add(mostExpiredPanel, gc);


		reportSP.add(reportPanel);
		reportSP.setViewportView(reportPanel);
		gc.gridx = 0;
		gc.gridy = 0;
		gc.ipady = 600;
		gc.ipadx = 800;
		add(reportSP, gc);
	}


}
