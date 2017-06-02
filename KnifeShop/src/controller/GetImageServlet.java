package controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/GetImageServlet")
public class GetImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String knifeName = request.getParameter("image");
			if(!knifeName.equals("") && knifeName != null){
			
			
				byte[] imageBytes = getImageAsBytes(knifeName);
		
				response.setContentType("image/jpeg");
				response.setContentLength(imageBytes.length);
		
				response.getOutputStream().write(imageBytes);
			}else{
				request.getRequestDispatcher("404.jsp").forward(request, response);
			}
	}
		
		public byte[] getImageAsBytes (String imageName) throws IOException {
			
			File fi = new File("/Users/Venci/Desktop/knifesProject/" + imageName);
			byte[] fileContent = Files.readAllBytes(fi.toPath());
			return fileContent;
			
			}
	
	
	
}
