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

        JPanel inputPanel = new JPanel(new GridLayout(6, 2, 5, 5));

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField(20);

        JLabel idLabel = new JLabel("ID:");
        JTextField idField = new JTextField(20);

        JLabel majorLabel = new JLabel("Major:");
        JTextField majorField = new JTextField(20);

        JLabel genderLabel = new JLabel("Gender:");
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JRadioButton maleRadioButton = new JRadioButton("Male");
        JRadioButton femaleRadioButton = new JRadioButton("Female");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleRadioButton);
        genderGroup.add(femaleRadioButton);
        genderPanel.add(maleRadioButton);
        genderPanel.add(femaleRadioButton);

        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(idLabel);
        inputPanel.add(idField);
        inputPanel.add(majorLabel);
        inputPanel.add(majorField);
        inputPanel.add(genderLabel);
        inputPanel.add(genderPanel);

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
                    if (!name.isEmpty() && !id.isEmpty() && !major.isEmpty() && (maleRadioButton.isSelected() || femaleRadioButton.isSelected())) {
                        if (maleRadioButton.isSelected() && !femaleRadioButton.isSelected()){
                            students.add(new Student(name, id, true, major));
                            try {
                                Class.forName(jdbcDriver);
                                connection = DriverManager.getConnection(jdbcURL, user, password);
                                System.out.println("Ket noi thanh cong!");
                                statement = connection.createStatement();
                                String checkTypeNameQuery = "select studentid from student where studentid = ?";
                                PreparedStatement preparedStatement = connection.prepareStatement(checkTypeNameQuery);
                                preparedStatement.setString(1, id);
                                ResultSet resultSet = preparedStatement.executeQuery();
                                String insertToStudentTableQuery = "insert into student (name, studentid, gender, major) values (?, ?, ?, ?)";
                                preparedStatement = connection.prepareStatement(insertToStudentTableQuery);
                                preparedStatement.setString(1, name);
                                preparedStatement.setString(2, id);
                                preparedStatement.setString(3, "nam");
                                preparedStatement.setString(4, major);
                                preparedStatement.executeUpdate();
                                System.out.println("Them sinh vien vao co so du lieu thanh cong");
                            } catch (Exception err) {
                                System.out.println(err);
                            }
                        };
                        if (!maleRadioButton.isSelected() && femaleRadioButton.isSelected()){
                            students.add(new Student(name, id, false, major));
                            try {
                                Class.forName(jdbcDriver);
                                connection = DriverManager.getConnection(jdbcURL, user, password);
                                System.out.println("Ket noi thanh cong!");
                                statement = connection.createStatement();
                                String checkTypeNameQuery = "select studentid from student where studentid = ?";
                                PreparedStatement preparedStatement = connection.prepareStatement(checkTypeNameQuery);
                                preparedStatement.setString(1, id);
                                ResultSet resultSet = preparedStatement.executeQuery();
                                String insertToStudentTableQuery = "insert into student (name, studentid, gender, major) values (?, ?, ?, ?)";
                                preparedStatement = connection.prepareStatement(insertToStudentTableQuery);
                                preparedStatement.setString(1, name);
                                preparedStatement.setString(2, id);
                                preparedStatement.setString(3, "nu");
                                preparedStatement.setString(4, major);
                                preparedStatement.executeUpdate();
                                System.out.println("Them sinh vien vao co so du lieu thanh cong");
                            } catch (Exception err) {
                                System.out.println(err);
                            }
                        };
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
