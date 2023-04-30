package controll;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.Immagine;
import model.ImmagineDAO;

/**
 * Servlet implementation class ImmagineUploadServlet
 */
@WebServlet("/ImmagineUploadServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50) // 50MB
public class ImmagineUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ImmagineUploadServlet() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("CANNOT USE GET");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Remember: ADD CONTROL ABOUT PARAMETERS OF REQUEST
		String id = request.getParameter("id");
		String testoAlternativo = request.getParameter("testoAlternativo");
		Part raw = request.getPart("raw");
		
		//Create the Immagine DTO
		Immagine immagine = new Immagine();
		immagine.setId(id);
		immagine.setTestoAlternativo(testoAlternativo);
		
		InputStream is = raw.getInputStream();
		byte[] bytes = new byte[is.available()];
		is.read(bytes);
		immagine.setBytes(bytes);
		//Create the Immagine DTO
		
		//Execute query to database to insert the Immagine
		ImmagineDAO.InsertImmagine(immagine);
		//Execute query to database to insert the Immagine
	}

}
