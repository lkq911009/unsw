package edu.unsw.comp9321.jdbc;

import java.util.List;

public interface BookDAO {
	
	public List<BookDTO> findAll();
	
	public List<BookDTO> findBooks();
	
	public List<BookDTO> findConferences();
	
	public List<BookDTO> findJournals();
	
	public List<BookDTO> findBooksByName(String name);
	
	public List<BookDTO> findBooksByAuthor(String author);
	
	public List<BookDTO> findBooksByEditor(String editor);
	
	public List<BookDTO> findBooksByPublisher(String publisher);
	
	public List<BookDTO> findBooksByJournal(String journal);
	
	public BookDTO findBookbyDate();
	
	public void storeBook(BookDTO book);
	
	public List<BookDTO> findMatching(String title, String author, String publisher, String[] type);
	
	public List<BookDTO> getRandom();
	
	public List<BookDTO> getShoppingCart(String username);
	
	public void addToCart(String username, BookDTO book, String time);
	
	public void removeFromCart(String username, BookDTO book, String time);
	
	public void buyBooks(String username, List<BookDTO> books, String saleTime);
	
	public List<BookDTO> findBooksBySeller(String seller);
	
	public void updateBook(BookDTO book);

	public BookDTO findbyID(String id);
	
	public void deleteentry(String id);
}
