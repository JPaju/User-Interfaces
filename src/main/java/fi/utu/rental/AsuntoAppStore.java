package fi.utu.rental;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AsuntoAppStore {

	private static final AsuntoAppStore store = new AsuntoAppStore();

	private final ObservableList<Asunto> asunnot;
	private AppState currentState;

	private AsuntoAppStore() {
		asunnot = FXCollections.observableArrayList();
		currentState = AppState.MainMenu;
	}

	public ObservableList<Asunto> getAsunnot() {
		return this.asunnot;
	}

	public AppState getCurrentState() {
		return currentState;
	}

	public static AsuntoAppStore getInstance() {
		return AsuntoAppStore.store;
	}


}


