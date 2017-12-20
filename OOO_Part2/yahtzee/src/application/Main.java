package application;

import controller.YahtzeeController;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		YahtzeeController controller = YahtzeeController.getInstance();
		controller.run();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
