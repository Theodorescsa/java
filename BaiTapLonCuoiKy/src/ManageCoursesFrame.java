import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ManageCoursesFrame extends JFrame {
    private ArrayList<Course> courses;
    private ArrayList<Faculty> faculties;
    private ArrayList<Student> students;
    private Enrollment enrollment;

    public ManageCoursesFrame(ArrayList<Course> courses, ArrayList<Faculty> faculties, ArrayList<Student> students, Enrollment enrollment) {
        this.courses = courses;
        this.faculties = faculties;
        this.students = students;
        this.enrollment = enrollment;

        setTitle("Manage Courses");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel for enrolling students in courses
        JPanel enrollPanel = new JPanel(new GridLayout(2, 2));
        JLabel studentLabel = new JLabel("Student:");
        JComboBox<String> studentComboBox = new JComboBox<>();
        JLabel courseLabel = new JLabel("Course:");
        JComboBox<String> courseComboBox = new JComboBox<>();
        JLabel pointLabel = new JLabel("Point:");
        JTextField pointField = new JTextField();


        for (Student student : students) {
            studentComboBox.addItem(student.getName());
        }

        for (Course course : courses) {
            courseComboBox.addItem(course.getCourseName());
        }

        enrollPanel.add(studentLabel);
        enrollPanel.add(studentComboBox);
        enrollPanel.add(courseLabel);
        enrollPanel.add(courseComboBox);
        enrollPanel.add(pointLabel);
        enrollPanel.add(pointField);

        JButton enrollButton = new JButton("Enroll");

        enrollButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                String studentName = (String) studentComboBox.getSelectedItem();
                String courseName = (String) courseComboBox.getSelectedItem();
                String getPoint = pointField.getText();
                float getFloatPoint = Float.parseFloat(getPoint);
                Student student = null;
                Course course = null;

                for (Student s : students) {
                    if (s.getName().equals(studentName)) {
                        student = s;
                        break;
                    }
                }

                for (Course c : courses) {
                    if (c.getCourseName().equals(courseName)) {
                        course = c;
                        break;
                    }
                }

                if (student != null && course != null && getPoint != null) {
                    if (course.getEnrolledStudents().size() < course.getCapacity()) {
                        enrollment.enroll(student, course, getFloatPoint);
                        JOptionPane.showMessageDialog(null, "Student enrolled successfully!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Course is full!");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please select both student and course!");
                }
            }
        });

        add(enrollPanel, BorderLayout.CENTER);
        add(enrollButton, BorderLayout.SOUTH);

        setVisible(true);
    }
}
