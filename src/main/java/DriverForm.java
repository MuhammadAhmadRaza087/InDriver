import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class DriverForm extends JFrame {
    JPanel mainPanel, leftGrid, centerGrid, dashGrid, balanceGrid, totalGrid, grid1, flow1, flow2;
    JLabel heading, dashboard, balanceL, totalL, approvalsL;
    JButton displayAll, delete, approve, logout, updateAccount, cancelRide, withDrawBalance;
    JList list;
    DefaultListModel model;
    FileClass fileClass = new FileClass();

    public DriverForm(Driver driver) {
        ImageIcon image  = new ImageIcon("images.jpg");
        setIconImage(image.getImage());
        mainPanel = new JPanel(new BorderLayout());
        leftGrid = new JPanel(new GridLayout(7, 1));
        centerGrid = new JPanel(new BorderLayout());
        balanceGrid = new JPanel(new GridLayout(1, 1));
        totalGrid = new JPanel(new GridLayout(1, 1));
        dashGrid = new JPanel(new GridLayout(1, 2));
        grid1 = new JPanel(new BorderLayout());
        flow1 = new JPanel(new FlowLayout());
        flow2 = new JPanel(new FlowLayout());

        leftGrid.setBackground(Color.CYAN);
        centerGrid.setBackground(Color.WHITE);
        balanceGrid.setBackground(Color.BLUE);
        totalGrid.setBackground(Color.GREEN);
        grid1.setBackground(Color.BLACK);
        flow1.setBackground(Color.BLACK);
        flow2.setBackground(Color.BLACK);

        heading = new JLabel("IN DRIVER DRIVER");
        heading.setFont(new Font("Serif", Font.BOLD, 24));

        dashboard = new JLabel("DASHBOARD " + driver.getName() + "   Rating: " + driver.getRating());
        dashboard.setFont(new Font("Serif", Font.BOLD, 24));

        totalL = new JLabel("Total Income: " + driver.getTotalEarning());
        totalL.setFont(new Font("Serif", Font.BOLD, 20));
        totalL.setForeground(Color.WHITE);

        balanceL = new JLabel("Balance: " + driver.getBalance());
        balanceL.setFont(new Font("Serif", Font.BOLD, 20));
        balanceL.setForeground(Color.WHITE);

        approvalsL = new JLabel("RIDE REQUEST");
        approvalsL.setFont(new Font("Serif", Font.BOLD, 18));
        approvalsL.setForeground(Color.WHITE);

        displayAll = new JButton("Display All Rides");
        updateAccount = new JButton("Update Account");
        withDrawBalance = new JButton("With Draw Balance");
        delete = new JButton("Delete Account");
        cancelRide = new JButton("Complete Ride");
        approve = new JButton("Accept");
        logout = new JButton("Logout");

        MyActionListener actionListener = new MyActionListener(driver);
        displayAll.addActionListener(actionListener);
        updateAccount.addActionListener(actionListener);
        cancelRide.addActionListener(actionListener);
        delete.addActionListener(actionListener);
        approve.addActionListener(actionListener);
        logout.addActionListener(actionListener);
        withDrawBalance.addActionListener(actionListener);

        list = new JList();
        model = new DefaultListModel();
        if (!driver.isBooked()) {
            ArrayList<Ride> rides = fileClass.readRides();
            for (Ride r : rides) {
                model.addElement(r);
            }
            list.setModel(model);
            list.setCellRenderer(new MyListCellRenderer());
        }
        leftGrid.add(heading);
        leftGrid.add(displayAll);
        leftGrid.add(withDrawBalance);
        leftGrid.add(cancelRide);
        leftGrid.add(updateAccount);
        leftGrid.add(delete);
        leftGrid.add(logout);

        balanceGrid.add(balanceL);
        totalGrid.add(totalL);

        dashGrid.add(balanceGrid);
        dashGrid.add(totalGrid);

        flow1.add((approvalsL));
        flow2.add(approve);

        grid1.add(flow1, BorderLayout.NORTH);
        grid1.add(new JScrollPane(list), BorderLayout.CENTER);
        grid1.add(flow2, BorderLayout.SOUTH);

        centerGrid.add(dashboard, BorderLayout.NORTH);
        centerGrid.add(dashGrid, BorderLayout.WEST);
        centerGrid.add(grid1, BorderLayout.CENTER);

        mainPanel.add(leftGrid, BorderLayout.WEST);
        mainPanel.add(centerGrid, BorderLayout.CENTER);

        add(mainPanel);

        setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        setVisible(true);

    }

    class MyActionListener implements ActionListener {
        Driver driver;
        FileClass fileClass = new FileClass();

        MyActionListener(Driver driver){
            this.driver = driver;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Display All Rides")) {
                DisplayAllRidesDriver displayAllRidesDriver = new DisplayAllRidesDriver(driver);
                dispose();
            } else if (e.getActionCommand().equals("Update Account")) {
                UpdateDriverFormSelf updateDriverFormSelf = new UpdateDriverFormSelf(driver);
                dispose();
            } else if (e.getActionCommand().equals("With Draw Balance")) {
                String amount = JOptionPane.showInputDialog(new JFrame(), "Enter Amount to With Draw");
                if (!Validator.isValidAmount(amount)) {
                    JOptionPane.showMessageDialog(new JFrame(), "INVALID Amount");
                    return;
                }
                int amountInt = Integer.parseInt(amount);
                if (amountInt > driver.getBalance()) {
                    JOptionPane.showMessageDialog(new JFrame(), "NOT Enough Balance");
                    return;
                }
                ArrayList<Driver> drivers = fileClass.readDrivers();
                for (Driver d:drivers) {
                    if (d.getUsername().equals(driver.getUsername())) {
                        d.setBalance(d.getBalance() - amountInt);
                        driver.setBalance(driver.getBalance() - amountInt);
                    }
                }
                fileClass.update(drivers, "asd");
                DriverForm driverForm = new DriverForm(driver);
                dispose();
            } else if (e.getActionCommand().equals("Complete Ride")) {
                if (!driver.isBooked()) {
                    JOptionPane.showMessageDialog(new JFrame(), "NO Ride");
                    return;
                }
                ArrayList<Driver> drivers = fileClass.readDrivers();
                for (Driver d:drivers) {
                    if (d.getUsername().equals(driver.getUsername())) {
                        d.setBooked(false);
                        driver.setBooked(false);
                    }
                }
                fileClass.update(drivers, "asd");
                int index = driver.getRides().size() - 1;
                String customer = driver.getRides().get(index).getCustomerId();
                ArrayList<Customer> customers = fileClass.readCustomers();
                for (Customer c:customers) {
                    if (c.getUsername().equals(customer)) {
                        c.setRated(true);
                        c.setBooked(false);
                    }
                }
                fileClass.update(customers, true);
                JOptionPane.showMessageDialog(new JFrame(), "Ride Completed");
                DriverForm driverForm = new DriverForm(driver);
                dispose();
            } else if (e.getActionCommand().equals("Delete Account")) {
                int result = JOptionPane.showConfirmDialog(new JFrame(),
                        "Are you sure to Delete Your ACCOUNT?",
                        "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (result == JOptionPane.YES_OPTION) {
                    ArrayList<Driver> drivers = fileClass.readDrivers();
                    for (int i = 0; i < drivers.size(); i++) {
                        if (drivers.get(i).getUsername().equals(driver.getUsername())) {
                            drivers.remove(i);
                        }
                    }
                    fileClass.update(drivers, "asd");
                    LoginForm loginForm = new LoginForm();
                    dispose();
                }
            } else if (e.getActionCommand().equals("Logout")) {
                LoginForm loginForm = new LoginForm();
                dispose();
            } else if (e.getActionCommand().equals("Accept")) {
                if (driver.isBooked()) {
                    JOptionPane.showMessageDialog(new JFrame(), "Already Booked");
                    return;
                }
                int index = list.getSelectedIndex();
                if (index == -1) {
                    JOptionPane.showMessageDialog(new JFrame(), "Please Select a Request");
                    return;
                } else {
                    Ride ride = (Ride) list.getSelectedValue();
                    String customer = ride.getCustomerId();
                    ride.setDriverId(driver.getUsername());
                    ArrayList<Driver> drivers = fileClass.readDrivers();
                    for (Driver d:drivers) {
                        if (d.getUsername().equals(driver.getUsername())) {
                            d.setBooked(true);
                            d.setBalance(d.getBalance() + (ride.getAmount() * 90 / 100));
                            d.setTotalEarning(d.getTotalEarning() + (ride.getAmount() * 90 / 100));
                            d.addRide(ride);
                            driver.addRide(ride);
                            driver.setBooked(true);
                            driver.setBalance(driver.getBalance() + (ride.getAmount() * 90 / 100));
                            driver.setTotalEarning(driver.getTotalEarning() + (ride.getAmount() * 90 / 100));
                        }
                    }
                    Admin admin = fileClass.readAdmin();
                    admin.setIncome(admin.getIncome() + (ride.getAmount() * 10 / 100));
                    admin.setTotalEarned(admin.getTotalEarned() + (ride.getAmount() * 10 / 100));
                    fileClass.writeFile(admin);
                    fileClass.update(drivers, "asd");
                    ArrayList<Customer> customers = fileClass.readCustomers();
                    for (Customer c:customers) {
                        if (c.getUsername().equals(customer)) {
                            c.addRide(ride);
                        }
                    }
                    ArrayList<Ride> rides = fileClass.readRides();
                    for (int i = 0; i < rides.size(); i++) {
                        if (rides.get(i).getCustomerId().equals(customer)) {
                            rides.remove(i);
                        }
                    }
                    if (rides.size() != 0) {
                        fileClass.addRide(rides);
                    } else {
                        File file = new File("Rides.ser");
                        file.delete();
                    }
                    fileClass.update(customers, true);
                    DriverForm driverForm = new DriverForm(driver);
                    dispose();
                }
            }
        }
    }

    class MyListCellRenderer extends DefaultListCellRenderer {

        @Override
        public Component getListCellRendererComponent(
                JList list, Object value, int index,
                boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            Ride label = (Ride) value;
            String user = label.getCustomerId();
            String from = label.getPickup();
            String to = label.getDrop();
            int amount = label.getAmount();
            double km = label.getDistanceKm();
            String date = label.getDate().toString();
            String labelText = "<html>" + "Customer: " + user + ", From: " + from + ", to: " + to + ", Amount: " + amount +
                    "<br/>distance(km): " + km + "<br/>Date" + date;
            setText(labelText);
            return this;
        }
    }
}
