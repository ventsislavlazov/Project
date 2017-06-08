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
import model.classes.Knife;
import model.dao.DBKnifeDAO;

@WebServlet("/RemoveKnifeServlet")
public class RemoveKnifeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	DBKnifeDAO knifeDAO = new DBKnifeDAO();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		FilterSession filter = new FilterSession();
		filter.filter(request, response, session, "RemoveKnife.jsp");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int knifeId = Integer.parseInt(request.getParameter("knifeId").toString());
		HttpSession session = request.getSession();
		try {
			knifeDAO.removeKnifeFromDBByKnifeId(knifeId);
		} catch (MYSQLException e) {
			e.getMessage();
			e.printStackTrace();
			request.getRequestDispatcher("InternalServerError.jsp").forward(request, response);
		}
		ArrayList<Knife> currentKnifes = new ArrayList<>();
		try {
			currentKnifes = knifeDAO.getAllKnifesFromDB();
		} catch (MYSQLException e) {
			e.getMessage();
			e.printStackTrace();
			request.getRequestDispatcher("InternalServerError.jsp").forward(request, response);
		}
		
		try {
			setSession(session, currentKnifes);
		} catch (MYSQLException e) {
			e.getMessage();
			e.printStackTrace();
			request.getRequestDispatcher("InternalServerError.jsp").forward(request, response);
		}
		
		
		
		request.setAttribute("success", "you have removed the knife successfully");
		FilterSession filter = new FilterSession();
		filter.filter(request, response, session, "RemoveKnife.jsp");
	}
	
	public void setSession(HttpSession session, ArrayList<Knife> currentKnifes) throws MYSQLException{
		
		session.removeAttribute("allKnifes");
		session.setAttribute("allKnifes", currentKnifes);
		
		session.removeAttribute("cheapest");
		session.setAttribute("cheapest", knifeDAO.getTheThreeCheapest());
		
		session.removeAttribute("mostExpenisve");
		session.setAttribute("mostExpenisve", knifeDAO.getTheThreeMostExpensive());
		
		session.removeAttribute("lowestQuantity");
		session.setAttribute("lowestQuantity", knifeDAO.getTheThreeWithLowestQuantity());
		
		session.removeAttribute("lowestQuantityMoreThanZero");
		session.setAttribute("lowestQuantityMoreThanZero", knifeDAO.getLastTreeByQuantityMoreThanZero());
		
		session.removeAttribute("highestQuantity");
		session.setAttribute("highestQuantity", knifeDAO.getTheThreeWithHighestQuantity());
		
		session.removeAttribute("bestsellers");
		session.setAttribute("bestsellers", knifeDAO.getTheThreeBestSellers());
		
		session.removeAttribute("allSoldKnifes");
		session.setAttribute("allSoldKnifes", knifeDAO.getAllSoldKnifes());
	}

}
