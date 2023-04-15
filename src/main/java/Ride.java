import java.io.Serializable;

public class Ride implements Serializable {

    private String customerId;
    private String driverId;
    private String pickup;
    private String drop;
    private int amount;
    private double distanceKm;
    private Date date;

    public Ride() {}

    public Ride(String pickup, String drop, int amount, double distanceKm, Date date) {
        this.pickup = pickup;
        this.drop = drop;
        this.amount = amount;
        this.distanceKm = distanceKm;
        this.date = date;
        this.customerId = "";
        this.driverId = "";
    }

    public String getPickup() {
        return pickup;
    }

    public void setPickup(String pickup) {
        this.pickup = pickup;
    }

    public String getDrop() {
        return drop;
    }

    public void setDrop(String drop) {
        this.drop = drop;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getDistanceKm() {
        return distanceKm;
    }

    public void setDistanceKm(double distanceKm) {
        this.distanceKm = distanceKm;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int adminAmount() {
        return amount * 10 / 100;
    }

    public int driverAmount() {
        return amount * 90 / 100;
    }

    public String toString() {
        return "Customer: " + customerId + ", Pickup Point: " + pickup + ", Drop Point: " + drop + ", Amount: " + amount +
                ", Distance: " + distanceKm + "km" + ", Date: " + date.toString();
    }
}
