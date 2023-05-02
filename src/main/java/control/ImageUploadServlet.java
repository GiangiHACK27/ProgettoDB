package control;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import javax.sql.DataSource;

import model.Image;
import model.ImageDAO;

/**
 * Servlet implementation class imageUploadServlet
 */
@WebServlet("/ImageUploadServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50) // 50MB
public class ImageUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ImageUploadServlet() {
        super();
        
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try{
			response.getWriter().append("CANNOT USE GET");
		}
		catch(IOException e) {
			//TODO: add error page
		}
	}

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Remember: ADD CONTROL ABOUT PARAMETERS OF REQUEST
		String id = request.getParameter("id");
		String altText = request.getParameter("altText");
		Part raw = null;
		try {
			raw = request.getPart("raw");
		}
		catch (ServletException | IOException e) {
			// TODO: add exception page
		}
		
		//Create the image DTO
		Image image = new Image();
		image.setId(id);
		image.setAltText(altText);
		
		byte[] bytes = null;
		try {
			InputStream is = raw.getInputStream();
			bytes =	new byte[is.available()];
			is.read(bytes);
		}
		catch(IOException e) {
			// TODO: add exception page
		}
		image.setBytes(bytes);
		//Create the image DTO
		
		//Execute query to database to insert the image
		ImageDAO imageDAO = new ImageDAO((DataSource)getServletContext().getAttribute("DataSource"));
		imageDAO.insertImage(image);
		//Execute query to database to insert the image
	}

}
