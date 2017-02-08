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

public class ItemOnSaleCommand implements Command{
	BookDAO books;
	public ItemOnSaleCommand(){
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
		String seller = (String) request.getSession().getAttribute("username");
		List<BookDTO> list = books.findBooksBySeller(seller);
		request.getSession().setAttribute("results", list);
		request.getSession().setAttribute("index", 0);
		RequestDispatcher rd = request.getRequestDispatcher("/ItemsOnSales.jsp");
		rd.forward(request, response);
		return null;
	}

}
