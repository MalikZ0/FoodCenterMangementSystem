// P.G.M.D.KUMARA 
package org.fc.ClassVer_;

public class Customer {
    private final String FIRST_NAME;
    private final String SECOND_NAME;
    private int BURGERS_AMOUNT;

    public Customer(String firstName, String secondName, int burgersAmount) {
        this.FIRST_NAME = firstName;
        this.SECOND_NAME = secondName;
        this.BURGERS_AMOUNT = burgersAmount;
    }
    public String getFullName() {
        return FIRST_NAME + " " + SECOND_NAME;
    }

    public int getBurgersRequired() {
        return BURGERS_AMOUNT;
    }
    public void setBurgersRequired(int burgersAmount){
        this.BURGERS_AMOUNT = burgersAmount;
    }
    public String toString() {
        return "Customer [Full name=" + FIRST_NAME +" "+  SECOND_NAME +", Burger amount=" +BURGERS_AMOUNT+ "]";
    }
}
