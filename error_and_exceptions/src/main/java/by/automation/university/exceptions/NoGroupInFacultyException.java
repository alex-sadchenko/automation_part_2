package by.automation.university.exceptions;

public class NoGroupInFacultyException extends Exception {
    public NoGroupInFacultyException() {
    }

    public NoGroupInFacultyException(String message) {
        super(message);
    }

    public NoGroupInFacultyException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoGroupInFacultyException(Throwable cause) {
        super(cause);
    }
}
