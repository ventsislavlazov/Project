package model.classes;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.DBBasketDAO;
import model.dao.DBKnifeDAO;

public class FilterSession{

	public void filter(HttpServletRequest request, HttpServletResponse response, HttpSession session, String to) throws ServletException, IOException{
		System.out.println("1User is " + session.getAttribute("user"));

		DBBasketDAO basketDAO = new DBBasketDAO();
		DBKnifeDAO knifeDAO = new DBKnifeDAO();
		//System.out.println("is null " + session.getAttribute("user").equals("null"));
		
		if(session.getAttribute("user") == null){
			//DA IZTRIWAM NOJOWETE OT KO6NICATA KOGATO IZTE4E SESIQTA
//			if(session.getAttribute("which").equals("user")){
//				int userId = Integer.parseInt(session.getAttribute("userId").toString());
//				ArrayList<Knife> knifesInBasket = basketDAO.getAllKnifesFromTheBasketByUserId(userId);
//				for(Knife knife : knifesInBasket){
//					int currentQuantityInTheShop = knife.getQuantity();
//					int currentQuantityInTheBasket = knife.getQuantityBasket();
//					knifeDAO.addQuantityToDB(currentQuantityInTheShop+currentQuantityInTheBasket, knife.getId());
//				}
//				//mahame wsi4ki nojowe ot ko6nicata na user-a
//				basketDAO.removeQuantityForSpecificUserByUserId(userId);
//			}
			session.invalidate();
			request.getRequestDispatcher("Login.jsp").forward(request, response);
		}else{
			request.getRequestDispatcher(to).forward(request, response);
		}
		
	}
	
}
