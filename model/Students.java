package model;

import Enums.Role;

import java.util.ArrayList;
import java.util.HashMap;

public class Students extends PersonInfo {
    static int studentsCounter = 0;
    ArrayList<Courses> courseslist;
    HashMap<Integer, Integer> coursesDegree;

    public Students(String name) {
        super(++studentsCounter, name, Role.STUDENT);
        courseslist = new ArrayList<>();
        coursesDegree = new HashMap<>();
    }
    @Override
    public int getId() {
        return this.id;
    }
    @Override
    public Role getrole() {
        return this.role;
    }

    public void setrole(Role role) {
        this.role = role;
    }

    public String getStudendName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public ArrayList<Courses> getCourseslist() {
        return courseslist;
    }

    public HashMap<Integer, Integer> getCoursesDegree() {
        return coursesDegree;
    }

    public void setCoursesDegree(int courseDegree, int courseId) {
        coursesDegree.put(courseId, courseDegree);
    }

    public int getCourseDegree(int courseId) {
        return coursesDegree.get(courseId);
    }

    public void Enroll(Courses c) {
        courseslist.add(c);
    }


    public boolean isEnrolledInCourse(int courseId) {
       return courseslist.contains(courseId);
    }



}