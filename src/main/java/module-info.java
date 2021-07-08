module org.knn {
    requires javafx.controls;
    requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.base;

    opens org.knn to javafx.fxml;
    opens org.knn.ui.controllers to javafx.fxml;
    opens org.knn.data;
    
    exports org.knn;
}