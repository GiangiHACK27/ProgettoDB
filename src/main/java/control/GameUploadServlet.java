package control;

import java.io.IOException;

import java.sql.SQLException;

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
import dao.SystemRequirementDAO;
import dao.BelongsDAO;

import model.Belong;
import model.SystemRequirement;
import model.SystemRequirement.OperatingSystem;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50) // 50MB
@WebServlet("/admin/GameUploadServlet")
public class GameUploadServlet extends BaseServlet {
	
	public GameUploadServlet() {
        super(); 
    }
	
	private void uploadImage(ImageDAO imageDAO, int gameId, Part image, String role ) throws SQLException, IOException {
		//upload image into database
		int imageId = imageDAO.insertImage(image.getInputStream().readAllBytes());
		//upload image into database

		//update represented table with role
		imageDAO.connectImageGame(imageId, gameId, role);
		//update represented table with role
	}
    
	/*This function takes a gameId, an operating system, arrays of names and values and inserts them into the database */
	private void uploadRequirements(SystemRequirementDAO dao, int gameId, String system, String[] names, String[] values) throws SQLException {
		if(names == null || values == null)
			return;
		//get system requirement bean
		SystemRequirement reqModel = new SystemRequirement();
		//get system requirement bean
		
		//set os and game id, which are the same for all requirements
		reqModel.setOs(OperatingSystem.valueOf(system.toUpperCase()));
		reqModel.setGameId(gameId);
		//set os and game id, which are the same for all requirements
		
		int lim = names.length; //names and values should have the same length
		
		//insert the requirements into the database
		for(int i = 0; i<lim; i++) {
			reqModel.setName(names[i]);
			reqModel.setValue(values[i]);
			dao.insertRequirement(reqModel);
		}
		//insert the requirements into the database
		
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
		
		//retrieve data source
		final DataSource dataSource = (DataSource)getServletContext().getAttribute("DataSource");
		//retrieve data source
		
		//Get game data from request
		Integer price = (int) (Float.parseFloat(request.getParameter("price")) *100);
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		String state = request.getParameter("state");
		String shortDescription = request.getParameter("shortDescription");
		String releaseDate = request.getParameter("releaseDate");
		String pegi = request.getParameter("pegi");
		
		String[] parameterCategories = request.getParameterValues("categories");
		//Get game data from request



		//insert game into database
		GameDAO gameDAO = new GameDAO(dataSource);
		int gameId = 0;
		try {
			gameId = gameDAO.insertGame(price, name, description, state, shortDescription, releaseDate, pegi);
		} catch (SQLException e) {
			showError(request, response, "Internal error while uploading game", selfPath);
			return;
		}
		//insert game into database
		
		//Insert relation between categories and game to add
		BelongsDAO belongDAO = new BelongsDAO(dataSource);
		
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
		ImageDAO imageDAO = new ImageDAO(dataSource);

		//insert banner image
		Part bannerImage = request.getPart("bannerImage");
		try {
			uploadImage(imageDAO, gameId, bannerImage, "BANNER");
		} catch (SQLException | IOException e) {
			showError(request, response, "Error uploading banner image", selfPath);
		}
		//insert banner image
		
		//insert showcase image
		Part showcaseImage = request.getPart("showcaseImage");
		try {
			uploadImage(imageDAO, gameId, showcaseImage, "SHOWCASE");
		} catch (SQLException | IOException e) {
			showError(request, response, "Error uploading banner image", selfPath);
		}
		//insert showcase image
		//Insert images into database and upload "represented" table
		
		//insert system requirements
		SystemRequirementDAO dao = new SystemRequirementDAO(dataSource);
		try {
			uploadRequirements(dao, gameId, "WINDOWS", request.getParameterValues("windows[name][]"), request.getParameterValues("windows[value][]"));
			uploadRequirements(dao, gameId, "MAC", request.getParameterValues("mac[name][]"), request.getParameterValues("mac[value][]"));
			uploadRequirements(dao, gameId, "LINUX", request.getParameterValues("linux[name][]"), request.getParameterValues("linux[value][]"));
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		//insert system requirements
		
		//Check if we must update max price of games, and max price unlisted
		Integer maxPrice = (Integer)getServletContext().getAttribute("maxPrice");
		
		if(maxPrice.compareTo(price) < 0) 
			getServletContext().setAttribute("maxPrice", price);
		
		Integer maxPriceUnlisted = (Integer)getServletContext().getAttribute("maxPriceUnlisted");
		
		if(maxPriceUnlisted.compareTo(price) < 0)
			getServletContext().setAttribute("maxPriceUnlisted", price);
		//Check if we must update max price of games, and max price unlisted
		
		response.sendRedirect(request.getContextPath() + "/PersonalGamePage.jsp?gameId=" + Integer.toString(gameId));
	}
	
	private static final long serialVersionUID = 1503010158356860644L;
	private static final String selfPath = "/admin/UploadGame.jsp";
}
