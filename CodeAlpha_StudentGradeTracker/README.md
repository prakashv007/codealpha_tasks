# Task 1 - Student Grade Tracker

This is my first task for the **CodeAlpha Java Programming Internship**.

---

## What This Project Does

This is a simple console-based Java application that helps track student grades.

You can:
- View a summary report of all students (name, average, grade)
- See a detailed breakdown of marks subject-wise
- Check how many students got each grade (A, B, C, D, F)
- Add a new student with their marks
- Search for a student by roll number

---

## Files in This Project

| File | What it does |
|------|-------------|
| `Student.java` | Stores student info like name, roll number, marks. Also has methods to calculate average, highest, lowest mark and grade. |
| `GradeTrackerApp.java` | Handles all the reports and search features. |
| `Main.java` | Entry point. Has sample students loaded and shows the menu to the user. |

---

## How to Run

Make sure you have Java installed. Then open terminal in this folder and run:

```bash
javac Student.java GradeTrackerApp.java Main.java
java Main
```

---

## Grading System

| Average Marks | Grade |
|---------------|-------|
| 90 and above  | A     |
| 75 - 89       | B     |
| 60 - 74       | C     |
| 45 - 59       | D     |
| Below 45      | F     |

---

## Sample Students Already Added

The program starts with 6 sample students (R001 to R006) with marks in 5 subjects:
- Mathematics
- Science
- English
- History
- Computer Science

---

## Tech Used

- **Language:** Java
- **Concepts:** OOP (classes and objects), ArrayLists, loops, Scanner for user input

---

*Made by Prakash | CodeAlpha Java Internship | Task 1*
