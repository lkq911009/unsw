package edu.unsw.comp9321.jdbc;
 
public class BookDTO {
	
	private int ID;
	private String seller;
	private String title;
	private String authors;
	private String editors;
	private String type;
	private String bookTitle;
	private String pages;
	private int year;
	private String publisher;
	private String journal;
	private String volume;
	private String number;
	private String month;
	private String ISBN;
	private String picture;
	private float cost;
	private boolean paused;
	
	public BookDTO(){
		
	}
	
	public BookDTO(int id) {
		ID = id;
	}
	public float getCost(){
		return cost;
	}
	public void setCost(float cost){
		this.cost = cost;
	}
	
	public int getID() {
		return ID;
	}
	public void setID(int id){
		ID=id;
	}
	public String getSeller() {
		return seller;
	}
	public void setSeller(String sell) {
		seller = sell;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String t) {
		title = t;
	}
	public String getAuthors(){
		return authors;
	}
	public void setAuthors(String a){
		authors = a;
	}
	public String getEditors(){
		return editors;
	}
	public void setEditors(String e){
		editors = e;
	}
	public String getBookTitle() {
		return bookTitle;
	}
	public void setBookTitle(String bt) {
		bookTitle = bt;
	}
	public String getPages() {
		return pages;
	}
	public void setPages(String p) {
		pages = p;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int y) {
		year = y;
	}
	public String getJournal() {
		return journal;
	}
	public void setJournal(String j) {
		journal = j;
	}
	public void setVolume(String v) {
		volume = v;
	}
	public String getVolume() {
		return volume;
	}
	public void setNumber(String n) {
		number = n;
	}
	public String getNumber() {
		return number;
	}
	public void setMonth(String m) {
		month = m;
	}
	public String getMonth() {
		return month;
	}
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String isbn) {
		ISBN = isbn;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String p) {
		picture = p;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String p) {
		publisher = p;
	}
	public void setPaused(boolean p) {
		paused = p;
	}
	public boolean getPaused(){
		return paused;
	}
	public void setType(String t){
		type = t;
	}
	public String getType(){
		return type;
	}
}
