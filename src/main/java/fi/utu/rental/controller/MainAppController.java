package fi.utu.rental.controller;

import fi.utu.rental.AppState;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;


public class MainAppController extends AbstractController implements Initializable {

	@FXML private Button rentalAdButton;
	@FXML private Button rentalSearchButton;

	@Override
	protected void handleStateChange(AppState newState) {
		if (newState == AppState.MainMenu) {
			stage.show();
		} else if (newState == AppState.Exit) {
			Platform.exit();
		} else {
			stage.hide();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		rentalAdButton.setOnAction(e -> store.setAppState(AppState.AddingProperty));
		rentalSearchButton.setOnAction(e -> store.setAppState(AppState.SearchingProperty));

		store.addStateListener((arg, oldValue, newValue) -> handleStateChange(newValue));
	}
}
