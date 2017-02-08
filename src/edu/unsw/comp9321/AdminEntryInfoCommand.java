package edu.unsw.comp9321;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.unsw.comp9321.jdbc.BookDAO;
import edu.unsw.comp9321.jdbc.BookDTO;
import edu.unsw.comp9321.jdbc.JDBCBookDAOImpl;

public class AdminEntryInfoCommand implements Command {

	BookDAO books;
	public AdminEntryInfoCommand(){
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
	
	
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		BookDTO book = new BookDTO();
		book = books.findbyID(request.getParameter("i"));
		request.setAttribute("book", book);
		request.setAttribute("index", 0);
		String nextPage = "/AdminEntryInfo.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(nextPage);
		rd.forward(request, response);
		
		
		
		
		
		return null;
	}

}
