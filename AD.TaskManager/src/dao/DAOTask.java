package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.DBConnection;
import model.Task;
import model.TaskList;

public class DAOTask {
	public static final String TABLE_NAME = "Tasks";
	public static final String KEY_ID = "id";
	public static final String KEY_TITLE = "title";
	public static final String KEY_DESCRIPTION = "description";
	public static final String KEY_PRIORITY = "priority";
	public static final String KEY_STATUS = "status";
	public static final String KEY_INIT_DATE = "init_date";
	public static final String KEY_END_DATE = "end_date";
	public static final String KEY_LEVEL = "level";
	public static final String KEY_FK_CATEGORY = "fk_category";
	public static final String KEY_FK_DEPENDENCY = "fk_dependency";
	private Connection conn = null;

	private static DAOTask instance = null;

	public DAOTask() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		conn = DBConnection.getConnection();
	}

	public static DAOTask getInstance()
			throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		if (instance == null)
			instance = new DAOTask();
		return instance;
	}

	public Task selectById(int id) throws SQLException {
		ArrayList<Task> list = new ArrayList<Task>();
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_ID + "=?");

		ps.setInt(1, id);

		ResultSet res = ps.executeQuery();

		while (res.next()) {
			Task task = new Task();
			task.setId(res.getInt(KEY_ID));
			task.setTitle(res.getString(KEY_TITLE));
			task.setDescription(res.getString(KEY_DESCRIPTION));
			task.setPriority(res.getInt(KEY_PRIORITY));
			task.setStatus(res.getInt(KEY_STATUS));
			task.setInit_date(res.getDate(KEY_INIT_DATE));
			task.setEnd_date(res.getDate(KEY_END_DATE));
			task.setLevel(res.getInt(KEY_LEVEL));
			task.setFk_category(res.getInt(KEY_FK_CATEGORY));
			task.setFk_dependency(res.getInt(KEY_FK_DEPENDENCY));

			list.add(task);
		}

		return list.get(0);
	}

	public void insert(Task t) throws SQLException {
		PreparedStatement ps = conn
				.prepareStatement("INSERT INTO " + TABLE_NAME + " (" + KEY_TITLE + "," + KEY_DESCRIPTION + ","
						+ KEY_PRIORITY + "," + KEY_STATUS + "," + KEY_INIT_DATE + "," + KEY_END_DATE + "," + KEY_LEVEL
						+ "," + KEY_FK_CATEGORY + "," + KEY_FK_DEPENDENCY + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");

		ps.setString(1, t.getTitle());
		ps.setString(2, t.getDescription());
		ps.setInt(3, t.getPriority());
		ps.setInt(4, t.getStatus());
		ps.setDate(5, t.getInit_date());
		ps.setDate(6, t.getEnd_date());
		ps.setInt(7, t.getLevel());
		ps.setInt(8, t.getFk_category());
		if (t.getFk_dependency() == 0)
			ps.setNull(9, t.getFk_dependency());
		else
			ps.setInt(9, t.getFk_dependency());

		ps.executeUpdate();
	}

	public void update(Task t) throws SQLException {
		PreparedStatement ps = conn.prepareStatement(
				"UPDATE " + TABLE_NAME + " SET " + KEY_TITLE + "=?, " + KEY_DESCRIPTION + "=?, " + KEY_PRIORITY + "=?, "
						+ KEY_STATUS + "=?, " + KEY_INIT_DATE + "=?, " + KEY_END_DATE + "=?, " + KEY_LEVEL + "=?, "
						+ KEY_FK_CATEGORY + "=?, " + KEY_FK_DEPENDENCY + "=? WHERE " + KEY_ID + "=?");

		ps.setString(1, t.getTitle());
		ps.setString(2, t.getDescription());
		ps.setInt(3, t.getPriority());
		ps.setInt(4, t.getStatus());
		ps.setDate(5, t.getInit_date());
		ps.setDate(6, t.getEnd_date());
		ps.setInt(7, t.getLevel());
		ps.setInt(8, t.getFk_category());
		if (t.getFk_dependency() == 0)
			ps.setNull(9, t.getFk_dependency());
		else
			ps.setInt(9, t.getFk_dependency());
		ps.setInt(10, t.getId());

		ps.executeUpdate();
	}

	public void delete(int id) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("DELETE FROM " + TABLE_NAME + " WHERE " + KEY_ID + "=?");

		ps.setInt(1, id);

		ps.executeUpdate();
	}

	public TaskList selectAll() throws SQLException {
		ArrayList<Task> list = new ArrayList<Task>();
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + TABLE_NAME);
		ResultSet res = ps.executeQuery();

		while (res.next()) {
			Task task = new Task();
			task.setId(res.getInt(KEY_ID));
			task.setTitle(res.getString(KEY_TITLE));
			task.setDescription(res.getString(KEY_DESCRIPTION));
			task.setPriority(res.getInt(KEY_PRIORITY));
			task.setStatus(res.getInt(KEY_STATUS));
			task.setInit_date(res.getDate(KEY_INIT_DATE));
			task.setEnd_date(res.getDate(KEY_END_DATE));
			task.setLevel(res.getInt(KEY_LEVEL));
			task.setFk_category(res.getInt(KEY_FK_CATEGORY));
			task.setFk_dependency(res.getInt(KEY_FK_DEPENDENCY));

			list.add(task);
		}

		return new TaskList(list);
	}

}