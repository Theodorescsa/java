import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DeleteInfomationFrame extends JFrame {
    private ArrayList<Student> students;
    private ArrayList<Faculty> faculties;
    private ArrayList<Course> courses;

    public DeleteInfomationFrame(ArrayList<Student> students, ArrayList<Faculty> faculties,ArrayList<Course> courses) {
        this.students = students;
        this.faculties = faculties;
        this.courses = courses;
        setTitle("Filter Person");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel mainPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(240, 240, 240));  

        JButton DeleteStudentButton = new JButton("Delete Student");
        JButton DeleteFacultyButton = new JButton("Delete Faculty");
        JButton DeleteCourseButton = new JButton("Delete Course");


        customizeButton(DeleteStudentButton);
        customizeButton(DeleteFacultyButton);
        customizeButton(DeleteCourseButton);


        mainPanel.add(DeleteFacultyButton);
        mainPanel.add(DeleteStudentButton);
        mainPanel.add(DeleteCourseButton);


        add(mainPanel, BorderLayout.CENTER);
        
        DeleteFacultyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DeleteInfo(students,faculties,courses,1);
            }
        });
        
        DeleteStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DeleteInfo(students,faculties,courses,0);
            }
        });
        DeleteCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DeleteInfo(students,faculties,courses,2);
            }
        });
        setVisible(true);
    }
    
    private void customizeButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 16)); // Có thể sửa font này nếu cần
        button.setBackground(new Color(130,130,130)); 
        button.setForeground(Color.GRAY);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }
}
