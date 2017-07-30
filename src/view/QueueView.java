package view;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import model.Playlist;
import model.Song;

/**
 * A ListView that shows a list of all of the songs
 * that are queued up to be played.
 * 
 * @author Braxton Lazar, Allen Kim
 * 
 */

public class QueueView extends ListView<Song> {
	
	private ObservableList <Song> queue;
	private ArrayList<Song> playlist;
	
	public QueueView (Playlist p){
		playlist = p.getQueue();
		queue = FXCollections.observableArrayList(); 
		for(int i = 0; i<playlist.size();i++){
			queue.add(playlist.get(i));
			System.out.println("adding");
		}
		this.setItems(queue);
	}
	
	public ArrayList<Song> getList(){
		return playlist;
	}

}