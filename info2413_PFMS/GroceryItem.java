package info2413_PFMS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
	
	
	// Get Items from database
	public static ArrayList<String[]> getGroceryItems(int inventoryId) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = App.getConnection();
			stmt = conn.prepareStatement("SELECT GroceryItem.GroceryItemId, GroceryItem.Img, GroceryItem.FoodName, GroceryItem.Price, GroceryItem.ExpiryDate, GroceryItem.Qty, GroceryItem.Expired "
					+ "FROM GroceryItem INNER JOIN GroceryInventory "
					+ "WHERE GroceryInventory.GroceryInventoryId = " + inventoryId + " AND GroceryInventory.GroceryInventoryId = GroceryItem.GroceryInventoryId;");
			rs = stmt.executeQuery();
			
			ArrayList<String[]> array = new ArrayList<>();
			while (rs.next()) {
				String[] groceryItem = new String[7];
				groceryItem[0] = rs.getString("GroceryItemId");
				groceryItem[1] = rs.getString("Img");
				groceryItem[2] = rs.getString("FoodName");
				groceryItem[3] = rs.getString("Price");
				groceryItem[4] = rs.getString("ExpiryDate");
				groceryItem[5] = rs.getString("Qty");
				groceryItem[6] = rs.getString("Expired");
				array.add(groceryItem);
			}
			System.out.println("All records have been selected");
			return array;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			App.closeQueitly(rs);
			App.closeQueitly(stmt);
			App.closeQueitly(conn);
		}
		
		return null;
	}
	
	// Create Item
	public static void createItem(int inventoryId, String img, String name, String category, float price, String expiry, int qty) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = App.getConnection();
			stmt = conn.prepareStatement("INSERT INTO GroceryItem (GroceryInventoryId, Category, FoodName, Price, ExpiryDate, Qty, Img) "
					+ "VALUES (" + inventoryId + ",'" + category + "','" + name + "'," + price + ",'" + expiry + "'," + qty + ",'" + img + "');");
			stmt.executeUpdate();
			System.out.println("Successfully created item");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			App.closeQueitly(stmt);
			App.closeQueitly(conn);
		}
	}
}
