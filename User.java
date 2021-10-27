package info2413_PFMS;

public class User {
	String username, password, email;
	float budget;
	
	// User can have multiple grocery inventories
	GroceryInventory[] inventory;
	
	// Constructor
	User(String username, String password, String email, GroceryInventory[] inventory) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.inventory = inventory;
	}
	
	// Setters and getters
	
	// Create User
	
	// Login
	
	// updateUser
	
	// createBudget
	
	// updateBudget
	
	// checkBudget
}
