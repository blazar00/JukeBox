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
	
	public Song(String n, String p, String a, int t){
		name = n;
		path = p;
		artist = a;
		time = t;
		timesSelected = 0;
	}
	
	public String getName(){
		return name;
	}
	
	public String getPath(){
		return path;
	}
	
	public String getArtist(){
		return artist;
	}
	
	public int getTime(){
		return time;
	}

}
