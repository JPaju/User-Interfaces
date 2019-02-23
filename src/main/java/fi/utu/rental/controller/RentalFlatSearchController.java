package fi.utu.rental.controller;

import fi.utu.rental.AppState;
import fi.utu.rental.Asunto;
import fi.utu.rental.AsuntoGeneraattori;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.transformation.FilteredList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import static fi.utu.rental.AsuntoGeneraattori.tehtyjäAsuntoja;
import static fi.utu.rental.AsuntoGeneraattori.tehtäviäAsuntoja;
import static fi.utu.rental.Formatters.decimalFilter;
import static fi.utu.rental.Formatters.integerFilter;

public class RentalFlatSearchController extends AbstractController implements Initializable {

	private FilteredList<Asunto> filteredContent = new FilteredList<>(store.getAsunnot(), p -> true);

	@FXML private TextField postalCodeField;
	@FXML private TextField addressField;
	@FXML private TextField minRentField;
	@FXML private TextField maxRentField;
	@FXML private TextField minBuiltYearField;
	@FXML private TextField maxBuiltYearField;
	@FXML private TextField minFlatSizeField;
	@FXML private TextField maxFlatSizeField;

	@FXML private TitledPane resultsPane;

	@FXML private TableView<Asunto> resultsList;
	@FXML private TableColumn<Asunto, String> addressColumn;
	@FXML private TableColumn<Asunto, Float> flatSizeColumn;
	@FXML private TableColumn<Asunto, Integer> rentAmountColumn;
	@FXML private TableColumn<Asunto, Integer> builtYearColumn;
	@FXML private TableColumn<Asunto, String> emailColumn;

	@FXML private Button returnButton;
	@FXML private Button clearFilterButton;
	@FXML private Button createFlatsButton;

	@FXML private ProgressBar createProgressBar;


	@Override
	protected void handleStateChange(AppState newState) {
		if (newState == AppState.SearchingProperty) {
			stage.show();
		} else {
			stage.hide();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initFormatters();
		initFilters();

		store.getAsunnot().addAll(
				AsuntoGeneraattori.luo(),
				AsuntoGeneraattori.luo(),
				AsuntoGeneraattori.luo(),
				AsuntoGeneraattori.luo(),
				AsuntoGeneraattori.luo()
		);

		resultsPane.textProperty().bind(Bindings.concat("Hakutulokset (").concat(Bindings.size(filteredContent).asString()).concat(" kpl)"));
		resultsList.setEditable(true);
		resultsList.setItems(filteredContent);

		createProgressBar.setManaged(false);

		returnButton.setOnAction(e -> store.setAppState(AppState.MainMenu));
		clearFilterButton.setOnAction(e -> clearFilters());
		createFlatsButton.setOnAction(e -> startFlatCreation(10));

		store.addStateListener((arg, oldValue, newValue) -> handleStateChange(newValue));
	}

	private void startFlatCreation(int amount) {
		createFlatsButton.setDisable(true);
		createProgressBar.setManaged(true);
		createProgressBar.setVisible(true);

		Task asuntoTask = new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				AsuntoGeneraattori.luoAsuntoja(amount, store.getAsunnot());
				while(tehtyjäAsuntoja() < tehtäviäAsuntoja()) {
					updateProgress(tehtyjäAsuntoja(), tehtäviäAsuntoja());
					Thread.sleep(200);
				}

				updateProgress(0,1);
				createFlatsButton.setDisable(false);
				createProgressBar.setManaged(false);
				createProgressBar.setVisible(false);

				return null;
			}
		};
		createProgressBar.progressProperty().bind(asuntoTask.progressProperty());
		new Thread(asuntoTask).start();
	}

	private void initFormatters() {
		postalCodeField.setTextFormatter(integerFilter(5));
		minRentField.setTextFormatter(integerFilter());
		maxRentField.setTextFormatter(integerFilter());
		minBuiltYearField.setTextFormatter(integerFilter(4));
		maxBuiltYearField.setTextFormatter(integerFilter(4));
		minFlatSizeField.setTextFormatter(decimalFilter());
		maxFlatSizeField.setTextFormatter(decimalFilter());

		addressColumn.setCellValueFactory(new PropertyValueFactory<>("osoite"));
		flatSizeColumn.setCellValueFactory(new PropertyValueFactory<>("neliömäärä"));
		rentAmountColumn.setCellValueFactory(new PropertyValueFactory<>("vuokra"));
		builtYearColumn.setCellValueFactory(new PropertyValueFactory<>("rakennusyear"));
		emailColumn.setCellValueFactory(new PropertyValueFactory<>("sähkömail"));
	}

	private void initFilters() {

		ObjectProperty<Predicate<Asunto>> addressFilter = new SimpleObjectProperty<>();
		ObjectProperty<Predicate<Asunto>> minRentFilter = new SimpleObjectProperty<>();
		ObjectProperty<Predicate<Asunto>> maxRentFilter = new SimpleObjectProperty<>();
		ObjectProperty<Predicate<Asunto>> minBuiltYearFilter = new SimpleObjectProperty<>();
		ObjectProperty<Predicate<Asunto>> maxBuiltYearFilter = new SimpleObjectProperty<>();

		addressFilter.bind(Bindings.createObjectBinding(() ->
						asunto -> asunto.getOsoite().toLowerCase().contains(addressField.getText().toLowerCase()),
				addressField.textProperty()));

		minRentFilter.bind(Bindings.createObjectBinding(() ->
				asunto -> {
					if (minRentField.getText().isEmpty())
						return true;
					return asunto.getVuokra() > Integer.parseInt(minRentField.getText());
				}, minRentField.textProperty()));

		maxRentFilter.bind(Bindings.createObjectBinding(() ->
				asunto -> {
					if (maxRentField.getText().isEmpty())
						return true;
					return asunto.getVuokra() < Integer.parseInt(maxRentField.getText());
				}, maxRentField.textProperty()));

		minBuiltYearFilter.bind(Bindings.createObjectBinding(() ->
				asunto -> {
					if (minBuiltYearField.getText().isEmpty())
						return true;
					return asunto.getRakennusyear() > Integer.parseInt(minBuiltYearField.getText());
				}, minBuiltYearField.textProperty()));

		maxBuiltYearFilter.bind(Bindings.createObjectBinding(() ->
				asunto -> {
					if (maxBuiltYearField.getText().isEmpty())
						return true;
					return asunto.getRakennusyear() < Integer.parseInt(maxBuiltYearField.getText());
				}, maxBuiltYearField.textProperty()));

		filteredContent.predicateProperty().bind(Bindings.createObjectBinding(() ->
						addressFilter.get().and(minRentFilter.get()).and(maxRentFilter.get())
								.and(minBuiltYearFilter.get()).and(maxBuiltYearFilter.get()),
				addressFilter, minRentFilter, maxRentFilter, minBuiltYearFilter, maxBuiltYearFilter
		));
	}

	private void clearFilters() {
		addressField.clear();
		minRentField.clear();
		maxRentField.clear();
		minBuiltYearField.clear();
		maxBuiltYearField.clear();
		minFlatSizeField.clear();
		maxFlatSizeField.clear();
	}

}
