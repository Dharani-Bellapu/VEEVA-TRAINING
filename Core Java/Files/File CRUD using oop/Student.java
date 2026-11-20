public class Student {
    private final String rollNumber;
    private final String name;
    private final int age;
    private final double[] marks;
    private final String grade;

    public Student(String rollNumber, String name, int age, double[] marks, String grade) {
        this.rollNumber = requireField(rollNumber, "Roll number");
        this.name = requireField(name, "Name");
        this.grade = requireField(grade, "Grade");
        if (age < 1) {
            throw new IllegalArgumentException("Age must be positive.");
        }
        if (marks == null || marks.length != 5) {
            throw new IllegalArgumentException("Exactly five marks are required.");
        }
        this.age = age;
        this.marks = marks.clone();
        for (double mark : this.marks) {
            if (Double.isNaN(mark) || Double.isInfinite(mark) || mark < 0 || mark > 100) {
                throw new IllegalArgumentException("Marks must be between 0 and 100.");
            }
        }
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double[] getMarks() {
        return marks.clone();
    }

    public String getGrade() {
        return grade;
    }

    public String toFileLine() {
        StringBuilder line = new StringBuilder();
        line.append(rollNumber).append('|').append(name).append('|').append(age);
        for (double mark : marks) {
            line.append('|').append(mark);
        }
        return line.append('|').append(grade).toString();
    }

    public static Student fromFileLine(String line) {
        String[] fields = line.split("\\|", -1);
        if (fields.length != 9) {
            throw new IllegalArgumentException("Expected nine fields.");
        }

        try {
            int age = Integer.parseInt(fields[2]);
            double[] marks = new double[5];
            for (int i = 0; i < marks.length; i++) {
                marks[i] = Double.parseDouble(fields[i + 3]);
            }
            return new Student(fields[0], fields[1], age, marks, fields[8]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Age and marks must be valid numbers.", e);
        }
    }

    private static String requireField(String value, String fieldName) {
        if (value == null || value.trim().isEmpty() || value.indexOf('|') >= 0) {
            throw new IllegalArgumentException(fieldName + " must be non-empty and cannot contain '|'.");
        }
        return value.trim();
    }
}
