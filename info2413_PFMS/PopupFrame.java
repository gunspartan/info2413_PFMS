package info2413_PFMS;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

enum PopupType{
	LOGIN_ERROR,
	CREATE_USER_SUCCESS, CREATE_USER_ERROR, INVALID_PASSWORD, INVALID_EMAIL,
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
			break;
		case CREATE_USER_SUCCESS:
			title = "Account Created!";
			contentLabel = new JLabel("Your account has been created.");
			break;
		case CREATE_USER_ERROR:
			title = "Error Creating Account";
			contentLabel = new JLabel("There was an error creating an account.");
			break;
		case INVALID_PASSWORD:
			title = "Invalid Password";
			contentLabel = new JLabel("Minimum password requirements were not met");
			break;
		case INVALID_EMAIL:
			title = "Invalid Email";
			contentLabel = new JLabel("Please enter a valid email address");
		case SET_BUDGET_SUCCESS:
			title = "Budget updated";
			contentLabel = new JLabel("Budget has been updated.");
			break;
		case SET_BUDGET_ERROR:
			title = "Error Updating Budget";
			contentLabel = new JLabel("There was an error updating the budget.");
			break;
		case INVENTORY_CREATE_SUCCESS:
			title = "Inventory Created";
			contentLabel = new JLabel("The inventory has been created.");
			break;
		case INVENTORY_CREATE_ERROR:
			title = "Error Creating Inventory";
			contentLabel = new JLabel("There was an error creating the inventory.");
			break;
		case INVENTORY_UPDATE_SUCCESS:
			title = "Inventory Updated";
			contentLabel = new JLabel("The inventory has been updated.");
			break;
		case INVENTORY_UPDATE_ERROR:
			title = "Error Updating Inventory";
			contentLabel = new JLabel("There was an error updating the inventory.");
			break;
		case INVENTORY_DELETE_SUCCESS:
			title = "Inventory Deleted";
			contentLabel = new JLabel("The inventory has been deleted.");
			break;
		case INVENTORY_DELETE_ERROR:
			title = "Error Deleting Inventory";
			contentLabel = new JLabel("There was an error deleting the inventory.");
			break;
		case ITEM_UPDATE_SUCCESS:
			title = "Item Created";
			contentLabel = new JLabel("The item has been updated.");
			break;
		case ITEM_UPDATE_ERROR:
			title = "Error Updating Item";
			contentLabel = new JLabel("There was an error updating the item.");
			break;
		case ITEM_CREATE_SUCCESS:
			title = "Item Created";
			contentLabel = new JLabel("The item has been created.");
			break;
		case ITEM_CREATE_ERROR:
			title = "Error Creating Item";
			contentLabel = new JLabel("There was an error creating the item.");
			break;
		case ITEM_DELETE_SUCCESS:
			title = "Item Deleted";
			contentLabel = new JLabel("The item has been deleted.");
			break;
		case ITEM_DELETE_ERROR:
			title = "Error Deleting Item";
			contentLabel = new JLabel("There was an error deleting the item.");
			break;
		case CATEGORY_CREATE_SUCCESS:
			title = "Category Created";
			contentLabel = new JLabel("The category has been created.");
			break;
		case CATEGORY_CREATE_ERROR:
			title = "Error Creating Category";
			contentLabel = new JLabel("There was an error creating the category.");
			break;
		case CATEGORY_UPDATE_SUCCESS:
			title = "Category Updated";
			contentLabel = new JLabel("The category has been updated.");
			break;
		case CATEGORY_UPDATE_ERROR:
			title = "Error Updating Category";
			contentLabel = new JLabel("There was an error updating the category.");
			break;
		case CATEGORY_DELETE_SUCCESS:
			title = "Category Deleted";
			contentLabel = new JLabel("The category has been deleted.");
			break;
		case CATEGORY_DELETE_ERROR:
			title = "Error Deleting Category";
			contentLabel = new JLabel("There was an error deleting the category.");
			break;
		default:
			break;
		}
		content.add(contentLabel);
		frame = new JFrame(title);
		frame.add(content);
		frame.setSize(sizeX, sizeY);
		frame.setVisible(true);
	}
}