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

public class AddingCommand implements Command{
	BookDAO books;
	public AddingCommand(){
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
		String nextPage;
		if(request.getParameter("action") == null || request.getParameter("action").equals("Reset")){
			nextPage = "/selling.jsp";
		}else {
		
			BookDTO book = new BookDTO();
			String username = (String)request.getSession().getAttribute("username");
			book.setAuthors(request.getParameter("author"));
			book.setBookTitle(request.getParameter("bookTitle"));
			if (request.getParameter("cost").matches("[1-9][0-9]*\\.[0-9]*")){
				System.out.println(" get cost                 ");
				book.setCost(Float.parseFloat(request.getParameter("cost")));
			}else{
				book.setCost(0);
			}	
			book.setEditors(request.getParameter("editor"));
			book.setISBN(request.getParameter("ISBN"));
			book.setJournal(request.getParameter("journal"));
			book.setMonth(request.getParameter("month"));
			book.setNumber(request.getParameter("number"));
			book.setPages(request.getParameter("page"));
			book.setPublisher(request.getParameter("publisher"));
			book.setSeller(username);
			book.setTitle(request.getParameter("title"));
			book.setType(request.getParameter("type"));
			book.setVolume(request.getParameter("volume"));
			book.setYear(Integer.parseInt(request.getParameter("year")));
			book.setPicture(request.getParameter("picture"));
			books.storeBook(book);
			request.setAttribute("message", "New book recorded, would you like to sell more book?");
			
			nextPage = "/selling.jsp";
		}
		RequestDispatcher rd = request.getRequestDispatcher(nextPage);
		rd.forward(request, response);
		
		return null;
	}

}
