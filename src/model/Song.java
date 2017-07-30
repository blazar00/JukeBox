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
	private String timeStr;
	private LocalDate date = null;
	private LocalDate lastPlayed = null;
	private int timesPlayed;
	
	
	//Song constructor
	public Song(String n, String p, String a, int t, String tStr){
		this.name = n;
		this.path = p;
		this.artist = a;
		this.time = t;
		this.timeStr = tStr;
		this.timesPlayed = 0;
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
	
	//Get time of song in formatted form
	public String getTimeStr(){
		return timeStr;
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
	
	public String toString(){
		return name+"- "+artist;
	}

}
