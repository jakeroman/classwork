import java.util.*;

public class CreditCard {
    public static void main(String[] args) {
        // input number of cards
        Scanner in = new Scanner(System.in);
        int numberCards = in.nextInt();

        // card processing loop
        for (int i = 0; i < numberCards; i++) {
            Long card = in.nextLong();
            Boolean valid = isValid(card);
            System.out.println(card+" is "+(valid ? "valid" : "invalid"));
        }

        // cleanup
        in.close();
    }

    static int[] validPrefixes = {4,5,6,37}; // allowed card number prefixes, isValid will use these when checking cards.

    public static boolean isValid(long number) {
        // top level function that returns true if the given card number is valid
        String numberStr = Long.toString(number);

        // length check
        int length = numberStr.length();
        if (length < 13 || length > 16) {
            // failed
            return false;
        }

        // prefix check
        boolean prefixCheckPassed = false;
        for (int i = 0; i < validPrefixes.length; i++) {
            if (prefixMatched(number, validPrefixes[i])) {
                prefixCheckPassed = true;
            }
        }
        if (!prefixCheckPassed) {
            // failed
            return false;
        }

        // luhn check
        System.out.println(sumOfDoubleEvenPlace(number));
        System.out.println(sumOfOddPlace(number));
        int luhn = sumOfDoubleEvenPlace(number) + sumOfOddPlace(number);
        if (luhn%10 != 0) {
            // failed
            return false;
        }   
        
        // number is valid
        return true;
    }

    public static int sumOfDoubleEvenPlace(long number) {
        // takes even place digits, doubles them, simplifies using getDigit, then returns their sum.
        String numberStr = Long.toString(number);
        int sum = 0;
        for (int i = numberStr.length()-1; i >= 0; i--) {
            int n = Integer.parseInt(numberStr.substring(i,i+1));
            if (i % 2 == 0) {
                // even digits
                n *= 2; // double n
                n = getDigit(n);
                sum += n;
            }
        }
        return sum;
    }

    public static int getDigit(int number) {
        // if given a two digit number, add the two digits, otherwise return without change.
        if (number >= 10) {
            int n1 = number/10;
            int n2 = number%10;
            number = n1 + n2; // it is a double digit number, so add both digits
        }
        return number;
    }

    public static int sumOfOddPlace(long number) {
        // adds up all the odd place numbers in the given card input
        String numberStr = Long.toString(number);
        int sum = 0;
        for (int i = numberStr.length()-1; i >= 0; i--) {
            int n = Integer.parseInt(numberStr.substring(i,i+1));
            if (i % 2 == 1) {
                // odd digits
                sum += n;
            }
        }
        return sum;
    }

    public static boolean prefixMatched(long number, int d) {
        // returns true if number starts with d
        return getPrefix(number, getSize(d)) == d;
    }

    public static int getSize(long d) {
        // returns the length of d
        return Long.toString(d).length();
    }

    public static long getPrefix(long number, int k) {
        // returns the first k digits of number
        String str = Long.toString(number);
        return Integer.parseInt(str.substring(0,k));
    }
}
