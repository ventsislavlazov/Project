package controller;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
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
import model.classes.User;
import model.dao.DBBasketDAO;
import model.dao.DBColorDAO;
import model.dao.DBFinanceDAO;
import model.dao.DBKnifeDAO;
import model.dao.DBUserDAO;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet implements Serializable{
	
	private static final long serialVersionUID = 7526472295622776147L;
	
	DBUserDAO userDAO = new DBUserDAO();
	DBKnifeDAO knifeDAO = new DBKnifeDAO();
	DBBasketDAO basketDAO = new DBBasketDAO();
	DBFinanceDAO financeDAO = new DBFinanceDAO();
	DBColorDAO colorDAO = new DBColorDAO();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getRequestURL().toString().equals("http://localhost:8080/KnifeShop/LoginServlet")){
			request.getRequestDispatcher("Login.jsp").forward(request, response);
		}else{
				request.getRequestDispatcher("404.jsp").forward(request, response);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		//maham wsi4ki atributi ot sesiqta, za da dobawqm samo nowite pri login
		if(!session.isNew()){
			session.invalidate();
			session = request.getSession();
		}
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		int userId = 0;
		try {
			userId = userDAO.getUserIdByUsernameAndPassword(username, password);
		} catch (MySQLExseption e) {
			e.getMessage();
			e.printStackTrace();
			request.getRequestDispatcher("InternalServerError.jsp").forward(request, response);
		}
		//ako sa wawedeni ime i parola
		if(username != null && password != null && (!username.equals("")) && (!password.equals(""))){
			//ako w bazata s danni ima takaw potrebitel, zna4i we4e e registriran
			try {
				if(userDAO.isThereSuchUserInDBByUsernameAndPassword(username, password)){
					
					
					//ako potrebitelqt ne e w sesiqta
					if(session.getAttribute("user") == null){
						//slagam teku6tiq potrebitel w sesiqta
						User user = userDAO.getUserByUsernameFromDB(username);
						session.setAttribute("user", user);
					}

					//ako potrebitelqt e glawniqt administrator ima osnownite ne6ta za administrator i o6te
//				if(username.equals("adminMaster") && password.equals("adminMaster123")){
					//prowerqwam ot bazata dail e glawniqt administrator
					if(userDAO.isTheUserMasterAdminByUserId(userId)){
						basicAdminSessionAttributes(session);
						session.setAttribute("allUsers", userDAO.getAllUsersFromDB());
						session.setAttribute("allAdmins", userDAO.getAllAdminsFromDB());
						session.setAttribute("which", "masterAdmin");
						//sesiqta na glawniq administrator e 15 min
						session.setMaxInactiveInterval(15*60);
						request.getRequestDispatcher("AdminMainPage.jsp").forward(request, response);
					}else{
						//ako e administrator
						if(userDAO.isTheUserAdminByUserId(userDAO.getUserIdByUsernameAndPassword(username, password))){
							basicAdminSessionAttributes(session);
							session.setAttribute("which", "admin");
							session.setMaxInactiveInterval(10*60);
							request.getRequestDispatcher("AdminWithoutControlUsersMainPage.jsp").forward(request, response);
						}else{
							//ako e potrebitel
							basicUserSessionAttributes(session, userId);
							session.setAttribute("which", "user");
							session.setMaxInactiveInterval(5*60);
							if(basketDAO.getAllKnifesFromTheBasketByUserId(userId) != null){
								//deletesItemsFromBasketIfTheTimeHasGone(userId);
							}
							request.getRequestDispatcher("UserMainPage.jsp").forward(request, response);
						}
					}
				}else{
					request.setAttribute("errorRegister", "invalid username or/and password, maybe you should make a registration first");
					request.getRequestDispatcher("Login.jsp").forward(request, response);
				}
			} catch (MySQLExseption e) {
				e.getMessage();
				e.printStackTrace();
				request.getRequestDispatcher("InternalServerError.jsp").forward(request, response);
			}
		}else{
			request.setAttribute("error", "username and/or password is/are empty");
			request.getRequestDispatcher("Login.jsp").forward(request, response);
		}
		
		
	}
	
	private void deletesItemsFromBasketIfTheTimeHasGone(int userId) throws MySQLExseption {
		LocalDateTime currentDateTime = LocalDateTime.now();
		//da naprawq koga ko6nicata e pipana za posledno, a ne nojowete
		ArrayList<Knife> knifesInTheBasket = basketDAO.getAllKnifesFromTheBasketByUserId(userId);
		for(Knife knife : knifesInTheBasket){
			if(currentDateTime.isAfter(knife.getCurrentDateTimeWhenIsAddedToTheBasket().plusMinutes(1))){
				int currentQuantityInTheShop = knife.getQuantity();
				int currentQuantityInTheBasket = knife.getQuantityBasket();
				basketDAO.removeKnifeFromBasketByKnifeId(userId, knife.getId());
				knifeDAO.addQuantityToDB(currentQuantityInTheShop + currentQuantityInTheBasket, knife.getId());
			}
		}
	}

	public void basicUserSessionAttributes(HttpSession session, int userId) throws MySQLExseption {
		session.setAttribute("knifesInTheBasket",basketDAO.getAllKnifesFromTheBasketByUserId(userId));
		session.setAttribute("allKnifes", knifeDAO.getAllKnifesFromDB());
		session.setAttribute("userId", userId);
		session.setAttribute("color", colorDAO.getTheCurrentColor());
		session.setAttribute("cheapest", knifeDAO.getTheThreeCheapest());
		session.setAttribute("lowestQuantity", knifeDAO.getTheThreeWithLowestQuantity());
	}

	public void basicAdminSessionAttributes(HttpSession session) throws MySQLExseption {
		session.setAttribute("manufactor", knifeDAO.getAllManufactorsNamesFromDB());
		session.setAttribute("steel", knifeDAO.getAllSteelsNamesFromDB());
		session.setAttribute("lock", knifeDAO.getAllLocksNamesFromDB());
		session.setAttribute("allKnifes", knifeDAO.getAllKnifesFromDB());
		session.setAttribute("allMoney", financeDAO.getAllMoneyByTheLastTransaction());
		session.setAttribute("allTransactions", financeDAO.getAllTransactions());
		session.setAttribute("allSoldKnifes", knifeDAO.getAllSoldKnifes());
		session.setAttribute("bestsellers", knifeDAO.getTheThreeBestSellers());
		session.setAttribute("cheapest", knifeDAO.getTheThreeCheapest());
		session.setAttribute("mostExpenisve", knifeDAO.getTheThreeMostExpensive());
		session.setAttribute("lowestQuantity", knifeDAO.getTheThreeWithLowestQuantity());
		session.setAttribute("highestQuantity", knifeDAO.getTheThreeWithHighestQuantity());
		session.setAttribute("color", colorDAO.getTheCurrentColor());
	}

}
