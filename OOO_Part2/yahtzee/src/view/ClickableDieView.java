package view;

import controller.DieController;
import controller.PlayerController;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class ClickableDieView extends DieView {

	private PlayerController playerController;
	private DieController dieController;

	public ClickableDieView(PlayerController playerController, DieController dieController) {
		this.playerController = playerController;
		this.dieController = dieController;
		this.setOnMouseClicked(new ClickHandler());
	}

	class ClickHandler implements EventHandler<MouseEvent> {
		@Override
		public void handle(MouseEvent event) {
			playerController.setDieAside(dieController);
		}
	}
}
