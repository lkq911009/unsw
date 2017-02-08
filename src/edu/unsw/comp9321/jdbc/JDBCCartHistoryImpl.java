package edu.unsw.comp9321.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class JDBCCartHistoryImpl implements CartHistoryDAO {

	static Logger logger = Logger.getLogger(JDBCUserDAOImpl.class.getName());
	private Connection connection;
	
	public JDBCCartHistoryImpl() throws Exception {
		// TODO Auto-generated constructor stub
		connection = DBConnectionFactory.getConnection();
		logger.info("Got connection");
	}
	
	@Override
	public List<CartHistoryDTO> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CartHistoryDTO> findbyUser(String Username) {
		// TODO Auto-generated method stub
		ArrayList<CartHistoryDTO> historylist = new ArrayList<CartHistoryDTO>();

		try {
			PreparedStatement stmnt = connection
					.prepareStatement("SELECT * FROM cart_history WHERE USERNAME = ?");
			stmnt.setString(1,Username);
			ResultSet res = stmnt.executeQuery();
			logger.info("The result set size is " + res.getFetchSize());
			while (res.next()) {
				CartHistoryDTO  history = new CartHistoryDTO();
				history.SetCustomer(res.getString("USERNAME"));
				BookDAO books = new JDBCBookDAOImpl();
				BookDTO book = books.findbyID(res.getString("ENTRY_ID"));
				System.out.println(book.getTitle());
				
				history.SetTitle(book.getTitle());
				history.SetTitleID(book.getID());
				
				history.SetAddedTime(res.getString("ADDED_TIME"));
				history.SetRemovedTime(res.getString("REMOVED_TIME"));
				historylist.add(history);
			}
			res.close();
			stmnt.close();

		} catch (Exception e) {
			System.out.println("Caught Exception");
			e.printStackTrace();
		}
		return historylist;
	}

	@Override
	public List<OrderDTO> orderfindbyUser(String Username) {
		// TODO Auto-generated method stub
		ArrayList<OrderDTO> orderHistorys = new ArrayList<OrderDTO>();
				try{
					PreparedStatement stmnt = connection
						.prepareStatement("SELECT * FROM ORDERS, ENTRIES WHERE USERNAME = ?" +
								"AND ORDERS.ENTRY_ID = ENTRIES.ID");
					stmnt.setString(1, Username);
					ResultSet res = stmnt.executeQuery();
					while (res.next()) {
						OrderDTO orderHistory = new OrderDTO();
						orderHistory.SetCustomer(Username);
						orderHistory.SetPrice(res.getFloat("PRICE"));
						orderHistory.SetSaleTime(res.getString("SALE_TIME"));
						orderHistory.SetSeller(res.getString("SELLER"));
						orderHistory.SetTitle(res.getString("TITLE"));
						orderHistory.SetTitleID(res.getInt("ENTRY_ID"));
						orderHistorys.add(orderHistory);
						
					}
					res.close();
					stmnt.close();
					
				}catch (Exception e) {
					System.out.println("Caught Exception");
					e.printStackTrace();
				}
				
				
				
				return orderHistorys;
	}

}
