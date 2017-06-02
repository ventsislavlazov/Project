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
import model.classes.Knife;
import model.dao.DBKnifeDAO;

@WebServlet("/AddQuantityServlet")
public class AddQuantityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	DBKnifeDAO knifeDAO = new DBKnifeDAO();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		FilterSession filter = new FilterSession();
		filter.filter(request, response, session, "AddQuantity.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int quantityToAdd;
		int knifeId = Integer.parseInt(request.getParameter("knifeId").toString());
		if(request.getParameter("quantity").equals("") || request.getParameter("quantity") == null){
			quantityToAdd=0;
		}else{
			quantityToAdd = Integer.parseInt(request.getParameter("quantity"));
		}
		if(quantityToAdd > 0){
			int currentQuantity=0;
			try {
				currentQuantity = knifeDAO.getQuantityForCurrentKnifeByKnifeId(knifeId);
			} catch (MySQLExseption e) {
				e.getMessage();
				e.printStackTrace();
				request.getRequestDispatcher("InternalServerError.jsp").forward(request, response);
			}
			currentQuantity+=quantityToAdd;
			try {
				knifeDAO.addQuantityToDB(currentQuantity, knifeId);
			} catch (MySQLExseption e) {
				e.getMessage();
				e.printStackTrace();
				request.getRequestDispatcher("InternalServerError.jsp").forward(request, response);
			}
			
			
			ArrayList<Knife> currentKnifes = new ArrayList<>();
			try {
				currentKnifes = knifeDAO.getAllKnifesFromDB();
			} catch (MySQLExseption e) {
				e.getMessage();
				e.printStackTrace();
				request.getRequestDispatcher("InternalServerError.jsp").forward(request, response);
			}
			
			try {
				setSession(session, currentKnifes);
			} catch (MySQLExseption e) {
				e.getMessage();
				e.printStackTrace();
				request.getRequestDispatcher("InternalServerError.jsp").forward(request, response);
			}
			
			
			request.setAttribute("success", "you have added the quantity successfully");
		}else{
			request.setAttribute("errorQuantity", "the quantity has to be greater than 0");
		}
		FilterSession filter = new FilterSession();
		filter.filter(request, response, session, "AddQuantity.jsp");
	}
	
	public void setSession(HttpSession session, ArrayList<Knife> currentKnifes) throws MySQLExseption{
		session.removeAttribute("allKnifes");
		session.setAttribute("allKnifes", currentKnifes);
		
		//slagam nanowo w sesiqta informaciqta za statistikite, za da se promenq dinami4no, bez da trqbwa da se logwa6
		session.removeAttribute("cheapest");
		session.setAttribute("cheapest", knifeDAO.getTheThreeCheapest());
		
		session.removeAttribute("mostExpenisve");
		session.setAttribute("mostExpenisve", knifeDAO.getTheThreeMostExpensive());
		
		session.removeAttribute("lowestQuantity");
		session.setAttribute("lowestQuantity", knifeDAO.getTheThreeWithLowestQuantity());
		
		session.removeAttribute("highestQuantity");
		session.setAttribute("highestQuantity", knifeDAO.getTheThreeWithHighestQuantity());
	}

}
