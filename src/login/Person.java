package login;

public class Person {

    //모델 1:1 대응
    private String id;
    private String pw;
    private String name;
    private int age;

    public Person(String id, String pw, String name, int age){
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.age = age;
    }

    public Person(){};

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
