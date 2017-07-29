package model;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import model.Song;

/**
 *
 * This class represents a song queue for the Jukebox. Songs are played in FIFO,
 * first in first out, order.
 * 
 * @author Braxton Lazar, Allen Kim
 * 
 */

public class Playlist extends Thread {
	private ArrayList<Song> playlist = new ArrayList<Song>();
	public ArrayList<Song> queue = new ArrayList<Song>();
	private int songplaycount = 0;
	private MediaPlayer mediaPlayer = null;
	private Song playingSong = null;
	
	public Playlist(){
		super();
		this.setDaemon(true);
		this.start();
		addSong(new Song("LopingSting", "songfiles/LopingSting.mp3", "Kevin MacLeod", 5));
		addSong(new Song("Pokemon Capture", "songfiles/Capture.mp3", "Pikachu", 5));
		addSong(new Song("Danse Macabre", "songfiles/DanseMacabreViolinHook.mp3", "Kevin MacLeod", 34));
		addSong(new Song("Determined Tumbao", "songfiles/DeterminedTumbao.mp3", "FreePlay Music", 20));
		addSong(new Song("Swing Cheese", "songfiles/SwingCheese.mp3", "FreePlay Music", 15));
		addSong(new Song("Untameable Fire", "songfiles/UntameableFire.mp3", "Pierre Langer", 282));
		addSong(new Song("The Curtain Rises", "songfiles/TheCurtainRises.mp3", "Kevin MacLeod", 28));
	}

	// Add a song to the playlist
	public void addSong(Song song) {
		playlist.add(song);
	}

	// Get a song from the playlist according to index position returns song
	// object
	public Song getSong(int index) {
		return playlist.get(index);
	}

	// Add a song to the song queue
	public void addToQueue(String song) {
		Song s = find(song);
		if (s == null)
			return;
		queue.add(s);
		play();
	}

	// Play the songs in the queue in first in first out order

	public void play() {
		if (queue.isEmpty() == false && playingSong == null) {
				Song s = queue.get(0);
				playingSong = s;
				File file = new File(s.getPath());
				URI uri = file.toURI();
				//System.out.println(uri);
				Media media = new Media(uri.toString());
				mediaPlayer = new MediaPlayer(media);
				mediaPlayer.setAutoPlay(true);
				mediaPlayer.play();
				try {
					Playlist.sleep(s.getTime());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				mediaPlayer.setOnEndOfMedia(new EndOfSongHandler());
		}
	}

	// Find a song in the playlist returns that song object
	public Song find(String songName) {
		for (int i = 0; i < playlist.size(); i++) {
			if (playlist.get(i).getName().equals(songName))
				return playlist.get(i);
		}
		return null;
	}

	// Used to signal the end of the song in order to play one at a time and
	// count plays
	private class EndOfSongHandler implements Runnable {
		@Override
		public void run() {
			songplaycount++;
			queue.remove(0);
			playingSong = null;
			System.out.println("Song ended, play song #" + songplaycount);
			play();
		}

	}
	
}