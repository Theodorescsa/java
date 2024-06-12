import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class FacultyCoursesFrame extends JFrame {
    private ArrayList<Faculty> faculties;
    private ArrayList<Course> courses;
    public FacultyCoursesFrame(ArrayList<Faculty> faculties, ArrayList<Course> courses) {

        this.faculties = faculties;
        setTitle("Courses Information");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JTextArea textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setEditable(false);

        StringBuilder sb = new StringBuilder();

        sb.append("Faculty:\n");
        for (Faculty faculty : faculties) {
            sb.append(faculty.getName()).append(", All Courses: ").append("\n");

            for (Course course : courses) {
                if (course.getFaculty() == faculty) {
                   sb.append("-").append(course.getCourseName()).append("\n");
                }
           
            }
            }
        
        textArea.setText(sb.toString());

        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }
}
