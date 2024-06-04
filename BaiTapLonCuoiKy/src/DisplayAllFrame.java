import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DisplayAllFrame extends JFrame {
    private ArrayList<Student> students;
    private ArrayList<Faculty> faculties;
    private ArrayList<Course> courses;

    public DisplayAllFrame(ArrayList<Student> students, ArrayList<Faculty> faculties, ArrayList<Course> courses, Enrollment enrollment) {
        this.students = students;
        this.faculties = faculties;
        this.courses = courses;

        setTitle("Display All Information");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JTextArea textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setEditable(false);

        StringBuilder sb = new StringBuilder();

        sb.append("Students:\n");
        for (Student student : students) {
            sb.append(student.getName()).append(", ID: ").append(student.getId()).append(", Major: ").append(student.getMajor()).append(", Gender").append(student.getGender()).append("\n");
        }

        sb.append("\nFaculties:\n");
        for (Faculty faculty : faculties) {
            sb.append("Name: ").append(faculty.getName()).append(", ID: ").append(faculty.getId()).append(", Department: ").append(faculty.getDepartment()).append(", Gender").append(faculty.getGender()).append("\n");
        }

        sb.append("\nCourses:\n");
        for (int i = 0; i< courses.size();i++) {
            sb.append("Name: ").append(courses.get(i).getCourseName())
              .append(", Code: ").append(courses.get(i).getCourseCode())
              .append(", Capacity: ").append(courses.get(i).getCapacity())
              .append("\n");
            sb.append("Faculty: ").append(courses.get(i).getFaculty().getName())
              .append(", Department: ").append(courses.get(i).getFaculty().getDepartment())
              .append("\n");
            sb.append("Enrolled Students:\n");
        
            for (Student student : courses.get(i).getEnrolledStudents()) {
                enrollment.getStudentsPoints(courses.get(i), student);
                System.out.println("Hoc sinh nay là:" + student);
                
                Float point = enrollment.getStudentsPoints(courses.get(i), student).get(student);
                sb.append("- ").append(student.getName())
                  .append(", Point: ").append(point)
                  .append("\n");
            }
        }
        
        textArea.setText(sb.toString());

        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }
}
