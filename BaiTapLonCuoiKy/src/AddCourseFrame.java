import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.sql.*;

public class AddCourseFrame extends JFrame {
    private ArrayList<Course> courses;
    private ArrayList<Faculty> faculties;
    final static String jdbcURL = "jdbc:mysql://localhost:3306/CoSoDaoTao";
    final static String jdbcDriver = "com.mysql.cj.jdbc.Driver";

    public AddCourseFrame(ArrayList<Course> courses, ArrayList<Faculty> faculties) {
        this.courses = courses;
        this.faculties = faculties;
        setTitle("Add Course");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        JLabel courseNameLabel = new JLabel("Course Name:");
        JTextField courseNameField = new JTextField();
        JLabel courseCodeLabel = new JLabel("Course Code:");
        JTextField courseCodeField = new JTextField();
        JLabel capacityLabel = new JLabel("Capacity:");
        JTextField capacityField = new JTextField();
        JLabel facultyLabel = new JLabel("Faculty:");
        JComboBox<ComboBoxItem> facultyComboBox = new JComboBox<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(jdbcURL, "root", "dinhthai2004");
            String query = "SELECT * FROM faculty";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String id = resultSet.getString("facultyid");
                String name = resultSet.getString("name");
                facultyComboBox.addItem(new ComboBoxItem(id, name));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        inputPanel.add(courseNameLabel);
        inputPanel.add(courseNameField);
        inputPanel.add(courseCodeLabel);
        inputPanel.add(courseCodeField);
        inputPanel.add(capacityLabel);
        inputPanel.add(capacityField);
        inputPanel.add(facultyLabel);
        inputPanel.add(facultyComboBox);

        JButton addButton = new JButton("Add Course");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String courseName = courseNameField.getText();
                String courseCode = courseCodeField.getText();
                int capacity = Integer.parseInt(capacityField.getText());
                ComboBoxItem selectedFacultyItem = (ComboBoxItem) facultyComboBox.getSelectedItem();
                String facultyId = selectedFacultyItem.getId();
                // Faculty faculty = null;
                String user = "root";
                String password = "dinhthai2004";
                Connection connection = null;
                PreparedStatement preparedStatement = null;

                for (Faculty f : faculties) {
                    // if (f.getId().equals(facultyId)) {
                    //     faculty = f;
                    //     break;
                    // }
                }

                try {
                    if (true) {
                        try {
                            Class.forName(jdbcDriver);
                            connection = DriverManager.getConnection(jdbcURL, user, password);
                            System.out.println("Connected to the database!");

                            String checkCourseCodeQuery = "SELECT coursecode FROM course WHERE coursecode = ?";
                            preparedStatement = connection.prepareStatement(checkCourseCodeQuery);
                            preparedStatement.setString(1, courseCode);
                            ResultSet resultSet = preparedStatement.executeQuery();

                            if (resultSet.next()) {
                                // Course code already exists
                                JOptionPane.showMessageDialog(null, "Course Code already exists. Please enter a different Course Code.");
                            } else {
                                // Course code does not exist, proceed to insert
                                String insertCourseQuery = "INSERT INTO course (coursename, coursecode, capacity, facultyid) VALUES (?, ?, ?, ?)";
                                preparedStatement = connection.prepareStatement(insertCourseQuery);
                                preparedStatement.setString(1, courseName);
                                preparedStatement.setString(2, courseCode);
                                preparedStatement.setInt(3, capacity);
                                preparedStatement.setString(4, facultyId);

                                preparedStatement.executeUpdate();
                                System.out.println("Course added to the database successfully!");

                                // Add to local list
                                // courses.add(new Course(courseName, courseCode, capacity, faculty));

                                JOptionPane.showMessageDialog(null, "Course added successfully!");
                            }
                        } catch (Exception err2) {
                            System.err.println(err2);
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
                    } else {
                        JOptionPane.showMessageDialog(null, "Please fill in all fields!");
                    }
                } catch (Exception err) {
                    System.err.println(err);
                }
            }
        });

        add(inputPanel, BorderLayout.CENTER);
        add(addButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    private class ComboBoxItem {
        private String id;
        private String name;

        public ComboBoxItem(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getId() {
            return id;
        }

        @Override
        public String toString() {
            return name + " (" + id + ")";
        }
    }
}
