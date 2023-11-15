import java.util.*;

public class Password {
    public static void main(String[] args) {
        // simple interface, following the example in the instructions
        System.out.print("Enter a password: ");
        Scanner input = new Scanner(System.in);
        String password = input.nextLine();
        input.close();
        if (isValidPassword(password)) {
            // valid password
            System.out.println("valid");
        }
        else {
            // invalid password
            System.out.println("invalid");
        }
    }

    public static boolean isValidPassword(String password) {
        // combine all of the other validation functions to check a password
        return (isLongEnough(password) && isOnlyLettersAndDigits(password) && hasRequiredDigits(password) && hasRequiredLetters(password));
    }

    public static boolean isLongEnough(String password) {
        // return true if the password is atleast 8 characters long
        return (password.length() >= 8);
    }

    public static boolean isOnlyLettersAndDigits(String password) {
        // checks for symbols or other banned characters
        boolean pass = true;
        for (int i = 0; i < password.length(); i++) {
            // check to make sure the password only contains letters and digits... wait a minute, that's not very secure
            char c = password.charAt(i);
            if (!Character.isLetter(c) && !Character.isDigit(c)) {
                // not a character or a digit, fail check
                pass = false;
            }
        }
        return pass;
    }

    public static boolean hasRequiredLetters(String password) {
        // checks to make sure the password has both upper and lowercase letter
        boolean uppercase = false;
        boolean lowercase = false;
        for (int i = 0; i < password.length(); i++) {
            // check each character
            char c = password.charAt(i);
            if (Character.isUpperCase(c)) {
                uppercase = true;
            }
            if (Character.isLowerCase(c)) {
                lowercase = true;
            }
        }
        return (uppercase && lowercase);
    }

    public static boolean hasRequiredDigits(String password) {
        // checks to make sure the password has atleast 2 digits
        int digits = 0;
        for (int i = 0; i < password.length(); i++) {
            // go through each character and count if it's a digit
            char c = password.charAt(i);
            if (Character.isDigit(c)) {
                digits++;
            }
        }
        return (digits >= 2);
    }
}
