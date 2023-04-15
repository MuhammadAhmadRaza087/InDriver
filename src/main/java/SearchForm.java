import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SearchForm extends JFrame {
    JPanel centre, grid, flow1, flow2;
    JButton searchB, backB;
    JTextField searchF;
    JTextArea textArea;
    JLabel heading, searchL;
    JRadioButton driver, rider, customer;
    FileClass fileClass = new FileClass();

    public SearchForm() {
        ImageIcon image  = new ImageIcon("images.jpg");
        setIconImage(image.getImage());
        setLayout(new BorderLayout());

        centre = new JPanel(new BorderLayout());
        grid = new JPanel(new GridLayout(2, 1));
        flow1 = new JPanel(new FlowLayout());
        flow2 = new JPanel(new FlowLayout());

        heading = new JLabel("Search");
        heading.setFont(new Font("Serif", Font.BOLD, 24));

        searchL = new JLabel("Username");
        searchL.setFont(new Font("Serif", Font.BOLD, 18));

        searchB = new JButton("Search");
        backB = new JButton("Back");
        searchB.setPreferredSize(new Dimension(150, 35));
        backB.setPreferredSize(new Dimension(250, 35));

        MyActionListener actionListener = new MyActionListener();
        searchB.addActionListener(actionListener);
        backB.addActionListener(actionListener);

        searchF = new JTextField();
        searchF.setPreferredSize(new Dimension(250, 35));


        textArea = new JTextArea();
        textArea.setPreferredSize(new Dimension(500, 500));
        textArea.setFont(new Font("Serif", Font.BOLD, 14));

        ButtonGroup buttonGroup = new ButtonGroup();
        driver = new JRadioButton("Driver");
        rider = new JRadioButton("Rider");
        customer = new JRadioButton("Customer");
        buttonGroup.add(driver);
        buttonGroup.add(rider);
        buttonGroup.add(customer);

        flow1.add(searchL);
        flow1.add(searchF);
        flow1.add(searchB);

        flow2.add(driver);
        flow2.add(rider);
        flow2.add(customer);

        grid.add(flow1);
        grid.add(flow2);

        centre.add(grid, BorderLayout.NORTH);
        centre.add(textArea, BorderLayout.CENTER);

        add(heading, BorderLayout.NORTH);
        add(centre, BorderLayout.CENTER);
        add(backB, BorderLayout.SOUTH);

        setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }

    class MyActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            textArea.setText("");
            if (e.getActionCommand().equals("Search")) {
                if (driver.isSelected()) {
                    Driver driver1 = (Driver) fileClass.search(searchF.getText(), "driver");
                    if (driver1 != null) {
                        String str = "Name: " + driver1.getName() + "\nPhone No: " + driver1.getPhoneNo() +
                                "\nCNIC: " + driver1.getCnic() +  "Licence No: " + driver1.getLicenceNo() +
                                "\nRating: " + driver1.getRating() + "\n" + driver1.getCar().toString()+
                                "\nBalacne: " + driver1.getBalance() +
                                "\nTotal Earning: " + driver1.getTotalEarning();;
                        textArea.setText(str);
                    } else {
                        JOptionPane.showMessageDialog(new JFrame(), "Driver Not Found");
                    }
                } else if (rider.isSelected()) {
                    Rider rider1 = (Rider) fileClass.search(searchF.getText(), "rider");
                    if (rider1 != null) {
                        String str = "Name: " + rider1.getName() + "\nPhone No: " + rider1.getPhoneNo() +
                                "\nCNIC: " + rider1.getCnic() +  "Licence No: " + rider1.getLicenceNo() +
                                "\nRating: " + rider1.getRating() + "\n" + rider1.getBike().toString() +
                                "\nBalacne: " + rider1.getBalance() +
                                "\nTotal Earning: " + rider1.getTotalEarning();
                        textArea.setText(str);
                    } else {
                        JOptionPane.showMessageDialog(new JFrame(), "Rider Not Found");
                    }
                } else if (customer.isSelected()) {
                    Customer customer1 = (Customer) fileClass.search(searchF.getText(), "customer");
                    if (customer1 != null) {
                        String str = "Name: " + customer1.getName() + "\nPhone No: " + customer1.getPhoneNo() +
                                "\nCNIC: " + customer1.getCnic() +
                                "\nTotal Spent: " + customer1.getSpentAmount() +
                                "\nBalance: " + customer1.getBalance();
                        textArea.setText(str);
                    } else {
                        JOptionPane.showMessageDialog(new JFrame(), "Customer Not Found");
                    }
                } else {
                    JOptionPane.showMessageDialog(new JFrame(), "Please Select a Option");
                }
            } else if (e.getActionCommand().equals("Back")) {
                dispose();
                AdminForm adminForm = new AdminForm(fileClass.readAdmin());
            }
        }
    }
}
