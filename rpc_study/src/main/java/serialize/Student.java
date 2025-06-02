package serialize;

/**
 * @description:
 * @author: jingouzhui
 * @date: 2025/5/29 1:10
 */
import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.io.*;
import java.nio.file.Files;

@JsonDeserialize(builder = Student.StudentBuilder.class)
public class Student implements Serializable {
    //学号
    private int no;
    //姓名
    private  String name;

    private  int age;

    private  int score;

    private Address address;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }

    private Student(StudentBuilder studentBuilder){
        this.no = studentBuilder.no;
        this.name = studentBuilder.name;
        this.age = studentBuilder.age;
        this.score = studentBuilder.score;
        this.address = studentBuilder.address;
    }
    public Student(){
    }

    @Override
    public String toString() {
        return "Student{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", score=" + score +
                ", address=" + address +
                '}';
    }

    public static StudentBuilder builder(){
            return new StudentBuilder();
    }
    @JsonPOJOBuilder(withPrefix = "")
    public static class StudentBuilder {
        private int no;
        private String name;
        private int age;
        private int score;
        private Address address;

        public StudentBuilder(){

        }
        public StudentBuilder(int no,String name,int age,int score,Address address){
            this.no = no;
            this.name = name;
            this.age = age;
            this.score = score;
            this.address = address;
        }
        public StudentBuilder no(int no){
            this.no = no;
            return this;
        }
        public StudentBuilder name(String name){
            this.name = name;
            return this;
        }
        public StudentBuilder age(int age){
            this.age = age;
            return this;
        }
        public StudentBuilder score(int score){
            this.score = score;
            return this;
        }
        public StudentBuilder address(Address address){
            this.address = address;
            return this;
        }

        public Student build(){
        return new Student(this);
        }
    }

    /**
     *JDK原生序列化方式
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
      jdkSerialize();
        jsonSerialize();
        hessianSerialize();

    }
    public static  void jdkSerialize() throws IOException, ClassNotFoundException {

        String home = System.getProperty("user.home");
        String basePath = home + "/Desktop";
        File file = new File(basePath + "/student1.txt");
        FileOutputStream fos = new FileOutputStream(file);
        Address address = new Address();
        address.setCity("重庆");
        address.setProvince("重庆市");
        address.setDistrict("北碚区");
        address.setDetail("天生路2号西南大学");
        Student jingouzhui = Student.builder()
                .no(123)
                .name("jingouzhui")
                .age(16)
                .score(100)
                .address(address)
                .build();
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(jingouzhui);
        oos.flush();
        oos.close();
        System.out.println("jdk序列化占用字节:"+file.length());
        FileInputStream fis = new FileInputStream(basePath + "/student1.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        Student deJingouzhui = (Student) ois.readObject();
        ois.close();
        System.out.println(deJingouzhui);
    }
    public static void jsonSerialize() throws IOException {
        String home = System.getProperty("user.home");
        String basePath = home + "/Desktop";
        ObjectMapper objectMapper = new ObjectMapper();
        Address address = new Address();
        address.setCity("重庆");
        address.setProvince("重庆市");
        address.setDistrict("北碚区");
        address.setDetail("天生路2号西南大学");
       /* Student student = new Student.StudentBuilder()
                .no(123)
                .name("jingouzhui-json")
                .age(16)
                .score(100)
                .address(address)
                .build();*/
        Student student = Student.builder()
                .no(123)
                .name("jingouzhui-json")
                .age(16)
                .score(100)
                .address(address)
                .build();
        //序列化到文件
        File file = new File(basePath + "/student2.txt");
        objectMapper.writeValue(file, student);
        System.out.println("json序列化占用字节:"+file.length());



        //反序列话
        Student deJingouzhui = objectMapper.readValue(new File(basePath + "/student2.txt"), Student.class);
        System.out.println(deJingouzhui);
    }

    public static void hessianSerialize() throws IOException, ClassNotFoundException {

        String home = System.getProperty("user.home");
        String basePath = home + "/Desktop";
        Address address = new Address();
        address.setCity("重庆");
        address.setProvince("重庆市");
        address.setDistrict("北碚区");
        address.setDetail("天生路2号西南大学");
        Student jingouzhui = Student.builder()
                .name("jingouzhui-json")
                .no(123)
                .age(16)
                .score(100)
                .address(address)
                .build();
        //ByteArrayOutputStream bos = new ByteArrayOutputStream();
        File file = new File(basePath + "/student3.txt");
        FileOutputStream fos = new FileOutputStream(file);
        Hessian2Output output = new Hessian2Output(fos);
        output.writeObject(jingouzhui);
        output.flush();

        System.out.println("hessian序列化占用字节:"+file.length());

        //把刚才序列化出来的byte数组转化为student对象
        Hessian2Input input = new Hessian2Input(Files.newInputStream(file.toPath()));
        Student stu = (Student) input.readObject();
        input.close();
        System.out.println(stu);
    }
}