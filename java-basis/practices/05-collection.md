# Collection Framework

#### 1. 다음 List 중 "abc" 의 index 를 출력하는 코드를 작성하세요.  
```java
public class Practice01 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("person");
        list.add("dog");
        list.add("cat");
        list.add("abc");
        list.add("god");
        
        // TODO
    }
}
```

#### 2. 다음 String[] 의 값 중 Set 을 이용해 중복 제거한 후 모두 출력하세요.
```java
public class Practice02 {
    public static void main(String[] args) {
        String[] strings = { "a", "b", "c", "d", "b", "a" };
        
        // TODO
    }
}
```

#### 3. 주어진 map 의 key 와 value 를 모두 출력하세요.
```java
public class Practice03 {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("name", "홍길동");
        map.put("age", "30");
        map.put("address", "서울");
        
        // TODO: key=value 형태로 출력
        /*
         * 예)
         * name=홍길동
         * age=30
         * address=서울
         */
    }
}
```
