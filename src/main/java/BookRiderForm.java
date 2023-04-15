import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

public class BookRiderForm extends JFrame {
    JPanel mainPanel, centreGrid, headingP, fromP, toP, amountP, distanceP, buttonP;
    JLabel heading, fromL, toL, amountL, distanceL;
    JTextField fromF, toF, amountF, distanceF;
    JButton book, back;

    public BookRiderForm(Customer customer){
        ImageIcon image  = new ImageIcon("images.jpg");
        setIconImage(image.getImage());
        setSize(600, 700);

        mainPanel = new JPanel(new BorderLayout());
        centreGrid = new JPanel(new GridLayout(4, 2));
        headingP = new JPanel(new FlowLayout());
        fromP = new JPanel(new FlowLayout());
        toP = new JPanel(new FlowLayout());
        amountP = new JPanel(new FlowLayout());
        distanceP = new JPanel(new FlowLayout());
        buttonP = new JPanel(new FlowLayout());

        heading = new JLabel("Ride Booking");
        heading.setFont(new Font("Serif", Font.BOLD, 24));
        headingP.add(heading);

        fromL = new JLabel("Pickup Location");
        toL = new JLabel("Drop Location");
        amountL = new JLabel("Amount (PKR)");
        distanceL = new JLabel("Distance (km)");

        fromL.setPreferredSize(new Dimension(250, 30));
        toL.setPreferredSize(new Dimension(250, 30));
        amountL.setPreferredSize(new Dimension(250, 30));
        distanceL.setPreferredSize(new Dimension(250, 30));

        fromF = new JTextField();
        toF = new JTextField();
        amountF = new JTextField();
        distanceF = new JTextField();
        fromF.setPreferredSize(new Dimension(300, 35));
        toF.setPreferredSize(new Dimension(300, 35));
        amountF.setPreferredSize(new Dimension(300, 35));
        distanceF.setPreferredSize(new Dimension(300, 35));

        book = new JButton("Book");
        back = new JButton("Back");
        book.setPreferredSize(new Dimension(150, 40));
        back.setPreferredSize(new Dimension(150, 40));

        MyActionListener actionListener = new MyActionListener(customer);
        book.addActionListener(actionListener);
        back.addActionListener(actionListener);

        fromP.add(fromL);
        fromP.add(fromF);

        toP.add(toL);
        toP.add(toF);

        amountP.add(amountL);
        amountP.add(amountF);

        distanceP.add(distanceL);
        distanceP.add(distanceF);

        buttonP.add(book);
        buttonP.add(back);

        centreGrid.add(fromP);
        centreGrid.add(toP);
        centreGrid.add(amountP);
        centreGrid.add(distanceP);

        mainPanel.add(headingP, BorderLayout.NORTH);
        mainPanel.add(centreGrid, BorderLayout.CENTER);
        mainPanel.add(buttonP, BorderLayout.SOUTH);

        add(mainPanel);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    class MyActionListener implements ActionListener {
        Customer customer;
        FileClass fileClass = new FileClass();

        MyActionListener(Customer customer){
            this.customer = customer;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Book")){
                if (checkEmpty()) {
                    JOptionPane.showMessageDialog(new JFrame(), "FILL ALL FIELDS");
                } else if (!Validator.isValidName(fromF.getText())) {
                    JOptionPane.showMessageDialog(new JFrame(), "INVALID Pickup");
                } else if (!Validator.isValidName(toF.getText())) {
                    JOptionPane.showMessageDialog(new JFrame(), "INVALID Drop Location");
                } else if (!Validator.isValidAmount(amountF.getText())) {
                    JOptionPane.showMessageDialog(new JFrame(), "INVALID Amount");
                } else if (!Validator.isValidDistance(distanceF.getText())) {
                    JOptionPane.showMessageDialog(new JFrame(), "INVALID Distance");
                } else {
                    if (customer.getBalance() >= Integer.parseInt(amountF.getText())) {
                        ArrayList<Ride> list = fileClass.readRidesBike();
                        LocalDate localDate = LocalDate.now();
                        int day = localDate.getDayOfMonth();
                        int month = localDate.getMonthValue();
                        int year = localDate.getYear();
                        Ride ride = new Ride(fromF.getText(), toF.getText(),
                                Integer.parseInt(amountF.getText()), Double.parseDouble(distanceF.getText()),
                                new Date(day, month, year));
                        ride.setCustomerId(customer.getUsername());
                        list.add(ride);
                        fileClass.addRideBike(list);
                        ArrayList<Customer> customers = fileClass.readCustomers();
                        for (Customer c : customers) {
                            if (c.getUsername().equals(customer.getUsername())) {
                                c.setBooked(true);
                                c.setBalance(c.getBalance() - Integer.parseInt(amountF.getText()));
                                c.setSpentAmount(c.getSpentAmount() + Integer.parseInt(amountF.getText()));
                                customer.setBooked(true);
                                customer.setBalance(customer.getBalance() -
                                        Integer.parseInt(amountF.getText()));
                                customer.setSpentAmount(customer.getSpentAmount() +
                                        Integer.parseInt(amountF.getText()));
                            }
                        }
                        fileClass.update(customers, true);
                        JOptionPane.showMessageDialog(new JFrame(), "Ride Request sent to Riders");
                    } else {
                        JOptionPane.showMessageDialog(new JFrame(), "Low Balance");
                    }
                    CustomerForm customerForm = new CustomerForm(customer);
                    dispose();
                }
            } else if (e.getActionCommand().equals("Back")) {
                CustomerForm customerForm = new CustomerForm(customer);
                dispose();
            }
        }
    }

    public boolean checkEmpty() {
        return fromF.getText().equals("") || toF.getText().equals("") ||
                amountF.getText().equals("") || distanceF.getText().equals("");
    }
}
