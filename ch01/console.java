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
        int user_choice;
        String[] Str_temp = new String[4];
        view view1 = new view();
        
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

        view1.column_Num = new ArrayList<Boolean> (customers.size());
        for(i = 0 ; i < customers.size(); i++){
            view1.column_Num.add(false);
        }

        //프로그램실행, 5번을 입력받아 end_flag가 flase가 되면 종료
        while(end_flag){    //1. SELECT   |   2. INSERT   |   3. UPDATE   |   4. DELETE   |   5. END 순서
            print_menu();
			user_choice = sc.nextInt();
            sc.nextLine();  //입력버퍼 비우는 용도

            switch(user_choice){    
                case 1 :    //select를 선택한 경우
                    String temp = "";   //값을 입력받을 변수
                    String selection_row = "";

                    //view의 값들을 초기화
                    for(i = 0 ; i < customers.size(); i++){
                         view1.column_Num.set(i, false);
                    }
                    for(i = 0 ; i < User.row_Name.length; i ++){
                        view1.row_Num[i] = END;
                    }

                    //정렬 부분(오름차순만)
                    System.out.print("정렬할 기준열 : ");
                    selection_row = sc.next();
                    if(selection_row.equals("name"))
                            Collections.sort(customers, new NameComparator());
                    else if(selection_row.equals("age"))
                            Collections.sort(customers, new AgeComparator());
                    else if(selection_row.equals("mvp"))
                            Collections.sort(customers, new MVPComparator());
                    else if(selection_row.equals("grade"))
                            Collections.sort(customers, new GradeComparator());
                    else
                        System.out.println("name, age, mvp, grade 중 입력해주세요.");
                    sc.nextLine();

                    //출력할 열 설정부분
                    System.out.print("출력할 열항목 혹은 '*'을 입력해주세요(열 이름 : name, age, mvp, grade) : ");
                    temp = sc.nextLine();
                    String[] divided_temp = temp.split(" ");
                    if(divided_temp[0].length() == 1){  // '*'이 아닌 경우 하나하나씩 비교하여 일치하는 열을 찾고 입력받은 열의 인덱스를 view1에 등록
                        if(divided_temp[0].equals("*")){    // '*'을 선택한 경우 모든 열을 선택하고 break로 탈출
                            for( j = 0 ; j < User.row_Name.length ; j++){
                                view1.row_Num[j] = j;
                            }
                        }
                        else{
                            throw new InputMismatchException("문자열의 형식으로 열의 이름을 입력해주세요.");
                        }
                    }
                    else{
                        for(i = 0 ; i < divided_temp.length ; i++){
                            for( j = 0 ; j < User.row_Name.length ; j++){
                                if(divided_temp[i].equals(User.row_Name[j])){  //일치하는 열을 찾은 경우
                                    view1.row_Num[i] = j;
                                    j = END - 1;    //루프를 종료
                                }
                            }
                            if(j != END){   //일치하는 열이 없는 경우
                                throw new InputMismatchException("일치하는 열이 없습니다.");
                            }
                        }
                    }

                    //조건식부분
                    System.out.print("1. *, 2. 조건식, 3. or연산(||) ");
                    user_choice = sc.nextInt();
                    sc.nextLine();
                    switch(user_choice){
                        case 1 :    //전체를 선택한 경우
                            for(i = 0; i < customers.size(); i++){
                                view1.column_Num.set(i, true);
                            }
                            break;
                        case 2 :    //하나의 조건식만 선택한 경우
                            input_Where(view1, customers);
                            break;
                        case 3 : //두 개의 조건식을 선택한 경우
                                input_Where(view1, customers);
                                input_Where(view1, customers);
                                break;
                        default :   //1~3를 입력하지 않은 경우
                            throw new InputMismatchException("실행하려는 번호를 입력해주세요.");
                    }
                    
                    //조건식을 통해 생성된 view를 통해 지정된 열을 출력하는 부분
                    print_view(view1, customers);

                    //계속 실행할지 여부를 묻는 부분
                    System.out.println("계속 실행하려면 1, 아니면 아무 숫자나 입력해주세요.");
                    user_choice = sc.nextInt();
                    if(user_choice != 1){
                        end_flag = false;
                    }
                    break;
                
                case 2 :    //insert를 선택한 경우
                    Boolean end_trigger = false;
                    i = 0;
                    String temp_Arr[] = new String[User.row_Name.length];   //한 줄로 받은 입력을 공백을 기준으로 나눌 문자열
                    int age_value = 0, grade_value = 0; //숫자로 변환해야하는 값들
                    while(end_trigger != true){
                        try{
                        //사용자에게 입력받는 부분
                          System.out.print(User.row_Name[i] + "값을 입력해주세요 : ");
                          temp_Arr[i] = sc.next();
                        //입력받은 값이 정확한지 검사하는 부분
                        switch(i){
                            case 0 : //name
                                if(temp_Arr[i].length() != 3){
                                    throw new InputMismatchException("이름은 세글자로 입력해주세요.");
                                }
                                break;
                            case 1 : //age
                                try{
                                    age_value = Integer.parseInt(temp_Arr[i]);
                                }
                                catch (NumberFormatException e){
                                    throw new InputMismatchException("숫자값을 넣어주세요.");
                                }
                                if(age_value > 123 || age_value <= 0){
                                    throw new InputMismatchException("0~122사이의 숫자를 입력해주세요.");
                                }
                                break;
                            case 2 : //mvp
                                if(temp_Arr[i].length() != 1){
                                    throw new InputMismatchException("등급은 a~f 로 입력해주세요");
                                }
                                else{
                                    if(temp_Arr[i].compareTo("a") < -1 || temp_Arr[i].compareTo("f") > 1 ){
                                        throw new InputMismatchException("등급은 소문자 a~f의 형태로 입력해주세요.");
                                    }
                                }
                                break;
                            case 3 : //grade
                                try{
                                    grade_value = Integer.parseInt(temp_Arr[i]);
                                }
                                catch (NumberFormatException e){
                                    throw new InputMismatchException("숫자값을 넣어주세요.");
                                }
                                if(grade_value> 5 || grade_value <= 0){
                                    throw new InputMismatchException("0~5사이의 학년을 입력해주세요.");
                                }
                                break;
                            }  
                        }
                        catch(InputMismatchException e){
                            System.out.println(e.getMessage());
                            i--;
                        }
                        //입력을 마칠때마다 i를 증가시키고, 전부 입력받았다면 루프를 종료
                        i++;
                        if(i == 4)
                            end_trigger = true;
                    }
    		    	 customers.add(new User_info(temp_Arr[0], age_value, temp_Arr[2], grade_value));
                     view1.column_Num.add(false);
                     System.out.println("이름 : " + temp_Arr[0] + " 나이 : " + age_value + " 등급 : " + temp_Arr[2] + " 학년 : " + grade_value + "인 데이터을 삽입했습니다.");
                     break;
                 
                case 3 : //update를 선택한 경우

                    //변수선언 부분
                    int count = 0;
                    String row_name, change_value;
                    int int_value = 0;

                    //set부분
                    System.out.println("바꿀 열 : ");
                    row_name = sc.next();
                    System.out.println("바꿀 값 : ");
                    change_value = sc.next();


                    //where부분
                    for(i = 0 ; i < customers.size() ; i++){    //view초기화
                        view1.column_Num.set(i, false);
                    }
                    System.out.print("1. *, 2. 조건식, 3. or연산(||)");
                    user_choice = sc.nextInt();
                    sc.nextLine();
                    switch(user_choice){
                        case 1 :    //전체를 선택한 경우
                            for(i = 0; i < customers.size(); i++){
                                view1.column_Num.set(i, true);
                            }
                            break;
                        case 2 :    //하나의 조건식만 선택한 경우
                            input_Where(view1, customers);
                            break;
                        case 3 : //두 개의 조건식을 선택한 경우
                            input_Where(view1, customers);
                            input_Where(view1, customers);
                            break;
                        default :   //1~3를 입력하지 않은 경우
                            throw new InputMismatchException("실행하려는 번호를 입력해주세요.");
                    }

                    //변경하는 부분
                    if(row_name == "age" || row_name == "grade"){
                        try{
                            int_value = Integer.parseInt(change_value);
                        }
                        catch (NumberFormatException e){
                            throw new InputMismatchException("나이, 학년에는 숫자값을 넣어주세요.");
                        }
                    }
                    for(i = 0 ; i < customers.size(); i++){    //i는 학생의 수
                        if(view1.column_Num.get(i) == true){
                            count++;
                            switch(row_name){
                                case "age" : 
                                    customers.get(i).set_age(int_value);
                                    break;

                                case "grade" : 
                                    customers.get(i).set_grade(int_value);
                                    break;

                                case "name" : 
                                    customers.get(i).set_name(change_value);
                                    break;

                                case "mvp" :
                                    customers.get(i).set_mvp(change_value);
                                    break;

                                default : 
                                    throw new InputMismatchException("일치하는 열이 없습니다.");
                            }
                        }
                     }
                    System.out.println("총 " + count + "개의 데이터를 수정하였습니다.");
                    break;

                case 4 :    //delete
                    count = 0;
                    //view초기화
                    for(i = 0 ; i < customers.size(); i++){
                        view1.column_Num.set(i, false);
                    }

                    //삭제
                    System.out.print("삭제할 조건에 쓰일 ");
                    input_Where(view1, customers);
                    for(i = customers.size() - 1 ; i >= 0; i--){
                        if(view1.column_Num.get(i) == true){
                            count++;
                            customers.remove(i);
                            view1.column_Num.remove(i);
                        }
                    }
                    System.out.println("총 " + count + "개의 열을 삭제했습니다.");
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
    

    public void input_Where(view view1, ArrayList<User_info> customers){    //조건식을 입력받고 view를 수정하는 where_calc함수를 호출하는 메소드
        int m = 0;
        System.out.println("조건식을 입력해주세요(기준열 연산자 값 의 순서)");
        String temp = sc.nextLine();
        String[] divided_temp2 = temp.split(" ");
        for(m = 0 ; m < User.row_Name.length ; m++ ){   //2번 인덱스에 들어온 문자열과 일치하는 문자열을 찾고 값을 넣음
            if(divided_temp2[0].equals(User.row_Name[m])){  //입력받은 문자열과 열의 이름이 일치할 경우
                if(User.row_Name[m] == "grade" || User.row_Name[m] == "age"){   //정수형 value를 필요로 하는 경우
                    int int_value = 0;
                    try{
                        int_value = Integer.parseInt(divided_temp2[2]);
                    }
                    catch (NumberFormatException e){
                        System.out.println("학년(grade)과 나이(age)는 정수로만 비교할 수 있습니다.");
                        break;
                    }
                    view1.where_calc(divided_temp2[0], divided_temp2[1], int_value, customers);  
                }
                else if (User.row_Name[m] == "name" || User.row_Name[m] == "mvp"){  //문자형 value를 필요로 하는 경우
                    view1.where_calc(divided_temp2[0], divided_temp2[1], divided_temp2[2], customers);
                }
                m = END - 1;    //일치하는 열을 찾은 경우 루프를 탈출
            }
        }
        if(m != END){   //if문에 걸리지 않고 m의 증가만으로 루프를 탈출한 경우
            throw new InputMismatchException("일치하는 열이 없습니다.");
        }
    }

    public void print_view(view view1, ArrayList<User_info> customers){ //view에 따라 리스트를 출력하는 메소드
        int i, j;
        int count = 0;
         for(i = 0 ; i < view1.row_Num.length ; i++){
            if(view1.row_Num[i] != END){
                System.out.print("  " + User.row_Name[view1.row_Num[i]]);
            }
         }
         System.out.println();
         for(i = 0 ; i < customers.size(); i++){    //i는 학생의 수
            if(view1.column_Num.get(i) == true){    //ivew의 column_num이 true라면 출력
                count++;
                //view의 row_num이 0인지 아닌지에 따라 열을 출력
                for(j = 0 ; j < view1.row_Num.length ; j++){    // j는 열의 번호
                    switch(view1.row_Num[j]){
                        case 0 : 
                            System.out.print(" " + customers.get(i).get_name());
                            break;
                        case 1 : 
                            System.out.print(" " + customers.get(i).get_age());
                            break;
                        case 2 : 
                            System.out.print(" " + customers.get(i).get_mvp());
                            break;
                        case 3 : 
                            System.out.print(" " + customers.get(i).get_grade());
                            break;
                        default : 
                            break;
                    }
                }
                System.out.println();
            }
         }
         System.out.println("검색된 데이터의 개수는 총 " + count + "개 입니다.");
    }

    public void print_menu() {
        System.out.println("------------------------------------------------------------------");
        System.out.println("1. SELECT   |   2. INSERT   |   3. UPDATE   |   4. DELETE   |   5. END");
        System.out.println("------------------------------------------------------------------");
        System.out.print("실행할 번호를 입력하세요 : ");		
    }

    static Scanner sc = new Scanner(System.in);

    public static void main(String args[]){
        try{
            console thread1 = new console();
        }
    	catch(InputMismatchException e){
            System.out.println(e.getMessage());
        }
        catch(ArrayIndexOutOfBoundsException e){
            e.getStackTrace();
        }
    }
}
