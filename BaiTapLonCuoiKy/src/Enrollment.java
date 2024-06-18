import java.util.HashMap;
import java.util.ArrayList;
import java.util.*;
import java.sql.*;
public class Enrollment {
    private HashMap<Student, ArrayList<Course>> enrollments;
    private HashMap<Course, HashMap<Student, Float>> coursePoints;
    // public List<HashMap<Course, HashMap<Student, Float>>> listHashMap; 
    private HashMap<Student, ArrayList<Float>> gpa;

    private ArrayList<Course> courses;
    private ArrayList<Student> students;
    public Enrollment() {
        enrollments = new HashMap<>();
        coursePoints = new HashMap<>();
        // listHashMap = new ArrayList<>();
        gpa = new HashMap<>();
    }

    public void enroll(Student student, Course course, float point) {
        if (course.enrollStudent(student)) {
            enrollments.putIfAbsent(student, new ArrayList<>());
            enrollments.get(student).add(course);
            coursePoints.putIfAbsent(course, new HashMap<>());
            coursePoints.get(course).put(student, point);
            gpa.putIfAbsent(student, new ArrayList<>());
            gpa.get(student).add(point);
            getCourses(student);
            getPoint(student, course);
            getStudentsPoints(course,student);
            System.out.println(student.getName() + " đã tham gia khóa học " + course.getCourseName());
        } else {
            System.out.println("Khóa học đã đủ thành viên: " + student.getName());
        }
    }

    public ArrayList<Course> getCourses(Student student) {
        return enrollments.getOrDefault(student, new ArrayList<>());
    }

    public Float getPoint(Student student, Course course) {
        if (coursePoints.containsKey(course) && coursePoints.get(course).containsKey(student)) {
            System.out.println("da them diem: " + coursePoints.get(course).get(student));
            return coursePoints.get(course).get(student);
        } else {
            return null;
        }
    }

    public HashMap<Student, Float> getStudentsPoints(Course course, Student student) {
        System.out.println(coursePoints.getOrDefault(course, new HashMap<>()));
        System.out.println(coursePoints.getOrDefault(course, new HashMap<>()).get(student));
        return coursePoints.getOrDefault(course, new HashMap<>());
    }
    public float getStudentGPA(Student student) {
        String jdbcURL = "jdbc:mysql://localhost:3306/cosodaotao";
        String jdbcDriver = "com.mysql.cj.jdbc.Driver";
        String user = "root";
        String password = "dinhthai2004";
        Connection connection = null;
        float sum = 0;
        int count = 0;
        try {
            connection = DriverManager.getConnection(jdbcURL, user, password);
            String query = "SELECT point FROM point WHERE studentid = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, student.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                float point = resultSet.getFloat("point");
                sum += point;
                count++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        if (count == 0) return 0; // To avoid division by zero
        return sum / count;
    }
    
    
}
