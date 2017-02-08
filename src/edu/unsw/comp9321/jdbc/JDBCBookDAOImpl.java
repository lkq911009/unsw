package edu.unsw.comp9321.jdbc;

import java.util.List;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Logger;
import java.sql.PreparedStatement;

public class JDBCBookDAOImpl implements BookDAO {
	
	static Logger logger = Logger.getLogger(JDBCUserDAOImpl.class.getName());
	private Connection connection;
	DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public JDBCBookDAOImpl() throws Exception, SQLException {
		connection = DBConnectionFactory.getConnection();
		logger.info("Got connection");
	}
	
	@Override
	public List<BookDTO> findAll() {
		ArrayList<BookDTO> books = new ArrayList<BookDTO>();
		try {
			Statement stmnt = connection.createStatement();
			String query = "SELECT * FROM ENTRIES WHERE PAUSED = 'FALSE'";
			ResultSet res = stmnt.executeQuery(query);
			logger.info("The result set size is " + res.getFetchSize());
			books = setBooks(res);
			res.close();
			stmnt.close();

		} catch (Exception e) {
			System.out.println("Caught Exception");
			e.printStackTrace();
		}
		return books;
	}

	@Override
	public List<BookDTO> findBooksByName(String title) {
		List<BookDTO> books = null;
		String t = String.format("^%s$|^%s | %s$| %s ", title, title, title, title);
		try {
			PreparedStatement stmnt = connection
					.prepareStatement("SELECT * FROM ENTRIES WHERE TITLE REGEXP ? AND PAUSED = 'FALSE';");
			stmnt.setString(1,t);
			ResultSet res = stmnt.executeQuery();
			logger.info("The result set size is " + res.getFetchSize());
			books = setBooks(res);
			res.close();
			stmnt.close();

		} catch (Exception e) {
			System.out.println("Caught Exception");
			e.printStackTrace();
		}
		return books;
	}

