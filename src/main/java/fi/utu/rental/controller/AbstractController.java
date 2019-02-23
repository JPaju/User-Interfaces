package fi.utu.rental.controller;

import fi.utu.rental.AppState;
import fi.utu.rental.AsuntoAppStore;
import javafx.stage.Stage;

public abstract class AbstractController {

	protected Stage stage;
	protected AsuntoAppStore store = AsuntoAppStore.getInstance();

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	protected abstract void handleStateChange(AppState newState);

}
