package ch01;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;	//txt파일을 불러올 패키지
import java.io.FileNotFoundException;	//파일을 못 찾는 경우
import java.io.FileReader;	//파일 읽을 거
import java.io.BufferedReader;	//버퍼
import java.io.IOException;	//잘못된 형식의 파일일 경우


public class console{

    

    public static void print_menu() {
        System.out.println("------------------------------------------------------------------");
        System.out.println("1. SELECT   |   2. INSERT   |   3. UPDATE   |   4. DELETE   |   5. END");
        System.out.println("------------------------------------------------------------------");
        System.out.print("실행할 번호를 입력하세요 : ");		
    }

    static Scanner sc = new Scanner(System.in);

    public static void main(String args[]){
    	
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
                    temp1 = Str_temp[3];
                    temp2 = Str_temp[1];
    		    	 customers.add(new User_info(Str_temp[0], Str_temp[2], Integer.parseInt(temp1), Integer.parseInt(temp2)));
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

        //프로그램실행, 5번을 입력받아 end_flag가 flase가 되면 종료
        while(end_flag){    //1. SELECT   |   2. INSERT   |   3. UPDATE   |   4. DELETE   |   5. END 순서
            print_menu();
			user_choice = sc.nextInt();
            sc.nextLine();  //입력버퍼 비우기

            switch(user_choice){
                case 1 :

                    //출력할 열 설정
                    j = 0;
                    System.out.print("출력할 열 혹은 '*'을 입력해주세요(열 이름 : name, mvp, grade, age)");
                    temp = sc.nextLine();
                    String[] divided_temp1 = temp.split(" ");
                    for(int k = 0 ; k < divided_temp1.length ; k++){
                        if(divided_temp1[k] == "*"){
                            for( i = 0 ; i < User.row_Name.length ; i++){
                                view1.row_Num[j] = i;
                            }
                            break;
                        }
                        for( i = 0 ; i < User.row_Name.length ; i++){
                            if(divided_temp1[k].equals(User.row_Name[i])){
                                view1.row_Num[j] = i;
                                j++;
                                i = 50;
                            }
                        }
                    }

                    //행 조건식
                    System.out.print("'*'혹은 조건식을 입력해주세요()");
                    temp = "";
                    temp = sc.nextLine();
                    String[] divided_temp2 = temp.split(" ");
                    switch(divided_temp2.length){
                        case 1:
                            if(divided_temp2[0] == "*"){
                                
                            }
                            break;
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
                    customers.add(new User_info(name, mvp, grade, age));
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
}
