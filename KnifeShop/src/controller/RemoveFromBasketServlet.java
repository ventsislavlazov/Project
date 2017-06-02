package controller;

import java.io.IOException;
import java.nio.file.WatchEvent.Kind;
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
import model.dao.DBBasketDAO;
import model.dao.DBKnifeDAO;


@WebServlet("/RemoveFromBasketServlet")
public class RemoveFromBasketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	DBBasketDAO basketDAO = new DBBasketDAO();
	DBKnifeDAO knifeDAO = new DBKnifeDAO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		int knifeId = Integer.parseInt(request.getParameter("knifeId").toString());
		int currentQuantityInTheShop = 0;
		try {
			currentQuantityInTheShop = knifeDAO.getQuantityForCurrentKnifeByKnifeId(knifeId);
		} catch (MySQLExseption e) {
			e.getMessage();
			e.printStackTrace();
			request.getRequestDispatcher("InternalServerError.jsp").forward(request, response);
		}
		int currentUserId = Integer.parseInt(session.getAttribute("userId").toString());
		int knifeQuantityInTheBasket = Integer.parseInt(request.getParameter("knifeQuantityInTheBasket").toString());
		int knifeQuantityToRemove = Integer.parseInt(request.getParameter("quantityToRemove").toString());
		String knifeModel = request.getParameter("knifeModel").toString();
		
		System.out.println("quantity to remove " + knifeQuantityToRemove);
		
		if(knifeQuantityInTheBasket >= knifeQuantityToRemove){
			try {
				basketDAO.removeQuantitFromKnifeInTheBasket(currentUserId, knifeId, knifeQuantityToRemove);
			} catch (MySQLExseption e) {
				e.getMessage();
				e.printStackTrace();
				request.getRequestDispatcher("InternalServerError.jsp").forward(request, response);
			}
			//ako sled mahaneto na koli4estwo, koli4estwoto e stanalo 0
			try {
				if(basketDAO.getQuantityForKnifeFromBasket(currentUserId, knifeId) == 0){
					//maham noja ot koli4kata
					basketDAO.removeKnifeFromBasketByKnifeId(currentUserId, knifeId);
				}
			} catch (MySQLExseption e) {
				e.getMessage();
				e.printStackTrace();
				request.getRequestDispatcher("InternalServerError.jsp").forward(request, response);
			}
			try {
				knifeDAO.addQuantityToDB(currentQuantityInTheShop + knifeQuantityToRemove, knifeId);
			} catch (MySQLExseption e) {
				e.getMessage();
				e.printStackTrace();
				request.getRequestDispatcher("InternalServerError.jsp").forward(request, response);
			}
			
			ArrayList<Knife> allKnifesFromTheBasket = new ArrayList<>();
			try {
				allKnifesFromTheBasket = basketDAO.getAllKnifesFromTheBasketByUserId(currentUserId);
			} catch (MySQLExseption e) {
				e.getMessage();
				e.printStackTrace();
				request.getRequestDispatcher("InternalServerError.jsp").forward(request, response);
			}
			session.removeAttribute("knifesInTheBasket");
			session.setAttribute("knifesInTheBasket", allKnifesFromTheBasket);
			
			request.setAttribute("success", "you have removed the requested quantity successfully");
		}else{
			request.setAttribute("errorNotEnoghtQuantity", "you have not " + knifeQuantityToRemove + 
					" from knife \"" + knifeModel +"\" in the basket. You can remove max " + knifeQuantityInTheBasket);
		}
		FilterSession filter = new FilterSession();
		filter.filter(request, response, session, "Basket.jsp");
	}

}
