package edu.unsw.comp9321.jdbc;

public class OrderDTO {
	private String customer;
	private String seller;
	private String title;
	private float price;
	private String saleTime;
	private int titleID;
	
	public OrderDTO(){
		
	}
	public void SetCustomer(String customer){
		this.customer = customer;
	}
	public String GetCustomer(){
		return customer;
	}
	public void SetSeller(String seller){
		this.seller = seller;
	}
	public String GetSeller(){
		return seller;
	}
	public void SetTitle(String title){
		this.title = title;
	}
	public String GetTitle(){
		return title;
	}
	public void SetPrice(float price){
		this.price = price;
	}
	public float GetPrice(){
		return price;
	}
	public void SetSaleTime(String saleTime){
		this.saleTime = saleTime;
	}
	public String GetSaleTime(){
		return saleTime;
	}
	public void SetTitleID(int id){
		this.titleID = id;
	}
	public int GetTitleID(){
		return titleID;
	}
}
