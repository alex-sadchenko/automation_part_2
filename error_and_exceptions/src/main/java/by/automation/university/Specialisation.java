package by.automation.university;

import by.automation.university.subjects.*;
import by.automation.university.subjects.Math;

import java.util.Arrays;
import java.util.List;

public enum Specialisation {
    SPECIALISATION_IN_ECONOMICS {
        public List<Subject> addSubject() {
            return Arrays.asList(
                    new English(),
                    new Math(),
                    new MacroeconomicsTheory()
            );
        }
    },
    MAJOR_IN_FINANCIAL_ECONOMICS {
        public List<Subject> addSubject() {
            return Arrays.asList(
                    new English(),
                    new Math(),
                    new Econometrics()
            );
        }
    },
    MASTER_IN_HISTORY {
        public List<Subject> addSubject() {
            return Arrays.asList(
                    new English(),
                    new History(),
                    new EuropeHistory()
            );
        }
    },
    SPECIALISATION_IN_HISTORY {
        public List<Subject> addSubject() {
            return Arrays.asList(
                    new English(),
                    new History(),
                    new WorldHistory()
            );
        }
    };

    public List<Subject> addSubject() {
        return null;
    }
}
