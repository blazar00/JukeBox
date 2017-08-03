package controller_view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Playlist;
import model.Registry;
import model.Song;
import model.User;
import view.SongView;

/**
 * A JavaFX GUI for JukeBox that can play songs.
 * This file also allows for a user to log on through
 * a user name and password. After logging in the user
 * can then choose songs to play.
 * 
 * @author Braxton Lazar, Allen Kim
 * 
 */

public class Iteration2Controller extends Application {

	private TextField accountName;
	private PasswordField password;
	private Button loginButton;
	private Button logoutButton;
	private Label loginStatus;
	private GridPane grid;
	private GridPane adminfield;
	private Playlist playlist;
	private Registry reg;
	private Button play;
	private Button adduser;
	private Button removeuser;
	private Label adminStatus;
	private SongView songview;
	VBox vboxright = new VBox();
	VBox vboxcenter;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		playlist = new Playlist(vboxright);
		reg = new Registry();
		BorderPane all = new BorderPane();
		Scene scene = new Scene(all, 1000, 450);
		grid = new GridPane();
		primaryStage.setTitle("JukeBox");
		BorderPane.setMargin(grid, new Insets(10, 10, 10, 10));
		loginButton = new Button("Login");
		logoutButton = new Button("Log Out");
		ButtonListener handler = new ButtonListener();
		loginButton.setOnAction(handler);
		logoutButton.setOnAction(handler);
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
		GridPane.setHalignment(accountLabel, HPos.RIGHT);
		GridPane.setHalignment(passLabel, HPos.RIGHT);
		adminfield= new GridPane();
		adduser= new Button("Add User");
		removeuser= new Button("Remove User");
		adminStatus= new Label("Welcome Admin");
		adminfield.add(adduser, 0,0);
		adminfield.add(removeuser, 1,0);
		adminfield.add(adminStatus, 1,1);
		adduser.setOnAction(handler);
		removeuser.setOnAction(handler);
		accountName.setMaxWidth(160);
		password.setMaxWidth(160);
		grid.setHgap(10);
		grid.setVgap(10);
		songview = new SongView(playlist);
		songview.getSelectionModel().selectFirst();
		play = new Button("Play");
		play.setOnAction(handler);
		play.setMaxWidth(300);
		play.setMaxHeight(200);
		vboxcenter = new VBox();
		vboxcenter.getChildren().addAll(play,grid);
		vboxcenter.setAlignment(Pos.CENTER);
		vboxcenter.setSpacing(20);
		grid.setAlignment(Pos.CENTER);
		adminfield.setAlignment(Pos.CENTER);
		VBox vboxleft = new VBox();
		vboxleft.getChildren().addAll(new Label("Choose a Song"), songview);
		vboxright.getChildren().addAll(new Label("Songs in Queue"), playlist.queueview);
		all.setCenter(vboxcenter);
		all.setLeft(vboxleft);
		all.setRight(vboxright);
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
					loginStatus.setText("Hi, " + account.getAccountName()+" you have "+(3-account.getSongsPlayed())+" plays remaining today.");
					if(reg.isAdmin(acc, pass)){
						System.out.println("Admin logged in");
						vboxcenter.getChildren().add(adminfield);
					}
					else{
						vboxcenter.getChildren().remove(adminfield);
					}
				} else {
					loginStatus.setText("Log in failed");
					loggedin = false;
				}
			}
			else if (logoutButton == buttonClicked && loggedin) {
				loggedin = false;
				loginStatus.setText("Please come again");
				vboxcenter.getChildren().remove(adminfield);
			}
			else if (play == buttonClicked && loggedin) {
				if(account.getSongsPlayed() < 3){
					String songtitle = ((Song) songview.getSelectionModel().getSelectedItem()).getName();
					Song s = playlist.find(songtitle);
					if(s.canBePlayed()){
						playlist.addToQueue(songtitle);
						account.playedSong();
						s.played();
						loginStatus.setText("Hi, " + account.getAccountName()+" you have "+(3-account.getSongsPlayed())+" plays remaining today.");
						songview.refresh();
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
			} 
			else if (play == buttonClicked && !loggedin){
				loginStatus.setText("Please login in to play a song");
			}
			else if (adduser == buttonClicked && reg.isAdmin(account.getAccountName(),account.getPassword())){
				reg.addUser(accountName.getText(), password.getText());
				adminStatus.setText("User Added");
			}
			else if (removeuser == buttonClicked && reg.isAdmin(account.getAccountName(),account.getPassword())){
				if(!accountName.getText().equals(account.getAccountName())){
					boolean removed = reg.removeUser(accountName.getText());

					if(removed==true){
						adminStatus.setText("User Removed");
					}
					else{
						adminStatus.setText("User Not Found");
					}
				}
			}
		}
	}
}


