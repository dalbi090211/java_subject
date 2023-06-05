package ch01;

import java.util.ArrayList;
import java.util.InputMismatchException;

interface User {    //데이터가 어떤 양식으로 들어와야하는지 정의해주는 인터페이스
    public static final String name = null;
    public static final String mvp = null;
    public static final int grade = 0;
    public static final int age = 0;
    public static final String[] row_Name = {"name", "age" , "mvp", "grade"};
}

class view {
    int[] row_Num = new int[User.row_Name.length];
    Boolean[] column_Num;

    void where_calc(String selection_Value, String operator, String row_Name, ArrayList<User_info> customers) throws InputMismatchException{ //인자로 조건식(값, 연산자, 열의 순서), 고객리스트를 받고 연산에 따라 표기여부를 변경하는 함수
        int i = 0;
        switch(row_Name){
            case "name" : // 이름을 비교하는 경우
                switch(operator){ //연산자에 따라 연산을 수행
                    //인코딩값을 비교
                    case "<" :  
                    for(i = 0; i < customers.size(); i ++){
                        if(selection_Value.compareTo(customers.get(i).get_name()) < 0){
                            this.column_Num[i] = true;
                        }
                    }
                    break;
                    case "<=" :  
                        for(i = 0; i < customers.size(); i ++){
                            if(selection_Value.compareTo(customers.get(i).get_name()) < 0 || selection_Value.equals(customers.get(i).get_name())){
                                this.column_Num[i] = true;
                            }
                        }
                        break;
                    case ">" :  
                        for(i = 0; i < customers.size(); i ++){
                            if(selection_Value.compareTo(customers.get(i).get_name()) > 0){
                                this.column_Num[i] = true;
                            }
                        }
                        break;
                    case ">=" :  
                        for(i = 0; i < customers.size(); i ++){
                            if(selection_Value.compareTo(customers.get(i).get_name()) > 0 || selection_Value.equals(customers.get(i).get_name())){
                                this.column_Num[i] = true;
                            }
                        }
                        break;
                    //성, 혹은 이름이 일치하는 지를 검사
                    case "=" :  
                        if(selection_Value.length() == 1){
                            for(i = 0 ; i < customers.size(); i ++){
                                if(selection_Value.charAt(0) == customers.get(i).get_name().charAt(0))  //입력받은 값이 한글자라면 성이 일치하는지 여부를 검사
                                {
                                    this.column_Num[i] = true;
                                }
                            }
                        }
                        else if(selection_Value.length() == 2){
                            for(i = 0 ; i < customers.size(); i ++){
                                if(selection_Value.charAt(0) == customers.get(i).get_name().charAt(1))  //입력받은 값이 두글자라면 이름이 일치하는지 여부를 검사
                                {
                                    if(selection_Value.charAt(1) == customers.get(i).get_name().charAt(2)){
                                        this.column_Num[i] = true;
                                    }
                                }
                            }
                        }
                        else if(selection_Value.length() == 3){
                            for(i = 0 ; i < customers.size(); i ++){
                                if(selection_Value.equals(customers.get(i).get_name()))  //입력받은 값이 세글자라면 이름이 일치하는지 여부를 검사
                                    this.column_Num[i] = true;  
                            }
                        }
                        break;
                }
                break;
            
            case "mvp" : // 등급을 비교하는 경우
                switch(operator){ //연산자에 따라 연산을 수행
                    case "<" :  
                        for(i = 0; i < customers.size(); i ++){
                            if(selection_Value.compareTo(customers.get(i).get_mvp()) < 0){
                                this.column_Num[i] = true;
                            }
                        }
                        break;
                    case "<=" :  
                        for(i = 0; i < customers.size(); i ++){
                            if(selection_Value.compareTo(customers.get(i).get_mvp()) < 0 || selection_Value.equals(customers.get(i).get_mvp())){
                                this.column_Num[i] = true;
                            }
                        }
                        break;
                    case ">" :  
                        for(i = 0; i < customers.size(); i ++){
                            if(selection_Value.compareTo(customers.get(i).get_mvp()) > 0){
                                this.column_Num[i] = true;
                            }
                        }
                        break;
                    case ">=" :  
                        for(i = 0; i < customers.size(); i ++){
                            if(selection_Value.compareTo(customers.get(i).get_mvp()) > 0 || selection_Value.equals(customers.get(i).get_mvp())){
                                this.column_Num[i] = true;
                            }
                        }
                        break;   
                    case "=" :  
                        for(i = 0; i < customers.size(); i ++){
                            if(selection_Value.equals(customers.get(i).get_mvp())){
                                this.column_Num[i] = true;
                            }
                        }
                        break;
                }
                break;    
        }
    }

