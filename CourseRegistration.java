import java.util.ArrayList;
import java.util.Scanner;

class Course {
    String code;
    String title;
    String description;
    int capacity;
    String schedule;
    int registered;

    Course(String code, String title, String description,
           int capacity, String schedule) {

        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.registered = 0;
    }

    void display() {
        System.out.println("\nCourse Code: " + code);
        System.out.println("Title: " + title);
        System.out.println("Description: " + description);
        System.out.println("Schedule: " + schedule);
        System.out.println("Available Slots: " + (capacity - registered));
    }
}

class Student {
    int id;
    String name;
    ArrayList<Course> courses;

    Student(int id, String name) {
        this.id = id;
        this.name = name;
        courses = new ArrayList<>();
    }

    void showCourses() {

        if (courses.isEmpty()) {
            System.out.println("No courses registered.");
            return;
        }

        System.out.println("\nRegistered Courses:");

        for (Course course : courses) {
            System.out.println(course.code + " - " + course.title);
        }
    }
}

public class CourseRegistration {

    static Scanner sc = new Scanner(System.in);

    static ArrayList<Course> courseList = new ArrayList<>();
    static ArrayList<Student> studentList = new ArrayList<>();

    public static void main(String[] args) {

        courseList.add(new Course(
                "CS101",
                "Java Programming",
                "Learn Java programming basics",
                3,
                "Monday 10:00 AM"
        ));

        courseList.add(new Course(
                "CS102",
                "Database Management",
                "Learn database concepts",
                3,
                "Tuesday 11:00 AM"
        ));

        courseList.add(new Course(
                "CS103",
                "Web Development",
                "Learn HTML, CSS and JavaScript",
                2,
                "Wednesday 2:00 PM"
        ));

        System.out.print("Enter Student ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Student Name: ");
        String name = sc.nextLine();

        Student student = new Student(id, name);
        studentList.add(student);

        int choice;

        do {

            System.out.println("\n==============================");
            System.out.println(" COURSE REGISTRATION SYSTEM");
            System.out.println("==============================");
            System.out.println("1. View Courses");
            System.out.println("2. Register for Course");
            System.out.println("3. Drop Course");
            System.out.println("4. View My Courses");
            System.out.println("5. Exit");
            System.out.println("==============================");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    viewCourses();
                    break;

                case 2:
                    registerCourse(student);
                    break;

                case 3:
                    dropCourse(student);
                    break;

                case 4:
                    student.showCourses();
                    break;

                case 5:
                    System.out.println("Thank you!");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 5);

        sc.close();
    }

    static void viewCourses() {

        System.out.println("\n===== AVAILABLE COURSES =====");

        for (Course course : courseList) {
            course.display();
        }
    }

    static void registerCourse(Student student) {

        viewCourses();

        System.out.print("\nEnter course code: ");
        String code = sc.nextLine();

        for (Course course : courseList) {

            if (course.code.equalsIgnoreCase(code)) {

                if (course.registered >= course.capacity) {
                    System.out.println("Course is full!");
                    return;
                }

                if (student.courses.contains(course)) {
                    System.out.println("You are already registered for this course.");
                    return;
                }

                student.courses.add(course);
                course.registered++;

                System.out.println("Course registered successfully!");
                return;
            }
        }

        System.out.println("Course not found.");
    }

    static void dropCourse(Student student) {

        student.showCourses();

        if (student.courses.isEmpty()) {
            return;
        }

        System.out.print("\nEnter course code to drop: ");
        String code = sc.nextLine();

        for (Course course : student.courses) {

            if (course.code.equalsIgnoreCase(code)) {

                student.courses.remove(course);
                course.registered--;

                System.out.println("Course dropped successfully!");
                return;
            }
        }

        System.out.println("You are not registered for this course.");
    }
}