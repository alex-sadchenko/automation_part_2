package by.automation.university;

import by.automation.university.subjects.AcademicPerformance;

import java.util.Arrays;
import java.util.List;

import static by.automation.university.subjects.Subject.*;

public enum Specialisation {
    SPECIALISATION_IN_ECONOMICS {
        public List<AcademicPerformance> addSubject() {
            return Arrays.asList(
                    new AcademicPerformance(ENGLISH),
                    new AcademicPerformance(MACROECONOMICS_THEORY),
                    new AcademicPerformance(MATH)
            );
        }
    },
    MAJOR_IN_FINANCIAL_ECONOMICS {
        public List<AcademicPerformance> addSubject() {
            return Arrays.asList(
                    new AcademicPerformance(ENGLISH),
                    new AcademicPerformance(MATH),
                    new AcademicPerformance(ECONOMETRICS)
            );
        }
    },
    MASTER_IN_HISTORY {
        public List<AcademicPerformance> addSubject() {
            return Arrays.asList(
                    new AcademicPerformance(ENGLISH),
                    new AcademicPerformance(HISTORY),
                    new AcademicPerformance(EUROPE_HISTORY)
            );
        }
    },
    SPECIALISATION_IN_HISTORY {
        public List<AcademicPerformance> addSubject() {
            return Arrays.asList(
                    new AcademicPerformance(ENGLISH),
                    new AcademicPerformance(HISTORY),
                    new AcademicPerformance(WORLD_HISTORY)
            );
        }
    };

    public List<AcademicPerformance> addSubject() {
        return null;
    }
}
