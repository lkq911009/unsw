package edu.unsw.comp9321;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.unsw.comp9321.jdbc.AdminDAO;
import edu.unsw.comp9321.jdbc.AdminDTO;
import edu.unsw.comp9321.jdbc.JDBCAdminDAOImpl;
import edu.unsw.comp9321.jdbc.JDBCUserDAOImpl;
import edu.unsw.comp9321.jdbc.UserDAO;
import edu.unsw.comp9321.jdbc.UserDTO;




public class AdminLoginCommand implements Command {
	
	AdminDAO admin = null;
	UserDAO users = null;
	
	/** Creates a new instance of LoginCommand 
	 * @throws ServletException */
	public AdminLoginCommand() throws ServletException {
		admin = null;
		 
		try {
			admin = new JDBCAdminDAOImpl();
			users = new JDBCUserDAOImpl();
			
		} catch (Exception e) {
			
			throw new ServletException();
		}
	}

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nextPage = null;
		String adminname = request.getParameter("adminname");
		String password = request.getParameter("password");

		if(adminname.equals("") || password.equals("")){
			request.setAttribute("message", "Please enter username and password");
			nextPage = "AdminLogin.jsp";
		}else{
			AdminDTO ad = admin.find(adminname);
			if(ad == null){
				request.setAttribute("message", "Username doesn't exist");
				nextPage = "AdminLogin.jsp";
			}else if(!ad.getPassword().equals(password)){
				request.setAttribute("message", "Password is incorrect");
				nextPage = "AdminLogin.jsp";
			}
			else{
				request.getSession().setAttribute("adminname", adminname);
				nextPage = "AdminPage.jsp";

			}
		}
		request.getSession().setAttribute("back", -1);
		RequestDispatcher rd = request.getRequestDispatcher("/"+nextPage);
		rd.forward(request, response);
		return null;
		
	}

}
