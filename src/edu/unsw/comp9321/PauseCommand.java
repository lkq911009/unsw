package edu.unsw.comp9321;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.unsw.comp9321.jdbc.BookDAO;
import edu.unsw.comp9321.jdbc.BookDTO;
import edu.unsw.comp9321.jdbc.JDBCBookDAOImpl;

public class PauseCommand implements Command {
	BookDAO books;
	public PauseCommand(){
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
		String username = (String) request.getSession().getAttribute("username");
		int index = Integer.parseInt(request.getParameter("i"));
		List<BookDTO> results = (List<BookDTO>)request.getSession().getAttribute("results");
		BookDTO currBook = results.get(index);
		if (request.getParameter("action").equals("Pause")){
			currBook.setPaused(true);
		}
		if (request.getParameter("action").equals("Resume")){
			currBook.setPaused(false);
		}
		books.updateBook(currBook);
		String nextPage = "/default.jsp";
		if (request.getParameter("from").equals("front")){
			nextPage = "/ItemsOnSales.jsp";
		}else if (request.getParameter("from").equals("info")){
			nextPage = "/selleriteminfo.jsp";
		}
		RequestDispatcher rd = request.getRequestDispatcher(nextPage);
		rd.forward(request, response);
		
		return null;
	}

}
