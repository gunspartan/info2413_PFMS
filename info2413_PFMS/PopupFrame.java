package info2413_PFMS;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

enum PopupType{
	LOGIN_ERROR,
	CREATE_USER_SUCCESS, CREATE_USER_ERROR,
	SET_BUDGET_SUCCESS, SET_BUDGET_ERROR,
	INVENTORY_CREATE_SUCCESS, INVENTORY_CREATE_ERROR, INVENTORY_UPDATE_SUCCESS, INVENTORY_UPDATE_ERROR, INVENTORY_DELETE_SUCCESS, INVENTORY_DELETE_ERROR,
	ITEM_UPDATE_SUCCESS, ITEM_UPDATE_ERROR, ITEM_CREATE_SUCCESS, ITEM_CREATE_ERROR, ITEM_DELETE_SUCCESS, ITEM_DELETE_ERROR,
	CATEGORY_CREATE_SUCCESS, CATEGORY_CREATE_ERROR, CATEGORY_UPDATE_SUCCESS, CATEGORY_UPDATE_ERROR, CATEGORY_DELETE_SUCCESS, CATEGORY_DELETE_ERROR;
}

public class PopupFrame extends JFrame {
	private String title;
	private JFrame frame;
	private JPanel content = new JPanel();
	private JLabel contentLabel;
	private int sizeX = 400;
	private int sizeY = 150;
	public PopupFrame(PopupType type) {
		
		switch (type) {
		case LOGIN_ERROR:
			title = "Login Error";
			contentLabel = new JLabel("There was a login error.");
			content.add(contentLabel);
			break;
		case CREATE_USER_SUCCESS:
			title = "Account Created!";
			contentLabel = new JLabel("Your account has been created.");
			content.add(contentLabel);
			break;
		case CREATE_USER_ERROR:
			title = "Error Creating Account";
			contentLabel = new JLabel("There was an error creating an account.");
			content.add(contentLabel);
			break;
		case SET_BUDGET_SUCCESS:
			title = "Budget updated";
			contentLabel = new JLabel("Budget has been updated.");
			content.add(contentLabel);
			break;
		case SET_BUDGET_ERROR:
			title = "Error Updating Budget";
			contentLabel = new JLabel("There was an error updating the budget.");
			content.add(contentLabel);
			break;
		case INVENTORY_CREATE_SUCCESS:
			title = "Inventory Created";
			contentLabel = new JLabel("The inventory has been created.");
			content.add(contentLabel);
			break;
		case INVENTORY_CREATE_ERROR:
			title = "Error Creating Inventory";
			contentLabel = new JLabel("There was an error creating the inventory.");
			content.add(contentLabel);
			break;
		case INVENTORY_UPDATE_SUCCESS:
			title = "Inventory Updated";
			contentLabel = new JLabel("The inventory has been updated.");
			content.add(contentLabel);
			break;
		case INVENTORY_UPDATE_ERROR:
			title = "Error Updating Inventory";
			contentLabel = new JLabel("There was an error updating the inventory.");
			content.add(contentLabel);
			break;
		case INVENTORY_DELETE_SUCCESS:
			title = "Inventory Deleted";
			contentLabel = new JLabel("The inventory has been deleted.");
			content.add(contentLabel);
			break;
		case INVENTORY_DELETE_ERROR:
			title = "Error Deleting Inventory";
			contentLabel = new JLabel("There was an error deleting the inventory.");
			content.add(contentLabel);
			break;
		case ITEM_UPDATE_SUCCESS:
			title = "Item Created";
			contentLabel = new JLabel("The item has been updated.");
			content.add(contentLabel);
			break;
		case ITEM_UPDATE_ERROR:
			title = "Error Updating Item";
			contentLabel = new JLabel("There was an error updating the item.");
			content.add(contentLabel);
			break;
		case ITEM_CREATE_SUCCESS:
			title = "Item Created";
			contentLabel = new JLabel("The item has been created.");
			content.add(contentLabel);
			break;
		case ITEM_CREATE_ERROR:
			title = "Error Creating Item";
			contentLabel = new JLabel("There was an error creating the item.");
			content.add(contentLabel);
			break;
		case ITEM_DELETE_SUCCESS:
			title = "Item Deleted";
			contentLabel = new JLabel("The item has been deleted.");
			content.add(contentLabel);
			break;
		case ITEM_DELETE_ERROR:
			title = "Error Deleting Item";
			contentLabel = new JLabel("There was an error deleting the item.");
			content.add(contentLabel);
			break;
		case CATEGORY_CREATE_SUCCESS:
			title = "Category Created";
			contentLabel = new JLabel("The category has been created.");
			content.add(contentLabel);
			break;
		case CATEGORY_CREATE_ERROR:
			title = "Error Creating Category";
			contentLabel = new JLabel("There was an error creating the category.");
			content.add(contentLabel);
			break;
		case CATEGORY_UPDATE_SUCCESS:
			title = "Category Updated";
			contentLabel = new JLabel("The category has been updated.");
			content.add(contentLabel);
			break;
		case CATEGORY_UPDATE_ERROR:
			title = "Error Updating Category";
			contentLabel = new JLabel("There was an error updating the category.");
			content.add(contentLabel);
			break;
		case CATEGORY_DELETE_SUCCESS:
			title = "Category Deleted";
			contentLabel = new JLabel("The category has been deleted.");
			content.add(contentLabel);
			break;
		case CATEGORY_DELETE_ERROR:
			title = "Error Deleting Category";
			contentLabel = new JLabel("There was an error deleting the category.");
			content.add(contentLabel);
			break;
		default:
			break;
		}
		
		frame = new JFrame(title);
		frame.add(content);
		frame.setSize(sizeX, sizeY);
		frame.setVisible(true);
	}
}