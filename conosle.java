import java.util.Scanner;
import java.util.ArrayList;


class User_info {   //고객의 정보를 담을 객체
    //변수
    String name, mvp;
    int grade, age;
    
    //constructor
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
        System.out.println("[ 고객이름: " + name + " , mvp등급: " + mvp + " , 학년: " + grade + " , 나이: " + age + " ]")
    }
    
}


public class conosle {
    public static void main(String args[]){
        //변수 선언
        Boolean end_flag = true;
        int user_choice;
        Scanner sc = new Scanner(System.in);

        //고객리스트 생성
        ArrayList<User_info> customers = new ArrayList<User_info>();


        //기능 실행
        while(end_flag){
            System.out.println("------------------------------------------------------------------");
			System.out.println("1. 고객 추가   |   2. 고객 삭제   |   3. 고객리스트   |   4. 고객 정보 수정   |   5. 종료");
			System.out.println("------------------------------------------------------------------");
			System.out.print("실행할 번호를 입력하세요:");
			user_choice = sc.nextInt();=

            switch(user_choice){
                case 1 : 
                case 5 : end_flag = false;
            }
            System.out.print("\033[H\033[2J");
        }

    }
}
