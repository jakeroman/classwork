// HW4 Portal Project: Building Class
// Jake Roman
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Building {
    // Properties
    private String name;
    private ArrayList<Course> courses = new ArrayList<Course>();
    private ArrayList<Faculty> instructors = new ArrayList<Faculty>();
    private ArrayList<Student> students = new ArrayList<Student>();

    // Constructors
    public Building(String name) {
        this.name = name;
    }

    // Getters
    public String getName() {
        return name;
    }
    public ArrayList<Course> getCourses() {
        return courses;
    }
    public ArrayList<Faculty> getFaculty() {
        return instructors;
    }
    public ArrayList<Student> getStudents() {
        return students;
    }
    public int getNumberOfCourses() {
        return courses.size();
    }
    public int getNumberOfFaculty() {
        return instructors.size();
    }
    public int getNumberOfStudents() {
        return students.size();
    }

    // Methods
    public void addCourse(Course course) {
        // add the course
        courses.add(course);
    }
    public void addSwipeAccess(Faculty instructor) {
        // check for duplicate instructor access
        for (int i = 0; i < instructors.size(); i++) {
            if (instructor == instructors.get(i)) {
                // duplicate found, do not add
                return;
            }
        }
        // no duplicate, adding instructor
        instructors.add(instructor);
    }
    public void addSwipeAccess(Student student) {
        // check for duplicate student access
        for (int i = 0; i < students.size(); i++) {
            if (student == students.get(i)) {
                // duplicate found, do not add
                return;
            }
        }
        // no duplicate, adding student
        students.add(student);
    }
    public void printSchedule() {
        // print out the schedule for this building
        System.out.println("Schedule for " + name); // print building name
        System.out.println("===============================================================");

        // print buildings's courses
        if (courses.size() > 0) {
            for (int i = 0; i < courses.size(); i++) {
                System.out.println(courses.get(i).getCourseId()+" "+courses.get(i).getName()); // print course id and name
            }
        }
        else {
            // no courses, print an error
            System.out.println("No courses are currently held in this building.");
        }
        System.out.println();
    }
    public void printSwipeAccess() {
        // print out the swipe access for this building
        System.out.println("Swipe Access for " + name); // print building name
        System.out.println("===============================================================");

        // print error message if no students or instructors
        if (students.size() <= 0 && instructors.size() <= 0) {
            System.out.println("Nobody currently has access to this building.");
            return;
        }

        // print faculty access
        DecimalFormat idFormat = new DecimalFormat("0000000"); // setup to print the IDs with leading zeroes
        if (instructors.size() > 0) {
            for (int i = 0; i < instructors.size(); i++) {
                System.out.print(idFormat.format(instructors.get(i).getId())); // print instructor's id formatted
                System.out.println(" " + instructors.get(i).getName()); // print instructor's name with a space
            }
        }

        // print student access
        if (students.size() > 0) {
            for (int i = 0; i < students.size(); i++) {
                System.out.print(idFormat.format(students.get(i).getId())); // print student's id formatted
                System.out.println(" " + students.get(i).getName()); // print student's name with a space
            }
        }
        System.out.println();
    }
}
