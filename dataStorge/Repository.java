package dataStorge;

import model.Instructors;
import model.Students;
import model.Courses;

import java.util.ArrayList;

public class Repository {
     ArrayList<Courses> courseslist;
     ArrayList<Students> studentslist;
     ArrayList<Instructors> instructorslist;

    public Repository(){
        courseslist=new ArrayList<>();
        studentslist=new ArrayList<>();
        instructorslist=new ArrayList<>();
    }
    public Students findStudent(String name) {
        Students newStudent = null;
        for (Students s : studentslist) {
            if (s.getStudendName().equals(name)) {
                newStudent = s;

            }
        }
        return newStudent;
    }
    public Students findStudent(int id) {
        Students newStudent = null;
        for (Students s : studentslist) {
            if (s.getId()==id) {
                newStudent = s;

            }
        }
        return newStudent;
    }


    public Instructors findInstructor(String instructorname){
       Instructors instructor = null;
        for (Instructors i : instructorslist) {
            if (i.getName().equals( instructorname)) {
               instructor = i;

            }
        }
        return instructor;
    }
    public  Courses findCourse(String name){
       Courses course = null;
        for (Courses c : courseslist) {
            if (c.getCourseName() .equals(name)) {
                course = c;

            }
        }
        return course;
    }
    public  Courses findCourse(int id){
        Courses course = null;
        for (Courses c : courseslist) {
            if (c.getCourseId() ==id) {
                course = c;

            }
        }
        return course;
    }
    public void addStudent(Students student){
        studentslist.add(student);
    }
    public void addCourse(Courses course){
        courseslist.add(course);
    }

    public void addInstructor(Instructors instructor){
        instructorslist.add(instructor);
    }
    public void displayCourses(){
        for(Courses c:courseslist)
            System.out.print(c.getCourseName()+" , ");
    }

}
