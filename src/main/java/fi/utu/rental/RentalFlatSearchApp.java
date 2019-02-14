package fi.utu.rental;

import fi.utu.rental.fxmlcontrollers.RentalFlatSearchController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RentalFlatSearchApp extends Application {


	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Rental_search_form.fxml"));
		Parent root = loader.load();
		RentalFlatSearchController controller = loader.getController();
		Scene scene = new Scene(root);

		stage.setTitle("Rental Flat Search");
		stage.setScene(scene);
		stage.setOnHidden(e -> controller.onClose());

		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
