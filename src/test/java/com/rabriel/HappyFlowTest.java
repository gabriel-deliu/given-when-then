package com.rabriel;

import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.rabriel.GivenWhenThen.given;
import static junit.framework.TestCase.assertEquals;
/**
 * Created by Rabriel on 11/27/2016.
 */
public class HappyFlowTest {

    @Test
    public void testPassedValueisMatchedGivenWhen(){

        int passedIntValue = 22;
        String passedStringValue = "Test Value";

        GivenWhenThen.given(passedIntValue).when("testing passed int value in 'when'",
            receivedValue -> { assertEquals("given passed int value should match when received value",
                passedIntValue, (int) receivedValue); return true; });
        GivenWhenThen.given(passedStringValue).when("testing passed string value in 'when'",
            receivedValue -> { assertEquals("given passed string value should match when received value",
                passedStringValue, receivedValue); return true; });

    }

    @Test
    public void testPassedValueIsMatchedWhenThen() throws ExecutionException, InterruptedException {
        String whenValue = "Test Value";

        GivenWhenThen.given(null)
            .when(whenReceivedValue -> whenValue)
            .then("testing passed string value in 'thenItThrows'",thenReceivedValue ->
            { assertEquals("when passed value should match thenItThrows received value",whenValue, thenReceivedValue); });

    }

    @Test
    public void basicFlowTest(){
        GivenWhenThen.given(1)
            .when("multiplying by 2", givenValue -> 2*givenValue)
            .then("value should be even", whenValue -> whenValue%2 == 0);
    }

    @Test
    public void basicMultiWhenFlowTest(){
        GivenWhenThen.given(1)
                .when("multiplying by 2", givenValue -> 2*givenValue)
                .when("High squared", givenValue -> 2*givenValue)
                .then("value should be a square", whenValue -> whenValue == Math.pow(Math.sqrt(whenValue),2));
    }

    @Test
    public void multiTypeFlowTest(){
        LocalDateTime localDateTime = LocalDateTime.now();
        DayOfWeek expectedDay = localDateTime.getDayOfWeek();

        GivenWhenThen.given(localDateTime)
            .when("retrieving current day", LocalDateTime::getDayOfWeek)
            .then("days should match", day -> expectedDay == day);
    }
    @Test
    public void assertFlowTest(){
        Integer primeNumber = 17;
        GivenWhenThen.given("a prime number", primeNumber)
            .when("finding dividers naively", number -> IntStream.rangeClosed(1, number)
                    .boxed().filter(value -> number%value == 0).collect(Collectors.toList()))
            .then(dividers -> {
                assertEquals("should have two dividers", 2, dividers.size());
                assertEquals("first divider should be 1", 1, (int) dividers.get(0));
                assertEquals(String.format("first divider should be %d", primeNumber), primeNumber, dividers.get(1));
            });
    }


}
