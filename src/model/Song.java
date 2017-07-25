package model;

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
	private int timesSelected; // for later
	
	//Song constructor
	public Song(String n, String p, String a, int t){
		name = n;
		path = p;
		artist = a;
		time = t;
		timesSelected = 0;
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

}
