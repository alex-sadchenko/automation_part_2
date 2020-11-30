package by.automation.university;

import by.automation.university.exceptions.NoStudentInGroupException;
import by.automation.university.exceptions.StudentHasNoSubjectException;
import by.automation.university.grades.GradePointAverage;
import by.automation.university.subjects.Subject;

import java.util.*;

public class Group implements GradePointAverage {
    private String facultyName;
    private String number;
    private Specialisation specialisation;
    private List<Student> studentList = new ArrayList<>();

    public Group(String number, Specialisation specialisation) {
        this.number = number;
        this.specialisation = specialisation;
    }

    public void addStudent(Student student) {
        if (!studentList.contains(student)) {
            student.setGroupNumber(number);
            student.setFacultyName(facultyName);
            studentList.add(student);
        }
    }

    public void addStudent(List<Student> studentList) {
        for (Student student : studentList) {
            if (student.getSpecialisation().equals(specialisation)) {
                student.setGroupNumber(number);
                student.setFacultyName(facultyName);
                this.studentList.add(student);
            }
        }
    }


    public String getNumber() {
        return number;
    }

    public Specialisation getSpecialisation() {
        return specialisation;
    }

    public List<Student> getStudentList() throws NoStudentInGroupException {
        if (studentList.isEmpty()) {
            throw new NoStudentInGroupException("No students in group");
        } else {
            return studentList;
        }
    }

    public String getFaculty() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    @Override
    public double calculateSubjectAverageGrade(Subject subject) {
        return studentList.stream()
                .mapToDouble(x -> {
                    try {
                        return x.getSubjectGrade(subject);
                    } catch (StudentHasNoSubjectException e) {
                        e.printStackTrace();
                    }
                    return 0;
                })
                .average()
                .orElse(0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return Objects.equals(number, group.number) &&
                Objects.equals(studentList, group.studentList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, studentList);
    }

    @Override
    public String toString() {
        return "Group{" +
                "facultyName='" + facultyName + '\'' +
                ", number='" + number + '\'' +
                ", specialisation=" + specialisation +
                ", studentList=" + studentList.toString() +
                '}';
    }
}
