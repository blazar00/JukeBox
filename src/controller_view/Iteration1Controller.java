package controller_view;

/**
 * A JavaFX GUI for JukeBox that can play songs.
 * This file also allows for a user to log on through
 * a user name and password. After logging in the user
 * can then choose songs to play.
 * 
 * @author Braxton Lazar, Allen Kim
 * 
 */
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Playlist;
import model.Registry;
import model.Song;
import model.User;
import view.QueueView;
import view.SongView;

public class Iteration1Controller extends Application {

	private TextField accountName;
	private PasswordField password;
	private Button loginButton;
	private Button logoutButton;
	private Button songSelectOne;
	private Button songSelectTwo;
	private Label loginStatus;
	private GridPane grid;
	private Playlist playlist;
	private Registry reg;
	private Button play;
	private SongView songview;
	private QueueView queueview;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		playlist = new Playlist();
		playlist.addSong(new Song("LopingSting", "songfiles/LopingSting.mp3", "Kevin MacLeod", 5));
		playlist.addSong(new Song("Pokemon Capture", "songfiles/Capture.mp3", "Pikachu", 5));
		playlist.addSong(new Song("Danse Macabre", "songfiles/DanseMacabreViolinHook.mp3", "Kevin MacLeod", 34));
		playlist.addSong(new Song("Determined Tumbao", "songfiles/DeterminedTumbao.mp3", "FreePlay Music", 20));
		playlist.addSong(new Song("Swing Cheese", "songfiles/SwingCheese.mp3", "FreePlay Music", 15));
		playlist.addSong(new Song("Untameable Fire", "songfiles/UntameableFire.mp3", "Pierre Langer", 282));
		playlist.addSong(new Song("The Curtain Rises", "songfiles/TheCurtainRises.mp3", "Kevin MacLeod", 28));
		reg = new Registry();
		reg.addUser("Chris", "1");
		reg.addUser("Devon", "22");
		reg.addUser("River", "333");
		reg.addUser("Ryan", "4444");
		BorderPane all = new BorderPane();
		Scene scene = new Scene(all, 1000, 600);
		grid = new GridPane();
		BorderPane.setMargin(grid, new Insets(10, 10, 10, 10));
		loginButton = new Button("Login");
		logoutButton = new Button("Log Out");
		songSelectOne = new Button("Select Song One");
		songSelectTwo = new Button("Select Song Two");
		ButtonListener handler = new ButtonListener();
		loginButton.setOnAction(handler);
		logoutButton.setOnAction(handler);
		songSelectOne.setOnAction(handler);
		songSelectTwo.setOnAction(handler);
		accountName = new TextField("");
		password = new PasswordField();
		loginStatus = new Label("Please Log In");
		Label accountLabel = new Label("Account Name");
		Label passLabel = new Label("Password");

		grid.add(accountLabel, 0, 0);
		grid.add(passLabel, 0, 1);
		grid.add(accountName, 1, 0);
		grid.add(password, 1, 1);
		grid.add(loginButton, 1, 2);
		grid.add(loginStatus, 1, 3);
		grid.add(logoutButton, 1, 4);
		/*
		GridPane songSelect=new GridPane();
		songSelect.add(songSelectOne, 0, 0);
		songSelect.add(new Label("   "), 1, 0);
		songSelect.add(songSelectTwo, 2, 0);
		songSelect.setAlignment(Pos.CENTER);
		*/
		GridPane.setHalignment(accountLabel, HPos.RIGHT);
		GridPane.setHalignment(passLabel, HPos.RIGHT);
		accountName.setMaxWidth(160);
		password.setMaxWidth(160);
		grid.setHgap(10);
		grid.setVgap(10);
		songview = new SongView();
		queueview = new QueueView(playlist);
		play = new Button("Play");
		play.setOnAction(handler);
		all.setCenter(play);
		all.setLeft(songview);
		all.setRight(queueview);
		all.setBottom(grid);
		primaryStage.setScene(scene);

