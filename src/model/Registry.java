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
	ArrayList<User> users = new ArrayList<User>();
	
	public void addUser(String account, String pass){
		users.add(new User(account, pass));
	}
	
	public User search(String account, String pass){
		for(int i = 0; i < users.size(); i++)
			if(users.get(i).getAccountName().equals(account))
				if(users.get(i).getPassword().equals(pass))
					return users.get(i);
		return null;
	}

}
