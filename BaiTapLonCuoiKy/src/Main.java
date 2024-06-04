import java.sql.*;
import java.util.*;
public class Main {

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

            String createPersonTableQuery = "create table if not exists cosodaotao.person "
                + "("
                + "id int auto_increment primary key,"
                + "name varchar(50),"
                + "personid varchar(20),"
                + "gender varchar(20)"
                + ")";
            statement.executeUpdate(createPersonTableQuery);
            String createIndexQueryforPersonTable = "create index personid_index ON person (personid)";
            statement.executeUpdate(createIndexQueryforPersonTable);
            String createStudentTableQuery = "create table if not exists cosodaotao.student "
                + "("
                + "id int auto_increment primary key,"
                + "name varchar(50),"
                + "studentid varchar(20),"
                + "gender varchar(20),"
                + "major varchar (50),"
                + "personid varchar(20),"
                + "foreign key (personid) references person(personid)"
                + "on delete cascade"
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
                + "department varchar (50),"
                + "personid varchar(20),"
                + "foreign key (personid) references person(personid)"
                + "on delete cascade"
                + ")";
            statement.executeUpdate(createFacultyTableQuery);
            System.out.println("Đã tạo thành công bảng faculty");
            String createIndexQueryforFacultyTable = "create index facultyid_index ON faculty (facultyid)";
            statement.executeUpdate(createIndexQueryforFacultyTable);
            String createCourseTableQuery = "create table if not exists cosodaotao.course "
                + "("
                + "id int auto_increment primary key,"
                + "coursename varchar(50),"
                + "coursecode varchar(20),"
                + "facultyid varchar(20),"
                + "studentid varchar(20),"
                + "foreign key (studentid) references student(studentid)"
                + "on delete cascade,"
                + "foreign key (facultyid) references faculty(facultyid)"
                + "on delete cascade"
                + ")";
            statement.executeUpdate(createCourseTableQuery);
            System.out.println("Đã tạo thành công bảng course");
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


    public static void main(String[] args) {
        Main main = new Main();
        main.connectToDatabase();
        Faculty faculty1 = new Faculty("Tran Dang Hoan", "F12345",true, "Master Java");
        Faculty faculty2 = new Faculty("Dr. Green", "F67890",true, "Mathematics");

        Course course1 = new Course("OOP", "CS101", 2, faculty1);
        Course course2 = new Course("Algorithms", "CS102", 1, faculty1);
        Course course3 = new Course("Calculus", "MATH101", 3, faculty2);

        Student student1 = new Student("John Doe", "S12345",true, "Computer Science");
        Student student2 = new Student("Jane Smith", "S67890",true, "Mathematics");

        Enrollment enrollment = new Enrollment();
        enrollment.enroll(student1, course1,6);
        enrollment.enroll(student2, course1,7);
        enrollment.enroll(student2, course2,8);
        enrollment.enroll(student1, course2,9); 
        enrollment.enroll(student2, course3,10);
        // student1.displayInfo();
        // faculty1.displayInfo();
        // course1.displayCourseInfo();
        System.out.println("Ten khoa hoc|GiangVien|Ten Sinh Vien|Diem");
        System.out.println(enrollment.infoHashmap);
    }
}