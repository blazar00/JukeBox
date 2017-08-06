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
	
	// Constructor
	public Registry() {
		users = new ArrayList<User>();
		addAdmin();
		addDefaultUsers();
	}
	
	// adds default admin
	private void addAdmin(){
		addUser("Alex","12345");
		admin = search("Alex","12345");
	}
	
	// sets all the default accounts
	private void addDefaultUsers(){
		addUser("Chris", "1");
		addUser("Devon", "22");
		addUser("River", "333");
		addUser("Ryan", "4444");
	}
	
	// checks if the user passed in is an Admin
	public boolean isAdmin(String account, String pass){
		if(search(account, pass).equals(admin)){
			return true;
		}
		else
			return false;
	}
	
	// remove a user from the list
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
	
	// Get the current list of users
	public ArrayList<User> getUserList(){
		return users;
	}
	
	// Set a new list of users
	public void setUserList(ArrayList<User> u){
		users = u;
		admin = search("Alex","12345");
	}

}