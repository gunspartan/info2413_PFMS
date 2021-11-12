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
			stmt = conn.prepareStatement("SELECT GroceryItem.GroceryItemId, GroceryItem.Img, GroceryItem.FoodName, GroceryItem.Category, GroceryItem.Price, GroceryItem.ExpiryDate, GroceryItem.Qty, GroceryItem.QtyConsumed, GroceryItem.Expired "
					+ "FROM GroceryItem INNER JOIN GroceryInventory "
					+ "WHERE GroceryInventory.GroceryInventoryId = " + inventoryId + " AND GroceryInventory.GroceryInventoryId = GroceryItem.GroceryInventoryId;");
			rs = stmt.executeQuery();
			
			ArrayList<String[]> array = new ArrayList<>();
			while (rs.next()) {
				String[] groceryItem = new String[9];
				groceryItem[0] = rs.getString("GroceryItemId");
				groceryItem[1] = rs.getString("Img");
				groceryItem[2] = rs.getString("FoodName");
				groceryItem[3] = rs.getString("Category");
				groceryItem[4] = rs.getString("Price");
				groceryItem[5] = rs.getString("ExpiryDate");
				groceryItem[6] = rs.getString("Qty");
				groceryItem[7] = rs.getString("QtyConsumed");
				groceryItem[8] = rs.getString("Expired");
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
	
	// Get item by id
	public static String[] getGroceryItemById(int itemId) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String[] item = new String[7];
		try {
			conn = App.getConnection();
			stmt = conn.prepareStatement("SELECT Img, FoodName, Category, Price, ExpiryDate, Qty, QtyConsumed "
					+ "FROM GroceryItem WHERE GroceryItemId = " + itemId + ";");
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				item[0] = rs.getString("Img");
				item[1] = rs.getString("FoodName");
				item[2] = rs.getString("Category");
				item[3] = rs.getString("Price");
				item[4] = rs.getString("ExpiryDate");
				item[5] = rs.getString("Qty");
				item[6] = rs.getString("QtyConsumed");
			}
			// Success message
			return item;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			App.closeQueitly(rs);
			App.closeQueitly(stmt);
			App.closeQueitly(conn);
		}
		
		return null;
	}
	
	// Update Item
		public static void updateItem(int itemId, String img, String name, String category, float price, String expiry, int qty, int numConsumed) {
			Connection conn = null;
			PreparedStatement stmt = null;
			try {
				conn = App.getConnection();
				stmt = conn.prepareStatement("UPDATE GroceryItem "
						+ "SET Img = '" + img + "', FoodName = '" + name + "', Category = '" + category + "', Price = " + price + ", ExpiryDate = '" + expiry + "', Qty = " + qty + ", QtyConsumed = " + numConsumed
						+ " WHERE GroceryItemId = " + itemId + ";");
				stmt.executeUpdate();
				new PopupFrame(PopupType.ITEM_UPDATE_SUCCESS);
			} catch (Exception e) {
				e.printStackTrace();
				new PopupFrame(PopupType.ITEM_UPDATE_ERROR);
			} finally {
				App.closeQueitly(stmt);
				App.closeQueitly(conn);
			}
		}
	
	// Create Item
	public static void createItem(int inventoryId, String img, String name, String category, float price, String expiry, int qty, int numConsumed) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = App.getConnection();
			stmt = conn.prepareStatement("INSERT INTO GroceryItem (GroceryInventoryId, Category, FoodName, Price, ExpiryDate, Qty, QtyConsumed, Img) "
					+ "VALUES (" + inventoryId + ",'" + category + "','" + name + "'," + price + ",'" + expiry + "'," + qty  + "," + numConsumed + ",'" + img + "');");
			stmt.executeUpdate();
			new PopupFrame(PopupType.ITEM_CREATE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			new PopupFrame(PopupType.ITEM_CREATE_ERROR);
		} finally {
			App.closeQueitly(stmt);
			App.closeQueitly(conn);
		}
	}
	
	// Delete Item
	public static void deleteItem(int itemId) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = App.getConnection();
			stmt = conn.prepareStatement("DELETE FROM GroceryItem WHERE GroceryItemId = " + itemId + ";");
			stmt.executeUpdate();
			new PopupFrame(PopupType.ITEM_DELETE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			new PopupFrame(PopupType.ITEM_DELETE_ERROR);
		} finally {
			App.closeQueitly(stmt);
			App.closeQueitly(conn);
		}
	}
	
	// Check quantity
	public static int checkQuantity(int itemId) {
		String[] item = getGroceryItemById(itemId);
		int remaining = Integer.parseInt(item[5]) - Integer.parseInt(item[6]);
		
		if (remaining <= 3) {
			// Popup
		}
		
		return remaining;
	}
	
}
