package edu.unsw.comp9321;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.unsw.comp9321.jdbc.BookDAO;
import edu.unsw.comp9321.jdbc.BookDTO;
import edu.unsw.comp9321.jdbc.JDBCBookDAOImpl;

public class CartCommand implements Command {
	
	BookDAO books;

	public CartCommand() {
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
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String nextPage = "/cart.jsp";
        String username = (String)request.getSession().getAttribute("username");
		RequestDispatcher rd;
		
		if(action == null){
			System.out.println(request.getSession().getAttribute("back"));
			request.getSession().setAttribute("back", -1);
			System.out.println(request.getSession().getAttribute("back"));
		}
		else if(action.equalsIgnoreCase("Remove")){
			List<BookDTO> shoppingCart = (List<BookDTO>) request.getSession().getAttribute("cart");
	        int cartSize = shoppingCart.size();
			Calendar cal = Calendar.getInstance();
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        String time = sdf.format(cal.getTime());
	        
			for(Enumeration<String> e = request.getParameterNames();e.hasMoreElements();){
				try{
					int deleteInd = Integer.parseInt(e.nextElement());
					if(deleteInd >=0 && deleteInd<cartSize){
						BookDTO currBook= shoppingCart.get(deleteInd);
						shoppingCart.remove(deleteInd);
						books.removeFromCart(username, currBook, time);
						request.setAttribute("message", "Item(s) removed<br>");
					}
				}catch(NumberFormatException ex){
					//
				}
			}
			request.getSession().setAttribute("cart", shoppingCart);
			int back = (Integer) request.getSession().getAttribute("back");
			request.getSession().setAttribute("back", back-1);
		}
		else if(action.equalsIgnoreCase("purchase")){

			nextPage="/purchase.jsp";
		}
		rd = request.getRequestDispatcher(nextPage);
		rd.forward(request, response);
		return null;
	}

}
