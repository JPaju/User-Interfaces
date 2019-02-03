package fi.utu.week1;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import static fi.utu.week1.Formatters.decimalFilter;
import static fi.utu.week1.Formatters.integerFilter;

public class RentalFlatSearchController {

	@FXML private TextField postalCodeField;
	@FXML private TextField minRentField;
	@FXML private TextField maxRentField;
	@FXML private TextField minBuiltYearField;
	@FXML private TextField maxBuiltYearField;
	@FXML private TextField minFlatSizeField;
	@FXML private TextField maxFlatSizeField;

	public void initialize() {
		postalCodeField.setTextFormatter(integerFilter(5));
		minRentField.setTextFormatter(decimalFilter());
		maxRentField.setTextFormatter(decimalFilter());
		minBuiltYearField.setTextFormatter(integerFilter(4));
		maxBuiltYearField.setTextFormatter(integerFilter(4));
		minFlatSizeField.setTextFormatter(decimalFilter());
		maxFlatSizeField.setTextFormatter(decimalFilter());
	}
}
