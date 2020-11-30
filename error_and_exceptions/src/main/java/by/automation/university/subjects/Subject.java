package by.automation.university.subjects;

import by.automation.university.exceptions.GradeOutOfBoundException;

import java.util.Objects;

public abstract class Subject {
    int grade;

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) throws GradeOutOfBoundException {
        if (grade < 0 || grade > 10) {
            throw new GradeOutOfBoundException("Grade should be from 0 to 10");
        } else {
            this.grade = grade;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject subject = (Subject) o;
        return grade == subject.grade;
    }

    @Override
    public int hashCode() {
        return Objects.hash(grade);
    }

    @Override
    public String toString() {
        return "Subject{" +
                "grade=" + grade +
                '}';
    }
}
