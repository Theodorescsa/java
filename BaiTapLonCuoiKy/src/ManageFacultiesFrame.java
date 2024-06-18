// ManageFacultiesFrame.java
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.sql.*;

public class ManageFacultiesFrame extends JFrame {
    private ArrayList<Faculty> faculties;
    final static String jdbcURL = "jdbc:mysql://localhost:3306/CoSoDaoTao";
    final static String jdbcDriver = "com.mysql.cj.jdbc.Driver";

    public ManageFacultiesFrame(ArrayList<Faculty> faculties) {
        this.faculties = faculties;

        setTitle("Manage Faculties");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(7, 2, 5, 5));

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField(20);

        JLabel idLabel = new JLabel("ID:");
        JTextField idField = new JTextField(20);

        JLabel departmentLabel = new JLabel("Department:");
        JTextField departmentField = new JTextField(20);

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
        inputPanel.add(departmentLabel);
        inputPanel.add(departmentField);
        inputPanel.add(genderLabel);
        inputPanel.add(genderPanel);

        JButton addButton = new JButton("Add Faculty");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String id = idField.getText();
                boolean gender = maleRadioButton.isSelected();
                String department = departmentField.getText();
                String user = "root"; 
                String password = "dinhthai2004"; 
                Connection connection = null;
                PreparedStatement preparedStatement = null;
                try {
                    Class.forName(jdbcDriver);
                    connection = DriverManager.getConnection(jdbcURL, user, password);
                    System.out.println("Connected to the Faculty database!");

                    if (!name.isEmpty() && !id.isEmpty() && !department.isEmpty() && (maleRadioButton.isSelected() || femaleRadioButton.isSelected())) {
                        String checkFacultyIdQuery = "SELECT facultyid FROM faculty WHERE facultyid = ?";
                        preparedStatement = connection.prepareStatement(checkFacultyIdQuery);
                        preparedStatement.setString(1, id);
                        ResultSet resultSet = preparedStatement.executeQuery();

                        if (resultSet.next()) {
                            // Faculty ID already exists
                            JOptionPane.showMessageDialog(null, "Faculty ID already exists. Please enter a different ID.");
                        } else {
                            // Faculty ID does not exist, proceed to insert
                            String genderValue = maleRadioButton.isSelected() ? "nam" : "nu";
                            String insertFacultyQuery = "INSERT INTO faculty (name, facultyid, gender, department) VALUES (?, ?, ?, ?)";
                            preparedStatement = connection.prepareStatement(insertFacultyQuery);
                            preparedStatement.setString(1, name);
                            preparedStatement.setString(2, id);
                            preparedStatement.setString(3, genderValue);
                            preparedStatement.setString(4, department);
                            preparedStatement.executeUpdate();

                            // Add to local list
                            faculties.add(new Faculty(name, id, gender, department));

                            JOptionPane.showMessageDialog(null, "Faculty added successfully!");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Please fill in all fields!");
                    }
                } catch (Exception err) {
                    System.out.println(err);
                } finally {
                    try {
                        if (preparedStatement != null) {
                            preparedStatement.close();
                        }
                        if (connection != null) {
                            connection.close();
                        }
                    } catch (SQLException sqlException) {
                        sqlException.printStackTrace();
                    }
                }
            }
        });

        add(inputPanel, BorderLayout.CENTER);
        add(addButton, BorderLayout.SOUTH);

        setVisible(true);
    }
}
