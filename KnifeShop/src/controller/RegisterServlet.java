package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import exceptions.MySQLExseption;
import model.classes.User;
import model.dao.DBUserDAO;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	
	DBUserDAO userDAO = new DBUserDAO();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("Register.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username").trim();
		String password = request.getParameter("password").trim();
		String email = request.getParameter("email").trim();
		int ageFromJsp = 0;
		if(!request.getParameter("age").equals("")){
			ageFromJsp = Integer.parseInt(request.getParameter("age").toString());
		}
		
		int age=0;
		
		HttpSession session = request.getSession();
		
//		if((!(request.getParameter("age").toString().equals("")))){
//			System.out.println(1);
//			if(!(ageFromJsp <= 0)){
//				System.out.println(2);
//				age = Integer.parseInt(request.getParameter("age").toString());
//			}else{
//				System.out.println(3);
//				request.setAttribute("error", "the age must be > 0");
//				request = setRequstAttributeForInformation(request, username, password, ageFromJsp, email);
//				request.getRequestDispatcher("Register.jsp").forward(request, response);
//			}
//		}else{
//			System.out.println(4);
//			request.setAttribute("error", "you shoud fill year");
//			request = setRequstAttributeForInformation(request, username, password, ageFromJsp, email);
//			request.getRequestDispatcher("Register.jsp").forward(request, response);
//		}
		
		if(username != null && (!username.equals("")) && password != null && 
		  (!password.equals("")) && email != null && (!email.equals("")) && /*age > 0*/
		  ageFromJsp != 0){
			if(!(ageFromJsp <= 0)){
				try {
					if(!userDAO.isThereSuchUsernameInDB(username)){
						if(!userDAO.isThereSuchEmailInDB(email)){
							User user = new User(username, password, email, ageFromJsp);
							session.setAttribute("user", user);
							userDAO.addUserToDB(user);
							request.getRequestDispatcher("Login.jsp").forward(request, response);
						}else{
							request.setAttribute("error", "there is such email already");
							request = setRequstAttributeForInformation(request, username, password, ageFromJsp, email);
							request.getRequestDispatcher("Register.jsp").forward(request, response);
						}
					}else{
						request.setAttribute("error", "there is such username already");
						request = setRequstAttributeForInformation(request, username, password, ageFromJsp, email);
						request.getRequestDispatcher("Register.jsp").forward(request, response);
					}
				} catch (MySQLExseption e) {
					e.getMessage();
					e.printStackTrace();
					request.getRequestDispatcher("InternalServerError.jsp").forward(request, response);
				}
			}else{
				request.setAttribute("error", "the age must be > 0");
				request = setRequstAttributeForInformation(request, username, password, ageFromJsp, email);
				request.getRequestDispatcher("Register.jsp").forward(request, response);
			}
		}else{
			request.setAttribute("error", "you should fill all spots");
			if(ageFromJsp == 0){
				request = setRequstAttributeForInformation(request, username, password, email);
			}else{
				request = setRequstAttributeForInformation(request, username, password, ageFromJsp, email);
			}
			request.getRequestDispatcher("Register.jsp").forward(request, response);
		}
	}
	
	public HttpServletRequest setRequstAttributeForInformation(HttpServletRequest request, String username, String password, int age, String email){
		request.setAttribute("username", username);
		request.setAttribute("password", password);
		request.setAttribute("age", age);
		request.setAttribute("email", email);
		return request;
	}
	
	public HttpServletRequest setRequstAttributeForInformation(HttpServletRequest request, String username, String password, String email){
		request.setAttribute("username", username);
		request.setAttribute("password", password);
		request.setAttribute("email", email);
		return request;
	}

}
