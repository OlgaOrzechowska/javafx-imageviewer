package com.starterkit.javafx.controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import com.starterkit.javafx.dataprovider.DataProvider;
import com.starterkit.javafx.model.ViewImage;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

/**
 * Controller for the person search screen.
 * <p>
 * The JavaFX runtime will inject corresponding objects in the @FXML annotated
 * fields. The @FXML annotated methods will be called by JavaFX runtime at
 * specific points in time.
 * </p>
 *
 */
public class ViewImagesController {

	private static final Logger LOG = Logger.getLogger(ViewImagesController.class);

	/**
	 * Resource bundle loaded with this controller. JavaFX injects a resource
	 * bundle specified in {@link FXMLLoader#load(URL, ResourceBundle)} call.
	 * <p>
	 * NOTE: The variable name must be {@code resources}.
	 * </p>
	 */
	@FXML
	private ResourceBundle resources;

	/**
	 * URL of the loaded FXML file. JavaFX injects an URL specified in
	 * {@link FXMLLoader#load(URL, ResourceBundle)} call.
	 * <p>
	 * NOTE: The variable name must be {@code location}.
	 * </p>
	 */
	@FXML
	private URL location;

	@FXML
	private Button previousButton;

	@FXML
	private Button nextButton;

	@FXML
	private Button chooseButton;

	@FXML
	private Button slideButton;

	@FXML
	private Button zoomInButton;

	@FXML
	private Button zoomOutButton;

	@FXML
	private ImageView imageView;

	private final DataProvider dataProvider = DataProvider.INSTANCE;

	private static final long DELAY = 5000;

	private final ViewImage model = new ViewImage();

	@FXML
	public void initialize() {
		previousButton.setDisable(true);
		nextButton.setDisable(true);
		slideButton.setDisable(true);
	}

	@FXML
	private void previousButtonAction() {
		LOG.debug("'Previous' button clicked");

		model.setSelectedIndex(model.getSelectedIndex() - 1);
		Image image = new Image(model.getImages()[model.getSelectedIndex()].toURI().toString());
		imageView.setImage(image);

		previousButton.setDisable(model.getSelectedIndex() == 0);
		nextButton.setDisable(model.getSelectedIndex() + 1 == model.getImages().length);
	}

	@FXML
	private void nextButtonAction() {
		LOG.debug("'Next' button clicked");

		model.setSelectedIndex(model.getSelectedIndex() + 1);
		Image image = new Image(model.getImages()[model.getSelectedIndex()].toURI().toString());
		imageView.setImage(image);

		previousButton.setDisable(model.getSelectedIndex() == 0);
		nextButton.setDisable(model.getSelectedIndex() + 1 == model.getImages().length);
	}

	@FXML
	private void chooseButtonAction() {
		LOG.debug("'Choose' button clicked");

		DirectoryChooser chooser = new DirectoryChooser();
		chooser.setTitle("Selected directory");
		File defaultDirectory = new File("c:");
		chooser.setInitialDirectory(defaultDirectory);

		Stage stage = (Stage) previousButton.getScene().getWindow();
		model.setSelectedDirectory(chooser.showDialog(stage));

		if (model.getSelectedDirectory() != null) {
			model.setImages(dataProvider.findImages(model.getSelectedDirectory()));
			model.setSelectedIndex(0);

			Image image = new Image(model.getImages()[model.getSelectedIndex()].toURI().toString());
			imageView.setImage(image);

			previousButton.setDisable(model.getSelectedIndex() == 0);
			nextButton.setDisable(model.getSelectedIndex() + 1 == model.getImages().length);
			slideButton.setDisable(false);
		}
	}

	@FXML
	private void slideButtonAction() {
		LOG.debug("'Slide' button clicked");

		Task<Void> backgroundTask = new Task<Void>() {

			@Override
			protected Void call() {
				LOG.debug("call() called");

				slideButton.setDisable(true);
				chooseButton.setDisable(true);

				File[] images = model.getImages();
				model.setSelectedIndex(0);

				Image image = new Image(images[model.getSelectedIndex()].toURI().toString());
				imageView.setImage(image);
				previousButton.setDisable(true);
				nextButton.setDisable(model.getSelectedIndex() + 1 == model.getImages().length);

				try {
					Thread.sleep(DELAY);
				} catch (InterruptedException e) {
					throw new RuntimeException("Thread interrupted", e);
				}

				while (model.getSelectedIndex() + 1 < images.length) {
					nextButtonAction();
					try {
						Thread.sleep(DELAY);
					} catch (InterruptedException e) {
						throw new RuntimeException("Thread interrupted", e);
					}
				}
				return null;
			}

			@Override
			protected void succeeded() {
				LOG.debug("succeeded() called");

				slideButton.setDisable(false);
				chooseButton.setDisable(false);
			}
		};

		new Thread(backgroundTask).start();
	}

	@FXML
	private void zoomInButtonAction() {
		LOG.debug("'+' button clicked");
	}

	@FXML
	private void zoomOutButtonAction() {
		LOG.debug("'-' button clicked");
	}

}
