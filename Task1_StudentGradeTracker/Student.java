import java.util.ArrayList;

public class Student {

    String rollNumber;
    String name;
    ArrayList<String> subjects;
    ArrayList<Double> marks;

    public Student(String rollNumber, String name) {
        this.rollNumber = rollNumber;
        this.name = name;
        subjects = new ArrayList<String>();
        marks = new ArrayList<Double>();
    }

    public void addMark(String subject, double mark) {
        subjects.add(subject);
        marks.add(mark);
    }

    public double getAverage() {
        if (marks.size() == 0) {
            return 0;
        }
        double total = 0;
        for (int i = 0; i < marks.size(); i++) {
            total = total + marks.get(i);
        }
        double average = total / marks.size();
        return average;
    }

    public double getHighest() {
        double highest = marks.get(0);
        for (int i = 1; i < marks.size(); i++) {
            if (marks.get(i) > highest) {
                highest = marks.get(i);
            }
        }
        return highest;
    }

    public double getLowest() {
        double lowest = marks.get(0);
        for (int i = 1; i < marks.size(); i++) {
            if (marks.get(i) < lowest) {
                lowest = marks.get(i);
            }
        }
        return lowest;
    }

    public String getGrade() {
        double avg = getAverage();
        if (avg >= 90) {
            return "A";
        } else if (avg >= 75) {
            return "B";
        } else if (avg >= 60) {
            return "C";
        } else if (avg >= 45) {
            return "D";
        } else {
            return "F";
        }
    }
}
