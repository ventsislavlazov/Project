package controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/GetStoreLogoImageServlet")
public class GetStoreLogoImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		byte[] imageBytes = getImageAsBytes();

		response.setContentType("image/jpeg");
		response.setContentLength(imageBytes.length);

		response.getOutputStream().write(imageBytes);
	}

	public byte[] getImageAsBytes () throws IOException {
		
		File fi = new File("/Users/Venci/Desktop/knifesProject/storeLogo.jpg");
		byte[] fileContent = Files.readAllBytes(fi.toPath());
		return fileContent;
		
	}

}
