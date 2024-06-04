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
            System.out.println("Ket noi thanh cong!");
            statement = connection.createStatement();
         
            
        } catch (Exception e) {
            System.err.println(e);
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
                new DisplayAllFrame(students, faculties, courses);
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
