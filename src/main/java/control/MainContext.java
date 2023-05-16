package control;

import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

import dao.GameDAO;

@WebListener
public class MainContext implements ServletContextListener {
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext context = sce.getServletContext();
		
		//Init data source and add data source to the ServletContext
		DataSource ds = null;
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");

			ds = (DataSource) envCtx.lookup("jdbc/storage");

		} catch (NamingException e) {
			e.printStackTrace();
		}		
		
		context.setAttribute("DataSource", ds);
		//Init data source and add data source to the ServletContext
		
		//Retrieve max price from Games
		GameDAO gameDAO = new GameDAO(ds);
		
		int maxPrice = 0;
		try {
			maxPrice = gameDAO.retrieveMaxPriceGame();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		context.setAttribute("maxPrice", maxPrice);
		//Retrieve max price from Games
	}
}
