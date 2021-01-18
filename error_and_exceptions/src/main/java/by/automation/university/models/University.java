package by.automation.university.models;

import by.automation.university.exceptions.NoFacultyInUniversityException;
import by.automation.university.exceptions.NoGroupInFacultyException;
import by.automation.university.faculty.Faculty;
import by.automation.university.subjects.Subject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class University {
    private final List<Faculty> facultyList = new ArrayList<>();

    public void addFaculty(Faculty faculty) {
        facultyList.add(faculty);
    }

    public Faculty getFaculty(String facultyName) throws NoFacultyInUniversityException {
        if (facultyList.isEmpty())
            throw new NoFacultyInUniversityException();
        return facultyList.stream()
                .filter(x -> x.facultyName.equalsIgnoreCase(facultyName.trim()))
                .findAny()
                .orElseThrow(()-> NoFacultyInUniversityException.forInputString(facultyName));
    }

    public double calculateSubjectAverageGrade(Subject subject) throws NoFacultyInUniversityException {
        if (facultyList.isEmpty())
            throw new NoFacultyInUniversityException();
        return facultyList.stream()
                .mapToDouble(faculty -> {
                    try {
                        return faculty.calculateSubjectAverageGrade(subject);
                    } catch (NoGroupInFacultyException e) {
                        e.printStackTrace();
                        return 0;
                    }
                })
                .average()
                .orElse(0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        University that = (University) o;
        return Objects.equals(facultyList, that.facultyList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(facultyList);
    }

    @Override
    public String toString() {
        return "University{" +
                "facultyList=" + facultyList +
                '}';
    }
}
