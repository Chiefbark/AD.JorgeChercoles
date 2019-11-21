package model;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;

import dao.DAOTask;

public class Task {

	private int id;
	private String title;
	private String description;
	private int priority;
	private int status;
	private Date init_date;
	private Date end_date;
	private int level;
	private int fk_category;
	private int fk_dependency;

	public Task() {
		this.title = "";
		this.description = "";
	}

	public Task(int id, String title, String description, int priority, int status, Date init_date, Date end_date,
			int level, int fk_category, int fk_dependency) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.priority = priority;
		this.status = status;
		this.init_date = init_date;
		this.end_date = end_date;
		this.level = level;
		this.fk_category = fk_category;
		this.fk_dependency = fk_dependency;
	}

	public Task(String title, String description, int priority, int status, Date init_date, Date end_date, int level,
			int fk_category, int fk_dependency) {
		this.title = title;
		this.description = description;
		this.priority = priority;
		this.status = status;
		this.init_date = init_date;
		this.end_date = end_date;
		this.level = level;
		this.fk_category = fk_category;
		this.fk_dependency = fk_dependency;
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
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the priority
	 */
	public int getPriority() {
		return priority;
	}

	/**
	 * @param priority the priority to set
	 */
	public void setPriority(int priority) {
		this.priority = priority;
	}

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @return the init_date
	 */
	public Date getInit_date() {
		return init_date;
	}

	public String getInitDate() {
		String date = "";
		if (init_date != null) {
			Calendar init = Calendar.getInstance();
			init.setTimeInMillis(init_date.getTime());
			date += init.get(Calendar.YEAR) + "-" + (init.get(Calendar.MONTH) + 1) + "-" + init.get(Calendar.DATE);
		}
		return date;
	}

	/**
	 * @param init_date the init_date to set
	 */
	public void setInit_date(Date init_date) {
		this.init_date = init_date;
	}

	/**
	 * @return the end_date
	 */
	public Date getEnd_date() {
		return end_date;
	}

	public String getEndDate() {
		String date = "";
		if (end_date != null) {
			Calendar end = Calendar.getInstance();
			end.setTimeInMillis(end_date.getTime());
			date += end.get(Calendar.YEAR) + "-" + (end.get(Calendar.MONTH) + 1) + "-" + end.get(Calendar.DATE);
		}
		return date;
	}

	/**
	 * @param end_date the end_date to set
	 */
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	/**
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * @param level the level to set
	 */
	public void setLevel(int level) {
		this.level = level;
	}

	/**
	 * @return the fk_category
	 */
	public int getFk_category() {
		return fk_category;
	}

	/**
	 * @param fk_category the fk_category to set
	 */
	public void setFk_category(int fk_category) {
		this.fk_category = fk_category;
	}

	/**
	 * @return the fk_dependency
	 */
	public int getFk_dependency() {
		return fk_dependency;
	}

	/**
	 * @param fk_dependency the fk_dependency to set
	 */
	public void setFk_dependency(int fk_dependency) {
		this.fk_dependency = fk_dependency;
	}

	//
	// PRINT METHODS
	//

	public String toHTML() {

		String priority = "bg-";
		switch (getPriority()) {
		case 1:
			priority += "success";
			break;
		case 2:
			priority += "warning";
			break;
		case 3:
			priority += "danger";
			break;
		}

		Calendar init = Calendar.getInstance();
		init.setTimeInMillis(init_date.getTime());
		Calendar end = Calendar.getInstance();
		end.setTimeInMillis(end_date.getTime());
		String dates = "";
		dates += init.get(Calendar.DATE) + "/" + init.get(Calendar.MONTH) + "/" + init.get(Calendar.YEAR);
		dates += " - ";
		dates += end.get(Calendar.DATE) + "/" + end.get(Calendar.MONTH) + "/" + end.get(Calendar.YEAR);

		String status = "";
		switch (getStatus()) {
		case 1:
			status = "Pending";
			break;
		case 2:
			status = "On progress";
			break;
		case 3:
			status = "Ended";
			break;
		}

		Category category = null;
		try {
			category = Category.selectById(getFk_category());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String str = "";
		str += "<tr>";
		str += "<td>";

		for (int ii = 0; ii < getLevel(); ii++)
			str += "<ion-icon name=\"return-right\" style=\"width: 20px;"
					+ ((ii < getLevel() - 1) ? "color: #00000000 !important;" : "") + "\"></ion-icon>";
		str += "<div class=\"rounded-circle d-inline-block " + priority
				+ "\" style=\"width: 20px; height: auto; padding-top: 20px;\"></div>";
		str += "</td>";
		str += "<td class=\"font-weight-bold\">" + getTitle() + "</td>";
		str += "<td class=\"text-center\"><i>" + status + "</i></td>";
		str += "<td class=\"text-center\">" + dates + "</td>";
		str += "<td class=\"text-center\">" + category.getName() + "</td>";

		str += "<td style=\"cursor:pointer;\" title=\"edit\" class=\"text-center\">";
		str += "<a href=\"ServletTask?option=update&id=" + getId() + "\" class=\"d-block\" style=\"color: #999999;\">";
		str += "<ion-icon name=\"create\"></ion-icon>";
		str += "</a>";
		str += "</td>";

		str += "<td style=\"cursor:pointer;\" title=\"delete\" class=\"text-center\">";
		str += "<a href=\"ServletTask?option=delete&id=" + getId() + "\" class=\"d-block\" style=\"color: #999999;\">";
		str += "<ion-icon name=\"trash\"></ion-icon>";
		str += "</a>";
		str += "</td>";

		str += "<td style=\"cursor:pointer;\" title=\"description\" class=\"text-center\" onclick=\"this.parentElement.nextElementSibling.childNodes[1].classList.toggle('d-none');\">";
		str += "<ion-icon name=\"menu\" style=\"color:#999999;\"></ion-icon>";
		str += "</td>";

		str += "<tr>";
		str += "<td class=\"p-0 m-0\"></td>";
		str += "<td colspan=\"7\" class=\"d-none bg-light\">";
		str += getDescription();
		str += "</td>";
		str += "</tr>";

		return str;
	}

	public String toHTMLForm(int id_selected) {
		String str = "";
		str += "<option value='" + getId() + "' " + ((id_selected == getId()) ? "selected" : "") + ">" + getTitle()
				+ "</option>";
		return str;
	}
	//
	// STATIC METHODS
	//

	@Override
	public String toString() {
		return "Task [id=" + id + ", title=" + title + ", description=" + description + ", priority=" + priority
				+ ", status=" + status + ", init_date=" + init_date + ", end_date=" + end_date + ", fk_category="
				+ fk_category + ", fk_dependency=" + fk_dependency + "]";
	}

	public static Task selectById(int id)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		return DAOTask.getInstance().selectById(id);
	}

	public static void insert(Task task)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		DAOTask.getInstance().insert(task);
	}

	public static void update(Task task)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		DAOTask.getInstance().update(task);
	}

	public static void delete(int id)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		DAOTask.getInstance().delete(id);
	}

}
