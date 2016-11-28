# given-when-then
basic given when then test helper [Java 8]

# use
```
import static com.rabriel.gwt.GivenWhenThen.given;
...
@Test
public void basicFlowTest(){
  given(1)
    .when("multiplying by 2", givenValue -> 2*givenValue)
    .then("value should be even", whenValue -> whenValue%2 == 0);
}
```
# Thanks to
Strategy & Technology (http://www.s-and-t.com/) for allowing the project to start.
