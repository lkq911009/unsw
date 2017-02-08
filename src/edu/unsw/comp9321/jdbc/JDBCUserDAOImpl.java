package edu.unsw.comp9321.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;
import java.sql.PreparedStatement;

public class JDBCUserDAOImpl implements UserDAO {

	static Logger logger = Logger.getLogger(JDBCUserDAOImpl.class.getName());
	private Connection connection;
	DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public JDBCUserDAOImpl() throws Exception, SQLException {
		connection = DBConnectionFactory.getConnection();
		logger.info("Got connection");
	}

	@Override
	public List<UserDTO> findAll() {
		// TODO
		ArrayList<UserDTO> users = new ArrayList<UserDTO>();
		try {
			Statement stmnt = connection.createStatement();
			String query = "SELECT * FROM USERS";
			ResultSet res = stmnt.executeQuery(query);
			logger.info("The result set size is " + res.getFetchSize());
			while (res.next()) {
				UserDTO user = new UserDTO(res.getString("USERNAME"));
				user.setPassword(res.getString("PASSWORD"));
				user.setActivated(Boolean.valueOf(res.getString("ACTIVATED")));
				user.setBanned(Boolean.valueOf(res.getString("BANNED")));
				user.setCreditCard(String.valueOf(res.getLong("CREDIT_CARD")));
				user.setDOB(String.valueOf(res.getDate("DOB")));
				user.setStNo(res.getString("ST_NO"));
				user.setStreet(res.getString("ST_ADDRESS"));
				user.setSuburb(res.getString("SUBURB"));
				user.setPostcode(String.valueOf(res.getInt("POSTCODE")));
				user.setState(res.getString("STATE"));
				user.setType(res.getString("USER_TYPE"));
				user.setEmail(res.getString("EMAIL"));
				user.setNickname(res.getString("NICKNAME"));
				user.setfName(res.getString("FIRST_NAME"));
				user.setlName(res.getString("LAST_NAME"));
				users.add(user);
			}

			res.close();
			stmnt.close();

		} catch (Exception e) {
			System.out.println("Caught Exception");
			e.printStackTrace();
		}
		return users;
	}

	public List<BookDTO> getSellingBooks(String username) {
		// TODO:
		return null;
	}

