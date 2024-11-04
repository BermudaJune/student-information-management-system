package JFrame;

import utils.DbUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddStudentGUI extends JFrame {

    private JTextField studentIdField;
    private JTextField nameField;
    private JComboBox<String> genderComboBox;
    private JTextField ageField;
    private JTextField classField;
    private JTextField addressField;
    private JComboBox<String> politicalStatusComboBox;
    private JTextField admissionTimeField;
    private JButton addButton;

    public AddStudentGUI() {
        setTitle("添加学生信息");
        setDefaultCloseOperation(1);
        Font font = new Font("宋体", Font.BOLD, 24);
        // 使用 BorderLayout 布局
        setLayout(new BorderLayout(10, 10));

        JPanel contentPanel = new JPanel(new GridLayout(9, 2, 10, 10));

        JLabel studentIdLabel = new JLabel("学号:");
        studentIdField = new JTextField(20);
        studentIdLabel.setFont(font);
        JLabel nameLabel = new JLabel("姓名:");
        nameField = new JTextField(20);
        nameLabel.setFont(font);
        JLabel genderLabel = new JLabel("性别:");
        String[] genders = {"男", "女"};
        genderComboBox = new JComboBox<>(genders);
        genderLabel.setFont(font);
        JLabel ageLabel = new JLabel("年龄:");
        ageField = new JTextField(20);
        ageLabel.setFont(font);
        JLabel classLabel = new JLabel("班级:");
        classField = new JTextField(20);
        classLabel.setFont(font);
        JLabel addressLabel = new JLabel("地址:");
        addressField = new JTextField(20);
        addressLabel.setFont(font);
        JLabel politicalStatusLabel = new JLabel("政治面貌:");
        String[] politicalStatuses = {"党员", "团员", "群众","入党积极分子"};
        politicalStatusComboBox = new JComboBox<>(politicalStatuses);
        politicalStatusLabel.setFont(font);
        JLabel admissionTimeLabel = new JLabel("入学时间:");
        admissionTimeField = new JTextField(20);
        admissionTimeLabel.setFont(font);
        addButton = new JButton("添加");
        addButton.setBackground(Color.BLUE);
        addButton.setForeground(Color.WHITE);
        addButton.setFont(new Font("宋体", Font.BOLD, 20));
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addStudentInfo();
            }
        });
        contentPanel.add(studentIdLabel);
        contentPanel.add(studentIdField);
        contentPanel.add(nameLabel);
        contentPanel.add(nameField);
        contentPanel.add(genderLabel);
        contentPanel.add(genderComboBox);
        contentPanel.add(ageLabel);
        contentPanel.add(ageField);
        contentPanel.add(classLabel);
        contentPanel.add(classField);
        contentPanel.add(addressLabel);
        contentPanel.add(addressField);
        contentPanel.add(politicalStatusLabel);
        contentPanel.add(politicalStatusComboBox);
        contentPanel.add(admissionTimeLabel);
        contentPanel.add(admissionTimeField);

        add(contentPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // 设置背景颜色
        contentPanel.setBackground(Color.LIGHT_GRAY);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    private void addStudentInfo() {
        String id = studentIdField.getText();
        String name = nameField.getText();
        String sex = (String) genderComboBox.getSelectedItem();
        String age = ageField.getText();
        String classValue = classField.getText();
        String address = addressField.getText();
        String facial =(String) politicalStatusComboBox.getSelectedItem();
        String time = admissionTimeField.getText();

        try {
            Connection conn = DbUtil.getConnection();
            String query = "INSERT INTO studentinfo (id, name, sex, age, class, address, facial, time) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, id);
            stmt.setString(2, name);
            stmt.setString(3, sex);
            stmt.setString(4, age);
            stmt.setString(5, classValue);
            stmt.setString(6, address);
            stmt.setString(7, facial);
            stmt.setString(8, time);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "学生信息添加成功");
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this, "学生信息添加失败");
            }

            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }
    private void clearFields() {
        studentIdField.setText("");
        nameField.setText("");
        ageField.setText("");
        classField.setText("");
        addressField.setText("");
        admissionTimeField.setText("");
    }
    public static void main(String[] args) {
        new AddStudentGUI();
    }
}