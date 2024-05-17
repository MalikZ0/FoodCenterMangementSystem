// P.G.M.D.KUMARA 
package org.fc.ClassVer_;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class FoodQueue {
    static final int[] MAXIMUM_CUSTOMERS = new int[]{2, 3, 5};    // Elements in this array correspond to the maximum number of customers for each cashier in the cashiers array.
    private static int currentBurger_Stock = 50;                  // Food Center will have exactly 50 burgers in stock from beginning.Later it can be changed.
    private static final int STOCK_WARNING = 10;                  // Constant value of remaining stock
    static int currentCashierIndex = 0;                           // First of the cashier queue.Later it can be incremented
    private final String name;
    FoodQueue (String name){
        this.name =name;
    }                  // Create a constructor for FoodQueue to pass names
    public String getName() {                  // get access for name
        return name;
    }                      // Create a constructor for get the name
    static Customer[] customers = new Customer[10];
    private static final int LIST_SIZE = 10;
    static int front = 0;                                         // For circular queue variables
    static int rear = -1;
    static int count = 0;
    static Customer[] waitingList = new Customer[LIST_SIZE];
    static FoodQueue[][] queues;                // Declaring of the cashiers array, which represents the queues for customers

    static  {
        queues = new FoodQueue[3][];            // 2D array with 3 rows (3 cashiers) ---jagged array---
        queues[0] = new FoodQueue[2];
        queues[1] = new FoodQueue[3];
        queues[2] = new FoodQueue[5];
    }
    // ------------Main methods of the program -------------------------------------------------------------------------

    static void ViewAllQueues() {
        System.out.println("*******************");
        System.out.println("*     Cashiers    *");
        System.out.println("*******************");
        int longest_QueueLine = 5;                                 // Get maximum rows for the number of cashiers
        for (int i = 0; i < longest_QueueLine; i++) {              // Iterates over maximum number of rows
            for (int j = 0; j < MAXIMUM_CUSTOMERS.length; j++) {
                if (i < queues[j].length) {                        // 'j' determines the rows, whereas 'i' determines the columns.
                    if (queues[j][i] != null) {
                        System.out.print(" O      ");
                    } else {
                        System.out.print(" X      ");
                    }
                } else {
                    System.out.print("        ");                  // Print empty spaces for in cashiers spots that exceed maximum spots
                }
            }
            System.out.println();
        }
    }

    static void ViewEmptyQueues() {
        System.out.println("********************");
        System.out.println("*   Empty Queues   *");
        System.out.println("********************");

        for (int i = 0; i < queues.length; i++) {               // Iterates all columns of each cashier
            System.out.println("Cashier " + (i + 1) + ":");
            for (int j = 0; j < queues[i].length; j++) {        // Iterates maximum number of spots in each cashier
                if (queues[i][j] == null) {
                    System.out.println("Position " + (j + 1) + ": Empty");
                } else {
                    System.out.println("Position " + (j + 1) + ": Full");
                }
            }
            System.out.println();
        }
    }

    static void AddCustomer() {
        Scanner userInput_2 = new Scanner(System.in);
        String firstName;
        String secondName;
        int burgersAmount = 0;
        while (true) {
            while(true){
                System.out.print("Enter the customer's first name: ");
                firstName = userInput_2.next();
                boolean contains_NumbersOrSymbols = false;
                for (char c : firstName.toCharArray()) {                             // Iterates each character in user input name
                    if (!((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z'))) {       // Check each character is letter or not
                        contains_NumbersOrSymbols = true;
                        break;
                    }
                }
                if (contains_NumbersOrSymbols) {
                    System.out.println("Do not use numbers/symbols for customer names!");
                } else {
                    break;
                }
            }
            while (true){
                System.out.print("Enter the customer's second name: ");
                secondName = userInput_2.next();
                boolean contains_NumbersOrSymbols = false;
                for (char c : secondName.toCharArray()) {                             // Iterates each character in user input name
                    if (!((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z'))) {       // Check each character is letter or not
                        contains_NumbersOrSymbols = true;
                        break;
                    }
                }
                if (contains_NumbersOrSymbols) {
                    System.out.println("Do not use numbers/symbols for customer names!");
                } else {
                    break;
                }
            }

            try {
                System.out.print("Enter the number of burgers required: ");
                burgersAmount += userInput_2.nextInt();
                break;
            }catch(InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer value.");
                userInput_2.nextLine();                                          // Clear the invalid input from the scanner
            }
        }
        Customer customer = new Customer(firstName, secondName, burgersAmount);
        if (currentBurger_Stock == 0){                                         // Check for available stock
            System.out.println("NO MORE STOCK AVAILABLE!!!!");
            System.out.println("---UPDATE THE STOCK---");
            return;
        }
        if (is_QueueFull()) {                                                  // Set boolean value to check whether queue is already full when adding
            System.out.println("Queue is full. Cannot add more customers.");
            // No available indexes to append the customer , so Waiting list adds here
            addingToList(customer);
            // System.out.println(Arrays.toString(waitingList));               //  Display waiting queue
            return;
        }
        // Find the first available index with a value of null in the customer array
        int indexToAppend = -1;
        for (int i = 0; i < customers.length; i++) {
            if (customers[i] == null) {
                indexToAppend = i;
                break;
            }
        }
        if (indexToAppend != -1) {
            customers[indexToAppend] = customer;
        } else {
            System.out.println("No available space in the Customer's list .");
            return;
        }
        emptySpot(customer);
        //System.out.println(Arrays.toString(customers));                 // Display all customer data in an array
    }

    static void RemoveCustomer() {
        Scanner userInput_3 = new Scanner(System.in);
        int cashierNumber;
        while (true) {                                                  // Validator for check user's input
            System.out.print("Enter the cashier number: ");
            try {
                cashierNumber = userInput_3.nextInt();
                break;                                                  // Exit the loop if the input is a valid integer
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer value.");
                userInput_3.nextLine();                                // Clear the invalid input from the scanner
            }
        }
        if (cashierNumber < 1 || cashierNumber > queues.length) {
            System.out.println("Invalid cashier number.");
            return;
        }
        int spotNumber;
        while (true) {                                                   // Validator for check user's input
            try {
                System.out.print("Enter the position number: ");
                spotNumber = userInput_3.nextInt();
                break;                                                  // Exit the loop if the input is a valid integer
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer value.");
                userInput_3.nextLine();                                // Clear the invalid input from the scanner
            }
        }
        if (spotNumber < 1 || spotNumber > queues[cashierNumber - 1].length) {   // Validate inputs
            System.out.println("Invalid spot number.");
            return;
        }

        if (queues[cashierNumber - 1][spotNumber - 1] == null) {                // Checks current position of user inputs
            System.out.println("No customer found at the specified spot.");
            return;
        }

        String customerName = queues[cashierNumber - 1][spotNumber - 1].getName();       // Get name of the customer in the user inputs
        queues[cashierNumber - 1][spotNumber - 1] = null;                                // Removing customer
        // Removing the corresponding customer from customers array
        int indexToRemove = getIndex(cashierNumber, spotNumber);                         // Index of corresponding customer
        Customer customerRemove = customers[indexToRemove];                              // Get corresponding customer
        int burgerAmountToRemove = customerRemove.getBurgersRequired();                  // Get corresponding customer's burgers amount
        currentBurger_Stock += burgerAmountToRemove;                                     // Reset burger amount
        customers[indexToRemove] = null;                                                 // Replace
        int swappingNumber = getIndex(cashierNumber, (spotNumber+1));                    // Shifting customers according to cashiers 2d array
        if (swappingNumber != -1) {
            swapping(cashierNumber,spotNumber);
        }
        //System.out.println(Arrays.toString(customers));                                  // Display all customer data in an array
        System.out.println("Customer " + customerName + " has been removed from Cashier " + cashierNumber +":"+"Position "+ spotNumber +",Burger Amount "+burgerAmountToRemove);
        shift_Queues();
    }

    static void RemoveServedCustomer() {                                                // Removing served customer from cashier
        int cashierIndex;
        while (true) {
            Scanner userInput_6 = new Scanner(System.in);
            System.out.println("Enter the cashier number(1-3):");
            try {
                cashierIndex = userInput_6.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer value.");
            }
        }
        if ((queues[0][0] == null) && (queues[1][0] == null) && (queues[2][0] == null)) {   // Check all three cashier spots in the front of cashiers
            System.out.println("No customer is found.");
        } else if (queues[cashierIndex-1][0] != null) {                                     // Removing first three spots from each cashier
            System.out.println("Served customer removed"+" from cashier "+ cashierIndex+ ": " + queues[cashierIndex-1][0].getName());
            queues[cashierIndex-1][0] = null;
            int indexToRemove = getIndex(cashierIndex,1);
            customers[indexToRemove] = null;       // Replace
            int swappingNumber = getIndex(cashierIndex, 2);
            Customer waitListcustomer = removingFromList();
            if (customers[swappingNumber] != null){
                swapping(cashierIndex,1,waitListcustomer);
                //System.out.println(Arrays.toString(waitingList));
                //System.out.println(Arrays.toString(customers));
            }
            shift_Queues(waitListcustomer);
        }
    }
    static void ViewCustomers() {
        Customer[] allCustomersNames = store_Customers();                       // Assigns the value of the all customers in an array of all customers names
        if (allCustomersNames.length == 0) {
            System.out.println("No customers found.");
        } else {
            System.out.println("Customers:");
            for (Customer customer : allCustomersNames) {                      // Displaying each name in array
                if (customer != null) {
                    System.out.println(customer.getFullName()+" Burger Amount: "+customer.getBurgersRequired());
                }
            }
        }
    }

    static void StoreProgramIntoFile() {
        try {                                                                   // Exception handling check input output errors
            FileWriter data_Writer = new FileWriter("foodQueue_Dat.txt");
            // Write cashiers data
            data_Writer.write("customer names with cashier number:");
            data_Writer.write("\n");
            for (int c = 0; c < queues.length; c++) {
                for (int i = 0; i < queues[c].length; i++) {
                    FoodQueue customer = queues[c][i];                          // Assign each customer to corresponding cashier
                    if (customer != null) {
                        data_Writer.write("Cashier " + (c + 1) + " position " + (i + 1) + " :" + queues[c][i].getName());  // Write cashier with index
                        data_Writer.write("\n");
                    }
                }
            }
            // Write stock value
            int remainingStock = currentBurger_Stock;
            data_Writer.write("Remaining stock:");                          // Write remaining stock to the file
            data_Writer.write(Integer.toString(remainingStock));

            data_Writer.close();
            System.out.println("Program data stored to 'foodQueue_Dat.txt' file.");
        } catch (IOException e) {
            System.out.println("Error occurred while storing program data: ");
        }

    }

    static void LoadProgramFromFile() {
        try {                                                                    // Exception handling check input output errors
            FileReader data_Reader = new FileReader("foodQueue_Dat.txt");
            Scanner userInput_4 = new Scanner(data_Reader);
            while (userInput_4.hasNext()) {                                      // Read and display file lines
                String line = userInput_4.nextLine();
                System.out.println(line);
            }
            userInput_4.close();
            System.out.println("Program data has been loaded from 'foodQueue_Dat.txt'.");
        } catch (IOException e) {
            System.out.println("Error occurred while loading program data: ");
        }
    }
    static void ViewBurgerStock() {
        int remainingStock = currentBurger_Stock;                               // Current burger stock is updated with other methods
        System.out.println("Remaining burger stock: " + remainingStock);
    }
    static void AddBurgers() {
        System.out.print("Enter the number of burgers to add: ");
        Scanner userInput_5 = new Scanner(System.in);
        int newStock;
        while (true) {                                                          // Validator for check user's input
            try {
                newStock = userInput_5.nextInt();
                break;                                                          // Exit the loop if the input is a valid integer
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer value.");
                userInput_5.nextLine();                                         // Clear the invalid input from the scanner
            }
        }
        currentBurger_Stock += newStock;                                        // Update burger stock
        System.out.println(newStock + " burgers have been added to the stock.");
    }
    static void ViewWaitingList(){
        for (Customer customer : waitingList) {
            if (customer != null) {
                System.out.println("Name :"+ customer.getFullName() + " - Burger amount: " + customer.getBurgersRequired());
            } else {
                System.out.println("null,customer is not added");
            }
        }
    }
    static void IncomeInEachQueues() {                                         // Calculate income from each queue
        System.out.println("Income of each Queue:");
        int pricePerBurger = 650;
        for (int i = 0; i < queues.length; i++) {
            int burgerIncome = 0;
            System.out.println("Total Income Of Queue "+(i+1)+":");
            for(int j = 0; j < queues[i].length; j++) {
                if (queues[i][j] != null) {
                    int customer_index = getIndex((i + 1), (j + 1));
                    burgerIncome += customers[customer_index].getBurgersRequired() * pricePerBurger;
                }
            }
            System.out.println("$"+ burgerIncome);
        }
    }
    // --------Helping methods of the program---------------------------------------------------------------------------

    static boolean is_QueueFull() {                             // This method is for calculating maximum customers in all cashiers
        int totalCustomers = 0;                                 // And checks current total customers with maximum customers to find out cashier is full or not
        for (FoodQueue[] queue : queues) {
            totalCustomers += queue.length;
        }
        return totalCustomers == getCurrent_TotalCustomers();   // check the total number of customers are equal/not  to the current number of customers in the cashiers
    }
    static int getCurrent_TotalCustomers() {                    // When calling this method it checks the number of customers in the current cashiers
        int current_TotalCustomers = 0;
        for (FoodQueue[] queue : queues) {
            for (int j = 0; j < queue.length; j++) {
                if (queue[j] != null) {                         // Checks to see if the customer is present or not with default value
                    current_TotalCustomers++;
                }
            }
        }
        return current_TotalCustomers;
    }
    static void emptySpot(Customer customer) {                          // Find cashiers with minimum length and empty spots within that cashier
        int empty_Spot = -1;                                            // Iterate over the array of customers for the current cashier to check default value/null
        for (int i = 0; i < queues[currentCashierIndex].length; i++) {
            if (queues[currentCashierIndex][i] == null) {
                empty_Spot = i;
                break;
            }
        }

        if (empty_Spot == -1) {
            currentCashierIndex = (currentCashierIndex + 1) % queues.length;                // Move to the next cashier and wrap around the array ,no out of bounds
            if (currentCashierIndex == 0) {                                                 // If all cashiers are full ,Display message
                System.out.println("Error: No empty spot found in any cashier.");
                return;
            }
            for (int c = 0; c < queues.length; c++) {                                      // Find an empty spot in any cashier
                for (int i = 0; i < queues[c].length; i++) {
                    if (queues[c][i] == null) {
                        empty_Spot = i;
                        currentCashierIndex = c;
                        break;
                    }
                }
                if (empty_Spot != -1) {
                    break;
                }
            }
        }
        if (empty_Spot == -1) {                                                            // Checks all spots are empty/not
            System.out.println("Error: No empty spots");
            return;
        }
        queues[currentCashierIndex][empty_Spot] = new FoodQueue(customer.getFullName());            // Change object type
        System.out.println("Customer " + customer.getFullName().toUpperCase() + " has been added to Cashier " + (currentCashierIndex + 1)+ ": position "+ (empty_Spot+1)+" ,Burger Amount: "+customer.getBurgersRequired());

        currentCashierIndex = (currentCashierIndex + 1) % queues.length;          // Increment to next cashier and wrap around the array(no out of bounds)

        calculate_BurgerStock(customer);
    }
    static void calculate_BurgerStock(Customer customer) {
        currentBurger_Stock -= customer.getBurgersRequired();
        int remainingStock = currentBurger_Stock;                                // Calculate remaining stock
        if (remainingStock <= STOCK_WARNING) {
            if (remainingStock < 0) {
                int decreaseStock = customer.getBurgersRequired() + remainingStock;   // Decrement the exceeding stock
                customer.setBurgersRequired(decreaseStock);
                currentBurger_Stock = 0;
                System.out.println("No stock available!!!!");
            } else {
                System.out.println("Warning: Low stock! Remaining stock is " + remainingStock);
            }
        }
    }
    private static int getIndex(int cashierNumber,int spotNumber){                      // Index of a customer that corresponding cashiers array
        int index = -1;
        if(cashierNumber== 1 && spotNumber== 1 ) {
            index = 0;
        }else if(cashierNumber== 1 && spotNumber== 2 ){
            index = 3;
        }else if (cashierNumber== 2 && spotNumber== 1 ) {
            index = 1;
        }else if (cashierNumber== 2 && spotNumber== 2 ) {
            index = 4;
        }else if (cashierNumber== 2 && spotNumber== 3 ) {
            index = 6;
        }else if (cashierNumber== 3 && spotNumber== 1 ) {
            index = 2;
        }else if (cashierNumber== 3 && spotNumber== 2 ) {
            index = 5;
        }else if (cashierNumber== 3 && spotNumber== 3 ) {
            index = 7;
        }else if (cashierNumber== 3 && spotNumber== 4 ) {
            index = 8;
        }else if (cashierNumber== 3 && spotNumber== 5) {
            index = 9;
        }
        return index;
    }
    private static void swapping(int cashierNumber, int spotNumber) {
        int i = 0;
        int indexToRemove = getIndex(cashierNumber, (spotNumber + i));
        int swappingNumber = getIndex(cashierNumber, (spotNumber + i + 1));

        while (customers[indexToRemove] == null && customers[swappingNumber] != null) {
            Customer temp = customers[indexToRemove];
            customers[indexToRemove] = customers[swappingNumber];
            customers[swappingNumber] = temp;
            if ((cashierNumber == 1 && (spotNumber + i + 1)== 2)||(cashierNumber == 2 && (spotNumber + i + 1)== 3)||(cashierNumber == 3 && (spotNumber + i + 1) == 5) ) {
                break;                                             // Exit the loop if swappingNumber exceeds array bounds
            }
            i++;
            indexToRemove = getIndex(cashierNumber, (spotNumber + i));
            swappingNumber = getIndex(cashierNumber, (spotNumber + i + 1));
        }
    }
    private static void swapping(int cashierNumber, int spotNumber,Customer waitListcustomer) {
        int i = 0;
        int indexToRemove = getIndex(cashierNumber, (spotNumber + i));
        int swappingNumber = getIndex(cashierNumber, (spotNumber + i + 1));

        while (customers[indexToRemove] == null && customers[swappingNumber] != null) {
            Customer temp = customers[indexToRemove];
            customers[indexToRemove] = customers[swappingNumber];
            customers[swappingNumber] = temp;
            if ((cashierNumber == 1 && (spotNumber + i + 1)== 2)||(cashierNumber == 2 && (spotNumber + i + 1)== 3)||(cashierNumber == 3 && (spotNumber + i + 1) == 5) ) {
                break;                                             // Exit the loop if swappingNumber exceeds array bounds
            }
            i++;
            indexToRemove = getIndex(cashierNumber, (spotNumber + i));
            swappingNumber = getIndex(cashierNumber, (spotNumber + i + 1));
        }
        if (customers[swappingNumber]== null && waitListcustomer != null){
            customers[swappingNumber] = waitListcustomer;
        }
    }
    private static void shift_Queues() {                                                         // This method is shifting customers in cashiers 2d array if any empty spots are in front of them
        for (int i = 0; i < queues.length; i++) {
            for (int j = 0; j < queues[i].length - 1; j++) {
                if (queues[i][j] == null && queues[i][j + 1] != null) {
                    queues[i][j] = queues[i][j + 1];
                    queues[i][j + 1] = null;
                }
            }
        }
    }
    private static void shift_Queues(Customer customer) {                                                         // This method is shifting customers in cashiers 2d array if any empty spots are in front of them
        for (int i = 0; i < queues.length; i++) {
            for (int j = 0; j < queues[i].length - 1; j++) {
                if (queues[i][j] == null && queues[i][j + 1] != null) {
                    queues[i][j] = queues[i][j + 1];
                    queues[i][j + 1] = null;
                    if (customer != null ){
                        queues[i][j + 1] = new FoodQueue(customer.getFullName());
                    }
                }
            }
        }
    }
    static Customer[] store_Customers() {                                                   // This method is for store all names of the customers
        int n = customers.length;
        Customer[] store_Customers = new Customer[n];
        System.arraycopy(customers, 0, store_Customers, 0, n);
        for (int i = 0; i < n - 1; i++) {                                                   // Bubble Sort for compare customer names
            for (int j = 0; j < (n - i) - 1; j++) {
                if (store_Customers[j] != null && store_Customers[j + 1] != null) {
                    Customer customer1 = store_Customers[j];
                    Customer customer2 = store_Customers[j + 1];
                    if (customer1.getFullName().compareTo(customer2.getFullName()) > 0) {   // Swap elements in continuity elements if elements are less /greater than in each
                        Customer temp = store_Customers[j];
                        store_Customers[j] = store_Customers[j + 1];
                        store_Customers[j + 1] = temp;
                    }
                }
            }
        }
        return store_Customers;
    }
    static boolean listIsFull() {                                         // Check for waiting list full
        return count == LIST_SIZE;
    }
    static boolean listIsEmpty() {                                        // Check for waiting list empty
        return count == 0;
    }
    static void addingToList(Customer customer) {
        if (!listIsFull()) {
            rear = (rear + 1) % LIST_SIZE;                                // jump to next position
            waitingList[rear] = customer;
            count++;
            System.out.println(customer.getFullName() +" added to the waiting list.");
        } else {
            System.out.println("Waiting List is full. Customer cannot be added.");
        }
    }
    static Customer removingFromList() {
        if (!listIsEmpty()) {
            Customer customer = waitingList[front];
            waitingList[front] = null;
            front = (front + 1) % LIST_SIZE;
            count--;
            System.out.println(customer.getFullName() +" removed from the waiting list.");
            return customer;
        } else {
            System.out.println("Waiting List is empty.");
            return null;
        }
    }
}
