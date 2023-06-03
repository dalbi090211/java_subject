package ch01;

interface User {    //데이터가 어떤 양식으로 들어와야하는지 정의해주는 인터페이스
    public static final String name = null;
    public static final String mvp = null;
    public static final int grade = 0;
    public static final int age = 0;
    public static final String[] row_Name = {"name", "mvp" , "grade", "age"};
}

class view {
    int[] row_Num = new int[User.row_Name.length];
    int[] column_Num;
}

class User_info implements User{   //고객의 정보를 담을 객체

    //변수, 인터페이스의 변수를 재정의
    private String name, mvp;
    private int grade, age;
    
    //constructor
    /* name, mvp, grade, age순으로 등록 */
    public User_info(String name, String mvp, int grade, int age){
        this.name = name;
        this.mvp = mvp;
        this.grade = grade;
        this.age = age;
    }

    //Setter함수
    public void set_name(String name){
        this.name = name; 
    }
    public void set_mvp(String mvp){
        this.mvp = mvp; 
    }
    public void set_grade(int grade){
        this.grade = grade; 
    }
    public void set_age(int age){
        this.age = age; 
    }

    //Getter함수
    public String get_name(){
        return name;
    }
    public String get_mvp(){
        return mvp;
    }
    public int get_grade(){
        return grade;
    }
    public int get_age(){
        return age;
    }

    /*값을 전부 출력하는 함수*/
    public void print_information(){
        System.out.println("[ 이름 : " + name + " 나이 : " + age + " 등급 : " + mvp + " 학년 : " + grade  + " ]");
    }

}