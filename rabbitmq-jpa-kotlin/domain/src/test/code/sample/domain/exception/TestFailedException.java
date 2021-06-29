package sample.domain.exception;

public class TestFailedException extends RuntimeException {
    TestFailedException(String message) {
        super(message);
    }

    TestFailedException(String message, Throwable t) {
        super(message, t);
    }
}
