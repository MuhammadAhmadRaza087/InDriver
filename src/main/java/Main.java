import javax.swing.*;
import java.io.File;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
       File file = new File("Admin.ser");
       if(!file.exists()){
           Admin admin = new Admin("Admin", "admin123", "asdf1234", "03123456789",
                   "12345-1234567-1");
           AdminForm adminForm = new AdminForm(admin);
           FileClass fileClass = new FileClass();
           fileClass.writeFile(admin);
       }
        LoginForm login = new LoginForm();

//        System.out.println(fileClass.readAdmin().toString());
    }
}
