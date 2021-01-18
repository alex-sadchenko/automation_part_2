package by.automation.university.subjects;

import by.automation.university.exceptions.GradeOutOfBoundException;

import java.util.Objects;

public class AcademicPerformance {
    private final Subject subject;
    private int grade;

    public AcademicPerformance(Subject subjects){
        this.subject = subjects;
        grade = 0;
    }

    public Subject getSubject() {
        return subject;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) throws GradeOutOfBoundException {
        if (grade < 0 || grade > 10)
            throw GradeOutOfBoundException.forInputNumber(grade);
        this.grade = grade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AcademicPerformance that = (AcademicPerformance) o;
        return grade == that.grade &&
                subject == that.subject;
    }

    @Override
    public int hashCode() {
        return Objects.hash(subject, grade);
    }

    @Override
    public String toString() {
        return "AcademicPerformance{" +
                "subject=" + subject +
                ", grade=" + grade +
                '}';
    }
}
