import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")
class adminisubject extends JFrame{
	//변수 선언
	
	//constructor
	public adminisubject(){
		//초기설정
		//이름설정
		setTitle("관리 프로그램");
		//종료버튼생성
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//컴포넌트 생성
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		
		//사용할 이미지를 불러들임.
		ImageIcon BonoIcon = new ImageIcon("images/msbonobono.png");
		
		//버튼 생성
		JButton btn_add = new JButton(BonoIcon);
		JButton btn_calc = new JButton(BonoIcon);
		JButton btn_delete = new JButton(BonoIcon);
		JButton btn_show = new JButton(BonoIcon);
		
		//컴포넌트에 버튼 지정
		c.add(btn_add);
		c.add(btn_calc);
		c.add(btn_delete);
		c.add(btn_show);
		
		//컴포넌트 크기설정
		setSize(600,600);
		//가시모드로 바꿈
		setVisible(true);
	}
	
	public static void main(String[] args){
		new adminisubject();
	}   
}

/*
class customer{
	
	//변수 선언
	private int age;
	private char mvp;
	private String phonenumber;
	
	//constructor
	customer(int age, char mvp, String phonenumber){
		age = this.age;
		mvp = this.mvp;
		phonenumber = this.phonenumber;
	}

	void print_mvp()
	
	
}
*/