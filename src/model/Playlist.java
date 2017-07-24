package model;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import model.Song;


public class Playlist {
	private ArrayList<Song> playlist = new ArrayList<Song>();
	private int songplaycount = 0;
	
	public void addSong(Song song) {
		playlist.add(song);
	}

	public Song getSong(int index) {
		return playlist.get(index);
	}

	public void play(String song) {
		Song s = find(song);
		if(s == null)
			return;

		File file = new File(s.getPath());
		URI uri = file.toURI();
		System.out.println(uri);
		Media media = new Media(uri.toString());
		MediaPlayer mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setAutoPlay(true);
		mediaPlayer.play();
		
		mediaPlayer.setOnEndOfMedia(new EndOfSongHandler());
	}

	public Song find(String songName){
		for(int i = 0; i < playlist.size(); i++){
			if(playlist.get(i).getName().equals(songName))
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