package by.automation.university.faculty;

import by.automation.university.exceptions.NoGroupInFacultyException;
import by.automation.university.models.Group;
import by.automation.university.subjects.Subject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Faculty {
    public final String facultyName;
    final List<Group> groupList = new ArrayList<>();

    Faculty(String facultyName){
        this.facultyName = facultyName;
    }

    public abstract void addGroup(Group group);

    public List<Group> getGroupList() throws NoGroupInFacultyException {
        if (groupList.isEmpty())
            throw new NoGroupInFacultyException();
        return this.groupList;
    }

    public Group getGroup(String groupNumber) throws NoGroupInFacultyException {
        if (groupList.isEmpty())
            throw new NoGroupInFacultyException();
        return groupList.stream()
                .filter(x -> x.getGroupNumber().equalsIgnoreCase(groupNumber.trim()))
                .findAny()
                .orElseThrow(()-> NoGroupInFacultyException.forInputString(groupNumber));
    }

    public double calculateSubjectAverageGrade(Subject subject) throws NoGroupInFacultyException {
        if (groupList.isEmpty())
            throw new NoGroupInFacultyException();
        return groupList.stream()
                .mapToDouble(x -> x.calculateSubjectAverageGrade(subject))
                .average()
                .orElse(0);
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
                "groupList=" + groupList +
                '}';
    }
}
