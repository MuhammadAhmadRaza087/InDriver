import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class UpdateDriverForm extends JFrame {
    JPanel mainPanel, centreGrid, headingP, usernameP, buttonP,
            regNoP, compP, modelP, colorP, acP;
    JLabel heading, usernameL, regNoL, companyL, modelL, colorL;
    JComboBox colors;
    JCheckBox ac, trunk;
    JTextField usernameF, regNoF, companyF, modelF;
    JButton register, back;
    FileClass fileClass = new FileClass();

    public UpdateDriverForm(){
        ImageIcon image  = new ImageIcon("images.jpg");
        setIconImage(image.getImage());
        mainPanel = new JPanel(new BorderLayout());
        centreGrid = new JPanel(new GridLayout(6, 2));
        headingP = new JPanel(new FlowLayout());
        usernameP = new JPanel(new FlowLayout());
        buttonP = new JPanel(new FlowLayout());
        regNoP = new JPanel(new FlowLayout());
        compP = new JPanel(new FlowLayout());
        modelP = new JPanel(new FlowLayout());
        colorP = new JPanel(new FlowLayout());
        acP = new JPanel(new FlowLayout());

        heading = new JLabel("Update Driver");
        heading.setFont(new Font("Serif", Font.BOLD, 24));
        headingP.add(heading);

        usernameL = new JLabel("Username Of Driver To Update");
        regNoL = new JLabel("Car Registration No");
        companyL = new JLabel("Car Company");
        modelL = new JLabel("Car Model");
        colorL = new JLabel("Car Color");

        usernameL.setPreferredSize(new Dimension(250, 30));
        regNoL.setPreferredSize(new Dimension(250, 30));
        companyL.setPreferredSize(new Dimension(250, 30));
        modelL.setPreferredSize(new Dimension(250, 30));
        colorL.setPreferredSize(new Dimension(250, 30));

        ac = new JCheckBox("AC");
        trunk = new JCheckBox("Has Trunk");

        usernameF = new JTextField();
        regNoF = new JTextField();
        companyF = new JTextField();
        modelF = new JTextField();
        usernameF.setPreferredSize(new Dimension(300, 35));
        regNoF.setPreferredSize(new Dimension(300, 35));
        companyF.setPreferredSize(new Dimension(300, 35));
        modelF.setPreferredSize(new Dimension(300, 35));

        String[] colorArr = {"white", "black", "red", "blue", "gray", "golden", "yellow"};
        colors = new JComboBox<>(colorArr);
        colors.setPreferredSize(new Dimension(300, 35));

        register = new JButton("Update");
        back = new JButton("Back");
        register.setPreferredSize(new Dimension(150, 40));
        back.setPreferredSize(new Dimension(150, 40));

        MyActionListener actionListener = new MyActionListener();
        register.addActionListener(actionListener);
        back.addActionListener(actionListener);

        usernameP.add(usernameL);
        usernameP.add(usernameF);

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

        centreGrid.add(usernameP);
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
            if (e.getActionCommand().equals("Update")){
                if (checkEmpty()) {
                    JOptionPane.showMessageDialog(new JFrame(), "FILL ALL FIELDS");
                } else if (!Validator.isValidRegistrationNo(regNoF.getText())) {
                    JOptionPane.showMessageDialog(new JFrame(), "INVALID REGISTRATION NO");
                } else if (!Validator.isValidModel(companyF.getText())) {
                    JOptionPane.showMessageDialog(new JFrame(), "INVALID COMPANY");
                } else if (!Validator.isValidModel(modelF.getText())) {
                    JOptionPane.showMessageDialog(new JFrame(), "INVALID MODEL");
                } else {
                    ArrayList<Driver> drivers = fileClass.readDrivers();
                    boolean check = false;
                    for (Driver driver:drivers) {
                        if (driver.getUsername().equals(usernameF.getText())) {
                            driver.setCar(new Car(regNoF.getText(), companyF.getText(), modelF.getText(),
                                    colors.getSelectedItem().toString(), ac.isSelected(),
                                    trunk.isSelected()));
                            check = true;
                        }
                    }
                    if (check) {
                        fileClass.update(drivers, "asd");
                        JOptionPane.showMessageDialog(new JFrame(), "DRIVER UPDATED");
                        UpdateDriverForm updateDriverForm = new UpdateDriverForm();
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(new JFrame(), "DRIVER NOT FOUND");
                    }
                }
            } else if (e.getActionCommand().equals("Back")) {
                AdminForm adminForm = new AdminForm(fileClass.readAdmin());
                dispose();
            }
        }
    }

    public boolean checkEmpty() {
        return usernameF.getText().equals("") || regNoF.getText().equals("") ||
                modelF.getText().equals("") || companyF.getText().equals("");
    }
}
