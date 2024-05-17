// P.G.M.D.KUMARA 
package org.fc.gui;

import java.util.Scanner;

public class ClassVersion extends GUIApplication {
    static boolean exit = false;                      // Displays the program menu and waits for user input until the exit is set to true
    public static void main(String[] args) {
        //FoodQueue.Queues();
        while (!exit) {                                 // For iterate until user input will set exit to true
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
            System.out.println("|  110 or IFQ: View Income of Each Queue.                    |");
            System.out.println("|  111 or VWL: View Waiting List.                            |");
            System.out.println("|  112 or GUI: Launch the Gui application.                   |");
            System.out.println("|  999 or EXT: Exit the Program.                             |");
            System.out.println("|------------------------------------------------------------|");
            System.out.println("");
            Scanner userInput_1 = new Scanner(System.in);
            System.out.print("Enter the option's id number or keyword here:");
            switch (userInput_1.next().toUpperCase()) {           // Get user input and matches the corresponding switch statements
                case "100":
                case "VFQ":
                    FoodQueue.ViewAllQueues();
                    break;
                case "101":
                case "VEQ":
                    FoodQueue.ViewEmptyQueues();
                    break;
                case "102":
                case "ACQ":
                    FoodQueue.AddCustomer();
                    break;
                case "103":
                case "RCQ":
                    FoodQueue.RemoveCustomer();
                    break;
                case "104":
                case "PCQ":
                    FoodQueue.RemoveServedCustomer();
                    break;
                case "105":
                case "VCS":
                    FoodQueue.ViewCustomers();
                    break;
                case "106":
                case "SPD":
                    FoodQueue.StoreProgramIntoFile();
                    break;
                case "107":
                case "LPD":
                    FoodQueue.LoadProgramFromFile();
                    break;
                case "108":
                case "STK":
                    FoodQueue.ViewBurgerStock();
                    break;
                case "109":
                case "AFS":
                    FoodQueue.AddBurgers();
                    break;
                case "110":
                case "IFQ":
                    FoodQueue.IncomeInEachQueues();
                    break;
                case "111":
                case "VWL":
                    FoodQueue.ViewWaitingList();
                    break;
                case "112", "GUI":
                    GUIApplication.launch() ;
                    break;
                case "999":
                case "EXT":
                    exit = true;                                        // exit case will break out of the main method's loop, which ends the program.
                    break;
                default:
                    System.out.println("Input is Wrong!: Check again");

            }
        }
    }
}
