package by.automation.university.faculty;

import by.automation.university.exceptions.NoGroupInFacultyException;
import by.automation.university.models.Group;
import by.automation.university.subjects.Subject;

public class HistoricFaculty extends Faculty {
    public HistoricFaculty() {
        super("Historic");
    }

    public void addGroup(Group group) {
        group.setFacultyName(facultyName);
        super.groupList.add(group);
    }

    @Override
    public double calculateSubjectAverageGrade(Subject subject) throws NoGroupInFacultyException {
        return super.calculateSubjectAverageGrade(subject);
    }

    @Override
    public String toString() {
        return "HistoricFaculty{" +
                "groupList=" + groupList +
                '}';
    }
}
