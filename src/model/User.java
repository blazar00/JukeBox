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
	
	User(String a, String p) {
		accountName = a;
		password = p;
		songsPlayed = 0;
	}
	
	public void setAccountName(String s) {
		accountName = s;
	}
	
	public void setPassword(String p) {
		password = p;
	}
	
	public String getAccountName() {
		return accountName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public int getSongsPlayed() {
		return songsPlayed;
	}
	
	public void playedSong() {
		songsPlayed++;
	}
}
