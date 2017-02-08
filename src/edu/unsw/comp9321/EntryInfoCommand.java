package edu.unsw.comp9321;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.unsw.comp9321.jdbc.BookDAO;
import edu.unsw.comp9321.jdbc.BookDTO;
import edu.unsw.comp9321.jdbc.JDBCBookDAOImpl;

public class EntryInfoCommand implements Command {
	BookDAO books;
	/** Creates a new instance of AddCommand */
	public EntryInfoCommand() {
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
		
		RequestDispatcher rd;
		String username = (String)request.getSession().getAttribute("username");
		String nextPage = "/info.jsp";

		//For viewing entry from results
		if(request.getParameter("action")==null){
			if(request.getParameter("from")!=null && request.getParameter("from").equals("cart")){
				request.getSession().setAttribute("results", (List<BookDTO>)request.getSession().getAttribute("cart"));
			}
			request.getSession().setAttribute("i", request.getParameter("i"));
			request.getSession().setAttribute("back", -1);
		}
		else{
			List<BookDTO> results = (List<BookDTO>)request.getSession().getAttribute("results");
			System.out.println(results);
			List<BookDTO> shoppingCart = (List<BookDTO>)request.getSession().getAttribute("cart");	
			int ind = Integer.parseInt((String)request.getParameter("i"));
			
			BookDTO currBook = results.get(ind);
			if(shoppingCart.contains(currBook)){
				request.setAttribute("message", "Already in cart<br>");
			}else{
				shoppingCart.add(currBook);
				request.getSession().setAttribute("cart", shoppingCart);
				request.setAttribute("message", "Added to cart<br>");
				
		        Calendar cal = Calendar.getInstance();
		        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		        books.addToCart(username, currBook, sdf.format(cal.getTime()) );
			}
			
			if(request.getParameter("from")!=null && request.getParameter("from").equals("home")){
				request.setAttribute("addedItem", ind);
				nextPage="/search.jsp";
			}
			else{
				int back = (Integer) request.getSession().getAttribute("back");
				request.getSession().setAttribute("back", back-1);
			}
			
		}
		rd = request.getRequestDispatcher(nextPage);
		rd.forward(request, response);
		
		
		
		
		return null;
	}

}
