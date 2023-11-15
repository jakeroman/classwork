// HW4 Portal Project: Student Class
// Jake Roman
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Student extends Person {
    // Constructors
    public Student() {
        // no arg constructor
        super(); // call super constructor
        if (Math.random() > 0.5) // randomly choose first name
            super.firstName = "Jane";
        else
            super.firstName = "John";
        super.lastName = "Doe";
    }
    public Student(String firstName, String lastName) {
        // specified name constructor
        super(firstName, lastName);
    }
    public Student(String firstName, String lastName, int id) {
        // specified id constructor
        super(firstName, lastName, id);
    }

    // Methods
    public void printSchedule() {
        // print out the schedule for this student
        DecimalFormat idFormat = new DecimalFormat("0000000"); // setup to print the IDs with leading zeroes
        System.out.println("Schedule for " + getName() + " ("+idFormat.format(id)+")"); // print student name and id
        System.out.println("===============================================================");

        // print student's courses
        if (courses.size() > 0) {
            for (int i = 0; i < courses.size(); i++) {
                System.out.println(courses.get(i).getCourseId()+" "+courses.get(i).getName()); // print course id and name
            }
        }
        else {
            // not enrolled in any courses, print an error
            System.out.println("Not currently enrolled in any courses.");
        }
        System.out.println();
    }
    public void printSwipeAccess() {
        // print out the swipe access for this student
        DecimalFormat idFormat = new DecimalFormat("0000000"); // setup to print the IDs with leading zeroes
        System.out.println("Swipe Access for " + getName() + " ("+idFormat.format(id)+")"); // print student name and id
        System.out.println("===============================================================");

        // print accessible buildings
        if (swipeAccess.size() > 0) {
            for (int i = 0; i < swipeAccess.size(); i++) {
                Building building = swipeAccess.get(i);
                if (building != null) {
                    System.out.println(building.getName()); // print building name
                }
            }
        }
        else {
            // no access, print an error
            System.out.println("Student does not have swipe access to any building.");
        }
        System.out.println();
    }
}
