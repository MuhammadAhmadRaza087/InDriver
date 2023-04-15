import java.io.Serializable;
import java.util.ArrayList;

public class Rider extends Person  implements Serializable {
    private String licenceNo;
    private Bike bike;
    private int totalEarning;
    private int balance;
    private int noOfRatings;
    private double rating;
    private ArrayList<Ride> rides;
    private boolean booked;

    public Rider() {}

    public Rider(String name, String username, String password, String phoneNo, String cnic, String licenceNo, Bike bike) {
        super(name, username, password, phoneNo, cnic);
        this.licenceNo = licenceNo;
        this.bike = bike;
        rides = new ArrayList<>();
    }

    public String getLicenceNo() {
        return licenceNo;
    }

    public void setLicenceNo(String licenceNo) {
        this.licenceNo = licenceNo;
    }

    public Bike getBike() {
        return bike;
    }

    public void setBike(Bike bike) {
        this.bike = bike;
    }

    public int getTotalEarning() {
        return totalEarning;
    }

    public void setTotalEarning(int totalEarning) {
        this.totalEarning = totalEarning;
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

    public double getRating() {
        return rating;
    }

    public void setRating(int stars) {
        rating = Math.round(((rating * noOfRatings + stars) / (++noOfRatings)) * 10) / 10.0;
    }

    public ArrayList<Ride> getRides() {
        return rides;
    }

    public void addRide(Ride ride) {
        rides.add(ride);
    }

    public boolean equals(Rider r) {
        return super.equals(r) && this.licenceNo.equals(r.getLicenceNo()) &&
                this.bike.equals(r.getBike());
    }

    @Override
    public String toString() {
        return super.toString() + ", LicenceNo: " + licenceNo + ", "
                + "Balance: " + balance + "Total Earning: " + totalEarning + bike.toString() +
                ", Rating: " + rating;
    }
}
