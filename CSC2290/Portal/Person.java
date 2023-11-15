import java.util.ArrayList;

public class Person {
    // Properties
    protected String firstName;
    protected String lastName;
    protected int id;
    protected ArrayList<Course> courses = new ArrayList<Course>();
    protected ArrayList<Building> swipeAccess = new ArrayList<Building>();

    // Constructors
    public Person() {
        // no arg constructor
        this.id = generateId();
    }
    public Person(String firstName, String lastName) {
        // random ID constructor
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = generateId();
    }
    public Person(String firstName, String lastName, int id) {
        // specified ID constructor
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
    }

    // Getters
    public String getName() {
        return lastName+", "+firstName;
    }

    // Methods
    private int generateId() {
        // generates a random 7 digit number
        // there is a 1 in 10 million chance of getting duplicate numbers, this should be considered before production use
        return (int)(Math.random() * 10000000.0);
    }
    public void addCourse(Course course) {
        courses.add(course);
        addSwipeAccess(course.getLocation()); // give person access to the building this course is located in
    }
    public void addSwipeAccess(Building building) {
        // check for duplicate swipe access
        for (int i = 0; i < swipeAccess.size(); i++) {
            if (building == swipeAccess.get(i)) {
                // duplicate found, do not add
                return;
            }
        }

        // no duplicate, adding building
        swipeAccess.add(building);
    }

    // Getters
    public int getId() {
        return id;
    }
    public ArrayList<Course> getCourses() {
        return courses;
    }
    public int getNumberOfCourses() {
        return courses.size();
    }
    public int getNumberOfBuildings() {
        return swipeAccess.size();
    }
}
