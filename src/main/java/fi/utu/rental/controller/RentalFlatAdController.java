package fi.utu.rental.controller;

import fi.utu.rental.Asunto;
import fi.utu.rental.MainApp;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import static fi.utu.rental.Formatters.*;

public class RentalFlatAdController implements Initializable {

	Stage stage;
	FileChooser imageChooser;
	File imageFile;

	@FXML private TextField nameField;
	@FXML private TextField phoneNumberField;
	@FXML private TextField emailField;

	@FXML private TextField streetAddressField;
	@FXML private TextField postalCodeField;

	@FXML private TextField builtYearField;
	@FXML private TextField rentAmountField;
	@FXML private TextField flatSizeField;

	@FXML private TextField termsField;
	@FXML private TextArea descriptionArea;

	@FXML private Button browseImageButton;
	@FXML private Button removeImageButton;
	@FXML private Button returnButton;
	@FXML private Button continueButton;

	@FXML private ImageView imagePreview;

	@Override
	public void initialize(URL url, ResourceBundle resources) {
		initFormatters();
		imageChooser = new FileChooser();
		browseImageButton.setOnAction(e -> chooseImage());
		removeImageButton.setOnAction(e -> removeImage());
		returnButton.setOnAction(e -> stage.hide());
		continueButton.setOnAction(e -> submitApartment());
	}

	public void submitApartment() {
		System.out.println("Submitting!");
		Asunto apartment = new Asunto();
		apartment.setKuvaFile(imageFile.toString());
		apartment.setNimi(nameField.getText());
		apartment.setOsoite(streetAddressField.getText());
		apartment.setRakennusyear(Integer.parseInt(builtYearField.getText()));
		apartment.setNeliömäärä(Float.parseFloat(flatSizeField.getText()));
		apartment.setVuokra(Integer.parseInt(rentAmountField.getText()));
		apartment.setMuutEhdot(termsField.getText());
		apartment.setKohteenDescription(descriptionArea.getText().split("\n"));
		apartment.setSähkömail(emailField.getText());

		MainApp.asunnot.add(apartment);
		stage.close();
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public void onClose() {
		MainApp.stage.show();
	}

	private void chooseImage(){
		imageChooser.setTitle("Choose Picture");
		imageChooser.setInitialDirectory(new File(System.getProperty("user.home")));
		imageFile = imageChooser.showOpenDialog(stage);
		if (imageFile != null) {
			imagePreview.setImage(new Image(imageFile.toURI().toString()));
		}
	}

	private void removeImage() {
		imagePreview.imageProperty().set(null);
	}

	private void initFormatters() {
		phoneNumberField.setTextFormatter(phoneNumberFilter());
		emailField.textProperty().addListener((arg0, oldValue, newValue) -> {
			if (isValidEmail(emailField.getText()))
				emailField.setStyle("-fx-focus-color: green");
			else
				emailField.setStyle("-fx-focus-color: red");
		});

		postalCodeField.setTextFormatter(integerFilter(5));
		builtYearField.setTextFormatter(integerFilter(4));
		flatSizeField.setTextFormatter(decimalFilter());
		rentAmountField.setTextFormatter(decimalFilter());
	}

}
