module com.project.sample {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.project.sample to javafx.fxml;
    exports com.project.sample;
//    opens com.project.sample.Controller to javafx.fxml;
}