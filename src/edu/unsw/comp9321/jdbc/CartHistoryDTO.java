package edu.unsw.comp9321.jdbc;

public class CartHistoryDTO {
	private String customer;
	private String title;
	private String addedTime;
	private String removedTime;
	private int titleID;
	
	public CartHistoryDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public void SetCustomer(String customer){
		this.customer = customer;
	}
	
	public String GetCustomer(){
		return customer;
	}
	
	public void SetTitle(String title){
		this.title = title;
	}
	
	public String GetTitle(){
		return title;
	}
	public void SetAddedTime(String addedTime){
		this.addedTime = addedTime;
	}
	public String GetAddedTime(){
		return addedTime;
		
	}
	public void SetRemovedTime(String removedTime){
		this.removedTime = removedTime;
	}
	
	public String GetRemovedTime(){
		return removedTime;
	}
	public void SetTitleID(int id){
		this.titleID = id;
	}
	public int GetTitleID(){
		return titleID;
	}
	
	
}
