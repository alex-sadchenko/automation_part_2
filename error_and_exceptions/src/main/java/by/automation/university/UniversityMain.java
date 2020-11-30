package by.automation.university;

import by.automation.university.exceptions.*;
import by.automation.university.faculty.EconomicsFaculty;
import by.automation.university.faculty.HistoricFaculty;
import by.automation.university.subjects.Math;
import by.automation.university.subjects.*;

import java.util.Arrays;
import java.util.List;

public class UniversityMain {
    public static void main(String[] args) {
        List<Group> groupList = Arrays.asList(
                new Group("101", Specialisation.SPECIALISATION_IN_ECONOMICS),
                new Group("102", Specialisation.MAJOR_IN_FINANCIAL_ECONOMICS),
                new Group("103", Specialisation.SPECIALISATION_IN_HISTORY),
                new Group("104", Specialisation.MASTER_IN_HISTORY)
        );

        List<Student> students = Arrays.asList(
                new Student("Ben", "Stiller", Specialisation.SPECIALISATION_IN_ECONOMICS),
                new Student("Alex", "Morales", Specialisation.SPECIALISATION_IN_ECONOMICS),
                new Student("John", "Smith", Specialisation.MAJOR_IN_FINANCIAL_ECONOMICS),
                new Student("Jane", "Smith", Specialisation.MAJOR_IN_FINANCIAL_ECONOMICS),
                new Student("Sarah", "Connor", Specialisation.SPECIALISATION_IN_HISTORY),
                new Student("Mary", "Anderson", Specialisation.SPECIALISATION_IN_HISTORY),
                new Student("Bob", "Boyd", Specialisation.MASTER_IN_HISTORY),
                new Student("Kristen", "Reed", Specialisation.MASTER_IN_HISTORY)
        );

        EconomicsFaculty economicsFaculty = new EconomicsFaculty();
        HistoricFaculty historicFaculty = new HistoricFaculty();
        economicsFaculty.addGroup(groupList);
        historicFaculty.addGroup(groupList);

        groupList.get(0).addStudent(students);
        groupList.get(1).addStudent(students);
        groupList.get(2).addStudent(students);
        groupList.get(3).addStudent(students);

        try {
            groupList.get(0).getStudentList().get(0).setSubjectGrade(new Math(), 6);
            groupList.get(0).getStudentList().get(0).setSubjectGrade(new English(), 6);
            groupList.get(0).getStudentList().get(0).setSubjectGrade(new MacroeconomicsTheory(), 7);
            groupList.get(0).getStudentList().get(1).setSubjectGrade(new Math(), 7);
            groupList.get(0).getStudentList().get(1).setSubjectGrade(new English(), 8);
            groupList.get(0).getStudentList().get(1).setSubjectGrade(new MacroeconomicsTheory(), 9);
            groupList.get(1).getStudentList().get(0).setSubjectGrade(new Math(), 5);
            groupList.get(1).getStudentList().get(0).setSubjectGrade(new English(), 9);
            groupList.get(1).getStudentList().get(0).setSubjectGrade(new Econometrics(), 7);
            groupList.get(1).getStudentList().get(1).setSubjectGrade(new Math(), 5);
            groupList.get(1).getStudentList().get(1).setSubjectGrade(new English(), 9);
            groupList.get(1).getStudentList().get(1).setSubjectGrade(new Econometrics(), 7);
            groupList.get(2).getStudentList().get(0).setSubjectGrade(new History(), 5);
            groupList.get(2).getStudentList().get(0).setSubjectGrade(new English(), 9);
            groupList.get(2).getStudentList().get(0).setSubjectGrade(new WorldHistory(), 7);
            groupList.get(2).getStudentList().get(1).setSubjectGrade(new History(), 5);
            groupList.get(2).getStudentList().get(1).setSubjectGrade(new English(), 9);
            groupList.get(2).getStudentList().get(1).setSubjectGrade(new WorldHistory(), 7);
            groupList.get(3).getStudentList().get(0).setSubjectGrade(new History(), 7);
            groupList.get(3).getStudentList().get(0).setSubjectGrade(new English(), 9);
            groupList.get(3).getStudentList().get(0).setSubjectGrade(new EuropeHistory(), 7);
            groupList.get(3).getStudentList().get(1).setSubjectGrade(new History(), 5);
            groupList.get(3).getStudentList().get(1).setSubjectGrade(new English(), 9);
            groupList.get(3).getStudentList().get(1).setSubjectGrade(new EuropeHistory(), 7);
        } catch (NoStudentInGroupException | StudentHasNoSubjectException | GradeOutOfBoundException e) {
            e.printStackTrace();
        }

        System.out.println(economicsFaculty.toString());
        System.out.println(historicFaculty.toString());

        try {
            System.out.println("Average Math grade in Economic faculty is " +
                    economicsFaculty.calculateSubjectAverageGrade(new Math()));
            System.out.println("Average History grade in Historic faculty is " +
                    historicFaculty.calculateSubjectAverageGrade(new History()));
        } catch (NoGroupInFacultyException e) {
            e.printStackTrace();
        }

        University university = new University(Arrays.asList(
                economicsFaculty,
                historicFaculty
        ));

        try {
            System.out.println("Average English grade in University is " +
                    university.calculateSubjectAverageGrade(new English()));
        } catch (NoFacultyInUniversityException e) {
            e.printStackTrace();
        }
    }
}
