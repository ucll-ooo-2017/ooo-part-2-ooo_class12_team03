package application;

import controller.Controller;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import view.YahtzeePane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		/*
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
		*/
		/*
		YahtzeePane pane = new YahtzeePane(new Controller());
		//InputPane pane = new InputPane(new Controller());
		Scene scene = new Scene(pane);
		primaryStage.setScene(scene);
		primaryStage.show();
		*/

		Controller controller = new Controller();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
