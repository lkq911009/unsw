package edu.unsw.comp9321;

import java.io.*;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.unsw.comp9321.jdbc.BookDAO;
import edu.unsw.comp9321.jdbc.BookDTO;
import edu.unsw.comp9321.jdbc.JDBCBookDAOImpl;
import edu.unsw.comp9321.jdbc.JDBCUserDAOImpl;
import edu.unsw.comp9321.jdbc.UserDAO;
import edu.unsw.comp9321.jdbc.UserDTO;

/**
 * This is the command that will be used for logging in users.
 * If logon is successful, the command should place a list of contacts
 * in the request attriubutes.
 * @author  yunki
 */
public class LoginCommand implements Command {
	
	UserDAO users = null;
	BookDAO books = null;
	
	/** Creates a new instance of LoginCommand 
	 * @throws ServletException */
	public LoginCommand() throws ServletException {
		users = null;
		 
		try {
			users = new JDBCUserDAOImpl();
			books = new JDBCBookDAOImpl();
			
		} catch (Exception e) {
			
			throw new ServletException();
		}
	}
	
	public String execute(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
		String nextPage = null;
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if(username.equals("") || password.equals("")){
			request.setAttribute("message", "Please enter username and password");
			nextPage = "default.jsp";
		}else{
			UserDTO user = users.findUser(username);
			
			


			
			
			
			if(user == null){
				request.setAttribute("message", "Username doesn't exist");
				nextPage = "default.jsp";
			}else if(!user.getPassword().equals(password)){
				request.setAttribute("message", "Password is incorrect");
				nextPage = "default.jsp";
			}else if(user.getBanned()){
				request.setAttribute("message", "This account has been barred from the site.");
				nextPage = "default.jsp";
			}else if(!user.getActivated()){
				request.setAttribute("message", "This account hasn't yet been activated");
				nextPage = "default.jsp";
			}
			else{
				if (user.getType().equals("CUSTOMER")){
				
					request.getSession().setAttribute("username", username);
					List<BookDTO> list = books.getRandom();
					request.getSession().setAttribute("results", list);
					nextPage = "search.jsp";
					request.getSession().setMaxInactiveInterval(3600);
					List<BookDTO>shoppingCart = books.getShoppingCart(username);
					request.getSession().setAttribute("cart", shoppingCart);
				}
				if (user.getType().equals("SELLER")){
					request.getSession().setAttribute("username", username);
					List<BookDTO> list = books.findBooksBySeller(username);
					request.getSession().setAttribute("results", list);
					request.getSession().setAttribute("index", 0);
					nextPage = "ItemsOnSales.jsp";
					request.getSession().setMaxInactiveInterval(3600);
				}
			}
		}
		request.getSession().setAttribute("back", -1);
		RequestDispatcher rd = request.getRequestDispatcher("/"+nextPage);
		rd.forward(request, response);
		return null;
	}
	
}
