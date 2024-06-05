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
        JComboBox<String> facultyComboBox = new JComboBox<>();

        for (Faculty faculty : faculties) {
            facultyComboBox.addItem(faculty.getName());
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
                String facultyName = (String) facultyComboBox.getSelectedItem();
                Faculty faculty = null;
                String user = "root"; 
                String password = "dinhthai2004"; 
                Connection connection = null;
                Statement statement = null;
                for (Faculty f : faculties) {
                    if (f.getName().equals(facultyName)) {
                        faculty = f;
                        break;
                    }
                }
                try {
                    if (faculty != null && !courseName.isEmpty() && !courseCode.isEmpty()) {
                        courses.add(new Course(courseName, courseCode, capacity, faculty));
                        JOptionPane.showMessageDialog(null, "Course added successfully!");
                        try {
                            Class.forName(jdbcDriver);
                            connection = DriverManager.getConnection(jdbcURL, user, password);
                            System.out.println("Ket noi thanh cong!");
                            statement = connection.createStatement();
                            String checkTypeNameQuery = "select coursecode from course where coursecode = ?";
                            PreparedStatement preparedStatement = connection.prepareStatement(checkTypeNameQuery);
                            preparedStatement.setString(1, courseCode);
                            ResultSet resultSet = preparedStatement.executeQuery();
                            // if (resultSet.next()) {
                                String insertToCourseTableQuery = "insert into course (coursename, coursecode, facultyname) values (?, ?, ?)";
                                preparedStatement = connection.prepareStatement(insertToCourseTableQuery);
                                preparedStatement.setString(1, courseName);
                                preparedStatement.setString(2, courseCode);
                                preparedStatement.setString(3, faculty.getName());
                                preparedStatement.executeUpdate();
                                System.out.println("Them course vao co so du lieu thanh cong");
                            // }
                   

                        } catch (Exception err2) {
                            System.err.println(err2);
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
}
