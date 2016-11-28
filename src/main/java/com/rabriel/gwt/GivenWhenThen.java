package com.rabriel.gwt;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by gabriel on 19/10/2016.
 * Class uses to provide Given When Then test flow methods
 */
public class GivenWhenThen<T> {

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
            throw new RuntimeException(message == null ? "When function failed." : message + " - failed.", ex);
        }
    }

    public void then(Predicate<T> thenFunction){
        then(null, thenFunction);
    }

    public void then(String failMessage, Predicate<T> thenFunction){
        boolean testResult = thenFunction.test(received);
        if(!testResult) {
            throw new RuntimeException(failMessage == null ? "Then not satisfied." : failMessage);
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
            throw new RuntimeException(message == null ? "then function failed." : message + " - failed.", ex);
        }
    }

    public static <E> GivenWhenThen<E> given(E receivedObj){
        return new GivenWhenThen<E>(receivedObj);
    }

}