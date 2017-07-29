package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Song;
import model.SongList;

public class SongView extends TableView {
	
	private ObservableList <Song> songs;
	private SongList songlist;
	
	public SongView (){
		TableColumn <Song, String> name = new TableColumn<>("Title");
		TableColumn <Song, String> artist = new TableColumn<>("Artist");
		TableColumn <Song, Integer> time = new TableColumn<>("Time");
		TableColumn <Song, Integer> timesPlayed = new TableColumn<>("Play Count");
		
		this.getColumns().addAll(timesPlayed,name,artist,time);
		timesPlayed.setCellValueFactory(new PropertyValueFactory<Song,Integer>("timesPlayed"));
		name.setCellValueFactory(new PropertyValueFactory<Song,String>("name"));
		artist.setCellValueFactory(new PropertyValueFactory<Song,String>("artist"));
		time.setCellValueFactory(new PropertyValueFactory<Song,Integer>("time"));
		
		songlist = new SongList();
		songs = FXCollections.observableArrayList(); 
		for(int i = 0; i<songlist.size();i++){
			songs.add(songlist.get(i));
		}
		
		this.setItems(songs);
	}

}