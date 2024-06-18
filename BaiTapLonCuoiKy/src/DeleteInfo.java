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

    public DeleteInfo(ArrayList<Student> students, ArrayList<Faculty> faculties, ArrayList<Course> courses, int isStudent) {
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
                Connection connection = null;
                PreparedStatement checkFacultyStatement = null;
                ResultSet facultyResult = null;

                try {
                    connection = DriverManager.getConnection(jdbcURL, "root", "dinhthai2004");
                    String checkFacultyQuery = "SELECT * FROM faculty WHERE facultyId = ?";
                    checkFacultyStatement = connection.prepareStatement(checkFacultyQuery);
                    checkFacultyStatement.setString(1, id);
                    facultyResult = checkFacultyStatement.executeQuery();

                    if (facultyResult.next()) {
                        found = true;
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } finally {
                    try {
                        if (facultyResult != null) facultyResult.close();
                        if (checkFacultyStatement != null) checkFacultyStatement.close();
                        if (connection != null) connection.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }

                if (found) {
                    JButton deleteButton = new JButton("Delete Faculty");
                    deleteButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            Connection connection = null;
                            PreparedStatement deleteFacultyStatement = null;

                            try {
                                connection = DriverManager.getConnection(jdbcURL, "root", "dinhthai2004");
                                String deleteFacultyQuery = "DELETE FROM faculty WHERE facultyid = ?";
                                deleteFacultyStatement = connection.prepareStatement(deleteFacultyQuery);
                                deleteFacultyStatement.setString(1, id);
                                int rowsAffected = deleteFacultyStatement.executeUpdate();

                                // Remove the faculty from the ArrayList if deleted from database
                                if (rowsAffected > 0) {
                                    for (Faculty f : faculties) {
                                        if (f.getId().equals(id)) {
                                            faculties.remove(f);
                                            break;
                                        }
                                    }
                                }
                                
                                JOptionPane.showMessageDialog(null, "Faculty updated successfully!");
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                                JOptionPane.showMessageDialog(null, "Error updating faculty in the database.");
                            } finally {
                                try {
                                    if (deleteFacultyStatement != null) deleteFacultyStatement.close();
                                    if (connection != null) connection.close();
                                } catch (SQLException ex) {
                                    ex.printStackTrace();
                                }
                            }
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
                Connection connection = null;
                PreparedStatement checkStudentStatement = null;
                ResultSet studentResult = null;
    
                try {
                    connection = DriverManager.getConnection(jdbcURL, "root", "dinhthai2004");
                    String checkStudentQuery = "SELECT * FROM student WHERE studentId = ?";
                    checkStudentStatement = connection.prepareStatement(checkStudentQuery);
                    checkStudentStatement.setString(1, id);
                    studentResult = checkStudentStatement.executeQuery();
    
                    if (studentResult.next()) {
                        found = true;
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } finally {
                    try {
                        if (studentResult != null) studentResult.close();
                        if (checkStudentStatement != null) checkStudentStatement.close();
                        if (connection != null) connection.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
    
                if (found) {
                    JButton deleteButton = new JButton("Delete Student");
                    deleteButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            Connection connection = null;
                            PreparedStatement deleteStudentStatement = null;
    
                            try {
                                connection = DriverManager.getConnection(jdbcURL, "root", "dinhthai2004");
                                String deleteStudentQuery = "DELETE FROM student WHERE studentid = ?";
                                deleteStudentStatement = connection.prepareStatement(deleteStudentQuery);
                                deleteStudentStatement.setString(1, id);
                                int rowsAffected = deleteStudentStatement.executeUpdate();
    
                                // Remove the student from the ArrayList if deleted from database
                                if (rowsAffected > 0) {
                                    for (Student s : students) {
                                        if (s.getId().equals(id)) {
                                            students.remove(s);
                                            break;
                                        }
                                    }
                                }
    
                                JOptionPane.showMessageDialog(null, "Student deleted successfully!");
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                                JOptionPane.showMessageDialog(null, "Error deleting student from the database.");
                            } finally {
                                try {
                                    if (deleteStudentStatement != null) deleteStudentStatement.close();
                                    if (connection != null) connection.close();
                                } catch (SQLException ex) {
                                    ex.printStackTrace();
                                }
                            }
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
    
        JLabel keywordidLabel = new JLabel("Enter Course Code:");
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
                Connection connection = null;
                PreparedStatement checkCourseStatement = null;
                ResultSet courseResult = null;
    
                try {
                    connection = DriverManager.getConnection(jdbcURL, "root", "dinhthai2004");
                    String checkCourseQuery = "SELECT * FROM course WHERE courseCode = ?";
                    checkCourseStatement = connection.prepareStatement(checkCourseQuery);
                    checkCourseStatement.setString(1, code);
                    courseResult = checkCourseStatement.executeQuery();
    
                    // if (courseResult.next()) {
                        found = true;
                    // }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } finally {
                    try {
                        if (courseResult != null) courseResult.close();
                        if (checkCourseStatement != null) checkCourseStatement.close();
                        if (connection != null) connection.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
    
                if (found) {
                    JButton deleteButton = new JButton("Delete Course");
                    deleteButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            Connection connection = null;
                            PreparedStatement deleteCourseStatement = null;
    
                            try {
                                connection = DriverManager.getConnection(jdbcURL, "root", "dinhthai2004");
                                String deleteCourseQuery = "DELETE FROM course WHERE courseCode = ?";
                                deleteCourseStatement = connection.prepareStatement(deleteCourseQuery);
                                deleteCourseStatement.setString(1, code);
                                int rowsAffected = deleteCourseStatement.executeUpdate();
    
                                // Remove the course from the ArrayList if deleted from database
                                if (rowsAffected > 0) {
                                    for (Course c : courses) {
                                        if (c.getCourseCode().equals(code)) {
                                            courses.remove(c);
                                            break;
                                        }
                                    }
                                }
    
                                JOptionPane.showMessageDialog(null, "Course deleted successfully!");
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                                JOptionPane.showMessageDialog(null, "Error deleting course from the database.");
                            } finally {
                                try {
                                    if (deleteCourseStatement != null) deleteCourseStatement.close();
                                    if (connection != null) connection.close();
                                } catch (SQLException ex) {
                                    ex.printStackTrace();
                                }
                            }
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
