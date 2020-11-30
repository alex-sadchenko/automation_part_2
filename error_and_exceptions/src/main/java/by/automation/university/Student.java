package by.automation.university;

import by.automation.university.exceptions.GradeOutOfBoundException;
import by.automation.university.exceptions.StudentHasNoSubjectException;
import by.automation.university.subjects.Subject;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

public class Student {
    private int id;
    private static int countId = 1;
    private String groupNumber;
    private String facultyName;
    private String name;
    private String surname;
    private Specialisation specialisation;
    private List<Subject> subjectList;

    public Student() {
    }

    public Student(String name, String surname, @NotNull Specialisation specialisation) {
        id = countId++;
        this.name = name;
        this.surname = surname;
        this.specialisation = specialisation;
        this.subjectList = specialisation.addSubject();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public List<Subject> getSubjectList() throws StudentHasNoSubjectException {
        if (subjectList.isEmpty()) {
            throw new StudentHasNoSubjectException("Student has no subjects");
        } else {
            return subjectList;
        }
    }

    public Specialisation getSpecialisation() {
        return specialisation;
    }

    public void setGroupNumber(String groupNumber) {
        this.groupNumber = groupNumber;
    }

    public void addSubject(Subject subject) {
        this.subjectList.add(subject);
    }

    public String getFaculty() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public int getSubjectGrade(Subject subject) throws StudentHasNoSubjectException {
        if (subjectList.isEmpty()) {
            throw new StudentHasNoSubjectException("Student has no subjects");
        } else {
            return subjectList.stream()
                    .filter(x -> x.getClass().equals(subject.getClass()))
                    .mapToInt(Subject::getGrade)
                    .findAny()
                    .orElse(0);
        }
    }

    public void setSubjectGrade(Subject subject, int grade) throws StudentHasNoSubjectException, GradeOutOfBoundException {
        if (subjectList.isEmpty()) {
            throw new StudentHasNoSubjectException("Student has no subjects");
        } else {
            for (Subject subjectFromList : subjectList) {
                if (subjectFromList.getClass().equals(subject.getClass())) {
                    subjectFromList.setGrade(grade);
                }
            }
        }
    }

    public double calculateAverageGrade() throws StudentHasNoSubjectException {
        if (subjectList.isEmpty()) {
            throw new StudentHasNoSubjectException("Student has no subjects");
        } else {
            return subjectList.stream()
                    .mapToDouble(Subject::getGrade)
                    .average()
                    .orElse(0);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id &&
                Objects.equals(name, student.name) &&
                Objects.equals(surname, student.surname) &&
                Objects.equals(subjectList, student.subjectList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, subjectList);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", groupNumber='" + groupNumber + '\'' +
                ", facultyName='" + facultyName + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", specialisation=" + specialisation +
                ", subjectList=" + subjectList.toString() +
                '}';
    }
}
