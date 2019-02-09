import java.util.Scanner;

class Birthday_Problem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("What are the chances that somebody else in this room has the same birthday as you?" + "\n" + "I can calculate the theoretical and experimental probabilities assuming that all" + "\n" + "babies are equally likely to be born on any of 365 days of the year. ");
        System.out.println("How many people are in the room?");
        int p = sc.nextInt();
        Experimental e = new Experimental(p);
        System.out.println("Experimental: " + e.getProb());
        Theoretical t = new Theoretical(p);
        System.out.println("Theoretical: " + t.getProb());
    }
}