package info2413_PFMS;

import java.util.LinkedList;

public class User {
	String username;
	String password;
	String email;
	float budget;

	// User can have multiple grocery inventories
	LinkedList<GroceryInventory> inventory = new LinkedList<GroceryInventory>();
	
	// Constructor
	User(String username, String password, String email) {
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	// Setters and getters
	public void setUsername(String newUsername) {
		username = newUsername;
		// Make change in databaase
	}
	
	public void setPassword(String newPwd) {
		password = newPwd;
		// Make change in database
	}
	
	public void setEmail(String newEmail) {
		email = newEmail;
		// Make change in database
	}
	
	public void setBudget(float newBudget) {
		budget = newBudget;
		// Make change in database
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getEmail() {
		return email;
	}
	
	public float getBudget() {
		return budget;
	}
	
	public LinkedList<GroceryInventory> getInventory() {
		return inventory;
	}
	// Create User
	// Add user to database
	
	// Login
	// Check database for username and corresponding password
	
	// updateUser
	// Update user information to database
	
	// createBudget
	// Add budget to the database
	
	// updateBudget
	// Update budget to the database
	
	// checkBudget
	// Send notification if budget is low
	
	// Manage inventories
	public void addGroceryInventory(GroceryInventory newInventory) {
		inventory.add(newInventory);
	}
	
	public void deleteGroceryInventory(GroceryInventory newInventory) {
		inventory.remove(newInventory);
	}
}
