import java.util.*;
import java.util.stream.Collectors;
class PayrollManager {
    private final ArrayList<Employee> employees = new ArrayList<>();

    public void addEmployee(String employeeType,int id, String name, String department,double baseSalary, double bonus) {
          FullTimeEmployee emp = new FullTimeEmployee(id, name, department, baseSalary, bonus);
           employees.add(emp);
    }

    public void DepartmentWiseSalary() {
        System.out.println("Department-wise Total Salaries:");
        employees.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.summingDouble(Employee::calculateSalary)))
                .forEach((department, totalSalary) -> System.out.println("Department: " + department + ", Total Salary: " + totalSalary));
    }

    public void TopEarners(double threshold) {
        System.out.println("Top Earners (Salary > " + threshold + "):");
        System.out.println(employees.stream().filter(e -> e.calculateSalary() > threshold)
                .sorted());
    }
}