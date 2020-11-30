package by.automation.university.exceptions;

public class NoFacultyInUniversityException extends Exception {
    public NoFacultyInUniversityException() {
    }

    public NoFacultyInUniversityException(String message) {
        super(message);
    }

    public NoFacultyInUniversityException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoFacultyInUniversityException(Throwable cause) {
        super(cause);
    }
}
