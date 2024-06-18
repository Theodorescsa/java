import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class SigninFrame extends JFrame {
    final static String jdbcURL = "jdbc:mysql://localhost:3306/CoSoDaoTao";
    final static String jdbcDriver = "com.mysql.cj.jdbc.Driver";

    public SigninFrame() {
        setTitle("Sign in");
        setSize(500, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 5, 5));

        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField(20);

        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(20);

        inputPanel.add(usernameLabel);
        inputPanel.add(usernameField);
        inputPanel.add(passwordLabel);
        inputPanel.add(passwordField);

        JButton signinButton = new JButton("Sign in");

        signinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                String dbUser = "root";
                String dbPassword = "dinhthai2004";
                Connection connection = null;
                PreparedStatement preparedStatement = null;
                ResultSet resultSet = null;

                try {
                    Class.forName(jdbcDriver);
                    connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
                    System.out.println("Connected to the database successfully!");

                    if (!username.isEmpty() && !password.isEmpty()) {
                        String checkUserQuery = "SELECT * FROM user WHERE username = ? AND password = ?";
                        preparedStatement = connection.prepareStatement(checkUserQuery);
                        preparedStatement.setString(1, username);
                        preparedStatement.setString(2, password);
                        resultSet = preparedStatement.executeQuery();

                        if (resultSet.next()) {
                            JOptionPane.showMessageDialog(null, "Sign in successful!");
                        } else {
                            JOptionPane.showMessageDialog(null, "Invalid username or password!");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Please fill in all fields!");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    try {
                        if (resultSet != null) resultSet.close();
                        if (preparedStatement != null) preparedStatement.close();
                        if (connection != null) connection.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        add(inputPanel, BorderLayout.CENTER);
        add(signinButton, BorderLayout.SOUTH);

        setVisible(true);
    }


}
