package edu.unsw.comp9321.jdbc;

public class AdminDTO {
	private String adminname, password;

	public AdminDTO(String adminname) {
		this.adminname = adminname;
	}

	public String getAdminname() {
		return adminname;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
}
