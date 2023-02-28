package login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUp extends JFrame implements ActionListener {
    JPanel jPanel = new JPanel();
    JPlaceholderTextField placeholderID = new JPlaceholderTextField("ID");
    JPlaceholderPassword placeholderPW = new JPlaceholderPassword("PW");
    JPlaceholderTextField placeholderName = new JPlaceholderTextField("Name");
    JPlaceholderTextField placeholderAge = new JPlaceholderTextField("Age");

    JTextField ID = new JTextField("ID");
    JTextField PW = new JTextField("PW");
    JTextField Name = new JTextField("Name");
    JTextField Age = new JTextField("age");
    JButton OK = new JButton("OK");

    JLabel labelPW;

    //회원가입할 때 필요한 정보
    String id;
    String pw;
    String name;
    String sage;
    int age;
    SignUp(){
        super("SignUp"); //타이틀
        setLayout(null);
        setSize(1440, 980);
        jPanel.setBounds(0,0,1440,980);
        jPanel.setLayout(null);
        jPanel.setBackground(Color.white);


        placeholderID.setBounds(450,200,530,60);
        jPanel.add(placeholderID);
        placeholderPW.setBounds(450,300,530,60);
        jPanel.add(placeholderPW);
        placeholderName.setBounds(450,400,530,60);
        jPanel.add(placeholderName);
        placeholderAge.setBounds(450,500,530,60);
        jPanel.add(placeholderAge);

        labelPW = new JLabel("비밀번호는 3~7개의 글자여야합니다.");
        labelPW.setForeground(Color.lightGray);
        labelPW.setBounds(795,350, 530,40);
        jPanel.add(labelPW);


        OK.setBounds(680, 600,90,50);
        OK.setForeground(new Color(85,148,255));
        jPanel.add(OK);

        OK.addActionListener(this);

        add(jPanel);
        setVisible(true);



    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == OK){
            id = placeholderID.getText();
            pw = placeholderPW.getText();
            name = placeholderName.getText();
            sage = placeholderAge.getText();
            age = Integer.parseInt(sage);

            if(id == null || pw == null || name == null || sage == null){
                JOptionPane.showMessageDialog(null, "정보 입력을 올바르게 해주세요.");
            } else{
                if(pw.length() < 3 || pw.length() > 7){
                    labelPW.setText("사용할 수 없는 비밀번호입니다.");
                    labelPW.setForeground(Color.RED);

                }else{
                    labelPW.setText("사용할 수 있는 비밀번호입니다.");
                    labelPW.setForeground(Color.lightGray);

                    PersonDao pDao = new PersonDao();
                    Person p = new Person(id,pw,name,age);
                    System.out.println(id);
                    System.out.println(pw);
                    System.out.println(name);
                    System.out.println(age);
                    pDao.insertPerson(p);

                    JOptionPane.showMessageDialog(null, "회원가입이 완료되셨습니다.");
                    new Login();
                    setVisible(false);

                }
            }
        }
    }
}
