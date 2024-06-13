import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.sql.*;
public class EditPersonInfo extends JFrame {
    private ArrayList<Student> students;
    private ArrayList<Faculty> faculties;
    final static String jdbcURL = "jdbc:mysql://localhost:3306/CoSoDaoTao";
    final static String jdbcDriver = "com.mysql.cj.jdbc.Driver";

    public EditPersonInfo(ArrayList<Student> students, ArrayList<Faculty> faculties, boolean isStudent) {
        this.students = students;
        this.faculties = faculties;
        setTitle("Edit Person Info");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        if (isStudent) {
            setupStudentUI();
        } else {
            setupFacultyUI();
        }
        setVisible(true);
    }

    private void setupFacultyUI() {
        JPanel inputPanel = new JPanel(new GridLayout(7, 2, 5, 5));

        JLabel keywordidLabel = new JLabel("Enter ID:");
        JTextField keywordidField = new JTextField(20);
        JButton filterButton = new JButton("Search");
        inputPanel.add(keywordidLabel);
        inputPanel.add(keywordidField);
        inputPanel.add(filterButton);
     
        filterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = keywordidField.getText();
                boolean found = false;
                
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
               
                if (!faculties.isEmpty()) {
                    for (Faculty f : faculties) {
                        if (f.getId().equals(id)) {
                            nameField.setText(f.getName());
                            idField.setText(f.getId());
                            departmentField.setText(f.getDepartment());
                            if (f.getGender()) {
                                maleRadioButton.setSelected(true);
                            } else {
                                femaleRadioButton.setSelected(true);
                            }
                            found = true;
                            break;
                        }
                    }
                }

                if (found) {
                    inputPanel.add(nameLabel);
                    inputPanel.add(nameField);
                    inputPanel.add(idLabel);
                    inputPanel.add(idField);
                    inputPanel.add(departmentLabel);
                    inputPanel.add(departmentField);
                    inputPanel.add(genderLabel);
                    inputPanel.add(genderPanel);

                    JButton updateButton = new JButton("Update Faculty");
                    updateButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String updatedName = nameField.getText();
                            String updatedId = idField.getText();
                            String updatedDepartment = departmentField.getText();
                            boolean updatedGender = maleRadioButton.isSelected();
                            String user = "root"; 
                            String password = "dinhthai2004"; 
                            Connection connection = null;
                            Statement statement = null;
                            for (Faculty f : faculties) {
                                if (f.getId().equals(id)) {
                                    f.setName(updatedName);
                                    f.setId(updatedId);
                                    f.setDepartment(updatedDepartment);
                                    f.setGender(updatedGender);
                                    try {
                                        Class.forName(jdbcDriver);
                                        connection = DriverManager.getConnection(jdbcURL, user, password);
                                        System.out.println("Connected to Faculty table successfully!");
                                        statement = connection.createStatement();
                                        if (!updatedName.isEmpty() && !id.isEmpty() && !updatedDepartment.isEmpty() && (maleRadioButton.isSelected() || femaleRadioButton.isSelected())) {
                                            String gender = maleRadioButton.isSelected() ? "nam" : "nu";
                                            try {
                                                String updateFacultyQuery = "UPDATE faculty SET name = ?, gender = ?, department = ? WHERE facultyid = ?";
                                                PreparedStatement preparedStatement = connection.prepareStatement(updateFacultyQuery);
                                                preparedStatement.setString(1, updatedName);
                                                preparedStatement.setString(2, gender);
                                                preparedStatement.setString(3, updatedDepartment);
                                                preparedStatement.setString(4, updatedId);
                                                preparedStatement.executeUpdate();
                                                System.out.println("Updated faculty in database successfully");
                                            } catch (Exception err) {
                                                System.out.println(err);
                                            }
                                            JOptionPane.showMessageDialog(null, "Faculty updated successfully!");
                                        } else {
                                            JOptionPane.showMessageDialog(null, "Please fill in all fields!");
                                        }
                                    } catch (Exception err) {
                                        System.out.println(err);
                                    }
                                    break;
                                }
                            }
                            JOptionPane.showMessageDialog(null, "Faculty updated successfully!");
                        }
                    });

                    add(inputPanel, BorderLayout.CENTER);
                    add(updateButton, BorderLayout.SOUTH);
                    revalidate();
                    repaint();
                } else {
                    JOptionPane.showMessageDialog(null, "Faculty not found!");
                }
            }
        });
        add(inputPanel, BorderLayout.NORTH);
    }

    private void setupStudentUI() {
        JPanel inputPanel = new JPanel(new GridLayout(7, 2, 5, 5));

        JLabel keywordidLabel = new JLabel("Enter ID:");
        JTextField keywordidField = new JTextField(20);
        JButton filterButton = new JButton("Search");
        inputPanel.add(keywordidLabel);
        inputPanel.add(keywordidField);
        inputPanel.add(filterButton);

        filterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = keywordidField.getText();
                boolean found = false;

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

                if (!students.isEmpty()) {
                    for (Student s : students) {
                        if (s.getId().equals(id)) {
                            nameField.setText(s.getName());
                            idField.setText(s.getId());
                            majorField.setText(s.getMajor());
                            if (s.getGender()) {
                                maleRadioButton.setSelected(true);
                            } else {
                                femaleRadioButton.setSelected(true);
                            }
                            
                            found = true;
                            break;
                        }
                    }
                }

                if (found) {
                    inputPanel.add(nameLabel);
                    inputPanel.add(nameField);
                    inputPanel.add(idLabel);
                    inputPanel.add(idField);
                    inputPanel.add(majorLabel);
                    inputPanel.add(majorField);
                    inputPanel.add(genderLabel);
                    inputPanel.add(genderPanel);

                    JButton updateButton = new JButton("Update Student");
                    updateButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String updatedName = nameField.getText();
                            String updatedId = idField.getText();
                            String updatedMajor = majorField.getText();
                            boolean updatedGender = maleRadioButton.isSelected();
                            String user = "root"; 
                            String password = "dinhthai2004"; 
                            Connection connection = null;
                            Statement statement = null;
                            for (Student s : students) {
                                if (s.getId().equals(id)) {
                                    s.setName(updatedName);
                                    s.setId(updatedId);
                                    s.setMajor(updatedMajor);
                                    s.setGender(updatedGender);
                                    try {
                                        Class.forName(jdbcDriver);
                                        connection = DriverManager.getConnection(jdbcURL, user, password);
                                        System.out.println("Ket noi thanh cong toi bang Students!");
                                        statement = connection.createStatement();
                                        if (!updatedName.isEmpty() && !updatedId.isEmpty() && !updatedMajor.isEmpty() && (maleRadioButton.isSelected() || femaleRadioButton.isSelected())) {
                                            String gender = maleRadioButton.isSelected() ? "nam" : "nu";
                                            try {
                                                String updateStudentQuery = "UPDATE student SET name = ?, gender = ?, major = ? WHERE studentid = ?";
                                                PreparedStatement preparedStatement = connection.prepareStatement(updateStudentQuery);
                                                preparedStatement.setString(1, updatedName);
                                                preparedStatement.setString(2, gender);
                                                preparedStatement.setString(3, updatedMajor);
                                                preparedStatement.setString(4, updatedId);
                                                preparedStatement.executeUpdate();
                                                System.out.println("Cap nhat sinh vien trong co so du lieu thanh cong");
                                            } catch (Exception err) {
                                                System.out.println(err);
                                            }
                                            JOptionPane.showMessageDialog(null, "Student updated successfully!");
                                        } else {
                                            JOptionPane.showMessageDialog(null, "Please fill in all fields!");
                                        }
                                    } catch(Exception r) {
                                        System.err.println(r);
                                    }
                                    break;
                                }
                            }
                            JOptionPane.showMessageDialog(null, "Student updated successfully!");
                        }
                    });

                    add(inputPanel, BorderLayout.CENTER);
                    add(updateButton, BorderLayout.SOUTH);
                    revalidate();
                    repaint();
                } else {
                    JOptionPane.showMessageDialog(null, "Student not found!");
                }
            }
        });
        add(inputPanel, BorderLayout.NORTH);
    }
}
