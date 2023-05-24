package ch01;

interface User {    //데이터가 어떤 양식으로 들어와야하는지 정의해주는 인터페이스
    public static final String name = null;
    public static final String mvp = null;
    public static final int grade = 0;
    public static final int age = 0;
    public static final String[] column_Name = {"name", "mvp" , "grade", "age"};
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

public void SELECT(){

    //ArrayList<User_info> joined_Arr = new ArrayList<User_info>();
    String[] selected_column = new String[USER.column_Name.length];
    String[] operator[3];
    int i = 0 ;
    System.out.print("SELECT : ");
    while(sc.next() == null){
        selected_column[i] = sc.next();
        for(int i = 0 ; i < )
        i++;
    }
    //받은 문자열에 대한 예외처리 필요함

    //joined_Arr = joined_Arr;
    System.out.print("WHERE : ");
    {
        for(int i = 0 ; i < 3; i++){
            operator[i] = sc.next();
        }

        try{
            operator[0] = Integer.parseInt(operator[0]);
            //인덱스 2번이 열이름인 경우
            switch(operator[1]){

            }

        }catch(NumberFormatException e){    //인덱스 0번이 열이름인 경우
            operator[2] = Integer.parseInt(operator[2]);

            
        }
    }
    //joined_Arr = where(joined_Arr);
    System.out.print("ORDERBY : ");

    for(int i = 0; i < selected_column.length ; i++){

    }
    
}

}