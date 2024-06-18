import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.sql.*;
import java.util.*;
public class CourseMenuFrame extends JFrame {
    private Enrollment enrollment;
    private ArrayList<Student> students;
    private ArrayList<Faculty> faculties;
    private ArrayList<Course> courses;
  

   

  
    public CourseMenuFrame(ArrayList<Course> courses, ArrayList<Faculty> faculties, ArrayList<Student> students, Enrollment enrollment) {
        this.enrollment = enrollment;
        this.faculties = faculties;
        this.courses = courses;
        this.students = students;
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        setTitle("University Course Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel mainPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(240, 240, 240));  


        JButton addCourseButton = new JButton("Add Course");
        JButton manageCoursesButton = new JButton("Manage Point");
        JButton EditPersonFrame = new JButton("Edit Course");
        JButton DeleteInfomationFrame = new JButton("Delete Course");

        // Customize buttons
   
        customizeButton(addCourseButton);
        customizeButton(manageCoursesButton);

        customizeButton(EditPersonFrame);
        customizeButton(DeleteInfomationFrame);
        





        mainPanel.add(addCourseButton);
        mainPanel.add(manageCoursesButton);

        mainPanel.add(EditPersonFrame);
        mainPanel.add(DeleteInfomationFrame);





        add(mainPanel, BorderLayout.CENTER);

      

        addCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddCourseFrame(courses, faculties);
            }
        });

        manageCoursesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ManageCoursesFrame(courses, faculties, students, enrollment);
            }
        });

        EditPersonFrame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Editcourseinfo();
            }
        });
        
        DeleteInfomationFrame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DeleteInfo(students,faculties,courses,2);
            }
        });

        setVisible(true);
    }

    private void customizeButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(new Color(130,130,130)); 
        button.setForeground(Color.GRAY);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

 
}
