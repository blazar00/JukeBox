package model;

public class User{
	private String accountName;
	private String password;
	
	User(String a, String p){
		accountName = a;
		password = p;
	}
	
	public void setAccountName(String s){
		accountName = s;
	}
	
	public void setPassword(String p){
		password = p;
	}
	
	public String getAccountName(){
		return accountName;
	}
	
	public String getPassword(){
		return password;
	}
}
