package org.knn;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import org.knn.data.DataHandler;

/**
 * JavaFX App
 */
public class App extends Application {

	public static int K;
    private static Stage primaryStage;
    private static Scene primaryScene;
    private static Scene scene;
    
    public final static DataHandler dataHandler = new DataHandler();

    @Override
    public void start(Stage stage) throws IOException {
    	
        scene = new Scene(loadFXML("mainWindow"));
        stage.setResizable(false);
        stage.setTitle("K nearest neighbors classifier");
        stage.setScene(scene);
        
        setPrimaryStage(stage);
        setPrimaryScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }


    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void setPrimaryStage(Stage primaryStage) {
        App.primaryStage = primaryStage;
    }

    public static Scene getPrimaryScene() {
        return primaryScene;
    }

    public static void setPrimaryScene(Scene primaryScene) {
        App.primaryScene = primaryScene;
    }

    
    public static void main(String[] args) {
        launch();
    }

}