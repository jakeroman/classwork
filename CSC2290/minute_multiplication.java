import java.util.*;

class MinuteMultiplication {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        long endTime = startTime + (60*1000);

        Scanner input = new Scanner(System.in);
        Random rng = new Random();

        int score = 0;
        while (System.currentTimeMillis() < endTime) {
            int a = rng.nextInt(1,10);
            int b = rng.nextInt(1,10);
            System.out.print(a + " x " + b + " = ");
            int i = input.nextInt();

            int timeLeft = (int) (endTime-System.currentTimeMillis())/1000;

            if (i == (a*b)) {
                score++;
                System.out.println("Correct! time remaining: "+timeLeft);
            }
            else {
                System.out.println("Incorrect, answer: "+a*b);
            }
        }

        input.close();

        System.out.println("You answered " + score + " problems correctly in 1 minute!");
    }
}
