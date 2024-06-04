import java.util.ArrayList;

public class Course {
    private String courseName;
    private String courseCode;
    private int capacity;
    private Faculty faculty;
    private ArrayList<Student> enrolledStudents;
    
    public Course(String courseName, String courseCode, int capacity, Faculty faculty) {
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.capacity = capacity;
        this.faculty = faculty;
        this.enrolledStudents = new ArrayList<>();
        this.faculty.addCourse(this); // Add this course to the faculty's course list
    }

    public String getCourseName() {
        return courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public int getCapacity() {
        return capacity;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public ArrayList<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public boolean enrollStudent(Student student) {
        if (enrolledStudents.size() < capacity) {
            enrolledStudents.add(student);
            return true;
        } else {
            return false;
        }
    }

    public void displayCourseInfo() {
        System.out.println("Course Name: " + courseName + ", Code: " + courseCode + ", Capacity: " + capacity);
        System.out.println("Faculty: " + faculty.getName() + ", Department: " + faculty.getDepartment());
    }
}