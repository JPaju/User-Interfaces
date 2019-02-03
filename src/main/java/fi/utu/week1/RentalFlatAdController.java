package fi.utu.week1;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import static fi.utu.week1.Formatters.*;

public class RentalFlatAdController {

	@FXML private TextField phoneNumberField;
	@FXML private TextField emailField;

	@FXML private TextField postalCodeField;

	@FXML private TextField builtYearField;
	@FXML private TextField rentAmountField;
	@FXML private TextField flatSizeField;

	public void initialize() {
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
