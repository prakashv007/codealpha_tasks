import java.util.ArrayList;

public class GradeTrackerApp {

    ArrayList<Student> students = new ArrayList<Student>();

    public void addStudent(Student s) {
        students.add(s);
    }

    public void displaySummaryReport() {
        System.out.println();
        System.out.println("  =========================================================");
        System.out.println("          STUDENT GRADE TRACKER - SUMMARY REPORT");
        System.out.println("  =========================================================");
        System.out.println();

        System.out.println("+------+----------+----------------------+-----------+-------+-----------+----------+");
        System.out.printf("| %-4s | %-8s | %-20s | %-9s | %-5s | %-9s | %-8s |%n",
                "#", "Roll No", "Name", "Average", "Grade", "Highest", "Lowest");
        System.out.println("+------+----------+----------------------+-----------+-------+-----------+----------+");

        if (students.size() == 0) {
            System.out.println("| No students added yet.                                                       |");
        } else {
            for (int i = 0; i < students.size(); i++) {
                Student s = students.get(i);
                System.out.printf("| %-4d | %-8s | %-20s | %-9.2f | %-5s | %-9.2f | %-8.2f |%n",
                        i + 1,
                        s.rollNumber,
                        s.name,
                        s.getAverage(),
                        s.getGrade(),
                        s.getHighest(),
                        s.getLowest());
            }
        }

        System.out.println("+------+----------+----------------------+-----------+-------+-----------+----------+");
        System.out.println();
    }

    public void displayDetailedBreakdown() {
        System.out.println("  ===========================================");
        System.out.println("        DETAILED SUBJECT BREAKDOWN");
        System.out.println("  ===========================================");

        for (int i = 0; i < students.size(); i++) {
            Student s = students.get(i);
            System.out.println();
            System.out.println("  Roll No : " + s.rollNumber + "   Name : " + s.name);
            System.out.println("  +------------------------+---------+");
            System.out.printf("  | %-22s | %-7s |%n", "Subject", "Marks");
            System.out.println("  +------------------------+---------+");

            for (int j = 0; j < s.subjects.size(); j++) {
                System.out.printf("  | %-22s | %5.1f   |%n", s.subjects.get(j), s.marks.get(j));
            }

            System.out.println("  +------------------------+---------+");
            System.out.printf("  | %-22s | %5.2f   |%n", "Average", s.getAverage());
            System.out.printf("  | %-22s | %5.2f   |%n", "Highest", s.getHighest());
            System.out.printf("  | %-22s | %5.2f   |%n", "Lowest", s.getLowest());
            System.out.printf("  | %-22s | %-7s |%n", "Grade", s.getGrade());
            System.out.println("  +------------------------+---------+");
        }

        System.out.println();
        System.out.println("  ===========================================");
        System.out.println();
    }

    public void displayGradeDistribution() {
        int countA = 0;
        int countB = 0;
        int countC = 0;
        int countD = 0;
        int countF = 0;

        for (int i = 0; i < students.size(); i++) {
            String grade = students.get(i).getGrade();
            if (grade.equals("A")) {
                countA++;
            } else if (grade.equals("B")) {
                countB++;
            } else if (grade.equals("C")) {
                countC++;
            } else if (grade.equals("D")) {
                countD++;
            } else {
                countF++;
            }
        }

        System.out.println("  ===========================================");
        System.out.println("        GRADE DISTRIBUTION");
        System.out.println("  ===========================================");
        System.out.println("  Grade A (90 - 100) : " + countA + " student(s)");
        System.out.println("  Grade B (75 -  89) : " + countB + " student(s)");
        System.out.println("  Grade C (60 -  74) : " + countC + " student(s)");
        System.out.println("  Grade D (45 -  59) : " + countD + " student(s)");
        System.out.println("  Grade F ( 0 -  44) : " + countF + " student(s)");
        System.out.println("  ===========================================");
        System.out.println();
    }

    public void searchByRollNumber(String rollNumber) {
        Student found = null;

        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).rollNumber.equalsIgnoreCase(rollNumber)) {
                found = students.get(i);
                break;
            }
        }

        System.out.println();

        if (found == null) {
            System.out.println("  Student with roll number " + rollNumber + " not found.");
            System.out.println("  Please check the roll number and try again.");
            System.out.println();
            return;
        }

        System.out.println("  ===========================================");
        System.out.println("   STUDENT PROFILE");
        System.out.println("  ===========================================");
        System.out.println("  Roll No : " + found.rollNumber);
        System.out.println("  Name    : " + found.name);
        System.out.println();
        System.out.println("  +------------------------+---------+");
        System.out.printf("  | %-22s | %-7s |%n", "Subject", "Marks");
        System.out.println("  +------------------------+---------+");

        for (int j = 0; j < found.subjects.size(); j++) {
            System.out.printf("  | %-22s | %5.1f   |%n", found.subjects.get(j), found.marks.get(j));
        }

        System.out.println("  +------------------------+---------+");
        System.out.printf("  | %-22s | %5.2f   |%n", "Average", found.getAverage());
        System.out.printf("  | %-22s | %5.2f   |%n", "Highest", found.getHighest());
        System.out.printf("  | %-22s | %5.2f   |%n", "Lowest", found.getLowest());
        System.out.printf("  | %-22s | %-7s |%n", "Grade", found.getGrade());
        System.out.println("  +------------------------+---------+");
        System.out.println();
    }
}
