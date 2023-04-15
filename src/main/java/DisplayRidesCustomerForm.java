import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DisplayRidesCustomerForm extends JFrame {
    JLabel heading;
    JList list;
    DefaultListModel<Ride> model;
    JButton backB;

    public DisplayRidesCustomerForm(Customer customer) {
        setLayout(new BorderLayout());

        heading = new JLabel("ALL RIDES");
        heading.setFont(new Font("Serif", Font.BOLD, 24));

        model = new DefaultListModel<>();
        list = new JList();

        for (Ride r: customer.getRides()) {
            model.addElement(r);
        }
        list.setModel(model);
        list.setCellRenderer(new MyListCellRenderer());

        backB = new JButton("Back");
        backB.setPreferredSize(new Dimension(250, 35));
        MyActionListener actionListener = new MyActionListener(customer);
        backB.addActionListener(actionListener);

        add(heading, BorderLayout.NORTH);
        add(new JScrollPane(list), BorderLayout.CENTER);
        add(backB, BorderLayout.SOUTH);

        setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }

    class MyActionListener implements ActionListener {
        Customer customer;

        MyActionListener(Customer customer) {
            this.customer = customer;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            CustomerForm customerForm = new CustomerForm(customer);
            dispose();
        }
    }

    class MyListCellRenderer extends DefaultListCellRenderer {

        @Override
        public Component getListCellRendererComponent(
                JList list, Object value, int index,
                boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            Ride label = (Ride) value;
            String from = label.getPickup();
            String to = label.getDrop();
            int amount = label.getAmount();
            double km = label.getDistanceKm();
            String date = label.getDate().toString();
            String labelText = "<html>From: " + from + ", to: " + to + ", Amount: " + amount +
                    "<br/>distance(km): " + km + "<br/>Date" + date;
            setText(labelText);
            return this;
        }
    }
}
