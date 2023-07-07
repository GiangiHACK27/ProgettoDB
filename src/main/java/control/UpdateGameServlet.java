package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.sql.DataSource;

import dao.BelongsDAO;
import dao.GameDAO;
import dao.ImageDAO;
import dao.SystemRequirementDAO;
import model.Belong;
import model.Game;
import model.SystemRequirement;
import model.SystemRequirement.OperatingSystem;
import utility.BackendException;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50) // 50MB
@WebServlet("/admin/UpdateGameServlet")
public class UpdateGameServlet extends BaseServlet {
	
	public UpdateGameServlet() {
        super(); 
    }
	
	private void updateImage(ImageDAO imageDAO, int gameId, Part image, String role ) throws SQLException, IOException {
		//image remains unchanged if no new image was inserted
		if(image.getSize() <=0) {
			return;
		}
		//image remains unchanged if no new image was inserted
		
		//upload image into database
		int imageId = imageDAO.insertImage(image.getInputStream().readAllBytes());
		//upload image into database

		//remove the image that was there previously
		imageDAO.removeImageGame(gameId, role);
		//remove the image that was there previously
		
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
		String publisher = request.getParameter("publisher");
		String state = request.getParameter("state");
		String shortDescription = request.getParameter("shortDescription");
		String releaseDate = request.getParameter("releaseDate");
		String pegi = request.getParameter("pegi");
		int gameId = Integer.parseInt(request.getParameter("gameId"));
		String[] parameterCategories = request.getParameterValues("categories");
		//Get game data from request
		
		//make game DTO
		Game game = new Game();
		game.setDescription(description);
		game.setName(name);
		game.setPegi(Game.Pegi.valueOf(pegi.toUpperCase()));
		game.setPrice(price);
		game.setPublisher(publisher);
		game.setReleaseDate(releaseDate);
		game.setShortDescription(shortDescription);
		game.setState(Game.State.valueOf(state.toUpperCase()));
		game.setId(gameId);
		//make game DTO


		//update game in database
		GameDAO gameDAO = new GameDAO(dataSource);
		try {
			gameDAO.updateGame(game);
		} catch (SQLException e) {
			showError(request, response, "Internal error while uploading game", selfPath);
			return;
		}
		//update game in database
		
		//update both max prices (throw inside pattern)
		//Retrieve max price from Games(only listed)
		ServletContext context = request.getServletContext();
		int maxPrice = 0;
		try {
			maxPrice = gameDAO.retrieveMaxPriceGame(false);
		} catch (SQLException e) {
			throw new BackendException();
		}
		
		context.setAttribute("maxPrice", maxPrice);
		//Retrieve max price from Games(only listed)
		
		//Retrieve max price from Games(even unlisted games)
		int maxPriceUnlisted = 0;
		try {
			maxPriceUnlisted = gameDAO.retrieveMaxPriceGame(true);
		} catch (SQLException e) {
		}
		
		context.setAttribute("maxPriceUnlisted", maxPriceUnlisted);
		//Retrieve max price from Games(even unlisted games)
		//update both max prices (throw inside pattern)

		
		//Insert relation between categories and game to add
		BelongsDAO belongDAO = new BelongsDAO(dataSource);
		
		//remove all previous categories from game
		try {
			belongDAO.deleteAllBelongs(gameId);
		} catch (SQLException e) {
		}
		//remove all previous categories from game
		
		Belong belong = new Belong();
		belong.setGameId(gameId);
		for(String category : parameterCategories) {
			belong.setCategory(category);
			try {
				belongDAO.insert(belong);
			} catch (SQLException e) {
				throw new BackendException();
			}
		}
		//Insert relation between categories and game to add

		//Insert images into database and upload "represented" table
		ImageDAO imageDAO = new ImageDAO(dataSource);

		//insert banner image
		Part bannerImage = request.getPart("bannerImage");
		try {
			updateImage(imageDAO, gameId, bannerImage, "BANNER");
		} catch (SQLException | IOException e) {
			showError(request, response, "Error uploading banner image", selfPath);
		}
		//insert banner image
		
		//insert showcase image
		Part showcaseImage = request.getPart("showcaseImage");
		try {
			updateImage(imageDAO, gameId, showcaseImage, "SHOWCASE");
		} catch (SQLException | IOException e) {
			showError(request, response, "Error uploading banner image", selfPath);
		}
		//insert showcase image
		//Insert images into database and upload "represented" table
		
		//insert system requirements
		SystemRequirementDAO dao = new SystemRequirementDAO(dataSource);
		
		//delete previous requirements
		try {
			dao.deleteRequirements(gameId);
		} catch (SQLException e) {
			throw new BackendException();
		}
		//delete previous requirements
		
		try {
			uploadRequirements(dao, gameId, "WINDOWS", request.getParameterValues("windows[name][]"), request.getParameterValues("windows[value][]"));
			uploadRequirements(dao, gameId, "MAC", request.getParameterValues("mac[name][]"), request.getParameterValues("mac[value][]"));
			uploadRequirements(dao, gameId, "LINUX", request.getParameterValues("linux[name][]"), request.getParameterValues("linux[value][]"));
		} catch (SQLException e) {
			
			throw new BackendException();
		}
		//insert system requirements
		
		response.sendRedirect(request.getContextPath() + "/PersonalGamePage.jsp?gameId=" + Integer.toString(gameId));
	}
	
	private static final String selfPath = "/admin/UpdateGame.jsp";
	private static final long serialVersionUID = -7220384849245062203L;
}
