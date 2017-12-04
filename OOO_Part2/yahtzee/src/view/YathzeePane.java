package view;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.util.Observable;
import java.util.Observer;

public class YathzeePane extends BorderPane implements Observer {

	Controller controller;

	Text playing;

	public YathzeePane(Controller controller) {
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

		Text yathzee = new Text("Yathzee");
		yathzee.setFont(new Font(22));
		top.getChildren().add(yathzee);

		playing = new Text("undefined playing");
		playing.setFont(new Font(22));
		bottom.getChildren().add(playing);
	}

	@Override
	public void update(Observable controller, Object o) {
		//
	}
}
