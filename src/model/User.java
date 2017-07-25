package model;

/**
*
* This class represents a user for the JukeBox GUI. Each user
* has an account name, a password, and a count of how many songs that user
* played in a day.
* 
* @author Braxton Lazar, Allen Kim
* 
*/


public class User {
	private String accountName;
	private String password;
	int songsPlayed;
	
	//User constructor consists of account name password and count of songs played
	User(String a, String p) {
		accountName = a;
		password = p;
		songsPlayed = 0;
	}
	
	//Set user account name
	public void setAccountName(String s) {
		accountName = s;
	}
	
	//Set the password of user account
	public void setPassword(String p) {
		password = p;
	}
	
	//Get the user account name returns string
	public String getAccountName() {
		return accountName;
	}
	
	//Get the user account password returns string
	public String getPassword() {
		return password;
	}
	
	//Get the count of songs played that day returns integer
	public int getSongsPlayed() {
		return songsPlayed;
	}
	
	//If song was chosen then increase the count
	public void playedSong() {
		songsPlayed++;
	}
}