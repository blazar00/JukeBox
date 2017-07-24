package model;

public class Song {
	private String name;
	private String path;
	private String artist;
	private int time;
	
	public Song(String n, String p, String a, int t){
		name = n;
		path = p;
		artist = a;
		time = t;
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
