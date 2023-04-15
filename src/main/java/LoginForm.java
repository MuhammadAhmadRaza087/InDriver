import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LoginForm extends JFrame {
    JPanel centre, top, middle, bottom1 , bottom2, inputs, radioButtons, fieldU, fieldP;
    JLabel heading, usernameL, passwordL;
    JTextField usernameF;
    JPasswordField passwordF;
    JRadioButton customer, admin, driver, rider;
    ButtonGroup group;
    JButton login, signup;

    public LoginForm() {
        ImageIcon image  = new ImageIcon("images.jpg");
        setIconImage(image.getImage());
        setSize(520, 280);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        centre = new JPanel(new BorderLayout());
        top = new JPanel(new FlowLayout());
        middle = new JPanel(new BorderLayout());
        inputs = new JPanel(new GridLayout(2, 1));
        fieldU = new JPanel(new FlowLayout());
        fieldP = new JPanel(new FlowLayout());
        radioButtons = new JPanel(new FlowLayout());
        bottom1 = new JPanel(new FlowLayout());
        bottom2 = new JPanel(new FlowLayout());

        heading = new JLabel("Login");
        heading.setFont(new Font("Serif", Font.BOLD, 24));
        usernameL = new JLabel("Username");
        passwordL = new JLabel("Password");

        usernameF = new JTextField();
        passwordF = new JPasswordField();
        usernameF.setPreferredSize(new Dimension(250, 35));
        passwordF.setPreferredSize(new Dimension(250, 35));

        customer = new JRadioButton("Customer");
        admin = new JRadioButton("Admin");
        driver = new JRadioButton("Driver");
        rider = new JRadioButton("Rider");

        group = new ButtonGroup();
        group.add(customer);
        group.add(admin);
        group.add(driver);
        group.add(rider);

        login = new JButton("Login");
        signup = new JButton("Signup");

        login.setPreferredSize(new Dimension(100, 30));
        signup.setPreferredSize(new Dimension(100, 30));

        MyActionListener actionListener = new MyActionListener();
        login.addActionListener(actionListener);
        signup.addActionListener(actionListener);

        top.add(heading);

        radioButtons.add(customer);
        radioButtons.add(driver);
        radioButtons.add(rider);
        radioButtons.add(admin);

        bottom2.add(login);
        bottom2.add(signup);

        fieldU.add(usernameL);
        fieldU.add(usernameF);
        fieldP.add(passwordL);
        fieldP.add(passwordF);

        inputs.add(fieldU);
        inputs.add(fieldP);

        middle.add(inputs, BorderLayout.CENTER);
        middle.add(radioButtons, BorderLayout.SOUTH);

        centre.add(top, BorderLayout.NORTH);
        centre.add(middle, BorderLayout.CENTER);
        centre.add(bottom2, BorderLayout.SOUTH);

        add(centre);

        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    class MyActionListener implements ActionListener {
        FileClass fileClass = new FileClass();
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Login")){
                if (admin.isSelected()){
                    Admin admin = fileClass.readAdmin();
                    if (usernameF.getText().equals(admin.getUsername()) &&
                            passwordF.getText().equals(admin.getPassword())){
                        AdminForm adminForm = new AdminForm(admin);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(new JFrame(), "Incorrect Username or Password");
                    }
                } else if (driver.isSelected()) {
                    ArrayList<Driver> drivers = fileClass.readDrivers();
                    Driver driver1 = null;
                    for (Driver d:drivers) {
                        if (d.getUsername().equals(usernameF.getText()) &&
                                d.getPassword().equals(passwordF.getText())) {
                            driver1 = d;
                            break;
                        }
                    }
                    if (driver1 != null) {
                        DriverForm customerForm = new DriverForm(driver1);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(new JFrame(), "Driver NOT FOUND");
                    }
                } else if (rider.isSelected()) {
                    ArrayList<Rider> riders = fileClass.readRiders();
                    Rider rider = null;
                    for (Rider c:riders) {
                        if (c.getUsername().equals(usernameF.getText()) &&
                                c.getPassword().equals(passwordF.getText())) {
                            rider = c;
                            break;
                        }
                    }
                    if (rider != null) {
                        RiderForm riderForm = new RiderForm(rider);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(new JFrame(), "Rider NOT FOUND");
                    }
                } else if (customer.isSelected()) {
                    ArrayList<Customer> customers = fileClass.readCustomers();
                    Customer customer = null;
                    for (Customer c:customers) {
                        if (c.getUsername().equals(usernameF.getText()) &&
                                c.getPassword().equals(passwordF.getText())) {
                            customer = c;
                            break;
                        }
                    }
                    if (customer != null) {
                        CustomerForm customerForm = new CustomerForm(customer);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(new JFrame(), "Customer NOT FOUND");
                    }
                } else {
                    JOptionPane.showMessageDialog(new JFrame(), "!Please Select 1 Option");
                }
            } else if (e.getActionCommand().equals("Signup")) {
                if (admin.isSelected()){
                    JOptionPane.showMessageDialog(new JFrame(), "!Admin Cannot Signup");
                } else if (driver.isSelected()) {
                    SignupFormDriver signupFormDriver = new SignupFormDriver();
                    dispose();
                } else if (rider.isSelected()) {
                    SignupFormRider signupFormRider = new SignupFormRider();
                    dispose();
                } else if (customer.isSelected()) {
                    SignupFormCustomer signupFormCustomer = new SignupFormCustomer();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(new JFrame(), "!Please Select 1 Option");
                }
            }
        }
    }
}
