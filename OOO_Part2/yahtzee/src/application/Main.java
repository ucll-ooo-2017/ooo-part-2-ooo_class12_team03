package application;

import controller.PlayerController;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import model.Player;
import view.PlayerView;

import javax.swing.*;
import java.util.ArrayList;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			ArrayList<PlayerController> players = new ArrayList<>();
			int playerCount = Integer.parseInt(JOptionPane.showInputDialog("How many players?"));
			for(int i = 0; i < playerCount; i++) {
				String name = JOptionPane.showInputDialog("What's your name?");
				PlayerController playerController = new PlayerController(
					new Player(name),
					new PlayerView(new BorderPane())
				);
				players.add(playerController);
			}

			primaryStage.setScene(players.get(0).getView());
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
