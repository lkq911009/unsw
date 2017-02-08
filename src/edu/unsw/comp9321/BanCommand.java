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
import edu.unsw.comp9321.jdbc.JDBCUserDAOImpl;
import edu.unsw.comp9321.jdbc.UserDAO;

public class BanCommand implements Command {
	
	UserDAO users;
	public BanCommand(){
		users = null;
		try {
			users = new JDBCUserDAOImpl();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String nextPage = null;
		String name = request.getParameter("name");
		
		System.out.println(request.getParameter("action")+"               action");
		if (request.getParameter("action").equals("Ban")){
			users.ban("true", name);
		}
		if (request.getParameter("action").equals("Unban")){
			users.ban("false", name);
		}

		nextPage = "/dispatcher?operation=adminuserinfo&name="+name;

		RequestDispatcher rd = request.getRequestDispatcher(nextPage);
		rd.forward(request, response);
		
		return null;
		
	}

}
