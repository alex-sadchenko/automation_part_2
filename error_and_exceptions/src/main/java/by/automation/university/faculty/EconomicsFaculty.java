package by.automation.university.faculty;

import by.automation.university.Specialisation;
import by.automation.university.Group;
import by.automation.university.exceptions.NoGroupInFacultyException;
import by.automation.university.subjects.Subject;

import java.util.*;

public class EconomicsFaculty extends Faculty {
    private static List<Specialisation> specialisationList = Arrays.asList(
            Specialisation.SPECIALISATION_IN_ECONOMICS,
            Specialisation.MAJOR_IN_FINANCIAL_ECONOMICS
    );

    public EconomicsFaculty() {
    }

    public void addGroup(List<Group> groupList) {
        for (Group group : groupList) {
            for (Specialisation specialisation : specialisationList) {
                if (group.getSpecialisation().equals(specialisation)) {
                    group.setFacultyName("Economy");
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
        return "EconomicsFaculty{" +
                "groupList=" + groupList.toString() +
                '}';
    }
}
