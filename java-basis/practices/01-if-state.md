# if - case문 연습문제

### 1. Scanner로 입력받은 값 input이 양수, 음수, 제로 인지 출력
```java
Scanner scanner = new Scanner(System.in);
int input = scanner.nextInt();

// TODO
```
Example
```
// input: 3
양수

// input: -3
음수

// input: 0
제로
```

### 2. Scanner로 입력받은 값 input이 짝수이면 "짝수" 홀수이면 "홀수"를 출력
```java
Scanner scanner = new Scanner(System.in);
int input = scanner.nextInt();

// TODO
```
Example
```
// input: 6
짝수

// input: 3
홀수
```

### 3. Scanner로 입력받은 값 input이 90 이상이면 "A학점", 80 이상이면 "B학점", 70 이상이면 "C학점", 70미만이면 "F학점"을 출력
```java
Scanner scanner = new Scanner(System.in);
int input = scanner.nextInt();

// TODO
```
Example
```
// input: 95
A학점

// input: 73
C학점
```

***

## 숙제

### 1. 주어진 변수 kor, eng, mat 의 평균 값이 95 이상이면 "장학생", 94\~90이면 "A 학점", 89\~80 이면 "B학점", 79\~70 이면 "C학점", 69이하면 "F학점"을 출력
```java
Scanner scanner = new Scanner(System.in);

System.out.print("kor: ");
int kor = scanner.nextInt();
System.out.println();

System.out.print("eng: ");
int eng = scanner.nextInt();
System.out.println();

System.out.print("mat: ");
int mat = scanner.nextInt();
System.out.println();

String result = "";

// TODO

System.out.println("결과: " + result);
```

### 2. 입력받은 String 타입 변수 operator의 연산 기호에 따라 입력받은 num1, num2의 사칙연산 결과를 출력
* operator는 "+", "-", "*", "/" 만 허용된다고 가정
```java
Scanner scanner = new Scanner(System.in);

System.out.print("num1: ");
int num1 = scanner.nextInt();
System.out.println();

System.out.print("num2: ");
int num2 = scanner.nextInt();
System.out.println();

System.out.print("operator: ");
String operator = scanner.next();
System.out.println();

int result = 0;

// TODO

System.out.println("결과: " + result);
```
