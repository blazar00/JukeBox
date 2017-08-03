package model;

import java.util.ArrayList;
import model.User;

/**
 * 
 * Class represents a list of all users and allows for the search
 * of the users.
 * 
 * @author Braxton Lazar, Allen Kim
 * 
 */

public class Registry {
	private ArrayList<User> users;
	private User admin;
	public Registry() {
		users = new ArrayList<User>();
		addAdmin();
		addDefaultUsers();
	}
	
	private void addAdmin(){
		addUser("Alex","1234");
		admin = search("Alex","1234");
	}
	
	private void addDefaultUsers(){
		addUser("Chris", "1");
		addUser("Devon", "22");
		addUser("River", "333");
		addUser("Ryan", "4444");
	}
	
	public boolean isAdmin(String account, String pass){
		if(search(account, pass)==admin){
			return true;
		}
		else
			return false;
	}
	
	public boolean removeUser(String account){
		for(int i = 0; i < users.size(); i++){
			if(users.get(i).getAccountName().equals(account)){
					users.remove(i);
					return true;
			}
		}
		return false;
	}
	
	//Add a user to the registry
	public void addUser(String account, String pass){
		users.add(new User(account, pass));
	}
	
	//Used to verify and check that user and pass are correct returns User object
	public User search(String account, String pass){
		for(int i = 0; i < users.size(); i++)
			if(users.get(i).getAccountName().equals(account))
				if(users.get(i).getPassword().equals(pass))
					return users.get(i);
		return null;
	}

}