package model;

import java.util.ArrayList;

public class SongList{
	private ArrayList<Song> allSongs;
	
	public SongList(){
		allSongs = new ArrayList<Song>();
		addDefaultSongs();
	}
	
	private void addDefaultSongs(){
		allSongs.add(new Song("LopingSting", "songfiles/LopingSting.mp3", "Kevin MacLeod", 5));
		allSongs.add(new Song("Pokemon Capture", "songfiles/Capture.mp3", "Pikachu", 5));
		allSongs.add(new Song("Danse Macabre", "songfiles/DanseMacabreViolinHook.mp3", "Kevin MacLeod", 34));
		allSongs.add(new Song("Determined Tumbao", "songfiles/DeterminedTumbao.mp3", "FreePlay Music", 20));
		allSongs.add(new Song("Swing Cheese", "songfiles/SwingCheese.mp3", "FreePlay Music", 15));
		allSongs.add(new Song("The Curtain Rises", "songfiles/TheCurtainRises.mp3", "Kevin MacLeod", 28));
		allSongs.add(new Song("Untameable Fire", "songfiles/UntameableFire.mp3", "Pierre Langer", 282));
		
	}
	
	public int size() {
		return allSongs.size();
	}
	
	public void add(Song newSong) {
		allSongs.add(newSong);
	}
	
	public Song get(int index){
		return allSongs.get(index);
	}
	
}