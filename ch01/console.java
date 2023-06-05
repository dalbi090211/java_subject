package ch01;

import java.util.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.io.File;	//txt파일을 불러올 패키지
import java.io.FileNotFoundException;	//파일을 못 찾는 경우
import java.io.FileReader;	//파일 읽을 거
import java.io.BufferedReader;	//버퍼
import java.io.IOException;	//잘못된 형식의 파일일 경우


public class console{

    final int END = 5000; 

    public console (){
        //변수 선언
        int  i, j;
        Boolean end_flag = true;	//프로그램의 종료 트리거
        int user_choice, arr_max = 0;
        String name, mvp;
        int grade, age;
        String[] Str_temp = new String[4];
        view view1 = new view();
        String temp = "";
        
        //고객리스트 생성
        ArrayList<User_info> customers = new ArrayList<User_info>();
        
        //파일 불러오기
        try {
            String temp1, temp2;
        	File data = new File("data.txt");
    		if(data.exists())	//남은 문장이 없을 때 까지
    		{	
    			//data를 읽어들이고 객체를 생성
    		    BufferedReader inFile = new BufferedReader(new FileReader(data));
    		    String sLine = null;
    		    while( (sLine = inFile.readLine()) != null ) {	//한 줄씩 읽어들임
        			//객체추가
                    Str_temp = sLine.split("\\s");
                    temp1 = null;
                    temp2 = null;
                    temp1 = Str_temp[1];
                    temp2 = Str_temp[3];
    		    	 customers.add(new User_info(Str_temp[0], Integer.parseInt(temp1), Str_temp[2], Integer.parseInt(temp2)));
                     arr_max++; 
    		    }
                //reader닫음
                inFile.close();
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
        //data의 개수만큼 view의 행을 생성
       view1.column_Num = new Boolean[customers.size()];

        //프로그램실행, 5번을 입력받아 end_flag가 flase가 되면 종료
        while(end_flag){    //1. SELECT   |   2. INSERT   |   3. UPDATE   |   4. DELETE   |   5. END 순서
            print_menu();
			user_choice = sc.nextInt();
            sc.nextLine();  //입력버퍼 비우는 용도

            switch(user_choice){    
                case 1 :    //select를 선택한 경우
                    for(i = 0 ; i < customers.size() ; i++){
                        view1.column_Num[i] = false;
                    }

                    //출력할 열 설정부분
                    j = 0;
                    System.out.print("출력할 열항목 혹은 '*'을 입력해주세요(열 이름 : name, age, mvp, grade)");
                    temp = sc.nextLine();
                    String[] divided_temp1 = temp.split(" ");
                    for(int k = 0 ; k < divided_temp1.length ; k++){
                        if(divided_temp1[k] == "*"){    // '*'을 선택한 경우 모든 열을 선택하고 break로 탈출
                            for( i = 0 ; i < User.row_Name.length ; i++){
                                view1.row_Num[j] = i;
                            }
                            break;
                        }
                        // '*'이 아닌 경우 하나하나씩 row의 인덱스를 view1에 등록
                        for( i = 0 ; i < User.row_Name.length ; i++){
                            if(divided_temp1[k].equals(User.row_Name[i])){  //일치하는 열을 찾은 경우
                                view1.row_Num[j] = i;
                                j++;
                                i = END;    //루프를 종료
                            }
                        }
                    }

                    //조건식부분    
                    temp = "";  //먼저 줄단위로 입력받을 변수
                    //입력
                    System.out.print("1. *, 2. 조건식, 3. or연산(||) ");
                    user_choice = sc.nextInt();
                    sc.nextLine();
                    switch(user_choice){
                        case 1 :    //전체를 선택한 경우
                            for(i = 0; i < customers.size(); i++){
                                view1.column_Num[i] = true;
                            }
                            break;
                        case 2 :    //하나의 조건식만 선택한 경우
                            try{
                                input_Where(view1, customers); 
                            }
                            catch(Exception e){
                                System.out.println(e.getMessage());
                                break;
                            }
                            break;
                        case 3 : //두 개의 조건식을 선택한 경우
                            try{
                                input_Where(view1, customers);
                                input_Where(view1, customers);
                            }
                            catch(Exception e){
                                System.out.println(e.getMessage());
                                break;
                            }
                            break;
                        default :   //1~3를 입력하지 않은 경우
                            System.out.println("잘못된 형식입니다.");
                    }       
                    break;
                
                case 2 : 
                    System.out.print("\033[H\033[2J");
                    System.out.print("이름 : ");
                    name = sc.next();
                    System.out.print("등급 : ");
                    mvp = sc.next();
                    System.out.print("학년 : ");
                    grade = sc.nextInt();
                    System.out.print("나이 : ");
                    age = sc.nextInt();
                    ///입력값(이름, 등급, 학년, 나이등)에 따른 예외처리 필요
                    customers.add(new User_info(name, age, mvp, grade));
                    arr_max++;
                    break;
                //고객리스트 출력
                case 3 : 
                    System.out.print("\033[H\033[2J");
                	for(i = 0 ; i  < arr_max; i++) {
                    	customers.get(i).print_information();
                	}
                    System.out.println("------------------------------------------------------------------");
                    System.out.println("1. 고객 삭제   |  2. 고객 정보 수정   |   3. 종료");
                    System.out.println("------------------------------------------------------------------");
                    System.out.println("실행할 번호를 입력하세요 : ");
                    //번호에 따른 예외처리 필요
                    user_choice = sc.nextInt();
                    switch(user_choice){
                        case 1 :
                            System.out.println("삭제할 학생의 번호를 입력해주세요.(0입력 시 지우지않음)");
                            user_choice = sc.nextInt();
                            if(user_choice!=0 && user_choice < arr_max - 1){
                                customers.remove(user_choice);
                                arr_max--;
                            }
                            break;
                    }
                    break;
                //종료
                case 5 : 
                    end_flag = false;
                    break;
            }
            /*System.out.print("\033[H\033[2J");*/
        }
        System.out.println("정상적인 종료입니다.");
        sc.close();
    }
    
    public void input_Where(view view1, ArrayList<User_info> customers){
        int m = 0;
        System.out.println("조건식을 입력해주세요(값, 연산자, 기준열 의 순서)");
        String temp = sc.nextLine();
        String[] divided_temp2 = temp.split(" ");
        for(m = 0 ; m < User.row_Name.length ; m++ ){   //2번 인덱스에 들어온 문자열과 일치하는 문자열을 찾고 값을 넣음
            if(divided_temp2[2].equals(User.row_Name[m])){  //입력받은 문자열과 열의 이름이 일치할 경우
                m = END;    //일치하는 열을 찾은 경우 루프를 탈출
                if(User.row_Name[m] == "grade" || User.row_Name[m] == "age"){   //정수형 value를 필요로 하는 경우
                    int int_value = 0;
                    try{
                        int_value = Integer.parseInt(divided_temp2[0]);
                    }
                    catch (NumberFormatException e){
                        System.out.println("학년(grade)과 나이(age)는 정수로만 비교할 수 있습니다.");
                        break;
                    }
                    view1.where_calc(int_value, divided_temp2[1], divided_temp2[2], customers);  
                }
                else if (User.row_Name[m] == "name" || User.row_Name[m] == "mvp"){  //문자형 value를 필요로 하는 경우
                    view1.where_calc(divided_temp2[0], divided_temp2[1], divided_temp2[2], customers);
                }
            }
        }
        if(m != END){
            throw new InputMismatchException("일치하는 열이 없습니다.");
        }
    }


    public void print_menu() {
        System.out.println("------------------------------------------------------------------");
        System.out.println("1. SELECT   |   2. INSERT   |   3. UPDATE   |   4. DELETE   |   5. END");
        System.out.println("------------------------------------------------------------------");
        System.out.print("실행할 번호를 입력하세요 : ");		
    }

    static Scanner sc = new Scanner(System.in);

    public static void main(String args[]){
    	console thread1 = new console();
    }
}
