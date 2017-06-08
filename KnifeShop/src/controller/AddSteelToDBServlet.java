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

@WebServlet("/AddSteelToDBServlet")
public class AddSteelToDBServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	DBKnifeDAO knifeDAO = new DBKnifeDAO();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		FilterSession filter = new FilterSession();
		filter.filter(request, response, session, "AddSteel.jsp");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String steel = request.getParameter("addSteelNameToDB");
		if(steel != null && (!steel.equals(""))){
			try {
				if(!knifeDAO.isThereSuchSteelInDB(steel)){
					knifeDAO.addSteelToDB(steel);

					ArrayList<String> allSteels = knifeDAO.getAllSteelsNamesFromDB();
					session.removeAttribute("steel");
					session.setAttribute("steel", allSteels);
					
					request.setAttribute("success", "you have added the new steel successfully");
				}else{
					request.setAttribute("errorExists", "you have already added this steel");
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
		filter.filter(request, response, session, "AddSteel.jsp");
	}

}
