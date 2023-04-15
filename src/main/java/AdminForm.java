import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AdminForm extends JFrame {
    JPanel mainPanel, leftGrid, centerGrid, dashGrid, balanceGrid, totalGrid, grid1, flow1, flow2;
    JLabel heading, dashboard, balanceL, totalL, approvalsL;
    JButton displayAll, updateD, delete, search, approve, logout, disapprove, updateAdmin, updateR,
    withDraw, dailyReportB;
    JList<Person> list;
    DefaultListModel model;

    public AdminForm(Admin admin) {
        ImageIcon image  = new ImageIcon("images.jpg");
        setIconImage(image.getImage());
        mainPanel = new JPanel(new BorderLayout());
        leftGrid = new JPanel(new GridLayout(10, 1));
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

        heading = new JLabel("IN DRIVER ADMIN");
        heading.setFont(new Font("Serif", Font.BOLD, 24));

        dashboard = new JLabel("DASHBOARD");
        dashboard.setFont(new Font("Serif", Font.BOLD, 24));

        totalL = new JLabel("Total Income: " + admin.getTotalEarned());
        totalL.setFont(new Font("Serif", Font.BOLD, 20));
        totalL.setForeground(Color.WHITE);

        balanceL = new JLabel("Balance: " + admin.getIncome());
        balanceL.setFont(new Font("Serif", Font.BOLD, 20));
        balanceL.setForeground(Color.WHITE);

        approvalsL = new JLabel("Pending Approvals");
        approvalsL.setFont(new Font("Serif", Font.BOLD, 18));
        approvalsL.setForeground(Color.WHITE);

        displayAll = new JButton("Display All");
        updateAdmin = new JButton("Update Admin");
        updateD = new JButton("Update Driver");
        updateR = new JButton("Update Rider");
        dailyReportB = new JButton("Generate Daily Report");
        delete = new JButton("Delete");
        search = new JButton("Search");
        approve = new JButton("Approve");
        logout = new JButton("Logout");
        disapprove = new JButton("Disapprove");
        withDraw = new JButton("With Draw");

        MyActionListener actionListener = new MyActionListener(admin);
        displayAll.addActionListener(actionListener);
        updateAdmin.addActionListener(actionListener);
        updateD.addActionListener(actionListener);
        updateR.addActionListener(actionListener);
        dailyReportB.addActionListener(actionListener);
        delete.addActionListener(actionListener);
        search.addActionListener(actionListener);
        approve.addActionListener(actionListener);
        logout.addActionListener(actionListener);
        disapprove.addActionListener(actionListener);
        withDraw.addActionListener(actionListener);

        list = new JList();
        model = new DefaultListModel();
        for (Person p:admin.getApprovals()) {
            model.addElement(p);
        }
        list.setModel(model);
        list.setCellRenderer(new MyListCellRenderer());

        leftGrid.add(heading);
        leftGrid.add(displayAll);
        leftGrid.add(withDraw);
        leftGrid.add(dailyReportB);
        leftGrid.add(updateAdmin);
        leftGrid.add(updateD);
        leftGrid.add(updateR);
        leftGrid.add(delete);
        leftGrid.add(search);
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
    }

    class MyActionListener implements ActionListener {
        FileClass fileClass = new FileClass();
        Admin admin;
        MyActionListener(Admin admin){this.admin = admin;}
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Display All")) {
                DisplayAllForm displayAllForm = new DisplayAllForm();
                dispose();
            } else if (e.getActionCommand().equals("Update Admin")) {
                UpdateAdminForm updateAdminForm = new UpdateAdminForm();
                dispose();
            } else if (e.getActionCommand().equals("Generate Daily Report")) {
                try {
                    GenerateReport generateReport = new GenerateReport(fileClass.readDrivers(),
                            fileClass.readRiders());
                    JOptionPane.showMessageDialog(new JFrame(), "Report Generated");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(new JFrame(), "Could not Generate Report");
                }
            } else if (e.getActionCommand().equals("With Draw")) {
                String amount = JOptionPane.showInputDialog(new JFrame(), "Enter Amount to With Draw");
                if (!Validator.isValidAmount(amount)) {
                    JOptionPane.showMessageDialog(new JFrame(), "INVALID Amount");
                    return;
                }
                int amountInt = Integer.parseInt(amount);
                if (amountInt > admin.getIncome()) {
                    JOptionPane.showMessageDialog(new JFrame(), "NOT Enough Balance");
                    return;
                }
                Admin admin1 = fileClass.readAdmin();
                admin1.setIncome(admin1.getIncome() - amountInt);
                fileClass.writeFile(admin1);
                AdminForm adminForm = new AdminForm(admin1);
                dispose();
            } else if (e.getActionCommand().equals("Update Driver")) {
                UpdateDriverForm updateDriverForm = new UpdateDriverForm();
                dispose();
            } else if (e.getActionCommand().equals("Update Rider")) {
                UpdateRiderForm updateRiderForm = new UpdateRiderForm();
                dispose();
            } else if (e.getActionCommand().equals("Delete")) {
                DeleteRecord deleteRecord = new DeleteRecord();
                dispose();
            } else if (e.getActionCommand().equals("Search")) {
                SearchForm searchForm = new SearchForm();
                dispose();
            } else if (e.getActionCommand().equals("Logout")) {
                LoginForm loginForm = new LoginForm();
                dispose();
            } else if (e.getActionCommand().equals("Approve")) {
                int index = list.getSelectedIndex();
                if (index == -1) {
                    JOptionPane.showMessageDialog(new JFrame(), "Please Select a Person");
                    return;
                } else {
                    Person p = (Person) list.getSelectedValue();
                    if (p instanceof Driver) {
                        Driver driver = (Driver) p;
                        fileClass.writeFile(driver, true);
                    } else {
                        Rider rider = (Rider) p;
                        fileClass.writeFile(rider, true);
                    }
                    Admin admin = fileClass.readAdmin();
                    ArrayList<Person> list = admin.getApprovals();
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).getUsername().equals(p.getUsername())){
                            list.remove(i);
                        }
                    }
                    fileClass.writeFile(admin);
                }
                JOptionPane.showMessageDialog(new JFrame(), "Accepted");
                dispose();
                AdminForm adminForm = new AdminForm(fileClass.readAdmin());
            } else if (e.getActionCommand().equals("Disapprove")) {
                int index = list.getSelectedIndex();
                if (index == -1) {
                    JOptionPane.showMessageDialog(new JFrame(), "Please Select a Person");
                    return;
                } else {
                    Person p = (Person) list.getSelectedValue();
                    Admin admin = fileClass.readAdmin();
                    ArrayList<Person> list = admin.getApprovals();
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).getUsername().equals(p.getUsername())){
                            list.remove(i);
                        }
                    }
                    fileClass.writeFile(admin);
                }
                JOptionPane.showMessageDialog(new JFrame(), "Rejected");
                dispose();
                AdminForm adminForm = new AdminForm(fileClass.readAdmin());
            }
        }
    }

    class MyListCellRenderer extends DefaultListCellRenderer {

        @Override
        public Component getListCellRendererComponent(
                JList list, Object value, int index,
                boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            Person label = (Person) value;
            String name = label.getName();
            String phone = label.getPhoneNo();
            String cnic = label.getCnic();
            String licence, veh;
            if (label instanceof Driver) {
                Driver d = (Driver) label;
                licence = d.getLicenceNo();
                veh = d.getCar().toString();
            } else {
                Rider d = (Rider) label;
                licence = d.getLicenceNo();
                veh = d.getBike().toString();
            }
            String labelText = "<html>Name: " + name + ", Phone no: " + phone + ", CNIC: " + cnic +
                    "<br/>Licence: " + licence + "<br/>" + veh;
            setText(labelText);

            return this;
        }
    }
}
