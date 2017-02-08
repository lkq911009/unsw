package edu.unsw.comp9321.jdbc;

import java.util.List;

public interface CartHistoryDAO {
	
	public List<CartHistoryDTO> findAll();
	
	public List<CartHistoryDTO> findbyUser(String Username);
	
	public List<OrderDTO> orderfindbyUser(String Username);
}
