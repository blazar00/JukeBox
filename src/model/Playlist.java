package model;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import model.Song;

/**
*
* This class represents a song queue for the Jukebox.
* Songs are played in FIFO, first in first out, order.
* 
* @author Braxton Lazar, Allen Kim
* 
*/

public class Playlist {
	private ArrayList<Song> playlist = new ArrayList<Song>();
	public ArrayList<Song> queue = new ArrayList<Song>();
	private int songplaycount = 0;
	private MediaPlayer mediaPlayer;

	//Add a song to the playlist
	public void addSong(Song song) {
		playlist.add(song);
	}

	//Get a song from the playlist according to index position returns song object
	public Song getSong(int index) {
		return playlist.get(index);
	}
	
	//Add a song to the song queue
	public void addToQueue(String song) {
		Song s = find(song);
		if (s == null)
			return;
		queue.add(s);
	}

	//Play the songs in the queue in first in first out order
	public void play() {
		while(!queue.isEmpty()){
			File file = new File(queue.get(0).getPath());
			URI uri = file.toURI();
			System.out.println(uri);
			Media media = new Media(uri.toString());
			mediaPlayer = new MediaPlayer(media);
			mediaPlayer.setAutoPlay(true);
			mediaPlayer.play();
			while(mediaPlayer.getStopTime().toSeconds() != queue.get(0).getTime()){}
			mediaPlayer.setOnEndOfMedia(new EndOfSongHandler());
			queue.remove(0);
			
		}
	}

	//Find a song in the playlist returns that song object
	public Song find(String songName) {
		for (int i = 0; i < playlist.size(); i++) {
			if (playlist.get(i).getName().equals(songName))
				return playlist.get(i);
		}
		return null;
	}

	//Used to signal the end of the song in order to play one at a time and count plays
	private class EndOfSongHandler implements Runnable {
		@Override
		public void run() {
			songplaycount++;
			System.out.println("Song ended, play song #" + songplaycount);
		}

	}

}