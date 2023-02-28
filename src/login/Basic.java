package login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Basic extends JFrame implements ActionListener {
    JButton edit = new JButton("내 정보 수정");
    JButton logout = new JButton("로그아웃");
    JButton exit = new JButton("탈퇴");

    ImageIcon login = new ImageIcon("image/메인 화면.jpg");
    JLabel setMainImage;

    Person vo;
    Basic(Person vo){
        super("Main"); //타이틀
        this.vo = vo;
        setSize(1440, 980);
        JPanel jPanel = new JPanel();
        jPanel.setBackground(Color.white);
        jPanel.setLayout(null);
        jPanel.setBounds(0,0,1440,980);
        add(jPanel);


        edit.setBounds(570, 540,90,50);
        edit.setForeground(new Color(85,148,255));
        jPanel.add(edit);

        logout.setBounds(670, 540,90,50);
        logout.setForeground(new Color(85,148,255));
        jPanel.add(logout);

        exit.setBounds(770, 540,90,50);
        exit.setForeground(new Color(85,148,255));
        jPanel.add(exit);

        Image login_Image = login.getImage();
        Image cmain_Image = login_Image.getScaledInstance(400,300,Image.SCALE_SMOOTH);
        ImageIcon cmain = new ImageIcon(cmain_Image);
        setMainImage = new JLabel(cmain);
        setMainImage.setBounds(510,150, 400, 300);
        jPanel.add(setMainImage);


        edit.addActionListener(this);
        logout.addActionListener(this);
        exit.addActionListener(this);

        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == edit){
            new Edit(vo);
            setVisible(false);
        }else if(e.getSource() == logout){
            JOptionPane.showMessageDialog(null, "정말로 로그아웃 하시겠습니까?");
            new Login();
            setVisible(false);
        }else if(e.getSource() == exit){
            JOptionPane.showMessageDialog(null, "정말로 탈퇴하시겠습니까?");
            PersonDao pDao = new PersonDao();
            pDao.deletePerson(vo.getId());
            new Login();
            setVisible(false);

        }
    }
}
