package by.automation.university.exceptions;

public class GradeOutOfBoundException extends Exception {
    public GradeOutOfBoundException() {
    }

    public GradeOutOfBoundException(String message) {
        super(message);
    }

    public GradeOutOfBoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public GradeOutOfBoundException(Throwable cause) {
        super(cause);
    }
}
