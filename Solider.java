import java.util.Scanner;

public class Solider {

    public static void main (String args[]){
      Scanner sc = new Scanner(System.in);
      String[] temp = new String[5];
      int i = 0 ;
      while(sc.hasNext()){
        temp[i] = sc.next();
        System.out.println(temp[i]);
        i++;
      }
      for(i = 0 ; i < temp.length ; i++){
          System.out.println(temp[i]);          
      }
      sc.close();
      
    }
}
