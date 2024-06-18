import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DisplayAllFrame extends JFrame {

    public DisplayAllFrame() {
        setTitle("Display All Information");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JTabbedPane tabbedPane = new JTabbedPane();

        // Students Table
        String[] studentColumns = {"Name", "ID", "Major", "Gender"};
        DefaultTableModel studentTableModel = new DefaultTableModel(studentColumns, 0);
        JTable studentTable = new JTable(studentTableModel);
        JScrollPane studentScrollPane = new JScrollPane(studentTable);
        tabbedPane.addTab("Students", studentScrollPane);

        // Faculties Table
        String[] facultyColumns = {"Name", "ID", "Department", "Gender"};
        DefaultTableModel facultyTableModel = new DefaultTableModel(facultyColumns, 0);
        JTable facultyTable = new JTable(facultyTableModel);
        JScrollPane facultyScrollPane = new JScrollPane(facultyTable);
        tabbedPane.addTab("Faculties", facultyScrollPane);

        // Courses and Enrollment Table
        String[] courseEnrollmentColumns = {"Course Name", "Course Code", "Capacity", "Faculty Name", "Faculty Department", "Student Name", "Point"};
        DefaultTableModel courseEnrollmentTableModel = new DefaultTableModel(courseEnrollmentColumns, 0);
        JTable courseEnrollmentTable = new JTable(courseEnrollmentTableModel);
        JScrollPane courseEnrollmentScrollPane = new JScrollPane(courseEnrollmentTable);
        tabbedPane.addTab("Courses & Enrollments", courseEnrollmentScrollPane);

        add(tabbedPane, BorderLayout.CENTER);

        loadStudentData(studentTableModel);
        loadFacultyData(facultyTableModel);
        loadCourseEnrollmentData(courseEnrollmentTableModel);

        setVisible(true);
    }

    private void loadStudentData(DefaultTableModel model) {
        String jdbcURL = "jdbc:mysql://localhost:3306/cosodaotao";
        String jdbcDriver = "com.mysql.cj.jdbc.Driver";
        String user = "root";
        String password = "dinhthai2004";

        try (Connection connection = DriverManager.getConnection(jdbcURL, user, password)) {
            String query = "SELECT * FROM student";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String id = resultSet.getString("id");
                String major = resultSet.getString("major");
                String gender = resultSet.getString("gender");
                model.addRow(new Object[]{name, id, major, gender});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadFacultyData(DefaultTableModel model) {
        String jdbcURL = "jdbc:mysql://localhost:3306/cosodaotao";
        String jdbcDriver = "com.mysql.cj.jdbc.Driver";
        String user = "root";
        String password = "dinhthai2004";

        try (Connection connection = DriverManager.getConnection(jdbcURL, user, password)) {
            String query = "SELECT * FROM faculty";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String id = resultSet.getString("id");
                String department = resultSet.getString("department");
                String gender = resultSet.getString("gender");
                model.addRow(new Object[]{name, id, department, gender});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadCourseEnrollmentData(DefaultTableModel model) {
        String jdbcURL = "jdbc:mysql://localhost:3306/cosodaotao";
        String jdbcDriver = "com.mysql.cj.jdbc.Driver";
        String user = "root";
        String password = "dinhthai2004";

        try (Connection connection = DriverManager.getConnection(jdbcURL, user, password)) {
            String query = "SELECT " +
            "c.coursename, " +
            "c.coursecode, " +
            "c.capacity, " +
            "f.name AS facultyname, " +
            "f.department AS facultydepartment, " +
            "s.name AS studentname, " +
            "p.point " +
            "FROM " +
            "cosodaotao.course c " +
            "INNER JOIN " +
            "point p ON c.courseCode = p.courseCode " +
            "INNER JOIN " +
            "faculty f ON c.facultyid = f.facultyid " +
            "INNER JOIN " +
            "student s ON p.studentid = s.studentid;";

            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String courseName = resultSet.getString("coursename");
                String courseCode = resultSet.getString("coursecode");
                int capacity = resultSet.getInt("capacity");
                String facultyName = resultSet.getString("facultyname");
                String facultyDepartment = resultSet.getString("facultydepartment");
                String studentName = resultSet.getString("studentname");
                float point = resultSet.getFloat("point");
                model.addRow(new Object[]{courseName, courseCode, capacity, facultyName, facultyDepartment, studentName, point});
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
