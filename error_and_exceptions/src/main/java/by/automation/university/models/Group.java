package by.automation.university.models;

import by.automation.university.Specialisation;
import by.automation.university.exceptions.NoStudentInGroupException;
import by.automation.university.exceptions.StudentHasNoSubjectException;
import by.automation.university.subjects.Subject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Group {
    private String facultyName;
    private final String groupNumber;
    private final Specialisation specialisation;
    private final List<Student> studentList = new ArrayList<>();

    public Group(String groupNumber, Specialisation specialisation) {
        this.groupNumber = groupNumber;
        this.specialisation = specialisation;
    }

    public void addStudents(Student student) {
        student.setGroupNumber(groupNumber);
        student.setFacultyName(facultyName);
        studentList.add(student);
    }

    public void addStudents(List<Student> studentList) {
        for (Student student : studentList) {
            if (student.getSpecialisation().equals(specialisation)) {
                student.setGroupNumber(groupNumber);
                student.setFacultyName(facultyName);
                this.studentList.add(student);
            }
        }
    }

    public String getGroupNumber() {
        return groupNumber;
    }

    public Specialisation getSpecialisation() {
        return specialisation;
    }

    public Student getStudent(String name, String surname) throws NoStudentInGroupException {
        if (studentList.isEmpty())
            throw new NoStudentInGroupException();
        return studentList.stream()
                .filter(s -> s.getName().equalsIgnoreCase(name.trim()) && s.getSurname().equalsIgnoreCase(surname.trim()))
                .findAny()
                .orElseThrow(NoStudentInGroupException::new);
    }

    public List<Student> getStudentList() throws NoStudentInGroupException {
        if (studentList.isEmpty())
            throw new NoStudentInGroupException();
        return studentList;
    }

    public String getFaculty() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public double calculateSubjectAverageGrade(Subject subject) {
        return studentList.stream()
                .mapToDouble(x -> {
                    try {
                        return x.getSubjectGrade(subject);
                    } catch (StudentHasNoSubjectException e) {
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
        Group group = (Group) o;
        return Objects.equals(groupNumber, group.groupNumber) &&
                Objects.equals(studentList, group.studentList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupNumber, studentList);
    }

    @Override
    public String toString() {
        return "Group{" +
                "facultyName='" + facultyName + '\'' +
                ", number='" + groupNumber + '\'' +
                ", specialisation=" + specialisation +
                ", studentList=" + studentList +
                '}';
    }
}
