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

public class DeleteEntryCommand implements Command {

	BookDAO books;
	public DeleteEntryCommand(){
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
		books.deleteentry(request.getParameter("i"));
		List<BookDTO> book = null;
		book = books.findAll();
		request.getSession().setAttribute("results", book);
		String nextPage = "/BookManagement.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(nextPage);
		rd.forward(request, response);
		return null;
	}

}
