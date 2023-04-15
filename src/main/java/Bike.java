import java.io.Serializable;

public class Bike extends Vehicle implements Serializable {
    private boolean deluxe;

    public Bike() {

    }

    public Bike(String registrationNo, String company, String model, String color, boolean deluxe) {
        super(registrationNo, company, model, color);
        this.deluxe = deluxe;
    }

    public boolean isDeluxe() {
        return deluxe;
    }

    public void setDeluxe(boolean deluxe) {
        this.deluxe = deluxe;
    }

    @Override
    public boolean equals(Vehicle v) {
        try {
            Bike c = (Bike) v;
            return super.equals(v) && this.deluxe == c.deluxe;
        } catch (Exception e){
            return false;
        }
    }

    public String toString() {
        String deluxeStr = "NO";
        if (deluxe) {
            deluxeStr = "YES";
        }
        return super.toString() + ", Deluxe: " + deluxeStr;
    }
}
