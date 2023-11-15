import java.util.*;

import java.util.*;

public class Arrays {
    public static void main(String[] args) {
        // initialize with random
        Scanner input = new Scanner(System.in);
        int[] myList = new int[10];
        Random rng = new Random();
        for (int i = 0; i < myList.length; i++) {
            myList[i] = rng.nextInt(0,100);
        }

        // displaying
        for (int i = 0; i < myList.length; i++) {
            System.out.print(myList[i]+" ");
        }

        // summing
        int total = 0;
        for (int i = 0; i < myList.length; i++) {
            total += myList[i];
        }
        System.out.println("\nSUM: " + total);

        // find biggest element
        int biggest = myList[0];
        int smallestIndex = 0;
        for (int i = 0; i < myList.length; i++) {
            if (myList[i] > biggest) {
                biggest = myList[i];
                smallestIndex = i;
            }
        }
        System.out.println("MAX: " + biggest);

        // find smallest index of largest element
        System.out.println("SM INDEX: " + smallestIndex);

        // array shuffling
        for (int i = 0; i < myList.length; i++) {
            int index = rng.nextInt(0,myList.length-1);
            int storage = myList[index];
            myList[index] = myList[i];
            myList[i] = storage;
        }
        
        // displaying
        System.out.println("POST SHUFFLING:");
        for (int i = 0; i < myList.length; i++) {
            System.out.print(myList[i]+" ");
        }
    }
}
