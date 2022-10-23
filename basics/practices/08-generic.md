# Generic

#### 1. map() 이라는 메소드의 Parameter Type 과 Return Type 을 받는 Mapper 란 이름의 Generic class 를 생성하하고 map() 메소드를 작성하세요.    
```java
public class Practice01 {
    public static void main(String[] args) {
        Mapper<Integer, String> mapper = new Mapper<>();
        String result = mapper.map(12345); // result = "12345"
    }
}

// TODO
```

#### 2. Class 를 입력받아 새 인스턴스 객체를 리턴하는 "create()" 라는 이름의 static 메소드를 작성하세요.
- 인스턴스 타입은 Leaf, Doobyeol, Home 만 가능하며, 그 외 값인 경우 IllegalArgumentException 을 발생 시키세요.
```java
public class Practice02 {
    public static void main(String[] args) {
        
    }

    // TODO
}

class Leaf {
    
}

class Doobyeol {
    
}

class Home {
    
}
```
