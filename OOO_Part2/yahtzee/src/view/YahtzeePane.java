package view;

import controller.PlayerController;
import controller.YahtzeeController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Player;

import java.util.Observable;
import java.util.Observer;

public class YahtzeePane extends BorderPane implements Observer {

	private static int WINDOW_WIDTH = 1024;
	private static int WINDOW_HEIGHT = 768;

	private PlayerController playerController;

	private DicePane dicePane;
	private OtherPlayerDicePane otherPlayerDicePane;

	private Stage stage;

	private Text playing;

	public YahtzeePane(PlayerController playerController) {
		this.playerController = playerController;

		this.setPrefSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		this.setStyle("-fx-background-color: #e9967a;");

		HBox top = createHBox(Pos.CENTER, "#fff");
		this.setTop(top);

		HBox bottom = createHBox(Pos.CENTER, "#fff");
		this.setBottom(bottom);

		HBox center = createHBox(Pos.CENTER_LEFT, "#e9967a");
		this.setCenter(center);
		dicePane = new DicePane(playerController);
		otherPlayerDicePane = playerController.getOtherPlayerDicePane();
		center.getChildren().add(dicePane);
		center.getChildren().add(otherPlayerDicePane);

		Font font = new Font(22);

		Text yahtzee = new Text("Yahtzee");
		yahtzee.setFont(font);
		top.getChildren().add(yahtzee);

		playing = new Text("undefined playing");
		playing.setFont(font);
		bottom.getChildren().add(playing);

		stage = new Stage();
		stage.setTitle("Yahtzee: " + playerController.getModel().getName());
		stage.setScene(new Scene(this));
	}

	private HBox createHBox(Pos alignment, String bgColor) {
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(16));
		hbox.setStyle("-fx-background-color: " + bgColor + ";");
		hbox.setAlignment(alignment);
		return hbox;
	}

	public void show() {
		stage.show();
	}

	@Override
	public void update(Observable controller, Object o) {
		if (o instanceof Player) {
			Player player = (Player) o;
			playing.setText(player.getName() + " playing");

			boolean active = YahtzeeController.getInstance().getActivePlayer() == playerController;
			dicePane.setVisible(active);
			dicePane.setManaged(active);
			otherPlayerDicePane.setVisible(!active);
			otherPlayerDicePane.setManaged(!active);
		}
	}

	public DicePane getDicePane() {
		return dicePane;
	}
}
