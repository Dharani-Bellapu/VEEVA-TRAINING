abstract class Employee {
    private final int id;
    private final String name;
    private final String department;
    Employee(int id, String name, String department) {
        this.id = id;
        this.name = name;
        this.department = department;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    abstract double calculateSalary();
}

class FullTimeEmployee extends Employee {
    private final double baseSalary;
    private final double bonus;
    FullTimeEmployee(int id, String name, String department, double baseSalary, double bonus) {
        super(id, name, department);
        this.baseSalary = baseSalary;
        this.bonus = bonus;
    }
    double calculateSalary() {
        return baseSalary + bonus;
    }
}
class PartTimeEmployee extends Employee {
    private final double hourlyRate;
    private final int hoursWorked;
    PartTimeEmployee(int id, String name, String department, double hourlyRate, int hoursWorked) {
        super(id, name, department);
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
    }
    double calculateSalary() {
        return hourlyRate * hoursWorked;
    }
}
