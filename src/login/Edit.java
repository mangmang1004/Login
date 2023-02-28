package login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Edit extends JFrame implements ActionListener {
    JPanel jPanel = new JPanel();
    JTextField ID;
    JTextField PW;
    JTextField Name;
    JTextField Age;
    JButton OK = new JButton("OK");

    JLabel labelID;
    JLabel labelPW;

    Person vo;

    //회원가입할 때 필요한 정보
    String id;
    String pw;
    String name;
    String sage;
    int age;

    Edit(Person vo) {
        super("Edit"); //타이틀
        this.vo = vo;
        setLayout(null);
        setSize(1440, 980);
        jPanel.setBounds(0, 0, 1440, 980);
        jPanel.setLayout(null);
        jPanel.setBackground(Color.white);

        ID = new JTextField(vo.getId()); //바뀔 수 없도록 바뀌기
        ID.setEnabled(false);
        ID.setBounds(450, 200, 530, 60);
        jPanel.add(ID);
        PW = new JTextField(vo.getPw());
        PW.setBounds(450, 300, 530, 60);
        jPanel.add(PW);
        Name = new JTextField(vo.getName());
        Name.setBounds(450, 400, 530, 60);
        jPanel.add(Name);
        Age = new JTextField(Integer.toString(vo.getAge()));
        Age.setBounds(450, 500, 530, 60);
        jPanel.add(Age);

        labelID = new JLabel("아이디는 변경하실 수 없습니다.");
        labelID.setForeground(Color.lightGray);
        labelID.setBounds(810,250,530,40);
        jPanel.add(labelID);

        labelPW = new JLabel("비밀번호는 3~7개의 글자여야합니다.");
        labelPW.setForeground(Color.lightGray);
        labelPW.setBounds(795,350, 530,40);
        jPanel.add(labelPW);


        OK.setBounds(680, 600, 90, 50);
        OK.setForeground(new Color(85, 148, 255));
        jPanel.add(OK);
        OK.addActionListener(this);

        add(jPanel);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == OK){
            //System.out.println("여긴 들어오늬?");
            id = vo.getId();
            pw = PW.getText();
            name = Name.getText();
            sage = Age.getText();
            age = Integer.parseInt(sage);


            if(3 > pw.length() || pw.length() > 7){
                labelPW.setText("올바르지 않는 비밀번호입니다.");
                labelPW.setForeground(Color.RED);

            }else {
                labelPW.setText("사용할 수 있는 비밀번호입니다.");
                labelPW.setForeground(Color.lightGray);
                PersonDao pDao = new PersonDao();
                Person p = new Person(id,pw,name,age);
                pDao.updatePerson(p);
                vo = p; //바뀐 값으로 넣어주기
                JOptionPane.showMessageDialog(null, "회원님의 정보가 변경되었습니다.");

                new Basic(vo);
                setVisible(false);
            }


        }
    }
}
