import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EditPersonFrame extends JFrame {
    private ArrayList<Student> students;
    private ArrayList<Faculty> faculties;

    public EditPersonFrame(ArrayList<Student> students, ArrayList<Faculty> faculties) {
        this.students = students;
        this.faculties = faculties;

        setTitle("Filter Person");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel mainPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(240, 240, 240));  

        JButton SearchStudentButton = new JButton("Update Student");
        JButton SearchFacultyButton = new JButton("Update Faculty");

        customizeButton(SearchStudentButton);
        customizeButton(SearchFacultyButton);

        mainPanel.add(SearchFacultyButton);
        mainPanel.add(SearchStudentButton);

        add(mainPanel, BorderLayout.CENTER);
        
        SearchFacultyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EditPersonInfo(students,faculties,false);
            }
        });
        
        SearchStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EditPersonInfo(students,faculties,true);
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
