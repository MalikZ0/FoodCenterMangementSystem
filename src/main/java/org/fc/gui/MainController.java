package org.fc.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class MainController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button cashier01;

    @FXML
    private Button cashier02;

    @FXML
    private Button cashier03;

    @FXML
    private Button search;

    @FXML
    private Button waitingQueue;

    @FXML
    private TextField CustomerName;

    @FXML
    String getCustomerName(ActionEvent event) {
        String name = CustomerName.getText();
        return searchCustomer(name);
    }

    @FXML
    void searchCustomerName(ActionEvent event)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        String displayMessage = getCustomerName(event);
        alert.setContentText(displayMessage);
        alert.showAndWait();
    }

    private ArrayList<Customer> getCustomersToArrayList(int cashierIndex)
    {
        ArrayList<Customer> customersInQueue = new ArrayList<>();
        for(int i=0; i < FoodQueue.queues[cashierIndex].length ;i++)
            if (FoodQueue.queues[cashierIndex][i] != null)
                customersInQueue.add(FoodQueue.customers[FoodQueue.getIndex(cashierIndex + 1, i + 1)]);

        return customersInQueue;
    }

    private String searchCustomer(String name)
    {
        for(int cashierIndex = 0; cashierIndex < 3; cashierIndex++)
        {
            ArrayList<Customer> customersInQueue = getCustomersToArrayList(cashierIndex);
            for(Customer customer : customersInQueue)
            {
                if(customer != null && customer.getFullName().contains(name)) {
                    return (customer.getFullName() + " is in Queue 0" + (cashierIndex + 1));
                }
            }
        }

        for(Customer customer : FoodQueue.waitingList)
        {
            if(customer != null && customer.getFullName().contains(name))
                return (customer.getFullName()+" is in waiting Queue ");
        }
        return (name+" is not in queue ");
    }

    @FXML
    void viewCashier01Click(ActionEvent event) throws IOException {

        Stage stage = (Stage) cashier01.getScene().getWindow(); // click cashier01 button will create a stage object and refer the current window
        stage.close();  // close the current window
        Stage primayStage = new Stage();  // create stage
        Parent root = FXMLLoader.load(getClass().getResource("CashierOne.fxml")); // load cashier window
        primayStage.setTitle("view Cashier 01 details");
        primayStage.setScene(new Scene(root));
        primayStage.show();
    }

    @FXML
    void viewCashier02Click(ActionEvent event) throws IOException
    {
        Stage stage = (Stage) cashier02.getScene().getWindow(); // click cashier01 button will create a stage object and refer the current window
        stage.close();  // close the current window
        Stage primayStage = new Stage();  // create stage
        Parent root = FXMLLoader.load(getClass().getResource("CashierTwo.fxml")); // load cashier window
        primayStage.setTitle("view Cashier 02 details");
        primayStage.setScene(new Scene(root));
        primayStage.show();
    }

    @FXML
    void viewCashier03Click(ActionEvent event) throws IOException {
        Stage stage = (Stage) cashier03.getScene().getWindow(); // click cashier01 button will create a stage object and refer the current window
        stage.close();  // close the current window
        Stage primayStage = new Stage();  // create stage
        Parent root = FXMLLoader.load(getClass().getResource("CashierThree.fxml")); // load cashier window
        primayStage.setTitle("view Cashier 03 details");
        primayStage.setScene(new Scene(root));
        primayStage.show();
    }

    @FXML
    void viewWaitingQueueClick(ActionEvent event) throws IOException {
        Stage stage = (Stage) waitingQueue.getScene().getWindow(); // click cashier01 button will create a stage object and refer the current window
        stage.close();  // close the current window
        Stage primayStage = new Stage();  // create stage
        Parent root = FXMLLoader.load(getClass().getResource("WaitingQueue.fxml")); // load cashier window
        primayStage.setTitle("view Waiting Queue details");
        primayStage.setScene(new Scene(root));
        primayStage.show();

    }

    @FXML
    void initialize() {
        assert cashier01 != null : "fx:id=\"cashier01\" was not injected: check your FXML file 'Main.fxml'.";
        assert cashier02 != null : "fx:id=\"cashier02\" was not injected: check your FXML file 'Main.fxml'.";
        assert cashier03 != null : "fx:id=\"cashier03\" was not injected: check your FXML file 'Main.fxml'.";
        assert search != null : "fx:id=\"search\" was not injected: check your FXML file 'Main.fxml'.";
        assert waitingQueue != null : "fx:id=\"waitingQueue\" was not injected: check your FXML file 'Main.fxml'.";

    }

}
