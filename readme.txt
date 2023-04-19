목적 : 자바언어를 활용하여 관리프로그램을 콘솔버전, Swing라이브러리를 활용한 버전을 만든다.
설계 : 고객의 정보를 추가, 삭제, 조회할 수 있는 관리프로그램을 만들고 데이터베이스에서 사용할 수 있는
DDL(Data Definition Language)과 DML(Data Manipulation Language) 중 DML의 select, delete, update,
insert문을 구현한다.추가적으로 사용되는 where, order by 역시 구현한다.

해야될거 : 

1. 입력잘못되면 예외던지고 아니면 입력값리턴하는 객체만들어서 입력관리하기.
2. select, where, order by, delete, update구현
3. 시간나면 main에서 다 처리하지말고 중앙객체만들어서 멀티스레드 구현하기.