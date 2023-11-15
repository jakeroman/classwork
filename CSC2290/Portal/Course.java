// HW4 Portal Project: Course Class
// Jake Roman
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Course {
    // Properties
    private String dept = "CSC";
    private String code = "####";
    private String name = "Test Course";
    private final int MAXSTUDENTS = 30;
    private ArrayList<Student> students = new ArrayList<Student>();
    private Faculty instructor;
    private Building location;

    // Constructors
    public Course() {
        // no arg constructor
    }
    public Course(String dept, String code, String name) {
        // create course with given code
        this.dept = dept;
        this.code = code;
        this.name = name;
    }
    
    // Getters
    public String getCourseId() {
        return dept + " " + code;
    }
    public String getName() {
        return name;
    }
    public ArrayList<Student> getStudents() {
        return students;
    }
    public int getNumberOfStudents() {
        return students.size();
    }
    public Faculty getInstructor() {
        return instructor;
    }
    public Building getLocation() {
        return location;
    }

    // Methods
    public void addPerson(Person person) {
        // adds the given person object to this course (polymorphism!)
        if (person instanceof Faculty) {
            // set course instructor
            this.instructor = (Faculty) person;
        }
        else if (person instanceof Student) {
            // add student to course
            if (students.size() < MAXSTUDENTS) {
                // only add student if we have room
                students.add((Student) person);
            }
        }
    }
    public void addLocation(Building location) {
        this.location = location;
    }
    public void printRoster() {
        // print out the roster for this course
        System.out.println("Roster for "+dept+" "+code+": "+name+" ("+instructor.getName()+")");
        System.out.println("===============================================================");

        // print each student's id and name
        if (students.size() > 0) {
            DecimalFormat idFormat = new DecimalFormat("0000000"); // setup to print the IDs with leading zeroes
            for (int i = 0; i < students.size(); i++) {
                System.out.print(idFormat.format(students.get(i).getId())); // print student id formatted
                System.out.println(" " + students.get(i).getName()); // print student's name with a space
            }
        }
        else {
            // if course is currently empty print an error
            System.out.println("No students are currently enrolled in this course.");
        }
        System.out.println();
    }
}
