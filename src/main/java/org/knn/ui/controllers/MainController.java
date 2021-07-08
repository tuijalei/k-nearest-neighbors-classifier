package org.knn.ui.controllers;

import org.knn.App;
import org.knn.data.CustomLineChart;
import org.knn.data.IrisData;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Main-näkymän kontrolleri
 */
public class MainController {

	private final String helpFXML = "/org/knn/infoWindow.fxml";
	private final String alertFXML = "/org/knn/alertWindow.fxml";
    private CustomLineChart sepalChart;
    private CustomLineChart petalChart;
    private final double min = 0.1;
    private final double max = 9.9;
	
	@FXML
	private ComboBox<String> chartCombo;
	
	@FXML
	private StackPane lineChartPane = new StackPane();
	
	@FXML
	private ComboBox<Integer> numOfKCombo;
    
    @FXML
    private TextField sepalLength;
    
    @FXML
    private TextField sepalWidth;
    
    @FXML
    private TextField petalLength;
    
    @FXML
    private TextField petalWidth;

    @FXML
    private Button learnButton;

    @FXML
    private Button addButton;

    @FXML
    private Button helpButton;

    @FXML
    private Button quitButton;
    
    /**
     * Suljetaan applikaatio
     */
    @FXML
    void quitButtonAction(ActionEvent action){
        Platform.exit();
    }

    /**
     * Vaihdetaan näkymä helpWindow.fxml mukaiseksi
     */
    @FXML
    void helpButtonAction(ActionEvent action) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource(helpFXML));
        Parent root = loader.load();
        App.getPrimaryStage().setScene(new Scene(root));
    }

    
    /**
     * Lisää uuden IrisData-tyyppisen olion 
     */
    @FXML
    void addButtonAction(ActionEvent action) throws IOException {
    	List<Double> confData = new ArrayList<>();
    	double[] lengthsAndWidths = new double[]{Double.parseDouble(sepalLength.getText()),
    	Double.parseDouble(sepalWidth.getText()),
   		Double.parseDouble(petalLength.getText()),
   		Double.parseDouble(petalWidth.getText())};
    	
   		for(int i = 0; i < lengthsAndWidths.length; i++) {
   			if(lengthsAndWidths[i] != 0.0 && lengthsAndWidths[i] >= min && lengthsAndWidths[i] <= max) {    				
   				confData.add(lengthsAndWidths[i]);
    		} else {
    			confData.add(0.0);
    		}
    	}
    		
    	if(confData.contains(0.0)) {
    		Stage stage = new Stage();
    		FXMLLoader loader = new FXMLLoader(getClass().getResource(alertFXML));
    		Parent alertRoot = loader.load();
    			
    		stage.setTitle("Too small or too big alert");
    		stage.setScene(new Scene(alertRoot));
    		stage.show();
    	} else {
    		IrisData[] irisData = App.dataHandler.addIrisData(lengthsAndWidths);
    		sepalChart.addDataPoint(irisData[0]);
    		petalChart.addDataPoint(irisData[1]);
    	}	
    }
    
    /**
     * Ajetaan launchin jälkeen - ladataan iris.data-tiedosto ja alustetaan main-näkymän komponentit
     */
    public void initialize() {
    	settingUpData();    	
    	settingUpChartsAndCombos();
    	this.lineChartPane.getChildren().add(this.sepalChart);
    	
    	// Bindataan TextFieldien textPropertyt addButtonin disabledPropertyyn
    	// Ei siis voida lisätä data-alkiota jos kaikissa kentissä ei ole jotain
    	BooleanBinding fieldsAreEmpty = sepalLength.textProperty().isEmpty().
    			or(sepalWidth.textProperty().isEmpty()).
    			or(petalLength.textProperty().isEmpty()).
    			or(petalWidth.textProperty().isEmpty());
    	this.addButton.disableProperty().bind(fieldsAreEmpty);
    	
    	// Charttien comboboxille mahdollisuus vaihtaa StackPaneen uusi chart
    	this.chartCombo.setOnAction(e -> {
    		if(!this.lineChartPane.getChildren().isEmpty()) {
    			this.lineChartPane.getChildren().clear();
    		}
    		String selectedChart = this.chartCombo.getSelectionModel().getSelectedItem();
    		if(selectedChart.contains("Sepal chart")) {
    			this.lineChartPane.getChildren().add(sepalChart);
    		} else {
    			this.lineChartPane.getChildren().add(petalChart);
    		}
    	});
    	
    	// K lkm:n comboBoxista tallennetaan arvo ohjelman käytettäväksi
    	this.numOfKCombo.setOnAction(e -> {
    		int selectedNro = this.numOfKCombo.getSelectionModel().getSelectedItem();
    		App.K = selectedNro;
    	});
    	
    	// ----- Ei mahdollista syöttää muita kun numeroita TextFieldeihin ----- //
    	sepalLength.textProperty().addListener(new ChangeListener<String>() {
    	    @Override
    	    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
    	        if(!newValue.matches("\\d*(\\.\\d*)?")) {
    	            sepalLength.setText(oldValue);
    	        }
    	    }
    	});
    	
    	sepalWidth.textProperty().addListener(new ChangeListener<String>() {
    		 @Override
     	    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
     	        if(!newValue.matches("\\d*(\\.\\d*)?")) {
     	            sepalWidth.setText(oldValue);
     	        }
     	    }
    	});
    	
    	petalLength.textProperty().addListener(new ChangeListener<String>() {
    		 @Override
     	    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
     	        if(!newValue.matches("\\d*(\\.\\d*)?")) {
     	            petalLength.setText(oldValue);
     	        }
     	    }
    	});
    	
    	petalWidth.textProperty().addListener(new ChangeListener<String>() {
    		 @Override
     	    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
     	        if(!newValue.matches("\\d*(\\.\\d*)?")) {
     	            petalWidth.setText(oldValue);
     	        }
     	    }
    	});
    }
    
    /**
     * Asetetaan LineChartit alkuun sekä lisätään ComboBoxiin LineChartien nimet
     */
    private void settingUpChartsAndCombos() {
    	this.sepalChart = new CustomLineChart(new NumberAxis(), new NumberAxis(), App.dataHandler.getSepalData());
    	this.petalChart = new CustomLineChart(new NumberAxis(), new NumberAxis(), App.dataHandler.getPetalData());
    	
    	this.chartCombo.getItems().addAll(new String("Sepal chart"), new String("Petal chart"));
    	this.chartCombo.getSelectionModel().selectFirst();
    	IntStream.rangeClosed(1,10).boxed().forEach(numOfKCombo.getItems()::add);
    	this.numOfKCombo.getSelectionModel().selectFirst();
    }
    
    /**
     * Ladataan koulutusaineisto
     */
    private void settingUpData() {
    	App.dataHandler.learnIrisData();
    }

}
