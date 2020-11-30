package by.automation.university.faculty;

import by.automation.university.Group;
import by.automation.university.exceptions.NoGroupInFacultyException;
import by.automation.university.grades.GradePointAverage;
import by.automation.university.subjects.Subject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Faculty implements GradePointAverage {
    List<Group> groupList = new ArrayList<>();

    public abstract void addGroup(List<Group> groupList);

    public List<Group> getGroupList() throws NoGroupInFacultyException {
        if (groupList.isEmpty()) {
            throw new NoGroupInFacultyException("No group in faculty");
        } else {
            return this.groupList;
        }
    }

    @Override
    public double calculateSubjectAverageGrade(Subject subject) throws NoGroupInFacultyException {
        if (groupList.isEmpty()) {
            throw new NoGroupInFacultyException("No group in faculty");
        } else {
            return groupList.stream()
                    .mapToDouble(x -> x.calculateSubjectAverageGrade(subject))
                    .average()
                    .orElse(0);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Faculty faculty = (Faculty) o;
        return Objects.equals(groupList, faculty.groupList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupList);
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "groupList=" + groupList.toString() +
                '}';
    }
}
