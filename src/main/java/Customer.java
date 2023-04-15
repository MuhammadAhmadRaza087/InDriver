import java.io.Serializable;
import java.util.ArrayList;

public class Customer extends Person implements Serializable {
    private int spentAmount;
    private int balance;
    private ArrayList<Ride> rides;
    private ArrayList<String> requests;
    private boolean booked;
    private boolean rated;

    public Customer() {

    }

    public Customer(String name, String username, String password, String phoneNo, String cnic) {
        super(name, username, password, phoneNo, cnic);
        rides = new ArrayList<>();
        requests = new ArrayList<>();
        booked = false;
        rated = false;
    }

    public int getSpentAmount() {
        return spentAmount;
    }

    public void setSpentAmount(int spentAmount) {
        this.spentAmount = spentAmount;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }

    public boolean isRated() {
        return rated;
    }

    public void setRated(boolean rated) {
        this.rated = rated;
    }

    public ArrayList<Ride> getRides() {
        return rides;
    }

    public ArrayList<String> getRequests() {
        return requests;
    }

    public void addMessage(String message) {
        requests.add(message);
    }

    public void sendRequest(Customer customer, String message) {
        customer.addMessage(message);
    }

    public void addRide(Ride ride) {
        rides.add(ride);
    }

    @Override
    public boolean equals(Person p) {
        return super.equals(p);
    }

    public String toString() {
        return super.toString() + ", Total Amount Spent: " + spentAmount + ", Balance: " + balance;
    }
}
