package fi.utu.rental;

import fi.utu.rental.controller.AbstractController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class MainApp extends Application {

	private final AsuntoAppStore store = AsuntoAppStore.getInstance();

	@Override
	public void start(Stage mainStage) throws Exception {
		Stage addStage = new Stage();
		Stage searchStage = new Stage();

		Platform.setImplicitExit(false);

		FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("Main_app.fxml"));
		FXMLLoader addLoader= new FXMLLoader(getClass().getResource("Rental_ad_form.fxml"));
		FXMLLoader searchLoader = new FXMLLoader(getClass().getResource("Rental_search_form.fxml"));

		initStage(mainStage, mainLoader, "Asunto-App Pro v2.0", false, AppState.Exit);
		initStage(addStage, addLoader, "Add Rental Flat", true, AppState.MainMenu);
		initStage(searchStage, searchLoader, "Rental Flat Search", true, AppState.MainMenu);

		mainStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	private void initStage(Stage stage, FXMLLoader loader, String title, boolean resizeable, AppState onCloseState) {
		try {
			stage.setScene(new Scene(loader.load()));
		} catch (IOException e) {
			e.printStackTrace();
		}

		stage.setTitle(title);
		stage.setResizable(resizeable);
		stage.setOnCloseRequest(e -> store.setAppState(onCloseState));

		AbstractController controller = loader.getController();
		controller.setStage(stage);
	}
}
