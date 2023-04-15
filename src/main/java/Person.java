import java.io.Serializable;

public abstract class Person implements Serializable {
    private String name;
    private String username;
    private String phoneNo;
    private String cnic;
    private String password;

    public Person() {}

    public Person(String name, String username,  String password, String phoneNo, String cnic) {
        this.name = name;
        this.phoneNo = phoneNo;
        this.cnic = cnic;
        this.username = username;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public String getCnic() {
        return cnic;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public void setCnic(String cnic) {
        this.cnic = cnic;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean equals(Person p) {
        return this.name.equals(p.name) && this.phoneNo.equals(p.phoneNo) && this.cnic.equals(p.cnic)
                && this.password.equals(p.getPassword()) && this.username.equals(p.getUsername());
    }

    public java.lang.String toString() {
        return "Name: " + name + ", Phone No: " + phoneNo + ", CNIC: " + cnic + ", " + password +
                ", Username: " + username;
    }
}
