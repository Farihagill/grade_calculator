import java.util.Arrays;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Student {
    private int id;
    private String name;
    private int score;

    public Student(int id, String name, int score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public char getGrade() {
        if (score >= 90) {
            return 'A';
        } else if (score >= 80) {
            return 'B';
        } else if (score >= 70) {
            return 'C';
        } else if (score >= 60) {
            return 'D';
        } else {
            return 'F';
        }
    }

    public double getGPA() {
        double gpa = 0.0;
        switch (getGrade()) {
            case 'A':
                gpa = 4.0;
                break;
            case 'B':
                gpa = 3.0;
                break;
            case 'C':
                gpa = 2.0;
                break;
            case 'D':
                gpa = 1.0;
                break;
            case 'F':
                gpa = 0.0;
                break;
            default:
                gpa = -1.0; // invalid grade
        }
        return gpa;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of students: ");
        int numStudents = scanner.nextInt();
        Student[] students = new Student[numStudents];

        double totalGPA = 0.0;
        for (int i = 0; i < numStudents; i++) {
            System.out.print("Enter student ID: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // consume newline
            System.out.print("Enter student name: ");
            String name = scanner.nextLine();
            System.out.print("Enter student score: ");
            int score = scanner.nextInt();
            students[i] = new Student(id, name, score);
            totalGPA += students[i].getGPA();
        }

        double overallGPA = totalGPA / numStudents;

        // Sort students by ID in ascending order
        Arrays.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return Integer.compare(s1.getId(), s2.getId());
            }
        });

        System.out.println("Student Information (sorted by ID):");
        for (Student student : students) {
            System.out.println("Student ID: " + student.getId() + ", Name: " + student.getName() + ", Grade: " + student.getGrade() + ", GPA: " + student.getGPA());
        }

        System.out.println("Overall GPA: " + overallGPA);

        // Additional functionality:
        System.out.println("\nTop 3 Students by GPA:");
        Student[] topStudents = getTopStudents(students, 3);
        for (Student student : topStudents) {
            System.out.println("Student ID: " + student.getId() + ", Name: " + student.getName() + ", Grade: " + student.getGrade() + ", GPA: " + student.getGPA());
        }

        System.out.println("\nStudents with GPA above 3.0:");
        Student[] aboveThreeStudents = getStudentsWithGPA(students, 3.0);
        for (Student student : aboveThreeStudents) {
            System.out.println("Student ID: " + student.getId() + ", Name: " + student.getName() + ", Grade: " + student.getGrade() + ", GPA: " + student.getGPA());
        }
    }

    public static Student[] getTopStudents(Student[] students, int topCount) {
        if (topCount <= 0) {
            throw new IllegalArgumentException("Invalid topCount: " + topCount);
        }

        // Sort students by GPA in descending order
        Arrays.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return Double.compare(s2.getGPA(), s1.getGPA());
            }
        });

        // If topCount is greater than the number of students, adjust it
        topCount = Math.min(topCount, students.length);

        // Extract top students
        Student[] topStudents = new Student[topCount];
        System.arraycopy(students, 0, topStudents, 0, topCount);

        return topStudents;
    }

    public static Student[] getStudentsWithGPA(Student[] students, double gpaThreshold) {
        ArrayList<Student> studentsWithGPA = new ArrayList<>();
        for (Student student : students) {
            if (student.getGPA() >= gpaThreshold) {
                studentsWithGPA.add(student);
            }
        }
        return studentsWithGPA.toArray(new Student[0]);
    }
}
