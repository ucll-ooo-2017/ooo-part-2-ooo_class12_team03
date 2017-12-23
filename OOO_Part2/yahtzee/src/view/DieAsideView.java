package view;

import controller.DieAsideController;
import controller.DieController;
import controller.PlayerController;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class DieAsideView extends DieView {

	private PlayerController playerController;
	private DieAsideController dieController;

	public DieAsideView(PlayerController playerController, DieAsideController dieController) {
		this.playerController = playerController;
		this.dieController = dieController;
		this.setOnMouseClicked(new ClickHandler());
	}

	class ClickHandler implements EventHandler<MouseEvent> {
		@Override
		public void handle(MouseEvent event) {
			playerController.unsetDieAside(dieController);
		}
	}
}
