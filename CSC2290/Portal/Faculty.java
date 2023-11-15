// HW4 Portal Project: Faculty Class
// Jake Roman
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Faculty extends Person {
    // Constructors
    public Faculty() {
        // no arg constructor
        super.firstName = "Frank";
        super.lastName = "Wright";
    }
    public Faculty(String firstName, String lastName) {
        // specified name constructor
        super(firstName, lastName);
    }
    public Faculty(String firstName, String lastName, int id) {
        // specified id constructor
        super(firstName, lastName, id);
    }

    // Getters
    public String getName() {
        return "Dr. " + lastName;
    }

    // Methods
    public void printSchedule() {
        // print out the schedule for this faculty member
        DecimalFormat idFormat = new DecimalFormat("0000000"); // setup to print the IDs with leading zeroes
        System.out.println("Schedule for " + getName() + " ("+idFormat.format(id)+")"); // print faculty name and id
        System.out.println("===============================================================");

        // print faculty's courses
        if (courses.size() > 0) {
            for (int i = 0; i < courses.size(); i++) {
                System.out.println(courses.get(i).getCourseId()+" "+courses.get(i).getName()); // print course id and name
            }
        }
        else {
            // not teaching any courses, print an error
            System.out.println("Not currently teaching any courses.");
        }
        System.out.println();
    }
    public void printSwipeAccess() {
        // print out the swipe access for this faculty members
        DecimalFormat idFormat = new DecimalFormat("0000000"); // setup to print the IDs with leading zeroes
        System.out.println("Swipe Access for " + getName() + " ("+idFormat.format(id)+")"); // print faculty name and id
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
            System.out.println("Does not have swipe access to any building.");
        }
        System.out.println();
    }
}
