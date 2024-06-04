import java.util.HashMap;
import java.util.ArrayList;
import java.util.*;

public class Enrollment {
    private HashMap<Student, ArrayList<Course>> enrollments;
    private HashMap<Course, HashMap<Student, Float>> coursePoints;
    public HashMap<String, HashMap<String, Float>> infoHashmap;
    public List<HashMap<Course, HashMap<Student, Float>>> listHashMap; 
    public Enrollment() {
        enrollments = new HashMap<>();
        coursePoints = new HashMap<>();
        listHashMap = new ArrayList<>();
        infoHashmap = new HashMap<>();
    }

    public void enroll(Student student, Course course, float point) {
        if (course.enrollStudent(student)) {
            enrollments.putIfAbsent(student, new ArrayList<>());
            enrollments.get(student).add(course);
            coursePoints.putIfAbsent(course, new HashMap<>());
            coursePoints.get(course).put(student, point);
            infoHashmap.putIfAbsent(course.getCourseName(), new HashMap<>());
            infoHashmap.get(course.getCourseName()).put(student.getName(), point);
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

}
