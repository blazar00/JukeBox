package model;

import java.util.ArrayList;
import model.User;

public class Registry {
	ArrayList<User> users = new ArrayList<User>();
	
	public void addUser(String account, String pass){
		users.add(new User(account, pass));
	}
	
	public boolean search(String account, String pass){
		for(int i = 0; i < users.size(); i++)
			if(users.get(i).getAccountName().equals(account))
				if(users.get(i).getPassword().equals(pass))
					return true;
		return false;
	}

}
