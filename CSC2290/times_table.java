import java.util.*;

class Main {
    public static void main(String[] args) {
        // user input
        Scanner input = new Scanner(System.in);
        System.out.println("[ TIMES TABLE ]");
        System.out.print("Enter Desired Size: ");
        int size = input.nextInt();
        input.close();

        // top numbers
        System.out.print("\t");
        for (int i=1; i<=size; i++) {
            System.out.print(i + "\t");
        }
        
        // - bar
        System.out.println();
        for (int i=1; i<=(size*8.5); i++) {
            System.out.print("-");
        }
        
        // numbers
        int offset = Integer.toString(size).length()+1;
        for (int i=1; i<=size; i++) {
            int localSize = Integer.toString(i).length();
            System.out.println();
            System.out.print(i);
            for (int z=0; z<(offset-localSize); z++) {
                System.out.print(" ");
            }
            System.out.print("|\t");
            for (int x=1; x<=size; x++) {
                System.out.print(i*x + "\t");
            }
        }
    }
}
