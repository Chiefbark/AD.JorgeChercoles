package model;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.DAOCategory;

public class CategoryList {

	private ArrayList<Category> list;

	public CategoryList() {
		this.list = new ArrayList<Category>();
	}

	public CategoryList(ArrayList<Category> list) {
		this.list = list;
	}

	public String toHTML() {
		String str = "";
		for (Category category : this.list)
			str += category.toHTML() + "\n";
		return str;
	}

	public String toHTMLForm(int id_selected) {
		String str = "";
		for (Category category : this.list)
			str += category.toHTMLForm(id_selected) + "\n";
		return str;
	}

	//
	// STATIC METHODS
	//

	public static CategoryList selectAll()
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		return DAOCategory.getInstance().selectAll();
	}
}
