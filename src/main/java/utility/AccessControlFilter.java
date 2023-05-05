package utility;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;
import model.User.Role;

/**
 * Servlet Filter implementation class AccessControlFilter
 */
@WebFilter(filterName = "/AccessControlFilter", urlPatterns = "/*")
public class AccessControlFilter extends HttpFilter implements Filter {
       
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
			throws IOException, ServletException {
		
		//Convert to httpServletRequest and get session
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;		
		HttpSession session = httpServletRequest.getSession();
		//Convert to httpServletRequest and get session
		
		
		String path = httpServletRequest.getServletPath();
		User user = (User) session.getAttribute("user");
		if((path.contains("/user/") && user == null) || (path.contains("/admin/") && user.getRole().compareTo(Role.ADMIN) != 0) ) {
			//httpServletResponse.sendRedirect(httpServletRequest.getContextPath());
			httpServletResponse.sendError(404, "Unauthorised");
			return;
		}

		
		chain.doFilter(request, response);
	}
	
	public void init(FilterConfig fConfig) throws ServletException {
	}
	
    public void destroy() {
	}

	private static final long serialVersionUID = 1L;

}
