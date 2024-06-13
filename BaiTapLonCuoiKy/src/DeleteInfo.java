import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.sql.*;
public class DeleteInfo extends JFrame {
    private ArrayList<Student> students;
    private ArrayList<Faculty> faculties;
    private ArrayList<Course> courses;

    final static String jdbcURL = "jdbc:mysql://localhost:3306/CoSoDaoTao";
    final static String jdbcDriver = "com.mysql.cj.jdbc.Driver";

    public DeleteInfo(ArrayList<Student> students, ArrayList<Faculty> faculties,ArrayList<Course> courses, int isStudent) {
        this.students = students;
        this.faculties = faculties;
        this.courses = courses;
        setTitle("Edit Person Info");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        if (isStudent == 0) {
            setupStudentUI();
        } else if(isStudent == 1) {
            setupFacultyUI();
        } else if(isStudent == 2) {
            setupCourseUI();
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
                for (Faculty f : faculties) {
                    if (f.getId().equals(id)) {
                        found = true;
                    }
                }

                if (found) {
                    JButton deleteButton = new JButton("delete Faculty");
                    deleteButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                      
                            String user = "root"; 
                            String password = "dinhthai2004"; 
                            Connection connection = null;
                            Statement statement = null;
                            for (Faculty f : faculties) {
                                if (f.getId().equals(id)) {
                                    faculties.remove(f);
                                    try {
                                        Class.forName(jdbcDriver);
                                        connection = DriverManager.getConnection(jdbcURL, user, password);
                                        System.out.println("Connected to Faculty table successfully!");
                                        String deleteFacultyQuery = "DELETE FROM faculty WHERE facultyid = ?";
                                        PreparedStatement preparedStatement = connection.prepareStatement(deleteFacultyQuery);
                                        preparedStatement.setString(1, id);
                                        preparedStatement.executeUpdate();
                                        System.out.println("Deleted faculty from database successfully");
                                    } catch (Exception err) {
                                        System.out.println(err);
                                    } finally {
                                        try {
                                            if (statement != null) statement.close();
                                            if (connection != null) connection.close();
                                        } catch (Exception err) {
                                            System.out.println(err);
                                        }
                                    }
                                    break;
                                }
                            }
                            JOptionPane.showMessageDialog(null, "Faculty updated successfully!");
                        }
                    });

                    add(inputPanel, BorderLayout.CENTER);
                    add(deleteButton, BorderLayout.SOUTH);
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
        JButton filterButton = new JButton("Delete");
        inputPanel.add(keywordidLabel);
        inputPanel.add(keywordidField);
        inputPanel.add(filterButton);

        filterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = keywordidField.getText();
                boolean found = false;
                for (Student s : students) {
                    if (s.getId().equals(id)) {
                        found = true;
                    }
                }

                if (found) {
                  

                    JButton deleteButton = new JButton("delete Student");
                    deleteButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            
                            String user = "root"; 
                            String password = "dinhthai2004"; 
                            Connection connection = null;
                            Statement statement = null;
                            for (Student s : students) {
                                if (s.getId().equals(id)) {
                                    students.remove(s);
                                    try {
                                        Class.forName(jdbcDriver);
                                        connection = DriverManager.getConnection(jdbcURL, user, password);
                                        System.out.println("Connected to student table successfully!");
                                        String deleteFacultyQuery = "DELETE FROM student WHERE studentid = ?";
                                        PreparedStatement preparedStatement = connection.prepareStatement(deleteFacultyQuery);
                                        preparedStatement.setString(1, id);
                                        preparedStatement.executeUpdate();
                                        System.out.println("Deleted student from database successfully");
                                    } catch (Exception err) {
                                        System.out.println(err);
                                    } finally {
                                        try {
                                            if (statement != null) statement.close();
                                            if (connection != null) connection.close();
                                        } catch (Exception err) {
                                            System.out.println(err);
                                        }
                                    }
                                    JOptionPane.showMessageDialog(null, "Student deleted successfully!");
                                    break;
                                }
                            }
                            JOptionPane.showMessageDialog(null, "Student updated successfully!");
                        }
                    });

                    add(inputPanel, BorderLayout.CENTER);
                    add(deleteButton, BorderLayout.SOUTH);
                    revalidate();
                    repaint();
                } else {
                    JOptionPane.showMessageDialog(null, "Student not found!");
                }
            }
        });
        add(inputPanel, BorderLayout.NORTH);
    }
    private void setupCourseUI() {
        JPanel inputPanel = new JPanel(new GridLayout(7, 2, 5, 5));

        JLabel keywordidLabel = new JLabel("Enter Coursecode:");
        JTextField keywordidField = new JTextField(20);
        JButton filterButton = new JButton("Delete");
        inputPanel.add(keywordidLabel);
        inputPanel.add(keywordidField);
        inputPanel.add(filterButton);

        filterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String code = keywordidField.getText();
                boolean found = false;
                for (Course c : courses) {
                    if (c.getCourseCode().equals(code)) {
                        found = true;
                    }
                }

                if (found) {
                  

                    JButton deleteButton = new JButton("delete course");
                    deleteButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            
                            String user = "root"; 
                            String password = "dinhthai2004"; 
                            Connection connection = null;
                            Statement statement = null;
                            for (Course c : courses) {
                                if (c.getCourseCode().equals(code)) {
                                    students.remove(c);
                                    try {
                                        Class.forName(jdbcDriver);
                                        connection = DriverManager.getConnection(jdbcURL, user, password);
                                        System.out.println("Connected to course table successfully!");
                                        String deleteFacultyQuery = "DELETE FROM course WHERE coursecode = ?";
                                        PreparedStatement preparedStatement = connection.prepareStatement(deleteFacultyQuery);
                                        preparedStatement.setString(1, code);
                                        preparedStatement.executeUpdate();
                                        System.out.println("Deleted course from database successfully");
                                    } catch (Exception err) {
                                        System.out.println(err);
                                    } finally {
                                        try {
                                            if (statement != null) statement.close();
                                            if (connection != null) connection.close();
                                        } catch (Exception err) {
                                            System.out.println(err);
                                        }
                                    }
                                    JOptionPane.showMessageDialog(null, "Course deleted successfully!");
                                    break;
                                }
                            }
                            JOptionPane.showMessageDialog(null, "Course updated successfully!");
                        }
                    });

                    add(inputPanel, BorderLayout.CENTER);
                    add(deleteButton, BorderLayout.SOUTH);
                    revalidate();
                    repaint();
                } else {
                    JOptionPane.showMessageDialog(null, "Course not found!");
                }
            }
        });
        add(inputPanel, BorderLayout.NORTH);
    }
}
