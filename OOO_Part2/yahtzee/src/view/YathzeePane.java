package view;

import controller.Controller;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.geometry.VPos;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.util.Observable;
import java.util.Observer;

public class YathzeePane extends FlowPane implements Observer {

	Controller controller;

	public YathzeePane(Controller controller) {
		this.controller = controller;

		this.setPrefSize(1024, 768);
		this.setAlignment(Pos.BASELINE_CENTER);

		Text yathzee = new Text("Yathzee");
		yathzee.setTextAlignment(TextAlignment.CENTER);

		this.getChildren().add(yathzee);
	}

	@Override
	public void update(Observable controller, Object o) {
		//
	}
}
