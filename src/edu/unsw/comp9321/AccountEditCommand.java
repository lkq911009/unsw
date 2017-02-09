package edu.unsw.comp9321;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.log.SystemLogHandler;

import edu.unsw.comp9321.jdbc.JDBCUserDAOImpl;
import edu.unsw.comp9321.jdbc.UserDAO;
import edu.unsw.comp9321.jdbc.UserDTO;

public class AccountEditCommand implements Command {

	UserDAO users = null;
	
	public AccountEditCommand() throws ServletException {
		// TODO Auto-generated constructor stub
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
		// TODO Auto-generated method stub
		String nextPage = "/AccountPage.jsp";
		System.out.println("111");
		String lkq="lkq shi da shuaige";
		String s="";
		UserDTO user = (UserDTO) request.getSession().getAttribute("user");
		String password = request.getParameter("password");
		if (!password.equals(""))
			user.setPassword(password);
		String firstName = request.getParameter("first_name");
		if (!firstName.equals(""))
			user.setfName(firstName);
		String lastName = request.getParameter("last_name");
		if (!lastName.equals(""))
			user.setlName(lastName);
		String nickname = request.getParameter("nick_name");
		if (!nickname.equals(""))
			user.setNickname(nickname);
		String dob = request.getParameter("dob");
		if (!dob.equals(""))
			user.setDOB(dob);
		String stNo = request.getParameter("st_no");
		if (!stNo.equals(""))
			user.setStNo(stNo);
		String stAddress = request.getParameter("st_address");
		if (!stAddress.equals(""))
			user.setStreet(stAddress);
		String suburb = request.getParameter("suburb");
		if (!suburb.equals(""))
			user.setSuburb(suburb);
		String postcode = request.getParameter("postcode");
		if (!postcode.equals(""))
			user.setPostcode(postcode);
		String state = request.getParameter("state");
		user.setState(state);
		String credit = request.getParameter("credit_card");
		if (!credit.equals(""))
			user.setCreditCard(credit);

		users.updateuser(user);
		

		RequestDispatcher rd = request.getRequestDispatcher(nextPage);
		rd.forward(request, response);
		return null;
	
		
	}

}
