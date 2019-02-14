package fi.utu.rental;

import fi.utu.rental.fxmlcontrollers.RentalFlatAdController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class RentalFlatAdApp extends Application {

	private FileChooser imageChooser = new FileChooser();
	private RentalFlatAdController controller;

	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Rental_ad_form.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root);

		controller = loader.getController();
		controller.setStage(stage);

		stage.setTitle("Flat for rent form");
		stage.setScene(scene);
		stage.setOnHidden(e -> controller.onClose());
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
