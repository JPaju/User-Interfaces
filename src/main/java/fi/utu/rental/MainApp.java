package fi.utu.rental;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainApp extends Application {

	public static Stage stage;

	@Override
	public void start(Stage stage) throws Exception {
		Platform.setImplicitExit(false);

		MainApp.stage = stage;

		Parent root = FXMLLoader.load(getClass().getResource("Main_app.fxml"));
		Scene scene = new Scene(root);
		//scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

		stage.setTitle("Asunto-app Pro");
		stage.setScene(scene);
		stage.setOnCloseRequest(e -> Platform.exit());

		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
