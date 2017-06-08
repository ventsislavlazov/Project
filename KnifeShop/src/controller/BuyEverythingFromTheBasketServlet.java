package controller;

import java.io.IOException;
import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.annotation.ManagedBean;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import exceptions.MYIOException;
import exceptions.MYSQLException;
import model.classes.FilterSession;
import model.classes.Knife;
import model.dao.DBBasketDAO;
import model.dao.DBFinanceDAO;
import model.dao.DBKnifeDAO;

@WebServlet("/BuyEverythingFromTheBasketServlet")
public class BuyEverythingFromTheBasketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	DBKnifeDAO knifeDAO = new DBKnifeDAO();
	DBFinanceDAO financeDAO = new DBFinanceDAO();
	DBBasketDAO basketDAO = new DBBasketDAO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int userId = (int) session.getAttribute("userId");
		//ArrayList<Knife> allSoldKnifes = new ArrayList<>();
		//namalqwame koli4estwoto na wsi4ki nojowe s koli4estwoto na nojowete ot koli4kata
		ArrayList<Knife> allKnifesFromTheBasket = (ArrayList<Knife>) session.getAttribute("knifesInTheBasket");
//		for(Knife knife : allKnifesFromTheBasket){
//			//pri slaganeto na noj w koli4kata namalqwame koli4estwoto mu w magazina
//			//knifeDAO.decreezeKnifeQuantityByKnifeId(knife.getId(), knife.getQuantityBasket());
//			//uweli4awame parite na magazina s cenite na wseki ot nojowete
//			if( knife.getQuantityBasket()>1){
//				for(int i = 0; i<knife.getQuantityBasket(); i++){
//					int moneyToAdd = knife.getPrice();
//					int currentMoneyInTheShop = financeDAO.getAllMoneyByTheLastTransaction();
//					int moneyAfterAdding = currentMoneyInTheShop + moneyToAdd;
//					financeDAO.addMoneyToTheShop(moneyAfterAdding);
//				}
//			}else{
//				int moneyToAdd = knife.getPrice();
//				int currentMoneyInTheShop = financeDAO.getAllMoneyByTheLastTransaction();
//				int moneyAfterAdding = currentMoneyInTheShop + moneyToAdd;
//				financeDAO.addMoneyToTheShop(moneyAfterAdding);
//			}
//			//allSoldKnifes.add(knife);
//			//dobawqme broq mu kam prodadenite nojowe
//			knifeDAO.addSoldKnifeQuantity(knife.getId(), knife.getQuantityBasket());
//			//mahame we4e prodadenite nojowe ot koli4kata
//			basketDAO.removeKnifeFromAllBasketsFromAdminByKnifeId(knife.getId());
//		}
		
		try {
			financeDAO.makeTransactionAddMoneySellKnife(allKnifesFromTheBasket, userId);
		} catch (MYIOException e) {
			e.getMessage();
			e.printStackTrace();
			request.getRequestDispatcher("InternalServerError.jsp").forward(request, response);
		} catch (MYSQLException e) {
			e.getMessage();
			e.printStackTrace();
			request.getRequestDispatcher("InternalServerError.jsp").forward(request, response);
		}
		
		//slagame gi w sesiqta kato prodadeni nojowe, za da gi imame w istoriqta
		session.removeAttribute("allSoldKnifes");
		try {
			session.setAttribute("allSoldKnifes", knifeDAO.getAllSoldKnifes());
		} catch (MYSQLException e) {
			e.getMessage();
			e.printStackTrace();
			request.getRequestDispatcher("InternalServerError.jsp").forward(request, response);
		}
		//w sesiqta slagame trite naj - prodawani nojowe
		session.removeAttribute("bestsellers");
		try {
			session.setAttribute("bestsellers", knifeDAO.getTheThreeBestSellers());
		} catch (MYSQLException e) {
			e.getMessage();
			e.printStackTrace();
			request.getRequestDispatcher("InternalServerError.jsp").forward(request, response);
		}
		//w sesiqta slagame trite naj - ewtini noja
		session.removeAttribute("cheapest");
		try {
			session.setAttribute("cheapest", knifeDAO.getTheThreeCheapest());
		} catch (MYSQLException e) {
			e.getMessage();
			e.printStackTrace();
			request.getRequestDispatcher("InternalServerError.jsp").forward(request, response);
		}
		//w sesiqta slagame trite naj - skapi noja
		session.removeAttribute("mostExpenisve");
		try {
			session.setAttribute("mostExpenisve", knifeDAO.getTheThreeMostExpensive());
		} catch (MYSQLException e) {
			e.getMessage();
			e.printStackTrace();
			request.getRequestDispatcher("InternalServerError.jsp").forward(request, response);
		}
		//w sesiqta slagame trite noja s naj - malko koli4estwo
		session.removeAttribute("lowestQuantity");
		try {
			session.setAttribute("lowestQuantity", knifeDAO.getTheThreeWithLowestQuantity());
		} catch (MYSQLException e) {
			e.getMessage();
			e.printStackTrace();
			request.getRequestDispatcher("InternalServerError.jsp").forward(request, response);
		}
		
		//w sesiqta slagame trite noja s naj - malko koli4estwo po-golqmo ot 0
				session.removeAttribute("lowestQuantityMoreThanZero");
				try {
					session.setAttribute("lowestQuantityMoreThanZero", knifeDAO.getLastTreeByQuantityMoreThanZero());
				} catch (MYSQLException e) {
					e.getMessage();
					e.printStackTrace();
					request.getRequestDispatcher("InternalServerError.jsp").forward(request, response);
				}
		

		//w sesiqta slagame trite noja s naj - golqmo koli4estwo
		session.removeAttribute("highestQuantity");
		try {
			session.setAttribute("highestQuantity", knifeDAO.getTheThreeWithHighestQuantity());
		} catch (MYSQLException e) {
			e.getMessage();
			e.printStackTrace();
			request.getRequestDispatcher("InternalServerError.jsp").forward(request, response);
		}
		
		try {
			setSession(session);
		} catch (MYSQLException e1) {
			e1.getMessage();
			e1.printStackTrace();
			request.getRequestDispatcher("InternalServerError.jsp").forward(request, response);
		}
		
		
		//slagame w sesiqta nowite tranzakcii
		session.removeAttribute("allTransactions");
		try {
			session.setAttribute("allTransactions", financeDAO.getAllTransactions());
		} catch (MYSQLException e) {
			e.getMessage();
			e.printStackTrace();
			request.getRequestDispatcher("InternalServerError.jsp").forward(request, response);
		}
		request.setAttribute("success", "you successfully byued all items");
		FilterSession filter = new FilterSession();
		filter.filter(request, response, session, "Basket.jsp");
	}
	
	public void setSession(HttpSession session) throws MYSQLException{
		//mahame we4e prodadenite nojowe ot sessiqta s koli4kata
		session.removeAttribute("knifesInTheBasket");
		session.setAttribute("knifesInTheBasket", new ArrayList<>());
		
		session.removeAttribute("bestsellers");
		session.setAttribute("bestsellers", knifeDAO.getTheThreeBestSellers());
		
		session.removeAttribute("allSoldKnifes");
		session.setAttribute("allSoldKnifes", knifeDAO.getAllSoldKnifes());
	}

}
