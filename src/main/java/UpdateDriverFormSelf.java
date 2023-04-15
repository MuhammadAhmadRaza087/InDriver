import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class UpdateDriverFormSelf extends JFrame {
    JPanel grid, flow2, flow3;
    JLabel heading, passwordL;
    JPasswordField passwordF;
    JButton deleteB, homeB;
    FileClass fileClass = new FileClass();

    public UpdateDriverFormSelf(Driver driver) {
        ImageIcon image  = new ImageIcon("images.jpg");
        setIconImage(image.getImage());
        setSize(400, 400);
        setLayout(new BorderLayout());

        grid = new JPanel(new GridLayout(1, 1));
        flow2 = new JPanel(new FlowLayout());
        flow3 = new JPanel(new FlowLayout());

        heading = new JLabel("Update Driver");
        heading.setFont(new Font("Serif", Font.BOLD, 24));

        passwordL = new JLabel("Password");
        passwordL.setFont(new Font("Serif", Font.BOLD, 14));

        passwordF = new JPasswordField();
        passwordF.setPreferredSize(new Dimension(300, 35));

        deleteB = new JButton("Update");
        homeB = new JButton("Back");
        deleteB.setPreferredSize(new Dimension(150, 40));
        homeB.setPreferredSize(new Dimension(150, 40));

        MyActionListener actionListener = new MyActionListener(driver);
        deleteB.addActionListener(actionListener);
        homeB.addActionListener(actionListener);

        flow2.add(passwordL);
        flow2.add(passwordF);

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
        Driver driver;
        MyActionListener(Driver driver) {
            this.driver = driver;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Update")) {
                if (passwordF.getText().equals("")) {
                    JOptionPane.showMessageDialog(new JFrame(), "Please Enter Password");
                } else {
                    if (Validator.isValidPassword(passwordF.getText())) {
                        ArrayList<Driver> drivers = fileClass.readDrivers();
                        for (Driver d:drivers) {
                            if (d.getUsername().equals(driver.getUsername())) {
                                d.setPassword(passwordF.getText());
                            }
                        }
                        fileClass.update(drivers, "asd");
                        JOptionPane.showMessageDialog(new JFrame(), "Password Updated");
                        DriverForm driverForm = new DriverForm(driver);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(new JFrame(), "Invalid Password");
                    }
                }
            } else if (e.getActionCommand().equals("Back")) {
                DriverForm driverForm = new DriverForm(driver);
                dispose();
            }
        }
    }
}
