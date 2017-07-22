package controller_view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Playlist;

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

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		playlist = new Playlist();
		BorderPane all = new BorderPane();
		Scene scene = new Scene(all, 400, 300);
		grid= new GridPane();
		BorderPane.setMargin(grid, new Insets(10,10,10,10));
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
		grid.add(songSelectOne, 0, 0);
		grid.add(songSelectTwo, 1, 0);
		grid.add(accountLabel, 0, 1);
		grid.add(passLabel, 0,2);
		grid.add(accountName, 1, 1);
		grid.add(password, 1, 2);
		grid.add(loginButton, 1, 3);
		grid.add(loginStatus, 1, 4);
		grid.add(logoutButton, 1, 5);
		GridPane.setHalignment(accountLabel, HPos.RIGHT);
		GridPane.setHalignment(passLabel, HPos.RIGHT);
		accountName.setMaxWidth(140);
		password.setMaxWidth(140);
		grid.setHgap(10);
		grid.setVgap(10);
		all.setBottom(grid);
		primaryStage.setScene(scene);

		// Don't forget to show the running application:
		primaryStage.show();
	}

	private class ButtonListener implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent arg0) {
			// TODO Auto-generated method stub
			Button buttonClicked = (Button) arg0.getSource();
			if(loginButton==buttonClicked) {
				String acc = accountName.getText();
				String pass = password.getText();
				//authenticate
				/*if(verified)
				 * 
				 *else{
				 *loginStatus.setText("Log in failed");
				 *}
				 */
			}
			if(logoutButton==buttonClicked) {
				//remove user
			}
			if(songSelectOne==buttonClicked) {
				//check if logged in else give an error
				//send to song object to playlist?
				playlist.play();
			}
			if(songSelectTwo==buttonClicked) {
			}
		}
	}
}
