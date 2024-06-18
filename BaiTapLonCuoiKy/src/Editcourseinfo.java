import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Editcourseinfo extends JFrame {

    private JComboBox<ComboBoxItem> facultyComboBox;
    private final static String jdbcURL = "jdbc:mysql://localhost:3306/CoSoDaoTao";
    private final static String jdbcDriver = "com.mysql.cj.jdbc.Driver";

    public Editcourseinfo() {
        setTitle("Edit Course Info");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        setupCourseInfo();
        setVisible(true);
    }

    private void setupCourseInfo() {
        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 5, 5));

        JLabel courseCodeLabel = new JLabel("Enter Course Code:");
        JTextField courseCodeField = new JTextField(20);
        JButton searchButton = new JButton("Search");
        inputPanel.add(courseCodeLabel);
        inputPanel.add(courseCodeField);
        inputPanel.add(searchButton);

        facultyComboBox = new JComboBox<>();

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String courseCode = courseCodeField.getText();
                boolean found = false;

                JLabel nameLabel = new JLabel("Course Name:");
                JTextField nameField = new JTextField(20);

                JLabel codeLabel = new JLabel("Course Code:");
                JTextField codeField = new JTextField(20);

                JLabel capacityLabel = new JLabel("Capacity:");
                JTextField capacityField = new JTextField(20);

                String user = "root";
                String password = "dinhthai2004";
                Connection connection = null;
                Statement statement = null;
                try {
                    Class.forName(jdbcDriver);
                    connection = DriverManager.getConnection(jdbcURL, user, password);

                    String selectQuery = "SELECT * FROM course WHERE courseCode = ?";
                    PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
                    preparedStatement.setString(1, courseCode);
                    ResultSet resultSet = preparedStatement.executeQuery();

                    if (resultSet.next()) {
                        nameField.setText(resultSet.getString("courseName"));
                        codeField.setText(resultSet.getString("courseCode"));
                        capacityField.setText(String.valueOf(resultSet.getInt("capacity")));
                        String facultyId = resultSet.getString("facultyId");
                        selectFaculty(facultyId);
                        found = true;
                    }

                } catch (SQLException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }

                if (!found) {
                    JOptionPane.showMessageDialog(null, "Course not found!");
                }

                if (found) {
                    inputPanel.remove(courseCodeLabel);
                    inputPanel.remove(courseCodeField);
                    inputPanel.remove(searchButton);

                    inputPanel.add(nameLabel);
                    inputPanel.add(nameField);
                    inputPanel.add(codeLabel);
                    inputPanel.add(codeField);
                    inputPanel.add(capacityLabel);
                    inputPanel.add(capacityField);
                    loadFaculties();
                    inputPanel.add(new JLabel("Faculty:"));
                    inputPanel.add(facultyComboBox);
                    JButton updateButton = new JButton("Update Course");
                    updateButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            ComboBoxItem selectedFacultyItem = (ComboBoxItem) facultyComboBox.getSelectedItem();
                            String facultyId = selectedFacultyItem.getId();
                            String updatedName = nameField.getText();
                            String updatedCode = codeField.getText();
                            int updatedCapacity = Integer.parseInt(capacityField.getText());

                            if (!updatedName.isEmpty() && !updatedCode.isEmpty() && updatedCapacity > 0) {
                                try {
                                    Connection connection = DriverManager.getConnection(jdbcURL, user, password);

                                    String updateCourseQuery = "UPDATE course SET courseName = ?, courseCode = ?, capacity = ?, facultyId = ? WHERE courseCode = ?";
                                    PreparedStatement preparedStatement = connection.prepareStatement(updateCourseQuery);
                                    preparedStatement.setString(1, updatedName);
                                    preparedStatement.setString(2, updatedCode);
                                    preparedStatement.setInt(3, updatedCapacity);
                                    preparedStatement.setString(4, facultyId);
                                    preparedStatement.setString(5, courseCode);
                                    preparedStatement.executeUpdate();

                                    JOptionPane.showMessageDialog(null, "Course updated successfully!");
                                } catch (SQLException ex) {
                                    ex.printStackTrace();
                                    JOptionPane.showMessageDialog(null, "Error updating course in the database.");
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Please fill in all fields correctly!");
                            }
                        }
                    });

                    add(inputPanel, BorderLayout.CENTER);
                    add(updateButton, BorderLayout.SOUTH);
                    revalidate();
                    repaint();
                } else {
                    JOptionPane.showMessageDialog(null, "Course not found!");
                }
            }
        });
        add(inputPanel, BorderLayout.NORTH);
    }

    private void loadFaculties() {
        String user = "root";
        String password = "dinhthai2004";
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName(jdbcDriver);
            connection= DriverManager.getConnection(jdbcURL, user, password);
            String selectQuery = "SELECT * FROM faculty";
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectQuery);
            while (resultSet.next()) {
                String facultyId = resultSet.getString("facultyId");
                String facultyName = resultSet.getString("name");
                facultyComboBox.addItem(new ComboBoxItem(facultyId, facultyName));
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void selectFaculty(String facultyId) {
        for (int i = 0; i < facultyComboBox.getItemCount(); i++) {
            ComboBoxItem item = facultyComboBox.getItemAt(i);
            if (item.getId().equals(facultyId)) {
                facultyComboBox.setSelectedIndex(i);
                break;
            }
        }
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Editcourseinfo();
            }
        });
    }
}

