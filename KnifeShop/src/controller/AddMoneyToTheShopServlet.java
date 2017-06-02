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

@WebServlet("/AddMoneyToTheShopServlet")
public class AddMoneyToTheShopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	DBFinanceDAO finanaceDAO = new DBFinanceDAO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		FilterSession filter = new FilterSession();
		filter.filter(request, response, session, "AddMoneyToTheShop.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int moneyToAdd = Integer.parseInt(request.getParameter("moneyToAdd").toString());
		int currentMoneyInTheShop=0;
		try {
			currentMoneyInTheShop = finanaceDAO.getAllMoneyByTheLastTransaction();
		} catch (MySQLExseption e) {
			e.getMessage();
			e.printStackTrace();
			request.getRequestDispatcher("InternalServerError.jsp").forward(request, response);
		}
		int moneyAfterAdding = currentMoneyInTheShop + moneyToAdd;
		String event = request.getParameter("commentName");
		try {
			finanaceDAO.addMoneyToTheShop(moneyAfterAdding);
		} catch (MySQLExseption e) {
			e.getMessage();
			e.printStackTrace();
			request.getRequestDispatcher("InternalServerError.jsp").forward(request, response);
		}
		if((!event.equals("")) && event != null){
			int transactionId=0;
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
		session.setAttribute("allMoney", moneyAfterAdding);
		
		session.removeAttribute("allTransactions");
		try {
			session.setAttribute("allTransactions", finanaceDAO.getAllTransactions());
		} catch (MySQLExseption e) {
			e.getMessage();
			e.printStackTrace();
			request.getRequestDispatcher("InternalServerError.jsp").forward(request, response);
		}
		
		request.setAttribute("success", "you have added " + moneyToAdd + " in the shop successfully. The current balanse is "
											+ moneyAfterAdding);
		FilterSession filter = new FilterSession();
		filter.filter(request, response, session, "AddMoneyToTheShop.jsp");
	}

}
