package model;

import java.time.LocalDate;

/**
 *
 * This class represents a song that is played on the jukebox.
 * 
 * @author Braxton Lazar, Allen Kim
 * 
 */

public class Song {
	private String name;
	private String path;
	private String artist;
	private int time;
	private LocalDate date = null;
	private LocalDate lastPlayed = null;
	private int timesPlayed;
	
	
	//Song constructor
	public Song(String n, String p, String a, int t){
		name = n;
		path = p;
		artist = a;
		time = t;
		timesPlayed = 0;
	}
	
	//Get the song name returns string
	public String getName(){
		return name;
	}
	
	//Get the path of the song file returns string
	public String getPath(){
		return path;
	}
	
	//Get the Song's artist returns string
	public String getArtist(){
		return artist;
	}
	
	//Get the time of how long the song is in seconds returns an integer
	public int getTime(){
		return time;
	}
	
	//Get how many times song was played that day
	public int getTimesPlayed(){
		return timesPlayed;
	}
	
	public void played(){
		date = LocalDate.now();
		if(lastPlayed == null){
			lastPlayed = date;
			timesPlayed++;
		}
		else if (date.compareTo(lastPlayed) == 1){
			lastPlayed = date;
			timesPlayed = 0;
		}
		else
			timesPlayed++;
		
	}
	
	public boolean canBePlayed(){
		if(timesPlayed < 3)
			return true;
		return false;
	}

}
