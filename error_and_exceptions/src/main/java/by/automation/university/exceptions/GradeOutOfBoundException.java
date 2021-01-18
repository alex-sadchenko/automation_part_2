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

    public static GradeOutOfBoundException forInputNumber(int number) {
        return new GradeOutOfBoundException("Grade should be from 0 to 10: \"" + number + "\"");
    }
}
