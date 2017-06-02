package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import exceptions.MySQLExseption;
import model.classes.FilterSession;
import model.dao.DBFinanceDAO;

@WebServlet("/GetMoneyFromTheShopServlet")
public class GetMoneyFromTheShopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	DBFinanceDAO finanaceDAO = new DBFinanceDAO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		FilterSession filter = new FilterSession();
		filter.filter(request, response, session, "GetMoneyFromTheShop.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int moneyToTake = Integer.parseInt(request.getParameter("moneyToTake").toString());
		int currentMoneyInTheShop = 0;
		try {
			currentMoneyInTheShop = finanaceDAO.getAllMoneyByTheLastTransaction();
		} catch (MySQLExseption e) {
			e.getMessage();
			e.printStackTrace();
			request.getRequestDispatcher("InternalServerError.jsp").forward(request, response);
		}
		
		if(currentMoneyInTheShop>=moneyToTake){
			int moneyAfterTaking = currentMoneyInTheShop - moneyToTake;
			try {
				finanaceDAO.getMoneyFromTheShop(moneyAfterTaking);
			} catch (MySQLExseption e) {
				e.getMessage();
				e.printStackTrace();
				request.getRequestDispatcher("InternalServerError.jsp").forward(request, response);
			}
			
			String event = request.getParameter("commentName");
			if((!event.equals("")) && event != null){
				int transactionId = 0;
				try {
					transactionId = finanaceDAO.getTheLastTransactionsIdNewMethod();
				} catch (MySQLExseption e) {
					e.getMessage();
					e.printStackTrace();
					request.getRequestDispatcher("InternalServerError.jsp").forward(request, response);
				}
				try {
					finanaceDAO.updateEventByTransactionId(event, transactionId);
				} catch (MySQLExseption e) {
					e.getMessage();
					e.printStackTrace();
					request.getRequestDispatcher("InternalServerError.jsp").forward(request, response);
				}
			}
			
			session.removeAttribute("allMoney");
			session.setAttribute("allMoney", moneyAfterTaking);
			
			session.removeAttribute("allTransactions");
			try {
				session.setAttribute("allTransactions", finanaceDAO.getAllTransactions());
			} catch (MySQLExseption e) {
				e.getMessage();
				e.printStackTrace();
				request.getRequestDispatcher("InternalServerError.jsp").forward(request, response);
			}
			
			request.setAttribute("success", "you have taken " + moneyToTake
												+ " from the shop. The current ballance in the safe is "
												+ moneyAfterTaking);
		}else{
			request.setAttribute("errorNotEnaughtMoneyInTheShop", "there are not " 
									+ moneyToTake + " in the shop. You can take max " + currentMoneyInTheShop);
		}
		FilterSession filter = new FilterSession();
		filter.filter(request, response, session, "GetMoneyFromTheShop.jsp");
	}

}
