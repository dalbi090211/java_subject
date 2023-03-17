public class makedata {

    public static void main(String args[]){
        int i, select_num = 50;
        String mvp = "l";
        int age, grade;
        int switch_num;
        double rand_num;
        for(i = 0; i < select_num; i++){
            rand_num = 4 * Math.random();
            switch_num = (int)rand_num;
            switch(switch_num){
                case 1 : 
                    mvp = "b";
                    break;
                case 2 : 
                    mvp = "c";
                    break;
                case 3 : 
                    mvp = "f";
                    break;
                default : //1보다 작은 경우
                    mvp = "a";
            }

            rand_num = 7 * Math.random();
            switch_num = (int)rand_num;
            age = 20 + switch_num;

            rand_num = 4 * Math.random();
            switch_num = (int)rand_num;
            grade = 1 + switch_num;
            System.out.println("김건부 " + age + " " + mvp + " " + grade);
        }
    }
    
}
