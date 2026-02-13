package services;

import Enums.Role;
import Exceptions.DuplicateAssignmentException;
import Exceptions.NotFoundException;
import Exceptions.UnauthorizedException;
import dataStorge.Repository;
import model.Courses;
import model.Instructors;
import model.PersonInfo;
import model.Students;
import utils.ExceptionsMessages;

import java.util.ArrayList;


public class EducationService {
    private Repository repository;

    public EducationService(Repository repository) {
        this.repository = repository;
    }

    public void addStudent(String name, PersonInfo person) throws Exception {
        validateActor(person);
        if (person.getrole() != Role.ADMIN) {
            throw new UnauthorizedException(ExceptionsMessages.UNAUTHORIZED_OPERATION);
        }
        if (repository.findStudent(name) != null) {
           throw new DuplicateAssignmentException(ExceptionsMessages.STUDENT_ALREADY_EXISTS);
        }

        Students newStudent = new Students(name);
        repository.addStudent(newStudent);

    }

    public void addCourse(String name, PersonInfo person) {
        validateActor(person);
        if (person.getrole() != Role.ADMIN) {
            throw new UnauthorizedException(ExceptionsMessages.UNAUTHORIZED_OPERATION);
        }
        if (repository.findCourse(name) != null) {
            throw new DuplicateAssignmentException(ExceptionsMessages.COURSE_ALREADY_EXISTS);
        }
        Courses newCourse = new Courses(name);
        repository.addCourse(newCourse);

    }


    public void addInstructor(String name, PersonInfo person) {
        validateActor(person);
        if (person.getrole() != Role.ADMIN) {
            throw new UnauthorizedException(ExceptionsMessages.UNAUTHORIZED_OPERATION);
        }
        if (repository.findInstructor(name) != null) {
            throw new DuplicateAssignmentException(ExceptionsMessages.INSTRUCTOR_ALREADY_EXISTS);
        }
        Instructors newInstructor = new Instructors(name);
        repository.addInstructor(newInstructor);
    }


    public void enroll(int studentId, int courseId, PersonInfo person) {
        validateActor(person);
        if (person.getrole() != Role.ADMIN && (person.getrole() != Role.STUDENT || person.getId() != studentId)) {
            throw new UnauthorizedException(ExceptionsMessages.UNAUTHORIZED_OPERATION);
        }

        Courses course = repository.findCourse(courseId);
        Students newStudent = repository.findStudent(studentId);

        validateActor((PersonInfo) newStudent);
        if (course == null)
           throw new NotFoundException(ExceptionsMessages.COURSE_NOT_FOUND);
        if(newStudent.isEnrolledInCourse(courseId))
            throw new DuplicateAssignmentException(ExceptionsMessages.DUPLICATE_ACTION);
            newStudent.Enroll(course);
        course.addStudent(studentId);
    }


    public void setdegree(int studentId, int courseId, int degree, PersonInfo person) {
        validateActor(person);
        Students newStudent = repository.findStudent(studentId);
        if (newStudent == null)
            throw new NotFoundException(ExceptionsMessages.STUDENT_NOT_FOUND);
        if (person.getrole() != Role.INSTRUCTOR) {
            throw new UnauthorizedException(ExceptionsMessages.UNAUTHORIZED_OPERATION);
        }




            if ( newStudent.isEnrolledInCourse(courseId)) {
                Instructors instructor= (Instructors) person;
if(!instructor.isAssignedtoCourse(courseId)){
   throw new UnauthorizedException(ExceptionsMessages.UNAUTHORIZED_OPERATION);
}
      if(!newStudent.isEnrolledInCourse(courseId)) {
          throw new NotFoundException(ExceptionsMessages.STUDENT_NOT_ENROLLED_IN_COURSE);

      }         newStudent.setCoursesDegree(courseId, degree);
            }



    }

    public void displaystudentcourses(int studentId,PersonInfo person) {
        validateActor(person);
        if (person.getrole().equals(Role.STUDENT) && person.getId() != studentId) {  throw new UnauthorizedException(ExceptionsMessages.UNAUTHORIZED_OPERATION);}
        Students newStudent = repository.findStudent(studentId);
        if(newStudent ==null){
           throw new NotFoundException(ExceptionsMessages.STUDENT_NOT_FOUND);
        }

        ArrayList<Courses> courseslist=newStudent.getCourseslist();
        for(Courses c:courseslist)
            System.out.print(c.getCourseName()+" , ");

    }

    public void assignInstructorToCourse(String name, int courseId, PersonInfo person) {
        validateActor(person);
        if (person.getrole() != Role.ADMIN) {
            throw new UnauthorizedException(ExceptionsMessages.UNAUTHORIZED_OPERATION);
        }
        Instructors instructor = repository.findInstructor(name);
        Courses course = repository.findCourse(courseId);
        if (instructor == null )
            throw new NotFoundException(ExceptionsMessages.INSTRUCTOR_NOT_FOUND);
           if(course == null)
               throw new NotFoundException(ExceptionsMessages.COURSE_NOT_FOUND);
               if(instructor.isAssignedtoCourse(courseId)) {
                   throw new DuplicateAssignmentException(ExceptionsMessages.DUPLICATE_ACTION);
               }
            course.setInstructor(instructor);
        }






    public int getcoursedegree(int courseId, int studentid, PersonInfo person) {
        validateActor(person);
        if (person.getrole()==Role.STUDENT && person.getId() != studentid) {
            throw new UnauthorizedException(ExceptionsMessages.UNAUTHORIZED_OPERATION);
        }

            Students student = repository.findStudent(studentid);
            if (student == null) {
                throw new NotFoundException(ExceptionsMessages.STUDENT_NOT_FOUND);
            }
        if (repository.findCourse(courseId) == null) {
            throw new NotFoundException(ExceptionsMessages.COURSE_NOT_FOUND);
        }
                if (!student.isEnrolledInCourse(courseId)) {
                    throw new NotFoundException(ExceptionsMessages.STUDENT_NOT_ENROLLED_IN_COURSE);
                }


                return student.getCourseDegree(courseId);

            }
    private void validateActor(PersonInfo person){
        if(person == null)
            throw new UnauthorizedException(
                    ExceptionsMessages.UNAUTHORIZED_OPERATION);
    }

    }


