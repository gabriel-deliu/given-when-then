# given-when-then
basic given when then test helper [Java 8]

# install
Add the following maven dependecy (Sample for version 0.1.2):
```xml
  <dependency>
    <groupId>com.rabriel</groupId>
    <artifactId>given-when-then</artifactId>
    <version>0.1.2</version>
  </dependency>
```

# how to use
```java
import static com.rabriel.gwt.GivenWhenThen.given;
...
@Test
public void basicFlowTest(){
  given(1)
    .when("multiplying by 2", givenValue -> 2*givenValue)
    .then("value should be even", whenValue -> whenValue%2 == 0);
}

@Test
public void basicMultiWhenFlowTest(){
    given(1)
            .when("multiplying by 2", givenValue -> 2*givenValue)
            .when("High squared", givenValue -> 2*givenValue)
            .then("value should be a square", whenValue -> whenValue == Math.pow(Math.sqrt(whenValue),2));
}

@Test
public void multiTypeFlowTest(){
    LocalDateTime localDateTime = LocalDateTime.now();
    DayOfWeek expectedDay = localDateTime.getDayOfWeek();

    given(localDateTime)
        .when("retrieving current day", date -> date.getDayOfWeek())
        .then("days should match", day -> expectedDay == day);
}

@Test
public void assertFlowTest(){
    Integer primeNumber = 17;
    given("a prime number", primeNumber)
        .when("finding dividers naively", number -> IntStream.rangeClosed(1, number)
                .boxed().filter(value -> number%value == 0).collect(Collectors.toList()))
        .then(dividers -> {
            assertEquals("should have two dividers", 2, dividers.size());
            assertEquals("first divider should be 1", 1, (int) dividers.get(0));
            assertEquals(String.format("first divider should be %d", primeNumber), primeNumber, dividers.get(1));
        });
}

@Test
public void basicGivenFutureFlowTest(){
    given(executor.submit(() -> 1))
            .when("multiplying by 2", givenValue -> 2*givenValue)
            .then("value should be even", whenValue -> whenValue%2 == 0);
}
```
# thanks to
Strategy & Technology (http://www.s-and-t.com/) for allowing the project to start.
