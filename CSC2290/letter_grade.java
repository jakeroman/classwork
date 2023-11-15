import java.util.Scanner;

class LetterGrade {
    public static void main(String[] args) {
        // Input
        System.out.print("Input grade %: ");
        Scanner input = new Scanner(System.in);
        int grade = input.nextInt();
        input.close();

        // Logic
        if (grade >= 90) {
            System.out.println("Grade A :D");
        }
        else if (grade >= 80) {
            System.out.println("Grade B :]");
        }
        else if (grade >= 70) {
            System.out.println("Grade C :/");
        }
        else if (grade >= 60) {
            System.out.println("Grade D :[");
        }
        else {
            System.out.println("Grade F -_-");
        }
    }
}
