# Lambda

#### 1. 다음 코드를 람다식과 Function interface 를 사용하도록 변경하세요.
```java
public class Practice01 {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);

        // TODO
        List<String> resultList = map(list, new MyMapper() {
            @Override
            public String apply(int number) {
                return "[" + number + "]";
            }
        });

        System.out.println("======= Result =======");
        for (String value : resultList) {
            System.out.print(value + ", ");
        }
    }

    public static List<String> map(List<Integer> list, MyMapper mapper) {
        List<String> result = new ArrayList<>();
        for (Integer value : list) {
            String temp = mapper.apply(value);
            result.add(temp);
        }
        return result;
    }
}

@FunctionalInterface
interface MyMapper {
    String apply(int number);
}
```
