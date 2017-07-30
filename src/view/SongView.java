package view;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Playlist;
import model.Song;

/**
 * A TableView that shows a list of all of the songs
 * on the jukebox. Allows for user to select a song
 * in order to be played
 * 
 * @author Braxton Lazar, Allen Kim
 * 
 */

public class SongView extends TableView<Song> {
	
	private ObservableList <Song> songs;
	private ArrayList<Song> songlist;
	
	public SongView (Playlist p){
		TableColumn <Song, String> name = new TableColumn<>("Title");
		TableColumn <Song, String> artist = new TableColumn<>("Artist");
		TableColumn <Song, Integer> time = new TableColumn<>("Time");
		TableColumn <Song, Integer> count = new TableColumn<>("Plays");
		
		this.getColumns().addAll(count,name,artist,time);
		count.setCellValueFactory(new PropertyValueFactory<Song,Integer>("timesPlayed"));
		name.setCellValueFactory(new PropertyValueFactory<Song,String>("name"));
		artist.setCellValueFactory(new PropertyValueFactory<Song,String>("artist"));
		time.setCellValueFactory(new PropertyValueFactory<Song,Integer>("time"));
		songlist = p.getPlaylist();
		songs = FXCollections.observableArrayList(); 
		for(int i = 0; i<songlist.size();i++){
			songs.add(songlist.get(i));
		}
		this.setItems(null);
		this.setItems(songs);
	}
	
	public ArrayList<Song> getList(){
		return songlist;
	}

}