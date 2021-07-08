package org.knn.ui.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;


/**
 * Kustomoidun alert-ikkunan kontrolleri
 *
 */
public class AlertController {
	
	@FXML
	private Label label;
	
	@FXML
	private Button okButton;
	
	/**
	 * Suljetaan alert-ikkuna
	 * @param event
	 */
	@FXML
	void handleOkButton(ActionEvent event) {
		Stage alertStage = (Stage) this.okButton.getScene().getWindow();
		alertStage.close();
	}

}
