package model;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import model.Song;
public class Playlist {
	private ArrayList<Song> playlist;
	private int songplaycount=0;
	
	private void addSong(Song song) {
		playlist.add(song);
	}

	private Song getSong(int index) {
		return playlist.get(index);
	}

	public void play() {
		String path = "songfiles/LopingSting.mp3";    

		File file = new File(path);
		URI uri = file.toURI();
		System.out.println(uri);
		Media media = new Media(uri.toString());
		MediaPlayer mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setAutoPlay(true);
		mediaPlayer.play();

		mediaPlayer.setOnEndOfMedia(new EndOfSongHandler());
	}

	private class EndOfSongHandler implements Runnable {
		@Override
		public void run() {
			songplaycount++;
			System.out.println("Song ended, play song #" + songplaycount);

		}

	}

}