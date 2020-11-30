package by.automation.university.exceptions;

public class StudentHasNoSubjectException extends Exception {
    public StudentHasNoSubjectException() {
    }

    public StudentHasNoSubjectException(String message) {
        super(message);
    }

    public StudentHasNoSubjectException(String message, Throwable cause) {
        super(message, cause);
    }

    public StudentHasNoSubjectException(Throwable cause) {
        super(cause);
    }
}
