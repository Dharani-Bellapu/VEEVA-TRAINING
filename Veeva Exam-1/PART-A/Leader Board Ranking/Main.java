import java.util.*;
public class Main{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        RankAssign rankAssign = new RankAssign();
        System.out.println("Enter the number of participants to add:");
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            System.out.println("Enter the details of participant " + (i + 1) + ":");
            String username = sc.next();
            int problemsSolved = sc.nextInt();
            int penaltyTime = sc.nextInt();
            int currentRating = sc.nextInt();
            
           rankAssign.addParticipant(problemsSolved, penaltyTime, currentRating, username);
        }

        System.out.println("Rankings:");
        while (!rankAssign.participantQueue.isEmpty()) {
            Participant participant = rankAssign.participantQueue.poll();
            System.out.println( participant.getUsername() + " " + participant.getProblemsSolved() +
                    " " + participant.getPenaltyTime() + " " + participant.getCurrentRating());
        }
        sc.close();
    }
}
class RankAssign {
    PriorityQueue<Participant> participantQueue=new PriorityQueue<>((Participant p1, Participant p2) -> {
        if (p1.getProblemsSolved() != p2.getProblemsSolved()) {
            return Integer.compare(p2.getProblemsSolved(), p1.getProblemsSolved());
        } else if (p1.getPenaltyTime() != p2.getPenaltyTime()) {
            return Integer.compare(p1.getPenaltyTime(), p2.getPenaltyTime());
        } else if(p1.getCurrentRating() != p2.getCurrentRating()) {
            return Integer.compare(p2.getCurrentRating(), p1.getCurrentRating());
        }
        else{
            return p1.getUsername().compareTo(p2.getUsername());
        }
    });
    public void addParticipant(int problemsSolved, int penaltyTime, int currentRating, String username) {
        Participant participant = new Participant(problemsSolved, penaltyTime, currentRating, username);
        participantQueue.add(participant);
    }
}

class Participant {
    private final int problemsSolved;
    private final int penaltyTime;
    private final int currentRating;
    private final String username;

    Participant(int problemsSolved, int penaltyTime, int currentRating, String username) {
        this.problemsSolved = problemsSolved;
        this.penaltyTime = penaltyTime;
        this.currentRating = currentRating;
        this.username = username;
    }

    public int getProblemsSolved() {
        return problemsSolved;
    }

    public int getPenaltyTime() {
        return penaltyTime;
    }

    public int getCurrentRating() {
        return currentRating;
    }

    public String getUsername() {
        return username;
    }
}
