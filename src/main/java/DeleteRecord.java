import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DeleteRecord extends JFrame {
    JPanel grid, flow1, flow2, flow3;
    JLabel heading, usernameL;
    JTextField usernameF;
    JRadioButton driverR, riderR, customerR;
    JButton deleteB, homeB;
    FileClass fileClass = new FileClass();

    public DeleteRecord() {
        setSize(400, 400);
        setLayout(new BorderLayout());

        grid = new JPanel(new GridLayout(2, 1));
        flow1 = new JPanel(new FlowLayout());
        flow2 = new JPanel(new FlowLayout());
        flow3 = new JPanel(new FlowLayout());

        heading = new JLabel("Delete");
        heading.setFont(new Font("Serif", Font.BOLD, 24));

        usernameL = new JLabel("Username");
        usernameL.setFont(new Font("Serif", Font.BOLD, 14));

        usernameF = new JTextField();
        usernameF.setPreferredSize(new Dimension(300, 35));

        driverR = new JRadioButton("Driver");
        riderR = new JRadioButton("Rider");
        customerR = new JRadioButton("Customer");
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(driverR);
        buttonGroup.add(riderR);
        buttonGroup.add(customerR);

        deleteB = new JButton("Delete");
        homeB = new JButton("Back");
        deleteB.setPreferredSize(new Dimension(150, 40));
        homeB.setPreferredSize(new Dimension(150, 40));

        MyActionListener actionListener = new MyActionListener();
        deleteB.addActionListener(actionListener);
        homeB.addActionListener(actionListener);

        flow1.add(usernameL);
        flow1.add(usernameF);

        flow2.add(driverR);
        flow2.add(riderR);
        flow2.add(customerR);

        grid.add(flow1);
        grid.add(flow2);

        flow3.add(deleteB);
        flow3.add(homeB);

        add(heading, BorderLayout.NORTH);
        add(grid, BorderLayout.CENTER);
        add(flow3, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    class MyActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Delete")) {
                if (usernameF.getText().equals("")) {
                    JOptionPane.showMessageDialog(new JFrame(), "Please Enter Username");
                } else if (driverR.isSelected()) {
                    ArrayList<Driver> drivers = fileClass.readDrivers();
                    boolean deleted = false;
                    for (int i = 0; i < drivers.size(); i++) {
                        if (drivers.get(i).getUsername().equals(usernameF.getText())) {
                            drivers.remove(i);
                            deleted = true;
                        }
                    }
                    if (deleted) {
                        fileClass.update(drivers, "asd");
                        JOptionPane.showMessageDialog(new JFrame(), "Driver Deleted");
                    } else {
                        JOptionPane.showMessageDialog(new JFrame(), "Driver Not Found");
                    }
                } else if (riderR.isSelected()) {
                    ArrayList<Rider> riders = fileClass.readRiders();
                    boolean deleted = false;
                    for (int i = 0; i < riders.size(); i++) {
                        if (riders.get(i).getUsername().equals(usernameF.getText())) {
                            riders.remove(i);
                            deleted = true;
                        }
                    }
                    if (deleted) {
                        fileClass.update(riders, 45);
                        JOptionPane.showMessageDialog(new JFrame(), "Rider Deleted");
                    } else {
                        JOptionPane.showMessageDialog(new JFrame(), "Rider Not Found");
                    }
                } else if (customerR.isSelected()) {
                    ArrayList<Customer> customers = fileClass.readCustomers();
                    boolean deleted = false;
                    for (int i = 0; i < customers.size(); i++) {
                        if (customers.get(i).getUsername().equals(usernameF.getText())) {
                            customers.remove(i);
                            deleted = true;
                        }
                    }
                    if (deleted) {
                        fileClass.update(customers, false);
                        JOptionPane.showMessageDialog(new JFrame(), "Customer Deleted");
                    } else {
                        JOptionPane.showMessageDialog(new JFrame(), "Customer Not Found");
                    }
                }
            } else if (e.getActionCommand().equals("Back")) {
                AdminForm adminForm = new AdminForm(fileClass.readAdmin());
                dispose();
            }
        }
    }
}
