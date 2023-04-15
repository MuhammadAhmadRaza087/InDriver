import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class UpdateAdminForm extends JFrame {
    JPanel grid, flow1, flow2, flow3;
    JLabel heading, usernameL, passwordL;
    JTextField usernameF;
    JPasswordField passwordF;
    JButton deleteB, homeB;
    FileClass fileClass = new FileClass();

    public UpdateAdminForm() {
        ImageIcon image  = new ImageIcon("images.jpg");
        setIconImage(image.getImage());
        setSize(400, 400);
        setLayout(new BorderLayout());

        grid = new JPanel(new GridLayout(2, 1));
        flow1 = new JPanel(new FlowLayout());
        flow2 = new JPanel(new FlowLayout());
        flow3 = new JPanel(new FlowLayout());

        heading = new JLabel("Update Admin");
        heading.setFont(new Font("Serif", Font.BOLD, 24));

        usernameL = new JLabel("Username");
        usernameL.setFont(new Font("Serif", Font.BOLD, 14));
        passwordL = new JLabel("Password");
        passwordL.setFont(new Font("Serif", Font.BOLD, 14));

        usernameF = new JTextField();
        usernameF.setPreferredSize(new Dimension(300, 35));
        passwordF = new JPasswordField();
        passwordF.setPreferredSize(new Dimension(300, 35));

        deleteB = new JButton("Update");
        homeB = new JButton("Back");
        deleteB.setPreferredSize(new Dimension(150, 40));
        homeB.setPreferredSize(new Dimension(150, 40));

        MyActionListener actionListener = new MyActionListener();
        deleteB.addActionListener(actionListener);
        homeB.addActionListener(actionListener);

        flow1.add(usernameL);
        flow1.add(usernameF);

        flow2.add(passwordL);
        flow2.add(passwordF);

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
            if (e.getActionCommand().equals("Update")) {
                if (usernameF.getText().equals("") || passwordF.getText().equals("")) {
                    JOptionPane.showMessageDialog(new JFrame(), "Please Enter Username or Password");
                } else {
                    if (Validator.isValidUsername(usernameF.getText()) &&
                    Validator.isValidPassword(passwordF.getText())) {
                        Admin admin = fileClass.readAdmin();
                        admin.setUsername(usernameF.getText());
                        admin.setPassword(passwordF.getText());
                        fileClass.writeFile(admin);
                        JOptionPane.showMessageDialog(new JFrame(), "Username and Password Updated");
                        AdminForm adminForm = new AdminForm(admin);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(new JFrame(), "Invalid Username or Password");
                    }
                }
            } else if (e.getActionCommand().equals("Back")) {
                AdminForm adminForm = new AdminForm(fileClass.readAdmin());
                dispose();
            }
        }
    }
}
