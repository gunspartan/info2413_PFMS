package info2413_PFMS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class GroceryItem {
	// Get All Items
	public static ArrayList<String[]> getAllItems() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = App.getConnection();
			stmt = conn.prepareStatement("SELECT * FROM GroceryItem");
			rs = stmt.executeQuery();

			ArrayList<String[]> array = new ArrayList<>();
			while (rs.next()) {
				String[] groceryItem = new String[10];
				groceryItem[0] = rs.getString("GroceryItemId");
				groceryItem[1] = rs.getString("GroceryInventoryId");
				groceryItem[2] = rs.getString("Img");
				groceryItem[3] = rs.getString("FoodName");
				groceryItem[4] = rs.getString("Category");
				groceryItem[5] = rs.getString("Price");
				groceryItem[6] = rs.getString("ExpiryDate");
				groceryItem[7] = rs.getString("Qty");
				groceryItem[8] = rs.getString("QtyConsumed");
				groceryItem[9] = rs.getString("Expired");
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
		checkQuantity();
		checkExpiryDate();
	}

	// Set expired
	public static void setExpired(ArrayList<String[]> items) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = App.getConnection();
			for (int i = 0; i < items.size(); i ++) {
				stmt = conn.prepareStatement("UPDATE GroceryItem "
						+ "SET Expired = true "
						+ " WHERE GroceryItemId = " + items.get(i)[0] + ";");
				stmt.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
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
		checkQuantity();
		checkExpiryDate();
	}

	// Delete Item
	public static void deleteItem(int itemId) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = App.getConnection();
			stmt = conn.prepareStatement("DELETE FROM GroceryItem WHERE GroceryItemId = " + itemId + ";");
			stmt.executeUpdate();
			Category.updateTotalSpending();
			new PopupFrame(PopupType.ITEM_DELETE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			new PopupFrame(PopupType.ITEM_DELETE_ERROR);
		} finally {
			App.closeQueitly(stmt);
			App.closeQueitly(conn);
		}
	}

	// Get Remaining Quantity
	public static int getRemaining(int itemId) {
		String[] item = getGroceryItemById(itemId);
		return Integer.parseInt(item[5]) - Integer.parseInt(item[6]);		
	}

	// Check Quantity
	public static void checkQuantity() {
		// Check all items
		ArrayList<String[]> items = getAllItems();
		ArrayList<String[]> lowQuantityItems = new ArrayList<>();
		for (int i = 0; i < items.size(); i++) {
			if (getRemaining(Integer.parseInt(items.get(i)[0])) < 3) {
				lowQuantityItems.add(items.get(i));
			}
		}
		if (!lowQuantityItems.isEmpty()) {
			new Notification(NotificationType.LOW_QTY, lowQuantityItems);
		}
	}

	// Check expiry date
	public static void checkExpiryDate() {
		// Check all items
		ArrayList<String[]> items = getAllItems();
		ArrayList<String[]> expiredItems = new ArrayList<>();
		ArrayList<String[]> expiringItems = new ArrayList<>();
		for (int i = 0; i < items.size(); i++) {
			String expiryDate = items.get(i)[6];
			if (App.daysDiff(expiryDate) < 0) {
				expiredItems.add(items.get(i));
			} else if (App.daysDiff(expiryDate) < 10) {
				expiringItems.add(items.get(i));
			}
		}
		if (!expiredItems.isEmpty()) {
			new Notification(NotificationType.EXPIRED, expiredItems);
			// Set expired
			setExpired(expiredItems);
		}
		if (!expiringItems.isEmpty()) {
			new Notification(NotificationType.EXPIRING, expiringItems);
		}
	}
}
