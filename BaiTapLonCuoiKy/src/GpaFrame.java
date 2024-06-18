import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GpaFrame extends JFrame {
    private ArrayList<Student> students;
    private Enrollment enrollment;

    public GpaFrame(ArrayList<Student> students, Enrollment enrollment) {
        this.students = students;
        this.enrollment = enrollment;

        setTitle("GPA Information");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Column names for the table
        String[] columns = {"Student Name", "Student ID", "GPA"};
        // Data for the table
        Object[][] data = new Object[students.size()][columns.length];
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            data[i][0] = student.getName();
            data[i][1] = student.getId();
            data[i][2] = enrollment.getStudentGPA(student);
        }

        // Create the table with the data
        JTable gpaTable = new JTable(data, columns);
        JScrollPane scrollPane = new JScrollPane(gpaTable);

        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }
}