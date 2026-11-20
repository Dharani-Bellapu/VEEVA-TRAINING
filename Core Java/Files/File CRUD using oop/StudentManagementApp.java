import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class StudentManagementApp {
    private final Scanner scanner;
    private final StudentManager studentManager;

    private StudentManagementApp(StudentManager studentManager) {
        this.studentManager = studentManager;
        this.scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        try {
            StudentManager manager = new StudentManager(Paths.get("student.txt"));
            new StudentManagementApp(manager).run();
        } catch (IOException e) {
            System.err.println("Unable to load or save student.txt: " + e.getMessage());
        }
    }

    private void run() throws IOException {
        boolean running = true;
        while (running) {
            System.out.println("\nStudent Management System");
            System.out.println("1. Add student");
            System.out.println("2. Display all students");
            System.out.println("3. Find student by roll number");
            System.out.println("4. Find students by name");
            System.out.println("5. Update student");
            System.out.println("6. Delete student by roll number");
            System.out.println("7. Delete students by name");
            System.out.println("8. Exit");

            int choice = readInt("Choose an option: ", 1, 8);
            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    displayStudents(studentManager.getAllStudents());
                    break;
                case 3:
                    findByRollNumber();
                    break;
                case 4:
                    findByName();
                    break;
                case 5:
                    updateStudent();
                    break;
                case 6:
                    deleteByRollNumber();
                    break;
                case 7:
                    deleteByName();
                    break;
                case 8:
                    running = false;
                    System.out.println("Goodbye.");
                    break;
                default:
                    throw new IllegalStateException("Unexpected menu choice: " + choice);
            }
        }
    }

    private void addStudent() throws IOException {
        Student student = readStudentDetails(readNonEmpty("Roll number: "));
        try {
            studentManager.addStudent(student);
            System.out.println("Student added.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void findByRollNumber() {
        String rollNumber = readNonEmpty("Roll number to find: ");
        Student student = studentManager.findByRollNumber(rollNumber);
        if (student == null) {
            System.out.println("No student found with roll number " + rollNumber + ".");
        } else {
            displayStudents(java.util.Collections.singletonList(student));
        }
    }

    private void findByName() {
        List<Student> matches = studentManager.findByName(readNonEmpty("Name to find: "));
        displayStudents(matches);
    }

    private void updateStudent() throws IOException {
        String rollNumber = readNonEmpty("Roll number to update: ");
        Student existingStudent = studentManager.findByRollNumber(rollNumber);
        if (existingStudent == null) {
            System.out.println("No student found with roll number " + rollNumber + ".");
            return;
        }

        Student updatedStudent = readStudentDetails(existingStudent.getRollNumber());
        try {
            studentManager.updateStudent(rollNumber, updatedStudent);
            System.out.println("Student updated.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void deleteByRollNumber() throws IOException {
        String rollNumber = readNonEmpty("Roll number to delete: ");
        if (studentManager.deleteByRollNumber(rollNumber)) {
            System.out.println("Student deleted.");
        } else {
            System.out.println("No student found with roll number " + rollNumber + ".");
        }
    }

    private void deleteByName() throws IOException {
        String name = readNonEmpty("Name to delete: ");
        int deletedCount = studentManager.deleteByName(name);
        if (deletedCount == 0) {
            System.out.println("No students found with that name.");
        } else {
            System.out.println(deletedCount + " student record(s) deleted.");
        }
    }

    private Student readStudentDetails(String rollNumber) {
        String name = readNonEmpty("Name: ");
        int age = readInt("Age: ", 1, Integer.MAX_VALUE);
        double[] marks = new double[5];
        for (int i = 0; i < marks.length; i++) {
            marks[i] = readMark("Mark " + (i + 1) + " (0-100): ");
        }
        String grade = readNonEmpty("Grade: ");
        return new Student(rollNumber, name, age, marks, grade);
    }

    private void displayStudents(List<Student> students) {
        if (students.isEmpty()) {
            System.out.println("No student records found.");
            return;
        }

        System.out.printf("%-12s %-20s %-5s %-8s %-8s %-8s %-8s %-8s %-8s%n",
                "Roll No", "Name", "Age", "Mark 1", "Mark 2", "Mark 3", "Mark 4", "Mark 5", "Grade");
        for (Student student : students) {
            double[] marks = student.getMarks();
            System.out.printf("%-12s %-20s %-5d %-8.2f %-8.2f %-8.2f %-8.2f %-8.2f %-8s%n",
                    student.getRollNumber(), student.getName(), student.getAge(),
                    marks[0], marks[1], marks[2], marks[3], marks[4], student.getGrade());
        }
    }

    private String readNonEmpty(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();
            if (value.isEmpty()) {
                System.out.println("This value cannot be empty.");
            } else if (value.indexOf('|') >= 0) {
                System.out.println("The | character is not allowed.");
            } else {
                return value;
            }
        }
    }

    private int readInt(String prompt, int minimum, int maximum) {
        while (true) {
            System.out.print(prompt);
            try {
                int value = Integer.parseInt(scanner.nextLine().trim());
                if (value >= minimum && value <= maximum) {
                    return value;
                }
            } catch (NumberFormatException e) {
                System.out.println("Enter a whole number in the allowed range.");
                continue;
            }
            System.out.println("Enter a whole number from " + minimum + " to " + maximum + ".");
        }
    }

    private double readMark(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                double mark = Double.parseDouble(scanner.nextLine().trim());
                if (!Double.isNaN(mark) && !Double.isInfinite(mark) && mark >= 0 && mark <= 100) {
                    return mark;
                }
            } catch (NumberFormatException e) {
                System.out.println("Enter a number from 0 to 100.");
                continue;
            }
            System.out.println("Enter a number from 0 to 100.");
        }
    }
}
