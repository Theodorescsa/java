
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

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

            String createUserTableQuery = "create table if not exists cosodaotao.user "
            + "("
            + "id int auto_increment primary key,"
            + "username varchar(50),"
            + "password varchar(50)"
            + ")";
            statement.executeUpdate(createUserTableQuery);
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
            // String createIndexQueryforStudentTable = "create index studentid_index ON student(studentid)";
            // statement.executeUpdate(createIndexQueryforStudentTable);
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
            // String createIndexQueryforFacultyTable = "create index facultyid_index ON faculty (facultyid)";
            // statement.executeUpdate(createIndexQueryforFacultyTable);
            // String createIndexQueryforFacultyTable2 = "create index _index ON faculty (name)";
            // statement.executeUpdate(createIndexQueryforFacultyTable2);
            String createCourseTableQuery = "create table if not exists cosodaotao.course "
                + "("
                + "id int auto_increment primary key,"
                + "coursename varchar(50),"
                + "coursecode varchar(20),"
                + "capacity int,"
                + "facultyid varchar(20),"
                + "foreign key (facultyid) references faculty(facultyid)"
                + "on delete cascade"
                + ")";
            statement.executeUpdate(createCourseTableQuery);
            // String createIndexQueryforCourseTable = "create index coursecode_index ON course (coursecode)";
            // statement.executeUpdate(createIndexQueryforCourseTable);
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

        // Ensure the user logs in before showing the main menu
        boolean isAuthenticated = login();
        if (isAuthenticated) {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }

            setTitle("University Course Management System");
            setSize(800, 600);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(new BorderLayout(10, 10));

            JPanel mainPanel = new JPanel(new GridLayout(3, 1, 10, 10));
            mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            mainPanel.setBackground(new Color(240, 240, 240));

            JButton manageStudentsButton = new JButton("Manage Students");
            JButton manageFacultiesButton = new JButton("Manage Faculties");
            JButton manageCoursesButton = new JButton("Manage Courses");

            JButton displayAllButton = new JButton("Display All Information");
            JButton displayAllStudentGpaButton = new JButton("Display All Student GPA");
            JButton displayAllFacultyCoursesButton = new JButton("Display All Courses Of Faculty");

            customizeButton(manageStudentsButton);
            customizeButton(manageFacultiesButton);
            customizeButton(manageCoursesButton);

            customizeButton(displayAllButton);
            customizeButton(displayAllStudentGpaButton);
            customizeButton(displayAllFacultyCoursesButton);

            mainPanel.add(manageStudentsButton);
            mainPanel.add(manageFacultiesButton);
            mainPanel.add(manageCoursesButton);

            mainPanel.add(displayAllButton);
            mainPanel.add(displayAllStudentGpaButton);
            mainPanel.add(displayAllFacultyCoursesButton);

            add(mainPanel, BorderLayout.CENTER);

            manageStudentsButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new StudentsMenuFrame(courses, faculties, students, enrollment);
                }
            });

            manageFacultiesButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new FacultiesMenuFrame(courses, faculties, students, enrollment);
                }
            });

            manageCoursesButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new CourseMenuFrame(courses, faculties, students, enrollment);
                }
            });

            displayAllButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new DisplayAllFrame();
                }
            });

            displayAllStudentGpaButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new GpaFrame(students, enrollment);
                }
            });

            displayAllFacultyCoursesButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new FacultyCoursesFrame(faculties, courses);
                }
            });

            setVisible(true);
        } else {
            System.exit(0);
        }
    }

    private boolean login() {
        JDialog loginDialog = new JDialog(this, "Login", true);
        loginDialog.setSize(300, 200);
        loginDialog.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField(20);
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(20);

        inputPanel.add(usernameLabel);
        inputPanel.add(usernameField);
        inputPanel.add(passwordLabel);
        inputPanel.add(passwordField);

        JButton loginButton = new JButton("Login");
        loginDialog.add(inputPanel, BorderLayout.CENTER);
        loginDialog.add(loginButton, BorderLayout.SOUTH);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (authenticateUser(username, password)) {
                    loginDialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(loginDialog, "Invalid username or password!", "Login Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        loginDialog.setLocationRelativeTo(null);
        loginDialog.setVisible(true);

        return true;
    }

    private boolean authenticateUser(String username, String password) {
        String dbUser = "root";
        String dbPassword = "dinhthai2004";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            Class.forName(jdbcDriver);
            connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);

            String checkUserQuery = "SELECT * FROM user WHERE username = ? AND password = ?";
            preparedStatement = connection.prepareStatement(checkUserQuery);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();

            return resultSet.next();
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

        return false;
    }

    private void customizeButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(new Color(130, 130, 130));
        button.setForeground(Color.GRAY);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }
    public void loadDataFromDatabase() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
    
        try {
            Class.forName(jdbcDriver);
            connection = DriverManager.getConnection(jdbcURL, "root", "dinhthai2004");
    
            // Load students
            ArrayList<Student> students = new ArrayList<>();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM student");
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String studentId = resultSet.getString("studentid");
                String getgender = resultSet.getString("gender");
                boolean gender = true;
                if(getgender == "nam") {
                    gender = true;
                }
                if(getgender == "nu") {
                    gender = false;
                }
                String major = resultSet.getString("major");
                Student student = new Student(name, studentId, gender, major);
                students.add(student);
            }
    
            // Load faculties
            ArrayList<Faculty> faculties = new ArrayList<>();
            resultSet = statement.executeQuery("SELECT * FROM faculty");
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String facultyId = resultSet.getString("facultyid");
                String getgender = resultSet.getString("gender");
                boolean gender = true;
                if(getgender == "nam") {
                    gender = true;
                }
                if(getgender == "nu") {
                    gender = false;
                }
                String department = resultSet.getString("department");
                Faculty faculty = new Faculty(name, facultyId, gender, department);
                faculties.add(faculty);
            }
    
            // Load courses
            ArrayList<Course> courses = new ArrayList<>();
            resultSet = statement.executeQuery("SELECT * FROM course");
            while (resultSet.next()) {
                String courseName = resultSet.getString("coursename");
                String courseCode = resultSet.getString("coursecode");
                int capacity = resultSet.getInt("capacity");
                String facultyId = resultSet.getString("facultyid");
                // You may need to find the faculty object corresponding to facultyId
                Faculty faculty = new Faculty(courseName, facultyId, rootPaneCheckingEnabled, facultyId);
                for (Faculty f : faculties) {
                    if (f.getId().equals(facultyId)) {
                        faculty = f;
                        break;
                    }
                }
                Course course = new Course(courseName, courseCode, capacity, faculty);
                courses.add(course);
            }
    
            // Assign the loaded data to instance variables
            this.students = students;
            this.faculties = faculties;
            this.courses = courses;
            System.out.println("Import du lieu thanh cong");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void main(String[] args) {
        MainFrame newFrame = new MainFrame();
        newFrame.loadDataFromDatabase();
        newFrame.connectToDatabase();
    }
}
