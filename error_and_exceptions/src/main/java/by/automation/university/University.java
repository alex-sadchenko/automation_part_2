package by.automation.university;

import by.automation.university.exceptions.NoFacultyInUniversityException;
import by.automation.university.exceptions.NoGroupInFacultyException;
import by.automation.university.faculty.Faculty;
import by.automation.university.grades.GradePointAverage;
import by.automation.university.subjects.Subject;

import java.util.List;
import java.util.Objects;

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

public class University implements GradePointAverage {
    private List<? extends Faculty> facultyList;

    public University(List<Faculty> facultyList) {
        this.facultyList = facultyList;
    }

    @Override
    public double calculateSubjectAverageGrade(Subject subject) throws NoFacultyInUniversityException {
        if (facultyList.isEmpty()) {
            throw new NoFacultyInUniversityException("No faculty in university");
        } else {
            return facultyList.stream()
                    .mapToDouble(x -> {
                        try {
                            return x.calculateSubjectAverageGrade(subject);
                        } catch (NoGroupInFacultyException e) {
                            e.printStackTrace();
                        }
                        return 0;
                    })
                    .average()
                    .orElse(0);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        University that = (University) o;
        return Objects.equals(facultyList, that.facultyList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(facultyList);
    }

    @Override
    public String toString() {
        return "University{" +
                "facultyList=" + facultyList.toString() +
                '}';
    }
}
