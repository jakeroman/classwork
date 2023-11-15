// HW1 | Part 2 | Jake Roman
import java.util.*;
import java.text.DecimalFormat;

class Playground {
    public static void main(String[] args) {
        // welcome
        System.out.println("Welcome to Jake's Java Pizzeria");
        System.out.println();

        // user input
        Scanner input = new Scanner(System.in);
        System.out.print("1. Delivery or takeout? (delivery, takeout) ");
        String sDelivery = input.nextLine();
        System.out.print("2. Size? (small, medium, large, extra large) ");
        String sSize = input.nextLine();
        System.out.print("3. Crust? (hand-tossed, deep-dish) ");
        String sCrust = input.nextLine();
        System.out.print("4. Toppings? (0-10) ");
        String sToppings = input.nextLine();
        System.out.print("5. Are you an FSC student? (yes, no) ");
        String sStudent = input.nextLine();
        input.close();
        System.out.println();

        // convert inputs
        int delivery = 0;
        int size = 0;
        int crust = 0;
        int toppings = -1; // 0 is a valid option so -1 is used to determine failure
        boolean student = sStudent.equals("yes");

        if (sDelivery.equals("delivery")) {
            delivery = 1;
        }
        else if (sDelivery.equals("takeout")) {
            delivery = 2;
        }

        if (sSize.equals("small")) {
            size = 1;
        }
        else if (sSize.equals("medium")) {
            size = 2;
        }
        else if (sSize.equals("large")) {
            size = 3;
        }
        else if (sSize.equals("extra large")) {
            size = 4;
        }

        if (sCrust.equals("hand-tossed")) {
            crust = 1;
        }
        else if (sCrust.equals("deep-dish")) {
            crust = 2;
        }

        String[] strToNumArray = {"zero","one","two","three","four","five","six","seven","eight","nine","ten"}; // since we have 11 possible values i'm using an array here
        for (int i=0; i<=10; i++) {
            if (sToppings.equals(strToNumArray[i])) {
                toppings = i;
            }
        }

        // validate input
        if (delivery >= 1 && delivery <= 2 && size >= 1 && size <= 4 && crust >= 1 && crust <= 2 && toppings >= 0 && toppings <= 10) {
            // pizza is valid
            double price = 0;

            switch(size) { // pizza size
                case 1: {
                    System.out.print("Small ");
                    price = 9.99;
                    break;
                }
                case 2 : {
                    System.out.print("Medium ");
                    price = 11.99;
                    break;
                }
                case 3 : {
                    System.out.print("Large ");
                    price = 13.99;
                    break;
                }
                case 4 : {
                    System.out.print("Extra large ");
                    price = 19.99;
                }
            }

            switch(crust) { // pizza size
                case 1 : {
                    System.out.print("hand-tossed ");
                    break;
                }
                case 2 : {
                    System.out.print("deep-dish ");
                    price += 2.0;
                }
            }

            System.out.print("pizza with " + toppings + (toppings == 1 ? " topping" : " toppings") + " for ");
            if (toppings <= 6) {
                price += toppings*1.25;
            }
            else {
                price += 6*1.25;
                toppings -= 6;
                price += toppings*0.75;
            }

            if (delivery == 1) { // delivery method
                System.out.print("delivery");
                price += 2.5;
            }
            else {
                System.out.print("takeout");
            }

            // student discount
            double discount = 0;
            if (student) {
                discount = price*0.2;
                System.out.print(" w/ student discount");
            }
            System.out.println();

            // print pricing
            DecimalFormat dFmt = new DecimalFormat("0.00");
            System.out.println("Subtotal: $"+dFmt.format(price));
            if (student) {
                System.out.println("Discount: $"+dFmt.format(discount));
                price -= discount;
            }
            double tax = price*0.07;
            System.out.println("Tax: $"+dFmt.format(tax));
            System.out.println("Total: $"+dFmt.format(tax+price));
            System.out.println();
            System.out.println("Thanks for ordering!");
        }
        else {
            // pizza is not valid
            System.out.println("An invalid input was entered, please try again.");
        }
    }
}
