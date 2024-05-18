import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MainFrame extends JFrame {
    final private Font mainFont = new Font("Segoe print", Font.BOLD,18);
    JTextField tfFirstName, tfLastName;
    JTextField tfEmail;
    JLabel lbWelcome;
    public void initialize() {
    JLabel lbFirstName = new JLabel("First Name");
    lbFirstName.setFont(mainFont);

    tfFirstName = new JTextField();
    tfFirstName.setFont(mainFont);

    JLabel lbLastName = new JLabel("Last Name");
    lbLastName.setFont(mainFont);

    JLabel lbEmail = new JLabel("Email");
    lbEmail.setFont(mainFont);

    tfLastName = new JTextField();
    tfLastName.setFont(mainFont);
    
    tfEmail = new JTextField();
    tfEmail.setFont(mainFont);

    JPanel formPanel = new JPanel();
    formPanel.setLayout(new GridLayout(4,1,5,5));
    formPanel.add(lbFirstName);
    formPanel.add(tfFirstName);
    formPanel.add(lbLastName);
    formPanel.add(tfLastName);
    formPanel.add(lbEmail);
    formPanel.add(tfEmail);

    lbWelcome = new JLabel();
    lbWelcome.setFont(mainFont);

    JButton btnOK = new JButton("OK");
    btnOK.setFont(mainFont);;
    btnOK.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            String firstName = tfFirstName.getText();
            String lastName = tfLastName.getText();
            lbWelcome.setText("Hello "+ firstName + " " + lastName);
        }
        
    });

    JButton btnClear = new JButton("Clear");
    btnClear.setFont(mainFont);
    btnClear.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            tfFirstName.setText("");
            tfLastName.setText("");
            tfEmail.setText("");
            lbWelcome.setText("");
        }
        
    });

    JPanel buttoPanel = new JPanel();
    buttoPanel.setLayout(new GridLayout(1,2,5,5));
    buttoPanel.add(btnOK);
    buttoPanel.add(btnClear);

    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BorderLayout());
    mainPanel.setBackground(new Color(128,238,255));
    mainPanel.add(formPanel, BorderLayout.NORTH);
    mainPanel.add(lbWelcome, BorderLayout.WEST);
    mainPanel.add(buttoPanel, BorderLayout.SOUTH);

    add(mainPanel);

    setTitle("welcome");
    setSize(500,600);
    setMaximumSize(new Dimension(300,400));
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setVisible(true);
}
    public static void main(String[] args) {
        MainFrame myFrame = new MainFrame();
        myFrame.initialize();
    }   
}