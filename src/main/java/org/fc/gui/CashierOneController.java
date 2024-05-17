package org.fc.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CashierOneController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button cashier01Back;

    @FXML
    private Label firstIndexOfCashierOne;

    @FXML
    private Label secondIndexOfCashierOne;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (FoodQueue.queues[0][0] != null)
            firstIndexOfCashierOne.setText(FoodQueue.queues[0][0].getName() + "-" + String.valueOf(FoodQueue.customers[FoodQueue.getIndex(1, 1)].getBurgersRequired()));

        if (FoodQueue.queues[0][1] != null)
            secondIndexOfCashierOne.setText(FoodQueue.queues[0][1].getName() + "-" + String.valueOf(FoodQueue.customers[FoodQueue.getIndex(1, 2)].getBurgersRequired()));
    }

    @FXML
    void cashier01BackClick(ActionEvent event) throws IOException {
        Stage stage = (Stage) cashier01Back.getScene().getWindow(); // click cashier01 button will create a stage object and refer the current window
        stage.close();  // close the current window
        Stage primayStage = new Stage();  // create stage
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml")); // load cashier window
        primayStage.setTitle("Back to Main Page");
        primayStage.setScene(new Scene(root));
        primayStage.show();
    }

    @FXML
    void initialize() {
        assert cashier01Back != null : "fx:id=\"cashier01Back\" was not injected: check your FXML file 'CashierOne.fxml'.";
        assert firstIndexOfCashierOne != null : "fx:id=\"firstIndexOfCashierOne\" was not injected: check your FXML file 'CashierOne.fxml'.";
        assert secondIndexOfCashierOne != null : "fx:id=\"secondIndexOfCashierOne\" was not injected: check your FXML file 'CashierOne.fxml'.";
    }
}