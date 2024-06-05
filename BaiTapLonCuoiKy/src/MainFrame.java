import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.sql.*;
import java.util.*;
public class MainFrame extends JFrame {
    private Enrollment enrollment;
    private ArrayList<Student> students;
    private ArrayList<Faculty> faculties;
    private ArrayList<Course> courses;
    final static String jdbcURL = "jdbc:mysql://localhost:3306/CoSoDaoTao";
    final static String jdbcDriver = "com.mysql.cj.jdbc.Driver"; 

    public void connectToDatabase() {
        String user = "root";
        String password = "dinhthai2004";
        Connection connection = null;
        Statement statement = null;

        try {
            Class.forName(jdbcDriver); 
            connection = DriverManager.getConnection(jdbcURL, user, password);
            System.out.println("Kết nối thành công!");

            statement = connection.createStatement();

  
            String createStudentTableQuery = "create table if not exists cosodaotao.student "
                + "("
                + "id int auto_increment primary key,"
                + "name varchar(50),"
                + "studentid varchar(20),"
                + "gender varchar(20),"
                + "major varchar (50)"
                + ")";
            statement.executeUpdate(createStudentTableQuery);
            System.out.println("Đã tạo thành công bảng student");
            String createIndexQueryforStudentTable = "create index studentid_index ON student(studentid)";
            statement.executeUpdate(createIndexQueryforStudentTable);
            String createFacultyTableQuery = "create table if not exists cosodaotao.faculty "
                + "("
                + "id int auto_increment primary key,"
                + "name varchar(50),"
                + "facultyid varchar(20),"
                + "gender varchar(20),"
                + "department varchar (50)"
                + ")";
            statement.executeUpdate(createFacultyTableQuery);
            System.out.println("Đã tạo thành công bảng faculty");
            String createIndexQueryforFacultyTable = "create index facultyid_index ON faculty (facultyid)";
            statement.executeUpdate(createIndexQueryforFacultyTable);
            String createIndexQueryforFacultyTable2 = "create index name_index ON faculty (name)";
            statement.executeUpdate(createIndexQueryforFacultyTable2);
            String createCourseTableQuery = "create table if not exists cosodaotao.course "
                + "("
                + "id int auto_increment primary key,"
                + "coursename varchar(50),"
                + "coursecode varchar(20),"
                + "facultyname varchar(50),"
                + "foreign key (facultyname) references faculty(name)"
                + "on delete cascade"
                + ")";
            statement.executeUpdate(createCourseTableQuery);
            String createIndexQueryforCourseTable = "create index coursecode_index ON course (coursecode)";
            statement.executeUpdate(createIndexQueryforCourseTable);
            System.out.println("Đã tạo thành công bảng course");
            String createPointTableQuery = "create table if not exists cosodaotao.point "
            + "("
            + "id int auto_increment primary key,"
            + "point float,"
            + "coursecode varchar(20),"
            + "facultyid varchar(20),"
            + "studentid varchar(20),"
            + "foreign key (facultyid) references faculty(facultyid)"
            + "on delete cascade,"
            + "foreign key (studentid) references student(studentid)"
            + "on delete cascade,"
            + "foreign key (coursecode) references course(coursecode)"
            + "on delete cascade"
            + ")";
            statement.executeUpdate(createPointTableQuery);
            System.out.println("Đã tạo thành công bảng point");


        } catch (Exception e) {
            System.err.println(e);
        } finally {
         
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println("Lỗi đóng kết nối: " + e);
            }
        }
    }

  
    public MainFrame() {
        enrollment = new Enrollment();
        students = new ArrayList<>();
        faculties = new ArrayList<>();
        courses = new ArrayList<>();
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        setTitle("University Course Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel mainPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(240, 240, 240));  

        JButton manageStudentsButton = new JButton("Manage Students");
        JButton manageFacultiesButton = new JButton("Manage Faculties");
        JButton addCourseButton = new JButton("Add Course");
        JButton manageCoursesButton = new JButton("Manage Courses");
        JButton displayAllButton = new JButton("Display All Information");

        // Customize buttons
        customizeButton(manageStudentsButton);
        customizeButton(manageFacultiesButton);
        customizeButton(addCourseButton);
        customizeButton(manageCoursesButton);
        customizeButton(displayAllButton);

        mainPanel.add(manageStudentsButton);
        mainPanel.add(manageFacultiesButton);
        mainPanel.add(addCourseButton);
        mainPanel.add(manageCoursesButton);
        mainPanel.add(displayAllButton);

        add(mainPanel, BorderLayout.CENTER);

        manageStudentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ManageStudentsFrame(students);
            }
        });

        manageFacultiesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ManageFacultiesFrame(faculties);
            }
        });

        addCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddCourseFrame(courses, faculties);
            }
        });

        manageCoursesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ManageCoursesFrame(courses, faculties, students, enrollment);
            }
        });

        displayAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DisplayAllFrame(students, faculties, courses, enrollment);
            }
        });

        setVisible(true);
    }

    private void customizeButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(new Color(130,130,130)); 
        button.setForeground(Color.GRAY);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    public static void main(String[] args) {
        MainFrame newFrame = new MainFrame();
        newFrame.connectToDatabase();

    }
}
