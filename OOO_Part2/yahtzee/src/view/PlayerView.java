package view;

import javafx.scene.Parent;
import javafx.scene.Scene;

public class PlayerView extends Scene {

	public PlayerView(Parent root) {
		super(root, 1024, 768);
		getStylesheets().add(getClass().getResource("../application/application.css").toExternalForm());
	}

	public static void Render() {

	}

}
