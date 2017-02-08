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
public class BasicSearchCommand implements Command {
	BookDAO books;
	/** Creates a new instance of AddCommand */
	public BasicSearchCommand() {
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
		if(request.getSession().getAttribute("username") == null){
			RequestDispatcher rd = request.getRequestDispatcher("/default.jsp");
			rd.forward(request, response);
			return null;
		}
		
		String nextPage = null;
		List<BookDTO> results = null;
		nextPage = "SearchResults.jsp";
		String inputType = request.getParameter("inputType");
		if(inputType.equalsIgnoreCase("Title")){
			results = books.findBooksByName(request.getParameter("searchquery"));		
		}else if (inputType.equalsIgnoreCase("Author")){
			results = books.findBooksByAuthor(request.getParameter("searchquery"));	
		}else if (inputType.equalsIgnoreCase("Editor")){
			results = books.findBooksByEditor(request.getParameter("searchquery"));	
		}else if(inputType.equalsIgnoreCase("Publisher")){
			results = books.findBooksByPublisher(request.getParameter("searchquery"));	
		}else if(inputType.equalsIgnoreCase("Journal")){
			results = books.findBooksByJournal(request.getParameter("searchquery"));	
		}
		request.getSession().setAttribute("results", results);
		request.getSession().setAttribute("index", 0);
		RequestDispatcher rd = request.getRequestDispatcher("/"+nextPage);
		rd.forward(request, response);
	    return null;
	     
	}
	
}
