package model;
import java.util.ArrayList;

public class Courses {
    static int coursesCounter = 0;
    int courseId;
    String courseName;
    Instructors instructor;
    ArrayList<Integer> students;

    public Courses(
            String courseName) {
        this.courseId = ++coursesCounter;
        this.courseName = courseName;
        students = new ArrayList<>();


    }

    public Instructors getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructors instructor) {
        this.instructor = instructor;
    }

    public int getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }


    public void addStudent(int id) {
        students.add(id);
    }

    public ArrayList enrolledStudents() {
        return students;
    }
}