package by.automation.university.exceptions;

public class NoGroupInFacultyException extends Exception {
    public NoGroupInFacultyException() {
        super("No group in faculty");
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

    public static NoGroupInFacultyException forInputString(String number) {
        return new NoGroupInFacultyException("No group \"" + number + "\" in faculty");
    }
}
