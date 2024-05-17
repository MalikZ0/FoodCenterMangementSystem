package org.fc.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class WaitingQueueController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea WaitingCustomers;

    @FXML
    private Button waitingQueueBack;

    @FXML
    void waitingQueueBackClick(ActionEvent event) throws IOException
    {
        Stage stage = (Stage) waitingQueueBack.getScene().getWindow(); // click cashier01 button will create a stage object and refer the current window
        stage.close();  // close the current window
        Stage primayStage = new Stage();  // create stage
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml")); // load cashier window
        primayStage.setTitle("Back to Main Page");
        primayStage.setScene(new Scene(root));
        primayStage.show();

    }
    @FXML
    void initialize() {
        assert WaitingCustomers != null : "fx:id=\"WaitingCustomers\" was not injected: check your FXML file 'WaitingQueue.fxml'.";
        assert waitingQueueBack != null : "fx:id=\"waitingQueueBack\" was not injected: check your FXML file 'WaitingQueue.fxml'.";

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        StringBuilder waitingCustomersText = new StringBuilder();
        for (Customer customer : FoodQueue.waitingList) {
            if (customer != null) {
                waitingCustomersText.append(customer.getFullName())
                        .append("-")
                        .append(customer.getBurgersRequired())
                        .append("\n\n");
            }
        }
        WaitingCustomers.setText(waitingCustomersText.toString());
    }
}
