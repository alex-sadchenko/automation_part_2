package by.automation.university.exceptions;

public class NoStudentInGroupException extends Exception {
    public NoStudentInGroupException() {
    }

    public NoStudentInGroupException(String message) {
        super(message);
    }

    public NoStudentInGroupException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoStudentInGroupException(Throwable cause) {
        super(cause);
    }
}
