package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.sql.DataSource;

import dao.ImageDAO;
import dao.RepresentedDAO;
import model.Image;

@WebServlet("/RetrieveGameImageServlet")
public class RetrieveGameImageServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    public RetrieveGameImageServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Retrieve id of game image from role and gameId
		Integer gameId = Integer.parseInt(request.getParameter("gameId"));
		String role = request.getParameter("role");
		
		DataSource ds = (DataSource)request.getServletContext().getAttribute("DataSource");
		RepresentedDAO representedDAO = new RepresentedDAO(ds);
		
		String idImage = null;
		try {
			idImage = representedDAO.retrieveIdImage(gameId, role).toString();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//Retrieve id of game image from role and gameId
		
		ImageDAO imageDAO = new ImageDAO(ds);
		Image image = null;
		try {
			image = imageDAO.getImageFromID(idImage);
		} catch (SQLException e) {
			response.sendError(404, "Image Not Found");
			return;
		}
		
		response.setContentType("image/png");
		try(ServletOutputStream out = response.getOutputStream()){
			out.write(image.getBytes());
		}
		catch(IOException e) {
			response.sendError(404, "Image Not Found");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
