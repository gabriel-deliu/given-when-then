package com.rabriel;

import org.junit.Test;

import static com.rabriel.GivenWhenThen.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public final class WhenTryingTest {

    //region Systems under tests
    private static final class SutFailing {
        void failingVoidCall() {
            throw new RuntimeException("Failing Void code");
        }

        int failingIntCall() {
            throw new RuntimeException("Failing int code");
        }
    }

    private static final class SutNotFailing {
        void nonFailingVoidCall() {
        }

        int nonFailingIntCall() {
            return 42;
        }
    }
    //endregion

    //region Test thenItThrows
    @Test
    public void givenFailingSut_whenFailingVoidCall_thenItThrows() {
        given(new SutFailing())
                .whenTrying(SutFailing::failingVoidCall)
                .thenItThrows(throwable -> assertThat(throwable)
                        .isInstanceOf(RuntimeException.class)
                        .hasMessage("Failing Void code")
                );
    }

    @Test
    public void givenFailingSut_whenFailingIntCall_thenItThrows() {
        given(new SutFailing())
                .whenTrying(SutFailing::failingIntCall)
                .thenItThrows(throwable -> assertThat(throwable)
                        .isInstanceOf(RuntimeException.class)
                        .hasMessage("Failing int code")
                );
    }

    @Test
    public void givenNotFailingSut_whenNonFailingVoidCall_thenItDoesNotThrow() {
        assertThatThrownBy(
                () ->
                        given(new SutNotFailing())
                                .whenTrying(SutNotFailing::nonFailingVoidCall)
                                .thenItThrows(throwable -> assertThat(throwable)
                                        .isInstanceOf(RuntimeException.class)
                                        .hasMessage("Failing Void code")
                                )
        ).isInstanceOf(RuntimeException.class)
                .hasMessage("Then function failed. Call is not failing.");
    }

    @Test
    public void givenNotFailingSut_whenNonFailingIntCall_thenItDoesNotThrow() {
        assertThatThrownBy(
                () ->
                        given(new SutNotFailing())
                                .whenTrying(SutNotFailing::nonFailingIntCall)
                                .thenItThrows(throwable -> assertThat(throwable)
                                        .isInstanceOf(RuntimeException.class)
                                        .hasMessage("Failing int code")
                                )
        ).isInstanceOf(RuntimeException.class)
                .hasMessage("Then function failed. Call is not failing.");
    }

    @Test
    public void givenNotFailingSut_whenNonFailingVoidCall_thenItDoesNotThrowWithMessage() {
        assertThatThrownBy(
                () ->
                        given(new SutNotFailing())
                                .whenTrying(SutNotFailing::nonFailingVoidCall)
                                .thenItThrows("Code must fail", throwable -> assertThat(throwable)
                                        .isInstanceOf(RuntimeException.class)
                                        .hasMessage("Failing Void code")
                                )
        ).isInstanceOf(RuntimeException.class)
                .hasMessage("Code must fail");
    }

    @Test
    public void givenNotFailingSut_whenNonFailingIntCall_thenItDoesNotThrowWithMessage() {
        assertThatThrownBy(
                () ->
                        given(new SutNotFailing())
                                .whenTrying(SutNotFailing::nonFailingIntCall)
                                .thenItThrows("Code must fail", throwable -> assertThat(throwable)
                                        .isInstanceOf(RuntimeException.class)
                                        .hasMessage("Failing int code")
                                )
        ).isInstanceOf(RuntimeException.class)
                .hasMessage("Code must fail");
    }
    //endregion

    //region Test thenItSucceeds
    @Test
    public void givenFailingSut_whenFailingVoidCall_thenItDoesNotSucceed() {
        assertThatThrownBy(
                () ->
                        given(new SutFailing())
                                .whenTrying(SutFailing::failingVoidCall)
                                .thenItSucceeds(aVoid -> {
                                })
        ).isInstanceOf(RuntimeException.class)
                .hasMessage("Then function failed. Call is failing.");
    }

    @Test
    public void givenFailingSut_whenFailingIntCall_thenItDoesNotSucceed() {
        assertThatThrownBy(
                () ->
                        given(new SutFailing())
                                .whenTrying(SutFailing::failingIntCall)
                                .thenItSucceeds(aInt -> {
                                })
        ).isInstanceOf(RuntimeException.class)
                .hasMessage("Then function failed. Call is failing.");
    }
    @Test
    public void givenFailingSut_whenFailingVoidCall_thenItDoesNotSucceedWithMessage() {
        assertThatThrownBy(
                () ->
                        given(new SutFailing())
                                .whenTrying(SutFailing::failingVoidCall)
                                .thenItSucceeds("Code is failing", aVoid -> {
                                })
        ).isInstanceOf(RuntimeException.class)
                .hasMessage("Code is failing");
    }

    @Test
    public void givenFailingSut_whenFailingIntCall_thenItDoesNotSucceedWithMessage() {
        assertThatThrownBy(
                () ->
                        given(new SutFailing())
                                .whenTrying(SutFailing::failingIntCall)
                                .thenItSucceeds("Code is failing", aInt -> {
                                })
        ).isInstanceOf(RuntimeException.class)
                .hasMessage("Code is failing");
    }

    @Test
    public void givenNotFailingSut_whenNonFailingVoidCall_thenItSucceeds() {
        given(new SutNotFailing())
                .whenTrying(SutNotFailing::nonFailingVoidCall)
                .thenItSucceeds(aVoid -> assertThat(true).isTrue());
    }

    @Test
    public void givenNotFailingSut_whenNonFailingIntCall_thenItSucceeds() {
        given(new SutNotFailing())
                .whenTrying(SutNotFailing::nonFailingIntCall)
                .thenItSucceeds(aInt -> assertThat(aInt).isEqualTo(42));
    }
    //endregion

}