// Jacob Roman | Math Question
import java.lang.Math;
import java.util.Scanner;

class MathQuestion {
    static private int randomNumber(int min, int max) {
        double rng = Math.random();
        return (int)(rng*(max-min+1)+min);
    }

    public static void main(String[] args) {
        // Settings
        final int lowRange = 1; // minimum number
        final int highRange = 50; // maximum number

        // Generate 2 random numbers
        int numA = randomNumber(lowRange, highRange);
        int numB = randomNumber(lowRange, highRange);

        // Pick math operation
        int mode = randomNumber(0, 3); // 0 = add, 1 = subtract, 2 = multiply, 3 = squared

        // Calculate correct answer
        int answer = 0;
        String symbol = " ";
        if (mode == 0) {
            // addition
            answer = numA + numB;
            symbol = "+";
        }
        else if (mode == 1) {
            // subtraction
            answer = numA - numB;
            symbol = "-";
        }
        else if (mode == 2) {
            // multiplication
            answer = numA * numB;
            symbol = "*";
        }
        else if (mode == 3) {
            // squaring numbers, added because you said you liked them.
            numB = 2; // number B will be forced to 2
            answer = numA * numA;
            symbol = "^";
        }

        // Prompt user
        System.out.println("[ Math Question ]");
        System.out.print(numA + " " + symbol + " " + numB + " = ");

        // Read user input
        Scanner input = new Scanner(System.in);
        int guess = input.nextInt();
        input.close();

        // Check answer
        if (guess == answer) {
            System.out.println("Correct!");
        }
        else {
            System.out.println("Incorrect.");
            System.out.println("Answer: " + answer);
        }
    }
}
