import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;	//txt파일을 불러올 패키지
import java.io.FileNotFoundException;	//파일을 못 찾는 경우
import java.io.FileReader;	//파일 읽을 거
import java.io.BufferedReader;	//버퍼
import java.io.IOException;	//잘못된 형식의 파일일 경우



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
        System.out.println("[ 고객이름: " + name + " , mvp등급: " + mvp + " , 학년: " + grade + " , 나이: " + age + " ]");
    }
    
}


public class console{
	
    public static void main(String args[]){
    	
        //변수 선언
    	int i;	//인자
        Boolean end_flag = true;	//프로그램의 종료 트리거
        int user_choice, arr_max;
        Scanner sc = new Scanner(System.in);
        String name, mvp, grade, age;
        String[] Str_temp = new String[4];
        
        //고객리스트 생성
        ArrayList<User_info> customers = new ArrayList<User_info>();
        
        //파일 불러오기
        try {
        	File data = new File("data.txt");
    		if(data.exists())	//남은 문장이 없을 때 까지
    		{	
    			//data를 읽어들이고 객체를 생성
    		    BufferedReader inFile = new BufferedReader(new FileReader(data));
    		    String sLine = null;
    		    while( (sLine = inFile.readLine()) != null ) {	//한 줄씩 읽어들임
    		    	//변수초기화
        			name = null;
        			mvp = null;
        			grade = null; 
        			age = null;
        			Str_temp = null;
        			
        			//공백문자 기준으로 끊음
        			Str_temp = sLine.split("\\s");
        			name = Str_temp[0];
        			mvp = Str_temp[1];
        			grade = Str_temp[2];
        			age = Str_temp[3];
        			//객체추가
    		    	 customers.add(new User_info(name, mvp, Integer.parseInt(grade), Integer.parseInt(age)));
    		    }
    		}
        	
        }
        catch(FileNotFoundException e) {	//파일을 찾을 수 없을 때
        	System.out.println("파일을 찾을 수 없습니다.");
        	end_flag = false;
        }
        catch(IOException e) {	//파일을 정상적으로 열지 못한 경우
        	System.out.println("잘못된 파일 입니다.");
        	end_flag = false;
        }

        //기능 실행
        while(end_flag){
            System.out.println("------------------------------------------------------------------");
			System.out.println("1. 고객 추가   |   2. 고객 삭제   |   3. 고객리스트   |   4. 고객 정보 수정   |   5. 종료");
			System.out.println("------------------------------------------------------------------");
			System.out.print("실행할 번호를 입력하세요:");
			user_choice = sc.nextInt();

            switch(user_choice){
                case 3 : 
                	for(i = 0 ; i  < arr_max; i++) {
                    	customers.get(i).print_information();
                	}
                case 5 : end_flag = false;
            }
            System.out.print("\033[H\033[2J");
        }

    }
}
