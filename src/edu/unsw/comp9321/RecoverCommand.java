package edu.unsw.comp9321;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.unsw.comp9321.jdbc.JDBCUserDAOImpl;
import edu.unsw.comp9321.jdbc.UserDAO;
import edu.unsw.comp9321.jdbc.UserDTO;

public class RecoverCommand implements Command {

	UserDAO users = null;
	public RecoverCommand() throws ServletException {
		users = null;
		 
		try {
			users = new JDBCUserDAOImpl();
			
		} catch (Exception e) {
			
			throw new ServletException();
		}
	}
	
	
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nextPage = "/default.jsp";
		String username = request.getParameter("username");
		if (request.getParameter("action").equals("Submit")){
			UserDTO user = users.findUser(username);
			if (user != null){
				sendMessage(user);
			}
			nextPage = "/recoveryConfirm.jsp";
			request.setAttribute("message", "If your Username matches our record, we will send the password to your registered email address.");
			
			
		}
		RequestDispatcher rd = request.getRequestDispatcher(nextPage);
		rd.forward(request, response);
		
		
		
		
		return null;
	}
	
	private void sendMessage(UserDTO user){
		
    	String to = user.getEmail();
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

 	         message.setSubject("Bookology account recovery");
 	         String msg = "You password is " + user.getPassword() ;
 	        		 
 	         message.setContent(msg, "text/html" );
 	         
 	        Transport.send(message);
 	         System.out.println("Sent message successfully....");
 	      }catch (MessagingException mex) {
 	         mex.printStackTrace();
 	      }
	
	}

}
