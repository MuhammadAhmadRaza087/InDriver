import java.io.Serializable;

public abstract class Vehicle implements Serializable {
    private String registrationNo;
    private String company;
    private String model;
    private String color;

    public Vehicle() {}

    public Vehicle(String registrationNo, String company, String model, String color) {
        this.registrationNo = registrationNo;
        this.company = company;
        this.model = model;
        this.color = color;
    }

    public String getRegistrationNo() {
        return registrationNo;
    }

    public void setRegistrationNo(String registrationNo) {
        this.registrationNo = registrationNo;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean equals(Vehicle v) {
        return this.getRegistrationNo().equals(v.getRegistrationNo()) &&
                this.getCompany().equals(v.getCompany()) && this.getModel().equals(v.getModel()) &&
                this.getColor().equals(v.getColor());
    }

    @Override
    public String toString() {
        return "Registration No: " + registrationNo + ", Company: " + company +
                ", Model: " + model + ", Color: " + color;
    }
}
