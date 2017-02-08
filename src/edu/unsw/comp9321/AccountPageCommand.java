package edu.unsw.comp9321;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.unsw.comp9321.jdbc.JDBCCartHistoryImpl;
import edu.unsw.comp9321.jdbc.JDBCUserDAOImpl;
import edu.unsw.comp9321.jdbc.OrderDTO;
import edu.unsw.comp9321.jdbc.UserDAO;
import edu.unsw.comp9321.jdbc.UserDTO;

public class AccountPageCommand implements Command {

	UserDAO users = null;
	
	public AccountPageCommand() {
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
		String nextPage = null;
		String from = request.getParameter("from");
		String username = (String) request.getSession().getAttribute("username");
		
		UserDTO me = users.findUser(username);
		List<OrderDTO> orderlist = users.orderHistory(username);

		
		request.getSession().setAttribute("user", me);
		request.getSession().setAttribute("orders", orderlist);
		request.getSession().setAttribute("from", from);
		request.getSession().setAttribute("index", 0);
		
		RequestDispatcher rd;
		nextPage = "/AccountPage.jsp";
		rd = request.getRequestDispatcher(nextPage);
		rd.forward(request, response);
		
		return null;
	}

}
