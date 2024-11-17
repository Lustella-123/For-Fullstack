package construct;

public class MemberConstruct {
    String name;
    int age;
    int grade;

    MemberConstruct(String name, int age, int grade) {
        System.out.println("name:" + name + "age:" + age + "grade:" + grade);
        this.name = name;
        this.age = age;
        this.grade = grade;
    }
}
