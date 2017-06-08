package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import exceptions.MYSQLException;
import model.classes.FilterSession;
import model.classes.User;
import model.dao.DBUserDAO;

@WebServlet("/DeleteAdminFromDBServlet")
public class DeleteAdminFromDBServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	DBUserDAO userDAO = new DBUserDAO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		FilterSession filter = new FilterSession();
		filter.filter(request, response, session, "DeleteAdminFromDB.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int userId = Integer.parseInt(request.getParameter("admin").toString());
		try {
			userDAO.deleteUserOrAdminFromDbByUsername(userId);
		} catch (MYSQLException e) {
			e.getMessage();
			e.printStackTrace();
			request.getRequestDispatcher("InternalServerError.jsp").forward(request, response);
		}
		
		ArrayList<User> allAdminsAfterTheChange = new ArrayList<>();
		try {
			allAdminsAfterTheChange = userDAO.getAllAdminsFromDB();
		} catch (MYSQLException e) {
			e.getMessage();
			e.printStackTrace();
			request.getRequestDispatcher("InternalServerError.jsp").forward(request, response);
		}
		session.removeAttribute("allAdmins");
		session.setAttribute("allAdmins", allAdminsAfterTheChange);
		
		request.setAttribute("success", "you have deleted the admin successfully");
		FilterSession filter = new FilterSession();
		filter.filter(request, response, session, "DeleteAdminFromDB.jsp");
	}

}
