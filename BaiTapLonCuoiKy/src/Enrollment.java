import java.util.HashMap;
import java.util.ArrayList;

public class Enrollment {
    private HashMap<Student, ArrayList<Course>> enrollments;

    public Enrollment() {
        enrollments = new HashMap<>();
    }

    public void enroll(Student student, Course course) {
        if (course.enrollStudent(student)) {
            enrollments.putIfAbsent(student, new ArrayList<>());
            enrollments.get(student).add(course);
            System.out.println(student.getName() + " has been enrolled in " + course.getCourseName());
        } else {
            System.out.println("Course is full. Cannot enroll " + student.getName());
        }
    }

    public ArrayList<Course> getCourses(Student student) {
        return enrollments.getOrDefault(student, new ArrayList<>());
    }
}

