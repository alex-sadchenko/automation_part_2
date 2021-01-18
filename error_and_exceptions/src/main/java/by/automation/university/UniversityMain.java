package by.automation.university;

import by.automation.university.exceptions.*;
import by.automation.university.faculty.EconomicsFaculty;
import by.automation.university.faculty.HistoricFaculty;
import by.automation.university.models.Group;
import by.automation.university.models.Student;
import by.automation.university.models.University;

import java.util.Arrays;
import java.util.List;

import static by.automation.university.subjects.Subject.*;

//В университете есть несколько факультетов, в которых учатся студенты, объединенные в группы. У каждого
//студента есть несколько учебных предметов по которым он получает оценки. Необходимо реализовать иерархию
//студентов, групп и факультетов.
//
//Посчитать средний балл по всем предметам студента
//Посчитать средний балл по конкретному предмету в конкретной группе и на конкретном факультете
//Посчитать средний балл по предмету для всего университета
//Релизовать следующие исключения:
//
//Оценка ниже 0 или выше 10
//Отсутсвие предметов у студента (должен быть хотя бы один)
//Отсутствие студентов в группе
//Отсутствие групп на факультете
//Отсутствие факультетов в университете

public class UniversityMain {
    public static void main(String[] args) {
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

        University university = new University();
        university.addFaculty(new EconomicsFaculty());
        university.addFaculty(new HistoricFaculty());

        try {
            university.getFaculty("Economics").addGroup(new Group("101", Specialisation.SPECIALISATION_IN_ECONOMICS));
            university.getFaculty("Economics").addGroup(new Group("102", Specialisation.MAJOR_IN_FINANCIAL_ECONOMICS));
            university.getFaculty("Historic").addGroup(new Group("103", Specialisation.SPECIALISATION_IN_HISTORY));
            university.getFaculty("Historic").addGroup(new Group("104", Specialisation.MASTER_IN_HISTORY));

            university.getFaculty("Economics").getGroup("101").addStudents(students);
            university.getFaculty("Economics").getGroup("102").addStudents(students);
            university.getFaculty("Historic").getGroup("103").addStudents(students);
            university.getFaculty("Historic").getGroup("104").addStudents(students);

            university.getFaculty("Economics").getGroup("101")
                    .getStudent("Ben", "Stiller").setSubjectGrade(MATH, 5);
            university.getFaculty("Economics").getGroup("101")
                    .getStudent("Ben", "Stiller").setSubjectGrade(ENGLISH, 6);
            university.getFaculty("Economics").getGroup("101")
                    .getStudent("Ben", "Stiller").setSubjectGrade(MACROECONOMICS_THEORY, 7);
            university.getFaculty("Economics").getGroup("101")
                    .getStudent("Alex", "Morales").setSubjectGrade(MATH, 9);
            university.getFaculty("Economics").getGroup("101")
                    .getStudent("Alex", "Morales").setSubjectGrade(ENGLISH, 8);
            university.getFaculty("Economics").getGroup("101")
                    .getStudent("Alex", "Morales").setSubjectGrade(MACROECONOMICS_THEORY, 9);
            university.getFaculty("Economics").getGroup("102")
                    .getStudent("John", "Smith").setSubjectGrade(MATH, 5);
            university.getFaculty("Economics").getGroup("102")
                    .getStudent("John", "Smith").setSubjectGrade(ENGLISH, 9);
            university.getFaculty("Economics").getGroup("102")
                    .getStudent("John", "Smith").setSubjectGrade(ECONOMETRICS, 7);
            university.getFaculty("Economics").getGroup("102")
                    .getStudent("Jane", "Smith").setSubjectGrade(MATH, 5);
            university.getFaculty("Economics").getGroup("102")
                    .getStudent("Jane", "Smith").setSubjectGrade(ENGLISH, 9);
            university.getFaculty("Economics").getGroup("102")
                    .getStudent("Jane", "Smith").setSubjectGrade(ECONOMETRICS, 7);
            university.getFaculty("Historic").getGroup("103")
                    .getStudent("Sarah", "Connor").setSubjectGrade(HISTORY, 5);
            university.getFaculty("Historic").getGroup("103")
                    .getStudent("Sarah", "Connor").setSubjectGrade(ENGLISH, 9);
            university.getFaculty("Historic").getGroup("103")
                    .getStudent("Sarah", "Connor").setSubjectGrade(WORLD_HISTORY, 7);
            university.getFaculty("Historic").getGroup("103")
                    .getStudent("Mary", "Anderson").setSubjectGrade(HISTORY, 5);
            university.getFaculty("Historic").getGroup("103")
                    .getStudent("Mary", "Anderson").setSubjectGrade(ENGLISH, 9);
            university.getFaculty("Historic").getGroup("103")
                    .getStudent("Mary", "Anderson").setSubjectGrade(WORLD_HISTORY, 7);
            university.getFaculty("Historic").getGroup("104")
                    .getStudent("Bob", "Boyd").setSubjectGrade(HISTORY, 7);
            university.getFaculty("Historic").getGroup("104")
                    .getStudent("Bob", "Boyd").setSubjectGrade(ENGLISH, 9);
            university.getFaculty("Historic").getGroup("104")
                    .getStudent("Bob", "Boyd").setSubjectGrade(EUROPE_HISTORY, 7);
            university.getFaculty("Historic").getGroup("104")
                    .getStudent("Kristen", "Reed").setSubjectGrade(HISTORY, 5);
            university.getFaculty("Historic").getGroup("104")
                    .getStudent("Kristen", "Reed").setSubjectGrade(ENGLISH, 9);
            university.getFaculty("Historic").getGroup("104")
                    .getStudent("Kristen", "Reed").setSubjectGrade(EUROPE_HISTORY, 7);

            System.out.println(university.getFaculty("Economics"));
            System.out.println(university.getFaculty("Historic"));

            System.out.println("Average Math grade in Economic faculty is " +
                    university.getFaculty("Economics").calculateSubjectAverageGrade(MATH));
            System.out.println("Average History grade in Historic faculty is " +
                    university.getFaculty("Historic").calculateSubjectAverageGrade(HISTORY));

            System.out.println("Average English grade in University is " +
                    university.calculateSubjectAverageGrade(ENGLISH));
        } catch (NoStudentInGroupException
                | StudentHasNoSubjectException
                | GradeOutOfBoundException
                | NoFacultyInUniversityException
                | NoGroupInFacultyException e) {
            e.printStackTrace();
        }
    }
}
