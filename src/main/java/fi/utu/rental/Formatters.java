package fi.utu.rental;

import javafx.scene.control.TextFormatter;

import java.text.DecimalFormat;
import java.text.ParsePosition;

public class Formatters {

	public static TextFormatter<String> decimalFilter() {

		return new TextFormatter<>(change -> {
			if (change.getControlNewText().isEmpty() || !change.isAdded()){
				return change;
			}

			DecimalFormat format = new DecimalFormat("#.0");
			ParsePosition parsePosition = new ParsePosition(0);
			Object object = format.parse( change.getControlNewText(), parsePosition );

			if (object == null || parsePosition.getIndex() < change.getControlNewText().length()) {
				return null;
			} else {
				return change;
			}
		});
	}

	public static TextFormatter<String> integerFilter(int maxDigits) {
		return new TextFormatter<>(change -> {
			if (change.isContentChange() && !change.getControlNewText().isEmpty()) {
				try {
					Integer.parseInt(change.getControlNewText());
					if (change.getControlNewText().length() > maxDigits) return null;
				} catch (NumberFormatException e) {
					return null;
				}
			}
			return change;
		});
	}

	public static TextFormatter<String> integerFilter() {
		return integerFilter(10);
	}

	public static TextFormatter<String> phoneNumberFilter() {
		return integerFilter(9);
	}

	public static boolean isValidEmail(String textToValidate) {
		return textToValidate
				.matches("^([\\p{L}0-9]+(\\.[\\p{L}0-9]+)?@[\\p{L}0-9]+\\.[\\p{L}0-9]+)$");
	}

}