    void where_calc(int selection_Value, String operator, String row_Name, ArrayList<User_info> customers) throws InputMismatchException{    //인자로 조건식(값, 연산자, 열의 순서), 고객리스트를 받고 연산에 따라 표기여부를 변경하는 함수
        int i = 0;
            switch(row_Name){
                case "age" : // 나이를 비교하는 경우
                    switch(operator){ //연산자에 따라 연산을 수행
                        case "<" :  
                            for(i = 0; i < customers.size(); i ++){
                                if(selection_Value < customers.get(i).get_age()){
                                    this.column_Num[i] = true;
                                }
                            }
                            break;
                        case "<=" :  
                            for(i = 0; i < customers.size(); i ++){
                                if(selection_Value < customers.get(i).get_age() || selection_Value == customers.get(i).get_age()){
                                    this.column_Num[i] = true;
                                }
                            }
                            break;
                        case ">" :  
                            for(i = 0; i < customers.size(); i ++){
                                if(selection_Value > customers.get(i).get_age()){
                                    this.column_Num[i] = true;
                                }
                            }
                            break;
                        case ">=" :  
                            for(i = 0; i < customers.size(); i ++){
                                if(selection_Value > customers.get(i).get_age() || selection_Value == customers.get(i).get_age()){
                                    this.column_Num[i] = true;
                                }
                            }
                            break;
                        case "=" : 
                            for(i = 0; i < customers.size(); i ++){
                                if(selection_Value == customers.get(i).get_age()){
                                    this.column_Num[i] = true;
                                }
                            }
                            break; 
                    }
                    break;
                
                    case "grade" : // 등급을 비교하는 경우
                        switch(operator){ //연산자에 따라 연산을 수행
                            case "<" :  
                                for(i = 0; i < customers.size(); i ++){
                                    if(selection_Value < customers.get(i).get_grade()){
                                        this.column_Num[i] = true;
                                    }
                                }
                                break;
                            case "<=" :  
                                for(i = 0; i < customers.size(); i ++){
                                    if(selection_Value < customers.get(i).get_grade() || selection_Value == customers.get(i).get_grade()){
                                        this.column_Num[i] = true;
                                    }
                                }
                                break;
                            case ">" :  
                                for(i = 0; i < customers.size(); i ++){
                                    if(selection_Value > customers.get(i).get_grade()){
                                        this.column_Num[i] = true;
                                    }
                                }
                                break;
                            case ">=" :  
                                for(i = 0; i < customers.size(); i ++){
                                    if(selection_Value > customers.get(i).get_grade() || selection_Value == customers.get(i).get_grade()){
                                        this.column_Num[i] = true;
                                    }
                                }
                                break;
                            case "=" : 
                                for(i = 0; i < customers.size(); i ++){
                                    if(selection_Value == customers.get(i).get_grade()){
                                        this.column_Num[i] = true;
                                    }
                                }
                                break; 
                        }
                    break;    
            }
        }
    
}

class User_info implements User{   //고객의 정보를 담을 객체

    //변수, 인터페이스의 변수를 재정의
    private String name, mvp;
    private int grade, age;
    
    //constructor
    /* name, mvp, grade, age순으로 등록 */
    public User_info(String name, int age, String mvp, int grade){
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