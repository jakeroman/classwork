// HW4 Portal Project: Portal Class
// Jake Roman
import java.io.*;
import java.util.*;

public class Portal {
    // Database
    private ArrayList<Course> courses = new ArrayList<Course>();
    private ArrayList<Faculty> instructors = new ArrayList<Faculty>();
    private ArrayList<Student> students = new ArrayList<Student>();
    private ArrayList<Building> buildings = new ArrayList<Building>();

    // Constructor
    public Portal() throws FileNotFoundException {
        // no arg constructor
        this.loadCourses("courses.txt");
        this.loadFaculty("faculty.txt");
        this.loadStudents("students.txt");
        this.loadBuildings("buildings.txt");
    }

    // Main Method
    public static void main(String[] args) throws FileNotFoundException {
        // creates a portal instance and provide user interface functions
        Portal portal = new Portal();
        portal.setupSwipeAccess();

        if (args[0].equals("-c")) {
            // Print the roster for the corresponding Course (if it exists)
            Course course = portal.findCourse(args[1],args[2]);
            if (course == null) {
                System.out.println("Sorry, "+args[1]+" "+args[2]+" is not a current course in Portal.");
            }
            else {
                course.printRoster();
            }
        }
        else if (args[0].equals("-s")) {
            // Print the schedule and swipe access for the corresponding Student (if they exist)
            Student student = portal.findStudent(args[1]);
            if (student == null) {
                System.out.println("Sorry, "+args[1]+" is not a current student in Portal.");
            }
            else {
                student.printSchedule();
                student.printSwipeAccess();
            }
        }
        else if (args[0].equals("-f")) {
            // Print the schedule and swipe access for the corresponding Faculty (if they exist)
            Faculty faculty = portal.findFaculty(args[1]);
            if (faculty == null) {
                System.out.println("Sorry, "+args[1]+" is not a current faculty in Portal.");
            }
            else {
                faculty.printSchedule();
                faculty.printSwipeAccess();
            }
        }
        else if (args[0].equals("-b")) {
            // Print the schedule and swipe access for the corresponding Building (if it exists)
            Building building = portal.findBuilding(args[1]);
            if (building == null) {
                System.out.println("Sorry, "+args[1]+" is not a current building in Portal.");
            }
            else {
                building.printSchedule();
                building.printSwipeAccess();
            }
        }
    }

    // Public Methods
    public void setupSwipeAccess() {
        // iterate through buildings and assign swipe access for professors and students
        for (int i = 0; i < buildings.size(); i++) {
            Building building = buildings.get(i);
            ArrayList<Course> courses = building.getCourses();
            for (int j = 0; j < building.getNumberOfCourses(); j++) {
                Course course = courses.get(j);
                building.addSwipeAccess(course.getInstructor()); // add instructor to building
                course.getInstructor().addSwipeAccess(building); // add swipe access to instructor
                ArrayList<Student> courseStudents = course.getStudents();
                for (int s = 0; s < courseStudents.size(); s++) {
                    if (courseStudents.get(s) != null) {
                        courseStudents.get(s).addSwipeAccess(building); // add building to student
                        building.addSwipeAccess(courseStudents.get(s)); // add student to building
                    }
                }
            }
        }
    }

    // Private Methods
    private void loadCourses(String filename) throws FileNotFoundException { 
        // load courses from the file and initialize courses data structure
        File file = new File(filename);
        Scanner input = new Scanner(file);
        int numberOfCourses = input.nextInt();
        for (int i = 0; i < numberOfCourses; i++) {
            // create each course object
            String dept = input.next();
            String code = input.next();
            String name = input.nextLine().substring(1);
            //System.out.println(dept+" "+code+" "+name);
            courses.add(new Course(dept, code, name));
        }
        input.close();
    }
    private void loadFaculty(String filename) throws FileNotFoundException { 
        // load faculty from the file and initialize instructors data structure
        File file = new File(filename);
        Scanner input = new Scanner(file);
        int numberOfFaculty = input.nextInt();
        for (int i = 0; i < numberOfFaculty; i++) {
            // create each faculty object
            String firstName = input.next();
            String lastName = input.next();
            instructors.add(new Faculty(firstName, lastName));
            String courseData[] = input.nextLine().substring(1).split(" ");
            // add taught courses
            for (int courseId = 0; courseId < courseData.length/2; courseId++) {
                String dept = courseData[courseId*2];
                String code = courseData[1+(courseId*2)];
                // search for course
                Course course = findCourse(dept, code);
                if (course != null) {
                    // if course exists, add instructor and vice versa
                    course.addPerson(instructors.get(i));
                    instructors.get(i).addCourse(course);
                }
            }
        }
        input.close();
    }
    private void loadStudents(String filename) throws FileNotFoundException { 
        // load students from the file and initialize students data structure
        File file = new File(filename);
        Scanner input = new Scanner(file);
        int numberOfStudents = input.nextInt();
        for (int i = 0; i < numberOfStudents; i++) {
            // create each student object
            String firstName = input.next();
            String lastName = input.next();
            students.add(new Student(firstName, lastName));
            String courseData[] = input.nextLine().substring(1).split(" ");
            // add enrolled courses
            for (int courseId = 0; courseId < courseData.length/2; courseId++) {
                String dept = courseData[courseId*2];
                String code = courseData[1+courseId*2];
                // search for course
                Course course = findCourse(dept, code);
                if (course != null) {
                    // if course exists, add student and vice versa
                    course.addPerson(students.get(i));
                    students.get(i).addCourse(course);
                }
            }
        }
        input.close();
    }
    private void loadBuildings(String filename) throws FileNotFoundException { 
        // load buildings from the file and initialize buildings data structure
        File file = new File(filename);
        Scanner input = new Scanner(file);
        int numberOfBuildings = input.nextInt();
        for (int i = 0; i < numberOfBuildings; i++) {
            Building building = new Building(input.next());
            buildings.add(building);
            String courseData[] = input.nextLine().substring(1).split(" ");
            // add held courses
            for (int courseId = 0; courseId < courseData.length/2; courseId++) {
                String dept = courseData[courseId*2];
                String code = courseData[1+courseId*2];
                // search for course
                Course course = findCourse(dept, code);
                if (course != null) {
                    building.addCourse(course); // add location
                }
            }
        }
        input.close();
    }
    private Course findCourse(String dept, String code) {
        // finds a course object based on its dept code
        for (int i = 0; i < courses.size(); i++) {
            //System.out.println(courses[i].getCourseId() + " =?= "+dept+" "+code);
            if (courses.get(i).getCourseId().equals(dept + " " + code)) {
                return courses.get(i);
            }
        }
        return null;
    }
    private Faculty findFaculty(String lastName) {
        // finds a faculty object from their lastname
        for (int i = 0; i < instructors.size(); i++) {
            String nameArray[] = instructors.get(i).getName().split(". ");
            String instructorLastName = nameArray[1];
            if (instructorLastName.equals(lastName)) {
                return instructors.get(i);
            }
        }
        return null;
    }
    private Student findStudent(String lastName) {
        // finds a student object from their lastname
        for (int i = 0; i < students.size(); i++) {
            String nameArray[] = students.get(i).getName().split(", ");
            String studentLastName = nameArray[0];
            if (studentLastName.equals(lastName)) {
                return students.get(i);
            }
        }
        return null;
    }
    private Building findBuilding(String name) {
        // finds a building object from its name
        for (int i = 0; i < buildings.size(); i++) {
            if (buildings.get(i).getName().equals(name)) {
                return buildings.get(i);
            }
        }
        return null;
    }
}
