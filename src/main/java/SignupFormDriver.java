import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SignupFormDriver extends JFrame {
    JPanel mainPanel, centreGrid, headingP, nameP, cnicP, phoneNoP, usernameP, passwordP, rePasswordP, buttonP,
    licenceP, regNoP, compP, modelP, colorP, acP;
    JLabel heading, nameL, cnicL, phoneNoL, usernameL, passwordL, rePasswordL, licenceNoL,
        regNoL, companyL, modelL, colorL;
    JComboBox colors;
    JCheckBox ac, trunk;
    JTextField nameF, cnicF, phoneNoF, usernameF, licenceNoF, regNoF, companyF, modelF;
    JPasswordField passwordF, rePasswordF;
    JButton register, back;

    public SignupFormDriver(){
        ImageIcon image  = new ImageIcon("images.jpg");
        setIconImage(image.getImage());

        mainPanel = new JPanel(new BorderLayout());
        centreGrid = new JPanel(new GridLayout(12, 2));
        headingP = new JPanel(new FlowLayout());
        nameP = new JPanel(new FlowLayout());
        cnicP = new JPanel(new FlowLayout());
        phoneNoP = new JPanel(new FlowLayout());
        usernameP = new JPanel(new FlowLayout());
        passwordP = new JPanel(new FlowLayout());
        rePasswordP = new JPanel(new FlowLayout());
        buttonP = new JPanel(new FlowLayout());
        licenceP = new JPanel(new FlowLayout());
        regNoP = new JPanel(new FlowLayout());
        compP = new JPanel(new FlowLayout());
        modelP = new JPanel(new FlowLayout());
        colorP = new JPanel(new FlowLayout());
        acP = new JPanel(new FlowLayout());

        heading = new JLabel("Driver Registration");
        heading.setFont(new Font("Serif", Font.BOLD, 24));
        headingP.add(heading);

        nameL = new JLabel("Name");
        cnicL = new JLabel("CNIC(XXXXX-XXXXXXX-X)");
        phoneNoL = new JLabel("Phone No");
        usernameL = new JLabel("Username");
        passwordL = new JLabel("Password");
        rePasswordL = new JLabel("Re-Write Password");
        licenceNoL = new JLabel("Licence No");
        regNoL = new JLabel("Car Registration No");
        companyL = new JLabel("Car Company");
        modelL = new JLabel("Car Model");
        colorL = new JLabel("Car Color");

        nameL.setPreferredSize(new Dimension(250, 30));
        cnicL.setPreferredSize(new Dimension(250, 30));
        phoneNoL.setPreferredSize(new Dimension(250, 30));
        usernameL.setPreferredSize(new Dimension(250, 30));
        passwordL.setPreferredSize(new Dimension(250, 30));
        rePasswordL.setPreferredSize(new Dimension(250, 30));
        licenceNoL.setPreferredSize(new Dimension(250, 30));
        regNoL.setPreferredSize(new Dimension(250, 30));
        companyL.setPreferredSize(new Dimension(250, 30));
        modelL.setPreferredSize(new Dimension(250, 30));
        colorL.setPreferredSize(new Dimension(250, 30));

        ac = new JCheckBox("AC");
        trunk = new JCheckBox("Has Trunk");

        nameF = new JTextField();
        cnicF = new JTextField();
        phoneNoF = new JTextField();
        usernameF = new JTextField();
        passwordF = new JPasswordField();
        rePasswordF = new JPasswordField();
        licenceNoF = new JTextField();
        regNoF = new JTextField();
        companyF = new JTextField();
        modelF = new JTextField();
        nameF.setPreferredSize(new Dimension(300, 35));
        cnicF.setPreferredSize(new Dimension(300, 35));
        phoneNoF.setPreferredSize(new Dimension(300, 35));
        usernameF.setPreferredSize(new Dimension(300, 35));
        passwordF.setPreferredSize(new Dimension(300, 35));
        rePasswordF.setPreferredSize(new Dimension(300, 35));
        licenceNoF.setPreferredSize(new Dimension(300, 35));
        regNoF.setPreferredSize(new Dimension(300, 35));
        companyF.setPreferredSize(new Dimension(300, 35));
        modelF.setPreferredSize(new Dimension(300, 35));

        String[] colorArr = {"white", "black", "red", "blue", "gray", "golden", "yellow"};
        colors = new JComboBox<>(colorArr);
        colors.setPreferredSize(new Dimension(300, 35));

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

        licenceP.add(licenceNoL);
        licenceP.add(licenceNoF);

        regNoP.add(regNoL);
        regNoP.add(regNoF);

        compP.add(companyL);
        compP.add(companyF);

        modelP.add(modelL);
        modelP.add(modelF);

        colorP.add(colorL);
        colorP.add(colors);

        acP.add(ac);
        acP.add(trunk);

        buttonP.add(register);
        buttonP.add(back);

        centreGrid.add(nameP);
        centreGrid.add(cnicP);
        centreGrid.add(phoneNoP);
        centreGrid.add(usernameP);
        centreGrid.add(passwordP);
        centreGrid.add(rePasswordP);
        centreGrid.add(licenceP);
        centreGrid.add(regNoP);
        centreGrid.add(compP);
        centreGrid.add(modelP);
        centreGrid.add(colorP);
        centreGrid.add(acP);

        mainPanel.add(headingP, BorderLayout.NORTH);
        mainPanel.add(centreGrid, BorderLayout.CENTER);
        mainPanel.add(buttonP, BorderLayout.SOUTH);

        add(mainPanel);

        setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
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
                } else if (!Validator.isValidRegistrationNo(regNoF.getText())) {
                    JOptionPane.showMessageDialog(new JFrame(), "INVALID REGISTRATION NO");
                } else if (!Validator.isValidLicenceNo(licenceNoF.getText())) {
                    JOptionPane.showMessageDialog(new JFrame(), "INVALID LICENCE NO");
                }else if (!Validator.isValidModel(companyF.getText())) {
                    JOptionPane.showMessageDialog(new JFrame(), "INVALID COMPANY");
                }  else if (!Validator.isValidModel(modelF.getText())) {
                    JOptionPane.showMessageDialog(new JFrame(), "INVALID MODEL");
                } else {
                    FileClass fileClass = new FileClass();
                    Driver driver = new Driver(nameF.getText(), usernameF.getText(), passwordF.getText(),
                            phoneNoF.getText(), cnicF.getText(), licenceNoF.getText(), new Car(
                                    regNoF.getText(), companyF.getText(), modelF.getText(), colors.getSelectedItem().toString(),
                            ac.isSelected(), trunk.isSelected()));
                    ArrayList<Driver> drivers = fileClass.readDrivers();
                    boolean check = true, checkCnic = true;
                    for (Driver d:drivers) {
                        if (d.getUsername().equals(driver.getUsername())){
                            check = false;
                        }
                        if (d.getCnic().equals(driver.getCnic())) {
                            checkCnic = false;
                        }
                    }
                    if (check && checkCnic) {
                        Admin admin = fileClass.readAdmin();
                        admin.addApproval(driver);
                        fileClass.writeFile(admin);
                        JOptionPane.showMessageDialog(new JFrame(), "Request Sent to Admin Wait for Approval");
                        SignupFormDriver signupFormDriver = new SignupFormDriver();
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
                passwordF.getText().equals("") || rePasswordF.getText().equals("") ||
                regNoF.getText().equals("") || licenceNoF.getText().equals("") ||
                modelF.getText().equals("") || companyF.getText().equals("");
    }
}
