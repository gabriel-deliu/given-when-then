package com.rabriel.gwt;

import org.junit.Test;
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
    public void testPassedValueIsMatchedWhenThen(){
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

}
