import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DisplaySearchResultFrame extends JFrame {
    private ArrayList<Student> students;
    private ArrayList<Faculty> faculties;
    private JTextArea textArea;
    private StringBuilder sb;

    public DisplaySearchResultFrame(ArrayList<Student> students, ArrayList<Faculty> faculties) {
        this.students = students;
        this.faculties = faculties;
        setTitle("Result");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        setupStudentUI();
        setVisible(true);
    }
    
    public DisplaySearchResultFrame(ArrayList<Faculty> faculties) {
        this.faculties = faculties;
        setTitle("Result");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        setupUI();
        setVisible(true);
    }

    private void setupUI() {
        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setEditable(false);
        sb = new StringBuilder();

        JPanel enrollPanel = new JPanel(new GridLayout(5, 1));
        JLabel idLabel = new JLabel("ID:");
        JTextField idField = new JTextField();

        enrollPanel.add(idLabel);
        enrollPanel.add(idField);

        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                boolean found = false;
                sb.setLength(0); // Clear the StringBuilder
        
                if (!faculties.isEmpty() && !found) {
                    for (Faculty f : faculties) {
                        if (f.getId().equals(id)) {
                            sb.append("Name: ").append(f.getName()).append("\n");
                            sb.append("ID: ").append(f.getId()).append("\n");
                            sb.append("Gender: ").append(f.getGender()).append("\n");
                            sb.append("Department: ").append(f.getDepartment()).append("\n");
                            found = true;
                            break;
                        }
                    }
                }
                if (!found) {
                    sb.append("No record found for ID: ").append(id);
                }
                textArea.setText(sb.toString());
            }
        });

        add(enrollPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(searchButton, BorderLayout.SOUTH);
    }
    private void setupStudentUI() {
        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setEditable(false);
        sb = new StringBuilder();

        JPanel enrollPanel = new JPanel(new GridLayout(5, 1));
        JLabel idLabel = new JLabel("ID:");
        JTextField idField = new JTextField();

        enrollPanel.add(idLabel);
        enrollPanel.add(idField);

        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                boolean found = false;
                sb.setLength(0); // Clear the StringBuilder
                if (!students.isEmpty()) {
                    for (Student s : students) {
                        if (s.getId().equals(id)) {
                            found = true;
                            sb.append("Name: ").append(s.getName()).append("\n");
                            sb.append("ID: ").append(s.getId()).append("\n");
                            sb.append("Gender: ").append(s.getGender()).append("\n");
                            sb.append("Major: ").append(s.getMajor()).append("\n");
                            break;
                        }
                    }
                }
  
                if (!found) {
                    sb.append("No record found for ID: ").append(id);
                }
                textArea.setText(sb.toString());
            }
        });

        add(enrollPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(searchButton, BorderLayout.SOUTH);
    }
}
