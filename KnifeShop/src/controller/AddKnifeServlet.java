package controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import exceptions.MySQLExseption;
import model.classes.FilterSession;
import model.classes.Knife;
import model.dao.DBKnifeDAO;

@WebServlet("/AddKnifeServlet")
@MultipartConfig
public class AddKnifeServlet extends HttpServlet /*implements Serializable*/{
	//private static final long serialVersionUID = 1L;
	
	DBKnifeDAO knifeDAO = new DBKnifeDAO();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		FilterSession filter = new FilterSession();
//		User user = (User) session.getAttribute("user");
//		System.out.println("User is " + user.getUsername());
		filter.filter(request, response, session, "AddKnife.jsp");
		//reqest.getRequestDispatcher("AddKnife.jsp").forward(reqest, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int length;
		if(request.getParameter("length").equals("") || request.getParameter("length") == null){
			length=0;
		}else{
			length = Integer.parseInt(request.getParameter("length"));
		}
		String steel = request.getParameter("steel");
		String manufactor = request.getParameter("manufactor");
		String model = request.getParameter("model");
		int price;
		if(request.getParameter("price").equals("") || request.getParameter("price") == null){
			price=0;
		}else{
			price = Integer.parseInt(request.getParameter("price"));
		}
		String lock = request.getParameter("lock");
		boolean folder = Boolean.parseBoolean(request.getParameter("folder"));
		
	    Part filePart = request.getPart("image"); 
	    String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); 
	    InputStream fileContent = filePart.getInputStream();
	    boolean isTheImageAdded = false;
	    if(fileName != null && (!fileName.equals(""))){
	    	isTheImageAdded=true;
	    }
	    //ako ne e prazno izobrajenieto
	    if(fileContent.available() != 0){
	    	isTheImageAdded = true;
	    }
		
		//ako wsi4ko e popalneno
		if(/*length>0 && steel!=null && (!steel.equals("")) && manufactor!=null && (!manufactor.equals("")) &&*/
		   model!=null && (!model.equals("")) /*&& price>0 && lock!=null && (!lock.equals(""))*/){
			if(length>0 && price>0){
				if(isTheImageAdded){
					Knife knife = new Knife(length, steel, manufactor, model, price, lock, folder, fileName);
					//ako we4e tozi noj go ima w db, pra6tame saob6tenie, ako go nqma go dobawqme
					try {
						if(isThereSuchKnifeInDB(knife)){
							request.setAttribute("errorDublicate", "you have already added this knife");
						}else{
							try {
								knifeDAO.addKnifeToDB(knife);
							} catch (MySQLExseption e) {
								e.getMessage();
								e.printStackTrace();
								request.getRequestDispatcher("InternalServerError.jsp").forward(request, response);
							}
							try {
								knife.setId(knifeDAO.getLastInsertedId());
							} catch (MySQLExseption e) {
								e.getMessage();
								e.printStackTrace();
								request.getRequestDispatcher("InternalServerError.jsp").forward(request, response);
							}
							
							ArrayList<Knife> currentKnifes = (ArrayList<Knife>) session.getAttribute("allKnifes");
							currentKnifes.add(knife);
							
							setSession(session, currentKnifes);
							
							request.setAttribute("success", "you have added the knife successfully");
							
						}
					} catch (MySQLExseption e) {
						e.getMessage();
						e.printStackTrace();
						request.getRequestDispatcher("InternalServerError.jsp").forward(request, response);
					}
				}else{
					request.setAttribute("errorImage", "you should upload an image");
				}
			}else{
				request.setAttribute("errorNumbers", "the length and the price have to be greater than 0");
			}
		}else{
			request.setAttribute("errorFields", "you should fill all fields");
		}
		FilterSession filter = new FilterSession();
		filter.filter(request, response, session, "AddKnife.jsp");
	}
	
	public boolean isThereSuchKnifeInDB(Knife knife) throws MySQLExseption {
		ArrayList<Knife> allKnifes = knifeDAO.getAllKnifesFromDB();
		for(Knife k : allKnifes){
			if(k.getLength() == knife.getLength() &&
			   k.getSteel().equals(knife.getSteel())&&
			   k.getManufactor().equals(knife.getManufactor())&&
			   k.getLock().equals(knife.getLock())&&
			   k.getImageName().equals(knife.getImageName())&&
			   k.isFolder() == knife.isFolder()&&
			   k.getModel().equals(knife.getModel())&&
			   k.getPrice() == knife.getPrice()){
				return true;
			}
		}
		return false;
	}
	
	public void setSession(HttpSession session, ArrayList<Knife> currentKnifes) throws MySQLExseption{
		session.removeAttribute("allKnifes");
		session.setAttribute("allKnifes", currentKnifes);
		
		session.removeAttribute("cheapest");
		session.setAttribute("cheapest", knifeDAO.getTheThreeCheapest());
		
		session.removeAttribute("mostExpenisve");
		session.setAttribute("mostExpenisve", knifeDAO.getTheThreeMostExpensive());
		
		session.removeAttribute("lowestQuantity");
		session.setAttribute("lowestQuantity", knifeDAO.getTheThreeWithLowestQuantity());
		
		session.removeAttribute("highestQuantity");
		session.setAttribute("highestQuantity", knifeDAO.getTheThreeWithHighestQuantity());
	}

}
