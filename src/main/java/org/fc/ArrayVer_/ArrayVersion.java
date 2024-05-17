// P.G.M.D.KUMARA -20222145  -W1985546
package org.fc.ArrayVer_;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ArrayVersion {
    static boolean exit = false;                      // Displays the program menu and waits for user input until the exit is set to true

    private static String[][] cashiers;                    // Declaring of the cashiers array, which represents the queues for customers

    static {
        cashiers = new String[3][];                       // 2D array with 3 rows (3 cashiers) ---jagged array---
        cashiers[0] = new String[2];
        cashiers[1] = new String[3];
        cashiers[2] = new String[5];
    }

    static final int[] MAXIMUM_CUSTOMERS = new int[]{2, 3, 5};    // Elements in this array correspond to the maximum number of customers for each cashier in the cashiers array.
    private static int currentBurger_Stock = 50;                  // Food Center will have exactly 50 burgers in stock from beginning.Later it can be changed.
    private static final int STOCK_WARNING = 10;                  // Constant value of remaining stock
    private static final int BURGERS_PER_CUSTOMER = 5;            // Constant value of burgers that served per customer
    static int currentCashierIndex = 0;                           // First of the cashier queue.Later it can be incremented

    public static void main(String[] args) {
        while (!exit) {                                            // For iterate until user input will set exit to true
            System.out.println("");
            System.out.println("---------------$$$ FoodiesFaveFoodCenter $$$------------------");
            System.out.println("");
            System.out.println("|------------------------------------------------------------|");
            System.out.println("|                      MENU OPTIONS                          |");
            System.out.println("|------------------------------------------------------------|");
            System.out.println("|  100 or VFQ: View all Queues.                              |");
            System.out.println("|  101 or VEQ: View all Empty Queues.                        |");
            System.out.println("|  102 or ACQ: Add customer to a Queue.                      |");
            System.out.println("|  103 or RCQ: Remove a customer from a Queue.               |");
            System.out.println("|  104 or PCQ: Remove a served customer.                     |");
            System.out.println("|  105 or VCS: View Customers Sorted in alphabetical order.  |");
            System.out.println("|  106 or SPD: Store Program Data into file.                 |");
            System.out.println("|  107 or LPD: Load Program Data from file.                  |");
            System.out.println("|  108 or STK: View Remaining burgers Stock.                 |");
            System.out.println("|  109 or AFS: Add burgers to Stock.                         |");
            System.out.println("|  999 or EXT: Exit the Program.                             |");
            System.out.println("|------------------------------------------------------------|");
            System.out.println("");
            MenuOptions();                                     // MenuOptions method has all methods related to each task
        }
    }

    // ------------Main methods of the program -------------------------------------------------------------------------
    static void MenuOptions() {

        Scanner userInput_1 = new Scanner(System.in);
        System.out.print("Enter the option's id number or keyword here:");
        switch (userInput_1.next().toUpperCase()) {           // Get user input and matches the corresponding switch statements
            case "100":
            case "VFQ":
                ViewAllQueues();
                break;
            case "101":
            case "VEQ":
                ViewEmptyQueues();
                break;
            case "102":
            case "ACQ":
                AddCustomer();
                break;
            case "103":
            case "RCQ":
                RemoveCustomer();
                break;
            case "104":
            case "PCQ":
                RemoveServedCustomer();
                break;
            case "105":
            case "VCS":
                ViewCustomers();
                break;
            case "106":
            case "SPD":
                StoreProgramIntoFile();
                break;
            case "107":
            case "LPD":
                LoadProgramFromFile();
                break;
            case "108":
            case "STK":
                ViewBurgerStock();
                break;
            case "109":
            case "AFS":
                AddBurgers();
                break;
            case "999":
            case "EXT":
                exit = true;                                        // exit case will break out of the main method's loop, which ends the program.
                break;
            default:
                System.out.println("Input is Wrong!: Check again");

        }
    }

    static void ViewAllQueues() {
        System.out.println("*******************");
        System.out.println("*     Cashiers    *");
        System.out.println("*******************");
        int longest_QueueLine = 5;                                 // Get maximum rows for the number of cashiers
        for (int i = 0; i < longest_QueueLine; i++) {              // Iterates over maximum number of rows
            for (int j = 0; j < MAXIMUM_CUSTOMERS.length; j++) {
                if (i < cashiers[j].length) {                      // 'j' determines the rows, whereas 'i' determines the columns.
                    if (cashiers[j][i] != null) {
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

        for (int i = 0; i < cashiers.length; i++) {               // Iterates all columns of each cashier
            System.out.println("Cashier " + (i + 1) + ":");
            for (int j = 0; j < cashiers[i].length; j++) {        // Iterates maximum number of spots in each cashier
                if (cashiers[i][j] == null) {
                    System.out.println("Position " + (j + 1) + ": Empty");
                } else {
                    System.out.println("Position " + (j + 1) + ": Full");
                }
            }
            System.out.println();
        }
    }

    static void AddCustomer() {
        String name;
        int cashier_number;
        Scanner userInput_2 = new Scanner(System.in);
        while (true) {
            System.out.print("Enter Name: ");                               // Get user input for adding the customer
            name = userInput_2.next();
            while (true) {                                                  // Validator for check user's input
                System.out.print("Enter Queue(1-3): ");
                try {
                    cashier_number = userInput_2.nextInt();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter an integer value.");
                    userInput_2.nextLine();                                // Clear the invalid input from the scanner
                }
            }
            boolean contains_NumbersOrSymbols = false;
            for (char c : name.toCharArray()) {                             // Iterates each character in user input name
                if (!((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z'))) {  // Check each character is letter or not
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
        empty_Spots(name, cashier_number);
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
        if (cashierNumber < 1 || cashierNumber > cashiers.length) {
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
                userInput_3.nextLine();                                 // Clear the invalid input from the scanner
            }
        }
        if (spotNumber < 1 || spotNumber > cashiers[cashierNumber - 1].length) {
            System.out.println("Invalid spot number.");
            return;
        }
        if (cashiers[cashierNumber - 1][spotNumber - 1] == null) {
            System.out.println("No customer found at the specified spot.");
            return;
        }
        String customerName = getName(cashierNumber - 1, spotNumber - 1);          // Get name of the customer in the user inputs
        cashiers[cashierNumber - 1][spotNumber - 1] = null;                                // Removing customer
        currentBurger_Stock += BURGERS_PER_CUSTOMER;                                       // adding burgers cause removed customer do not get burgers
        System.out.println("Customer " + customerName + " has been removed from Cashier " + cashierNumber + ":" + "Position " + spotNumber);
        shift_Queues();                                                                   // shift behind customer to front
    }

    static void RemoveServedCustomer() {                                  // Removing served customer from cashier
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
        if ((cashiers[0][0] == null) && (cashiers[1][0] == null) && (cashiers[2][0] == null)) {   // Check all three cashier spots in the front of cashiers
            System.out.println("No customer is found.");
        } else if (cashiers[cashierIndex-1][0] != null) {                                 // Removing first three spots from each cashier
            System.out.println("Served customer removed: " + getName((cashierIndex-1), 0));
            cashiers[cashierIndex-1][0] = null;
            shift_Queues();
        }
    }
    static void ViewCustomers() {
        String[] allCustomersNames = store_Customers();                   // Assigns the value of the all customers in an array of all customers names

        if (allCustomersNames.length == 0) {
            System.out.println("No customers found.");
        } else {
            System.out.println("Customers (Alphabetical Order):");
            for (String customer : allCustomersNames) {                  // Displaying each name in array
                if (customer != null) {
                    System.out.println(customer);
                }
            }
        }
    }

    static void StoreProgramIntoFile() {
        try {                                                               // Exception handling check input output errors
            FileWriter data_Writer = new FileWriter("foodQueue_Dat.txt");
            // Write cashiers data
            data_Writer.write("customer names with cashier number:");
            data_Writer.write("\n");
            for (int c = 0; c < cashiers.length; c++) {
                for (int i = 0; i < cashiers[c].length; i++) {
                    String customer = cashiers[c][i];                 // Assign each customer to corresponding cashier
                    if (customer != null) {
                        data_Writer.write("Cashier " + (c + 1) + " position " + (i + 1) + " :" + getName(c, i));  // Write cashier with index
                        data_Writer.write("\n");
                    }
                }
            }
            // Write stock value
            int remainingStock = currentBurger_Stock;
            data_Writer.write("Remaining stock:");            // Write remaining stock to the file
            data_Writer.write(Integer.toString(remainingStock));

            data_Writer.close();
            System.out.println("Program data stored to 'foodQueue_Dat.txt' file.");
        } catch (IOException e) {
            System.out.println("Error occurred while storing program data: ");
        }

    }

    static void LoadProgramFromFile() {
        try {                                                             // Exception handling check input output errors
            FileReader data_Reader = new FileReader("foodQueue_Dat.txt");
            Scanner userInput_4 = new Scanner(data_Reader);
            while (userInput_4.hasNext()) {                               // Read and display file lines
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
        int remainingStock = currentBurger_Stock;                        // Current burger stock is updated with other methods

        System.out.println("Remaining burger stock: " + remainingStock);
    }

    static void AddBurgers() {
        System.out.print("Enter the number of burgers to add: ");
        Scanner userInput_5 = new Scanner(System.in);
        int newStock;
        while (true) {                                                    // Validator for check user's input
            try {
                newStock = userInput_5.nextInt();
                break;                                                    // Exit the loop if the input is a valid integer
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer value.");
                userInput_5.nextLine();                                   // Clear the invalid input from the scanner
            }
        }
        currentBurger_Stock += newStock;                                  // Update burger stock
        System.out.println(newStock + " burgers have been added to the stock.");
    }

    // --------Helping methods of the program---------------------------------------------------------------------------
    static void addName(String name, int row, int col) {
        cashiers[row][col] = name;
    }

    static String getName(int row, int col) {
        return cashiers[row][col];
    }

    static int getCurrent_TotalCustomers() {                      // When calling this method it checks the number of customers in the current cashiers
        int current_TotalCustomers = 0;
        for (int i = 0; i < cashiers.length; i++) {
            for (int j = 0; j < cashiers[i].length; j++) {
                if (cashiers[i][j] != null) {                    // Checks to see if the customer is present or not with default value
                    current_TotalCustomers++;
                }
            }
        }
        return current_TotalCustomers;
    }

    static void empty_Spots(String name, int cashier_number) {
        if (currentBurger_Stock == 0){                                         // Check for available stock
            System.out.println("NO MORE STOCK AVAILABLE!!!!");
            System.out.println("---UPDATE THE STOCK---");
            return;
        }
        currentCashierIndex = (cashier_number - 1);
        int empty_Spot = -1;                               // Iterate over the current cashier array of customers to check default value/null
        for (int i = 0; i < cashiers[currentCashierIndex].length; i++) {
            if (cashiers[currentCashierIndex][i] == null) {
                empty_Spot = i;
                break;
            }
        }
        if (empty_Spot == -1) {                                                            // Checks all spots are empty/not
            System.out.println("Queue is full. Cannot add more customers to the cashier " + (currentCashierIndex + 1));
            return;                                                                       // doesn't execute after return
        }
        addName(name, currentCashierIndex, empty_Spot);
        System.out.println("Customer " + name + " has been added to Cashier " + (currentCashierIndex + 1) + ": position " + (empty_Spot + 1));
        calculate_BurgerStock();

    }

    static void calculate_BurgerStock() {
        currentBurger_Stock -= BURGERS_PER_CUSTOMER;
        int remainingStock = currentBurger_Stock;                                // Calculate remaining stock
        if (remainingStock <= STOCK_WARNING) {
            if (remainingStock < 0) {
                currentBurger_Stock = 0;
                System.out.println("No stock available!!!!");
            } else {
                System.out.println("Warning: Low stock! Remaining stock is " + remainingStock);
            }
        }
    }

    private static void shift_Queues_1() {                        // This method is shifting customers if any empty spots in front of them
        for (int i = 0; i < cashiers.length; i++) {
            for (int j = 0; j < cashiers[i].length - 1; j++) {
                cashiers[i][j] = cashiers[i][j + 1];            // Swap values in continuity indexes
            }
            cashiers[i][cashiers[i].length - 1] = null;
        }
    }

    private static void shift_Queues() {                        // This method is shifting customers if any empty spots in front of them
        for (int i = 0; i < cashiers.length; i++) {
            for (int j = 0; j < cashiers[i].length - 1; j++) {
                if (cashiers[i][j] == null && cashiers[i][j + 1] != null) {
                    cashiers[i][j] = cashiers[i][j + 1];
                    cashiers[i][j + 1] = null;
                }
            }
        }
    }

    private static String[] store_Customers() {                // This method is for store all names of the customers
        String[] allCustomers = new String[getCurrent_TotalCustomers()];   // Create array of all customers to store their names
        int index = 0;
        for (String[] queue : cashiers) {                      // Iterates over the all spots in each cashier
            for (String customer : queue) {
                if (customer != null) {
                    allCustomers[index] = customer;              // Store names of customers
                    index++;
                }
            }
        }
        int n = allCustomers.length;                            // Bubble Sort for compare customer names
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < (n - i) - 1; j++) {
                if (allCustomers[j] != null && allCustomers[j + 1] != null) {
                    if (allCustomers[j].compareTo(allCustomers[j + 1]) > 0) {   // Swap elements in continuity elements if elements are less /greater than in each
                        String temp = allCustomers[j];
                        allCustomers[j] = allCustomers[j + 1];
                        allCustomers[j + 1] = temp;
                    }
                }
            }
        }
        return allCustomers;
    }
}