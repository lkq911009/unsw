package edu.unsw.comp9321.jdbc;

import java.util.List;

public class UserDTO {
	private String username, password, email, nickname, fName, lName, DOB, stNo, street, suburb,postcode, state, type;
	private String creditCard;
	private boolean banned;
	private boolean activated;
	
	public UserDTO(String username) {
		this.username = username;
		this.banned = false;
		this.type = "customer";
	}
	public String getUsername(){
		return username;
	}
	public void setPassword(String pw){
		password = pw;
	}
	public String getPassword(){
		return password;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public String getNickname() {
		return nickname;
	}
	
	public void setfName(String fName) {
		this.fName = fName;
	}
	
	public String getfName() {
		return fName;
	}
	
	public void setlName(String lName) {
		this.lName = lName;
	}
	
	public String getlName() {
		return lName;
	}
	
	public void setDOB(String dOB) {
		DOB = dOB;
	}
	
	public String getDOB() {
		return DOB;
	}
	
	public void setStNo(String stNo) {
		this.stNo = stNo;
	}
	
	public String getStNo() {
		return stNo;
	}
	
	public void setStreet(String street) {
		this.street = street;
	}
	
	public String getStreet() {
		return street;
	}
	
	public void setSuburb(String suburb) {
		this.suburb = suburb;
	}
	
	public String getSuburb() {
		return suburb;
	}
	
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	
	public String getPostcode() {
		return postcode;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	public String getState() {
		return state;
	}

	public void setCreditCard(String creditCard) {
		this.creditCard = creditCard;
	}
	
	public String getCreditCard() {
		return creditCard;
	}
	
	public void setBanned(boolean banned) {
		this.banned = banned;
	}
	
	public boolean getBanned(){
		return this.banned;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
	public void setActivated(boolean activated){
		this.activated = activated;
	}
	public boolean getActivated(){
		return activated;
	}
}
