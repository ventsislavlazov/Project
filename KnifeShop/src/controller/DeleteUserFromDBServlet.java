package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import exceptions.MySQLExseption;
import model.classes.FilterSession;
import model.classes.User;
import model.dao.DBUserDAO;

@WebServlet("/DeleteUserFromDBServlet")
public class DeleteUserFromDBServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	DBUserDAO userDAO = new DBUserDAO();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		FilterSession filter = new FilterSession();
		filter.filter(request, response, session, "DeleteUserFromDB.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int userId = Integer.parseInt(request.getParameter("user").toString());
		try {
			userDAO.deleteUserOrAdminFromDbByUsername(userId);
		} catch (MySQLExseption e) {
			e.getMessage();
			e.printStackTrace();
			request.getRequestDispatcher("InternalServerError.jsp").forward(request, response);
		}
		
		ArrayList<User> allUsersAfterTheChange = new ArrayList<>();
		try {
			allUsersAfterTheChange = userDAO.getAllUsersFromDB();
		} catch (MySQLExseption e) {
			e.getMessage();
			e.printStackTrace();
			request.getRequestDispatcher("InternalServerError.jsp").forward(request, response);
		}
		session.removeAttribute("allUsers");
		session.setAttribute("allUsers", allUsersAfterTheChange);
		
		request.setAttribute("seccess", "you have deleted the user successfully");
		FilterSession filter = new FilterSession();
		filter.filter(request, response, session, "DeleteUserFromDB.jsp");
	}

}
