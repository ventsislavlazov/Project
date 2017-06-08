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

import exceptions.MYSQLException;
import model.classes.FilterSession;
import model.classes.User;
import model.dao.DBUserDAO;

@WebServlet("/MakeAdminToUserServlet")
public class MakeAdminToUserServlet extends HttpServlet implements Serializable{
	private static final long serialVersionUID = 1L;
	
	DBUserDAO userDAO = new DBUserDAO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		FilterSession filter = new FilterSession();
		filter.filter(request, response, session, "MakeAdminToUser.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int adminId = Integer.parseInt(request.getParameter("admin").toString());
		try {
			userDAO.makeAdminToUserByUserId(adminId);
		} catch (MYSQLException e) {
			e.getMessage();
			e.printStackTrace();
			request.getRequestDispatcher("InternalServerError.jsp").forward(request, response);
		}
		
		ArrayList<User> allUsers = new ArrayList<>();
		try {
			allUsers = userDAO.getAllUsersFromDB();
		} catch (MYSQLException e) {
			e.getMessage();
			e.printStackTrace();
			request.getRequestDispatcher("InternalServerError.jsp").forward(request, response);
		}
		session.removeAttribute("allUsers");
		session.setAttribute("allUsers", allUsers);
		
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
		
		request.setAttribute("success", "you had changed the status successfully");
		FilterSession filter = new FilterSession();
		filter.filter(request, response, session, "MakeAdminToUser.jsp");
	}

}
