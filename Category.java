package info2413_PFMS;

import java.util.ArrayList;

public class Category {
	String categoryName;
	float totalSpent;
	
	// Category has a list of GroceryItems that belong to the category
	ArrayList<GroceryItem> items = new ArrayList<GroceryItem>();
	
	// Constructor
	Category(String categoryName) {
		this.categoryName = categoryName;
	}
	
	// Getters and setters
	public void setCategoryName(String newCategoryName) {
		categoryName = newCategoryName;
		// Make changes to database
	}
	
	public String getCategoryName() {
		return categoryName;
	}
	
	// createCategory
	// Add category to database
	
	// deleteCategory
	// Delete category from database
	
	// updateCategory
	// Make changes to database
	
	// calculateSpending
	// Get prices from all items and add
	
	// addGroceryItems
	// Add item to list
}
