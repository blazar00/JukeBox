package view;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Playlist;
import model.Song;

public class QueueView extends TableView<Song> {
	
	private ObservableList <Song> queue;
	private ArrayList<Song> playlist;
	
	public QueueView (Playlist p){
		TableColumn <Song, String> name = new TableColumn<>("Title");
		TableColumn <Song, String> artist = new TableColumn<>("Artist");
		TableColumn <Song, Integer> time = new TableColumn<>("Time");
		TableColumn <Song, Integer> count = new TableColumn<>("Play Count");
		
		this.getColumns().addAll(count,name,artist,time);
		count.setCellValueFactory(new PropertyValueFactory<Song,Integer>("timesPlayed"));
		name.setCellValueFactory(new PropertyValueFactory<Song,String>("name"));
		artist.setCellValueFactory(new PropertyValueFactory<Song,String>("artist"));
		time.setCellValueFactory(new PropertyValueFactory<Song,Integer>("time"));
		
		playlist = p.queue;
		queue = FXCollections.observableArrayList(); 
		for(int i = 0; i<playlist.size();i++){
			queue.add(playlist.get(i));
		}
		
		this.setItems(queue);
	}

}