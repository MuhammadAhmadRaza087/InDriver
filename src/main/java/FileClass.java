import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileClass {
    public void writeFile (Admin admin) {
        ObjectOutputStream objOut = null;
        try {
            File file = new File("Admin.ser");
            objOut = new ObjectOutputStream(new FileOutputStream(file, false));
            objOut.writeObject(admin);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                objOut.close();
            } catch (Exception e) {
            }
        }
    }

    public void writeFile (Driver driver, boolean append) {
        ObjectOutputStream objOut = null;
        try {
            File file = new File("Drivers.ser");
            if (file.exists()) {
                objOut = new MyObjectOutputStream(new FileOutputStream(file, append));
                objOut.writeObject(driver);
            } else {
                objOut = new ObjectOutputStream(new FileOutputStream(file, append));
                objOut.writeObject(driver);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                objOut.close();
            } catch (Exception e) {
            }
        }
    }

    public Admin readAdmin () {
        Admin admin = null;
        ObjectInputStream objIn = null;
        try {
            File file = new File("Admin.ser");
            objIn = new ObjectInputStream(new FileInputStream(file));
            admin = (Admin) objIn.readObject();
            return admin;
        } catch (ClassNotFoundException e) {
            return admin;
        }catch (Exception e) {
            return admin;
        }
        finally {
            try {
                objIn.close();
            } catch (Exception e) {
            }
        }
    }

    public ArrayList<Driver> readDrivers () {
        ArrayList<Driver> list = new ArrayList<>();
        ObjectInputStream objIn = null;
        try {
            File file = new File("Drivers.ser");
            objIn = new ObjectInputStream(new FileInputStream(file));
            while (true) {
                Driver stu = (Driver)objIn.readObject();
                list.add(stu);
            }
        } catch (ClassNotFoundException e) {
            return list;
        } catch (EOFException e) {
            return list;
        } catch (Exception e) {
            return list;
        } finally {
            try {
                objIn.close();
                return list;
            } catch (Exception e) {
            }
        }
    }

    public void writeFile (Customer customer, boolean append) {
        ObjectOutputStream objOut = null;
        try {
            File file = new File("Customers.ser");
            if (file.exists()) {
                objOut = new MyObjectOutputStream(new FileOutputStream(file, append));
                objOut.writeObject(customer);
            } else {
                objOut = new ObjectOutputStream(new FileOutputStream(file, append));
                objOut.writeObject(customer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                objOut.close();
            } catch (Exception e) {
            }
        }
    }

    public ArrayList<Customer> readCustomers () {
        ArrayList<Customer> list = new ArrayList<>();
        ObjectInputStream objIn = null;
        try {
            File file = new File("Customers.ser");
            objIn = new ObjectInputStream(new FileInputStream(file));
            while (true) {
                Customer stu = (Customer)objIn.readObject();
                list.add(stu);
            }
        } catch (ClassNotFoundException e) {
            return list;
        } catch (EOFException e) {
            return list;
        } catch (Exception e) {
            return list;
        } finally {
            try {
                objIn.close();
                return list;
            } catch (Exception e) {
            }
        }
    }

    public void writeFile (Rider rider, boolean append) {
        ObjectOutputStream objOut = null;
        try {
            File file = new File("Riders.ser");
            if (file.exists()) {
                objOut = new MyObjectOutputStream(new FileOutputStream(file, append));
                objOut.writeObject(rider);
            } else {
                objOut = new ObjectOutputStream(new FileOutputStream(file, append));
                objOut.writeObject(rider);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                objOut.close();
            } catch (Exception e) {
            }
        }
    }

    public ArrayList<Rider> readRiders () {
        ArrayList<Rider> list = new ArrayList<>();
        ObjectInputStream objIn = null;
        try {
            File file = new File("Riders.ser");
            objIn = new ObjectInputStream(new FileInputStream(file));
            while (true) {
                Rider stu = (Rider)objIn.readObject();
                list.add(stu);
            }
        } catch (ClassNotFoundException e) {
            return list;
        } catch (EOFException e) {
            return list;
        } catch (Exception e) {
            return list;
        } finally {
            try {
                objIn.close();
                return list;
            } catch (Exception e) {
            }
        }
    }

    public void addRide(ArrayList<Ride> rides) {
        ObjectOutputStream objOut = null;
        try {
            File file = new File("Rides.ser");
            objOut = new ObjectOutputStream(new FileOutputStream(file, false));
            objOut.writeObject(rides.get(0));
            for (int i = 1; i < rides.size(); i++) {
                objOut = new MyObjectOutputStream(new FileOutputStream(file, true));
                objOut.writeObject(rides.get(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                objOut.close();
            } catch (Exception e) {
            }
        }
    }

    public ArrayList<Ride> readRides() {
        ArrayList<Ride> list = new ArrayList<>();
        ObjectInputStream objIn = null;
        try {
            File file = new File("Rides.ser");
            objIn = new ObjectInputStream(new FileInputStream(file));
            while (true) {
                Ride stu = (Ride)objIn.readObject();
                list.add(stu);
            }
        } catch (ClassNotFoundException e) {
            return list;
        } catch (EOFException e) {
            return list;
        } catch (Exception e) {
            return list;
        } finally {
            try {
                objIn.close();
                return list;
            } catch (Exception e) {
            }
        }
    }

    public void addRideBike(ArrayList<Ride> rides) {
        ObjectOutputStream objOut = null;
        try {
            File file = new File("RidesBike.ser");
            objOut = new ObjectOutputStream(new FileOutputStream(file, false));
            objOut.writeObject(rides.get(0));
            for (int i = 1; i < rides.size(); i++) {
                objOut = new MyObjectOutputStream(new FileOutputStream(file, true));
                objOut.writeObject(rides.get(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                objOut.close();
            } catch (Exception e) {
            }
        }
    }

    public ArrayList<Ride> readRidesBike() {
        ArrayList<Ride> list = new ArrayList<>();
        ObjectInputStream objIn = null;
        try {
            File file = new File("RidesBike.ser");
            objIn = new ObjectInputStream(new FileInputStream(file));
            while (true) {
                Ride stu = (Ride)objIn.readObject();
                list.add(stu);
            }
        } catch (ClassNotFoundException e) {
            return list;
        } catch (EOFException e) {
            return list;
        } catch (Exception e) {
            return list;
        } finally {
            try {
                objIn.close();
                return list;
            } catch (Exception e) {
            }
        }
    }

    public Person search(String username, String type) {
        if (type.equals("driver")) {
            ArrayList<Driver> list = readDrivers();
            for (Driver d : list) {
                if (d.getUsername().equals(username)) {
                    return d;
                }
            }
        } else if (type.equals("rider")) {
            ArrayList<Rider> list = readRiders();
            for (Rider d : list) {
                if (d.getUsername().equals(username)) {
                    return d;
                }
            }
        } else if (type.equals("customer")) {
            ArrayList<Customer> list = readCustomers();
            for (Customer d : list) {
                if (d.getUsername().equals(username)) {
                    return d;
                }
            }
        }
        return null;
    }

    public void update(ArrayList<Driver> drivers, String str) {
        ObjectOutputStream objOut = null;
        try {
            File file = new File("Drivers.ser");
            objOut = new ObjectOutputStream(new FileOutputStream(file, false));
            objOut.writeObject(drivers.get(0));
            for (int i = 1; i < drivers.size(); i++) {
                objOut = new MyObjectOutputStream(new FileOutputStream(file, true));
                objOut.writeObject(drivers.get(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                objOut.close();
            } catch (Exception e) {
            }
        }
    }

    public void update(ArrayList<Rider> riders, int j) {
        ObjectOutputStream objOut = null;
        try {
            File file = new File("Riders.ser");
            objOut = new ObjectOutputStream(new FileOutputStream(file, false));
            objOut.writeObject(riders.get(0));
            for (int i = 1; i < riders.size(); i++) {
                objOut = new MyObjectOutputStream(new FileOutputStream(file, true));
                objOut.writeObject(riders.get(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                objOut.close();
            } catch (Exception e) {
            }
        }
    }

    public void update(ArrayList<Customer> customers, boolean b) {
        ObjectOutputStream objOut = null;
        try {
            File file = new File("Customers.ser");
            objOut = new ObjectOutputStream(new FileOutputStream(file, false));
            objOut.writeObject(customers.get(0));
            for (int i = 1; i < customers.size(); i++) {
                objOut = new MyObjectOutputStream(new FileOutputStream(file, true));
                objOut.writeObject(customers.get(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                objOut.close();
            } catch (Exception e) {
            }
        }
    }
}