package controller;

import java.io.IOException;
import java.io.Serializable;
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

@WebServlet("/MakeUserToAdminServlet")
public class MakeUserToAdminServlet extends HttpServlet implements Serializable{
	private static final long serialVersionUID = 1L;

	DBUserDAO userDAO = new DBUserDAO();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		FilterSession filter = new FilterSession();
		filter.filter(request, response, session, "MakeUserToAdmin.jsp");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int userId = Integer.parseInt(request.getParameter("user").toString());
		try {
			userDAO.makeUserToAdminByUserId(userId);
		} catch (MySQLExseption e) {
			e.getMessage();
			e.printStackTrace();
			request.getRequestDispatcher("InternalServerError.jsp").forward(request, response);
		}
		
		ArrayList<User> allAdmins = new ArrayList<>();
		try {
			allAdmins = userDAO.getAllAdminsFromDB();
		} catch (MySQLExseption e) {
			e.getMessage();
			e.printStackTrace();
			request.getRequestDispatcher("InternalServerError.jsp").forward(request, response);
		}
		session.removeAttribute("allAdmins");
		session.setAttribute("allAdmins", allAdmins);
		
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
		
		
		request.setAttribute("success", "you had changed the status successfully");
		FilterSession filter = new FilterSession();
		filter.filter(request, response, session, "MakeUserToAdmin.jsp");
	}

}
