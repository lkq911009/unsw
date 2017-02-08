package edu.unsw.comp9321;

import java.io.IOException;
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

public class RandomCommand implements Command {

	BookDAO books = null;

	public RandomCommand() throws ServletException {
		try {
			books = new JDBCBookDAOImpl();
		} catch (Exception e) {
			
			throw new ServletException();
		}
	}
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		List<BookDTO> list = books.getRandom();
		request.getSession().setAttribute("results", list);
		RequestDispatcher rd = request.getRequestDispatcher("/search.jsp");
		rd.forward(request, response);
		
		return null;
	}

}
