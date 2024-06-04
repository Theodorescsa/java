import java.util.ArrayList;

public class Course {
    private String courseName;
    private String courseCode;
    private int capacity;
    private Faculty faculty;
    private float pointOfStudents; // Tổng điểm của các sinh viên trong khóa học này
    private ArrayList<Student> enrolledStudents;

    public Course(String courseName, String courseCode, int capacity, Faculty faculty) {
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.capacity = capacity;
        this.faculty = faculty;
        this.enrolledStudents = new ArrayList<>();
        this.faculty.addCourse(this); // Thêm khóa học này vào danh sách khóa học của giảng viên
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

    public float getPointOfStudents() {
        return pointOfStudents;
    }

    public void setPointOfStudents(float pointOfStudents) {
        this.pointOfStudents = pointOfStudents;
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
