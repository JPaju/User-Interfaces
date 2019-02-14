package fi.utu.rental.fxmlcontrollers;

import fi.utu.rental.MainApp;
import fi.utu.rental.RentalFlatAdApp;
import fi.utu.rental.RentalFlatSearchApp;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class MainAppController {

	@FXML private Button rentalAdButton;
	@FXML private Button rentalSearchButton;

	public void initialize() {
		MainApp.stage.setResizable(false);
		rentalAdButton.setOnAction(e -> startChildApp(new RentalFlatAdApp()));
		rentalSearchButton.setOnAction(e -> startChildApp(new RentalFlatSearchApp()));
	}

	private void startChildApp(Application app) {
		try {
			app.start(new Stage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		MainApp.stage.hide();
	}
}
