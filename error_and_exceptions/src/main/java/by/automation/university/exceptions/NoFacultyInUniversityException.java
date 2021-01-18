package by.automation.university.exceptions;

public class NoFacultyInUniversityException extends Exception {
    public NoFacultyInUniversityException() {
        super("No faculty in university");
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

    public static NoFacultyInUniversityException forInputString(String facultyName) {
        return new NoFacultyInUniversityException("No faculty \"" + facultyName + "\" in university");
    }
}
