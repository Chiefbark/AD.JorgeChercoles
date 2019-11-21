package model;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.DAOTask;

public class TaskList {

	private ArrayList<Task> list;

	public TaskList() {
		this.list = new ArrayList<Task>();
	}

	public TaskList(ArrayList<Task> list) {
		this.list = list;
	}

	public String toHTML() {
		String str = "";
		for (Task task : this.list)
			str += task.toHTML() + "\n";
		return str;
	}

	public String toHTMLForm(int id_selected) {
		String str = "";
		for (Task task : this.list) {
			if (task.getLevel() < 2)
				str += task.toHTMLForm(id_selected) + "\n";
		}
		return str;
	}

	private void orderList() {
		for (int ii = 0; ii < list.size(); ii++) {
			int id = ii;
			for (int jj = ii; jj < list.size(); jj++) {
				int idSub = 0;
				if (list.get(id).getId() == list.get(jj).getFk_dependency()) {
					swapOrder(ii + 1, jj);
					ii++;
					idSub = ii;
				}
				if (idSub != 0) {
					for (int kk = ii; kk < list.size(); kk++) {
						if (list.get(idSub).getId() == list.get(kk).getFk_dependency()) {
							swapOrder(ii + 1, kk);
							ii++;
						}
					}
				}
			}
		}
	}

	private void swapOrder(int pos1, int pos2) {
		Task aux = list.get(pos1);
		list.set(pos1, list.get(pos2));
		list.set(pos2, aux);
	}

	//
	// STATIC METHODS
	//

	public static TaskList selectAll()
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		TaskList list = DAOTask.getInstance().selectAll();
		list.orderList();
		return list;
	}
}
