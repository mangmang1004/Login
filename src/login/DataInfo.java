package login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DataInfo extends JFrame implements ActionListener {
    Person user;
    JTextField txtId = new JTextField(10);
    JTextField txtPw = new JTextField(10);
    JTextField txtName = new JTextField(10);
    int age;
    String sage;
    JTextField txtAge = new JTextField(10);


    JButton editBt;
    JButton delBt;
    JButton home;

    public DataInfo(Person user) {
        super("작업");
        this.user = user;
        setLayout(null);
        //display();
        //startEvent();
        setSize(300,550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //System.out.println("히"+user.getId());
        txtId.setBounds(0,10,300,50);
        txtId.setEnabled(false);
        txtId.setText(user.getId());
        txtPw.setBounds(0,70,300,50);
        txtPw.setText(user.getPw());
        txtName.setBounds(0,130,300,50);
        txtName.setText(user.getName());
        age = user.getAge();
        sage = Integer.toString(age);
        txtAge.setBounds(0,200,300,50);
        txtAge.setText(sage);

        add(txtId);
        add(txtPw);
        add(txtName);
        add(txtAge);

        editBt = new JButton("수정하기");
        editBt.setBounds(40, 300, 100, 50);
        editBt.setForeground(new Color(85, 148, 255));
        add(editBt);
        editBt.addActionListener(this);

        delBt = new JButton("삭제하기");
        delBt.setBounds(160, 300, 100, 50);
        delBt.setForeground(new Color(85, 148, 255));
        add(delBt);
        delBt.addActionListener(this);

        home = new JButton("홈");
        home.setBounds(100, 380, 100, 50);
        home.setForeground(new Color(85, 148, 255));
        add(home);
        home.addActionListener(this);

        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == editBt){
            PersonDao pDao = new PersonDao();
            Person p = new Person(txtId.getText(),txtPw.getText(),txtName.getText(),Integer.parseInt(txtAge.getText()));
            pDao.updatePerson(p);
            JOptionPane.showMessageDialog(null, "회원님의 정보가 변경되었습니다.");

            new Manage();

        }else if(e.getSource() == delBt){
            JOptionPane.showMessageDialog(null, "정말로 탈퇴하시겠습니까?");
            PersonDao pDao = new PersonDao();
            pDao.deletePerson(txtId.getText());

            new Manage();

        }else if(e.getSource() == home){
            new Login();
            setVisible(false);
        }

    }
}
