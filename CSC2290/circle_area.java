// Area of a Circle - Jake Roman
import java.util.Scanner;

class AreaCircle {
    public static void main(String[] args) {
        // Pi approximation
        double pi = 3.14159;

        // "User interface"
        System.out.println("[ Area of a Circle ]");
        System.out.print("Radius: ");  

        // Receive user input
        Scanner input = new Scanner(System.in);
        double radius = input.nextDouble();
        input.close();

        // Calculate area
        double squaredRadius = radius * radius;
        double area = squaredRadius * pi;

        // Display result
        System.out.println("Area: " + Double.toString(area)); 
    }
}
