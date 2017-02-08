package edu.unsw.comp9321;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import edu.unsw.comp9321.jdbc.JDBCUserDAOImpl;
import edu.unsw.comp9321.jdbc.UserDAO;
import edu.unsw.comp9321.jdbc.UserDTO;

public class AdminHomeCommands implements Command {

	UserDAO users;
	
	public AdminHomeCommands() {
		// TODO Auto-generated constructor stub
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

		List<UserDTO> list = users.findAll();
		
		request.getSession().setAttribute("results", list);
		request.getSession().setAttribute("index", 0);
		RequestDispatcher rd = request.getRequestDispatcher("/AdminPage.jsp");
		rd.forward(request, response);
		return null;
		

	}

}
