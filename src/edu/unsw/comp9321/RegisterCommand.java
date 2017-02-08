package edu.unsw.comp9321;

import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

import edu.unsw.comp9321.jdbc.BookDAO;
import edu.unsw.comp9321.jdbc.BookDTO;
import edu.unsw.comp9321.jdbc.JDBCBookDAOImpl;
import edu.unsw.comp9321.jdbc.JDBCUserDAOImpl;
import edu.unsw.comp9321.jdbc.UserDAO;
import edu.unsw.comp9321.jdbc.UserDTO;

import java.util.UUID;

public class RegisterCommand implements Command {
	
	UserDAO users = null;
	
	/** Creates a new instance of LoginCommand 
	 * @throws ServletException */
	public RegisterCommand() throws ServletException {
		users = null;
		 
		try {
			users = new JDBCUserDAOImpl();
			
		} catch (Exception e) {
			
			throw new ServletException();
		}
	}

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String nextPage;
		String nickname = "";
		if(request.getParameter("action") == null || request.getParameter("action").equals("reset")){
			nextPage = "/register.jsp";
		}else if(request.getParameter("action").equals("activate")){
			if(users.activate(request.getParameter("key"))){
				request.setAttribute("message", "Your account has been activated. You may log in");
			}else{
				request.setAttribute("message", "The activation key is not valid");
			}
			nextPage = "/registerConfirm.jsp";
		}
		else{
			boolean complete = true;
			String username = request.getParameter("username");
			if(username.equals("")) 
				complete = false;
			String password = request.getParameter("password");
			if(password.equals(""))
				complete = false;
			String email = request.getParameter("email");
			if(email.equals(""))
				complete = false;
			String firstName = request.getParameter("first_name");
			if(firstName.equals(""))
				complete = false;
			String lastName = request.getParameter("last_name");
			if(lastName.equals(""))
				complete = false;
			nickname = request.getParameter("nick_name");
			String dob = request.getParameter("dob");
			if(dob.equals(""))
				complete = false;
			String stNo = request.getParameter("st_no");
			if(stNo.equals(""))
				complete = false;
			String stAddress = request.getParameter("st_address");
			if(stAddress.equals(""))
				complete = false;
			String suburb = request.getParameter("suburb");
			if(suburb.equals(""))
				complete = false;
			String postcode = request.getParameter("postcode");
			if(postcode.equals(""))
				complete = false;
			String state = request.getParameter("state");
			String credit = request.getParameter("credit_card");
			if(credit.equals(""))
				complete = false;
			String account = request.getParameter("account_type");
			
			if(complete){
				UserDTO user = new UserDTO(username);
				user.setPassword(password);
				user.setEmail(email);
				user.setfName(firstName);
				user.setlName(lastName);
				user.setNickname(nickname);
				user.setDOB(dob);
				user.setStNo(stNo);
				user.setStreet(stAddress);
				user.setSuburb(suburb);
				user.setPostcode(postcode);
				user.setState(state);
				user.setCreditCard(credit);
				user.setType(account);
				users.storeUser(user);
				
				
				String activationKey = UUID.randomUUID().toString();
				String link = String.valueOf(request.getRequestURL())+"?operation=register&action=activate&key="+activationKey;
				System.out.println(link);
				users.setActivationKey(username, activationKey);
				sendMessage(user, link);
				
				nextPage = "/registerEmail.jsp";
			}else{
				request.setAttribute("username", username);
				request.setAttribute("email", email);
				request.setAttribute("first_name", firstName);
				request.setAttribute("last_name", lastName);
				request.setAttribute("nick_name", nickname);
				request.setAttribute("dob", dob);
				request.setAttribute("st_no", stNo);
				request.setAttribute("st_address", stAddress);
				request.setAttribute("suburb", suburb);
				request.setAttribute("postcode", postcode);
				request.setAttribute("message", "Please complete all details");
				nextPage = "/register.jsp";

			}	
			
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(nextPage);
		rd.forward(request, response);
		return null;
	}
	
	private void sendMessage(UserDTO user, String link){
			
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

	 	         message.setSubject("Bookology account confirmation");
	 	         String msg = "Please click the link to activate your account<br>"
	 	        		 + "<a href=\""+link+"\">"+link+"</a>";
	 	         message.setContent(msg, "text/html" );
	 	         
	 	        Transport.send(message);
	 	         System.out.println("Sent message successfully....");
	 	      }catch (MessagingException mex) {
	 	         mex.printStackTrace();
	 	      }
		
	}

}
