
import java.util.*;

class PatientDetails {
    int id;
    String name;
    int severity;
    int arrivalTime;

    Patient(int id, String name, int severity, int arrivalTime) {
        this.id = id;
        this.name = name;
        this.severity = severity;
        this.arrivalTime = arrivalTime;
    }

    public String toString() {
        return id + " " + name + " " + severity + " " + arrivalTime;
    }
}

public class Patient {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        PriorityQueue<Patient> pq = new PriorityQueue<>(
                (p1, p2) -> {
                    if (p1.severity != p2.severity) {
                        return p2.severity - p1.severity;
                    }
                    return p1.arrivalTime - p2.arrivalTime;
                }
        );

        System.out.println("Enter number of patients:");
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.println("Enter patient ID:");
            int id = sc.nextInt();

            System.out.println("Enter patient name:");
            String name = sc.next();

            System.out.println("Enter severity level (1-10):");
            int severity = sc.nextInt();

            System.out.println("Enter arrival time (1-23):");
            int arrivalTime = sc.nextInt();

            pq.add(new Patient(id, name, severity, arrivalTime));
        }

        System.out.println("\nPatients Treatment Order:");
        System.out.println("ID Name Severity ArrivalTime");

        while (!pq.isEmpty()) {
            System.out.println(pq.poll());
        }

        sc.close();
    }
}
