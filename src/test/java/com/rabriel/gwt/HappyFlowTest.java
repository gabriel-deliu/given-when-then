package com.rabriel.gwt;

import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.rabriel.gwt.GivenWhenThen.given;
import static junit.framework.TestCase.assertEquals;
/**
 * Created by Rabriel on 11/27/2016.
 */
public class HappyFlowTest {

    @Test
    public void testPassedValueisMatchedGivenWhen(){

        int passedIntValue = 22;
        String passedStringValue = "Test Value";

        given(passedIntValue).when("testing passed int value in 'when'",
            receivedValue -> { assertEquals("given passed int value should match when received value",
                passedIntValue, (int) receivedValue); return true; });
        given(passedStringValue).when("testing passed string value in 'when'",
            receivedValue -> { assertEquals("given passed string value should match when received value",
                passedStringValue, receivedValue); return true; });

    }

    @Test
    public void testPassedValueIsMatchedWhenThen() throws ExecutionException, InterruptedException {
        String whenValue = "Test Value";

        given(null)
            .when(whenReceivedValue -> whenValue)
            .then("testing passed string value in 'then'",thenReceivedValue ->
            { assertEquals("when passed value should match then received value",whenValue, thenReceivedValue); });

    }

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
            .when("retrieving current day", LocalDateTime::getDayOfWeek)
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


}
