package com.rabriel;

import io.vavr.control.Try;

import java.util.function.Consumer;

import static com.rabriel.GivenWhenThen.THEN_FUNCTION_FAILED;

public final class WhenTrying<T> {
    private static final String CALL_IS_NOT_FAILING = " Call is not failing.";
    private static final String CALL_IS_FAILING = " Call is failing.";

    private final Try<T> failable;

    WhenTrying(final Try<T> failable) {
        this.failable = failable;
    }

    public void thenItThrows(final Consumer<? super Throwable> action) {
        thenItThrows(null, action);
    }

    public void thenItThrows(final String message, final Consumer<? super Throwable> action) {
        if (!failable.isFailure()) {
            throw new RuntimeException(
                    message == null ?
                            String.format("%s%s", THEN_FUNCTION_FAILED, CALL_IS_NOT_FAILING)
                            :
                            message
            );
        }
        failable.onFailure(action);
    }

    public void thenItSucceeds(final Consumer<? super T> action) {
        thenItSucceeds(null, action);
    }

    public void thenItSucceeds(final String message, final Consumer<? super T> action) {
        if (!failable.isSuccess()) {
            throw new RuntimeException(
                    message == null ?
                            String.format("%s%s", THEN_FUNCTION_FAILED, CALL_IS_FAILING)
                            :
                            message
            );
        }
        failable.onSuccess(action);
    }
}
