package fi.utu.rental;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainApp extends Application {

	public static Stage stage;
	public static ObservableList<Asunto> asunnot = FXCollections.observableArrayList();

	@Override
	public void start(Stage stage) throws Exception {
		MainApp.stage = stage;
		Platform.setImplicitExit(false);

		Parent root = FXMLLoader.load(getClass().getResource("Main_app.fxml"));
		Scene scene = new Scene(root);

		stage.setTitle("Asunto-app Pro");
		stage.setScene(scene);
		stage.setOnCloseRequest(e -> Platform.exit());

		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
