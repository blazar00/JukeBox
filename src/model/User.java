package model;

public class User{
	private String accountName;
	private String password;
	
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
