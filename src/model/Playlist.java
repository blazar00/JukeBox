package model;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import model.Song;

public class Playlist {
	private ArrayList<Song> playlist = new ArrayList<Song>();
	public ArrayList<Song> queue = new ArrayList<Song>();
	private int songplaycount = 0;
	private MediaPlayer mediaPlayer;

	public void addSong(Song song) {
		playlist.add(song);
	}

	public Song getSong(int index) {
		return playlist.get(index);
	}

	public void addToQueue(String song) {
		Song s = find(song);
		if (s == null)
			return;
		queue.add(s);
	}

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

	public Song find(String songName) {
		for (int i = 0; i < playlist.size(); i++) {
			if (playlist.get(i).getName().equals(songName))
				return playlist.get(i);
		}
		return null;
	}

	private class EndOfSongHandler implements Runnable {
		@Override
		public void run() {
			songplaycount++;
			System.out.println("Song ended, play song #" + songplaycount);
		}

	}

}