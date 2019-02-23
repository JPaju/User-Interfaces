package fi.utu.rental;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AsuntoAppStore {

	private static final AsuntoAppStore store = new AsuntoAppStore();

	private final ObservableList<Asunto> asunnot;
	private final ObjectProperty<AppState> state;

	private AsuntoAppStore() {
		asunnot = FXCollections.observableArrayList();
		state = new SimpleObjectProperty<>();
		state.set(AppState.MainMenu);
	}

	public ObservableList<Asunto> getAsunnot() {
		return this.asunnot;
	}

	public void setAppState(AppState newState) {
		synchronized (this.state) {
			this.state.set(newState);
		}
	}

	public void addStateListener(ChangeListener<AppState> listener) {
		this.state.addListener(listener);
	}

	public static AsuntoAppStore getInstance() {
		return AsuntoAppStore.store;
	}

}


