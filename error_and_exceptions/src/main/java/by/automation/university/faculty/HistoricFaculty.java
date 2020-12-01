package by.automation.university.faculty;

import by.automation.university.Specialisation;
import by.automation.university.Group;
import by.automation.university.exceptions.NoGroupInFacultyException;
import by.automation.university.subjects.Subject;

import java.util.*;

public class HistoricFaculty extends Faculty {
    private static List<Specialisation> specialisationList = Arrays.asList(
            Specialisation.SPECIALISATION_IN_HISTORY,
            Specialisation.MASTER_IN_HISTORY
    );

    public HistoricFaculty() {
    }

    public void addGroup(List<Group> groupList) {
        for (Group group : groupList) {
            for (Specialisation specialisation : specialisationList) {
                if (group.getSpecialisation().equals(specialisation)) {
                    group.setFacultyName("History");
                    super.groupList.add(group);
                }
            }
        }
    }

    @Override
    public double calculateSubjectAverageGrade(Subject subject) throws NoGroupInFacultyException {
        return super.calculateSubjectAverageGrade(subject);
    }

    @Override
    public String toString() {
        return "HistoricFaculty{" +
                "groupList=" + groupList.toString() +
                '}';
    }
}
