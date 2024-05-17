module org.fc.gui {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.fc.gui to javafx.fxml;
    exports org.fc.gui ;
}