package controller;

import java.io.IOException;
import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import exceptions.MYSQLException;
import model.classes.FilterSession;
import model.classes.Knife;
import model.dao.DBBasketDAO;
import model.dao.DBKnifeDAO;

@WebServlet("/AddToBasketServlet")
public class AddToBasketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	DBBasketDAO basketDAO = new DBBasketDAO();
	DBKnifeDAO knifeDAO = new DBKnifeDAO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		//if(request.getParameter("quantityToAdd")!=null && isValidInteger(request.getParameter("quantityToAdd").toString())){
		int currentUserId = Integer.parseInt(session.getAttribute("userId").toString());
		int knifeToAddId = Integer.parseInt(request.getParameter("knifeToAddId").toString());
		int requestedQuantity = Integer.parseInt(request.getParameter("quantityToAdd").toString());
		String knifeModel = request.getParameter("knifeToAddModel");
		int totalQuantityForThisKnife = 0;
		try {
			totalQuantityForThisKnife = knifeDAO.getQuantityByKnifeId(knifeToAddId);
		} catch (MYSQLException e) {
			e.getMessage();
			e.printStackTrace();
			request.getRequestDispatcher("InternalServerError.jsp").forward(request, response);
		}
		
		//ako ot iskaniq noj ima koli4estwo
		try {
			if(knifeDAO.getQuantityByKnifeId(knifeToAddId) > 0){
				//ako ima dostata4no koli4estwo
				if(knifeDAO.isThereEnaughtQuantityFromThisKnifeByKnifeId(knifeToAddId, requestedQuantity)){
					//ako we4e ima dobaweno koli4estwo ot tozi noj w koli4kata, se dobawq nowoto kam staroto koli4estwo
					if(basketDAO.isThereSuchKnifeInTheBasketByKnifeId(currentUserId, knifeToAddId)){
						basketDAO.addMoreQuantityToKnifeInBasket(currentUserId, knifeToAddId, requestedQuantity);
						request.setAttribute("successAddMore", "you have added " + requestedQuantity + 
								"more from knife \"" + knifeModel + "\"successfully to the basket");
					}else{//ako ot tozi noj nqma dobaweno koli4estwo w koli4kata, se slaga samo sega6noto koli4estwo
						try {
							basketDAO.addKnifeToBasketByKnifeId(currentUserId, knifeToAddId, requestedQuantity);
						} catch (MYSQLException e) {
							e.getMessage();
							e.printStackTrace();
							request.getRequestDispatcher("InternalServerError.jsp").forward(request, response);
						}
						request.setAttribute("successAdd", "you have added " + requestedQuantity + 
								" from knife \"" + knifeModel + "\"successfully to the basket");
					}
					
					LocalDateTime now = LocalDateTime.now();
					Knife knife = knifeDAO.getKnifeByKnifeId(knifeToAddId);
					knife.setCurrentDateTimeWhenIsAddedToTheBasket(now);
					System.out.println("add " + knife.getCurrentDateTimeWhenIsAddedToTheBasket());
					
					knifeDAO.decreezeKnifeQuantityByKnifeId(knifeToAddId, requestedQuantity);
					
					ArrayList<Knife> allKnifesFromTheBasket = new ArrayList<>();
					allKnifesFromTheBasket = basketDAO.getAllKnifesFromTheBasketByUserId(currentUserId);
					
					session.removeAttribute("knifesInTheBasket");
					session.setAttribute("knifesInTheBasket", allKnifesFromTheBasket);
					
				}else{//ako nqma dostata4no koli4estwo se wra6ta saob6tebie
					request.setAttribute("notEnaughtQuantity", 
							"there is not such quantity, you can add max "
							+ totalQuantityForThisKnife + " from knife : " + knifeModel);
				}
			}else{//ako ot iskaniq noj nqma koli4estwo
				request.setAttribute("noQuantity", 
						"there is not quantity, from knife : " + knifeModel);
			}
		} catch (MYSQLException e) {
			e.getMessage();
			e.printStackTrace();
			request.getRequestDispatcher("InternalServerError.jsp").forward(request, response);
		}
		FilterSession filter = new FilterSession();
		filter.filter(request, response, session, "UserViewAllKnifes.jsp");
//	}else{
//		//redirect to 404 page
//	}
		}

	private boolean isValidInteger(String attribute) {
		//da ne e "" prazno
		//gleda6 dali sa validni ascii kodovete na vseki char ot stringa
		return false;
	}

}
