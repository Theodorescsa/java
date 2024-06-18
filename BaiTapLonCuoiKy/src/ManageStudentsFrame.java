// ManageStudentsFrame.java
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.sql.*;

public class ManageStudentsFrame extends JFrame {
    private ArrayList<Student> students;
    final static String jdbcURL = "jdbc:mysql://localhost:3306/CoSoDaoTao";
    final static String jdbcDriver = "com.mysql.cj.jdbc.Driver";

    public ManageStudentsFrame(ArrayList<Student> students) {
        this.students = students;

        setTitle("Manage Students");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(6, 2, 5, 5));

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField(20);

        JLabel idLabel = new JLabel("ID:");
        JTextField idField = new JTextField(20);

        JLabel majorLabel = new JLabel("Major:");
        JTextField majorField = new JTextField(20);

        JLabel genderLabel = new JLabel("Gender:");
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JRadioButton maleRadioButton = new JRadioButton("Male");
        JRadioButton femaleRadioButton = new JRadioButton("Female");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleRadioButton);
        genderGroup.add(femaleRadioButton);
        genderPanel.add(maleRadioButton);
        genderPanel.add(femaleRadioButton);

        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(idLabel);
        inputPanel.add(idField);
        inputPanel.add(majorLabel);
        inputPanel.add(majorField);
        inputPanel.add(genderLabel);
        inputPanel.add(genderPanel);

        JButton addButton = new JButton("Add Student");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String id = idField.getText();
                String major = majorField.getText();
                boolean gender = maleRadioButton.isSelected();
                String user = "root"; 
                String password = "dinhthai2004"; 
                Connection connection = null;
                PreparedStatement preparedStatement = null;
                try {
                    Class.forName(jdbcDriver);
                    connection = DriverManager.getConnection(jdbcURL, user, password);
                    System.out.println("Connected to the Students database!");

                    if (!name.isEmpty() && !id.isEmpty() && !major.isEmpty() && (maleRadioButton.isSelected() || femaleRadioButton.isSelected())) {
                        String checkStudentIdQuery = "SELECT studentid FROM student WHERE studentid = ?";
                        preparedStatement = connection.prepareStatement(checkStudentIdQuery);
                        preparedStatement.setString(1, id);
                        ResultSet resultSet = preparedStatement.executeQuery();

                        if (resultSet.next()) {
                            // Student ID already exists
                            JOptionPane.showMessageDialog(null, "Student ID already exists. Please enter a different ID.");
                        } else {
                            // Student ID does not exist, proceed to insert
                            String genderValue = maleRadioButton.isSelected() ? "nam" : "nu";
                            String insertStudentQuery = "INSERT INTO student (name, studentid, gender, major) VALUES (?, ?, ?, ?)";
                            preparedStatement = connection.prepareStatement(insertStudentQuery);
                            preparedStatement.setString(1, name);
                            preparedStatement.setString(2, id);
                            preparedStatement.setString(3, genderValue);
                            preparedStatement.setString(4, major);
                            preparedStatement.executeUpdate();

                            // Add to local list
                            students.add(new Student(name, id, gender, major));

                            JOptionPane.showMessageDialog(null, "Student added successfully!");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Please fill in all fields!");
                    }
                } catch (Exception err) {
                    System.out.println(err);
                } finally {
                    try {
                        if (preparedStatement != null) {
                            preparedStatement.close();
                        }
                        if (connection != null) {
                            connection.close();
                        }
                    } catch (SQLException sqlException) {
                        sqlException.printStackTrace();
                    }
                }
            }
        });

        add(inputPanel, BorderLayout.CENTER);
        add(addButton, BorderLayout.SOUTH);

        setVisible(true);
    }
}
