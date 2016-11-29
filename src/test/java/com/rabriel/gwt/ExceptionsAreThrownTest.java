package com.rabriel.gwt;

import org.junit.Test;

import java.util.function.Consumer;
import java.util.function.Predicate;

import static junit.framework.TestCase.assertEquals;

import static com.rabriel.gwt.GivenWhenThen.given;

/**
 * Created by Rabriel on 11/28/2016.
 */
public class ExceptionsAreThrownTest {

    public static final String EVERYTHING_WILL_BE_FINE = "everything will be fine";
    public static final String BAD_THINGS_HAPPEN = "bad things happen";

    @Test(expected=RuntimeException.class)
    public void testExceptionIsThrownFromWhen(){
        given(null)
            .when(whenReceivedValue -> {throw new NullPointerException(BAD_THINGS_HAPPEN); });
    }

    @Test
    public void testExceptionMessageFromWhen(){
        try{
            given(null)
                    .when(whenReceivedValue -> {throw new NullPointerException(BAD_THINGS_HAPPEN); });
        } catch (RuntimeException ex){
            assertEquals("Exception message should match.",GivenWhenThen.WHEN_FUNCTION_FAILED, ex.getMessage());
        }

        try{
            given(null)
                    .when(EVERYTHING_WILL_BE_FINE, whenReceivedValue -> {throw new NullPointerException("bad things happen"); });
        } catch (RuntimeException ex){
            assertEquals("Exception message should match.",EVERYTHING_WILL_BE_FINE + GivenWhenThen.FAILED, ex.getMessage());
        }

    }

    @Test
    public void testExceptionMessageFromThen(){
        try{
            given(null)
                    .when(value -> null)
                    .then((Consumer<Object>) whenReceivedValue -> { throw new NullPointerException(BAD_THINGS_HAPPEN); });

        } catch (RuntimeException ex){
            assertEquals("Exception message should match.",GivenWhenThen.THEN_FUNCTION_FAILED, ex.getMessage());
        }

        try{
            given(null)
                    .when(value -> null)
                    .then(EVERYTHING_WILL_BE_FINE, (Consumer<Object>) whenReceivedValue -> { throw new NullPointerException(BAD_THINGS_HAPPEN); });
        } catch (RuntimeException ex){
            assertEquals("Exception message should match.",EVERYTHING_WILL_BE_FINE + GivenWhenThen.FAILED, ex.getMessage());
        }

        try{
            given(null)
                    .when(value -> null)
                    .then((Predicate<Object>) whenReceivedValue -> { throw new NullPointerException(BAD_THINGS_HAPPEN); });

        } catch (RuntimeException ex){
            assertEquals("Exception message should match.",BAD_THINGS_HAPPEN, ex.getMessage());
        }

        try{
            given(null)
                    .when(value -> null)
                    .then(whenReceivedValue -> false);
        } catch (RuntimeException ex){
            assertEquals("Exception message should match.", GivenWhenThen.THEN_NOT_SATISFIED, ex.getMessage());
        }

    }
}
