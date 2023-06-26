package control;

import java.io.IOException;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.sql.DataSource;

import dao.GameDAO;
import dao.ImageDAO;
import dao.BelongsDAO;

import model.Belong;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50) // 50MB
@WebServlet("/admin/GameUploadServlet")
public class GameUploadServlet extends BaseServlet {
	
	public GameUploadServlet() {
        super(); 
    }
	
	public void uploadImage(ImageDAO imageDAO, int gameId, Part image, String role ) throws SQLException, IOException {
		//upload image into database
		int imageId = imageDAO.insertImage(image.getInputStream().readAllBytes());
		//upload image into database

		//update represented table with role
		imageDAO.connectImageGame(imageId, gameId, role);
		//update represented table with role
	}
    
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Include Retrieve all categories Servlet 
		RequestDispatcher dispatcher = request.getRequestDispatcher("/RetrieveAllCategories");
		dispatcher.include(request, response);
		//Include Retrieve all categories Servlet
		RequestDispatcher dispatcher2 = request.getRequestDispatcher("/admin/UploadGame.jsp");
		dispatcher2.forward(request, response);
	}
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//Retrieve form inputs and check if they're valid
		if(! validParameters(request, response)) {
			return;
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
		
		String[] parameterCategories = request.getParameterValues("categories");
		//Get game data from request



		//insert game into database
		GameDAO gameDAO = new GameDAO((DataSource)getServletContext().getAttribute("DataSource"));
		int gameId = 0;
		try {
			gameId = gameDAO.insertGame(price, name, description, state, shortDescription, releaseDate, pegi);
		} catch (SQLException e) {
			showError(request, response, "Internal error while uploading game", selfPath);
			return;
		}
		//insert game into database
		
		//Insert relation beetween categories and game to add
		BelongsDAO belongDAO = new BelongsDAO((DataSource)getServletContext().getAttribute("DataSource"));
		
		Belong belong = new Belong();
		belong.setGameId(gameId);
		for(String category : parameterCategories) {
			belong.setCategory(category);
			try {
				belongDAO.insert(belong);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		//Insert relation between categories and game to add

		//Insert images into database and upload "represented" table
		ImageDAO imageDAO = new ImageDAO((DataSource)getServletContext().getAttribute("DataSource"));

		//insert banner image
		Part bannerImage = request.getPart("bannerImage");
		try {
			uploadImage(imageDAO, gameId, bannerImage, "BANNER");
		} catch (SQLException | IOException e) {
			showError(request, response, "Error uploading banner image", selfPath);
		}
		//insert banner image
		
		//insert showcase images
		Collection<Part> showcaseImages = request.getParts();
		Iterator<Part> i = showcaseImages.iterator();
		i.next(); //Perché la prima l'ho già messa. E' brutto ma non c'è un modo decente per farlo
		while(i.hasNext()) {
			try {
				Part image = i.next();
				//check if it's not an image. If it isn't, stop iterating. Only works if the form starts with the images
				if(image.getContentType() == null || !image.getContentType().contains("image"))
					break; 
				//check if it's not an image. If it isn't, stop iterating.
				uploadImage(imageDAO, gameId, image, "SHOWCASE");
			} catch (SQLException | IOException e) {
				showError(request, response, "Error uploading banner image", selfPath);
			} 
		}
		//insert showcase images
		//Insert images into database and upload "represented" table
		
		//Check if we must update max price of games
		Integer maxPrice = (Integer)getServletContext().getAttribute("maxPrice");
		
		if(maxPrice.compareTo(price) < 0) 
			getServletContext().setAttribute("maxPrice", price);
		//Check if we must update max price of games
		
		response.sendRedirect(request.getContextPath());	
	}
	
	private static final long serialVersionUID = 1503010158356860644L;
	private static final String selfPath = "/admin/UploadGame.jsp";
}
