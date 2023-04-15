import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SignupFormCustomer extends JFrame {
    JPanel mainPanel, centreGrid, headingP,nameP, cnicP, phoneNoP, usernameP, passwordP, rePasswordP, buttonP;
    JLabel heading, nameL, cnicL, phoneNoL, usernameL, passwordL, rePasswordL;
    JTextField nameF, cnicF, phoneNoF, usernameF;
    JPasswordField passwordF, rePasswordF;
    JButton register, back;

    public SignupFormCustomer(){
        ImageIcon image  = new ImageIcon("images.jpg");
        setIconImage(image.getImage());
        setSize(600, 700);

        mainPanel = new JPanel(new BorderLayout());
        centreGrid = new JPanel(new GridLayout(6, 2));
        headingP = new JPanel(new FlowLayout());
        nameP = new JPanel(new FlowLayout());
        cnicP = new JPanel(new FlowLayout());
        phoneNoP = new JPanel(new FlowLayout());
        usernameP = new JPanel(new FlowLayout());
        passwordP = new JPanel(new FlowLayout());
        rePasswordP = new JPanel(new FlowLayout());
        buttonP = new JPanel(new FlowLayout());

        heading = new JLabel("Customer Registration");
        heading.setFont(new Font("Serif", Font.BOLD, 24));
        headingP.add(heading);

        nameL = new JLabel("Name");
        cnicL = new JLabel("CNIC(XXXXX-XXXXXXX-X)");
        phoneNoL = new JLabel("Phone No");
        usernameL = new JLabel("Username");
        passwordL = new JLabel("Password");
        rePasswordL = new JLabel("Re-Write Password");

        nameL.setPreferredSize(new Dimension(250, 30));
        cnicL.setPreferredSize(new Dimension(250, 30));
        phoneNoL.setPreferredSize(new Dimension(250, 30));
        usernameL.setPreferredSize(new Dimension(250, 30));
        passwordL.setPreferredSize(new Dimension(250, 30));
        rePasswordL.setPreferredSize(new Dimension(250, 30));

        nameF = new JTextField();
        cnicF = new JTextField();
        phoneNoF = new JTextField();
        usernameF = new JTextField();
        passwordF = new JPasswordField();
        rePasswordF = new JPasswordField();
        nameF.setPreferredSize(new Dimension(300, 35));
        cnicF.setPreferredSize(new Dimension(300, 35));
        phoneNoF.setPreferredSize(new Dimension(300, 35));
        usernameF.setPreferredSize(new Dimension(300, 35));
        passwordF.setPreferredSize(new Dimension(300, 35));
        rePasswordF.setPreferredSize(new Dimension(300, 35));

        register = new JButton("Register");
        back = new JButton("Back");
        register.setPreferredSize(new Dimension(150, 40));
        back.setPreferredSize(new Dimension(150, 40));

        MyActionListener actionListener = new MyActionListener();
        register.addActionListener(actionListener);
        back.addActionListener(actionListener);

        nameP.add(nameL);
        nameP.add(nameF);

        cnicP.add(cnicL);
        cnicP.add(cnicF);

        phoneNoP.add(phoneNoL);
        phoneNoP.add(phoneNoF);

        usernameP.add(usernameL);
        usernameP.add(usernameF);

        passwordP.add(passwordL);
        passwordP.add(passwordF);

        rePasswordP.add(rePasswordL);
        rePasswordP.add(rePasswordF);

        buttonP.add(register);
        buttonP.add(back);

        centreGrid.add(nameP);
        centreGrid.add(cnicP);
        centreGrid.add(phoneNoP);
        centreGrid.add(usernameP);
        centreGrid.add(passwordP);
        centreGrid.add(rePasswordP);

        mainPanel.add(headingP, BorderLayout.NORTH);
        mainPanel.add(centreGrid, BorderLayout.CENTER);
        mainPanel.add(buttonP, BorderLayout.SOUTH);

        add(mainPanel);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    class MyActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Register")){
                if (checkEmpty()) {
                    JOptionPane.showMessageDialog(new JFrame(), "FILL ALL FIELDS");
                } else if (!Validator.isValidName(nameF.getText())) {
                    JOptionPane.showMessageDialog(new JFrame(), "INVALID NAME");
                } else if (!Validator.isValidCnic(cnicF.getText())) {
                    JOptionPane.showMessageDialog(new JFrame(), "INVALID CNIC");
                } else if (!Validator.isValidPhoneNo(phoneNoF.getText())) {
                    JOptionPane.showMessageDialog(new JFrame(), "INVALID PHONE NUMBER");
                } else if (!Validator.isValidUsername(usernameF.getText())) {
                    JOptionPane.showMessageDialog(new JFrame(), "INVALID USERNAME");
                } else if (!Validator.isValidPassword(passwordF.getText())) {
                    JOptionPane.showMessageDialog(new JFrame(), "INVALID PASSWORD");
                } else if (!passwordF.getText().equals(rePasswordF.getText())) {
                    JOptionPane.showMessageDialog(new JFrame(), "CONFIRM PASSWORD DO NOT MATCH");
                } else {
                    FileClass fileClass = new FileClass();
                    Customer customer = new Customer(nameF.getText(), usernameF.getText(), passwordF.getText(),
                            phoneNoF.getText(), cnicF.getText());
                    ArrayList<Customer> customers = fileClass.readCustomers();
                    boolean check = true, checkCnic = true;
                    for (Customer c:customers) {
                        if (c.getUsername().equals(customer.getUsername())){
                            check = false;
                        }
                        if (c.getCnic().equals(customer.getCnic())) {
                            checkCnic = false;
                        }
                    }
                    if (check && checkCnic) {
                        fileClass.writeFile(customer, true);
                        JOptionPane.showMessageDialog(new JFrame(), "Registered Successfully");
                        SignupFormCustomer signupFormCustomer = new SignupFormCustomer();
                        dispose();
                    } else if (!check) {
                        JOptionPane.showMessageDialog(new JFrame(), "USERNAME ALREADY EXISTS");
                    } else {
                        JOptionPane.showMessageDialog(new JFrame(), "USER ALREADY EXISTS");
                    }
                }
            } else if (e.getActionCommand().equals("Back")) {
                LoginForm loginForm = new LoginForm();
                dispose();
            }
        }
    }

    public boolean checkEmpty() {
        return nameF.getText().equals("") || cnicF.getText().equals("") ||
                phoneNoF.getText().equals("") || usernameF.getText().equals("") ||
                passwordF.getText().equals("") || rePasswordF.getText().equals("");
    }
}
