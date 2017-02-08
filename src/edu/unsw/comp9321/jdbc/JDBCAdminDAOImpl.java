package edu.unsw.comp9321.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Logger;

public class JDBCAdminDAOImpl implements AdminDAO {

	static Logger logger = Logger.getLogger(JDBCUserDAOImpl.class.getName());
	private Connection connection;


	public JDBCAdminDAOImpl() throws Exception, SQLException {
		connection = DBConnectionFactory.getConnection();
		logger.info("Got connection");
	}
	
	
	
	
	@Override
	public AdminDTO find(String adminname) {
		// TODO Auto-generated method stub
		AdminDTO admin = null;
		try{
			PreparedStatement stmnt = connection
					.prepareStatement("SELECT * FROM ADMIN WHERE USERNAME=?");
			stmnt.setString(1, adminname);
			ResultSet res = stmnt.executeQuery();
			logger.info("The result set size is " + res.getFetchSize());
			while (res.next()) {
				admin = new AdminDTO(res.getString("USERNAME"));
				admin.setPassword(res.getString("PASSWORD"));


			}

			res.close();
			stmnt.close();
		} catch (Exception e) {
			System.out.println("Caught Exception");
			e.printStackTrace();
		}
		return admin;
			
			
			
		
	}

}
