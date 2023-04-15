import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CustomerForm extends JFrame {
    JPanel mainPanel, leftGrid, centerGrid, dashGrid, balanceGrid, totalGrid, grid1, flow1, flow2;
    JLabel heading, dashboard, balanceL, totalL, approvalsL;
    JButton displayAll, delete, approve, logout, disapprove, updateAccount, bookRide, addBalance,
    requestBalance;
    JList list;
    FileClass fileClass = new FileClass();

    public CustomerForm(Customer customer) {
        ImageIcon image  = new ImageIcon("images.jpg");
        setIconImage(image.getImage());
        mainPanel = new JPanel(new BorderLayout());
        leftGrid = new JPanel(new GridLayout(8, 1));
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

        heading = new JLabel("IN DRIVER CUSTOMER");
        heading.setFont(new Font("Serif", Font.BOLD, 24));

        dashboard = new JLabel("DASHBOARD " + customer.getName());
        dashboard.setFont(new Font("Serif", Font.BOLD, 24));

        totalL = new JLabel("Total Spent: " + customer.getSpentAmount());
        totalL.setFont(new Font("Serif", Font.BOLD, 20));
        totalL.setForeground(Color.WHITE);

        balanceL = new JLabel("Balance: " + customer.getBalance());
        balanceL.setFont(new Font("Serif", Font.BOLD, 20));
        balanceL.setForeground(Color.WHITE);

        approvalsL = new JLabel("BALANCE REQUEST");
        approvalsL.setFont(new Font("Serif", Font.BOLD, 18));
        approvalsL.setForeground(Color.WHITE);

        displayAll = new JButton("Display All Rides");
        updateAccount = new JButton("Update Account");
        delete = new JButton("Delete Account");
        bookRide = new JButton("Book Ride");
        addBalance = new JButton("Add Balance");
        requestBalance = new JButton("Request Balance");
        approve = new JButton("Accept");
        logout = new JButton("Logout");
        disapprove = new JButton("Ignore");

        MyActionListener actionListener = new MyActionListener(customer);
        displayAll.addActionListener(actionListener);
        updateAccount.addActionListener(actionListener);
        addBalance.addActionListener(actionListener);
        requestBalance.addActionListener(actionListener);
        bookRide.addActionListener(actionListener);
        delete.addActionListener(actionListener);
        approve.addActionListener(actionListener);
        logout.addActionListener(actionListener);
        disapprove.addActionListener(actionListener);

        list = new JList(customer.getRequests().toArray(new String[0]));

        leftGrid.add(heading);
        leftGrid.add(displayAll);
        leftGrid.add(bookRide);
        leftGrid.add(updateAccount);
        leftGrid.add(addBalance);
        leftGrid.add(requestBalance);
        leftGrid.add(delete);
        leftGrid.add(logout);

        balanceGrid.add(balanceL);
        totalGrid.add(totalL);

        dashGrid.add(balanceGrid);
        dashGrid.add(totalGrid);

        flow1.add((approvalsL));
        flow2.add(approve);
        flow2.add(disapprove);

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
        if (customer.isRated()) {
            String[] comboList = {"1", "2", "3", "4", "5"};
            JComboBox comboBox = new JComboBox<>(comboList);
            JOptionPane.showMessageDialog(new JFrame(), comboBox, "Rate Last Driver",
                    JOptionPane.QUESTION_MESSAGE);
            int rating = Integer.parseInt(comboBox.getSelectedItem().toString());
            ArrayList<Customer> customers = fileClass.readCustomers();
            for (Customer c:customers) {
                if (c.getUsername().equals(customer.getUsername())) {
                    c.setRated(false);
                    customer.setRated(false);
                }
            }
            fileClass.update(customers, true);
            int index = customer.getRides().size() - 1;
            String username = customer.getRides().get(index).getDriverId();
            ArrayList<Driver> drivers = fileClass.readDrivers();
            for (Driver driver:drivers) {
                if (driver.getUsername().equals(username)) {
                    driver.setRating(rating);
                }
            }
            fileClass.update(drivers, "asd");
            ArrayList<Rider> riders = fileClass.readRiders();
            for (Rider rider:riders) {
                if (rider.getUsername().equals(username)) {
                    rider.setRating(rating);
                }
            }
            fileClass.update(riders, 12);
        }
    }

    class MyActionListener implements ActionListener {
        Customer customer;
        FileClass fileClass = new FileClass();

        MyActionListener(Customer customer){
            this.customer = customer;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Display All Rides")) {
                DisplayRidesCustomerForm displayRidesCustomerForm = new DisplayRidesCustomerForm(customer);
                dispose();
            } else if (e.getActionCommand().equals("Update Account")) {
                UpdateCustomerFormSelf updateCustomerFormSelf = new UpdateCustomerFormSelf(customer);
                dispose();
            } else if (e.getActionCommand().equals("Book Ride")) {
                JRadioButton driverR = new JRadioButton("Book Driver");
                JRadioButton riderR = new JRadioButton("Book Rider");
                ButtonGroup bg = new ButtonGroup();
                bg.add(driverR);
                bg.add(riderR);
                Object[] message = {driverR, riderR};
                int option = JOptionPane.showConfirmDialog(null, message, "Request Balance",
                        JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    if (driverR.isSelected()) {
                        if (customer.isBooked()) {
                            JOptionPane.showMessageDialog(new JFrame(), "Already Booked");
                        } else {
                            BookDriverForm bookDriverForm = new BookDriverForm(customer);
                            dispose();
                        }
                    } else if (riderR.isSelected()){
                        if (customer.isBooked()) {
                            JOptionPane.showMessageDialog(new JFrame(), "Already Booked");
                        } else {
                            BookRiderForm bookRiderForm = new BookRiderForm(customer);
                            dispose();
                        }
                    }
                }
            } else if (e.getActionCommand().equals("Request Balance")) {
                JTextField usernameF = new JTextField();
                JTextField amountF = new JTextField();
                Object[] message = {"Username to Send Request", usernameF,
                        "Amount to Request", amountF};
                int option = JOptionPane.showConfirmDialog(null, message, "Request Balance",
                        JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    if (usernameF.getText().equals(customer.getUsername())) {
                        JOptionPane.showMessageDialog(new JFrame(), "Cannot Request From Yourself");
                        return;
                    }
                    ArrayList<Customer> customers = fileClass.readCustomers();
                    boolean found = false;
                    for (Customer c:customers) {
                        if (c.getUsername().equals(usernameF.getText())) {
                            found = true;
                        }
                    }
                    if (found) {
                        try {
                            int amount = Integer.parseInt(amountF.getText());
                            if (amount <= 0) {
                                JOptionPane.showMessageDialog(new JFrame(), "Invalid Amount");
                                return;
                            }
                            for (Customer c:customers) {
                                if (c.getUsername().equals(usernameF.getText())) {
                                    c.addMessage("Username: " + customer.getUsername() + ", Amount: " + amount);
                                }
                            }
                            fileClass.update(customers,  true);
                            JOptionPane.showMessageDialog(new JFrame(), "Request Sent");
                        } catch (Exception a) {
                            JOptionPane.showMessageDialog(new JFrame(), "Invalid Amount");
                        }
                    } else {
                        JOptionPane.showMessageDialog(new JFrame(), "User not Found");
                    }
                }
            } else if (e.getActionCommand().equals("Delete Account")) {
                int result = JOptionPane.showConfirmDialog(new JFrame(),
                        "Are you sure to Delete Your ACCOUNT?",
                        "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (result == JOptionPane.YES_OPTION) {
                    ArrayList<Customer> customers = fileClass.readCustomers();
                    for (int i = 0; i < customers.size(); i++) {
                        if (customers.get(i).getUsername().equals(customer.getUsername())) {
                            customers.remove(i);
                        }
                    }
                    fileClass.update(customers, true);
                    LoginForm loginForm = new LoginForm();
                    dispose();
                }
            } else if (e.getActionCommand().equals("Add Balance")) {
                String balance = JOptionPane.showInputDialog(new JFrame(), "Enter amount to add");
                try {
                    int amount = Integer.parseInt(balance);
                    if (amount > 0) {
                        ArrayList<Customer> customers = fileClass.readCustomers();
                        for (int i = 0; i < customers.size(); i++) {
                            if (customers.get(i).getUsername().equals(customer.getUsername())) {
                                customers.get(i).setBalance(customer.getBalance() + amount);
                                customer.setBalance(customer.getBalance() + amount);
                            }
                        }
                        fileClass.update(customers, true);
                        dispose();
                        CustomerForm customerForm = new CustomerForm(customer);
                    } else {
                        JOptionPane.showMessageDialog(new JFrame(), "Invalid Amount");
                    }
                } catch (Exception a) {
                    JOptionPane.showMessageDialog(new JFrame(), "Invalid Amount");
                }
            } else if (e.getActionCommand().equals("Logout")) {
                LoginForm loginForm = new LoginForm();
                dispose();
            } else if (e.getActionCommand().equals("Accept")) {
                int index = list.getSelectedIndex();
                if (index == -1) {
                    JOptionPane.showMessageDialog(new JFrame(), "Please Select a Request");
                    return;
                } else {
                    String str = (String) list.getSelectedValue();
                    String username = str.split(",")[0].strip().split(":")[1].strip();
                    int amount = Integer.parseInt(str.split(",")[1].strip().split(":")[1].strip());
                    if (amount > customer.getBalance()) {
                        JOptionPane.showMessageDialog(new JFrame(), "Your Balance is low");
                    } else {
                        ArrayList<Customer> list1 = fileClass.readCustomers();
                        for (Customer c:list1) {
                            if (c.getUsername().equals(username)) {
                                c.setBalance(c.getBalance() + amount);
                            } else if (c.getUsername().equals(customer.getUsername())) {
                                c.setBalance(c.getBalance() - amount);
                                customer.setBalance(customer.getBalance() - amount);
                                c.getRequests().remove(index);
                                customer.getRequests().remove(index);
                            }
                        }
                        fileClass.update(list1, true);
                        JOptionPane.showMessageDialog(new JFrame(), "Balance Sent");
                        CustomerForm customerForm = new CustomerForm(customer);
                        dispose();
                    }
                }
            } else if (e.getActionCommand().equals("Ignore")) {
                int index = list.getSelectedIndex();
                if (index == -1) {
                    JOptionPane.showMessageDialog(new JFrame(), "Please Select a Request");
                    return;
                } else {
                    String str = (String) list.getSelectedValue();
                    String username = str.split(",")[0].strip().split(":")[1].strip();
                    ArrayList<Customer> list1 = fileClass.readCustomers();
                    for (Customer c:list1) {
                        if (c.getUsername().equals(customer.getUsername())) {
                            c.getRequests().remove(index);
                        }
                    }
                    fileClass.update(list1, true);
                    JOptionPane.showMessageDialog(new JFrame(), "Request Removed");
                    CustomerForm customerForm = new CustomerForm(customer);
                    dispose();
                }
            }
        }
    }
}
