import java.io.Serializable;
import java.util.ArrayList;

public class Driver extends Person  implements Serializable {
    private String licenceNo;
    private Car car;
    private int totalEarning;
    private int balance;
    private int noOfRatings;
    private double rating;
    private boolean booked;
    private ArrayList<Ride> rides;

    public Driver() {}

    public Driver(String name, String username, String password, String phoneNo, String cnic, String licenceNo, Car car) {
        super(name, username, password, phoneNo, cnic);
        this.licenceNo = licenceNo;
        this.car = car;
        rides = new ArrayList<>();
    }

    public String getLicenceNo() {
        return licenceNo;
    }

    public void setLicenceNo(String licenceNo) {
        this.licenceNo = licenceNo;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
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

    public int getNoOfRatings() {
        return noOfRatings;
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

    public boolean equals(Driver d) {
        return super.equals(d) && this.licenceNo.equals(d.getLicenceNo())
                && this.car.equals(d.getCar());
    }

    @Override
    public String toString() {
        return super.toString() + ", LicenceNo: " + licenceNo + ", "
                + "Balance: " + balance + "Total Earning: " + totalEarning + car.toString() +
                ", Rating: " + rating;
    }
}
