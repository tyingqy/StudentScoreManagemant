import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.List;
class Student{
    private String name;
    private int id;
    /*
     我见过一个很好的想法，它在学生类和课程类中都定义了一个成绩的集合的属性；
     然后创建了一个成绩类，包含了学生、课程两个类和整形score，共三个属性；
     通过这种方式将学生、课程、成绩三个实体类关联了起来。
     可惜我是个fw，写不出来，只能大概理解这种思路。（寄）
     所以我直接在学生类中创建三个整形变量，分别是语数英的成绩。（摆）
    */
    private int ChineseScore;
    private int MathScore;
    private int EnglishScore;
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return id;
    }
    public void setChineseScore(int ChineseScore){
        this.ChineseScore = ChineseScore;
    }
    public int getChineseScore(){
        return ChineseScore;
    }
    public void setMathScore(int MathScore){
        this.MathScore = MathScore;
    }
    public int getMathScore(){
        return MathScore;
    }
    public void setEnglishScore(int EnglishScore){
        this.EnglishScore = EnglishScore;
    }
    public int getEnglishScore(){
        return EnglishScore;
    }
}
public class StudentManger {
    /*
     注意！
     下一行代码定义了文件路径；
     这个文件路径是我自己的；
     记得修改一下。
    */
    private static final String FILE_PATH = "C:/Users/www19/Desktop/list.csv";//定义文件路径
    public static void main(String[] args) {
        List<Student> studentList = new ArrayList<>();
        // 读取学生信息
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] read = line.split(",");
                Student student = new Student();
                student.setName(read[0]);
                student.setId(Integer.parseInt(read[1]));
                student.setChineseScore(Integer.parseInt(read[2]));
                student.setMathScore(Integer.parseInt(read[3]));
                student.setEnglishScore(Integer.parseInt(read[4]));
                studentList.add(student);
            }
        } catch (Exception e) {
            System.out.println("读取文件失败。");
        }
        //运行系统
        System.out.println("欢迎使用学生成绩管理系统");
        while (true){
            System.out.println();
            System.out.println("***********************");
            System.out.println("请选择操作");
            System.out.println("1、添加学生信息");
            System.out.println("2、删除学生信息");
            System.out.println("3、修改学生信息");
            System.out.println("4、查找学生信息");
            System.out.println("5、显示所有学生信息");
            System.out.println("0、退出系统");
            System.out.println("***********************");
            Scanner scan = new Scanner(System.in);
            int n = scan.nextInt();
            if(n == 1){//添加
                boolean next = true;
                boolean right;
                while (next) {
                    System.out.println("请输入学生姓名：");//开始输入学生信息
                    String name = scan.next();
                    int id = 0;
                    int score1 = 0;
                    int score2 = 0;
                    int score3 = 0;
                    do {
                        right = false;
                        System.out.println("请依次输入学生信息（学号、语文成绩、数学成绩、英语成绩）：");
                        try {
                            id = scan.nextInt();
                            score1 = scan.nextInt();
                            if (score1 < 0) {
                                System.out.println("输入的数据过小，请在输入数学和英语成绩后重新输入。");
                                right = true;
                            }
                            score2 = scan.nextInt();
                            if (score2 < 0) {
                                System.out.println("输入的数据过小，请在输入英语成绩后重新输入。");
                                right = true;
                            }
                            score3 = scan.nextInt();
                            if (score3 < 0) {
                                System.out.println("输入的数据过小，请重新输入。");
                                right = true;
                            }
                        } catch (Exception e) {
                            System.out.println("输入数据异常，请重新输入。");
                            scan.nextLine();
                            right = true;
                        }
                    } while (right);
                    //将数据写入对象中
                    Student stu = new Student();
                    stu.setName(name);
                    stu.setId(id);
                    stu.setChineseScore(score1);
                    stu.setMathScore(score2);
                    stu.setEnglishScore(score3);
                    studentList.add(stu);
                    System.out.println("是否继续录入？true/false");
                    do {
                        right = false;
                        try {
                            next = scan.nextBoolean();
                        } catch (Exception e) {
                            System.out.println("输入数据异常，请重新输入。");
                            scan.nextLine();
                            right = true;
                        }
                    } while (right);
                }
            } else if (n == 2) {//删除
                System.out.println("请输入要删除的学生姓名：");
                String name = scan.next();
                //迭代器遍历
                Iterator<Student> across = studentList.iterator();
                boolean success = true;
                while (across.hasNext()){
                    Student stu = across.next();
                    if(stu.getName().equals(name)){
                        across.remove();
                        System.out.println("删除成功。");
                        success = false;
                        break;
                    }
                }
                if(success){
                    System.out.println("未找到该学生的信息。");
                }
            } else if (n == 3) {//修改
                System.out.println("请输入要修改的学生姓名：");
                String name = scan.next();
                //迭代器遍历
                Iterator<Student> across = studentList.iterator();
                boolean success = true;
                boolean done = false;
                while (across.hasNext()) {
                    if (done)//条件是修改完成
                        break;
                    Student stu = across.next();
                    if (stu.getName().equals(name)) {
                        boolean invalid;
                        do {
                            invalid = false;
                            try {
                                System.out.println("请输入学生姓名：");
                                String newName = scan.next();
                                stu.setName(newName);
                                System.out.println("请输入学生学号：");
                                int id = scan.nextInt();
                                stu.setId(id);
                                System.out.println("请输入学生语文成绩：");
                                int score1 = scan.nextInt();
                                if (score1 < 0){
                                    System.out.println("输入成绩为负数，请重新输入。");
                                    invalid = true;
                                }else {
                                    stu.setChineseScore(score1);
                                }
                                if(!invalid){//条件是上一个成绩没出错
                                    System.out.println("请输入学生数学成绩：");
                                    int score2 = scan.nextInt();
                                    if (score2 < 0){
                                        System.out.println("输入成绩为负数，请重新输入。");
                                        invalid = true;
                                    }else {
                                        stu.setMathScore(score2);
                                    }
                                }
                                if (!invalid){
                                    System.out.println("请输入学生英语成绩：");
                                    int score3 = scan.nextInt();
                                    if (score3 < 0){
                                        System.out.println("输入成绩为负数，请重新输入。");
                                        invalid = true;
                                    }else {
                                        stu.setEnglishScore(score3);
                                    }
                                }
                                if (!invalid){
                                    System.out.println("修改成功。");
                                    success = false;
                                    done = true;
                                }
                            }catch (Exception e){
                                System.out.println("输入数据异常，请重新输入。");
                            }
                        } while (invalid);
                    }
                }
                if (success) {
                    System.out.println("未找到该学生的信息。");
                }
            } else if (n == 4) {//查找单个学生
                System.out.println("请输入要查找的学生姓名：");
                String name = scan.next();
                //迭代器遍历
                Iterator<Student> across = studentList.iterator();
                boolean success = true;
                while (across.hasNext()){
                    Student stu = across.next();
                    if(stu.getName().equals(name)){
                        System.out.println("姓名：" + stu.getName());
                        System.out.println("学号：" + stu.getId());
                        System.out.println("语文成绩：" + stu.getChineseScore());
                        System.out.println("数学成绩" + stu.getMathScore());
                        System.out.println("英语成绩" + stu.getEnglishScore());
                        success = false;
                        break;
                    }
                }
                if(success){
                    System.out.println("未找到该学生的信息。");
                }
            } else if (n == 5) {//显示全部
                if(studentList.isEmpty()){
                    System.out.println("暂无数据。");
                }else {
                    System.out.println("已添加的学生信息有：");
                    for (Student stu : studentList){
                        System.out.println("姓名：" + stu.getName());
                        System.out.println("学号：" + stu.getId());
                        System.out.println("语文成绩：" + stu.getChineseScore());
                        System.out.println("数学成绩" + stu.getMathScore());
                        System.out.println("英语成绩" + stu.getEnglishScore());
                    }
                }
            } else if (n == 0) {
                System.out.println("退出成功。");
                break;
            }else {
                System.out.println("输入了其他数字，请重新输入");
            }
        }
        //进行信息保存
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Student student : studentList) {
                StringBuilder write = new StringBuilder();
                write.append(student.getName()).append(",");
                write.append(student.getId()).append(",");
                write.append(student.getChineseScore()).append(",");
                write.append(student.getMathScore()).append(",");
                write.append(student.getEnglishScore()).append("\n");
                writer.write(write.toString());
            }
            System.out.println("学生信息已成功写入CSV文件。");
        } catch (Exception e) {
            System.out.println("写入文件失败。");
        }
    }
}