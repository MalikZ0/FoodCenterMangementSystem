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

public class CashierThreeController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button cashier03Back;

    @FXML
    private Label fifthIndexOfCashierThree;

    @FXML
    private Label firstIndexOfCashierThree;

    @FXML
    private Label fourthIndexOfCashierThree;

    @FXML
    private Label secondIndexOfCashierThree;

    @FXML
    private Label thirdIndexOfCashierThree;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        if (FoodQueue.queues[2][0] != null)
            firstIndexOfCashierThree.setText(FoodQueue.queues[2][0].getName() + "-" + String.valueOf(FoodQueue.customers[FoodQueue.getIndex(3, 1)].getBurgersRequired()));

        if (FoodQueue.queues[2][1] != null)
            secondIndexOfCashierThree.setText(FoodQueue.queues[2][1].getName() + "-" + String.valueOf(FoodQueue.customers[FoodQueue.getIndex(3, 2)].getBurgersRequired()));

        if (FoodQueue.queues[2][2] != null)
            thirdIndexOfCashierThree.setText(FoodQueue.queues[2][2].getName() + "-" + String.valueOf(FoodQueue.customers[FoodQueue.getIndex(3, 3)].getBurgersRequired()));

        if (FoodQueue.queues[2][3] != null)
            fourthIndexOfCashierThree.setText(FoodQueue.queues[2][3].getName() + "-" + String.valueOf(FoodQueue.customers[FoodQueue.getIndex(3, 4)].getBurgersRequired()));

        if (FoodQueue.queues[2][4] != null)
            fifthIndexOfCashierThree.setText(FoodQueue.queues[2][4].getName() + "-" + String.valueOf(FoodQueue.customers[FoodQueue.getIndex(3, 5)].getBurgersRequired()));

    }

    @FXML
    void cashier03BackClick(ActionEvent event) throws IOException
    {
        Stage stage = (Stage) cashier03Back.getScene().getWindow(); // click cashier01 button will create a stage object and refer the current window
        stage.close();  // close the current window
        Stage primayStage = new Stage();  // create stage
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml")); // load cashier window
        primayStage.setTitle("Back to Main Page");
        primayStage.setScene(new Scene(root));
        primayStage.show();

    }

    @FXML
    void initialize() {
        assert cashier03Back != null : "fx:id=\"cashier03Back\" was not injected: check your FXML file 'CashierThree.fxml'.";
        assert fifthIndexOfCashierThree != null : "fx:id=\"fifthIndexOfCashierThree\" was not injected: check your FXML file 'CashierThree.fxml'.";
        assert firstIndexOfCashierThree != null : "fx:id=\"firstIndexOfCashierThree\" was not injected: check your FXML file 'CashierThree.fxml'.";
        assert fourthIndexOfCashierThree != null : "fx:id=\"fourthIndexOfCashierThree\" was not injected: check your FXML file 'CashierThree.fxml'.";
        assert secondIndexOfCashierThree != null : "fx:id=\"secondIndexOfCashierThree\" was not injected: check your FXML file 'CashierThree.fxml'.";
        assert thirdIndexOfCashierThree != null : "fx:id=\"thirdIndexOfCashierThree\" was not injected: check your FXML file 'CashierThree.fxml'.";

    }

}
