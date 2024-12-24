package faby.faby_BE.service;

import faby.faby_BE.dao.StudentDAO;
import faby.faby_BE.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class StudentService {


    private final StudentDAO studentDAO;
    private final PasswordEncoder passwordEncoder;

    public Student readStudent() {

        // create the student object
        System.out.println("Creating new student object ...");
        Student tempStudent = new Student("Michelino", "Curci", "mDam@luv2code.com");

        // save the student object
        System.out.println("Saving the student ...");
        studentDAO.save(tempStudent);

        // display id of the saved student
        int theId = tempStudent.getId();
        System.out.println("Saved student. Generated id: " + theId);

        // retrieve student based on the id: primary key
        System.out.println("Retrieving student with id: " + theId);
        Student myStudent = studentDAO.findById(theId);

        // display  student
        System.out.println("Found the student: " + myStudent);

        return myStudent;
    }


    @Autowired
    public StudentService(StudentDAO studentDAO, PasswordEncoder passwordEncoder) {
        this.studentDAO = studentDAO;
        this.passwordEncoder = passwordEncoder;
    }

    public Student saveStudent(Student student) {
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        studentDAO.save(student);
        return student;
    }
}
