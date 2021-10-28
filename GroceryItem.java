package info2413_PFMS;

import java.util.ArrayList;

public class GroceryItem {
	String name, purchaseDate, expiryDate, img;
	int quantity, numConsumed;
	float price;
	Boolean isExpired;
	
	// GroceryItem belongs to a GroceryInventory
	GroceryInventory inventory;
	
	// GroceryItem can have multiple categories categories
	ArrayList<Category> categories = new ArrayList<Category>();
	
	// Constructor
	GroceryItem(String name, int quantity, float price, String purchaseDate, String expiryDate, String img) {
		this.name = name;
		this.quantity = quantity;
		this.price = price;
		this.purchaseDate = purchaseDate;
		this.expiryDate = expiryDate;
		this.img = img;
		// Items are not expired by default
		isExpired = false;
	}
	
	// Setters and getters
	public void setName(String newName) {
		name = newName;
		// Make changes to database
	}
	
	public void setQuantity(int newQuantity) {
		quantity = newQuantity;
		// Make changes to database
	}
	
	public void setPrice(float newPrice) {
		price = newPrice;
		// Make changes to database
	}
	
	public void setPurchaseDate(String newPurchaseDate) {
		purchaseDate = newPurchaseDate;
		// Make changes to database
	}
	
	public void setExpiryDate(String newExpiryDate) {
		expiryDate = newExpiryDate;
		// Make changes to database
	}
	
	public void setImg(String newImg) {
		img = newImg;
		// Make changes to database
	}
	
	public String getName() {
		return name;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public float getPrice() {
		return price;
	}
	
	// setExpired
		// If today >= expiryDate -> expired = true
	
	public String getPurchaseDate() {
		return purchaseDate;
	}
	
	public String getExpiryDate() {
		return expiryDate;
	}
	
	public String getImg() {
		return img;
	}
	
	public Boolean getExpired() {
		return isExpired;
	}

	// createGroceryItem
	
	// deleteGroceryItem
	
	// updateGroceryItem
	
	// checkQuantity
	// If quantity >= 3 -> send low count notification
	
}
