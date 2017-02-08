package edu.unsw.comp9321;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.unsw.comp9321.jdbc.CartHistoryDAO;
import edu.unsw.comp9321.jdbc.CartHistoryDTO;
import edu.unsw.comp9321.jdbc.JDBCCartHistoryImpl;
import edu.unsw.comp9321.jdbc.JDBCUserDAOImpl;
import edu.unsw.comp9321.jdbc.OrderDTO;
import edu.unsw.comp9321.jdbc.UserDAO;
import edu.unsw.comp9321.jdbc.UserDTO;



public class AdminUserInfoCommands implements Command {

	UserDAO users;
	CartHistoryDAO historys;
	/** Creates a new instance of AddCommand */
	public AdminUserInfoCommands() {
		users = null;
		try {
			users = new JDBCUserDAOImpl();
			historys = new JDBCCartHistoryImpl();
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
		String nextPage = null;
		String name = request.getParameter("name");
		List<CartHistoryDTO> history = historys.findbyUser(name);
		//System.out.println("========="+history.size());
		UserDTO user = users.findUser(name);
		List<OrderDTO> order = historys.orderfindbyUser(name);
		
		request.getSession().setAttribute("history", history);
		request.getSession().setAttribute("user", user);
		request.getSession().setAttribute("order", order);
		
		RequestDispatcher rd;
		nextPage = "/AdminUserInfo.jsp";
		rd = request.getRequestDispatcher(nextPage);
		rd.forward(request, response);
		return null;
	}
	
}
