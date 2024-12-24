package faby.faby_BE.rest;

import faby.faby_BE.entity.Student;
import faby.faby_BE.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestController {

    @Value("${spring.application.name}")
    private String applicationName;

    @Autowired
    private StudentService studentService;
    private Student student;


    @GetMapping("/getStudent")
    public String getStudent(){
        student = studentService.readStudent();
        System.out.println(student.getFirstName());
        return "Hello " +student.getFirstName() + " -" + applicationName;
    }

    @GetMapping("/helloFaby")
    public String sayHello(){
        System.out.println("hello fabyioioio");
        return "Hello Faby - " + applicationName;
    }

    // Endpoint per creare un nuovo studente


    @PostMapping("/saveStudent")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student savedStudent = studentService.saveStudent(student);
        System.out.println("Student saved: " + savedStudent);
        // Restituisci lo studente salvato avvolto in un ResponseEntity con lo stato HTTP 201 (Created)
        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
    }




}
