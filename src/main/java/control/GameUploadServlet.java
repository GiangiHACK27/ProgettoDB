package control;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import dao.GameDAO;

@WebServlet("/admin/GameUploadServlet")
public class GameUploadServlet extends HttpServlet {
	
	private boolean isNotValidParam(String s) {
    	return s == null || s.trim().isEmpty();
    }
	
	private void errorUpload(HttpServletRequest request, HttpServletResponse response) {
    	RequestDispatcher rs = request.getRequestDispatcher("/admin/UploadGame.jsp");
    	try {
			rs.forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		} 
    }
	
	public GameUploadServlet() {
        super(); 
    }
    
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendError(404, "Cannot use GET");
	}
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Retrieve form inputs and check if they're valid
		Enumeration <String> parameters = request.getParameterNames();
		
		while(parameters.hasMoreElements()) {
			String parameterName = parameters.nextElement();
			String parameterValue = request.getParameter(parameterName);
			if(isNotValidParam(parameterValue)) {
				request.setAttribute("logError", "Missing " + parameterName);
				errorUpload(request, response);
				return;
			}
		}
		//Retrieve form inputs and check if they're valid
		
		//Get game data from request
		Integer price = Integer.parseInt(request.getParameter("price"));
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		String state = request.getParameter("state");
		String shortDescription = request.getParameter("shortDescription");
		String releaseDate = request.getParameter("releaseDate");
		String pegi = request.getParameter("pegi");
		//Get game data from request



		//insert game into database
		GameDAO gameDAO = new GameDAO((DataSource)getServletContext().getAttribute("DataSource"));
		try {
			gameDAO.insertGame(price, name, description, state, shortDescription, releaseDate, pegi);
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("logError","Upload error");
			errorUpload(request, response);
			return;
		}
		//insert game into database

		response.sendRedirect(request.getContextPath());	
	}
	
	private static final long serialVersionUID = 1503010158356860644L;

}
