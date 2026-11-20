import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StudentManager {
    private final Path studentFile;
    private final ArrayList<Student> students;

    public StudentManager(Path studentFile) throws IOException {
        if (studentFile == null) {
            throw new IllegalArgumentException("Student file path cannot be null.");
        }
        this.studentFile = studentFile;
        this.students = loadStudents();
    }

    public List<Student> getAllStudents() {
        return Collections.unmodifiableList(new ArrayList<Student>(students));
    }

    public Student findByRollNumber(String rollNumber) {
        for (Student student : students) {
            if (student.getRollNumber().equalsIgnoreCase(rollNumber.trim())) {
                return student;
            }
        }
        return null;
    }

    public List<Student> findByName(String name) {
        List<Student> matches = new ArrayList<Student>();
        String searchName = name.trim();
        for (Student student : students) {
            if (student.getName().equalsIgnoreCase(searchName)) {
                matches.add(student);
            }
        }
        return matches;
    }

    public void addStudent(Student student) throws IOException {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null.");
        }
        if (findByRollNumber(student.getRollNumber()) != null) {
            throw new IllegalArgumentException("A student with that roll number already exists.");
        }

        ArrayList<Student> updatedStudents = new ArrayList<Student>(students);
        updatedStudents.add(student);
        saveStudents(updatedStudents);
        students.add(student);
    }

    public boolean updateStudent(String rollNumber, Student updatedStudent) throws IOException {
        int index = indexOfRollNumber(rollNumber);
        if (index < 0) {
            return false;
        }
        Student existingStudent = students.get(index);
        Student duplicate = findByRollNumber(updatedStudent.getRollNumber());
        if (duplicate != null && duplicate != existingStudent) {
            throw new IllegalArgumentException("A student with that roll number already exists.");
        }

        ArrayList<Student> updatedStudents = new ArrayList<Student>(students);
        updatedStudents.set(index, updatedStudent);
        saveStudents(updatedStudents);
        students.set(index, updatedStudent);
        return true;
    }

    public boolean deleteByRollNumber(String rollNumber) throws IOException {
        int index = indexOfRollNumber(rollNumber);
        if (index < 0) {
            return false;
        }

        ArrayList<Student> updatedStudents = new ArrayList<Student>(students);
        updatedStudents.remove(index);
        saveStudents(updatedStudents);
        students.remove(index);
        return true;
    }

    public int deleteByName(String name) throws IOException {
        ArrayList<Student> updatedStudents = new ArrayList<Student>(students);
        String searchName = name.trim();
        updatedStudents.removeIf(student -> student.getName().equalsIgnoreCase(searchName));
        int deletedCount = students.size() - updatedStudents.size();
        if (deletedCount > 0) {
            saveStudents(updatedStudents);
            students.clear();
            students.addAll(updatedStudents);
        }
        return deletedCount;
    }

    private ArrayList<Student> loadStudents() throws IOException {
        ArrayList<Student> loadedStudents = new ArrayList<Student>();
        if (!Files.exists(studentFile)) {
            return loadedStudents;
        }

        try (BufferedReader reader = Files.newBufferedReader(studentFile, StandardCharsets.UTF_8)) {
            String line;
            int lineNumber = 0;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                try {
                    Student student = Student.fromFileLine(line);
                    for (Student existing : loadedStudents) {
                        if (existing.getRollNumber().equalsIgnoreCase(student.getRollNumber())) {
                            throw new IllegalArgumentException("Duplicate roll number.");
                        }
                    }
                    loadedStudents.add(student);
                } catch (IllegalArgumentException e) {
                    throw new IOException("Invalid student record at line " + lineNumber + ": "
                            + e.getMessage(), e);
                }
            }
        }
        return loadedStudents;
    }

    private void saveStudents(List<Student> studentsToSave) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(studentFile, StandardCharsets.UTF_8)) {
            for (Student student : studentsToSave) {
                writer.write(student.toFileLine());
                writer.newLine();
            }
        }
    }

    private int indexOfRollNumber(String rollNumber) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getRollNumber().equalsIgnoreCase(rollNumber.trim())) {
                return i;
            }
        }
        return -1;
    }
}
