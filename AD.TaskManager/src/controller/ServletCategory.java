package controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Category;

/**
 * Servlet implementation class ServletCategory
 */
@WebServlet("/ServletCategory")
public class ServletCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletCategory() {
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
			response.sendRedirect("categories.jsp?id=" + id);
		} else if (option != null && option.equals("delete")) {
			try {
				Category.delete(id);
				response.sendRedirect("categories.jsp");
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			String name = request.getParameter("name");

			Category category = new Category();
			category.setId(id);
			category.setName(name);

			try {

				if (id == 0)
					Category.insert(category);
				else
					Category.update(category);
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.sendRedirect("categories.jsp");
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
