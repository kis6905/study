# Stream API

#### 1. stream 을 이용해 코드를 작성하세요.
```java
public class Practice01 {
    public static void main(String[] args) {
        List<Person> list = Arrays.asList(
            new Person("홍길동", 35),
            new Person("주몽", 25),
            new Person("이순신", 55),
            new Person("세종대왕", 40),
            new Person("권율", 45),
            new Person("이지은", 27),
            new Person("손흥민", 30)
        );
        
        // TODO: 40대인 사람들만 추출하세요.
        
        // TODO: 성이 '이' 씨인 사람들만 추출하세요.
        
        // TODO: 20대인 사람들만 추출해 "이름(나이)" 형태의 List<String> 를 만드세요.
        
        // TODO: 30대가 몇 명인지 구하시오. 
        
        // TODO: 나이가 내림차순으로 정렬된 List 를 만드세요.
    }
}
class Person {
    private String name;
    private int age;
    
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    public String getName() {
        return this.name;
    }
    
    public int getAge() {
        return this.age;
    }
}
```

