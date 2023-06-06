package ch01;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ManagementProgram extends JFrame {
    private JTextField nameField, ageField, mvpField, gradeField;
    private JButton addButton, showButton;
    private JTextArea displayArea;
    private ArrayList<User_info> userList;

    public ManagementProgram() {
        userList = new ArrayList<>();

        // Create GUI components
        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField(20);
        JLabel ageLabel = new JLabel("Age:");
        ageField = new JTextField(20);
        JLabel mvpLabel = new JLabel("MVP:");
        mvpField = new JTextField(20);
        JLabel gradeLabel = new JLabel("Grade:");
        gradeField = new JTextField(20);

        addButton = new JButton("Add User");
        showButton = new JButton("Show Users");

        displayArea = new JTextArea(10, 30);
        JScrollPane scrollPane = new JScrollPane(displayArea);

        // Set layout
        setLayout(new FlowLayout());

        // Add components to the frame
        add(nameLabel);
        add(nameField);
        add(ageLabel);
        add(ageField);
        add(mvpLabel);
        add(mvpField);
        add(gradeLabel);
        add(gradeField);
        add(addButton);
        add(showButton);
        add(scrollPane);

        // Add action listeners
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                int age = Integer.parseInt(ageField.getText());
                String mvp = mvpField.getText();
                int grade = Integer.parseInt(gradeField.getText());

                User_info user = new User_info(name, age, mvp, grade);
                userList.add(user);

                nameField.setText("");
                ageField.setText("");
                mvpField.setText("");
                gradeField.setText("");
            }
        });

        showButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayArea.setText("");
                for (User_info user : userList) {
                    displayArea.append(user.toString() + "\n");
                }
            }
        });

        // Set frame properties
        setTitle("User Management Program");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ManagementProgram();
            }
        });
    }