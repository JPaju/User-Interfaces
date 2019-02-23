package fi.utu.rental.controller;

import fi.utu.rental.AppState;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;


public class MainAppController extends AbstractController implements Initializable {

	@FXML private Label animationLabel;

	@FXML private Button rentalAdButton;
	@FXML private Button rentalSearchButton;

	@Override
	protected void handleStateChange(AppState newState) {
		if (newState == AppState.MainMenu) {
			stage.show();
		} else if (newState == AppState.Exit) {
			Platform.exit();
		} else {
			stage.hide();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initDateEmojiAnimation("👺", 1);

		rentalAdButton.setOnAction(e -> store.setAppState(AppState.AddingProperty));
		rentalSearchButton.setOnAction(e -> store.setAppState(AppState.SearchingProperty));

		store.addStateListener((arg, oldValue, newValue) -> handleStateChange(newValue));
	}

	private void initDateEmojiAnimation(String textToAnimate, int interval) {
		final var renderEmoji = new SimpleBooleanProperty(true);
		var dtf = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy");

		var emojiFont = Font.font(30);
		var dateFont = Font.font("Harlow Solid Italic" ,15);

		Timeline timeline = new Timeline();
		KeyFrame keyFrame = new KeyFrame(
				Duration.seconds(interval),
				actionEvent -> {
					if (renderEmoji.get()) {
						animationLabel.setFont(dateFont);
						animationLabel.setText(dtf.format(LocalDateTime.now()));
						renderEmoji.set(false);
					} else {
						animationLabel.setFont(emojiFont);
						animationLabel.setText(textToAnimate);
						renderEmoji.set(true);
					}
		});

		timeline.getKeyFrames().add(keyFrame);
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();

	}
}
