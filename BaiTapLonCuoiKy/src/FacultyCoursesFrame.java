import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class FacultyCoursesFrame extends JFrame {
    private ArrayList<Faculty> faculties;
    private ArrayList<Course> courses;

    public FacultyCoursesFrame(ArrayList<Faculty> faculties, ArrayList<Course> courses) {
        this.faculties = faculties;
        this.courses = courses;

        setTitle("Courses Information");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Column names for the table
        String[] columns = {"Faculty Name", "Course Name"};
        // Calculate the number of rows
        int rowCount = 0;
        for (Faculty faculty : faculties) {
            for (Course course : courses) {
                if (course.getFaculty() == faculty) {
                    rowCount++;
                }
            }
        }

        // Data for the table
        Object[][] data = new Object[rowCount][columns.length];
        int rowIndex = 0;
        for (Faculty faculty : faculties) {
            for (Course course : courses) {
                if (course.getFaculty() == faculty) {
                    data[rowIndex][0] = faculty.getName();
                    data[rowIndex][1] = course.getCourseName();
                    rowIndex++;
                }
            }
        }

        // Create the table with the data
        JTable facultyCoursesTable = new JTable(data, columns);
        JScrollPane scrollPane = new JScrollPane(facultyCoursesTable);

        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }
}