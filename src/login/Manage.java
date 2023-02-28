package login;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class Manage extends JFrame implements ActionListener, MouseListener {
    private final String[] labels = {"ID", "PW", "Name", "Age"};
  //  JPanel jPanel = new JPanel();

    PersonDao dao = new PersonDao();
    Person selectUser;

    List<Person> voList = new ArrayList<>();



    private  JScrollPane scrollPane;
    private JTable table;

    private JButton editBt;
    private JButton delBt;


    String id;
    String pw;
    String name;
    String sage;
    int age;

    Manage(){

        super("Admin"); //타이틀
        setSize(1440, 980);
        //setLayout(null);
//        jPanel.setBounds(0,0,1440,980);
//        jPanel.setLayout(null);
//        jPanel.setBackground(Color.white);

        //setVisible(true);

        //voList = new ArrayList<>();
        voList = dao.selectAll();
        System.out.println(voList.size());
        String[][] contents = new String[voList.size()][5];

        for(int i=0; i<voList.size(); i++){

            contents[i][0] = voList.get(i).getId();
            contents[i][1] = voList.get(i).getPw();
            contents[i][2] = voList.get(i).getName();
            contents[i][3] = String.valueOf(voList.get(i).getAge());
        }

        table = new JTable(contents, labels);
        table.setBounds(10,10,500,500);
        JScrollPane jScrollPane = new JScrollPane(table);
        jScrollPane.setPreferredSize(new Dimension(200,200));
        add(jScrollPane);

        table.addMouseListener(this);

        setVisible(true);


//       editBt = new JButton("수정하기");
//        editBt.setBounds(680, 600, 90, 50);
//        editBt.setForeground(new Color(85, 148, 255));
//        add(editBt);
//        editBt.addActionListener(this);

//        delBt = new JButton("삭제하기");
//        delBt.setBounds(700, 600, 90, 50);
//        delBt.setForeground(new Color(85, 148, 255));
//        add(delBt);
//        delBt.addActionListener(this);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == editBt){

            PersonDao pDao = new PersonDao();
            pDao.updatePerson(selectUser);
            JOptionPane.showMessageDialog(null, "회원님의 정보가 변경되었습니다.");

        }else if(e.getSource() == delBt){
            JOptionPane.showMessageDialog(null, "정말로 탈퇴하시겠습니까?");
            PersonDao pDao = new PersonDao();
            pDao.deletePerson(id);

        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int row = table.getSelectedRow();

        id = (String)table.getValueAt(row,0);
        pw = (String)table.getValueAt(row,1);
        name = (String)table.getValueAt(row,2);
        sage = (String) table.getValueAt(row,3);
        age = Integer.parseInt(sage);

        System.out.println(id);
        System.out.println(pw);
        System.out.println(name);
        System.out.println(age);


        selectUser = new Person(id, pw, name, age);

        //새 창을 띄우면서 dto객체를 넘기기
        new DataInfo(selectUser);

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
