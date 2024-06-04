// ManageStudentsFrame.java
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.sql.*;
public class ManageStudentsFrame extends JFrame {
    private ArrayList<Student> students;
    final static String jdbcURL = "jdbc:mysql://localhost:3306/CoSoDaoTao";
    final static String jdbcDriver = "com.mysql.cj.jdbc.Driver";

    public ManageStudentsFrame(ArrayList<Student> students) {
        this.students = students;

        setTitle("Manage Students");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        
        JLabel nameLabel = new JLabel("Name:");
        inputPanel.add(nameLabel, gbc);
        
        gbc.gridy++;
        JTextField nameField = new JTextField(50);
        inputPanel.add(nameField, gbc);
        
        gbc.gridy++;
        JLabel idLabel = new JLabel("ID:");
        inputPanel.add(idLabel, gbc);
        
        gbc.gridy++;
        JTextField idField = new JTextField(50);
        inputPanel.add(idField, gbc);
        
        gbc.gridy++;
        JLabel majorLabel = new JLabel("Major:");
        inputPanel.add(majorLabel, gbc);
        
        gbc.gridy++;
        JTextField majorField = new JTextField(50);
        inputPanel.add(majorField, gbc);
        
        gbc.gridy++;
        JLabel genderLabel = new JLabel("Gender:");
        inputPanel.add(genderLabel, gbc);
        
        gbc.gridy++;
        JRadioButton maleRadioButton = new JRadioButton("Male");
        inputPanel.add(maleRadioButton, gbc);
        
        gbc.gridy++;
        JRadioButton femaleRadioButton = new JRadioButton("Female");
        inputPanel.add(femaleRadioButton, gbc);
        
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleRadioButton);
        genderGroup.add(femaleRadioButton);
        

        JButton addButton = new JButton("Add Student");

        addButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {

                String name = nameField.getText();
                String id = idField.getText();
                String major = majorField.getText();
                boolean gender = maleRadioButton.isSelected();
                String user = "root"; 
                String password = "dinhthai2004"; 
                Connection connection = null;
                Statement statement = null;
                try {
                    Class.forName(jdbcDriver);
                    connection = DriverManager.getConnection(jdbcURL, user, password);
                    System.out.println("Ket noi thanh cong toi bang Students!");
                    statement = connection.createStatement();
                    // try {
                    //     String createTableStudents = "create table if not exists cosodaotao.students("
                    //     +   "StudentId int auto_increment primarykey,"
                    //     +   "gender"

                    //     ")";
                    //     statement.executeUpdate(createTableStudents);
                    // } catch (Exception r) {
                    //     System.err.println(r);
                    // }

                    if (!name.isEmpty() && !id.isEmpty() && !major.isEmpty() && (maleRadioButton.isSelected() || femaleRadioButton.isSelected())) {
                        // String insertDataToStudents = "insert into cosodaotao.students ()"
                        if (maleRadioButton.isSelected() || !femaleRadioButton.isSelected())students.add(new Student(name, id,true, major));
                        if (!maleRadioButton.isSelected() || femaleRadioButton.isSelected())students.add(new Student(name, id, false, major));
                        JOptionPane.showMessageDialog(null, "Student added successfully!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Please fill in all fields!");
                    }
                } catch(Exception r) {
                    System.err.println(r);
                }
            }
        });

        add(inputPanel, BorderLayout.CENTER);
        add(addButton, BorderLayout.SOUTH);

        setVisible(true);
    }
}
