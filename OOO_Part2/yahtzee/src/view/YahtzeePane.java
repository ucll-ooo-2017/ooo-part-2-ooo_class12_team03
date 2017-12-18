package view;

import controller.Controller;
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

	private Controller controller;

	private Stage stage;

	private Text playing;

	public YahtzeePane(Controller controller) {
		this.controller = controller;

		this.setPrefSize(1024, 768);
		this.setStyle("-fx-background-color: #e9967a;");

		HBox top = new HBox();
		top.setPadding(new Insets(16));
		top.setStyle("-fx-background-color: #fff;");
		top.setAlignment(Pos.CENTER);
		this.setTop(top);

		HBox bottom = new HBox();
		bottom.setPadding(new Insets(16));
		bottom.setStyle("-fx-background-color: #fff;");
		bottom.setAlignment(Pos.CENTER);
		this.setBottom(bottom);

		HBox center = new HBox();
		center.setPadding(new Insets(16));
		center.setAlignment(Pos.CENTER_LEFT);
		this.setCenter(center);
		center.getChildren().add(new DicePane(controller));

		Text yahtzee = new Text("Yahtzee");
		yahtzee.setFont(new Font(22));
		top.getChildren().add(yahtzee);

		playing = new Text("undefined playing");
		playing.setFont(new Font(22));
		bottom.getChildren().add(playing);

		stage = new Stage();
		stage.setTitle("Input");
		stage.setScene(new Scene(this));
	}

	public void show() {
		stage.show();
	}

	@Override
	public void update(Observable controller, Object o) {
		if (o instanceof Player) {
			Player player = (Player) o;
			playing.setText(player.getName() + " playing");
		}
	}
}
