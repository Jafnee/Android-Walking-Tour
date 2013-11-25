package uk.ac.aber.cs22120.fuzzyNinja.pathFinder.Model;

public class Account {

	private String fullName;
	private String username;
	private String password;
	
	
	
	public Account(){}
	
	public Account(String username, String password, String fullName){
		
		this.fullName = fullName;
		this.username = username;
		this.password = password;
	}
		
	public Account(String username, String password){
		
		this.fullName = "False";
		this.username = username;
		this.password = password;
	}
	
}
