package by.automation.university.models;

import by.automation.university.Specialisation;
import by.automation.university.exceptions.GradeOutOfBoundException;
import by.automation.university.exceptions.StudentHasNoSubjectException;
import by.automation.university.subjects.AcademicPerformance;
import by.automation.university.subjects.Subject;

import java.util.List;
import java.util.Objects;

public class Student {
    private final int id;
    private static int generator = 1;
    private String groupNumber;
    private String facultyName;
    private final String name;
    private final String surname;
    private final Specialisation specialisation;
    private final List<AcademicPerformance> academicPerformanceList;

    public Student(String name, String surname, Specialisation specialisation) {
        id = generator++;
        this.name = name;
        this.surname = surname;
        this.specialisation = specialisation;
        this.academicPerformanceList = specialisation.addSubject();
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

    public List<AcademicPerformance> getAcademicPerformanceList() throws StudentHasNoSubjectException {
        if (academicPerformanceList.isEmpty())
            throw new StudentHasNoSubjectException("Student has no subjects");
        return academicPerformanceList;
    }

    public Specialisation getSpecialisation() {
        return specialisation;
    }

    public void setGroupNumber(String groupNumber) {
        this.groupNumber = groupNumber;
    }

    public void addSubject(AcademicPerformance subject) {
        this.academicPerformanceList.add(subject);
    }

    public String getFaculty() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public int getSubjectGrade(Subject subject) throws StudentHasNoSubjectException {
        if (academicPerformanceList.isEmpty())
            throw new StudentHasNoSubjectException();
        return academicPerformanceList.stream()
                .filter(x -> x.getSubject().equals(subject))
                .mapToInt(AcademicPerformance::getGrade)
                .findAny()
                .orElse(0);
    }

    public void setSubjectGrade(Subject subject, int grade) throws StudentHasNoSubjectException, GradeOutOfBoundException {
        if (academicPerformanceList.isEmpty())
            throw new StudentHasNoSubjectException();
        for (AcademicPerformance academicPerformance : academicPerformanceList) {
            if (academicPerformance.getSubject().equals(subject)) {
                academicPerformance.setGrade(grade);
            }
        }
    }

    public double calculateAverageGrade() throws StudentHasNoSubjectException {
        if (academicPerformanceList.isEmpty())
            throw new StudentHasNoSubjectException();
        return academicPerformanceList.stream()
                .mapToDouble(AcademicPerformance::getGrade)
                .average()
                .orElse(0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id &&
                Objects.equals(groupNumber, student.groupNumber) &&
                Objects.equals(facultyName, student.facultyName) &&
                Objects.equals(name, student.name) &&
                Objects.equals(surname, student.surname) &&
                specialisation == student.specialisation &&
                Objects.equals(academicPerformanceList, student.academicPerformanceList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, groupNumber, facultyName, name, surname, specialisation, academicPerformanceList);
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
                ", academicPerformanceList=" + academicPerformanceList +
                '}';
    }
}
