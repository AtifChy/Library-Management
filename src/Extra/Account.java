package Extra;

import Frame.LoginFrame;

import javax.swing.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Account {
    private JFrame parentComponent;
    private String userType;
    private String id;
    private String name;
    private String mail;
    private String password;
    private String gender;
    private File file;
    private BufferedReader reader;
    private LoginFrame loginFrame;

    public Account(JFrame parentComponent, String userType, String name, String password) {
        this.parentComponent = parentComponent;
        this.userType = userType;
        this.name = name;
        this.password = password;
    }

    public Account(JFrame parentComponent, String userType, String id, String name, String mail, String password, String gender) {
        this.parentComponent = parentComponent;
        this.userType = userType;
        this.id = id;
        this.name = name;
        this.mail = mail;
        this.password = password;
        this.gender = gender;
    }

    public JFrame getParentComponent() {
        return parentComponent;
    }

    public String getUserType() {
        return userType;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }

    public String getGender() {
        return gender;
    }

    public void setParentComponent(JFrame parentComponent) {
        this.parentComponent = parentComponent;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void addAccount() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd, hh:mm:ss a");
        String registrationTime = LocalDateTime.now().format(format);
        String userInfo = "===============================================\n" +
                "ID: " + id + "\n" +
                "Name: " + name + "\n" +
                "Email Address: " + mail + "\n" +
                "Password: " + password + "\n" +
                "Gender: " + gender + "\n" +
                "Registration Time: " + registrationTime + "\n" +
                "===============================================\n";

        try {
            if (userType.equals("librarian")) {
                file = new File("src/data/librarian.txt");
            } else if (userType.equals("student")) {
                file = new File("src/data/student.txt");
            }

            File parentDir = file.getParentFile();
            if (!parentDir.exists()) {
                parentDir.mkdirs();
                file.createNewFile();
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
            writer.write(userInfo);
            writer.flush();
            writer.close();

            JOptionPane.showMessageDialog(
                    parentComponent,
                    " Extra.Account creation successful. Now you can try logging in.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            if (loginFrame == null) {
                loginFrame = new LoginFrame();
            }
            loginFrame.setVisible(true);
            parentComponent.setVisible(false);

        } catch (IOException e) {
            JOptionPane.showMessageDialog(
                    parentComponent,
                    "Extra.Account creation failed.",
                    "Failed",
                    JOptionPane.ERROR_MESSAGE
            );
            throw new RuntimeException(e);
        }
    }

    public boolean accountExists(String name, String id) {
        try {
            if (userType.equals("librarian")) {
                file = new File("src/data/librarian.txt");
            } else if (userType.equals("student")) {
                file = new File("src/data/student.txt");
            }

            File parentDir = file.getParentFile();
            if (!parentDir.exists()) {
                parentDir.mkdirs();
                file.createNewFile();
            }

            reader = new BufferedReader(new FileReader(file));
            String line;
            String foundName = null;
            String foundID = null;
            while ((line = reader.readLine()) != null) {
                if (line.contains("Name: ")) {
                    foundName = line.replace("Name: ", "");
                } else if (line.contains("ID: ")) {
                    foundID = line.replace("ID: ", "");
                }

                if (foundName != null && foundID != null) {
                    if (foundName.equals(name) && foundID.equals(id)) {
                        return true;
                    }
                }
            }
            return false;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(
                    parentComponent,
                    "Error while checking account.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            throw new RuntimeException(e);
        }
    }

    public boolean loginAccount() {
        if (userType.equals("librarian")) {
            file = new File("src/data/librarian.txt");
        } else if (userType.equals("student")) {
            file = new File("src/data/student.txt");
        }

        try {
            reader = new BufferedReader(new FileReader(file));
            String line;
            String foundName = null;
            String foundPassword = null;
            while ((line = reader.readLine()) != null) {
                if (line.contains("Name: ")) {
                    foundName = line.replace("Name: ", "");
                } else if (line.contains("Password: ")) {
                    foundPassword = line.replace("Password: ", "");
                }

                if (foundName != null && foundPassword != null) {
                    if (foundName.equals(name) && foundPassword.equals(password)) {
                        JOptionPane.showMessageDialog(
                                parentComponent,
                                "Login successful.",
                                "Success",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                        return true;
                    }
                }
            }
            JOptionPane.showMessageDialog(
                    parentComponent,
                    "Login failed.",
                    "Fail",
                    JOptionPane.ERROR_MESSAGE
            );
        } catch (IOException e) {
            JOptionPane.showMessageDialog(
                    parentComponent,
                    "Login failed.",
                    "Fail",
                    JOptionPane.ERROR_MESSAGE
            );
            throw new RuntimeException(e);
        }
        return false;
    }
}
