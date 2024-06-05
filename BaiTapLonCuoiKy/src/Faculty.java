import java.util.ArrayList;

public class Faculty extends Person {
    private String department;
    private ArrayList<Course> courses;
    public Faculty(String name, String id,boolean gender, String department) {
        super(name, id, gender);
        this.department = department;
        this.courses = new ArrayList<>();
    }
    public String getDepartment() {
        return department;
    }
    public void addCourse(Course course) {
        courses.add(course);
    }
    public ArrayList<Course> getCourses() {
        return courses;
    }
    @Override
    public void displayInfo() {
        System.out.println("Faculty Name: " + getName() + ", ID: " + getId() + ", Department: " + department);
        System.out.println("Courses teaching: ");
        for (Course course : courses) {
            System.out.println(course.getCourseName());
        }
    }
}
