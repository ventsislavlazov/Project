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
import model.dao.DBKnifeDAO;

@WebServlet("/AddManufactorToDBServlet")
public class AddManufactorToDBServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	DBKnifeDAO knifeDAO = new DBKnifeDAO();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		FilterSession filter = new FilterSession();
		filter.filter(request, response, session, "AddManufactor.jsp");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String manufactor = request.getParameter("addManufactorNameToDB");
		if(manufactor != null && (!manufactor.equals(""))){
			try {
				if(!knifeDAO.isThereSuchManufactorInDB(manufactor)){
					knifeDAO.addManufactorToDB(manufactor);
					
					ArrayList<String> allManufactors = knifeDAO.getAllManufactorsNamesFromDB();
					session.removeAttribute("manufactor");
					session.setAttribute("manufactor", allManufactors);
					
					request.setAttribute("success", "you have added the new manufactor successfully");
				}else{
					request.setAttribute("errorExists", "you have already added this manufactor");
				}
			} catch (MySQLExseption e) {
				e.getMessage();
				e.printStackTrace();
				request.getRequestDispatcher("InternalServerError.jsp").forward(request, response);
			}
		}else{
			request.setAttribute("errorEmpty", "you should fill the name");
		}
		FilterSession filter = new FilterSession();
		filter.filter(request, response, session, "AddManufactor.jsp");
	}

}
