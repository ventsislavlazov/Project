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
		
		if(session.getAttribute("user") == null){
			session.invalidate();
			request.getRequestDispatcher("Login.jsp").forward(request, response);
		}else{
			request.getRequestDispatcher(to).forward(request, response);
		}
		
	}
	
}
