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
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class CashierTwoController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button cashier02Back;

    @FXML
    private Label firstIndexOfCashierTwo;

    @FXML
    private Label secondIndexOfCashierTwo;

    @FXML
    private Label thirdIndexOfCashierTwo;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        if (FoodQueue.queues[1][0] != null)
            firstIndexOfCashierTwo.setText(FoodQueue.queues[1][0].getName() + "-" + String.valueOf(FoodQueue.customers[FoodQueue.getIndex(2, 1)].getBurgersRequired()));

        if (FoodQueue.queues[1][1] != null)
            secondIndexOfCashierTwo.setText(FoodQueue.queues[1][1].getName() + "-" + String.valueOf(FoodQueue.customers[FoodQueue.getIndex(2, 2)].getBurgersRequired()));

        if (FoodQueue.queues[1][2] != null)
            thirdIndexOfCashierTwo.setText(FoodQueue.queues[1][2].getName() + "-" + String.valueOf(FoodQueue.customers[FoodQueue.getIndex(2, 3)].getBurgersRequired()));

    }


    @FXML void cashier02BackClick(ActionEvent event) throws IOException
    {
        Stage stage = (Stage) cashier02Back.getScene().getWindow();
        stage.close();  // close the current window
        Stage primayStage = new Stage();  // create stage
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml")); // load cashier window
        primayStage.setTitle("Back to Main Page");
        primayStage.setScene(new Scene(root));
        primayStage.show();
    }


    @FXML
    void initialize() {
        assert cashier02Back != null : "fx:id=\"cashier02Back\" was not injected: check your FXML file 'CashierTwo.fxml'.";
        assert firstIndexOfCashierTwo != null : "fx:id=\"firstIndexOfCashierTwo\" was not injected: check your FXML file 'CashierTwo.fxml'.";
        assert secondIndexOfCashierTwo != null : "fx:id=\"secondIndexOfCashierTwo\" was not injected: check your FXML file 'CashierTwo.fxml'.";
        assert thirdIndexOfCashierTwo != null : "fx:id=\"thirdIndexOfCashierTwo\" was not injected: check your FXML file 'CashierTwo.fxml'.";

    }

}