	@Override
	public BookDTO findBookbyDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void storeBook(BookDTO book) {
		// TODO Auto-generated method stub
		try{
			String title = "UNDEFINE", author = "UNDEFINE", editor = "UNDEFINE", 
			pages = "UNDEFINE", bookTitle = "UNDEFINE", publisher = "UNDEFINE", 
			journal = "UNDEFINE", volume = "UNDEFINE", number = "UNDEFINE",
			month = "UNDEFINE", isbn = "UNDEFINE", type = "UNDEFINE",picture ="";
			int year = 0;
			float cost = 0;
			if (book.getTitle() != ""){
				title = book.getTitle();
			}
			if (book.getAuthors() != ""){
				author = book.getAuthors();
			}
			if (book.getEditors() != ""){
				editor = book.getEditors();
			}
			if (book.getPages() != ""){
				pages = book.getPages();
			}
			if (book.getBookTitle() != ""){
				bookTitle = book.getBookTitle();
			}
			if (book.getPublisher() != ""){
				publisher = book.getPublisher();
			}
			if (book.getJournal() != ""){
				journal = book.getJournal();
			}
			if (book.getVolume() != ""){
				volume = book.getVolume();
			}
			if (book.getNumber() != ""){
				number = book.getNumber();
			}
			if (book.getMonth() != ""){
				month = book.getMonth();
			}
			if (book.getISBN() != ""){
				isbn = book.getISBN();
			}
			if (book.getType() != ""){
				type = book.getType();
			}
			if (book.getYear() > 0){
				year = book.getYear();
			}
			if (book.getCost() > 0){
				cost = book.getCost();
			}
			if (book.getPicture() != ""){
				picture = book.getPicture();
			}
			String seller = book.getSeller();
			PreparedStatement stmnt = connection.prepareStatement("INSERT INTO ENTRIES (SELLER, TITLE, AUTHORS, EDITORS,TYPES, BOOKTITLE, PAGES, YEAR,"
					+ "PUBLISHER, JOURNAL, VOLUME, NUMBER, MONTH, ISBN,PRICE,PICTURE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
			stmnt.setString(1, seller);
			stmnt.setString(2, title);
			stmnt.setString(3, author);
			stmnt.setString(4, editor);
			stmnt.setString(5, type);
			stmnt.setString(6, bookTitle);
			stmnt.setString(7, pages);
			stmnt.setInt(8, year);
			stmnt.setString(9, publisher);
			stmnt.setString(10, journal);
			stmnt.setString(11, volume);
			stmnt.setString(12, number);
			stmnt.setString(13, month);
			stmnt.setString(14, isbn);
			stmnt.setFloat(15, cost);
			stmnt.setString(16, picture);
			stmnt.execute();
			stmnt.close();
			
		}catch (Exception e) {
			System.out.println("Caught Exception");
			e.printStackTrace();
		}
		
	}

	@Override
	public List<BookDTO> findBooks() {
		ArrayList<BookDTO> books = new ArrayList<BookDTO>();
		try {
			Statement stmnt = connection.createStatement();
			String query = "SELECT * FROM ENTRIES WHERE ID IN (SELECT ID FROM BOOKS) AND PAUSED = 'FALSE';";
			ResultSet res = stmnt.executeQuery(query);
			logger.info("The result set size is " + res.getFetchSize());
			books = setBooks(res);
			res.close();
			stmnt.close();

		} catch (Exception e) {
			System.out.println("Caught Exception");
			e.printStackTrace();
		}
		return books;
	}

	@Override
	public List<BookDTO> findConferences() {
		ArrayList<BookDTO> books = new ArrayList<BookDTO>();
		try {
			Statement stmnt = connection.createStatement();
			String query = "SELECT * FROM ENTRIES WHERE ID IN (SELECT ID FROM CONFERENCES) AND PAUSED ='FALSE';";
			ResultSet res = stmnt.executeQuery(query);
			logger.info("The result set size is " + res.getFetchSize());
			books = setBooks(res);
			res.close();
			stmnt.close();

		} catch (Exception e) {
			System.out.println("Caught Exception");
			e.printStackTrace();
		}
		return books;
	}

	@Override
	public List<BookDTO> findJournals() {
		ArrayList<BookDTO> books = new ArrayList<BookDTO>();
		try {
			Statement stmnt = connection.createStatement();
			String query = "SELECT * FROM ENTRIES WHERE ID IN (SELECT ID FROM JOURNALS) AND PAUSED='FALSE';";
			ResultSet res = stmnt.executeQuery(query);
			logger.info("The result set size is " + res.getFetchSize());
			books = setBooks(res);
			res.close();
			stmnt.close();

		} catch (Exception e) {
			System.out.println("Caught Exception");
			e.printStackTrace();
		}
		return books;
	}

	@Override
	public List<BookDTO> findMatching(String title, String author, String publisher, String[] types) {
		ArrayList<BookDTO> books = new ArrayList<BookDTO>();
		if(types==null){
			String query = "SELECT * FROM ENTRIES";
			String attach = "";
			if(!title.equals("")){
				
				attach = " WHERE TITLE REGEXP "+String.format("'^%s$|^%s | %s$| %s '", title, title, title, title);
			}
			if(!author.equals("")){
				
				if(attach.equals("")){
					attach = " WHERE AUTHORS REGEXP "+String.format("'^%s$|^%s | %s$| %s '", author, author, author, author);
				}else{
					attach = attach + " AND AUTHORS REGEXP "+String.format("'^%s$|^%s | %s$| %s '", author, author, author, author);
				}
			}
			if(!publisher.equals("")){
				
				if(attach.equals("")){
					attach = " WHERE PUBLISHER REGEXP "+String.format("'^%s$|^%s | %s$| %s '", publisher, publisher, publisher, publisher);
				}else{
					attach = attach + " AND PUBLISHER REGEXP "+String.format("'^%s$|^%s | %s$| %s '", publisher, publisher, publisher, publisher);
				}
			}
			query=query+attach+" AND PAUSED='FALSE';";
			System.out.println(query);
			try {
				Statement stmnt = connection.createStatement();
				ResultSet res = stmnt.executeQuery(query);
				logger.info("The result set size is " + res.getFetchSize());
				books = setBooks(res);
				res.close();
				stmnt.close();
				
			} catch (Exception e) {
				System.out.println("Caught Exception");
				e.printStackTrace();
			}
			return books;
		}	
		for(String t:types){

			String query = "SELECT * FROM ENTRIES WHERE ID IN (SELECT ID FROM "+t+")";
			String attach = "";
			if(!title.equals("")){
				attach = " AND TITLE REGEXP "+String.format("'^%s$|^%s | %s$| %s '", title, title, title, title);
			}
			if(!author.equals("")){
				attach = attach + " AND AUTHORS REGEXP "+String.format("'^%s$|^%s | %s$| %s '", author, author, author, author);
			}
			if(!publisher.equals("")){
					attach = attach + " AND WHERE PUBLISHER REGEXP "+String.format("'^%s$|^%s | %s$| %s '", publisher, publisher, publisher, publisher);
			}
			query=query+attach+" AND PAUSED = 'FALSE';";
			System.out.println(query);
			try {
				Statement stmnt = connection.createStatement();
					ResultSet res = stmnt.executeQuery(query);
					logger.info("The result set size is " + res.getFetchSize());
					books.addAll(setBooks(res));
					res.close();
					stmnt.close();
				
			} catch (Exception e) {
				System.out.println("Caught Exception");
				e.printStackTrace();
			}
		}
		return books;
	}
	
	private ArrayList<BookDTO> setBooks(ResultSet res) throws SQLException{
		ArrayList<BookDTO> books = new ArrayList<BookDTO>();
		while (res.next()) {

			BookDTO book = new BookDTO(res.getInt("ID"));
			book.setSeller(res.getString("SELLER"));
			book.setTitle(res.getString("TITLE"));
			book.setAuthors(res.getString("AUTHORS"));
			book.setEditors(res.getString("EDITORS"));
			book.setType(res.getString("TYPES"));
			book.setBookTitle(res.getString("BOOKTITLE"));
			book.setPages(res.getString("PAGES"));
			book.setYear(res.getInt("YEAR"));
			book.setPublisher(res.getString("PUBLISHER"));
			book.setJournal(res.getString("JOURNAL"));
			book.setVolume(res.getString("VOLUME"));
			book.setNumber(res.getString("NUMBER"));
			book.setMonth(res.getString("MONTH"));
			book.setPicture(res.getString("PICTURE"));
			book.setCost(res.getFloat("PRICE"));
			book.setISBN(res.getString("ISBN"));
			book.setPaused(Boolean.valueOf(res.getString("PAUSED")));	
			if(book.getPicture() == null || book.getPicture().equals("")){
				book.setPicture("http://www.jordans.com/~/media/Jordans%20Redesign/No-image-found.jpg");
				System.out.println("adding pict");
			}
			books.add(book);
		}
		return books;
	}

	@Override
	public List<BookDTO> findBooksByAuthor(String author) {
		List<BookDTO> books = null;
		String t = String.format("^%s$|^%s | %s$| %s ", author, author, author, author);
		try {
			PreparedStatement stmnt = connection
					.prepareStatement("SELECT * FROM ENTRIES WHERE AUTHORS REGEXP ? AND PAUSED = 'FALSE'");
			stmnt.setString(1,t);
			ResultSet res = stmnt.executeQuery();
			logger.info("The result set size is " + res.getFetchSize());
			books = setBooks(res);
			res.close();
			stmnt.close();

		} catch (Exception e) {
			System.out.println("Caught Exception");
			e.printStackTrace();
		}
		return books;
	}

	@Override
	public List<BookDTO> findBooksByEditor(String editor) {
		List<BookDTO> books = null;
		String t = String.format("^%s$|^%s | %s$| %s ", editor, editor, editor, editor);
		try {
			PreparedStatement stmnt = connection
					.prepareStatement("SELECT * FROM ENTRIES WHERE EDITORS REGEXP ? AND PAUSED = 'FALSE'");
			stmnt.setString(1,t);
			ResultSet res = stmnt.executeQuery();
			logger.info("The result set size is " + res.getFetchSize());
			books = setBooks(res);
			res.close();
			stmnt.close();

		} catch (Exception e) {
			System.out.println("Caught Exception");
			e.printStackTrace();
		}
		return books;
	}

	@Override
	public List<BookDTO> findBooksByPublisher(String publisher) {
		List<BookDTO> books = null;
		String t = String.format("^%s$|^%s | %s$| %s ", publisher, publisher, publisher, publisher);
		try {
			PreparedStatement stmnt = connection
					.prepareStatement("SELECT * FROM ENTRIES WHERE PUBLISHER REGEXP ? AND PAUSED = 'FALSE'");
			stmnt.setString(1,t);
			ResultSet res = stmnt.executeQuery();
			logger.info("The result set size is " + res.getFetchSize());
			books = setBooks(res);
			res.close();
			stmnt.close();

		} catch (Exception e) {
			System.out.println("Caught Exception");
			e.printStackTrace();
		}
		return books;
	}

	@Override
	public List<BookDTO> findBooksByJournal(String journal) {
		List<BookDTO> books = null;
		String t = String.format("^%s$|^%s | %s$| %s ", journal, journal, journal, journal);
		try {
			PreparedStatement stmnt = connection
					.prepareStatement("SELECT * FROM ENTRIES WHERE JOURNAL REGEXP ? AND PAUSED='FALSE'");
			stmnt.setString(1,t);
			ResultSet res = stmnt.executeQuery();
			logger.info("The result set size is " + res.getFetchSize());
			books = setBooks(res);
			res.close();
			stmnt.close();

		} catch (Exception e) {
			System.out.println("Caught Exception");
			e.printStackTrace();
		}
		return books;
	}

	@Override
	public List<BookDTO> getRandom() {
		ArrayList<BookDTO> books = new ArrayList<BookDTO>();
		int size;
		try {
			Statement st = connection.createStatement();
			String query = "SELECT MAX(ID) FROM ENTRIES;";
			ResultSet res = st.executeQuery(query);
			if(res.wasNull()){
				size = 200;
			}else{
				res.next();
				size = res.getInt(1);
			}
			res.close();
			st.close();
			
			while(books.size()<10){
				int randNo = (int)(Math.max(Math.random()*size, 1));
				PreparedStatement stmnt = connection
						.prepareStatement("SELECT * FROM ENTRIES WHERE ID = ? AND PAUSED = 'FALSE'");
				stmnt.setInt(1,randNo);
				res = stmnt.executeQuery();
				if(res.next()){
					BookDTO book = new BookDTO(res.getInt("ID"));
					book.setSeller(res.getString("SELLER"));
					book.setTitle(res.getString("TITLE"));
					book.setAuthors(res.getString("AUTHORS"));
					book.setEditors(res.getString("EDITORS"));
					book.setType(res.getString("TYPES"));
					book.setBookTitle(res.getString("BOOKTITLE"));
					book.setPages(res.getString("PAGES"));
					book.setYear(res.getInt("YEAR"));
					book.setPublisher(res.getString("PUBLISHER"));
					book.setJournal(res.getString("JOURNAL"));
					book.setVolume(res.getString("VOLUME"));
					book.setNumber(res.getString("NUMBER"));
					book.setMonth(res.getString("MONTH"));
					book.setPicture(res.getString("PICTURE"));
					book.setCost(res.getFloat("PRICE"));
					book.setISBN(res.getString("ISBN"));
					book.setPaused(Boolean.valueOf(res.getString("PAUSED")));
					if(book.getPicture() == null || book.getPicture().equals("")){
						book.setPicture("http://www.jordans.com/~/media/Jordans%20Redesign/No-image-found.jpg");
					}
					books.add(book);
				}
				res.close();
				stmnt.close();
			}

		} catch (Exception e) {
			System.out.println("Caught Exception");
			e.printStackTrace();
		}
		return books;
	}

	@Override
	public List<BookDTO> getShoppingCart(String username) {	
		List<BookDTO> books = null;
		String query = "SELECT * FROM ENTRIES WHERE ID IN" 
		+"(SELECT ENTRY_ID FROM SHOPPING_CART WHERE USERNAME= ?);";
		try {
			PreparedStatement stmnt = connection.prepareStatement(query);
			stmnt.setString(1,username);
			ResultSet res = stmnt.executeQuery();
			logger.info("The result set size is " + res.getFetchSize());
			books = setBooks(res);
			res.close();
			stmnt.close();

		} catch (Exception e) {
			System.out.println("Caught Exception");
			e.printStackTrace();
		}
		System.out.println(books);
		return books;
		
	}

	@Override
	public void addToCart(String username, BookDTO book, String time) {
		
		try {
			PreparedStatement stmnt = connection
					.prepareStatement("INSERT INTO SHOPPING_CART VALUES (?, ?, ?);");
			stmnt.setString(1, username);
			stmnt.setInt(2, book.getID());
			stmnt.setString(3, time);
			
			stmnt.execute();

			stmnt.close();

		} catch (Exception e) {
			System.out.println("Caught Exception");
			e.printStackTrace();
		}
		
	}

	@Override
	public void removeFromCart(String username, BookDTO book, String removedTime) {
		System.out.println(username);
		try {
			String addedTime = "";
			PreparedStatement stmnt = connection
					.prepareStatement("SELECT * FROM SHOPPING_CART WHERE USERNAME = ? AND ENTRY_ID = ?");
			stmnt.setString(1, username);
			stmnt.setInt(2, book.getID());	

			ResultSet res = stmnt.executeQuery();
			while(res.next()){
				addedTime = res.getString("ADDED_TIME");
			}
			res.close();
			stmnt.close();
			
			stmnt = connection
					.prepareStatement("DELETE FROM SHOPPING_CART WHERE USERNAME = ? AND ENTRY_ID = ?");
			stmnt.setString(1, username);
			stmnt.setInt(2, book.getID());	
			stmnt.execute();
			stmnt.close();
			
			stmnt = connection
					.prepareStatement("INSERT INTO CART_HISTORY VALUES (?, ?, ?, ?)");
			stmnt.setString(1, username);
			stmnt.setInt(2, book.getID());
			stmnt.setString(3, addedTime);
			stmnt.setString(4, removedTime);
			stmnt.execute();
			stmnt.close();

		} catch (Exception e) {
			System.out.println("Caught Exception");
			e.printStackTrace();
		}
	}

	@Override
	public void buyBooks(String username, List<BookDTO> books, String saleTime) {
		try{
			for(BookDTO book:books){
				PreparedStatement stmnt = connection
						.prepareStatement("DELETE FROM SHOPPING_CART WHERE USERNAME = ? AND ENTRY_ID = ?");
				stmnt.setString(1, username);
				stmnt.setInt(2, book.getID());	
				stmnt.execute();
				stmnt.close();
				
				stmnt = connection.prepareStatement("INSERT INTO ORDERS (USERNAME, ENTRY_ID, SALE_TIME)"
						+"VALUES (?, ?, ?);");
				stmnt.setString(1, username);
				stmnt.setInt(2, book.getID());
				stmnt.setString(3, saleTime);
				stmnt.execute();
				stmnt.close();
			}
		}
		catch (Exception e) {
			System.out.println("Caught Exception");
			e.printStackTrace();
		}
	}

	@Override
	public List<BookDTO> findBooksBySeller(String seller) {
		List<BookDTO> books = null;
		try {
			PreparedStatement stmnt = connection
					.prepareStatement("SELECT * FROM ENTRIES WHERE SELLER = ?");
			stmnt.setString(1,seller);
			ResultSet res = stmnt.executeQuery();
			logger.info("The result set size is " + res.getFetchSize());
			books = setBooks(res);
			res.close();
			stmnt.close();

		} catch (Exception e) {
			System.out.println("Caught Exception");
			e.printStackTrace();
		}
		return books;
	}

	@Override
	public void updateBook(BookDTO book) {
		// TODO Auto-generated method stub
		int id = book.getID();
		String title = book.getTitle();
		String author = book.getAuthors();
		String editor = book.getEditors();
		String type = book.getType();
		String bookTitle = book.getBookTitle();
		String page = book.getPages();
		int year = book.getYear();
		String publisher = book.getPublisher();
		String journal = book.getJournal();
		String volume = book.getVolume();
		String number = book.getNumber();
		String month = book.getMonth();
		String isbn = book.getISBN();
		String picture = book.getPicture();
		float price = book.getCost();
		boolean pause = book.getPaused();
		try {
			PreparedStatement stmnt = connection
					.prepareStatement("UPDATE ENTRIES " +  
							"SET TITLE = ?, AUTHORS = ?, EDITORS = ?, TYPES = ?, BOOKTITLE = ?, PAGES = ?, YEAR = ?, "+
							"PUBLISHER =?, JOURNAL = ?, VOLUME = ?, NUMBER = ?, MONTH = ?, ISBN = ?, PICTURE = ?, " +
							"PRICE = ? , PAUSED = ? WHERE id = ?;");
			stmnt.setString(1,title);
			stmnt.setString(2, author);
			stmnt.setString(3, editor);
			stmnt.setString(4, type);
			stmnt.setString(5, bookTitle);
			stmnt.setString(6, page);
			stmnt.setInt(7, year);
			stmnt.setString(8, publisher);
			stmnt.setString(9, journal);
			stmnt.setString(10, volume);
			stmnt.setString(11, number);
			stmnt.setString(12, month);
			stmnt.setString(13, isbn);
			stmnt.setString(14, picture);
			stmnt.setFloat(15, price);
			if (pause){
				stmnt.setString(16, "TRUE");
			}else{
				stmnt.setString(16, "FALSE");
			}
			
			stmnt.setInt(17, id);
			
			stmnt.execute();
			stmnt.close();

		} catch (Exception e) {
			System.out.println("Caught Exception");
			e.printStackTrace();
		}
		
	}

	@Override
	public BookDTO findbyID(String id) {
		// TODO Auto-generated method stub
		BookDTO book = new BookDTO();
		try {
			PreparedStatement stmnt = connection
					.prepareStatement("SELECT * FROM ENTRIES WHERE ID = ?");
			stmnt.setString(1,id);
			ResultSet res = stmnt.executeQuery();
			logger.info("The result set size is " + res.getFetchSize());
			while(res.next()){
			
			book.setID(res.getInt("ID"));
			book.setSeller(res.getString("SELLER"));
			book.setTitle(res.getString("TITLE"));
			book.setAuthors(res.getString("AUTHORS"));
			book.setEditors(res.getString("EDITORS"));
			book.setType(res.getString("TYPES"));
			book.setBookTitle(res.getString("BOOKTITLE"));
			book.setPages(res.getString("PAGES"));
			book.setYear(res.getInt("YEAR"));
			book.setPublisher(res.getString("PUBLISHER"));
			book.setJournal(res.getString("JOURNAL"));
			book.setVolume(res.getString("VOLUME"));
			book.setNumber(res.getString("NUMBER"));
			book.setMonth(res.getString("MONTH"));
			book.setPicture(res.getString("PICTURE"));
			book.setCost(res.getFloat("PRICE"));
			book.setISBN(res.getString("ISBN"));
			book.setPaused(Boolean.valueOf(res.getString("PAUSED")));
			if(book.getPicture() == null || book.getPicture().equals("")){
				book.setPicture("http://www.jordans.com/~/media/Jordans%20Redesign/No-image-found.jpg");
			}
			}
			
			res.close();
			stmnt.close();
			
		} catch (Exception e) {
			System.out.println("book findbyid Caught Exception"+e.getMessage());
			e.printStackTrace();
		}
		return book;
		
	}

	@Override
	public void deleteentry(String id) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement stmnt = connection
					.prepareStatement("DELETE FROM ENTRIES WHERE ID = ?");
			stmnt.setString(1,id);
			stmnt.executeUpdate();
			stmnt.close();
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			}
	
	
}
