# Thread

#### 1. 아래 항목을 구현하세요.
- number 변수를 1 증가시키는 메소드를 만들고 동기화 처리하세요.
- Thread 10개를 만들고 쓰레드가 위에서 만든 메소드를 실행하세요.
```java
public class Practice01 {
    public static int number = 0;

    public static void main(String[] args) throws Exception {
        // TODO

        Thread.sleep(2000);
        System.out.println("number: " + number);
    }
}
```

#### 2. 1번에서 Thread 대신 ExecutorService 를 사용해 구현하세요.
```java
public class Practice02 {
    public static int number = 0;

    public static void main(String[] args) throws Exception {
        // TODO

        Thread.sleep(2000);
        System.out.println("number: " + number);
    }
}
```
