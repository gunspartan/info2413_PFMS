package info2413_PFMS;

public class GroceryItem {
	String name, purchaseDate, expiryDate, img;
	int quantity, numConsumed;
	float price;
	Boolean isExpired;
	
	// GroceryItem belongs to a GroceryInventory
	GroceryInventory inventory;
	
	// GroceryItem can have multiple categories categories
	Category[] categories;
	
	// Constructor
	GroceryItem(String name, int quantity, float price, String purchaseDate, String expiryDate, String img, GroceryInventory inventory, Category[] categories) {
		this.name = name;
		this.quantity = quantity;
		this.price = price;
		this.purchaseDate = purchaseDate;
		this.expiryDate = expiryDate;
		this.img = img;
		this.inventory = inventory;
		this.categories = categories;
	}
	
	// Setters and getters
	
	// createGroceryItem
	
	// deleteGroceryItem
	
	// updateGroceryItem
	
	// setExpired
	
	// checkQuantity
	
}
