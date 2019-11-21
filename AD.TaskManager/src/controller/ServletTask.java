package controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Task;

/**
 * Servlet implementation class ServletTask
 */
@WebServlet("/ServletTask")
public class ServletTask extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletTask() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String option = "";
		int id = 0;
		try {
			option = request.getParameter("option");
			id = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException | NullPointerException e) {
		}
		if (option != null && option.equals("update")) {
			response.sendRedirect("tasks.jsp?id=" + id);
		} else if (option != null && option.equals("delete")) {
			try {
				Task.delete(id);
				response.sendRedirect("tasks.jsp");
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			String title = request.getParameter("title");
			String description = request.getParameter("description");
			int priority = Integer.parseInt(request.getParameter("priority"));
			int status = Integer.parseInt(request.getParameter("status"));
			String init_date = request.getParameter("init_date");
			String end_date = request.getParameter("end_date");
			int fk_category = Integer.parseInt(request.getParameter("category"));
			int fk_dependency = Integer.parseInt(request.getParameter("dependency"));

			Task task = new Task();
			task.setId(id);
			task.setTitle(title);
			task.setDescription(description);
			task.setPriority(priority);
			task.setStatus(status);

			SimpleDateFormat formatter = new SimpleDateFormat("yyy-MM-dd");
			try {
				java.util.Date aux = formatter.parse(init_date);
				Date init = new Date(aux.getTime());
				task.setInit_date(init);

				aux = formatter.parse(end_date);
				Date end = new Date(aux.getTime());
				task.setEnd_date(end);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			task.setFk_category(fk_category);
			task.setFk_dependency(fk_dependency);

			try {
				if (fk_dependency == 0)
					task.setLevel(0);
				else {
					Task dependency = Task.selectById(fk_dependency);
					task.setLevel(dependency.getLevel() + 1);
				}

				if (id == 0)
					Task.insert(task);
				else
					Task.update(task);
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.sendRedirect("tasks.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
