package com.rabriel.gwt;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by gabriel on 19/10/2016.
 * Class uses to provide Given When Then test flow methods
 */
public class GivenWhenThen<T> {

    public static final String WHEN_FUNCTION_FAILED = "When function failed.";
    public static final String FAILED = " - failed";
    public static final String THEN_NOT_SATISFIED = "Then not satisfied.";
    public static final String THEN_FUNCTION_FAILED = "Then function failed.";
    private T received;

    private GivenWhenThen(T received){
        this.received = received;
    }

    public <F> GivenWhenThen<F> when(Function<T,F> whenFunction){
        return when(null, whenFunction);
    }

    public <F> GivenWhenThen<F> when(String message, Function<T,F> whenFunction){

        try {
            return new GivenWhenThen<F>(whenFunction.apply(received));
        }
        catch (Exception ex){
            throw new RuntimeException(message == null ? WHEN_FUNCTION_FAILED : message + FAILED, ex);
        }
    }

    public void then(Predicate<T> thenFunction){
        then(null, thenFunction);
    }

    public void then(String failMessage, Predicate<T> thenFunction){
        boolean testResult = thenFunction.test(received);
        if(!testResult) {
            throw new RuntimeException(failMessage == null ? THEN_NOT_SATISFIED : failMessage);
        }
    }

    public void then(Consumer<T> thenFunction){
        then(null, thenFunction);
    }

    public void then(String message, Consumer<T> thenFunction){
        try {
            thenFunction.accept(received);
        }
        catch (Exception ex){
            throw new RuntimeException(message == null ? THEN_FUNCTION_FAILED : message + FAILED, ex);
        }
    }

    public static <E> GivenWhenThen<E> given(E receivedObj){
        return given(null, receivedObj);
    }

    public static <E> GivenWhenThen<E> given(String message, E receivedObj){
        return new GivenWhenThen<E>(receivedObj);
    }

}