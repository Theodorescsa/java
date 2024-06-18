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
        this.faculty.addCourse(this);
    }
    public void setCourseName(String name) {
        this.courseName = name;
    }

    public String getCourseName() {
        return courseName;
    }
    public void setCourseCode(String code) {
        this.courseCode = code;
    }
    public String getCourseCode() {
        return courseCode;
    }
    public void setCourseCapacity(int quantity) {
        this.capacity = quantity;
    }
    public int getCapacity() {
        return capacity;
    }
    public void setCourseFaculty(Faculty faculty) {
        this.faculty = faculty;
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
