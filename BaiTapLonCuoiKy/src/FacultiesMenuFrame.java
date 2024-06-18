import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.sql.*;
import java.util.*;
public class FacultiesMenuFrame extends JFrame {
    private Enrollment enrollment;
    private ArrayList<Student> students;
    private ArrayList<Faculty> faculties;
    private ArrayList<Course> courses;
   
  
    public FacultiesMenuFrame(ArrayList<Course> courses, ArrayList<Faculty> faculties, ArrayList<Student> students, Enrollment enrollment) {
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

        JButton manageFacultiesButton = new JButton("Add Faculty");
        JButton FilterPersonFrame = new JButton("Filter Faculty");
        JButton EditPersonFrame = new JButton("Edit Faculty");
        JButton DeleteInfomationFrame = new JButton("Delete Faculty");

        // Customize buttons
        customizeButton(manageFacultiesButton);
        customizeButton(FilterPersonFrame);
        customizeButton(EditPersonFrame);
        customizeButton(DeleteInfomationFrame);
        





        mainPanel.add(manageFacultiesButton);

        mainPanel.add(FilterPersonFrame);
        mainPanel.add(EditPersonFrame);
        mainPanel.add(DeleteInfomationFrame);





        add(mainPanel, BorderLayout.CENTER);

   

        manageFacultiesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ManageFacultiesFrame(faculties);
            }
        });

 
        FilterPersonFrame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DisplaySearchResultFrame(faculties);
            }
        });
        EditPersonFrame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EditPersonInfo(students,faculties,false);
            }
        });
        
        DeleteInfomationFrame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DeleteInfo(students,faculties,courses,1);
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
