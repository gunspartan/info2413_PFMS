package info2413_PFMS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

	// ----- SQL Queries -----
	public static ArrayList<String[]> getCategories() {
		ArrayList<String[]> categories = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = App.getConnection();
			stmt = conn.prepareStatement("SELECT * FROM Category;");
			rs = stmt.executeQuery();
			while (rs.next()) {
				String[] category = new String[3];
				category[0] = rs.getString("CategoryId");
				category[1] = rs.getString("CategoryName");
				category[2] = rs.getString("TotalSpent");
				categories.add(category);
			}
			return categories;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			App.closeQueitly(rs);
			App.closeQueitly(stmt);;
			App.closeQueitly(conn);
		}
		return null;
	}

	public static String getCategoryById(int categoryId) {
		String categoryName = "";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = App.getConnection();
			stmt = conn.prepareStatement("SELECT CategoryName FROM Category WHERE CategoryId = " + categoryId + ";");
			rs = stmt.executeQuery();
			while (rs.next()) {
				categoryName = rs.getString("CategoryName");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			App.closeQueitly(rs);
			App.closeQueitly(stmt);
			App.closeQueitly(conn);
		}
		return categoryName;
	}

	// Create category
	public static void createCategory(String categoryName) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = App.getConnection();
			stmt = conn.prepareStatement("INSERT INTO Category (CategoryName) VALUES ('" + categoryName + "');");
			stmt.executeUpdate();
			new PopupFrame(PopupType.CATEGORY_CREATE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			new PopupFrame(PopupType.CATEGORY_CREATE_ERROR);
		} finally {
			App.closeQueitly(stmt);
			App.closeQueitly(conn);
		}
	}

	// Update category
	public static void updateCategory(int categoryId, String categoryName) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = App.getConnection();
			stmt = conn.prepareStatement("UPDATE Category SET CategoryName = '" + categoryName + "' WHERE CategoryId = " + categoryId + ";");
			stmt.executeUpdate();
			new PopupFrame(PopupType.CATEGORY_UPDATE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			new PopupFrame(PopupType.CATEGORY_UPDATE_ERROR);
		} finally {
			App.closeQueitly(stmt);
			App.closeQueitly(conn);
		}
	}

	// Delete category
	public static void deleteCategory(int categoryId) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = App.getConnection();
			stmt = conn.prepareStatement("DELETE FROM Category WHERE CategoryId = " + categoryId + ";");
			stmt.executeUpdate();
			new PopupFrame(PopupType.CATEGORY_DELETE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			new PopupFrame(PopupType.CATEGORY_DELETE_ERROR);
		} finally {
			App.closeQueitly(stmt);
			App.closeQueitly(conn);
		}
	}

	// Update spending
	public static void updateTotalSpending(String categoryName) {
		float totalSpent = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		PreparedStatement stmt1 = null;
		ResultSet rs = null;
		try {
			conn = App.getConnection();
			stmt = conn.prepareStatement("SELECT ROUND(SUM(GroceryItem.Price * GroceryItem.Qty), 2) AS TotalSpent "
					+ "FROM GroceryItem INNER JOIN Category "
					+ "WHERE GroceryItem.Category = Category.CategoryName AND Category.CategoryName = '" + categoryName + "';");
			rs = stmt.executeQuery();
			while(rs.next()) {
				totalSpent = rs.getFloat("TotalSpent");
				System.out.print(totalSpent);
			}

			stmt1 = conn.prepareStatement("UPDATE Category "
					+ "SET TotalSpent = " + totalSpent + "WHERE CategoryName = '" + categoryName + "';");
			System.out.print(totalSpent);
			stmt1.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			App.closeQueitly(rs);
			App.closeQueitly(stmt);
			App.closeQueitly(stmt1);
			App.closeQueitly(conn);
		}
		
	}

	public static void updateTotalSpending() {
		ArrayList<Integer> categoryIds = new ArrayList<>();
		float totalSpent = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		PreparedStatement stmt1 = null;
		PreparedStatement stmt2 = null;
		ResultSet rs = null;

		try {
			conn = App.getConnection();
			stmt = conn.prepareStatement("SELECT CategoryId FROM Category;");
			rs = stmt.executeQuery();
			while (rs.next()) {
				categoryIds.add(rs.getInt("CategoryId"));
			}
			for (int i = 0; i < categoryIds.size(); i++) {
				stmt1 = conn.prepareStatement("SELECT ROUND(SUM(GroceryItem.Price * GroceryItem.Qty), 2) AS TotalSpent "
						+ "FROM GroceryItem INNER JOIN Category "
						+ "WHERE GroceryItem.Category = Category.CategoryName AND Category.CategoryId = " + categoryIds.get(i) + ";");
				rs = stmt1.executeQuery();
				while(rs.next()) {
					totalSpent = rs.getFloat("TotalSpent");
					System.out.print(totalSpent);
				}

				stmt2 = conn.prepareStatement("UPDATE Category "
						+ "SET TotalSpent = " + totalSpent + "WHERE CategoryId = " + categoryIds.get(i) + ";");
				System.out.print(totalSpent);
				stmt2.executeUpdate();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			App.closeQueitly(rs);
			App.closeQueitly(stmt);
			App.closeQueitly(stmt1);
			App.closeQueitly(stmt2);
			App.closeQueitly(conn);
		}

	}
}
