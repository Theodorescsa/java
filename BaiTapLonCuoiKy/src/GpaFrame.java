import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GpaFrame extends JFrame {
    private ArrayList<Student> students;
    private ArrayList<Course> courses;

    public GpaFrame(ArrayList<Student> students, Enrollment enrollment) {
        this.students = students;
      
        setTitle("Gpa Information");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JTextArea textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setEditable(false);

        StringBuilder sb = new StringBuilder();

        sb.append("Students:\n");
        for (Student student : students) {
            sb.append(student.getName()).append(", ID: ").append(enrollment.getStudentGPA(student)).append("\n");
        }


      
        
        textArea.setText(sb.toString());

        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }
}
