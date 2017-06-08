package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import exceptions.MYSQLException;
import model.classes.Knife;
import model.dao.DBKnifeDAO;

@WebServlet("/SortByHighestPriceServlet")
public class SortByHighestPriceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	DBKnifeDAO knifeDAO = new DBKnifeDAO();

//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		ArrayList<Knife> knifesSortedByHighestPrice = new ArrayList<>();
		try {
			knifesSortedByHighestPrice = knifeDAO.getAllKnifesSortByHighestPrice();
		} catch (MYSQLException e) {
			e.getMessage();
			e.printStackTrace();
			request.getRequestDispatcher("InternalServerError.jsp").forward(request, response);
		}
		session.removeAttribute("allKnifes");
		session.setAttribute("allKnifes", knifesSortedByHighestPrice);
		//request.getRequestDispatcher("UserViewAllKnifes.jsp").forward(request, response);
		JsonArray array = new JsonArray();
		for (Knife knife : knifesSortedByHighestPrice) {
			JsonObject obj = new JsonObject();
			obj.addProperty("id", knife.getId());
			obj.addProperty("length", knife.getLength());
			obj.addProperty("steel", knife.getSteel());
			obj.addProperty("manufactor", knife.getManufactor());
			obj.addProperty("model", knife.getModel());
			obj.addProperty("price", knife.getPrice());
			obj.addProperty("lock", knife.getLock());
			obj.addProperty("folder", knife.isFolder());
			obj.addProperty("imageName", knife.getImageName());
			obj.addProperty("quantity", knife.getQuantity());
			array.add(obj);
			}
		
		if(session.getAttribute("user") == null){
			session.invalidate();
			request.getRequestDispatcher("Login.jsp").forward(request, response);
		}else{
			response.getWriter().write(array.toString());
		}
		
	}

}
