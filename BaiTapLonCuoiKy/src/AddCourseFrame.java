import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.sql.*;
import java.util.*;;
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

        for (Faculty faculty : faculties) {
            facultyComboBox.addItem(new ComboBoxItem(faculty.getId(), faculty.getName()));
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
                Faculty faculty = null;
                String user = "root";
                String password = "dinhthai2004";
                Connection connection = null;
                Statement statement = null;

                for (Faculty f : faculties) {
                    if (f.getId().equals(facultyId)) {
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

                            String insertToCourseTableQuery = "insert into course (coursename, coursecode, facultyid) values (?, ?, ?)";
                            preparedStatement = connection.prepareStatement(insertToCourseTableQuery);
                            preparedStatement.setString(1, courseName);
                            preparedStatement.setString(2, courseCode);
                            preparedStatement.setString(3, faculty.getId());
                            preparedStatement.executeUpdate();
                            System.out.println("Them course vao co so du lieu thanh cong");

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
