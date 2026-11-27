import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PayrollManager payrollManager = new PayrollManager();
        System.out.println("Enter the number of employees to add:");
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.println("Enter the details of employee " + (i + 1) + ":");
            String employeeType = sc.next();
            int id = sc.nextInt();
            String name = sc.next();
            String department = sc.next();
            if(employeeType.equalsIgnoreCase("FullTime")) {
            System.out.println("Enter base salary and bonus:");
            double baseSalary = sc.nextDouble();
            double bonus = sc.nextDouble();
            payrollManager.addEmployee(employeeType,id, name, department,baseSalary, bonus);
            }
            else if(employeeType.equalsIgnoreCase("PartTime")) {
            System.out.println("Enter hourly rate and hours worked:");
            double hourlyRate = sc.nextDouble();
            int hoursWorked = sc.nextInt();
            payrollManager.addEmployee(employeeType,id, name, department,hourlyRate, hoursWorked);
            }
            
        }
        System.out.println("Enter the salary threshold for top earners:");
        while(true) {
          System.out.println("1- Department-wise total Salary\n2- Top Earners");
          int choice = sc.nextInt();
          if(choice == 1) {
            payrollManager.DepartmentWiseSalary();
          }
          else if(choice == 2) {
            System.out.println("enter threshhold :");
            double threshold = sc.nextDouble();
            if(threshold < 0) {
                System.out.println("Please enter a valid threshold (non-negative):");
                continue;
            }
            payrollManager.TopEarners(threshold);
        }
        else
            break;
        }
       
        sc.close();
    }
}

