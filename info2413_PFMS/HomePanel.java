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

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class HomePanel extends JPanel {
	
	private final ContainerPanel parentPanel;
	private final CardLayout cl;
	private final JScrollPane inventoryScrollPane;
	private final JPanel inventoryPanel;
	private final LinkedList<String> temp;
	
	public HomePanel(ContainerPanel parentPanel, CardLayout cl, LinkedList<String> temp) {
		this.parentPanel = parentPanel;
		this.cl = cl;
		this.temp = temp;
		
		
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
		
		gc.anchor = GridBagConstraints.PAGE_END;
		gc.gridx = 0;
		gc.gridy = 1;
		add(shoppingDateLabel, gc);
		
		
		
		// Get inventories from database
		for (int i = 0; i < temp.size(); i++) {
			JLabel inventoryId = new JLabel(Integer.toString(i));
			// Create Label for each inventory
			JLabel dateLabel = new JLabel(temp.get(i));
			// Create a button for each inventory
			JButton inventoryBtn = new JButton("OPEN");
			// Add Action Listener for each button
			inventoryBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String inventorySelectedId = inventoryId.getText();
					String inventorySelected = dateLabel.getText();
					try {
						parentPanel.handleHomePanelInventoryBtn(e, inventorySelectedId, inventorySelected);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (URISyntaxException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			});
			// Add Labels
			listConstraints.gridx = 0;
			listConstraints.gridy = i;
			inventoryPanel.add(dateLabel, listConstraints);
			// Add Buttons
			listConstraints.gridx = 1;
			listConstraints.gridy = i;
			inventoryPanel.add(inventoryBtn, listConstraints);
		}
		
		// Layout for scroll pane
		gc.anchor = GridBagConstraints.PAGE_START;
		inventoryScrollPane.add(inventoryPanel);
		inventoryScrollPane.setViewportView(inventoryPanel);
		gc.ipady = 250;
		gc.ipadx = 450;
		gc.gridwidth = 2;
		gc.gridx = 0;
		gc.gridy = 2;
		add(inventoryScrollPane, gc);
	
	}
	
}
