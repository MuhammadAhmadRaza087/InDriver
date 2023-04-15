import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DisplayAllForm extends JFrame {
    JPanel grid, border1, border2, border3;
    JLabel heading, customerL, driverL, riderL;
    JList customerList;
    JList<Person> driverList, riderList;
    DefaultListModel modelD, modelR;
    JButton homeB;
    FileClass fileClass = new FileClass();

    public DisplayAllForm() {
        ImageIcon image  = new ImageIcon("images.jpg");
        setIconImage(image.getImage());
        setLayout(new BorderLayout());
        grid = new JPanel(new GridLayout(3, 1));
        border1 = new JPanel(new BorderLayout());
        border2 = new JPanel(new BorderLayout());
        border3 = new JPanel(new BorderLayout());

        heading = new JLabel("Display All");
        heading.setFont(new Font("Serif", Font.BOLD, 24));

        customerL = new JLabel("Customers");
        customerL.setFont(new Font("Serif", Font.BOLD, 18));

        driverL = new JLabel("Drivers");
        driverL.setFont(new Font("Serif", Font.BOLD, 18));

        riderL = new JLabel("Riders");
        riderL.setFont(new Font("Serif", Font.BOLD, 18));

        homeB = new JButton("Back");
        MyActionListener actionListener = new MyActionListener();
        homeB.addActionListener(actionListener);
        homeB.setPreferredSize(new Dimension(250, 35));

        customerList = new JList(fileClass.readCustomers().toArray(new Customer[0]));
        driverList = new JList();
        riderList = new JList();
        modelD = new DefaultListModel();
        modelR = new DefaultListModel();
        for (Person p:fileClass.readDrivers()) {
            modelD.addElement(p);
        }
        for (Person p:fileClass.readRiders()) {
            modelR.addElement(p);
        }
        driverList.setModel(modelD);
        riderList.setModel(modelR);
        driverList.setCellRenderer(new MyListCellRenderer());
        riderList.setCellRenderer(new MyListCellRenderer());

        border1.add(customerL, BorderLayout.NORTH);
        border1.add(new JScrollPane(customerList), BorderLayout.CENTER);

        border2.add(driverL, BorderLayout.NORTH);
        border2.add(new JScrollPane(driverList), BorderLayout.CENTER);

        border3.add(riderL, BorderLayout.NORTH);
        border3.add(new JScrollPane(riderList), BorderLayout.CENTER);

        grid.add(border1);
        grid.add(border2);
        grid.add(border3);

        add(heading, BorderLayout.NORTH);
        add(grid, BorderLayout.CENTER);
        add(homeB, BorderLayout.SOUTH);

        setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }

    class MyActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            AdminForm adminForm = new AdminForm(fileClass.readAdmin());
            dispose();
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
            String licence, veh, rating;
            if (label instanceof Driver) {
                Driver d = (Driver) label;
                licence = d.getLicenceNo();
                rating = d.getRating() + "";
                veh = d.getCar().toString();
            } else {
                Rider d = (Rider) label;
                licence = d.getLicenceNo();
                rating = d.getRating() + "";
                veh = d.getBike().toString();
            }
            String labelText = "<html>Name: " + name + ", Phone no: " + phone + ", CNIC: " + cnic +
                    ", Rating: " + rating + "<br/>Licence: " + licence + "<br/>" + veh;
            setText(labelText);

            return this;
        }
    }
}
