package ch01;

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

    void where(int selection_Value, String operator, String selection_row){
        System.out.print("'*'혹은 조건식(값, 연산자, 열항목의 순서)을 입력해주세요 : ");
        String temp = "";
        temp = sc.nextLine();
        String[] divided_temp2 = temp.split(" ");
        switch(divided_temp2.length){
            case 1: // '*'을 받는경우 
                if(divided_temp2[0].equals("*")){   // '*'인경우 
                    for(i = 0; i < customers.size(); i++){
                        view1.column_Num[i] = true;
                        System.out.println("view1.column : " + view1.column_Num[i]);
                    }
                }
                else{   //다른 문자열이 들어간 경우
                    System.out.println("양식에 맞춰 입력하거나 *을 입력해주세요");
                }
                break;
            case 3 : // 조건식을 받는 경우
                //divided_temp2의 0번 인덱스에 숫자가 들어왔는지 확인하고 변환.
                int selection_Num = 0;
                try {
                    selection_Num = Integer.parseInt(divided_temp2[0]);
                } catch (NumberFormatException e) {
                    System.out.println("지정된 양식에 맞게 작성해주세요");
                    break;
                }
                //2번 인덱스에 들어온 문자열과 일치하는 문자열을 찾고 값을 넣음
                for( i = 0 ; i < User.row_Name.length ; i++){
                    if(divided_temp2[2].equals(User.row_Name[i])){
                        switch(divided_temp2[1]){
                            case "<" :
                                for(i = 0 ; i < customers.size() ; i++){
                                    switch(User.row_Name[i]){
                                        case "age" : 
                                            if(selection_Num < customers.get(i).get_age()){
                                                view1.column_Num[i] = true;
                                            }
                                        case "mvp" :
                                            if(selection_Num.compareTo(customers.get(i).get_mvp())){
                                                view1.column_Num[i] = true;
                                            }
                                        case "grade" :
                                            if(selection_Num < customers.get(i).get_grade()){
                                                view1.column_Num[i] = true;
                                            }    
                                    }
                                }
                                break;
                        }
                        break;
                    }
                }
                break;
                
            default : 
                System.out.println("잘못된 형식입니다.");
        }  
        break;
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