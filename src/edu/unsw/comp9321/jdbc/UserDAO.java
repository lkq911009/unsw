package edu.unsw.comp9321.jdbc;

import java.util.List;

public interface UserDAO {
	
	public List<UserDTO> findAll();

	public UserDTO findUser(String value);
	
	public void storeUser(UserDTO newUser);
	
	public List<BookDTO> getSellingBooks(String username);
	
	public void setActivationKey(String username, String activationKey);
	
	public boolean activate(String activationKey);
	
	public void ban(String ban, String username);
	
	public List<UserDTO> findCustomerOnly();

	public List<OrderDTO> orderHistory(String username);
	
	public void updateuser(UserDTO user);
	
	public List<UserDTO> findusername(String value);
	
	
}
