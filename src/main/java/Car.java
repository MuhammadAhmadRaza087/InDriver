import java.io.Serializable;

public class Car extends Vehicle implements Serializable {
    private boolean ac;
    private boolean trunk;

    public Car(String registrationNo, String company, String model, String color, boolean ac, boolean trunk) {
        super(registrationNo, company, model, color);
        this.ac = ac;
        this.trunk = trunk;
    }

    public boolean hasAc() {
        return ac;
    }

    public void setAc(boolean ac) {
        this.ac = ac;
    }

    public boolean hasTrunk() {
        return trunk;
    }

    public void setTrunk(boolean trunk) {
        this.trunk = trunk;
    }

    @Override
    public boolean equals(Vehicle v) {
        try {
            Car c = (Car) v;
            return super.equals(v) && this.ac == c.ac && this.trunk == c.trunk;
        } catch (Exception e){
            return false;
        }
    }

    @Override
    public String toString() {
        String acstr = "NO", trunkstr = "NO";
        if (ac) {
            acstr = "YES";
        }
        if (trunk){
            trunkstr = "YES";
        }
        return super.toString() + ", AC: " + acstr + ", Trunk: " +trunkstr;
    }
}
