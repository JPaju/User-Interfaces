package fi.utu.rental.fxmlcontrollers;

import fi.utu.rental.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import static fi.utu.rental.Formatters.decimalFilter;
import static fi.utu.rental.Formatters.integerFilter;

public class RentalFlatSearchController {

	@FXML private TextField postalCodeField;
	@FXML private TextField minRentField;
	@FXML private TextField maxRentField;
	@FXML private TextField minBuiltYearField;
	@FXML private TextField maxBuiltYearField;
	@FXML private TextField minFlatSizeField;
	@FXML private TextField maxFlatSizeField;

	@FXML private Button returnButton;

	public void initialize() {
		postalCodeField.setTextFormatter(integerFilter(5));
		minRentField.setTextFormatter(decimalFilter());
		maxRentField.setTextFormatter(decimalFilter());
		minBuiltYearField.setTextFormatter(integerFilter(4));
		maxBuiltYearField.setTextFormatter(integerFilter(4));
		minFlatSizeField.setTextFormatter(decimalFilter());
		maxFlatSizeField.setTextFormatter(decimalFilter());

		returnButton.setOnAction(e -> returnButton.getScene().getWindow().hide());
	}

	public void onClose() {
		MainApp.stage.show();
	}
}
