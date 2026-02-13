import dataStorge.Repository;
import model.Admin;
import model.Instructors;
import model.Students;
import services.EducationService;


void main() {

    Repository repo = new Repository();
    EducationService service = new EducationService(repo);
    Admin admin = new Admin("salma", "admin");
    if (admin.login("admin")) {
        System.out.println("login  succeeded");
    }
        try {
            service.addCourse("OOP", admin);
            service.addCourse("Database", admin);
            service.addCourse("Algorithms", admin);
            service.addCourse("OOP", admin);

        } catch (Exception e) {
            System.out.println("AddCourse Error: " + e.getMessage());
        }
        finally {
            System.out.println("Added courses :");
            repo.displayCourses();
            System.out.println("\n");
        }

        try {
            service.addStudent("Mona", admin);
            service.addStudent("Omar", admin);

        } catch (Exception e) {
            System.out.println("AddStudent Error: " + e.getMessage());
        }

        try {
            service.assignInstructorToCourse("Ali", 1, admin);
            System.out.println("Successful assignment");

        } catch (Exception e) {
            System.out.println("AssignInstructor Error: " + e.getMessage());
        }

        Students mona = repo.findStudent(1);
        Students omar = repo.findStudent(2);

        Instructors instructorAli=repo.findInstructor("Ali");
        try {
            service.enroll(mona.getId(), 1, mona);

            service.enroll(mona.getId(), 2, admin);


            service.enroll(omar.getId(), 3, instructorAli);

        } catch (Exception e) {
            System.out.println("Enroll Error: " + e.getMessage());
        }

        try {
            System.out.println("Mona courses:");
            service.displaystudentcourses(1, mona);

            System.out.println("Omar courses:");
            service.displaystudentcourses(2, admin);

        } catch (Exception e) {
            System.out.println("Display Error: " + e.getMessage());
        }

        try {
            service.setdegree(1, 1, 95, instructorAli);

        } catch (Exception e) {
            System.out.println("SetDegree Error: " + e.getMessage());
        }

        try {

            int degree = service.getcoursedegree(1, 1, mona);
            System.out.println("Mona degree in course 1 = " + degree);

            service.getcoursedegree(1, 1, omar);

        } catch (Exception e) {
            System.out.println("GetDegree Error: " + e.getMessage());
        }
        try {
            service.getcoursedegree(99, 1, admin);

        } catch (Exception e) {
            System.out.println("NotFound Test: " + e.getMessage());
        }



}
