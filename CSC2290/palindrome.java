import java.util.Scanner;

class IsPalindrome {
    public static void main(String[] args) {
        // Input
        System.out.print("Enter a 3 digit number: ");
        Scanner input = new Scanner(System.in);
        int number = input.nextInt();
        input.close();

        // Break Down Input
        int x = number;
        int array[] = {0,0,0};
        for (int i=0; i < 3; i++) {
            array[i] = x % 10;
            x /= 10;
        }

        // Reconstruct Reversed
        int rev = 0;
        int digits = 100;
        for (int i=0; i < 3; i++) { // i'm sorry for this hardcoding but it works for 3 digit numbers
            rev = rev + array[i] * digits;
            digits /= 10;
        }

        // Output
        System.out.println(number + " == " + rev + "?");
        System.out.println((rev == number) ? "Number is a palindrome!" : "Number is not a palindrome.");
    }
}
