// public class Main {
//     public static void main(String[] args) {
//         Faculty faculty1 = new Faculty("Dr. Brown", "F12345", "Computer Science");
//         Faculty faculty2 = new Faculty("Dr. Green", "F67890", "Mathematics");

//         Course course1 = new Course("OOP", "CS101", 2, faculty1);
//         Course course2 = new Course("Algorithms", "CS102", 1, faculty1);
//         Course course3 = new Course("Calculus", "MATH101", 3, faculty2);

//         Student student1 = new Student("John Doe", "S12345", "Computer Science");
//         Student student2 = new Student("Jane Smith", "S67890", "Mathematics");

//         Enrollment enrollment = new Enrollment();
//         enrollment.enroll(student1, course1);
//         enrollment.enroll(student2, course1);
//         enrollment.enroll(student2, course2);
//         enrollment.enroll(student1, course2); // This should fail as the course is full
//         enrollment.enroll(student2, course3);

//         student1.displayInfo();
//         faculty1.displayInfo();
//         course1.displayCourseInfo();

//         System.out.println("Courses enrolled by " + student1.getName() + ":");
//         for (Course course : enrollment.getCourses(student1)) {
//             System.out.println(course.getCourseName());
//         }
//     }
// }