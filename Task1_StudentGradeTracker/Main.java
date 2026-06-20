import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        GradeTrackerApp tracker = new GradeTrackerApp();

        Student s1 = new Student("R001", "Alice Johnson");
        s1.addMark("Mathematics", 92);
        s1.addMark("Science", 88.5);
        s1.addMark("English", 95);
        s1.addMark("History", 90);
        s1.addMark("Computer Sci.", 97);
        tracker.addStudent(s1);

        Student s2 = new Student("R002", "Bob Smith");
        s2.addMark("Mathematics", 74);
        s2.addMark("Science", 68);
        s2.addMark("English", 79.5);
        s2.addMark("History", 65);
        s2.addMark("Computer Sci.", 72);
        tracker.addStudent(s2);

        Student s3 = new Student("R003", "Carol White");
        s3.addMark("Mathematics", 55);
        s3.addMark("Science", 62);
        s3.addMark("English", 58.5);
        s3.addMark("History", 60);
        s3.addMark("Computer Sci.", 50);
        tracker.addStudent(s3);

        Student s4 = new Student("R004", "David Brown");
        s4.addMark("Mathematics", 40);
        s4.addMark("Science", 35);
        s4.addMark("English", 42);
        s4.addMark("History", 38);
        s4.addMark("Computer Sci.", 30);
        tracker.addStudent(s4);

        Student s5 = new Student("R005", "Eva Martinez");
        s5.addMark("Mathematics", 85);
        s5.addMark("Science", 91);
        s5.addMark("English", 78.5);
        s5.addMark("History", 82);
        s5.addMark("Computer Sci.", 88);
        tracker.addStudent(s5);

        Student s6 = new Student("R006", "Frank Lee");
        s6.addMark("Mathematics", 47);
        s6.addMark("Science", 52);
        s6.addMark("English", 45);
        s6.addMark("History", 49);
        s6.addMark("Computer Sci.", 44);
        tracker.addStudent(s6);

        System.out.println();
        System.out.println("  ---------------------------------------------");
        System.out.println("  |   STUDENT GRADE TRACKER APPLICATION       |");
        System.out.println("  |        CodeAlpha Java Internship          |");
        System.out.println("  ---------------------------------------------");

        int choice = 0;

        while (choice != 6) {

            System.out.println();
            System.out.println("  =========================================");
            System.out.println("              MAIN MENU");
            System.out.println("  =========================================");
            System.out.println("  1. View Summary Report");
            System.out.println("  2. View Detailed Subject Breakdown");
            System.out.println("  3. View Grade Distribution");
            System.out.println("  4. Add a New Student");
            System.out.println("  5. Search Student by Roll Number");
            System.out.println("  6. Exit");
            System.out.println("  =========================================");
            System.out.print("  Enter your choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
                tracker.displaySummaryReport();

            } else if (choice == 2) {
                tracker.displayDetailedBreakdown();

            } else if (choice == 3) {
                tracker.displayGradeDistribution();

            } else if (choice == 4) {
                System.out.println();
                System.out.print("  Enter Roll Number: ");
                String roll = sc.nextLine();

                System.out.print("  Enter Student Name: ");
                String name = sc.nextLine();

                System.out.print("  How many subjects? ");
                int numSub = sc.nextInt();
                sc.nextLine();

                Student newStudent = new Student(roll, name);

                for (int i = 1; i <= numSub; i++) {
                    System.out.print("  Subject " + i + " name: ");
                    String sub = sc.nextLine();
                    System.out.print("  Marks for " + sub + ": ");
                    double mark = sc.nextDouble();
                    sc.nextLine();
                    newStudent.addMark(sub, mark);
                }

                tracker.addStudent(newStudent);
                System.out.println("  Student added successfully!");

            } else if (choice == 5) {
                System.out.println();
                System.out.print("  Enter Roll Number to search: ");
                String roll = sc.nextLine();
                tracker.searchByRollNumber(roll);

            } else if (choice == 6) {
                System.out.println();
                System.out.println("  Goodbye!");
                System.out.println();

            } else {
                System.out.println("  Invalid choice. Enter 1 to 6.");
            }
        }

        sc.close();
    }
}
