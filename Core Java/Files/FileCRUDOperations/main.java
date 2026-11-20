import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class main {
    private static final File STUDENT_FILE = new File("student.txt");
    private static final Scanner INPUT = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            runMenu();
        } catch (IOException e) {
            System.err.println("Unable to access student.txt: " + e.getMessage());
        }
    }

    private static void runMenu() throws IOException {
        boolean running = true;
        while (running) {
            System.out.println("\nStudent Management System");
            System.out.println("1. Add student");
            System.out.println("2. Display all students");
            System.out.println("3. Find student by roll number");
            System.out.println("4. Find students by name");
            System.out.println("5. Delete student by roll number");
            System.out.println("6. Delete students by name");
            System.out.println("7. Update student by roll number");
            System.out.println("8. Exit");
            int choice = readInt("Choose an option: ", 1, 8);

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    displayStudents(readStudents());
                    break;
                case 3:
                    findByRollNumber();
                    break;
                case 4:
                    findByName();
                    break;
                case 5:
                    deleteByRollNumber();
                    break;
                case 6:
                    deleteByName();
                    break;
                case 7:
                    updateByRollNumber();
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

    private static void addStudent() throws IOException {
        List<Student> students = readStudents();
        String rollNumber = readNonEmpty("Roll number: ");
        if (findStudent(students, rollNumber) != null) {
            System.out.println("A student with that roll number already exists.");
            return;
        }

        Student student = readStudentDetails(rollNumber);
        students.add(student);
        writeStudents(students);
        System.out.println("Student added.");
    }

    private static Student readStudentDetails(String rollNumber) {
        String name = readField("Name: ");
        int age = readInt("Age: ", 1, Integer.MAX_VALUE);
        double[] marks = new double[5];
        for (int i = 0; i < marks.length; i++) {
            marks[i] = readMark("Mark " + (i + 1) + " (0-100): ");
        }
        String grade = readField("Grade: ");
        return new Student(rollNumber, name, age, marks, grade);
    }

    private static void findByRollNumber() throws IOException {
        String rollNumber = readNonEmpty("Roll number to find: ");
        Student student = findStudent(readStudents(), rollNumber);
        if (student == null) {
            System.out.println("No student found with roll number " + rollNumber + ".");
        } else {
            displayStudents(singleStudentList(student));
        }
    }

    private static void findByName() throws IOException {
        String name = readNonEmpty("Name to find: ");
        List<Student> matches = new ArrayList<Student>();
        for (Student student : readStudents()) {
            if (student.name.equalsIgnoreCase(name)) {
                matches.add(student);
            }
        }
        if (matches.isEmpty()) {
            System.out.println("No students found with that name.");
        } else {
            displayStudents(matches);
        }
    }

    private static void deleteByRollNumber() throws IOException {
        String rollNumber = readNonEmpty("Roll number to delete: ");
        List<Student> students = readStudents();
        Student student = findStudent(students, rollNumber);
        if (student == null) {
            System.out.println("No student found with roll number " + rollNumber + ".");
            return;
        }
        students.remove(student);
        writeStudents(students);
        System.out.println("Student deleted.");
    }

    private static void deleteByName() throws IOException {
        String name = readNonEmpty("Name to delete: ");
        List<Student> students = readStudents();
        int originalSize = students.size();
        students.removeIf(student -> student.name.equalsIgnoreCase(name));
        int deletedCount = originalSize - students.size();
        if (deletedCount == 0) {
            System.out.println("No students found with that name.");
            return;
        }
        writeStudents(students);
        System.out.println(deletedCount + " student record(s) deleted.");
    }

    private static void updateByRollNumber() throws IOException {
        String rollNumber = readNonEmpty("Roll number to update: ");
        List<Student> students = readStudents();
        Student student = findStudent(students, rollNumber);
        if (student == null) {
            System.out.println("No student found with roll number " + rollNumber + ".");
            return;
        }

        Student updatedStudent = readStudentDetails(student.rollNumber);
        int index = students.indexOf(student);
        students.set(index, updatedStudent);
        writeStudents(students);
        System.out.println("Student updated.");
    }

    private static List<Student> readStudents() throws IOException {
        List<Student> students = new ArrayList<Student>();
        if (!STUDENT_FILE.exists()) {
            return students;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(STUDENT_FILE))) {
            String line;
            int lineNumber = 0;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                try {
                    Student student = Student.fromLine(line);
                    if (findStudent(students, student.rollNumber) != null) {
                        throw new IllegalArgumentException("duplicate roll number");
                    }
                    students.add(student);
                } catch (IllegalArgumentException e) {
                    throw new IOException("Invalid student record at line " + lineNumber + ": " + e.getMessage(), e);
                }
            }
        }
        return students;
    }

    private static void writeStudents(List<Student> students) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(STUDENT_FILE))) {
            for (Student student : students) {
                writer.write(student.toLine());
                writer.newLine();
            }
        }
    }

    private static Student findStudent(List<Student> students, String rollNumber) {
        for (Student student : students) {
            if (student.rollNumber.equalsIgnoreCase(rollNumber)) {
                return student;
            }
        }
        return null;
    }

    private static List<Student> singleStudentList(Student student) {
        List<Student> students = new ArrayList<Student>();
        students.add(student);
        return students;
    }

    private static void displayStudents(List<Student> students) {
        if (students.isEmpty()) {
            System.out.println("No student records found.");
            return;
        }
        System.out.printf("%-12s %-20s %-5s %-8s %-8s %-8s %-8s %-8s %-8s%n",
                "Roll No", "Name", "Age", "Mark 1", "Mark 2", "Mark 3", "Mark 4", "Mark 5", "Grade");
        for (Student student : students) {
            System.out.printf("%-12s %-20s %-5d %-8.2f %-8.2f %-8.2f %-8.2f %-8.2f %-8s%n",
                    student.rollNumber, student.name, student.age, student.marks[0], student.marks[1],
                    student.marks[2], student.marks[3], student.marks[4], student.grade);
        }
    }

    private static String readNonEmpty(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = INPUT.nextLine().trim();
            if (!value.isEmpty()) {
                return value;
            }
            System.out.println("This value cannot be empty.");
        }
    }

    private static String readField(String prompt) {
        while (true) {
            String value = readNonEmpty(prompt);
            if (value.indexOf('|') == -1) {
                return value;
            }
            System.out.println("The | character is not allowed.");
        }
    }

    private static int readInt(String prompt, int minimum, int maximum) {
        while (true) {
            System.out.print(prompt);
            String value = INPUT.nextLine().trim();
            try {
                int number = Integer.parseInt(value);
                if (number >= minimum && number <= maximum) {
                    return number;
                }
            } catch (NumberFormatException e) {
                System.out.println("Enter a whole number in the allowed range.");
                continue;
            }
            System.out.println("Enter a whole number from " + minimum + " to " + maximum + ".");
        }
    }

    private static double readMark(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = INPUT.nextLine().trim();
            try {
                double mark = Double.parseDouble(value);
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

    private static class Student {
        private final String rollNumber;
        private final String name;
        private final int age;
        private final double[] marks;
        private final String grade;

        private Student(String rollNumber, String name, int age, double[] marks, String grade) {
            this.rollNumber = rollNumber;
            this.name = name;
            this.age = age;
            this.marks = marks.clone();
            this.grade = grade;
        }

        private String toLine() {
            return rollNumber + "|" + name + "|" + age + "|" + marks[0] + "|" + marks[1] + "|"
                    + marks[2] + "|" + marks[3] + "|" + marks[4] + "|" + grade;
        }

        private static Student fromLine(String line) {
            String[] fields = line.split("\\|", -1);
            if (fields.length != 9 || fields[0].trim().isEmpty() || fields[1].trim().isEmpty()
                    || fields[8].trim().isEmpty()) {
                throw new IllegalArgumentException("expected 9 non-empty fields where required");
            }
            int age;
            double[] marks = new double[5];
            try {
                age = Integer.parseInt(fields[2]);
                for (int i = 0; i < marks.length; i++) {
                    marks[i] = Double.parseDouble(fields[i + 3]);
                }
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("age or marks are not valid numbers", e);
            }
            if (age < 1) {
                throw new IllegalArgumentException("age must be positive");
            }
            for (double mark : marks) {
                if (Double.isNaN(mark) || Double.isInfinite(mark) || mark < 0 || mark > 100) {
                    throw new IllegalArgumentException("marks must be from 0 to 100");
                }
            }
            return new Student(fields[0], fields[1], age, marks, fields[8]);
        }
    }
}
