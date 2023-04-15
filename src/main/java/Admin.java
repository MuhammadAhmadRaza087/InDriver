import java.io.Serializable;
import java.util.ArrayList;

public class Admin extends Person implements Serializable {
    private int totalEarned;
    private int income;
    private ArrayList<Person> approvals;

    public Admin() {

    }

    public Admin(String name, String username, String password, String phoneNo, String cnic) {
        super(name, username, password, phoneNo, cnic);
        approvals = new ArrayList<>();
    }

    public int getTotalEarned() {
        return totalEarned;
    }

    public void setTotalEarned(int totalEarned) {
        this.totalEarned = totalEarned;
    }

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public void addApproval(Person p) {
        approvals.add(p);
    }

    public void removeApproval(Person p) {
        approvals.remove(p);
    }

    public ArrayList<Person> getApprovals() {
        return approvals;
    }

    public String toString() {
        return super.toString() + ", Total Income: " + income + ", Income Present: " + income;
    }
}
