package login;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//insert, update, delete, select
public class PersonDao {

    private Connection conn;
    private static final String url = "jdbc:mysql://localhost:3306/PERSON2";
    private static final String userName = "root";
    private static final String password = "1004";

    public PersonDao(){
        try{
            conn = DriverManager.getConnection(url, userName, password);
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("연결 실패 !");
        }
    }

    public void insertPerson (Person person){
        String sql = "insert into Person values(?,?,?,?);";
        PreparedStatement pstmt = null;

        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, person.getId());
            pstmt.setString(2,person.getPw());
            pstmt.setString(3,person.getName());
            pstmt.setInt(4,person.getAge());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            try{
                if (pstmt != null && !pstmt.isClosed())
                    pstmt.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    public void updatePerson(Person person){
        String sql = "update Person set PersonPW=?, PersonName=?, Age=? where PersonID=?;";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,person.getPw());
            pstmt.setString(2,person.getName());
            pstmt.setInt(3,person.getAge());
            pstmt.setString(4, person.getId());
            pstmt.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            try{
                if (pstmt != null && !pstmt.isClosed())
                    pstmt.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    public void deletePerson (String id){
        String sql = "delete from Person where PersonID = ?;";
        PreparedStatement pstmt = null;
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,id);
            pstmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try{
                if(pstmt != null && !pstmt.isClosed())
                    pstmt.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    public Person selectOne (String id){
        String sql = "select * from Person where PersonID = ?;";
        PreparedStatement pstmt = null;
        Person ps = new Person();

        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,id);
            ResultSet rs = pstmt.executeQuery();

            if(rs.next()){
                ps.setPw(rs.getString("PersonPW"));
                ps.setName(rs.getString("PersonName"));
                ps.setAge(rs.getInt("Age"));
            }
//            System.out.println(ps.getPw());

        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null && !pstmt.isClosed())
                    pstmt.close();
            } catch (SQLException e){
                e.printStackTrace();
            }

        }
        return ps;
    }

    List<Person> selectAll(){
        String sql = "select * from Person;";
        PreparedStatement pstmt = null;

        List<Person> list = new ArrayList<Person>();

        try{
            pstmt = conn.prepareStatement(sql);
            ResultSet re = pstmt.executeQuery();

            while(re.next()){
                Person ps = new Person();
                ps.setId(re.getString("PersonID"));
                ps.setPw(re.getString("PersonPW"));
                ps.setName(re.getString("PersonName"));
                ps.setAge(re.getInt("Age"));
                list.add(ps);
            }
            re.close();
        }catch (SQLException e){
            e.printStackTrace();
        } finally {
            try{
                if (pstmt != null && !pstmt.isClosed())
                    pstmt.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return list;
    }




}