		// Don't forget to show the running application:
		primaryStage.show();
	}

	private class ButtonListener implements EventHandler<ActionEvent>{
		boolean loggedin = false;
		User account;

		@Override
		public void handle(ActionEvent arg0) {
			Button buttonClicked = (Button) arg0.getSource();
			if (loginButton == buttonClicked) {
				String acc = accountName.getText();
				String pass = password.getText();
				account = reg.search(acc, pass);
				// authenticate
				if ( account != null) {
					loggedin = true;
					loginStatus.setText("Hi, " + account.getAccountName());
				} else {
					loginStatus.setText("Log in failed");
					loggedin = false;
				}

			}
			else if (logoutButton == buttonClicked && loggedin) {
				loggedin = false;
				loginStatus.setText("Please come again");
			}
			else if (play == buttonClicked && loggedin) {
				if(account.getSongsPlayed() < 3){
					String songtitle = ((Song) songview.getSelectionModel().getSelectedItem()).getName();
					System.out.println(songtitle);
					Song s = playlist.find(songtitle);
					if(s.canBePlayed()){
						playlist.addToQueue(songtitle);
						account.playedSong();
						s.played();
					}
					else{
						Alert dailyplaylimit= new Alert(AlertType.WARNING);
						dailyplaylimit.setTitle("Warning");
						dailyplaylimit.setHeaderText("This song has already been played 3 times today.");
						dailyplaylimit.showAndWait();
					}
				}
				else {
					Alert dailyplaylimit= new Alert(AlertType.WARNING);
					dailyplaylimit.setTitle("Warning");
					dailyplaylimit.setHeaderText("Limit of 3 songs have been chosen today.");
					dailyplaylimit.showAndWait();
				}

			} else if (play == buttonClicked && !loggedin){
				loginStatus.setText("Please login in to play a song");
			}
			
			
			/*
			else if (songSelectOne == buttonClicked && loggedin) {
				if(account.getSongsPlayed() < 3){
					Song s = playlist.find("LopingSting");
					if(s.canBePlayed()){
						playlist.addToQueue("LopingSting");
						account.playedSong();
						s.played();
					}
					else{
						Alert dailyplaylimit= new Alert(AlertType.WARNING);
						dailyplaylimit.setTitle("Warning");
						dailyplaylimit.setHeaderText("This song has already been played 3 times today.");
						dailyplaylimit.showAndWait();
					}
				}
				else {
					Alert dailyplaylimit= new Alert(AlertType.WARNING);
					dailyplaylimit.setTitle("Warning");
					dailyplaylimit.setHeaderText("Limit of 3 songs have been chosen today.");
					dailyplaylimit.showAndWait();
				}
				

			} else if (songSelectOne == buttonClicked && !loggedin){
				loginStatus.setText("Please login in to play a song");
			}
			if (songSelectTwo == buttonClicked && loggedin) {
				if(account.getSongsPlayed() < 3){
					Song s = playlist.find("Pokemon Capture");
					if(s.canBePlayed()){
						playlist.addToQueue("Pokemon Capture");
						account.playedSong();
						s.played();
					}
					else{
						Alert dailyplaylimit= new Alert(AlertType.WARNING);
						dailyplaylimit.setTitle("Warning");
						dailyplaylimit.setHeaderText("This song has already been played 3 times today.");
						dailyplaylimit.showAndWait();
					}
				}
				else {
					Alert dailyplaylimit= new Alert(AlertType.INFORMATION);
					dailyplaylimit.setTitle("Warning");
					dailyplaylimit.setHeaderText("Limit of 3 songs have been chosen today.");
					dailyplaylimit.showAndWait();
				}
				
			} else if (songSelectTwo == buttonClicked && !loggedin){
				loginStatus.setText("Please login in to play a song");
			}
			*/
			playlist.play();
		}
	}
}


