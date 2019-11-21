package model;

import java.sql.SQLException;

import dao.DAOCategory;

public class Category {

	private int id;
	private String name;

	public Category() {
		this.name = "";
	}

	//
	// GETTERS & SETTERS
	//

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	//
	// PRINT METHODS
	//

	public String toHTML() {
		String str = "";

		str += "<div class=\"d-inline-block border rounded border-secondary p-2 ml-2 mr-2\">";
		str += "<span class=\"pl-2 pr-2\">" + getName() + "</span>";

		str += "<div style=\"cursor:pointer;\" title=\"edit\" class=\"text-center d-inline-block pr-1 pl-1\">";
		str += "<a href=\"ServletCategory?option=update&id=" + getId()
				+ "\" class=\"d-block\" style=\"color: #999999;\">";
		str += "<ion-icon name=\"create\"></ion-icon>";
		str += "</a>";
		str += "</div>";

		str += "<div style=\"cursor:pointer;\" title=\"delete\" class=\"text-center d-inline-block pr-1 pl-1\">";
		str += "<a href=\"ServletCategory?option=delete&id=" + getId()
				+ "\" class=\"d-block\" style=\"color: #999999;\">";
		str += "<ion-icon name=\"trash\"></ion-icon>";
		str += "</a>";
		str += "</div>";
		str += "</div>";

		return str;
	}

	public String toHTMLForm(int id_selected) {
		String str = "";
		str += "<option value='" + getId() + "' " + ((id_selected == getId()) ? "selected" : "") + ">" + getName()
				+ "</option>";
		return str;
	}

	//
	// STATIC METHODS
	//

	public static Category selectById(int id)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		return DAOCategory.getInstance().selectById(id);
	}

	public static void insert(Category category)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		DAOCategory.getInstance().insert(category);
	}

	public static void update(Category category)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		DAOCategory.getInstance().update(category);
	}

	public static void delete(int id)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		DAOCategory.getInstance().delete(id);
	}

}
