package info2413_PFMS;

import java.util.ArrayList;

public class GroceryInventory {
	// GroceryInventory has a user
	User user;
	float totalSpent;
	String shoppingDate;
	// GroceryInventory has a list of items
	ArrayList<GroceryItem> items = new ArrayList<GroceryItem>();
	
	// Constructor
	GroceryInventory(User user, String shoppingDate) {
		this.user = user;
		this.shoppingDate = shoppingDate;
	}
	
	// Setters and getters
	public void setUser(User newUser) {
		user = newUser;
		// Make change to database
	}
	
	public void setShoppingDate(String newShoppingDate) {
		shoppingDate = newShoppingDate;
		// Make change to database
	}
	
	public User getUser() {
		return user;
	}
	
	public String getShoppingDate() {
		return shoppingDate;
	}
	
	// createGroceryInventory
	// Create GroceryInventory in database
	
	// deleteGroceryInventory
	// Delete GroceryInventory in database
	
	// addGroceryItem
	// Add item to Inventory
	public void addGroceryItem(GroceryItem newItem) {
		items.add(newItem);
		// Make change to database
	}
	
	// deleteGroceryItem
	// Delete item from Inventory
	public void deleteGroceryItem(GroceryItem newItem) {
		items.remove(newItem);
		// Make change to database
	}
	
	// checkExpiry
	// Check expiry dates of all foods
	public ArrayList<GroceryItem> checkExpiry() {
		ArrayList<GroceryItem> expiringItems = new ArrayList<GroceryItem>();
		for (int i = items.size() - 1; i >= 0; i--) {
			// If expiring
			// Add to expiringItems
		}
		
		return expiringItems;
	}
	
	// calculateSpending
	// Check total spending on all foods
	
}
