package edu.unsw.comp9321;

import java.io.*;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.unsw.comp9321.jdbc.BookDAO;
import edu.unsw.comp9321.jdbc.BookDTO;
import edu.unsw.comp9321.jdbc.JDBCBookDAOImpl;
/**
 * This command adds a new contact
 * @author  yunki
 */
public class AdvanceSearchCommand implements Command {
	BookDAO books;
	/** Creates a new instance of AddCommand */
	public AdvanceSearchCommand() {
		books = null;
		try {
			books = new JDBCBookDAOImpl();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String execute(HttpServletRequest request, HttpServletResponse response) 
       throws ServletException, IOException {

		String nextPage = null;
		List<BookDTO> results = books.findMatching(request.getParameter("title"),request.getParameter("author"),
				request.getParameter("publisher"),request.getParameterValues("type"));
		nextPage = "SearchResults.jsp";

		request.getSession().setAttribute("results", results);
		request.getSession().setAttribute("index", 0);
		RequestDispatcher rd = request.getRequestDispatcher("/"+nextPage);
		rd.forward(request, response);
	    return null;
	     
	}
	
}