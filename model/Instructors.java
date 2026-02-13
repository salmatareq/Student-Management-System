package model;
import Enums.Role;
import java.util.ArrayList;

public class Instructors extends PersonInfo {
    static int idcounter = 0;
    ArrayList<Integer> assignedCourses;

    public Instructors(String name) {
        super(++idcounter, name, Role.INSTRUCTOR);
        assignedCourses = new ArrayList<Integer>();
    }

    @Override
    public Role getrole() {
        return this.role;
    }

    public void setrole(Role role) {
        this.role = role;
    }

    @Override
    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }
     public void setName(String name){
        this.name=name;
     }

    public ArrayList getassignedCourses() {
        return assignedCourses;
    }

    public  boolean isAssignedtoCourse(int courseId){
return assignedCourses.contains(courseId);
    }

}