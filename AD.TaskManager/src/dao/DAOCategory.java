package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.DBConnection;
import model.Category;
import model.CategoryList;

public class DAOCategory {
	public static final String TABLE_NAME = "Categories";
	public static final String KEY_ID = "id";
	public static final String KEY_NAME = "name";
	private Connection conn = null;

	private static DAOCategory instance = null;

	public DAOCategory() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		conn = DBConnection.getConnection();
	}

	public static DAOCategory getInstance()
			throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		if (instance == null)
			instance = new DAOCategory();
		return instance;
	}

	public Category selectById(int id) throws SQLException {
		ArrayList<Category> list = new ArrayList<Category>();
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_ID + "=?");

		ps.setInt(1, id);

		ResultSet res = ps.executeQuery();

		while (res.next()) {
			Category category = new Category();
			category.setId(res.getInt(KEY_ID));
			category.setName(res.getString(KEY_NAME));

			list.add(category);
		}

		return list.get(0);
	}

	public void insert(Category t) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("INSERT INTO " + TABLE_NAME + " (" + KEY_NAME + ") VALUES (?)");

		ps.setString(1, t.getName());

		ps.executeUpdate();
	}

	public void update(Category t) throws SQLException {
		PreparedStatement ps = conn
				.prepareStatement("UPDATE " + TABLE_NAME + " SET " + KEY_NAME + "=? WHERE " + KEY_ID + "=?");

		ps.setString(1, t.getName());
		ps.setInt(2, t.getId());

		ps.executeUpdate();
	}

	public void delete(int id) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("DELETE FROM " + TABLE_NAME + " WHERE " + KEY_ID + "=?");

		ps.setInt(1, id);

		ps.executeUpdate();
	}

	public CategoryList selectAll() throws SQLException {
		ArrayList<Category> list = new ArrayList<Category>();
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + TABLE_NAME);
		ResultSet res = ps.executeQuery();

		while (res.next()) {
			Category category = new Category();
			category.setId(res.getInt(KEY_ID));
			category.setName(res.getString(KEY_NAME));

			list.add(category);
		}

		return new CategoryList(list);
	}

}