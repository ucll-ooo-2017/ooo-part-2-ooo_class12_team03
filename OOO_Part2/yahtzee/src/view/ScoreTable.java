package view;

import controller.PlayerController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import model.Categories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

public class ScoreTable extends GridPane implements Observer {

	private PlayerController playerController;

	private HashMap<String, TextField> textFields;

	public ScoreTable(PlayerController playerController) {
		this.playerController = playerController;

		this.setHgap(10);
		this.setVgap(4);
		this.setAlignment(Pos.CENTER_RIGHT);
		this.setPadding(new Insets(0, 0, 0, 128));

		textFields = new HashMap<>();
		addRow(0, new Text("Category"), new Text("Game 1"));
		int row = 1;
		for (String category : Categories.getNames()) {
			addRow(row, new Text(category + ":"), createTextField(category, false));
			++row;

			if (category.equals(Categories.SIXES.getName())) {
				addRow(row, new Text("Total score:"), createTextField("TOTAL_SCORE", true));
				addRow(row + 1, new Text("Bonus:"), createTextField("BONUS", false));
				addRow(row + 2, new Text("Total:"), createTextField("TOTAL", true));
				row += 3;
			}
			if (category.equals(Categories.CHANCE.getName())) {
				addRow(row, new Text("Lower section total:"), createTextField("LOWER_SECTION_TOTAL", true));
				addRow(row + 1, new Text("Upper section total:"), createTextField("UPPER_SECTION_TOTAL", true));
				addRow(row + 2, new Text("Grand total:"), createTextField("GRAND_TOTAL", true));
				row += 3;
			}
		}
	}

	private TextField createTextField(String category, boolean bold) {
		TextField textField = new TextField();
		textField.setPrefWidth(32);
		textField.setEditable(false);
		textField.setStyle("-fx-border-width: " + (bold ? 2 : 1) + "; -fx-border-color: black;");
		textFields.put(category, textField);
		return textField;
	}

	@Override
	public void update(Observable controller, Object o) {
		// The only hashmap that would be sent to this is the rolls map
		if (o instanceof HashMap) {
			HashMap<String, Integer> rolls = (HashMap<String, Integer>) o;
			rolls.forEach((category, points) -> {
				textFields.get(category).setText(points.toString());
			});
		}
	}
}