	@Override
	public UserDTO findUser(String username) {
		// TESTING FOR USERNAME, PASSWORD MATCH
		UserDTO user = null;
		try {
			PreparedStatement stmnt = connection
					.prepareStatement("SELECT * FROM USERS WHERE USERNAME=?");
			stmnt.setString(1, username);
			ResultSet res = stmnt.executeQuery();
			logger.info("The result set size is " + res.getFetchSize());
			while (res.next()) {
				user = new UserDTO(res.getString("USERNAME"));
				user.setPassword(res.getString("PASSWORD"));
				user.setActivated(Boolean.valueOf(res.getString("ACTIVATED")));
				user.setBanned(Boolean.valueOf(res.getString("BANNED")));
				user.setCreditCard(String.valueOf(res.getLong("CREDIT_CARD")));
				user.setDOB(String.valueOf(res.getDate("DOB")));
				user.setStNo(res.getString("ST_NO"));
				user.setStreet(res.getString("ST_ADDRESS"));
				user.setSuburb(res.getString("SUBURB"));
				user.setPostcode(String.valueOf(res.getInt("POSTCODE")));
				user.setState(res.getString("STATE"));
				user.setType(res.getString("USER_TYPE"));
				user.setEmail(res.getString("EMAIL"));
				user.setNickname(res.getString("NICKNAME"));
				user.setfName(res.getString("FIRST_NAME"));
				user.setlName(res.getString("LAST_NAME"));
			}

			res.close();
			stmnt.close();

		} catch (Exception e) {
			System.out.println("Caught Exception");
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public void storeUser(UserDTO newUser) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement stmnt = connection
					.prepareStatement("INSERT INTO USERS VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
			stmnt.setString(1, newUser.getUsername());
			stmnt.setString(2, newUser.getPassword());
			stmnt.setString(3, newUser.getEmail());
			stmnt.setString(4, newUser.getNickname());
			stmnt.setString(5, newUser.getfName());
			stmnt.setString(6, newUser.getlName());
			stmnt.setString(7, newUser.getDOB());
			stmnt.setString(8, newUser.getStNo());
			stmnt.setString(9, newUser.getStreet());
			stmnt.setString(10, newUser.getSuburb());
			stmnt.setString(11, newUser.getPostcode());
			stmnt.setString(12, newUser.getState());
			stmnt.setString(13, newUser.getCreditCard());
			
			stmnt.setString(14, Boolean.toString(newUser.getBanned()));
			
			stmnt.setString(15, newUser.getType());

			stmnt.setString(16, Boolean.toString(newUser.getActivated()));
			
			stmnt.execute();

			stmnt.close();

		} catch (Exception e) {
			System.out.println("Caught Exception");
			e.printStackTrace();
		}

	}

	@Override
	public void setActivationKey(String username, String activationKey) {
		try {
			PreparedStatement stmnt = connection
					.prepareStatement("INSERT INTO USER_ACTIVATIONS VALUES (?, ?);");
			stmnt.setString(1, username);
			stmnt.setString(2, activationKey);
			stmnt.execute();

			stmnt.close();

		} catch (Exception e) {
			System.out.println("Caught Exception");
			e.printStackTrace();
		}
		
	}

	@Override
	public boolean activate(String activationKey) {
		String username = null;
		try {
			PreparedStatement stmnt = connection
					.prepareStatement("SELECT USERNAME FROM USER_ACTIVATIONS WHERE ACTIVATION_KEY= ?");
			stmnt.setString(1, activationKey);
			ResultSet res = stmnt.executeQuery();
			while (res.next()) {
				username = res.getString("USERNAME");
			}
			res.close();
			stmnt.close();
			
			if(username == null) return false;
			
			stmnt = connection
					.prepareStatement("DELETE FROM USER_ACTIVATIONS WHERE USERNAME = ?");
			stmnt.setString(1, username);
			stmnt.execute();
			stmnt.close();
			
			stmnt = connection
					.prepareStatement("UPDATE USERS "
							+ "SET ACTIVATED = 'TRUE' WHERE USERNAME = ?");
			stmnt.setString(1, username);
			stmnt.executeUpdate();
			stmnt.close();
			
		} catch (Exception e) {
			System.out.println("Caught Exception");
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public List<UserDTO> findCustomerOnly() {
		// TODO Auto-generated method stub
		ArrayList<UserDTO> users = new ArrayList<UserDTO>();
		try {
			PreparedStatement stmnt = connection
					.prepareStatement("SELECT * FROM USERS WHERE USER_TYPE = ?");
			stmnt.setString(1, "CUSTOMER");
			ResultSet res = stmnt.executeQuery();
			logger.info("The result set size is " + res.getFetchSize());
			while (res.next()) {
				UserDTO user = new UserDTO(res.getString("USERNAME"));
				user.setPassword(res.getString("PASSWORD"));
				user.setActivated(Boolean.valueOf(res.getString("ACTIVATED")));
				user.setBanned(Boolean.valueOf(res.getString("BANNED")));
				user.setCreditCard(String.valueOf(res.getLong("CREDIT_CARD")));
				user.setDOB(String.valueOf(res.getDate("DOB")));
				user.setStNo(res.getString("ST_NO"));
				user.setStreet(res.getString("ST_ADDRESS"));
				user.setSuburb(res.getString("SUBURB"));
				user.setPostcode(String.valueOf(res.getInt("POSTCODE")));
				user.setState(res.getString("STATE"));
				user.setType(res.getString("USER_TYPE"));
				user.setEmail(res.getString("EMAIL"));
				user.setNickname(res.getString("NICKNAME"));
				user.setfName(res.getString("FIRST_NAME"));
				user.setlName(res.getString("LAST_NAME"));
				users.add(user);
			}

			res.close();
			stmnt.close();

		} catch (Exception e) {
			System.out.println("Caught Exception");
			e.printStackTrace();
		}
		
		return users;
	}

	@Override
	public void ban(String ban, String username) {
		// TODO Auto-generated method stub
		
		try {
			PreparedStatement stmnt = connection
					.prepareStatement("UPDATE USERS "
							+ "SET BANNED = ? WHERE USERNAME = ?");
			stmnt.setString(1, ban);
			stmnt.setString(2, username);
			stmnt.executeUpdate();
			
			stmnt.close();
			
		} catch (Exception e) {
			System.out.println("Caught Exception");
			e.printStackTrace();
		}
		return;
	}

	@Override
	public List<OrderDTO> orderHistory(String username) {
		// TODO Auto-generated method stub
		ArrayList<OrderDTO> orders = new ArrayList<OrderDTO>();
		try {
			PreparedStatement stmnt = connection
					.prepareStatement("SELECT * FROM ORDERS WHERE USERNAME= ?");
			stmnt.setString(1, username);
			ResultSet res = stmnt.executeQuery();
			BookDAO books = new JDBCBookDAOImpl();
			while(res.next()){
				
				OrderDTO order = new OrderDTO();
				order.SetCustomer(res.getString("USERNAME"));
				BookDTO book = books.findbyID(res.getString("ENTRY_ID"));
				order.SetPrice(book.getCost());
				order.SetSaleTime(res.getString("SALE_TIME"));
				order.SetSeller(book.getSeller());
				order.SetTitle(book.getTitle());
				orders.add(order);
			}
			res.close();
			stmnt.close();
			
		} catch (Exception e) {
			System.out.println("Caught Exception");
			e.printStackTrace();
		}
		return orders;
	}

	@Override
	public void updateuser(UserDTO user) {
		// TODO Auto-generated method stub
		String username = user.getUsername();
		String password = user.getPassword();
		String fName = user.getfName();
		String lName = user.getlName();
		String nickname = user.getNickname();
		String postcode = user.getPostcode();
		String state = user.getState();
		String stNo = user.getStNo();
		String street = user.getStreet();
		String suburb = user.getSuburb();
		String creditcard = user.getCreditCard();
		String DOB = user.getDOB();

		try {
			PreparedStatement stmnt = connection
					.prepareStatement("UPDATE USERS " +  
							"SET PASSWORD = ?, FIRST_NAME  = ?, LAST_NAME = ?,NICKNAME = ?,POSTCODE = ?,STATE = ?,"
							+ "ST_NO = ?,ST_ADDRESS = ?,SUBURB = ?,CREDIT_CARD = ?,DOB = ? WHERE USERNAME = ?;");
			stmnt.setString(1,password);
			stmnt.setString(2, fName);
			stmnt.setString(3, lName);
			stmnt.setString(4, nickname);
			stmnt.setString(5, postcode);
			stmnt.setString(6, state);
			stmnt.setString(7, stNo);
			stmnt.setString(8, street);
			stmnt.setString(9, suburb);
			stmnt.setString(10, creditcard);
			stmnt.setString(11, DOB);
			stmnt.setString(12, username);

			
			
			stmnt.executeUpdate();
			stmnt.close();

		} catch (Exception e) {
			System.out.println("Caught Exception");
			e.printStackTrace();
		}
		
	}

	@Override
	public List<UserDTO> findusername(String value) {
		// TODO Auto-generated method stub
		List<UserDTO> users = new ArrayList<UserDTO>();
		String t = String.format("%s", value);
		try {
			PreparedStatement stmnt = connection
					.prepareStatement("SELECT * FROM USERS WHERE USERNAME REGEXP ? ;");
			stmnt.setString(1,t);
			ResultSet res = stmnt.executeQuery();
			logger.info("The result set size is " + res.getFetchSize());
			while (res.next()) {
				UserDTO user = new UserDTO(res.getString("USERNAME"));
				System.out.println(res.getString("USERNAME"));
				user.setPassword(res.getString("PASSWORD"));
				user.setActivated(Boolean.valueOf(res.getString("ACTIVATED")));
				user.setBanned(Boolean.valueOf(res.getString("BANNED")));
				user.setCreditCard(String.valueOf(res.getLong("CREDIT_CARD")));
				user.setDOB(String.valueOf(res.getDate("DOB")));
				user.setStNo(res.getString("ST_NO"));
				user.setStreet(res.getString("ST_ADDRESS"));
				user.setSuburb(res.getString("SUBURB"));
				user.setPostcode(String.valueOf(res.getInt("POSTCODE")));
				user.setState(res.getString("STATE"));
				user.setType(res.getString("USER_TYPE"));
				user.setEmail(res.getString("EMAIL"));
				user.setNickname(res.getString("NICKNAME"));
				user.setfName(res.getString("FIRST_NAME"));
				user.setlName(res.getString("LAST_NAME"));
				users.add(user);
			}
			res.close();
			stmnt.close();


		} catch (Exception e) {
			System.out.println("Caught Exception");
			e.printStackTrace();
		}
		return users;
	}

}
