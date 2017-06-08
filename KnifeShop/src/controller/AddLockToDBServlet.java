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
import model.dao.DBKnifeDAO;

@WebServlet("/AddLockToDBServlet")
public class AddLockToDBServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	DBKnifeDAO knifeDAO = new DBKnifeDAO();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		FilterSession filter = new FilterSession();
		filter.filter(request, response, session, "AddLock.jsp");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String lock = request.getParameter("addLockNameToDB");
		if(lock != null && (!lock.equals(""))){
			try {
				if(!knifeDAO.isThereSuchLockInDB(lock)){
					knifeDAO.addLockToDB(lock);

					ArrayList<String> allLocks = knifeDAO.getAllLocksNamesFromDB();
					session.removeAttribute("lock");
					session.setAttribute("lock", allLocks);
					
					request.setAttribute("success", "you have added the new lock successfully");
				}else{
					request.setAttribute("errorExists", "you have already added this lock");
				}
			} catch (MYSQLException e) {
				e.getMessage();
				e.printStackTrace();
				request.getRequestDispatcher("InternalServerError.jsp").forward(request, response);
			}
		}else{
			request.setAttribute("errorEmpty", "you should fill the name");
		}
		FilterSession filter = new FilterSession();
		filter.filter(request, response, session, "AddLock.jsp");
	}

}
