# given-when-then
basic given when then test helper [Java 8]

# how to use
```
import static com.rabriel.gwt.GivenWhenThen.given;
...
@Test
public void basicFlowTest(){
  given(1)
    .when("multiplying by 2", givenValue -> 2*givenValue)
    .then("value should be even", whenValue -> whenValue%2 == 0);
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
    given(primeNumber)
        .when("finding dividers naively", number -> IntStream.rangeClosed(1, number)
                .boxed().filter(value -> number%value == 0).collect(Collectors.toList()))
        .then("days should match", dividers -> {
            assertEquals("should have two dividers", 2, dividers.size());
            assertEquals("first divider should be 1", 1, (int) dividers.get(0));
            assertEquals(String.format("first divider should be %d", primeNumber), primeNumber, dividers.get(1));
        });
}
```
# thanks to
Strategy & Technology (http://www.s-and-t.com/) for allowing the project to start.
