package org.knn.ui.controllers;

import org.knn.App;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Kontrolleri Info-näkymälle
 */
public class InfoController {

    @FXML
    private Button backButton;

    /**
     * Palataan takaisin Main-näkymään
     * Palautetaan takaisin alkuperäiset Stage ja Scene
     */
    @FXML
    void handleButtonAction(ActionEvent event) {
        App.getPrimaryStage().setScene(App.getPrimaryScene());
    }

}
