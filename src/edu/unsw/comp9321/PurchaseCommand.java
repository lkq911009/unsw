package edu.unsw.comp9321;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

import edu.unsw.comp9321.jdbc.*;

public class PurchaseCommand implements Command {
	BookDAO books;
	UserDAO users;

	public PurchaseCommand() {
		books = null;
		try {
			books = new JDBCBookDAOImpl();
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
		String username = (String)request.getSession().getAttribute("username");
		String nextPage;
		List<BookDTO> shoppingCart = (List<BookDTO>) request.getSession().getAttribute("cart");
		RequestDispatcher rd;
		
		if(request.getParameter("action").equalsIgnoreCase("Back to Cart")){
			nextPage = "/cart.jsp";
			int back = (Integer) request.getSession().getAttribute("back");
			request.getSession().setAttribute("back", back-1);
		}
		else{
			Calendar cal = Calendar.getInstance();
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        String time = sdf.format(cal.getTime());
	        books.buyBooks(username, shoppingCart, time);
	        sendMessages(shoppingCart, username);
	        shoppingCart.clear();
			request.getSession().setAttribute("cart", shoppingCart);
			
			nextPage = "/confirmed.jsp";
		}
		rd = request.getRequestDispatcher(nextPage);
		rd.forward(request, response);
		
		return null;
	}
	
	private void sendMessages(List<BookDTO> books, String buyer){
		HashMap<String,ArrayList<BookDTO>> sellers = new HashMap<String,ArrayList<BookDTO>>();
		for(BookDTO book:books){
			if(!sellers.containsKey(book.getSeller())){
				ArrayList<BookDTO> list = new ArrayList<BookDTO>();
				list.add(book);
				sellers.put(book.getSeller(), list);
			}else{
				sellers.get(book.getSeller()).add(book);
			}
		}
		for(String s : sellers.keySet()){
			
	    	UserDTO seller= users.findUser(s);
	    	System.out.println(seller);
	    	String to = seller.getEmail();
	    	System.out.println("email"+to);
	    	String host = "smtp.gmail.com";
	    	String from = "booktopia2015@gmail.com";
	    	String pw = "booktopia";

	    	Properties properties = System.getProperties();
	    	properties.setProperty("mail.smtp.ssl.enable", "true");
	 	    properties.setProperty("mail.smtp.host", host);
	 	   properties.put("mail.smtp.user", from);
	 	  properties.put("mail.smtp.password", pw);
	 	 properties.put("mail.smtp.port", "465"); 
	 	 properties.put("mail.smtp.auth", "true");
	 	    Session session = Session.getDefaultInstance(properties, new SmtAuthenticator());
	 	      try{
	 	         MimeMessage message = new MimeMessage(session);

	 	         message.setFrom(new InternetAddress(from));

	 	         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

	 	         message.setSubject("Bookology: Your books have been sold");
	 	         String msg = "<h1>Bookology</h1>Congratulations "+s+"! You've sold items to "+buyer+":<Br>"+
	 	         "<Table><tr><th>ID</th><th>title</th><th>price</th></tr>";
	 	         List<BookDTO> soldItems = sellers.get(s);
	 	         for(BookDTO item : soldItems){
	 	        	 msg=msg+String.format("<tr><td>%d</td><td>%s</td><td>%.2f</td></tr>", 
	 	        			 item.getID(),item.getTitle(),item.getCost());
	 	         }
	 	         msg=msg+"</table><p>Thank you for choosing Bookology</p>";
	 	         message.setContent(msg, "text/html" );
	 	        Transport.send(message);
	 	         System.out.println("Sent message successfully....");
	 	      }catch (MessagingException mex) {
	 	         mex.printStackTrace();
	 	      }
		}
	    
	}

}


